package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the refill command which is used to refill a fire
 * engine at a lake or a fire station.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandRefill extends Command {

    private static final String COMMAND_NAME = "refill";
    private static final String REFILL_PARAMETERS_REGEX = Regex.START + Regex.FIRE_STATION_ID + Regex.POSITIVE_NUMBERS
            + Regex.END;

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandRefill() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(REFILL_PARAMETERS_REGEX);
    }

    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);
        } else {
            ParameterParser parser = new ParameterParser(parameters);
            String message = "";
            try {
                message += gameManager.refill(parser.getStringParameters()[0]);
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            return new Result(ResultType.SUCCESS, message);
        }
    }
}
