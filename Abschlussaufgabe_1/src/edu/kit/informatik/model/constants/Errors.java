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
    public static final String INVALID_PARAMETERS = "the parameters are invalid. Please try again "
            + "with other parameters.";

    /**
     * This error message is displayed if the command does not accept any parameters
     * but the user entered something regardless.
     */
    public static final String NO_PARAMETERS = "this command does not accept any parameters. Try to get rid of '%s'.";

    /**
     * This error message is displayed if the graph does not have a valid vertex
     * pair, such as no existing start vertex, no existing end vertex or both.
     */
    public static final String VERTEX_PAIR = "the graph does not have a valid start/end vertex pair, "
            + "thus the network/section cannot be added.";

    /**
     * This error message is displayed if a network that is searched for by the user
     * does not exist in the system.
     */
    public static final String NO_SUCH_NETWORK = "the network '%s' does not exist in the system.";

    /**
     * This error message is used when a network already exists.
     */
    public static final String NETWORK_EXISTS_ALREADY = "the network '%s' already exists and thus "
            + "cannot be added again.";

    /**
     * This error message is used when a graph has loops in it which contradicts the
     * requirements of a graph.
     */
    public static final String HAS_LOOPS = "the graph contains loops on vertex '%s'. "
            + "Please consider removing it before the network/section can be added.";

    /**
     * This error message is displayed when the user enters a section one or more
     * times during the creation of a network.
     */
    public static final String HAS_DOUBLED_SECTION = "the section '%s' exists more than one time "
            + "in the graph, thus the network/section cannot be added.";

    /**
     * This error message is displayed when the user enters a parallel section to
     * the graph during the creation of a network.
     */
    public static final String HAS_BACK_SECTION = "the graph contains a path '%s', thus "
            + "the path back '%s' cannot be added.";

    /**
     * This error message is displayed when the flow is to be calculated but the
     * start vertex is invalid.
     */
    public static final String FLOW_INVALID_START_VERTEX = "the vertex '%s' does "
            + "either not exist or is not a start vertex.";

    /**
     * This error message is displayed when the flow is to be calculated but the end
     * vertex is invalid.
     */
    public static final String FLOW_INVALID_END_VERTEX = "the vertex '%s' does "
            + "either not exist or is not an end vertex.";

    /**
     * This error message is displayed when the flow is to be calculated but both
     * the start and the end vertices are invalid.
     */
    public static final String FLOW_INVALID_BOTH_VERTICES = "the vertices '%s' and '%s' both "
            + "do either not exist or are not start or end vertices, respectively.";

    // Utility classes have a private constructor.
    private Errors() {

    }
}
