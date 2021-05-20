/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.kit.informatik.core.Input;
import edu.kit.informatik.core.Output;
import edu.kit.informatik.core.Pair;
import edu.kit.informatik.view.parameter.ParseException;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.model.resources.Errors;
import edu.kit.informatik.view.command.Command;
import edu.kit.informatik.view.command.CommandParser;
import edu.kit.informatik.view.command.CommandSupplier;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;

/**
 * This class describes a session for command execution.
 * 
 * Allows the execution of {@link Command} which may be 
 * provided my a {@link CommandSupplier}.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class Session {

    private final List<CommandSupplier> supplier = new ArrayList<>();
    private final Output output;
    private final Output errOutput;
    private final Input input;
    private final CommandParser parser;
    private boolean quit;
    private boolean running;

    /**
     * Constructs a new instance.
     *
     * @param output the output consumer
     * @param errOutput the error output consumer
     * @param input the input supplier
     * @param parser the parser used to decode the input strings
     */
    public Session(final Output output, final Output errOutput, final Input input, final CommandParser parser) {
        this.input = input;
        this.output = output;
        this.errOutput = errOutput;
        this.parser = parser;
    }


    /**
     * Starts the interactive session by reading from the specified input supplier.
     * The output gets printed to the specified output consumer.
     * If the input is {@code null} the interaction is exited.
     */
    public void interactive() {
        this.running = true;
        while (!this.quit) {
            this.processSingleCommand();
        }
        this.quit = false;
        this.running = false;
    }

    /**
     * Processes a single input.
     * 
     */
    private void processSingleCommand() {
        final String commandStr = this.input.read();
        if (commandStr == null) {
            return;
        }

        final Pair<String, List<String>> parseResult = this.parser.parse(commandStr);       

        if (parseResult == null) {
            this.errOutput.output(String.format(Errors.COMMAND_REGEX_NOT_MATCHED, commandStr));
            return;
        }

        final String commandName = parseResult.getFirst();
        final List<String> params = parseResult.getSecond();
        final Command command = this.findCommand(commandName);

        if (command == null) {
            this.errOutput.output(String.format(Errors.COMMAND_NOT_KNOWN, commandName));
            return;
        }

        final int parameterCount = command.getParameters().size();
        int nonOptionalParameterCount = 0;
        for (Parameter<?> p : command.getParameters()) {
            if (!p.isOptional()) {
                nonOptionalParameterCount++;
            }
        }

        if (!this.validParameterCount(params, parameterCount, nonOptionalParameterCount)) {
            return;
        }

        final ParameterBundle bundle = this.createBundle(params, command);
        if (bundle != null) {
            this.executeCommand(command, bundle);
        }
    }

    /**
     * Returns true if count valid, false otherwise.
     * 
     * In case of a invalid number of parameter, a error is printed to 
     * the error output.
     *  
     * @param params the list of parameters
     * @param paramCount the number of max expected parameter
     * @param nonOptionalParamCount the number of min expected parameter
     * @return true if count valid, false otherwise
     */
    private boolean validParameterCount(final List<String> params, final int paramCount, 
            final int nonOptionalParamCount) {
        if (params.size() > paramCount || params.size() < nonOptionalParamCount) {
            if (paramCount == nonOptionalParamCount) {
                this.errOutput.output(String.format(Errors.PARAMETER_COUNT_EXACTLY, 
                    paramCount,
                    params.size()));
            } else {
                this.errOutput.output(String.format(Errors.PARAMETER_COUNT_BETWEEN, 
                    nonOptionalParamCount,
                    paramCount,
                    params.size()));
            }
            return false;
        }
        return true;
    }

    /**
     * Parses the parameters according to the command.
     * Returns a {@link ParameterBundle} to be used to execute the command.
     * 
     * If the parsing fails, a error message is sent to the error consumer.
     *
     * @param params a list of the parameters as String
     * @param command the command
     *
     * @return the parameter bundle for the command. Or null if parsing did fail.
     */
    private ParameterBundle createBundle(final List<String> params, final Command command) {
        // Parse Parameters and create bundle
        final ParameterBundle bundle = new ParameterBundle();
        for (int i = 0; i < params.size(); i++) {
            final String paramStr = params.get(i);
            final Parameter<?> p = command.getParameters().get(i);
            try {
                bundle.put(p, p.fromString(paramStr));
            } catch (ParseException e) {
                if (p.getDescription() == null) {
                    this.errOutput.output(String.format(Errors.PARAMETER_PARSING, i, e.getMessage()));
                } else {
                    this.errOutput.output(String.format(Errors.PARAMETER_PARSING_DESC, 
                            p.getDescription(), i, e.getMessage()));
                }
                return null;
            }
        }
        return bundle;
    }

    /**
     * Executes the given {@link Command} with the given {@link ParameterBundle}.
     * Outputs the {@link Result} to the output-consumer or in case of failure to the errOutput-consumer.
     * 
     * In case of success a message is only printed to the output consumer, if the {@link Result} carries one.
     * In case of failure the message of the {@link Result} is printed or, of none is contained, 
     * a general purpose message is printed.
     *
     * @param command the command
     * @param bundle the parameter bundle
     */
    private void executeCommand(final Command command, final ParameterBundle bundle) {
        final Result result = command.apply(bundle);
        switch (result.getType()) {
            case SUCCESS:
                if (result.getMessage() != null) {
                    this.output.output(result.getMessage());
                }
                break;
            case FAILURE:
                if (result.getMessage() != null) {
                    this.errOutput.output(result.getMessage());
                } else {
                    this.errOutput.output(Errors.COMMAND_ENDED_ERROR);
                }
                break;
            default:
                throw new IllegalStateException(String.format(Errors.NOT_IMPLEMENTED, 
                        result.getType().toString()));
        }
    }

    /**
     * Searches in the known {@link CommandSupplier} for a command which does match the 
     * given command name.
     *
     * @param commandStr the command name string
     *
     * @return the {@link Command} or {@code null} if there is no command matching the command name.
     */
    private Command findCommand(final String commandStr) {
        // Find matching command
        for (CommandSupplier cProvider : this.supplier) {
            for (Command com : cProvider.get()) {
                final String commandNameRegex = com.getRegex();
                final Pattern cRegex = Pattern.compile(commandNameRegex);
                final Matcher cMatcher = cRegex.matcher(commandStr);
                
                if (cMatcher.matches()) {
                    return com;
                }
            }
        }
        return null;
    }


    /**
     * Adds a command supplier.
     *
     * @param supplier  The supplier to add
     */
    public void addCommandSupplier(final CommandSupplier supplier) {
        this.supplier.add(supplier);
    }

    /**
     * Removes a command supplier from this IO Interface.
     *
     * @param supplier the supplier to remove
     *
     * @return true if this {@link Session} contained the specified supplier
     */
    public boolean removeCommandSupplier(final CommandSupplier supplier) {
        return this.supplier.remove(supplier);
    }

    /**
     * Quits any running interactive session.
     */
    public void quit() {
        if (this.running) {
            this.quit = true;
        }
    }

}

