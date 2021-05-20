package edu.kit.informatik.commands;

import java.util.ArrayList;

/**
 * This class combines all methods used in the subclasses to avoid code copies.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public abstract class Command implements CommandMethods {
    
    private ArrayList<String> parameterRegex = new ArrayList<>();
    private String commandName = "";
    private RegexMatcher matcher = new RegexMatcher();
    
    @Override
    public void setParameterRegex(String parameterRegex) {
        this.parameterRegex.add(parameterRegex);
    }

    @Override
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String getParameterRegex(int index) {
        return this.parameterRegex.get(index);
    }

    @Override
    public String getCommandName() {
        return this.commandName;
    }

    /**
     * This method checks if the parameters match the regex.
     * 
     * @param inputParameter these are the still unprocessed parameters from the
     *                       user input.
     * @return Returns the index as an integer value at which the regex is located
     *         in the array list or -1 if the regex does not exist in the array list
     */
    public int matchesRegex(String inputParameter) {
        int resultIndex = -1;
        boolean matches = false;

        // search through the array list
        for (int i = 0; i < parameterRegex.size(); i++) {
            matches = matcher.checkIfMatches(inputParameter, this.parameterRegex.get(i));
            // if the parameters match with a regex then return the index
            if (matches) {
                resultIndex = i;
                return resultIndex;
            }
        }
        // else return -1
        return resultIndex;
    }
}
