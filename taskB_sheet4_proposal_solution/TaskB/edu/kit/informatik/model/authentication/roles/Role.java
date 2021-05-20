/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.model.authentication.roles;

/**
 * An enumeration of access roles.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public enum Role {
    /**
     * A customer of the post office.
     */
    CUSTOMER,
    /**
     * An employee of the post office.
     */
    MAILMAN,
    /**
     * An employee of the post office, working in the call center.
     */
    AGENT;
}