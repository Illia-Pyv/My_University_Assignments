package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the buy fire engine command which lets the player buy
 * another fire engine if he has enough reputation points.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandBuyFireEngine extends Command {

    private static final String COMMAND_NAME = "buy-fire-engine";
    private static final String SHOW_FIELD_PARAMETERS_REGEX = Regex.START + Regex.POSITIVE_NUMBERS + Regex.COMMA
            + Regex.POSITIVE_NUMBERS + Regex.END;

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandBuyFireEngine() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(SHOW_FIELD_PARAMETERS_REGEX);
    }

    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);
        } else {
            ParameterParser parser = new ParameterParser(parameters);
            String message;
            try {
                message = String.format("%s",
                        gameManager.buyFireEngine(parser.getNumberParameters()[0], parser.getNumberParameters()[1]));
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            return new Result(ResultType.SUCCESS, message);
        }
    }

}
