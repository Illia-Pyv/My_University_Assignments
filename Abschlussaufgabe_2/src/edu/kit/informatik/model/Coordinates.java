package edu.kit.informatik.model;

/**
 * This class represents the coordinates of a cell on the board.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Coordinates {
    private int row;
    private int column;

    /**
     * This is the constructor of this class. It sets the row and the column on a
     * board to represent a cell on a board.
     * 
     * @param row    the row of the cell on the board
     * @param column the column of the cell on the board
     */
    public Coordinates(int row, int column) {
        set(row, column);
    }

    /**
     * This method sets the row and column of this coordinates object.
     * 
     * @param row    the row of a cell on the board that is to be set
     * @param column the column of a cell on the board that is to be set
     */
    public void set(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * This method gets the row from this Coordinates object.
     * 
     * @return Returns the row of a cell on the board as an integer
     */
    public int getRow() {
        return this.row;
    }

    /**
     * This method gets the column from this Coordinates object.
     * 
     * @return Returns the column of a cell on the board as an integer
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * This method checks if the coordinates of this object are the same as the
     * coordinates of a passed object.
     * 
     * @param coordinates the coordinates that should be checked if they are the
     *                    same as the coordinates of this object
     * @return Returns
     */
    public boolean areSame(Coordinates coordinates) {
        if (this.getRow() == coordinates.getRow() && this.getColumn() == coordinates.getColumn()) {
            return true;
        }
        return false;
    }
}
