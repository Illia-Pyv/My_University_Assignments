package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.Board;
import edu.kit.informatik.model.Cell;
import edu.kit.informatik.model.ForestCell;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.constants.CellProperties;
import edu.kit.informatik.model.constants.CellProperties.CellType;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Messages;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the command which prints the board in the command line.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandShowBoard extends Command {

    private static final String COMMAND_NAME = "show-board";
    private static final String SHOW_BOARD_PARAMETERS_REGEX = "";

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandShowBoard() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(SHOW_BOARD_PARAMETERS_REGEX);
    }

    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.NO_PARAMETERS);
        } else {
            String message;
            message = processBoard(gameManager.getBoard());
            return new Result(ResultType.SUCCESS, message);
        }
    }

    // takes in a board object and processes it to a readable string
    private String processBoard(Board board) {
        String result = "";
        String specialCharacter;
        for (int i = 0; i < board.getAmountOfRows(); i++) {
            for (int j = 0; j < board.getAmountOfColumns(); j++) {
                Cell thisCell = board.getCellFromCoordinates(i, j);
                // selects a special character such as a newline or a comma
                if ((i == board.getAmountOfRows() - 1) && (j == board.getAmountOfColumns() - 1)) {
                    specialCharacter = "";
                } else if (j == board.getAmountOfColumns() - 1) {
                    specialCharacter = Regex.NEWLINE;
                } else {
                    specialCharacter = Regex.COMMA;
                }
                // checks if the cell is a forest and if not then it is something else and is
                // represented through an 'x'
                if (thisCell.getType().equals(CellType.FOREST)) {
                    ForestCell forestCell = (ForestCell) thisCell;
                    // checks if the forest cell is burning in which case it is represented through
                    // its state, otherwise it is represented through an 'x'
                    if (forestCell.getForestState().equals(CellProperties.FOREST_LIGHTLY_BURNING)
                            || forestCell.getForestState().equals(CellProperties.FOREST_STRONGLY_BURNING)) {
                        result += forestCell.getForestState() + specialCharacter;
                    } else {
                        result += Messages.FIELD_THAT_IS_NOT_BURNING + specialCharacter;
                    }
                } else {
                    result += Messages.FIELD_THAT_IS_NOT_BURNING + specialCharacter;
                }
            }
        }

        return result;
    }
}
