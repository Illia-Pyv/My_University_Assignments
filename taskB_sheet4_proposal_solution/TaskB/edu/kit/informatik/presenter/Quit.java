/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter;

import java.util.List;

import edu.kit.informatik.view.command.Command;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;

/**
 * This class describes a command to quit a interactive session.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class Quit extends Command {

    private static final String REGEX_QUIT = "quit";

    private final Session session;

    /**
     * Constructs a new command to quit a {@link Session}.
     *
     * @param session the session to quit
     */
    public Quit(final Session session) {
        this.session = session;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of();
    }

    @Override
    public String getRegex() {
        return REGEX_QUIT;
    }

    @Override
    public Result apply(final ParameterBundle bundle) {
        this.session.quit();
        return new Result(Result.ResultType.SUCCESS);
    }

}