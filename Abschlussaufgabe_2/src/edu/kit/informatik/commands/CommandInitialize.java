package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.GameManager;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the command for the initialization of the program.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandInitialize extends Command {

    // probably wrong regex
    private static final String INITIAL_PARAMETERS_REGEX = Regex.START + Regex.START_CONFIGURATION + Regex.END;

    /**
     * This is the constructor of this class. It sets the regular expression string
     * which the parameters must match before they can be processed in the logic of
     * this program.
     */
    public CommandInitialize() {
        setParameterRegex(INITIAL_PARAMETERS_REGEX);
    }

    @Override
    public Result execute(String parameters, GameManager gameManager) throws InvalidParametersException {
        int index = matchesRegex(parameters);

        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);

            // if they match the regex to add a section, then execute this
        } else {
            ParameterParser parser = new ParameterParser(parameters);
            try {
                gameManager.initialize(parser.getNumberParameters()[0], parser.getNumberParameters()[1],
                        parser.getStringParameters());
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            return new Result(ResultType.SUCCESS);
        }
    }
}
