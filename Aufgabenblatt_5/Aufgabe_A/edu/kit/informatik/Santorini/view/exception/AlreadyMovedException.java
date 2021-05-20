package edu.kit.informatik.Santorini.view.exception;

public class AlreadyMovedException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -708387443588029998L;

    /**
     * Constructs a exception with message.
     *
     * @param message the message describing the exception
     */
    public AlreadyMovedException(final String message) {
        super(message);
    }
}
