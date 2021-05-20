/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.model.authentication.roles;

import java.util.Objects;

/**
 * This class describes an user of a access control system.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public abstract class User {
    private final Role role;
    private final String identification;
    private String password;
    private final String forename;
    private final String surname;

    /**
     * Constructs a new User.
     *
     * @param role the role of the user
     * @param forename the forename of the user
     * @param surname the surname of the user
     * @param identification the identity of the user
     * @param password the password of the user
     */
    public User(final Role role, final String forename, final String surname,
            final String identification, final String password) {
        this.role = Objects.requireNonNull(role);
        this.identification = Objects.requireNonNull(identification);
        this.password = Objects.requireNonNull(password);
        this.forename = Objects.requireNonNull(forename);
        this.surname = Objects.requireNonNull(surname);
    }

    /**
     * Returns the role of the user.
     *
     * @return the role of the user
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Returns the identification.
     *
     * @return the identification of the user.
     */
    public String getIdentification() {
        return this.identification;
    }

    /**
     * Returns the forename of the user.
     *
     * @return the forename of the user
     */
    public String getForename() {
        return this.forename;
    }

    /**
     * Returns the surname of the user.
     *
     * @return the surname of the user
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Determines if the password matches.
     *
     * @param password the string to check against
     *
     * @return true if password matches, false otherwise.
     */
    public boolean passwordMatches(final String password) {
        return this.password.matches(password);
    }

    /**
     * Allows setting a new password for this user
     *
     * @param password the password
     */
    public void setPassword(final String password) {
        this.password = password;
    }
}
