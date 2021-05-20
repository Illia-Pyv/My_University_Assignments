package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.NetworkManager;
import edu.kit.informatik.model.constants.Errors;

/**
 * This class is the only command which can exit the application.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandQuit extends Command {

    private static final String COMMAND_NAME = "quit";
    private static final String PARAMETER_REGEX = "";

    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as the regular expression string.
     */
    public CommandQuit() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(PARAMETER_REGEX);
    }
    
    @Override
    public Result execute(String parameters, NetworkManager networkManager) throws InvalidParametersException {
        if (matchesRegex(parameters) != -1) {
            return new Result(ResultType.SUCCESS);
        } else {
            throw new InvalidParametersException(String.format(Errors.NO_PARAMETERS, parameters));
        }
    }
}
