/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.model.post;

import edu.kit.informatik.model.authentication.AccessControl;
import edu.kit.informatik.model.authentication.roles.Customer;
import edu.kit.informatik.model.authentication.roles.User;
import edu.kit.informatik.model.resources.Errors;

/**
 * This class describes a post system. The post system operates on the users
 * stored in a {@link AccessControl}.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class PostSystem {

    private final AccessControl accessControl;

    /**
     * Constructs a new {@link PostSystem}.
     * 
     * @param accessControl the {@link AccessControl} to use for user resolving
     */
    public PostSystem(final AccessControl accessControl) {
        this.accessControl = accessControl;
    }

    /**
     * Returns the {@link AccessControl} used as user database of this {@link PostSystem}.
     *
     * @return the {@link AccessControl} used as user database
     */
    public AccessControl getAccessControl() {
        return this.accessControl;
    }

    /**
     * Sends mail from one customer to another.
     *
     * @param usernameFrom the username of the sender
     * @param usernameTo he username of the receiver
     * @param service the postal service to send
     * @throws PostSystemException if one of the usernames are unknown
     */
    public void sendMail(final String usernameFrom, final String usernameTo, 
            final PostalService service) throws PostSystemException {
        final Customer from = this.getCustomer(usernameFrom);
        final Customer to = this.getCustomer(usernameTo);
        from.sendMail(service);
        to.receivedMail(service);
    }

    /**
     * Returns a customer with the given username. The customer
     * must be registered in the {@link AccessControl} of this {@link PostSystem}.
     *
     * @param username the username of the customer
     *
     * @return the customer, if any
     *
     * @throws PostSystemException if the username does not belongs to a customer or if the username is unknown
     */
    public Customer getCustomer(final String username) throws PostSystemException {
        final User user = this.accessControl.getUser(username);
        if (user == null) {
            throw new PostSystemException(String.format(Errors.USER_NOT_EXISTS, username));
        }
        if (!(user instanceof Customer)) {
            throw new PostSystemException(String.format(Errors.USERNAME_NOT_CUSTOMER, username));
        }
        return (Customer) user;
    }
    
}