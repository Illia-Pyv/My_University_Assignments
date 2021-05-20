/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.model.post;

/**
 * Enumeration of the available postal services.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public enum PostalService {
    /**
     * Represents a letter
     */
    LETTER("Brief", 0.7),
    /**
     * Represents registered mail, to drop in
     */
    DROP_IN_REGISTERED_MAIL("Einwurf-Einschreiben", 1.2),
    /**
     * Represents registered mail
     */
    REGISTERED_MAIL("Einschreiben", 2.0),
    /**
     * Represents a parcel, size small
     */
    PARCEL_S("PaketS", 5.0),
    /**
     * Represents a parcel, size medium
     */
    PARCEL_M("PaketM", 6.0),
    /**
     * Represents a parcel, size large
     */
    PARCEL_L("PaketL", 7.0);

    private final String stringRepresentation;
    private final double price;

    private PostalService(final String stringRepresentation, final double price) {
        this.stringRepresentation = stringRepresentation;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.stringRepresentation;
    }

    /**
     * Returns the price for this postal service.
     *
     * @return the price for this postal service
     */
    public double getPrice() {
        return this.price;
    }
}