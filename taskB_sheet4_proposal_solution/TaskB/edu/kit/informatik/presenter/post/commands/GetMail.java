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
import edu.kit.informatik.model.resources.Errors;
import edu.kit.informatik.model.resources.Messages;
import edu.kit.informatik.presenter.RestrictedCommand;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.view.parameter.AuthenticationParameter;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;

/**
 * This class describes a command to clear the received mail of a customer.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class GetMail extends RestrictedCommand {

    private static final String REGEX_GET_MAIL = "get-mail";
    
    private final Parameter<String> username = new AuthenticationParameter().setOptional(true);
    private final PostSystem postSystem;

    /**
     * Constructs a new instance.
     *
     * @param postSystem the post system
     */
    public GetMail(final PostSystem postSystem) {
        super(postSystem);
        this.postSystem = postSystem;
    }


    @Override
    protected Result execute(final ParameterBundle bundle) throws PostSystemException {
        String username;
        if (this.postSystem.getAccessControl().getLoggedInUser() instanceof Customer) {
            username = this.postSystem.getAccessControl().getLoggedInUser().getIdentification();
        } else { // must be MAILMAN
            username = bundle.get(this.username);
        }
        final Customer customer = this.postSystem.getCustomer(username);
        if (customer.clearReceivedMail()) {
            return new Result(Result.ResultType.SUCCESS, Messages.SUCCESS);
        } else {
            return new Result(Result.ResultType.FAILURE, Errors.MAILBOX_EMPTY);
        }
    }

    @Override
    protected List<Role> getAllowedRoles() {
        return List.of(Role.CUSTOMER, Role.MAILMAN);
    }

    @Override
    protected Map<Role, List<Parameter<?>>> parameterByRole() {
        return Map.of(
                Role.CUSTOMER, List.of(),
                Role.MAILMAN, List.of(this.username)
            );
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of(this.username);
    }

    @Override
    public String getRegex() {
        return REGEX_GET_MAIL;
    }

}