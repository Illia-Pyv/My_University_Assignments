package edu.kit.informatik.Santorini.core;

import edu.kit.informatik.Terminal;

/**
 * 
 * @author illya
 *
 */
public class OutputManager {

    private String message = "";
    private String errorMessage = "";
    //private String recentlyRetrieved = "";

    /**
     * 
     * @param message
     */
    public void retrieveMessage(String message) {
        this.message = message;
    }
    
    /**
     * 
     * @param errorMessage
     */
    public void retrieveErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 
     */
    public void print() {
        if (this.message.equals("")) {
            this.message = "";
        } else {
            Terminal.printLine(this.message);
            this.message = "";
        }
    }
    
    public void printError() {
        if (this.errorMessage.equals("")) {
            this.errorMessage = "";
        } else {
            Terminal.printError(this.errorMessage);
            this.errorMessage = "";
        }
    }
    
}
