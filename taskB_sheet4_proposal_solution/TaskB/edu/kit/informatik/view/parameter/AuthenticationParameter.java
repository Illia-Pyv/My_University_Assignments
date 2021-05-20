/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.parameter;

import edu.kit.informatik.model.resources.Errors;

/**
 * This class describes a parameter for usernames and passwords.
 * 
 * Such parameter must have between 4 and 9 characters.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class AuthenticationParameter extends StringParameter {

    /** The minimum length of a authentication parameter */
    public static final int AUTH_MIN_LENGTH = 4;
    /** The maximum length of a authentication parameter */
    public static final int AUTH_MAX_LENGTH = 9;

    /**
     * Constructs a new parameter.
     */
    public AuthenticationParameter() {
        super();
    }

    @Override
    public String fromString(final String str) throws ParseException {
        if (str.length() < AUTH_MIN_LENGTH || str.length() > AUTH_MAX_LENGTH) {
            throw new ParseException(String.format(Errors.AUTH_PARAM_LENGTH, AUTH_MIN_LENGTH, AUTH_MAX_LENGTH));
        }
        return str;
    }
    
}