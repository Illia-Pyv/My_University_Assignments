package edu.kit.informatik.Santorini.view.commandParser;

import edu.kit.informatik.Santorini.view.Arguments;
import edu.kit.informatik.Santorini.view.SpecialCharacters;

/**
 * 
 * @author illya
 *
 */
public class Command {
    private String command;
    private String[] rawCommand;
    private String arguments;
    private String[] parameters;

    /**
     * 
     * @param arguments
     */
    public Command(String arguments) {
            this.arguments = arguments;
            rawCommand = this.arguments.split(" ");
        }

    /**
     * 
     * @return Returns the command
     */
    public String getCommand() {
        command = rawCommand[Arguments.ARG0.ordinal()];
        return (command);
    }

    /**
     * 
     * @return Returns the command
     */
    public String[] getParameters() {
        int i = -1;
        parameters = new String[rawCommand.length - 1];
        for (String s : rawCommand) {
            i++;
            if (i == 0) { // skip befehl
                continue;
            }
            parameters[i - 1] = s;
        }
        return parameters;
    }
    
    /**
     * 
     * @param parameters
     * @return Returns the command
     */
    public String[] splitByEachParameter(String[] parameters) {
        String[] parameter = new String[parameters[0].length()];
        for (String s: parameters) {
            parameter = s.split(SpecialCharacters.SEMICOLON);
        }
        return parameter;
    }
}
