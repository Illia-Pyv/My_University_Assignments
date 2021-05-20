/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.parameter;

/**
 * Describes a Parameter.
 * 
 * @author Lucas Alber
 * @version 1.0
 *
 * @param <T> the type of the parsing result
 */
public abstract class Parameter<T> {

    private final Class<T> type;
    private String description;
    private boolean isOptional;

    /**
     * Constructs a new mandatory parameter, without description.
     *
     * @param type the class of the parsing result
     */
    public Parameter(final Class<T> type) {
        this.type = type;
    }

    /**
     * Returns the type of the parameter.
     *
     * @return The type.
     */
    public Class<T> getType() {
        return this.type;
    }

    /**
     * Returns the description of the parameter.
     *
     * @return the description or {@code null} if there is none.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Signals if this {@link Parameter} is optional. In this case the parser does
     * make a best-effort attempt to provide the parameters.
     *
     * @return {@code false} if this {@link Parameter} is mandatory,{@code false} otherwise.
     */
    public boolean isOptional() {
        return this.isOptional;
    }

    /**
     * Sets if this parameter is optional. In this case the parser does
     * make a best-effort attempt to provide the parameters.
     *
     * @param isOptional {@code false} if this {@link Parameter} is mandatory,{@code false} otherwise.
     *
     * @return {@code this} for chaining
     */
    public Parameter<T> setOptional(final boolean isOptional) {
        this.isOptional = isOptional;
        return this;
    }

    /**
     * Sets the description.
     *
     * @param description the description, or {@code null}
     *
     * @return {@code this} for chaining
     */
    public Parameter<T> setDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * Parses a string to the parameter type.
     *
     * @param str the string to parse
     *
     * @return a object of the type of the parameter
     *
     * @throws ParseException if the string cannot be parsed
     */
    public abstract T fromString(String str) throws ParseException;

}