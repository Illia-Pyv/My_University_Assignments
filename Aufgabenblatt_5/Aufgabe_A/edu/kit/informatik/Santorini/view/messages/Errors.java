package edu.kit.informatik.Santorini.view.messages;

/**
 * 
 * @author illya
 *
 */
public final class Errors {
    /**
     * 
     */
    public static final String COMMAND_NOT_KNOWN = "the command is not known.";
    /**
     * 
     */
    public static final String NOPARAMETER = "incorrect input format, this command does not accept any parameters.";
    /**
     * 
     */
    public static final String INVALID_PARAMETERS = "incorrect input format, "
            + "this command does not accept these parameters.";
    /**
     * 
     */
    public static final String INVALID_CELL = "you cannot move to this cell.";
    /**
     * 
     */
    public static final String INVALID_FIGURE = "this figure does not exist.";
    /**
     * 
     */
    public static final String ALREADY_MOVED = "you cannot move, because you already moved this turn.";
    /**
     * 
     */
    public static final String ALREADY_BUILT = "you cannot build, because you already built this turn.";
    /**
     * 
     */
    public static final String BUILD_ON_OCCUPIED_CELL = "you cannot build, because the cell is occupied.";
    /**
     * 
     */
    public static final String MOVE_TO_OCCUPIED_CELL = "you cannot move, because the cell is occupied.";

    private Errors() {

    }
}
