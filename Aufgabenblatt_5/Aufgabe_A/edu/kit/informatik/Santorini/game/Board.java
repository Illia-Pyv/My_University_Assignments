package edu.kit.informatik.Santorini.game;

import edu.kit.informatik.Santorini.game.board.Cell;
import edu.kit.informatik.Santorini.game.board.Coordinates;
import edu.kit.informatik.Santorini.game.gamepieces.Figurine;
import edu.kit.informatik.Santorini.game.gamepieces.GamePieces;
import edu.kit.informatik.Santorini.view.SpecialCharacters;
import edu.kit.informatik.Santorini.view.messages.Messages;

/**
 * 
 * @author illya
 *
 */
public class Board {

    /** The size of the square with all of the neighbours of a cell. */
    private static final int NEIGHBOUR_SQUARE = 3;
    /** The board stays constant throughout the game. */
    private static final Cell[][] CELL = new Cell[5][5];
    /** The size of the board. */
    private static final int SIZE = CELL.length;
    /** The maximum height that can be achieved */
    private static final int MAXIMUM_HEIGHT = 4;

    /**
     * 
     */
    public Board() {
        initializeBoard(CELL);
        setNeighborCells();
    }

    // Variablen umbenennen
    /**
     * 
     * @return Returns the command
     */
    public String printBoard() {
        String result = "";
        // result += SpecialCharacters.NEWLINE;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (CELL[i][j].getLast() == null) {
                    if (j == SIZE - 1) {
                        result += SpecialCharacters.DOT;
                    } else {
                        result += SpecialCharacters.DOT + SpecialCharacters.COMMA;
                    }
                } else {
                    if (j == SIZE - 1) {
                        result += CELL[i][j].getLast();
                    } else {
                        result += CELL[i][j].getLast() + SpecialCharacters.COMMA;
                    }
                }
            }
            if (i < SIZE - 1) {
                result += SpecialCharacters.NEWLINE;
            }
        }
        return result;
    }

    /**
     * 
     * @param row
     * @param column
     * @return Returns the command
     */
    public String printCell(int row, int column) {
        Cell thisCell = CELL[row][column];
        String result = "";
        if (thisCell.isEmpty()) {
            return Messages.EMPTY;
        } else {
            for (int i = 0; i < thisCell.getSize(); i++) {
                if (i == thisCell.getSize() - 1) {
                    result += thisCell.gamePieceGet(i);
                } else {
                    result += thisCell.gamePieceGet(i) + SpecialCharacters.COMMA;
                }
            }
        }
        return result;

    }

    /**
     * This method gets a cell when receiving coordinates.
     * 
     * @param coordinates the coordinates of a cell
     * @return Returns a cell of the coordinates received
     */
    public Cell getCellFromCoordinates(Coordinates coordinates) {
        return CELL[coordinates.getRow()][coordinates.getColumn()];
    }

    /**
     * This method gets a cell when receiving coordinates.
     * 
     * @param row    the C coordinates of a cell
     * @param column the Y coordinates of a cell
     * @return Returns a cell of the coordinates received
     */
    public Cell getCellFromRawCoordinates(int row, int column) {
        return CELL[row][column];
    }

    /**
     * 
     * @param row
     * @param column
     * @return Returns the command
     */
    public Coordinates getCoordinatesFromCell(int row, int column) {
        return CELL[row][column].getCoordinates();
    }

    public int getBoardSize() {
        return this.SIZE;
    }

    private boolean isValidCell(Cell thisCell, Coordinates destinationCoordinates) {
        Cell destinationCell = getCellFromCoordinates(destinationCoordinates);
        if (thisCell.hasNeighbourCell(destinationCoordinates) && (!destinationCell.isOccupied())) {
            return true;
        }
        return false;
    }

    // muss ich noch implementieren um zu überprüfen, ob die Maximalhöhe schon
    // erreicht wurde
    /**
     * 
     * @param destinationCoordinates
     * @return Returns the command
     */
    public boolean maximumHeight(Coordinates destinationCoordinates) {
        Cell destinationCell = getCellFromCoordinates(destinationCoordinates);
        if (destinationCell.getSize() == MAXIMUM_HEIGHT) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @param destinationCoordinates
     * @param figure
     * @return Returns the command
     */
    public boolean setFigurine(Coordinates destinationCoordinates, Figurine figure) {
        boolean success = false;
        Cell thisCell;
        Cell destinationCell = getCellFromCoordinates(destinationCoordinates);
        if (figure.getCoordinates() == null) {
            setGamePiece(destinationCoordinates, figure);
            destinationCell.setOccupied(true);
            return true;
        }
        thisCell = getCellFromCoordinates(figure.getCoordinates());
        if (isValidCell(thisCell, destinationCoordinates) && (!thisCell.neighborHasTower(destinationCell))) {
            setGamePiece(destinationCoordinates, figure);
            destinationCell.setOccupied(true);
            success = true;
        }
        return success;
    }

    /**
     * 
     * @param figurineCoordinates
     * @param destinationCoordinates
     * @param buildingPiece
     * @return Returns the command
     */
    public boolean setBuildingPiece(Coordinates figurineCoordinates, Coordinates destinationCoordinates,
            GamePieces buildingPiece) {
        boolean success = false;
        Cell figurineCell = getCellFromCoordinates(figurineCoordinates);
        if (isValidCell(figurineCell, destinationCoordinates)) {
            setGamePiece(destinationCoordinates, buildingPiece);
        }
        return success;
    }

    /**
     * 
     * @param coordinates
     * @param gamePiece
     */
    private void setGamePiece(Coordinates coordinates, GamePieces gamePiece) {
        Cell thisCell = getCellFromCoordinates(coordinates);
        thisCell.addToCell(gamePiece);
    }

    /**
     * 
     * @param coordinates
     * @param figure
     */
    public void removePieceAt(Coordinates coordinates, Figurine figure) {
        Cell thisCell = getCellFromCoordinates(coordinates);
        thisCell.remove(figure);
        thisCell.setOccupied(false);
    }

    // Nur zur Überprüfung bzw. wird nicht in der Klasse bleiben
    /**
     * 
     * @param row
     * @param column
     * @return Returns the command
     */
    public String printNeighbours(int row, int column) {
        String s = "";
        Cell thisCell = CELL[row][column];
        for (int i = 0; i < thisCell.getNeighbourListSize(); i++) {
            s += "(" + thisCell.getNeighbour(i).getCoordinates().getRow() + ", "
                    + thisCell.getNeighbour(i).getCoordinates().getColumn() + "); ";
        }
        return s;
    }

    // In kleine Methoden aufteilen evtl.
    private void calculateNeighborCells(int row, int column) {
        for (int i = 0; i < NEIGHBOUR_SQUARE; i++) {
            int rowIterator = row - 1 + i;
            // checks if Cells are within board size
            if ((rowIterator > -1) && (rowIterator < SIZE)) {
                for (int n = 0; n < NEIGHBOUR_SQUARE; n++) {
                    int columnIterator = column - 1 + n;
                    // checks if Cells are within board size
                    if ((columnIterator > -1) && (columnIterator < SIZE)
                            && (CELL[rowIterator][columnIterator] != CELL[row][column])) {
                        CELL[row][column].addNeighbours(CELL[rowIterator][columnIterator]);
                    }
                }
            }
        }
    }

    private void setNeighborCells() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                calculateNeighborCells(i, j);
            }
        }
    }

    private Cell[][] initializeBoard(Cell[][] matrix) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                CELL[i][j] = new Cell(i, j);
            }
        }
        return CELL;
    }
}
