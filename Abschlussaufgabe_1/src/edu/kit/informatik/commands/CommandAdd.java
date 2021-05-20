package edu.kit.informatik.commands;

import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.Result;
import edu.kit.informatik.ioProcessing.Result.ResultType;
import edu.kit.informatik.model.LogicException;
import edu.kit.informatik.model.NetworkManager;
import edu.kit.informatik.model.constants.Errors;
import edu.kit.informatik.model.constants.Messages;
import edu.kit.informatik.model.constants.Regex;

/**
 * This class represents the add command which adds either an escape section or
 * a network depending on the input parameters.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class CommandAdd extends Command {

    private static final int CHECK_AMOUNT_OF_SECTIONS = 100;
    private static final String COMMAND_NAME = "add";
    private static final String PARAMETER_REGEX_ADD_NETWORK = Regex.START + Regex.NETWORK_IDENTIFIER + Regex.SPACE
            + Regex.GRAPH + Regex.END;
    private static final String PARAMETER_REGEX_BIG_INPUTS = Regex.START + Regex.GRAPH + Regex.END;
    private static final String PARAMETER_REGEX_ADD_SECTION = Regex.START + Regex.NETWORK_IDENTIFIER + Regex.SPACE
            + Regex.ESCAPE_SECTION + Regex.END;

    /**
     * This is the constructor of this class. It sets the name of this command as
     * well as all of the regular expression strings.
     */
    public CommandAdd() {
        setCommandName(COMMAND_NAME);
        setParameterRegex(PARAMETER_REGEX_ADD_NETWORK);
        setParameterRegex(PARAMETER_REGEX_BIG_INPUTS);
        setParameterRegex(PARAMETER_REGEX_ADD_SECTION);
    }

    @Override
    public Result execute(String parameters, NetworkManager networkManager) throws InvalidParametersException {
        int index = checkRegexBigInputs(parameters);

        // checks if the input parameters do match a regex, if they do not than throw an
        // error
        if (index == -1) {
            throw new InvalidParametersException(Errors.INVALID_PARAMETERS);

            // if they match the regex to add a section, then execute this
        } else if (getParameterRegex(index).equals(PARAMETER_REGEX_ADD_SECTION)) {
            ParameterParser parser = new ParameterParser(parameters);
            try {
                networkManager.addNewSection(parser.getNetworkIdentifier(), parser.getFromVertices()[0],
                        parser.getToVertices()[0], parser.getCapacities()[0]);
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }

            return new Result(ResultType.SUCCESS,
                    String.format(Messages.ADDED_SECTION,
                            parser.getFromVertices()[0] + parser.getCapacities()[0] + parser.getToVertices()[0],
                            parser.getNetworkIdentifier()));

            // if they match the regex to add a network, then execute this
        } else {
            ParameterParser parser = new ParameterParser(parameters);
            try {
                networkManager.addNewNetwork(parser.getNetworkIdentifier(), parser.getFromVertices(),
                        parser.getToVertices(), parser.getCapacities());
            } catch (LogicException exception) {
                return new Result(ResultType.FAILURE, exception.getMessage());
            }
            return new Result(ResultType.SUCCESS, String.format(Messages.ADDED_NETWORK, parser.getNetworkIdentifier()));
        }
    }

    // this method checks graphs that are too big by checking smaller packs of
    // sections if they match the regex to avoid a stack overflow error
    private int checkRegexBigInputs(String parameters) {
        int index = -1;
        int temporaryIndex = 0;
        int loopVariable = 0;
        int lengthOfParametersString = parameters.length();
        boolean set = false;
        String temporary = "";
        for (int i = 0; i < lengthOfParametersString; i++) {
            if (String.format("%s", parameters.charAt(i)).equals(Regex.SEMICOLON)
                    && loopVariable + 1 == CHECK_AMOUNT_OF_SECTIONS) {
                temporaryIndex = matchesRegex(temporary);
                if (temporaryIndex == -1
                        || (!set && !getParameterRegex(temporaryIndex).equals(PARAMETER_REGEX_ADD_NETWORK))) {
                    return temporaryIndex;
                } else if (!set) {
                    index = temporaryIndex;
                    set = true;
                }
                loopVariable = 0;
                temporary = "";
            } else if (String.format("%s", parameters.charAt(i)).equals(Regex.SEMICOLON)) {
                loopVariable++;
                temporary += parameters.charAt(i);
            } else {
                temporary += parameters.charAt(i);
            }
        }
        temporaryIndex = matchesRegex(temporary);
        if (temporaryIndex == -1 || (!set && !getParameterRegex(temporaryIndex).equals(PARAMETER_REGEX_BIG_INPUTS))) {
            index = temporaryIndex;
        }
        return index;
    }
}
