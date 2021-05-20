package edu.kit.informatik.Santorini.game.board;
/**
 * 
 * @author illya
 *
 */
public class Coordinates {
    private int row;
    private int column;

    /**
     * 
     * @param row
     * @param column
     */
    public Coordinates(int row, int column) {
        set(row, column);
    }

    /**
     * 
     * @param row
     * @param column
     */
    public void set(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * 
     * @return Returns the command
     */
    public int getRow() {
        return this.row;
    }

    /**
     * 
     * @return Returns the command
     */
    public int getColumn() {
        return this.column;
    }
    
    /**
     * 
     * @param coordinates
     * @return Returns the command
     */
    public boolean areSame(Coordinates coordinates) {
        if (getRow() == coordinates.getRow() && getColumn() == coordinates.getColumn()) {
            return true;
        }
        return false;
    }
}
