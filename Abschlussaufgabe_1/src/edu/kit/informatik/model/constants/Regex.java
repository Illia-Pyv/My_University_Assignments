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
     * This regex represents the symbols used to separate the user input.
     */
    public static final String SEPERATORS = "[" + Regex.SPACE + Regex.SEMICOLON + "]";

    /**
     * This regex indicate when a new line starts in a string.
     */
    public static final String NEWLINE = "\n";
    
    /**
     * This regex represents the numbers from 1 to 2147483647, which are all the
     * values the capacity function can accept.
     */
    public static final String ESCAPE_SECTION_CAPACITY = "(214748364[0-7]|21474836[0-3][0-9]{1}|"
            + "2147483[0-5][0-9]{2}|214748[0-2][0-9]{3}|21474[0-7][0-9]{4}|2147[0-3][0-9]{5}|"
            + "214[0-6][0-9]{6}|21[0-3][0-9]{7}|20[0-9]{8}|1[0-9]{9}|[1-9][0-9]{1,8}|[1-9])";

    /**
     * This regex describes how a vertex identifier should be structured.
     */
    public static final String VERTEX_IDENTIFIER = "[a-z]{1,6}";

    /**
     * This regex describes how a network identifier should be structured.
     * 
     */
    public static final String NETWORK_IDENTIFIER = "[A-Z]{1,6}";

    /**
     * This regex describes how the parameters for an escape section should be
     * structured.
     */
    public static final String ESCAPE_SECTION = VERTEX_IDENTIFIER + ESCAPE_SECTION_CAPACITY + VERTEX_IDENTIFIER;

    /**
     * This regex describes how the parameters for a graph, which consists of
     * multiple escape sections should be structured.
     */
    public static final String GRAPH = "(?:" + ESCAPE_SECTION + SEMICOLON + ")+" + ESCAPE_SECTION;

    // private constructor in a utility class
    private Regex() {

    }
}
