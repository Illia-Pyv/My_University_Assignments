package edu.kit.informatik.ioProcessing;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.ioProcessing.Result.ResultType;

/**
 * This class handles the output of the program. It
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Output {

    /**
     * This method checks whether the message passed is an error message or a normal
     * message and prints it accordingly by adding "Error, " in front of the message
     * if the result type is Failure.
     * 
     * @param result is a parameter that contains the result type as well as the
     *               message to be printed
     */
    public void print(Result result) {
        if (result.getType() == ResultType.SUCCESS) {
            Terminal.printLine(result.getMessage());
        } else {
            Terminal.printError(result.getMessage());
        }
    }
}
