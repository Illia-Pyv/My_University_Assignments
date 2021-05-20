package edu.kit.informatik.commands;

import java.util.ArrayList;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.Player;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Messages;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the command fire to roll, which determines in what
 * direction the wind has to blow.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandFireToRoll extends Command {

    private static final String COMMAND_NAME = "fire-to-roll";
    private static final String FIRE_TO_ROLL_PARAMETERS_REGEX = Regex.START + Regex.DIE_NUMBERS + Regex.END;
    private int sizeOfPlayersPrevious = 0;

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandFireToRoll() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(FIRE_TO_ROLL_PARAMETERS_REGEX);
    }

    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);
        } else {
            ParameterParser parser = new ParameterParser(parameters);
            ArrayList<Player> players;
            try {
                players = gameManager.fireToRoll(parser.getNumberParameters()[0]);
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            if (players.isEmpty()) {
                return new Result(ResultType.SUCCESS, Messages.LOST_GAME);
            } else if (players.size() < sizeOfPlayersPrevious) {
                sizeOfPlayersPrevious = players.size();
                return new Result(ResultType.SUCCESS, players.get(0).getName());
            } else {
                sizeOfPlayersPrevious = players.size();
                return new Result(ResultType.SUCCESS, Messages.SUCCESS);
            }
        }
    }

}
