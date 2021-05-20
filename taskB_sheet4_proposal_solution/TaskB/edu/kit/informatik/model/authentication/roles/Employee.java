/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.model.authentication.roles;

/**
 * This class describes an employee.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class Employee extends User {

    private final int stuffNumber;

    /**
     * Constructs a new employee.
     *
     * @param role the role of the user
     * @param forename the forename of the user
     * @param surname the surname of the user
     * @param stuffNumber the stuff number of the user
     * @param password the password of the user
     */
    public Employee(final Role role, final String forename, final String surname, 
            final int stuffNumber, final String password) {
        super(role, forename, surname, String.valueOf(stuffNumber), password);
        this.stuffNumber = stuffNumber;
    }

    /**
     * Returns the stuff number of this user.
     *
     * @return the stuff number of this user
     */
    public int getStuffNumber() {
        return this.stuffNumber;
    }

}