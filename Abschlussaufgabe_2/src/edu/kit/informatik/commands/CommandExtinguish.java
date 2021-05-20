package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.FireEngine;
import edu.kit.informatik.model.ForestCell;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Messages;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the command used to extinguish a fire on a forest cell.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandExtinguish extends Command {

    private static final String COMMAND_NAME = "extinguish";
    private static final String EXTINGUISH_PARAMETERS_REGEX = Regex.START + Regex.FIRE_STATION_ID
            + Regex.POSITIVE_NUMBERS + Regex.COMMA + Regex.POSITIVE_NUMBERS + Regex.COMMA + Regex.POSITIVE_NUMBERS
            + Regex.END;

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandExtinguish() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(EXTINGUISH_PARAMETERS_REGEX);
    }

    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);
        } else {
            ParameterParser parser = new ParameterParser(parameters);
            FireEngine fireEngine;
            String message;
            try {
                fireEngine = gameManager.extinguish(parser.getStringParameters()[0], parser.getNumberParameters()[0],
                        parser.getNumberParameters()[1]);
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            if (fireEngine == null) {
                return new Result(ResultType.SUCCESS, Messages.WON_GAME);
            } else {
                message = processToString(fireEngine);
            }
            return new Result(ResultType.SUCCESS, message);
        }
    }

    // takes in a fire engine object and returns a string
    private String processToString(FireEngine fireEngine) {
        String message = "";
        int extinguishedForestCellsSize = fireEngine.getAlreadyExtinguishedCells().size();
        ForestCell extinguishedCell = (ForestCell) fireEngine.getAlreadyExtinguishedCells()
                .get(extinguishedForestCellsSize - 1);
        message = extinguishedCell.getForestState() + Regex.COMMA + fireEngine.getActionPoints();
        return message;
    }

}
