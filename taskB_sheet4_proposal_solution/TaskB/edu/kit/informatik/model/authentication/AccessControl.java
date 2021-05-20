/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.model.authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.informatik.model.authentication.roles.User;
import edu.kit.informatik.model.resources.Errors;

/**
 * A system to provide access control.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class AccessControl {
    private User loggedInUser;
    private final Map<String, User> users = new HashMap<>();

    /**
     * Constructs a new access control.
     */
    public AccessControl() {

    }

    /**
     * Returns the logged in user.
     *
     * @return The logged in user or {@code null} if nobody is logged in.
     */
    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    /**
     * Logout the currently logged in user.
     *
     * @return true if an user was logged in, false otherwise
     */
    public boolean logout() {
        if (this.loggedInUser != null) {
            this.loggedInUser = null;
            return true;
        }
        return false;
    }

    /**
     * Logs in the user if a user with this identification and password exists.
     *
     * @param identification the identification
     * @param password the password
     *
     * @return if the user was logged in, false otherwise
     * @throws IllegalStateException if somebody was already logged in
     */
    public boolean authenticate(final String identification, final String password) {
        if (this.getLoggedInUser() != null) {
            throw new IllegalStateException(Errors.COMMAND_NOT_AVAIL_LOGGED_IN);
        }

        final User user = this.getUser(identification);
        if (user != null && user.passwordMatches(password)) {
            this.loggedInUser = user;
            return true;
        }
        return false;
    }

    /**
     * Adds an user if a user with this identification does not exist.
     *
     * @param user the user to add
     * @return true if the user was added, false if a user with this identification did already exist
     */
    public boolean addUser(final User user) {
        if (this.users.get(user.getIdentification()) == null) {
            this.users.put(user.getIdentification(), user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a list of the users.
     *
     * @return a list of the users
     */
    public List<User> getUsers() {
        return List.copyOf(this.users.values());
    }

    /**
     * Returns the user with the given username.
     *  
     * @param username the username of the user
     * @return the user with the given username, or {@code null} if there is none
     */
    public User getUser(final String username) {
        return this.users.get(username);
    }
}