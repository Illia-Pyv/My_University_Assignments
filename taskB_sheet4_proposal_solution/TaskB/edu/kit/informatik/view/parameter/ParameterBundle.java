/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.parameter;

import java.util.Map;
import java.util.Objects;
import java.util.HashMap;

import edu.kit.informatik.model.resources.Errors;

/**
 * This class describes a parameter bundle.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class ParameterBundle {
    private final Map<Parameter<?>, Object> parameters;

    /**
     * Constructs an empty Parameter Bundle.
     */
    public ParameterBundle() {
        this.parameters = new HashMap<>();
    }

    /**
     * Puts a new Parameter into the bundle.
     *
     * @param parameter the parameter type
     * @param value the parameter associated with the {@link Parameter}
     */
    public void put(final Parameter<?> parameter, final Object value) {
        if (parameter.getType() != value.getClass()) {
            throw new IllegalArgumentException(String.format(Errors.PARAM_CLASS_MATCH, 
                    parameter.getType(), value.getClass()));
        }
        this.parameters.put(parameter, value);
    }

    /**
     * Returns the specified parameter.
     *
     * @param parameter The parameter type to get
     * @param <T> The type parameter, inference by the given parameter type
     * @return the object associated with this parameter, or {@code null} if there is none
     */
    @SuppressWarnings("unchecked") // It is guaranteed by put that the classes match!
    public <T> T get(final Parameter<T> parameter) {
        return (T) this.parameters.get(Objects.requireNonNull(parameter));
    }

    /**
     * Returns if the bundle contains a parameter. This is helpful when working
     * with optional parameters.
     *
     * @param parameter the {@link Parameter} to check
     *
     * @return {@code true} if the parameter is contained, {@code false} otherwise
     */
    public boolean contains(final Parameter<?> parameter) {
        return this.parameters.containsKey(parameter);
    }

    /**
     * Returns the size of this bundle.
     *
     * @return the size of this bundle
     */
    public int size() {
        return this.parameters.size();
    }

}