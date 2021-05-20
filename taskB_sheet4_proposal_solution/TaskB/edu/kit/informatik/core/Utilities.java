/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.core;

import java.util.LinkedList;
import java.util.List;

/**
 * This class provides utility functions.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public final class Utilities {
    private Utilities() { }

    /**
     * A copy of {@link String#join} witch allows joining objects.
     *
     * @param separator the separator between the tokens from the iterable
     * @param iterable the iterable to join
     *
     * @return a string analog to {@link String#join}
     */
    public static String join(final String separator, final Iterable<?> iterable) {
        final List<String> stringList = new LinkedList<>();
        for (Object object : iterable) {
            stringList.add(object.toString());
        }
        return String.join(separator, stringList);
    }
}