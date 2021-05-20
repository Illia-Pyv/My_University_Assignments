package edu.kit.informatik.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Pattern;
import edu.kit.informatik.model.constants.CellProperties;
import edu.kit.informatik.model.constants.CellProperties.CellType;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the board of the fire breaker game.
 * <p>
 * It consists of the cell objects which divide themselves into more concrete
 * cell types such as:<br>
 * ForestCell, FireStationCell and LakeCell.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Board {

    /** The board stays constant throughout the game. */
    private Cell[][] board;
    /** The size of the board. */
    private int amountOfRows;
    private int amountOfColumns;
    /** The list of all fires on the board. */
    private ArrayList<ForestCell> fires = new ArrayList<>();
    /** The list of all fire stations on the board. */
    private ArrayList<FireStationCell> fireStations = new ArrayList<>();

    /**
     * This is the constructor of the board class. It sets the individual cells of
     * the board to an initial configuration.
     * 
     * @param rows              the amount of rows of the board
     * @param columns           the amount of columns of the board
     * @param initialParameters the parameters that initialize the board at the
     *                          start of the game
     */
    public Board(int rows, int columns, String[] initialParameters) {
        this.amountOfRows = rows;
        this.amountOfColumns = columns;
        board = new Cell[rows][columns];
        initializeBoard(initialParameters);
    }

    /**
     * This method gets the amount of rows that the board was initialized with.
     * 
     * @return Returns the amount of rows as an integer
     */
    public int getAmountOfRows() {
        return amountOfRows;
    }

    /**
     * This method gets the amount of columns that the board was initialized with.
     * 
     * @return Returns the amount of columns as an integer
     */
    public int getAmountOfColumns() {
        return amountOfColumns;
    }

    /**
     * This method gets the list of the fire stations that were initialized in the
     * beginning of the game.
     * 
     * @return Returns the list of all fire stations as an array list which holds
     *         objects of the type FireStationCell
     */
    public ArrayList<FireStationCell> getFireStations() {
        return this.fireStations;
    }

    /**
     * This method gets a cell when receiving coordinates.
     * 
     * @param row    the X coordinates of a cell
     * @param column the Y coordinates of a cell
     * @return Returns a cell of the coordinates received
     */
    public Cell getCellFromCoordinates(int row, int column) {
        return board[row][column];
    }

    /**
     * This method gets the whole list of all fires that exist on the board.
     * 
     * @return Returns the list of all fires as an array list which holds objects of
     *         the type ForestCell
     */
    public ArrayList<ForestCell> getAllFires() {
        return this.fires;
    }

    /**
     * This method checks every cell on the board and adds it to the list of all
     * fires it it burns either lightly or strongly. Else if the list of all fires
     * contains cells that are not burning than this method removes them.
     */
    public void updateFires() {
        for (int i = 0; i < amountOfRows; i++) {
            for (int j = 0; j < amountOfColumns; j++) {
                if (!board[i][j].getType().equals(CellType.FOREST)) {
                    continue;
                }
                ForestCell forest = (ForestCell) board[i][j];
                forest.resetAlreadyAddedFire();
                if (fires.contains(forest) && (forest.getForestState().equals(CellProperties.FOREST_DRY)
                        || forest.getForestState().equals(CellProperties.FOREST_WET))) {
                    fires.remove(forest);
                } else if (!fires.contains(forest)
                        && (forest.getForestState().equals(CellProperties.FOREST_LIGHTLY_BURNING)
                                || forest.getForestState().equals(CellProperties.FOREST_STRONGLY_BURNING))) {
                    fires.add(forest);
                }
            }
        }
    }

    /**
     * This method checks if all fires were extinguished.
     * 
     * @return Returns true if all fires were extinguished and false if there are
     *         still fires on the board
     */
    public boolean allFiresExstinguished() {
        boolean allFiresExstinguished = false;
        if (fires.isEmpty()) {
            allFiresExstinguished = true;
        }
        return allFiresExstinguished;
    }

    // calculates vertical and horizontal neighbors
    private void calculateNeighborCells(int row, int column) {
        Cell thisCell = getCellFromCoordinates(row, column);
        if (row - 1 >= 0) {
            thisCell.addNeighbours(getCellFromCoordinates(row - 1, column));
        }
        if (row + 1 < this.amountOfRows) {
            thisCell.addNeighbours(getCellFromCoordinates(row + 1, column));
        }
        if (column - 1 >= 0) {
            thisCell.addNeighbours(getCellFromCoordinates(row, column - 1));
        }
        if (column + 1 < this.amountOfColumns) {
            thisCell.addNeighbours(getCellFromCoordinates(row, column + 1));
        }
    }

    // calculates diagonal neighbor cells of this cell
    private void calculateDiagonalNeighborCells(int row, int column) {
        Cell thisCell = getCellFromCoordinates(row, column);
        if ((row - 1 > 0) && (column - 1 > 0)) {
            thisCell.addNeighbours(getCellFromCoordinates(row - 1, column - 1));
        }
        if ((row - 1 > 0) && (column + 1 < amountOfColumns)) {
            thisCell.addNeighbours(getCellFromCoordinates(row - 1, column + 1));
        }
        if ((row + 1 < amountOfRows) && (column - 1 > 0)) {
            thisCell.addNeighbours(getCellFromCoordinates(row + 1, column - 1));
        }
        if ((row + 1 < amountOfRows) && (column + 1 < amountOfColumns)) {
            thisCell.addNeighbours(getCellFromCoordinates(row + 1, column + 1));
        }
    }

    // initialize the board with initial values
    private void initializeBoard(String[] parameters) {
        int loopVariable = 0;
        Hashtable<String, Cell> fireEngines = new Hashtable<>();
        // go through every parameter and create a new cell and set it on the board
        for (int i = 0; i < amountOfRows; i++) {
            for (int j = 0; j < amountOfColumns; j++) {
                if (Pattern.matches(Regex.FOREST_STATE, parameters[loopVariable])) {
                    board[i][j] = new ForestCell(i, j, parameters[loopVariable]);
                    if (parameters[loopVariable].equals(CellProperties.FOREST_LIGHTLY_BURNING)
                            || parameters[loopVariable].equals(CellProperties.FOREST_STRONGLY_BURNING)) {
                        fires.add((ForestCell) board[i][j]);
                    }
                } else if (Pattern.matches(Regex.FIRE_STATION_ID, parameters[loopVariable])) {
                    board[i][j] = new FireStationCell(i, j, parameters[loopVariable]);
                    fireStations.add((FireStationCell) board[i][j]);
                } else if (Pattern.matches(Regex.INITIAL_FIRE_ENGINE, parameters[loopVariable])) {
                    board[i][j] = new ForestCell(i, j, CellProperties.FOREST_DRY);
                    fireEngines.put(parameters[loopVariable], board[i][j]);
                } else if (Pattern.matches(Regex.LAKE, parameters[loopVariable])) {
                    board[i][j] = new LakeCell(i, j, parameters[loopVariable]);
                }
                loopVariable++;
            }
        }

        // set the fire engines on the initialized cells
        for (FireStationCell station : fireStations) {
            FireEngine thisFireEngine = station.getFireEngineList().get(0);
            Cell thisCell = fireEngines.get(thisFireEngine.getIdentifier());
            thisCell.addToCell(thisFireEngine);
            thisFireEngine.setCurrentPosition(thisCell);
        }

        // calculate neighbors for each cell
        for (int i = 0; i < amountOfRows; i++) {
            for (int j = 0; j < amountOfColumns; j++) {
                if (board[i][j].getType().equals(CellType.FIRE_STATION)) {
                    calculateNeighborCells(i, j);
                    calculateDiagonalNeighborCells(i, j);
                } else {
                    calculateNeighborCells(i, j);
                }
            }
        }
    }
}
