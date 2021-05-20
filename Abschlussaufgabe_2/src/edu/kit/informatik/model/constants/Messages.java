package edu.kit.informatik.model.constants;

/**
 * This class contains all messages that will later be output for the user.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class Messages {

    /**
     * This message is used when the game was won by the players. This is the last
     * message the players will receive as it quits the game after winning.
     */
    public static final String WON_GAME = "win";

    /**
     * This message is used when the game was lost by the players. This is the last
     * message the players will receive as it quits the game after losing.
     */
    public static final String LOST_GAME = "lose";

    /**
     * This message is displayed when a command has been successfully executed.
     */
    public static final String SUCCESS = "OK";

    /**
     * This message is displayed when the command 'show-board' is building a string to represent
     * the board configuration in which case this message represents a cell that is
     * not burning.
     */
    public static final String FIELD_THAT_IS_NOT_BURNING = "x";

    // Utility classes have a private constructor.
    private Messages() {

    }
}
