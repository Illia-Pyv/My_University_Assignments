package edu.kit.informatik.ioProcessing;

import edu.kit.informatik.ioProcessing.Result.ResultType;

/**
 * This is an exception class that is used in the case that a command that the
 * user entered is invalid.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class InvalidCommandException extends Exception {

    private static final long serialVersionUID = 7917328268140390782L;
    private Result result;

    /**
     * This is the constructor of this exception class. It constructs an exception
     * with an error message and also sets the "ResultType" as a failure so that the
     * output class can print it out correctly.
     *
     * @param message the message describing the exception
     */
    public InvalidCommandException(final String message) {
        super(message);
        this.result = new Result(ResultType.FAILURE, message);
    }

    /**
     * The method returns a message as a result of the type "FAILURE" and a passed
     * message.
     * 
     * @return Returns the message as Result of the type "FAILURE" to easier process
     *         the output
     */
    public Result getResult() {
        return this.result;
    }
}