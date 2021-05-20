package edu.kit.informatik.Santorini.view.exception;

/**
 * 
 * @author illya
 *
 */
public class SantoriniException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -8049162251952699013L;
    
    /**
     * Constructs a exception with message.
     *
     * @param message the message describing the exception
     */
    public SantoriniException(final String message) {
        super(message);
    }
}
