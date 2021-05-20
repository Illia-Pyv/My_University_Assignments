/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.parameter;

import edu.kit.informatik.model.resources.Errors;

/**
 * This class describes a double parameter.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class DoubleParameter extends Parameter<Double> {

    /**
     * Constructs a new double parameter.
     */
    public DoubleParameter() {
        super(Double.class);
    }

    @Override
    public Double fromString(final String str) throws ParseException {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(Errors.PARSING_FAILED, str, getType().toString()));
        }
    }
}