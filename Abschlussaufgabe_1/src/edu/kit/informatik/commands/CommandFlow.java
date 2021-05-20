package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.NetworkManager;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the flow command which computes the maximal flow
 * between two vertices.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandFlow extends Command {

    private static final String COMMAND_NAME = "flow";
    private static final String PARAMETER_REGEX = Regex.START + Regex.NETWORK_IDENTIFIER + Regex.SPACE
            + Regex.VERTEX_IDENTIFIER + Regex.SPACE + Regex.VERTEX_IDENTIFIER + Regex.END;

    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as the regular expression string.
     */
    public CommandFlow() {
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
            long maximumFlow = 0;
            try {
                maximumFlow = networkManager.flow(parser.getNetworkIdentifier(), parser.getFromVertices()[0],
                        parser.getToVertices()[0]);
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            return new Result(ResultType.SUCCESS, String.format("%s", maximumFlow));
        }
    }

}
