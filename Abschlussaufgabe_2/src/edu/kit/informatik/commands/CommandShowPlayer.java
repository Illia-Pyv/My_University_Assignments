package edu.kit.informatik.commands;

import java.util.ArrayList;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.FireEngine;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.Player;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the command which shows the statistics of the player.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandShowPlayer extends Command {
    private static final String COMMAND_NAME = "show-player";
    private static final String SHOW_PLAYER_PARAMETERS_REGEX = "";

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandShowPlayer() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(SHOW_PLAYER_PARAMETERS_REGEX);
    }

    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.NO_PARAMETERS);
        } else {
            String message;
            message = processPlayer(gameManager.getPlayer());
            return new Result(ResultType.SUCCESS, message);
        }
    }

    // takes in a Player object and processes it to a string
    private String processPlayer(Player player) {
        String result = player.getStation().getIdentifier() + Regex.COMMA + player.getReputation() + Regex.NEWLINE;
        String specialCharacter;
        ArrayList<FireEngine> fireEngines = sortList(player.getStation().getFireEngineList());
        int fireEnginesListSize = fireEngines.size();
        int loopVariable = 0;
        for (FireEngine fireEngine : fireEngines) {
            if (loopVariable == fireEnginesListSize - 1) {
                specialCharacter = "";
            } else {
                specialCharacter = Regex.NEWLINE;
            }
            result += fireEngine.getIdentifier() + Regex.COMMA + fireEngine.getWaterLevel() + Regex.COMMA
                    + fireEngine.getActionPoints() + Regex.COMMA
                    + fireEngine.getCurrentPosition().getCoordinates().getRow() + Regex.COMMA
                    + fireEngine.getCurrentPosition().getCoordinates().getColumn() + specialCharacter;
            loopVariable++;
        }
        return result;
    }

    // sorts the list of fire engines by their identifiers
    private ArrayList<FireEngine> sortList(ArrayList<FireEngine> fireEngineList) {
        String[] listOfIDs = new String[fireEngineList.size()];
        ArrayList<FireEngine> sortedList = new ArrayList<>();
        int loopVariable = 0;
        for (FireEngine fireEngine : fireEngineList) {
            listOfIDs[loopVariable] = fireEngine.getIdentifier();
            loopVariable++;
        }
        getSorter().sortAscending(listOfIDs);
        for (String fireEngineID : listOfIDs) {
            for (FireEngine fireEngine : fireEngineList) {
                if (fireEngine.getIdentifier().equals(fireEngineID)) {
                    sortedList.add(fireEngine);
                    break;
                }
            }
        }
        return sortedList;
    }
}
