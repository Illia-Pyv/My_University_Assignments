/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.model.post;

/**
 * Exception for signaling post system errors.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class PostSystemException extends Exception {

    private static final long serialVersionUID = 7638739206852225436L;

    /**
     * Constructs a new exception with message.
     *
     * @param message the message
     */
    public PostSystemException(final String message) {
        super(message);
    }
    
}