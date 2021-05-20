/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.model.authentication.roles;

import java.util.EnumMap;
import java.util.Map;

import edu.kit.informatik.model.post.PostalService;

/**
 * This class describes a customer.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class Customer extends User {

    private final String identityCardNumber;

    private final Map<PostalService, Integer> sentMail = new EnumMap<>(PostalService.class);
    private final Map<PostalService, Integer> receivedMail = new EnumMap<>(PostalService.class);

    /**
     * Constructs a new customer.
     *
     * @param forename the forename of the user
     * @param surname the surname of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param identityCardNumber the number of the identity card
     */
    public Customer(final String forename, final String surname, final String username, 
            final String password, final String identityCardNumber) {
        super(Role.CUSTOMER, forename, surname, username, password);
        this.identityCardNumber = identityCardNumber;
    }

    /**
     * Returns the identity card number.
     *
     * @return the identity card number
     */
    public String getIdentityCardNumber() {
        return this.identityCardNumber;
    }

    /**
     * Adds the postal service to the sent mail of this customer.
     *
     * @param service the postal service sent
     */
    public void sendMail(final PostalService service) {
        this.sentMail.put(service, this.sentMail.getOrDefault(service, 0) + 1);
    }

    /**
     * Adds the postal service to the received mail of this customer.
     *
     * @param service the postal service received
     */
    public void receivedMail(final PostalService service) {
        this.receivedMail.put(service, this.receivedMail.getOrDefault(service, 0) + 1);
    }

    /**
     * Clears the received mail.
     * @return {@code true} if there was any mail, {@code false} otherwise
     */
    public boolean clearReceivedMail() {
        if (this.receivedMail.isEmpty()) {
            return false;
        }
        this.receivedMail.clear();
        return true;
    }

    /**
     * Returns a Map of the sent mail as (service, count)-entries.
     *
     * @return the sent mail.
     */
    public Map<PostalService, Integer> getSentMail() {
        return new EnumMap<>(this.sentMail);
    }

    /**
     * Returns a Map of the received mail as (service, count)-entries.
     *
     * @return the received mail.
     */
    public Map<PostalService, Integer> getReceivedMail() {
        return new EnumMap<>(this.receivedMail);
    }

}