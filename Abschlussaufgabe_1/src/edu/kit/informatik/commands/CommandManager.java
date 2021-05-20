package edu.kit.informatik.commands;

import java.util.ArrayList;

/**
 * This class manages all of the commands that are needed for further usage of
 * the program.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandManager {

    private ArrayList<Command> commandList = new ArrayList<>();

    /**
     * This is the constructor of this class. It puts all of the commands into the
     * commandList when this object is created.
     */
    public CommandManager() {
        commandList.add(new CommandAdd());
        commandList.add(new CommandList());
        commandList.add(new CommandPrint());
        commandList.add(new CommandFlow());
        commandList.add(new CommandQuit());
    }

    /**
     * This method returns the size of the list that contains all of the commands.
     * 
     * @return Returns the size of the list that contains the commands as an integer
     */
    public int size() {
        return commandList.size();
    }

    /**
     * This method method returns a command that at a certain position 'index'.
     * 
     * @param index the value that represents at which position in the list the
     *              command should be gotten from
     * @return Returns the command that is searched for if it exists in the command
     *         list
     */
    public Command getCommandFromList(int index) {
        return commandList.get(index);
    }
}
