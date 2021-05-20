/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.parameter;

/**
 * An exception thrown if parsing fails.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class ParseException extends Exception {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -2705770202564290141L;
    
    /**
     * Constructs a exception with message.
     *
     * @param message the message describing the exception
     */
    public ParseException(final String message) {
        super(message);
    }
}
