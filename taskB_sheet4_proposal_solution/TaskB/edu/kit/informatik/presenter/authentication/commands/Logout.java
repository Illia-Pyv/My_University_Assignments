/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter.authentication.commands;

import java.util.List;

import edu.kit.informatik.model.authentication.AccessControl;
import edu.kit.informatik.model.resources.Errors;
import edu.kit.informatik.model.resources.Messages;
import edu.kit.informatik.view.command.Command;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;

/**
 * This class describes a logout command.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class Logout extends Command {

    private static final String REGEX_LOGOUT = "logout";

    private final AccessControl accessControl;

    /**
     * Constructs a new logout command.
     *
     * @param  accessControl the target of the logout command
     */
    public Logout(final AccessControl accessControl) {
        this.accessControl = accessControl;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of();
    }

    @Override
    public String getRegex() {
        return REGEX_LOGOUT;
    }

    @Override
    public Result apply(final ParameterBundle bundle) {
        if (this.accessControl.logout()) {
            return new Result(Result.ResultType.SUCCESS, Messages.SUCCESS);
        }
        return new Result(Result.ResultType.FAILURE, Errors.NOBODY_LOGGED_IN);
    }

}