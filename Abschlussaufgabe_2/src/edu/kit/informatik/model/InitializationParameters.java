package edu.kit.informatik.model;

/**
 * This class represents the parameters that are needed to start a new game.
 * They are set by the player when this application is first started.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class InitializationParameters {
    private int rows;
    private int columns;
    private String[] parameters;

    /**
     * This is the constructor of this class. It sets the amount of rows and columns
     * that the first board was initialized with as well as all the other parameters
     * that are initialized on the board.
     * 
     * @param rows       the amount of rows of the board on which the game takes
     *                   place
     * @param columns    the amount of columns of the board on which the game takes
     *                   place
     * @param parameters all the other parameters such as fire stations, fire
     *                   engines and lakes that are initialized on he board
     */
    public InitializationParameters(int rows, int columns, String[] parameters) {
        this.rows = rows;
        this.columns = columns;
        this.parameters = parameters;
    }

    /**
     * This method gets the amount of rows that the board has been initialized with.
     * 
     * @return Returns the amount of rows of the board as an integer
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * This method gets the amount of columns that the board has been initialized
     * with.
     * 
     * @return Returns the amount of columns of the board as an integer
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * This method gets the string parameters of what has been initialized on the
     * board.
     * 
     * @return Returns the string parameters as an array of strings
     */
    public String[] getStringParameters() {
        return this.parameters;
    }
}
