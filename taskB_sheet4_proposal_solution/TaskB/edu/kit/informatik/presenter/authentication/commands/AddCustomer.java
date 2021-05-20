/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter.authentication.commands;

import java.util.List;

import edu.kit.informatik.model.authentication.AccessControl;
import edu.kit.informatik.model.authentication.roles.Customer;
import edu.kit.informatik.model.authentication.roles.User;
import edu.kit.informatik.model.resources.Errors;
import edu.kit.informatik.model.resources.Messages;
import edu.kit.informatik.view.command.Command;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.view.parameter.AuthenticationParameter;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;
import edu.kit.informatik.view.parameter.StringParameter;

/**
 * This class describes a command to add a customer to an {@link AccessControl}.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class AddCustomer extends Command {

    private static final String REGEX_ADD_CUSTOMER = "add-customer";
    private static final int ID_CARD_NUM_LENGTH = 9;

    private final AccessControl accessControl;

    private final Parameter<String> forename = new StringParameter();
    private final Parameter<String> surname = new StringParameter();
    private final Parameter<String> username = new AuthenticationParameter();
    private final Parameter<String> password = new AuthenticationParameter();
    private final Parameter<String> identityCardNum = new StringParameter();

    /**
     * Constructs a new command to an {@link AccessControl}.
     *
     * @param accessControl the {@link AccessControl} the customer should be added 
     */
    public AddCustomer(final AccessControl accessControl) {
        this.accessControl = accessControl;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of(
                this.forename,
                this.surname,
                this.username,
                this.password,
                this.identityCardNum
            );
    }

    @Override
    public String getRegex() {
        return REGEX_ADD_CUSTOMER;
    }

    @Override
    public Result apply(final ParameterBundle bundle) {
        if (this.accessControl.getLoggedInUser() != null) {
            return new Result(Result.ResultType.FAILURE, Errors.COMMAND_NOT_AVAIL_LOGGED_IN);
        }

        final String username = bundle.get(this.username);
        final String password = bundle.get(this.password);
        final String forename = bundle.get(this.forename);
        final String surname = bundle.get(this.surname);

        final String identityCardNumber = bundle.get(this.identityCardNum);
        if (identityCardNumber.equals(username)) {
            return new Result(Result.ResultType.FAILURE, Errors.CUSTOMER_PRIVACY);
        }

        if (identityCardNumber.length() != ID_CARD_NUM_LENGTH) {
            return new Result(Result.ResultType.FAILURE, String.format(Errors.IDENTITY_NUM_LENGTH, 
                ID_CARD_NUM_LENGTH));
        }

        for (User user : this.accessControl.getUsers()) {
            if (user instanceof Customer) {
                if (((Customer) user).getIdentityCardNumber().equals(identityCardNumber)) { // id exists
                    return new Result(Result.ResultType.FAILURE, 
                    String.format(Errors.IDENTITY_NUM_EXISTS, identityCardNumber));
                }
            }
        }

        final User customer = new Customer(forename, surname, username, password, identityCardNumber);

        if (this.accessControl.addUser(customer)) {
            return new Result(Result.ResultType.SUCCESS, Messages.SUCCESS);
        } else {
            return new Result(Result.ResultType.FAILURE, String.format(Errors.USERNAME_IN_USE, username));
        }
    }

}