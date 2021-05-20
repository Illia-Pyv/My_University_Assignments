package edu.kit.informatik.ioProcessing;

import edu.kit.informatik.Terminal;

/**
 * This class handles the inputs of the user.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Input {
    
    /**
     * This method reads the input of the user.
     * 
     * @return Returns the user input as a string
     */
    public String read() {
        return Terminal.readLine();
    }
}
