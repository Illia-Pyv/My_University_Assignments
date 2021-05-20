package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Messages;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the move command which moves a fire engine from a cell
 * on which the fire engine stands to a specified target cell.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandMove extends Command {

    private static final String COMMAND_NAME = "move";
    private static final String MOVE_PARAMETERS_REGEX = Regex.START + Regex.FIRE_STATION_ID + Regex.POSITIVE_NUMBERS
            + Regex.COMMA + Regex.POSITIVE_NUMBERS + Regex.COMMA + Regex.POSITIVE_NUMBERS + Regex.END;

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandMove() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(MOVE_PARAMETERS_REGEX);
    }

    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);
        } else {
            ParameterParser parser = new ParameterParser(parameters);
            try {
                gameManager.move(parser.getStringParameters()[0], parser.getNumberParameters()[0],
                        parser.getNumberParameters()[1]);
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            return new Result(ResultType.SUCCESS, Messages.SUCCESS);
        }
    }

}
