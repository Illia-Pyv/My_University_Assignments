/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.parameter;

import java.util.List;

import edu.kit.informatik.core.Utilities;
import edu.kit.informatik.model.resources.Errors;

/**
 * This class describes a parameter for a enum.
 * The parsing is done by comparing the given string to the
 * values toString method.
 * 
 * @author Lucas Alber
 * @version 1.0
 * 
 * @param <T> the enum type to parse
 */
public class EnumParameter<T extends Enum<T>> extends Parameter<T> {

    // used for error message
    private static final String VALUES_SEPARATOR = ", ";

    private final List<T> enumConstants;

    /**
     * Constructs a enum parameter.
     *  
     * @param type the class of the parsing result
     */
    public EnumParameter(final Class<T> type) {
        super(type);
        this.enumConstants = List.of(type.getEnumConstants());
    }

    @Override
    public T fromString(final String str) throws ParseException {
        for (T enumConstant : this.enumConstants) {
            if (str.equals(enumConstant.toString())) {
                return enumConstant;
            }
        }
        throw new ParseException(String.format(Errors.POSTAL_SERVICE_PARAMETER_INVALID, 
            Utilities.join(VALUES_SEPARATOR, this.enumConstants), str));
    }

}