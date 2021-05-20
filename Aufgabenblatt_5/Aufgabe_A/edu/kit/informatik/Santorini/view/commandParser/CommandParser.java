package edu.kit.informatik.Santorini.view.commandParser;

import edu.kit.informatik.Santorini.view.SpecialCharacters;

/**
 * 
 * @author illya
 *
 */
public class CommandParser {
    /**
     * 
     * @param arguments
     * @return Returns the command
     */
    public String[] parseArguments(String[] arguments) {
        String[] thisInput = null;
        for (String players: arguments) {
            thisInput = players.split(SpecialCharacters.SEMICOLON);
        }
        return thisInput;
    }
}