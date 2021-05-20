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
import edu.kit.informatik.view.parameter.StringParameter;

/**
 * This class describes an authenticate command.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class Authenticate extends Command {

    private static final String REGEX_AUTHENTICATE = "authenticate";

    private final Parameter<String> username = new StringParameter();
    private final Parameter<String> password = new StringParameter();

    private final AccessControl accessControl;

    /**
     * Constructs an authenticate command.
     *
     * @param accessControl the {@link AccessControl} where to perform the login.
     */
    public Authenticate(final AccessControl accessControl) {
        this.accessControl = accessControl;
    }


    @Override
    public List<Parameter<?>> getParameters() {
        return List.of(
                this.username,
                this.password
            );
    }

    @Override
    public String getRegex() {
        return REGEX_AUTHENTICATE;
    }

    @Override
    public Result apply(final ParameterBundle bundle) {
        if (this.accessControl.getLoggedInUser() != null) {
            return new Result(Result.ResultType.FAILURE, Errors.COMMAND_NOT_AVAIL_LOGGED_IN);
        }

        if (this.accessControl.authenticate(bundle.get(this.username), bundle.get(this.password))) {
            return new Result(Result.ResultType.SUCCESS, Messages.SUCCESS);
        } else {
            return new Result(Result.ResultType.FAILURE, Errors.LOGIN_FAILED);
        }
    }

}