package edu.kit.informatik.Santorini;
import edu.kit.informatik.Santorini.core.InputManager;
import edu.kit.informatik.Santorini.core.OutputManager;
import edu.kit.informatik.Santorini.game.management.GameManager;
import edu.kit.informatik.Santorini.view.InputCommands;
import edu.kit.informatik.Santorini.view.commandParser.Command;

/**
 * 
 * @author illya
 *
 */
public class Santorini {
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        InputManager input = new InputManager();
        OutputManager output = new OutputManager();
        GameManager gameManager = new GameManager(output, args);
        Command command = null;
        
        
        while (input.getNextInput()) {
                command = input.parse();
                if (!gameManager.executeCommand(command)) {
                    if (command.getCommand().equals(InputCommands.SURRENDER)) {
                        output.print();
                    }     
                    /**
                     * Only QUIT and command can return false!
                     */
                    break;
                }
                output.print();
                output.printError();
            }
        }
    }


