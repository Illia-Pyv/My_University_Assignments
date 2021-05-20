/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.parameter;

import edu.kit.informatik.model.resources.Errors;

/**
 * This class describes an integer parameter.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class IntegerParameter extends Parameter<Integer> {

    /**
     * Constructs a new integer parameter.
     */
    public IntegerParameter() {
        super(Integer.class);
    }

    @Override
    public Integer fromString(final String str) throws ParseException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(Errors.PARSING_FAILED, str, getType().toString()));
        }
    }
}