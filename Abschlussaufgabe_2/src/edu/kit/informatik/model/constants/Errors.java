package edu.kit.informatik.model.constants;

/**
 * This class contains all the error messages that will later be output for the
 * user.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class Errors {

    /**
     * This error message is displayed if the user enters a command that does not
     * exist.
     */
    public static final String INVALID_COMMAND = "the command '%s' does not exist. Please try another one.";

    /**
     * This error message is displayed if the user entered an existing command but
     * with wrong parameters.
     */
    public static final String INVALID_PARAMETERS = "the parameters are invalid. "
            + "Please try again with other parameters.";

    /**
     * This error message is displayed if the command does not accept any parameters
     * but the user entered something regardless.
     */
    public static final String NO_PARAMETERS = "incorrect input format, this command does not accept any "
            + "parameters.";

    /**
     * This error message is displayed if there are more parameters than the amount
     * of cells that fit onto the board, which size is specified by the user in the
     * command line arguments.
     */
    public static final String TOO_MANY_PARAMETERS = "the board is smaller than the amount of initialized cells.";

    /**
     * This error message is displayed if there are less parameters than the amount
     * of cells that could fit onto the board, which size is specified by the user
     * in the command line arguments.
     */
    public static final String TOO_FEW_PARAMETERS = "the board is bigger than the amount of initialized cells.";

    /**
     * This error message is displayed if the user enters too few lakes in the
     * command line arguments.
     */
    public static final String LESS_THAN_FOUR_LAKES = "you are trying to initialize more than four lakes.";

    /**
     * This error message is displayed if the user enters too many lakes in the
     * command line arguments.
     */
    public static final String MORE_THAN_FOUR_LAKES = "you are trying to initialize more than four lakes.";

    /**
     * This error message is displayed if the user enters too few fire stations in
     * the command line arguments.
     */
    public static final String LESS_THAN_FOUR_STATIONS = "you are trying to initialize more than "
            + "four fire stations.";

    /**
     * This error message is displayed if the user enters too many fire stations in
     * the command line arguments.
     */
    public static final String MORE_THAN_FOUR_STATIONS = "you are trying to initialize more than "
            + "four fire stations.";

    /**
     * This error message is displayed if the user enters too few fire engines in
     * the command line arguments.
     */
    public static final String LESS_THAN_FOUR_FIRE_ENGINES = "you are trying to initialize more than "
            + "four fire engines.";

    /**
     * This error message is displayed if the user enters too many fire engines in
     * the command line arguments.
     */
    public static final String MORE_THAN_FOUR_FIRE_ENGINES = "you are trying to initialize more than "
            + "four fire engines.";

    /**
     * This error message is displayed if the user places a fire engine on a wrong
     * spot in the command line arguments.
     * 
     * <p>
     * This error expects the identifier of the misplaced fire engine.
     */
    public static final String FIRE_ENGINE_MISPLACED = "the fire engine '%s' is not placed correctly. "
            + "Fire engines can only be placed one diagonal to their fire station.";

    /**
     * This error message is displayed if the user places a fire station on a wrong
     * spot in the command line arguments.
     * 
     * <p>
     * This error expects the identifier of the misplaced station.
     */
    public static final String STATION_MISPLACED = "the fire station '%s' is not placed correctly. "
            + "Fire stations can only be placed in the corners of the board.";

    /**
     * This error message is displayed if the user places a lake on a wrong spot in
     * the command line arguments.
     */
    public static final String LAKE_MISPLACED = "a lake is not placed correctly on the board. "
            + "Lakes can only be placed exactly in between two fire stations on all four edges of the board.";

    /**
     * This error message is displayed if the user tries to initialize two fire
     * stations with the same identifiers.
     * 
     * <p>
     * This error expects the identifier of the fire station that is initialized
     * twice or more.
     */
    public static final String DOUBLE_INITIALIZATION_STATIONS = "you are trying to initialize at least two "
            + "fire stations with the same identifier '%s'.";

    /**
     * This error message is displayed if the user tries to initialize two fire
     * engines with the same identifiers.
     * 
     * <p>
     * This error expects the identifier of the fire engine that is initialized
     * twice or more times.
     */
    public static final String DOUBLE_INITIALIZATION_FIRE_ENGINES = "you are trying to initialize at least two "
            + "fire engines with the same identifier '%s'.";

    /**
     * This error message is displayed if the player tries to make a move while the
     * 'fire-to-roll' command has not been called.
     */
    public static final String FIRE_NOT_ROLLED = "you have to roll a fire direction before the "
            + "current player can make a move.";

    /**
     * This error message is displayed if the player tries to move to a burning
     * cell.
     */
    public static final String CANNOT_MOVE_TO_BURNING_CELL = "this cell cannot be moved to as it is on fire.";

    /**
     * This error message is displayed if the player tries to get a cell that is not
     * within the borders of the board.
     */
    public static final String CELL_NOT_ON_BOARD = "this cell does not exist on the board.";

    /**
     * This error message is displayed if the player tries to roll a fire even
     * though there are still players that haven't played yet in the current round.
     */
    public static final String PLAYERS_NOT_PLAYED = "you cannot roll a fire as you still have players in queue.";

    /**
     * This error message is displayed if the player tries to set a new bought fire
     * engine onto a burning cell or onto a cell that is not adjacent to the fire
     * station of the current player.
     */
    public static final String CANNOT_INITIALIZE_FIRE_ENGINE = "cannot initialize fire engine because the "
            + "cell is not adjacent to the fire station or due to fire.";

    /**
     * This error message is displayed if the player tries to access a fire engine
     * that either is not the players fire engine or it does not exist at all.
     * 
     * <p>
     * This error message expects the identifier of the fire engine that is not the
     * players.
     */
    public static final String FIRE_ENGINE_DOES_NOT_EXIST = "the fire '%s' engine does not exist "
            + "for the current player.";

    /**
     * This error message is displayed if the player tries to buy a fire engine but
     * does not have enough reputation points.
     */
    public static final String NOT_ENOUGH_REPUTATION = "you have not enough reputation points for this action.";

    /**
     * This error message is displayed if the player tries to do an action with a
     * fire engine that has not enough action points to perform an action.
     */
    public static final String NOT_ENOUGH_ACTION_POINTS = "not enough action points to perform command.";

    /**
     * This error message is displayed if the fire engine that tries to extinguish a
     * cell has no water.
     */
    public static final String NO_WATER = "this fire engine has no water.";

    /**
     * This error message is displayed if the fire engine tries to extinguish a wet
     * forest cell.
     */
    public static final String WET_FOREST = "cannot extinguish wet forest fields.";

    /**
     * This error message is displayed if the fire engine tries to extinguish a cell
     * that it already extinguished.
     */
    public static final String ALREADY_EXTINGUISHED = "this fire engine already extinguished this cell.";

    /**
     * This error message is displayed if the fire engine tries to extinguish a cell
     * that is not a forest cell.
     */
    public static final String NOT_A_FOREST = "cannot extinguish cell that is not a forest field.";

    /**
     * This error message is displayed if the fire engine tries to extinguish a cell
     * that is not a neighbor to the one this fire engine is currently standing on.
     */
    public static final String NOT_A_NEIGHBOR = "the cell to be extiguished is not a neighbor.";

    /**
     * This error message is displayed if the fire engine cannot reach a cell
     * because it is too far away.
     */
    public static final String CELL_OUTSIDE_OF_MOVE_RANGE = "cell is outside of the fire engines move range.";

    /**
     * This error message is displayed if the fire engine cannot move to the cell
     * because there is fire in the way, blocking the path completely.
     */
    public static final String PATH_BLOCKED = "cell cannot be reached because there is fire in the way.";

    /**
     * This error message is displayed if the fire engine already has the maximum
     * water level in which case it cannot refill.
     */
    public static final String MAXIMAL_WATER_LEVEL = "cannont refill because fire engine has maximal water level.";

    /**
     * This error message is displayed if the fire engine already performed an other
     * action other than move.
     */
    public static final String PERFORMED_OTHER_ACTION = "cannot move as another action other than "
            + "'move' was already performed by this fire engine.";

    /**
     * This error message is displayed if the fire engine already stands on the cell
     * to which the player wants it to move.
     */
    public static final String STAYING_ON_CELL = "cannot move to field you are standing on already.";

    /**
     * This error message is displayed if the fire engine wants to refill but there
     * is neither a fire station or a lake nearby.
     */
    public static final String NO_WATER_SOURCE = "no water source nearby.";

    // Utility classes have a private constructor.
    private Errors() {

    }
}
