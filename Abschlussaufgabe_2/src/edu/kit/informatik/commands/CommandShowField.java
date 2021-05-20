package edu.kit.informatik.commands;

import java.util.ArrayList;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.Cell;
import edu.kit.informatik.model.FireEngine;
import edu.kit.informatik.model.ForestCell;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.constants.CellProperties.CellType;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the command which shows the fields attributes and who
 * is standing on this field.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandShowField extends Command {
    private static final String COMMAND_NAME = "show-field";
    private static final String SHOW_FIELD_PARAMETERS_REGEX = Regex.START + Regex.POSITIVE_NUMBERS + Regex.COMMA
            + Regex.POSITIVE_NUMBERS + Regex.END;

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandShowField() {
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
                message = processCell(
                        gameManager.getCell(parser.getNumberParameters()[0], parser.getNumberParameters()[1]));
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            return new Result(ResultType.SUCCESS, message);
        }
    }

    private String processCell(Cell cell) {
        String message = "";
        if (cell.getType().equals(CellType.FOREST)) {
            ForestCell forestCell = (ForestCell) cell;
            ArrayList<FireEngine> fireEngines = sortList(forestCell.getObjectList());
            message = forestCell.getForestState();
            for (FireEngine fireEngine : fireEngines) {
                message += Regex.COMMA + fireEngine.getIdentifier();
            }
        } else {
            message = cell.getIdentifier();
        }
        return message;
    }

    // takes in a Cell object and returns an array list of fire engines
    private ArrayList<FireEngine> sortList(ArrayList<FireEngine> fireEngineList) {
        String[] listOfIDs = new String[fireEngineList.size()];
        ArrayList<FireEngine> sortedList = new ArrayList<>();
        int loopVariable = 0;
        for (FireEngine fireEngine : fireEngineList) {
            listOfIDs[loopVariable] = fireEngine.getIdentifier();
            loopVariable++;
        }
        // sorts the list of fire engine identifiers
        getSorter().sortAscending(listOfIDs);
        for (String fireEngineID : listOfIDs) {
            for (FireEngine fireEngine : fireEngineList) {
                if (fireEngine.getIdentifier().equals(fireEngineID)) {
                    sortedList.add(fireEngine);
                    break;
                }
            }
        }
        return sortedList;
    }
}
