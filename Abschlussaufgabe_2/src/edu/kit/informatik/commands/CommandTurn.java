package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.Player;
import edu.kit.informatik.model.constants.Errors;

/**
 * This class represents the turn command which sets the next player in the
 * queue for the round as the current player.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandTurn extends Command {

    private static final String COMMAND_NAME = "turn";
    private static final String TURN_PARAMETERS_REGEX = "";

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandTurn() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(TURN_PARAMETERS_REGEX);
    }
    
    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);
        } else {
            Player player;
            try {
                player = gameManager.turn();
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            return new Result(ResultType.SUCCESS, player.getName());
        }
    }

}
