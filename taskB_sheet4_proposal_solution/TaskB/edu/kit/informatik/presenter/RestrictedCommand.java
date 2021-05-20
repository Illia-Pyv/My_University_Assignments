/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter;

import java.util.List;
import java.util.Map;

import edu.kit.informatik.core.Utilities;
import edu.kit.informatik.model.authentication.AccessControl;
import edu.kit.informatik.model.authentication.roles.Role;
import edu.kit.informatik.model.post.PostSystem;
import edu.kit.informatik.model.post.PostSystemException;
import edu.kit.informatik.model.resources.Errors;
import edu.kit.informatik.view.command.Command;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;

/**
 * This class describes a command, that can only be run, if the users {@link Role} allows it.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public abstract class RestrictedCommand extends Command {

    private static final String ROLE_SEPARATOR = ", ";

    private final AccessControl accessControl;

    /**
     * Constructs a restricted command which checks against the given {@link PostSystem}.
     * 
     * @param postSystem the {@link PostSystem} to check against
     */
    public RestrictedCommand(final PostSystem postSystem) {
        this.accessControl = postSystem.getAccessControl();
    }

    @Override
    public final Result apply(final ParameterBundle bundle) {
        if (this.accessControl.getLoggedInUser() == null) {
            return new Result(Result.ResultType.FAILURE, Errors.COMMAND_NOT_AVAIL_LOGGED_OUT);
        }

        if (!this.getAllowedRoles().contains(this.accessControl.getLoggedInUser().getRole())) {
            return new Result(Result.ResultType.FAILURE, String.format(Errors.COMMAND_RESTRICTED,
                Utilities.join(ROLE_SEPARATOR, this.getAllowedRoles())));
        }

        if (this.parameterByRole() != null) {
            final Role role = this.accessControl.getLoggedInUser().getRole();
            if (this.parameterByRole().get(role) != null) {
                if (bundle.size() != this.parameterByRole().get(role).size()) {
                    return new Result(Result.ResultType.FAILURE, String.format(Errors.PARAMETER_COUNT_EXACTLY,
                            this.parameterByRole().get(role).size(), bundle.size()));
                }
                for (Parameter<?> parameter : this.parameterByRole().get(role)) {
                    if (!bundle.contains(parameter)) {
                        final String parameterDescription = parameter.getDescription() == null 
                            ? parameter.getClass().getSimpleName() : parameter.getDescription();
                        return new Result(Result.ResultType.FAILURE, 
                            String.format(Errors.PARAMETER_MISSING, parameterDescription));
                    }
                }
            }
        }

        try {
            return this.execute(bundle);
        } catch (PostSystemException e) {
            return new Result(Result.ResultType.FAILURE, e.getMessage());
        }
    }

    /**
     * Internal template method to run the command. The {@link RestrictedCommand}
     * does call this internally if the role of the current logged in user is valid.
     *
     * @param bundle the parameter bundle
     *
     * @return the {@link Result} of the command execution
     * @throws PostSystemException if the command did fail in the system
     */
    protected abstract Result execute(ParameterBundle bundle) throws PostSystemException;

    /**
     * Returns a list of {@link Role} which are allowed to execute the command.
     *
     * @return     The allowed roles.
     */
    protected abstract List<Role> getAllowedRoles();

    /**
     * Returns a map of Roles and Parameters. This is used for 
     * parameter validation when using optional parameters.
     * 
     * The {@link #execute} method is only called if the {@link ParameterBundle}
     * does exactly contain the parameters specified here.
     * 
     * Returning {@code null} does disable parameter validation.
     * 
     * @return a map of {@link Role} and {@link Parameter}
     */
    protected abstract Map<Role, List<Parameter<?>>> parameterByRole();
    
}