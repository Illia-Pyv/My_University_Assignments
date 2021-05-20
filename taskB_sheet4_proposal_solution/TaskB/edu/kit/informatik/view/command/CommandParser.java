/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.command;

import java.util.List;

import edu.kit.informatik.core.Pair;

/**
 * This interface describes a parser for commands.
 * The parser parses a string into a command name and a List of parameters.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public interface CommandParser {

    /**
     * Parses the given string into a {@link Pair} of command name and a List of parameters.
     *
     * @param input the input string
     *
     * @return a {@link Pair} of command name and a List of parameters as string representation.
     *      Returns {@code null} if the string could not be parsed.
     */
    Pair<String, List<String>> parse(String input);
}