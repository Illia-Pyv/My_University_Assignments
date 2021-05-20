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
import edu.kit.informatik.model.post.PostalService;
import edu.kit.informatik.model.resources.Messages;
import edu.kit.informatik.presenter.RestrictedCommand;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.view.parameter.AuthenticationParameter;
import edu.kit.informatik.view.parameter.EnumParameter;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;

/**
 * This class describes a send mail command.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class SendMail extends RestrictedCommand {

    private static final String REGEX_SEND_MAIL = "send-mail";

    private final PostSystem postSystem;
    private final Parameter<PostalService> service = new EnumParameter<>(PostalService.class);
    private final Parameter<String> receiver = new AuthenticationParameter();
    private final Parameter<String> sender = new AuthenticationParameter().setOptional(true);

    /**
     * Constructs a new instance.
     *
     * @param postSystem the post system
     */
    public SendMail(final PostSystem postSystem) {
        super(postSystem);
        this.postSystem = postSystem;
    }

    @Override
    protected Result execute(final ParameterBundle bundle) throws PostSystemException {
        String sender;
        if (this.postSystem.getAccessControl().getLoggedInUser() instanceof Customer) {
            sender = this.postSystem.getAccessControl().getLoggedInUser().getIdentification();
        } else { // must be MAILMAN
            sender = bundle.get(this.sender);
        }
        this.postSystem.sendMail(sender, bundle.get(this.receiver), bundle.get(this.service));
        return new Result(Result.ResultType.SUCCESS, Messages.SUCCESS);
    }

    @Override
    protected List<Role> getAllowedRoles() {
        return List.of(Role.CUSTOMER, Role.MAILMAN);
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of(this.service, this.receiver, this.sender);
    }

    @Override
    public String getRegex() {
        return REGEX_SEND_MAIL;
    }

    @Override
    protected Map<Role, List<Parameter<?>>> parameterByRole() {
        return Map.of(
                Role.CUSTOMER, List.of(this.service, this.receiver),
                Role.MAILMAN, List.of(this.service, this.receiver, this.sender)
            );
    }

}