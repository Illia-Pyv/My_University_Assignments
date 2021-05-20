package edu.kit.informatik.Santorini.core;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.Santorini.view.commandParser.Command;
import edu.kit.informatik.Santorini.view.commandParser.CommandParser;

/**
 * 
 * @author illya
 *
 */
public class InputManager {
    private CommandParser commandParser = new CommandParser();
    private String lastCommand;
    /**
     * 
     * @return Returns the command
     */
    public boolean getNextInput() {
        lastCommand = Terminal.readLine();
        if (lastCommand == null) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    /**
     * 
     * @param arguments
     * @return Returns the command
     */
    public String[] parseArguments(String[] arguments) {
        String[] argument = commandParser.parseArguments(arguments);
        return argument;
    }
    
    /**
     * 
     * @return Returns the command
     */
    public Command parse() {
        return (new Command(lastCommand));
    }
}
