package edu.kit.informatik.model;

import java.util.ArrayList;
import java.util.regex.Pattern;

import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the requirements that must be fulfilled for the board
 * configuration to be valid.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class BoardRequirements {

    private static final int AMOUNT_OF_SPECIAL_CELLS = 4;
    private static final int HALF_OF_BOARD = 2;
    private ArrayList<String> stationList = new ArrayList<>();
    private ArrayList<String> lakeList = new ArrayList<>();
    private ArrayList<String> fireEngineList = new ArrayList<>();
    private InsertionSort sorter = new InsertionSort();
    private int lastRow;
    private int lastColumn;

    /**
     * This method checks if all requirements are fulfilled for the board
     * configuration to be valid.
     * 
     * @param rows       the number of rows the board has
     * @param columns    the number of columns the board has
     * @param parameters the parameters to be checked if they fulfill the
     *                   requirements
     * @return Returns true if the requirements are fulfilled, the exception handles
     *         the rest
     * @throws LogicException an exception which is thrown if one of the
     *                        requirements is not fulfilled
     */
    public boolean fulfillsRequirements(int rows, int columns, String[] parameters) throws LogicException {
        lastRow = rows - 1;
        lastColumn = columns - 1;
        everyCellCovered(rows, columns, parameters);
        String[][] temporaryBoard = constructTemporaryBoard(rows, columns, parameters);
        stationsPlacedCorrectly(temporaryBoard);
        fireEnginesPlacedCorrectly(temporaryBoard);
        lakesPlacedCorrectly(temporaryBoard);
        return true;
    }

    // checks if the parameters array is the same size as rows * columns
    private void everyCellCovered(int rows, int columns, String[] parameters) throws LogicException {
        int amountOfParameters = parameters.length;
        int amountOfCells = rows * columns;
        if (amountOfCells > amountOfParameters) {
            throw new LogicException(Errors.TOO_FEW_PARAMETERS);
        } else if (amountOfCells < amountOfParameters) {
            throw new LogicException(Errors.TOO_MANY_PARAMETERS);
        }
    }

    private void fireEnginesPlacedCorrectly(String[][] board) throws LogicException {
        if (fireEngineList.size() < AMOUNT_OF_SPECIAL_CELLS) {
            throw new LogicException(Errors.LESS_THAN_FOUR_FIRE_ENGINES);
        } else if (fireEngineList.size() > AMOUNT_OF_SPECIAL_CELLS) {
            throw new LogicException(Errors.MORE_THAN_FOUR_FIRE_ENGINES);
        }
        String[] fireEngines = new String[fireEngineList.size()];
        fireEngineList.toArray(fireEngines);
        sorter.sortAscending(fireEngines);
        int loopRow = 1;
        int loopColumn = 1;
        for (String fireEngine : fireEngines) {
            // checks if the fire engines are placed correctly
            if (!board[loopRow][loopColumn].equals(fireEngine)) {
                throw new LogicException(
                        String.format(Errors.FIRE_ENGINE_MISPLACED, fireEngine));
            } else {
                fireEngineList.remove(fireEngine);
                if (fireEngineList.contains(fireEngine)) {
                    throw new LogicException(
                            String.format(Errors.DOUBLE_INITIALIZATION_FIRE_ENGINES, fireEngine));
                }
            }
            if (loopRow == 1 && loopColumn == 1) {
                loopRow = lastRow - 1;
                loopColumn = lastColumn - 1;
            } else if (loopRow == lastRow - 1 && loopColumn == lastColumn - 1) {
                loopRow = lastRow - 1;
                loopColumn = 1;
            } else if (loopRow == lastRow - 1 && loopColumn == 1) {
                loopRow = 1;
                loopColumn = lastColumn - 1;
            }
        }
    }

    private void stationsPlacedCorrectly(String[][] board) throws LogicException {
        if (stationList.size() < AMOUNT_OF_SPECIAL_CELLS) {
            throw new LogicException(Errors.LESS_THAN_FOUR_STATIONS);
        } else if (stationList.size() > AMOUNT_OF_SPECIAL_CELLS) {
            throw new LogicException(Errors.MORE_THAN_FOUR_STATIONS);
        }
        String[] stations = new String[stationList.size()];
        stationList.toArray(stations);
        sorter.sortAscending(stations);
        int loopRow = 0;
        int loopColumn = 0;
        for (String station : stations) {
            // checks if the fire stations are placed correctly
            if (!board[loopRow][loopColumn].equals(station)) {
                throw new LogicException(
                        String.format(Errors.STATION_MISPLACED, station));
            } else {
                stationList.remove(station);
                if (stationList.contains(station)) {
                    throw new LogicException(
                            String.format(Errors.DOUBLE_INITIALIZATION_STATIONS, station));
                }
            }
            if (loopRow == 0 && loopColumn == 0) {
                loopRow = lastRow;
                loopColumn = lastColumn;
            } else if (loopRow == lastRow && loopColumn == lastColumn) {
                loopRow = lastRow;
                loopColumn = 0;
            } else if (loopRow == lastRow && loopColumn == 0) {
                loopRow = 0;
                loopColumn = lastColumn;
            }
        }
    }

    private void lakesPlacedCorrectly(String[][] board) throws LogicException {
        if (lakeList.size() < AMOUNT_OF_SPECIAL_CELLS) {
            throw new LogicException(Errors.LESS_THAN_FOUR_LAKES);
        } else if (lakeList.size() > AMOUNT_OF_SPECIAL_CELLS) {
            throw new LogicException(Errors.MORE_THAN_FOUR_LAKES);
        }
        int posOfLakeOnRow = lastColumn / HALF_OF_BOARD;
        int posOfLakeOnColumn = lastRow / HALF_OF_BOARD;
        int[] rows = {0, posOfLakeOnColumn};
        int[] columns = {0, posOfLakeOnRow};
        for (String lake : lakeList) {
            for (int row : rows) {
                for (int column : columns) {
                    if (row == 0 && column == 0) {
                        continue;
                    } else if (row != 0 && column != 0) {
                        continue;
                    }
                    if (!board[row][column].equals(lake)) {
                        throw new LogicException(Errors.LAKE_MISPLACED);
                    }
                }
            }
        }
    }

    private String[][] constructTemporaryBoard(int rows, int columns, String[] parameters) {
        String[][] temporaryBoard = new String[rows][columns];
        int loopVariable = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (Pattern.matches(Regex.FIRE_STATION_ID, parameters[loopVariable])) {
                    stationList.add(parameters[loopVariable]);
                } else if (Pattern.matches(Regex.FIRE_ENGINE, parameters[loopVariable])) {
                    fireEngineList.add(parameters[loopVariable]);
                } else if (Pattern.matches((Regex.LAKE), parameters[loopVariable])) {
                    lakeList.add(parameters[loopVariable]);
                }
                temporaryBoard[i][j] = parameters[loopVariable];
                loopVariable++;
            }
        }
        return temporaryBoard;
    }
}
