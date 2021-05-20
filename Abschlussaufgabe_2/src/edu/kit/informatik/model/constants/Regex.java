package edu.kit.informatik.model.constants;

/**
 * This class contains all the regular expressions needed to construct more
 * complex regular expressions.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class Regex {

    /**
     * This character marks the beginning of a regex string.
     */
    public static final String START = "^";

    /**
     * This character marks the end of a regex string.
     */
    public static final String END = "$";

    /**
     * This regex symbolizes a space in regex notation.
     */
    public static final String SPACE = " ";

    /**
     * This regex symbolizes a semicolon in regex notation.
     */
    public static final String SEMICOLON = ";";

    /**
     * This regex represents a comma in regex notation.
     */
    public static final String COMMA = ",";

    /**
     * This regex represents the symbols used to separate the user input.
     */
    public static final String SEPERATORS = "[" + Regex.SPACE + Regex.COMMA + Regex.SEMICOLON + "]";

    /**
     * This regex indicates when a new line starts in a string.
     */
    public static final String NEWLINE = "\n";

    /**
     * This method represents all positive numbers as regex notation.
     */
    public static final String POSITIVE_NUMBERS = "([1-9][0-9]+|[0-9])";

    /**
     * This regex represents the identifier of a Fire station.
     */
    public static final String FIRE_STATION_ID = "[A-D]";

    /**
     * This regex represents the numbers that a die can represent.
     */
    public static final String DIE_NUMBERS = "[1-6]";

    /**
     * This regex represents the identifier the initial fire engines which belong to
     * a fire station "A-D".
     */
    public static final String INITIAL_FIRE_ENGINE = Regex.FIRE_STATION_ID + "[0]";

    /**
     * This regex represents the identifier of a fire engine which belongs to a fire
     * station "A-D".
     */
    public static final String FIRE_ENGINE = Regex.FIRE_STATION_ID + POSITIVE_NUMBERS;

    /**
     * This regex represents the state of a forest field which can be either
     * "w","d","+" or "*".
     */
    public static final String FOREST_STATE = "[wd+*]";

    /**
     * This regex represents the symbol for a lake on the board in regex notation.
     */
    public static final String LAKE = "L";

    /**
     * This regex represents an odd number which is greater than 5. It indicates the
     * amount of rows/columns and thereby specifies the size of the board.
     */
    public static final String SIZE_OF_BOARD = "([1-9][0-9]+[^0,2,4,6,8]|[1-9][^0,2,4,6,8]|[579])";

    /**
     * This regex represents how the start configuration should be structured when
     * initializing a new game.
     */
    public static final String START_CONFIGURATION = "(" + Regex.SIZE_OF_BOARD + Regex.COMMA + "){2}(("
            + Regex.FIRE_STATION_ID + "|" + Regex.FOREST_STATE + "|" + Regex.INITIAL_FIRE_ENGINE + "|" + Regex.LAKE
            + ")" + Regex.COMMA + ")+((" + Regex.FIRE_STATION_ID + "|" + Regex.FOREST_STATE + "|"
            + Regex.INITIAL_FIRE_ENGINE + "))";

    // private constructor in a utility class
    private Regex() {

    }
}
