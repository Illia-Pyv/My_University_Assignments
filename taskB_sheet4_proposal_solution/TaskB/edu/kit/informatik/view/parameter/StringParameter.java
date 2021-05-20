/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.parameter;

/**
 * This class describes a string parameter.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class StringParameter extends Parameter<String> {

    /**
     * Constructs a new string parameter.
     */
    public StringParameter() {
        super(String.class);
    }

    @Override
    public String fromString(final String str) throws ParseException {
        return str;
    }
}