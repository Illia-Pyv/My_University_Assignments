package edu.kit.informatik.Santorini.game.management;

public class AlreadyBuiltException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -6528830105667660605L;

    /**
     * Constructs a exception with message.
     *
     * @param message the message describing the exception
     */
    public AlreadyBuiltException(final String message) {
        super(message);
    }
}
