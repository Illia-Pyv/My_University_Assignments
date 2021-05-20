/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter.authentication.commands;

import edu.kit.informatik.model.authentication.AccessControl;
import edu.kit.informatik.model.authentication.roles.Role;

/**
 * This class describes an command to add an agent to a {@link AccessControl}.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class AddAgent extends AddEmployee {

    private static final String REGEX_ADD_AGENT = "add-agent";
    
    /**
     * Constructs a new command to add an agent
     *
     * @param accessControl the {@link AccessControl} where to add the mailmen
     */
    public AddAgent(final AccessControl accessControl) {
        super(accessControl);
    }

    @Override
    protected Role getRole() {
        return Role.AGENT;
    }

    @Override
    public String getRegex() {
        return REGEX_ADD_AGENT;
    }

}