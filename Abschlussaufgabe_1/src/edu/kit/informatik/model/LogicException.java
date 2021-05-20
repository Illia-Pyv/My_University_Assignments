package edu.kit.informatik.model;

/**
 * This is an exception class that is used in the case that an error in the
 * logic classes appeared.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class LogicException extends Exception {

    private static final long serialVersionUID = 6784050849186322915L;

    /**
     * Constructs an exception with an error message .
     *
     * @param message the message describing the exception
     */
    public LogicException(final String message) {
        super(message);
    }
}
