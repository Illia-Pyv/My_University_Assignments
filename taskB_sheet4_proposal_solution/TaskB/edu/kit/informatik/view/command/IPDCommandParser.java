/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.command;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.kit.informatik.core.Pair;

/**
 * This class describes a {@link CommandParser} for the IPD command pattern.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class IPDCommandParser implements CommandParser {

    /** The regular expression of a single parameter*/
    private static final String REGEX_SINGLE_PARAMETER = "[^;\\n\\r]+";
    /** The regular expression of multiple parameters */
    private static final String REGEX_MULTIPLE_PARAMETER 
            = REGEX_SINGLE_PARAMETER + "(?:;" + REGEX_SINGLE_PARAMETER + ")*";
    /** The regular expression of a generic command, 
     * containing one capturing group for the command and one for all parameters */
    private static final String REGEX_COMMAND = "(\\S+)(?: (" + REGEX_MULTIPLE_PARAMETER + "))?";
    private static final int REGEX_GROUP_COMMAND_NAME = 1;
    private static final int REGEX_GROUP_COMMAND_PARAMETER = 2;

    /**
     * Constructs a new command parser.
     */
    public IPDCommandParser() {
        
    }

    @Override
    public Pair<String, List<String>> parse(final String input) {
        final Pattern commandPattern = Pattern.compile(REGEX_COMMAND);
        final Matcher commandMatcher = commandPattern.matcher(input);

        if (!commandMatcher.matches()) {
            return null;
        }

        final String commandName = commandMatcher.group(REGEX_GROUP_COMMAND_NAME);

        if (commandMatcher.groupCount() < 2 || commandMatcher.group(REGEX_GROUP_COMMAND_PARAMETER) == null) {
            return new Pair<>(commandName, List.of());
        }

        final String parameterString = commandMatcher.group(REGEX_GROUP_COMMAND_PARAMETER);
        final List<String> parameters = extractParameters(parameterString);

        return new Pair<>(commandName, parameters);
    }

    /**
     * Extracts the parameters from the given string into a List of Strings.
     *
     * @param parameterString the a non-{@code null} String containing the parameters in the specified pattern.
     *
     * @return the parameters as list of strings.
     */
    private static List<String> extractParameters(final String parameterString) {
        final List<String> parameters = new LinkedList<>();

        final Pattern singleParam = Pattern.compile(REGEX_SINGLE_PARAMETER);
        final Matcher paramMatcher = singleParam.matcher(parameterString);

        while (paramMatcher.find()) {
            parameters.add(paramMatcher.group());
        }

        return parameters;
    }

}