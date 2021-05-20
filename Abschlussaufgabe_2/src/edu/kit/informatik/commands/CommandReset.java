package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Messages;

/**
 * This class represents the reset command which resets the game to the board
 * configuration at which the first game was initialized without leaving.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandReset extends Command {

    private static final String COMMAND_NAME = "reset";
    private static final String RESET_PARAMETERS_REGEX = "";

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandReset() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(RESET_PARAMETERS_REGEX);
    }

    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.NO_PARAMETERS);
        } else {
            try {
                gameManager.reset();
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            return new Result(ResultType.SUCCESS, Messages.SUCCESS);
        }
    }

}
