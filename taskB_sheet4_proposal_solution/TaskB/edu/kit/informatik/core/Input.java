/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.core;

/**
 * This interface describes an input.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public interface Input {
    
    /**
     * Reads a string from the input. The method should block until the next
     * input is available.
     *
     * @return the next input string.
     */
    String read();
}