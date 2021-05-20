/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter.authentication.commands;

import edu.kit.informatik.model.authentication.AccessControl;
import edu.kit.informatik.model.authentication.roles.Role;

/**
 * This class describes an command to add a mailman to a {@link AccessControl}.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class AddMailman extends AddEmployee {

    private static final String REGEX_ADD_MAILMAN = "add-mailman";
    
    /**
     * Constructs a new command to add a mailman
     *
     * @param accessControl the {@link AccessControl} where to add the mailman
     */
    public AddMailman(final AccessControl accessControl) {
        super(accessControl);
    }

    @Override
    protected Role getRole() {
        return Role.MAILMAN;
    }

    @Override
    public String getRegex() {
        return REGEX_ADD_MAILMAN;
    }

}