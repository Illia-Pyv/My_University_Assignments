package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.model.GameManager;

/**
 * This class specifies how the command classes are to be structured.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public interface CommandMethods {
    
    /**
     * This method executes the function that the command that called this method
     * should perform.
     * 
     * @param parameters     the parameters that the command should parse and
     *                       execute
     * @param gameManager this is the game Manager object that is needed to
     *                       execute the command or else it cannot access the
     *                       methods in the logic of this program
     * @return Returns a result of the type Result for easier processing of the
     *         output
     * @throws InvalidParametersException Throws an exception if the user input was wrong
     */
    Result execute(String parameters, GameManager gameManager) throws InvalidParametersException;

    /**
     * This method sets the multiple regular expressions if needed to store
     * 
     * @param parameterRegex the regex of parameters that has to be set for a
     *                       command
     */
    void setParameterRegex(String parameterRegex);

    /**
     * This method sets the name of a command so that it can be accessed later on.
     * 
     * @param commandName the name of the command which is received as string and is
     *                    to be set
     */
    void setCommandName(String commandName);

    /**
     * This method gets the parameter regex as a string which is located at the
     * position "index" in the list of all parameters regex.
     * 
     * @param index the number which indicates the position in the list where the
     *              parameter regex should be gotten from
     * @return Returns the parameter regex as a string at the position index from
     *         the parameter regex list
     */
    String getParameterRegex(int index);

    /**
     * This method gets the name of the command.
     * 
     * @return Returns the name of the command as a string
     */
    String getCommandName();
}
