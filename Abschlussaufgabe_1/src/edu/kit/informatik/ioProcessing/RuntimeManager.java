/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.ioProcessing;

import edu.kit.informatik.commands.Command;
import edu.kit.informatik.commands.CommandManager;
import edu.kit.informatik.model.NetworkManager;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Regex;

/**
 * This is the class that is waiting for input and prepares the output.
 * 
 * It is inspired by the Session class of the proposal solution of sheet number
 * 4 but I did not copy it over because I wanted to make some drastic changes.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class RuntimeManager {

    private NetworkManager networkManager = new NetworkManager();
    private CommandManager commandManager = new CommandManager();
    private Input input = new Input();
    private Output output = new Output();
    private boolean quit = false;
    private String parameters = "";
    private Result result;

    /**
     * Starts the interactive session by reading from the specified input supplier.
     * The output gets printed to the specified output consumer. If the input is
     * {@code null} the interaction is exited.
     * 
     * <p>
     * <b>What I changed:</b>
     * 
     * I changed the name of this method from "interactive" to "start" because it
     * sounded better to me. And I also changed the name of "processSingleCommand"
     * to "processIO" because I have a method that I want to execute instead of
     * "processSingleCommand". I then removed the "running" variable because I do
     * not need it.
     * 
     * @author Lucas Alber, Illia Pyvovar
     */
    public void start() {
        while (!this.quit) {
            processIO(input.read());
        }
    }

    private void processIO(String userInput) {
        try {
            result = this.getCommand(userInput).execute(parameters, networkManager);
        } catch (InvalidParametersException invalidParameters) {
            output.print(invalidParameters.getResult());
            return;
        } catch (InvalidCommandException invalidCommand) {
            output.print(invalidCommand.getResult());
            return;
        }
        if (result.getMessage() == null) {
            quit = true;
        } else {
            output.print(result);
        }
    }

    private Command getCommand(String userInput) throws InvalidCommandException {
        Command command = null;
        String commandName = parseCommand(userInput);

        for (int i = 0; i < commandManager.size(); i++) {
            if (commandName.equals(commandManager.getCommandFromList(i).getCommandName())) {
                command = commandManager.getCommandFromList(i);
                return command;
            }
        }
        throw new InvalidCommandException(String.format(Errors.INVALID_COMMAND, commandName));
    }

    private String parseCommand(String input) {
        String commandName = "";
        if (input.length() == 0) {
            return commandName;
        }

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (String.format("%s", character).equals(Regex.SPACE)) {
                parameters = input.substring(commandName.length() + 1, input.length());
                return commandName;
            } else {
                commandName += character;
            }
        }
        parameters = "";
        return commandName;
    }
}
