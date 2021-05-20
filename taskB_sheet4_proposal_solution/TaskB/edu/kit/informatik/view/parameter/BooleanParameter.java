/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.parameter;

import edu.kit.informatik.model.resources.Errors;

/**
 * This class describes a boolean parameter.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class BooleanParameter extends Parameter<Boolean> {

    private static final String PARAMETER_REGEX = "true|false|True|False|TRUE|FALSE";

    /**
     * Constructs a new boolean parameter.
     */
    public BooleanParameter() {
        super(Boolean.class);
    }

    @Override
    public Boolean fromString(final String str) throws ParseException {
        if (!str.matches(PARAMETER_REGEX)) {
            throw new ParseException(String.format(Errors.PARSING_FAILED, str, Boolean.class.getSimpleName()));
        }
        return Boolean.parseBoolean(str);
    }
}