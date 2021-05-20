/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter.authentication.commands;

import java.util.List;

import edu.kit.informatik.model.authentication.AccessControl;
import edu.kit.informatik.model.authentication.roles.Employee;
import edu.kit.informatik.model.authentication.roles.User;
import edu.kit.informatik.model.resources.Errors;
import edu.kit.informatik.model.resources.Messages;
import edu.kit.informatik.model.authentication.roles.Role;
import edu.kit.informatik.view.command.Command;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.view.parameter.AuthenticationParameter;
import edu.kit.informatik.view.parameter.IntegerParameter;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;
import edu.kit.informatik.view.parameter.StringParameter;

/**
 * This class describes an abstract command to add an employee.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public abstract class AddEmployee extends Command {

    private final Parameter<String> forename = new StringParameter();
    private final Parameter<String> surname = new StringParameter();
    private final Parameter<Integer> staffNumber = new IntegerParameter();
    private final Parameter<String> password = new AuthenticationParameter();

    private final AccessControl accessControl;

    /**
     * Constructs a new abstract add-employee command.
     *
     * @param accessControl the {@link AccessControl} the employee should be added
     */
    public AddEmployee(final AccessControl accessControl) {
        this.accessControl = accessControl;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of(this.forename, this.surname, this.staffNumber, this.password);
    }

    @Override
    public Result apply(final ParameterBundle bundle) {
        if (this.accessControl.getLoggedInUser() != null) {
            return new Result(Result.ResultType.FAILURE, Errors.COMMAND_NOT_AVAIL_LOGGED_IN);
        }

        final String forename = bundle.get(this.forename);
        final String surname = bundle.get(this.surname);
        final Integer staffNumber = bundle.get(this.staffNumber);
        final String password = bundle.get(this.password);

        if (staffNumber < 0) {
            return new Result(Result.ResultType.FAILURE, Errors.STUFF_NUMBER_NEG);
        }

        final User employee = new Employee(this.getRole(), forename, surname, staffNumber, password);
        if (this.accessControl.addUser(employee)) {
            return new Result(Result.ResultType.SUCCESS, Messages.SUCCESS);
        } else {
            return new Result(Result.ResultType.FAILURE, String.format(Errors.STAFF_NUM_IN_USE, staffNumber));
        }
    }

    /**
     * A template method to return the role of the 
     * employee to add.
     *
     * @return the role
     */
    protected abstract Role getRole();

}