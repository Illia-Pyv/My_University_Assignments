package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.NetworkManager;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the print command which prints all of the sections in
 * the network graph sorted by the identifiers of the vertices in the sections.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandPrint extends Command {

    private static final String COMMAND_NAME = "print";
    private static final String PARAMETER_REGEX = Regex.START + Regex.NETWORK_IDENTIFIER + Regex.END;

    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as the regular expression string.
     */
    public CommandPrint() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(PARAMETER_REGEX);
    }

    @Override
    public Result execute(String parameters, NetworkManager networkManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);
        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);
        } else {
            ParameterParser parser = new ParameterParser(parameters);
            String[][] result;
            String message;
            try {
                result = networkManager.print(parser.getNetworkIdentifier());
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            message = processToMessage(result);
            return new Result(ResultType.SUCCESS, message);
        }
    }

    private String processToMessage(String[][] executionResult) {
        String message = "";
        for (int i = 0; i < executionResult.length; i++) {
            for (int n = 0; n < executionResult[i].length; n++) {
                if (n < executionResult[i].length - 1) {
                    message += executionResult[i][n];
                } else if (i == executionResult.length - 1) {
                    message += executionResult[i][n];
                } else {
                    message += executionResult[i][n] + Regex.NEWLINE;
                }
            }
        }
        return message;
    }

}