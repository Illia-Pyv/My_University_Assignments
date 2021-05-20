package edu.kit.informatik.model.constants;

/**
 * This class contains all messages that will later be output for the user.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class Messages {

    /**
     * This message returns the value "EMPTY". It is mostly used to construct an
     * output.
     */
    public static final String EMPTY = "EMPTY";

    /**
     * This message is displayed when a network is successfully added to the system.
     */
    public static final String ADDED_NETWORK = "Added new escape network with identifier %s.";

    /**
     * This message is displayed when an escape route section was successfully added
     * to a network.
     */
    public static final String ADDED_SECTION = "Added new section %s to escape network %s.";

    /**
     * This message accepts the two parameters 1. network identifier and 2. the
     * amount of vertices in that network.
     */
    public static final String ALL_NETWORKS = "%s %s\n";

    // Utility classes have a private constructor.
    private Messages() {

    }
}
