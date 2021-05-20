package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.NetworkManager;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Messages;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the list command which lists either all networks with
 * their amount of vertices or all elements of a specific network, depending on
 * the input parameters.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandList extends Command {

    private static final String COMMAND_NAME = "list";
    private static final String PARAMETER_REGEX_ALL_NETWORKS = "";
    private static final String PARAMETER_REGEX_NETWORK_ELEMENTS = Regex.START + Regex.NETWORK_IDENTIFIER
            + Regex.END;

    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as all of the regular expression Strings.
     */
    public CommandList() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(PARAMETER_REGEX_ALL_NETWORKS);
        setParameterRegex(PARAMETER_REGEX_NETWORK_ELEMENTS);
    }

    @Override
    public Result execute(String parameters, NetworkManager networkManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);
        
        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);   
        } else if (getParameterRegex(index).equals(PARAMETER_REGEX_ALL_NETWORKS)) {
            String[][] result = networkManager.list();
            if (result == null) {
                return new Result(ResultType.SUCCESS, Messages.EMPTY);
            }
            String message = processToMessage(result);
            return new Result(ResultType.SUCCESS, message);
        } else {
            ParameterParser parser = new ParameterParser(parameters);
            String[][] result;
            try {
                result = networkManager.list(parser.getNetworkIdentifier());
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            if (result == null) {
                return new Result(ResultType.SUCCESS, Messages.EMPTY);
            }
            String message = processToMessage(result);
            return new Result(ResultType.SUCCESS, message);
        }
    }
    
    private String processToMessage(String[][] executionResult) {
        String message = "";
        for (int i = 0; i < executionResult.length; i++) {
            for (int n = 0; n < executionResult[i].length; n++) {
                if (n < executionResult[i].length - 1) {
                    message += executionResult[i][n] + Regex.SPACE;
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
