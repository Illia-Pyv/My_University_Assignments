/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter.authentication;

import java.util.List;

import edu.kit.informatik.model.authentication.AccessControl;
import edu.kit.informatik.presenter.authentication.commands.AddAgent;
import edu.kit.informatik.presenter.authentication.commands.AddCustomer;
import edu.kit.informatik.presenter.authentication.commands.AddMailman;
import edu.kit.informatik.presenter.authentication.commands.Authenticate;
import edu.kit.informatik.presenter.authentication.commands.Logout;
import edu.kit.informatik.view.command.Command;
import edu.kit.informatik.view.command.CommandSupplier;

/**
 * This class describes an command provider for authentication and access control.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class AuthenticationCommandSupplier implements CommandSupplier {

    private final List<Command> commands;

    /**
     * Constructs a command provider for authentication.
     * 
     * @param accessControl the {@link AccessControl} to control with the commands
     */
    public AuthenticationCommandSupplier(final AccessControl accessControl) {
        this.commands = List.of(
                new Logout(accessControl),
                new AddMailman(accessControl),
                new AddCustomer(accessControl),
                new AddAgent(accessControl),
                new Authenticate(accessControl)
            );
    }

    @Override
    public List<Command> get() {
        // no copy needed since List.of() creates a immutable list and commands are immutable by contract.
        return this.commands;
    }

}