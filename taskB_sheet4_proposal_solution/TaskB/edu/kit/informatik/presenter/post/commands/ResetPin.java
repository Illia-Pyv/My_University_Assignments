/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter.post.commands;

import java.util.List;
import java.util.Map;

import edu.kit.informatik.model.authentication.roles.Customer;
import edu.kit.informatik.model.authentication.roles.Role;
import edu.kit.informatik.model.post.PostSystem;
import edu.kit.informatik.model.post.PostSystemException;
import edu.kit.informatik.model.resources.Messages;
import edu.kit.informatik.presenter.RestrictedCommand;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.view.parameter.AuthenticationParameter;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;

/**
 * This class describes a reset pin command.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class ResetPin extends RestrictedCommand {

    private static final String REGEX_RESET_PIN = "reset-pin";

    private final Parameter<String> username = new AuthenticationParameter();
    private final Parameter<String> password = new AuthenticationParameter();
    private final PostSystem postSystem;

    /**
     * Constructs a new instance.
     *
     * @param postSystem the post system
     */
    public ResetPin(final PostSystem postSystem) {
        super(postSystem);
        this.postSystem = postSystem;
    }

    @Override
    protected Result execute(final ParameterBundle bundle) throws PostSystemException {
        final Customer customer = this.postSystem.getCustomer(bundle.get(this.username));
        customer.setPassword(bundle.get(this.password));
        return new Result(Result.ResultType.SUCCESS, Messages.SUCCESS);
    }

    @Override
    protected List<Role> getAllowedRoles() {
        return List.of(Role.AGENT);
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of(this.username, this.password);
    }

    @Override
    public String getRegex() {
        return REGEX_RESET_PIN;
    }

    @Override
    protected Map<Role, List<Parameter<?>>> parameterByRole() {
        return null;
    }

}