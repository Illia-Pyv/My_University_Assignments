/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.core;

import java.util.Comparator;

/**
 * This class describes a lexicographic comparator. Used for sorting
 * objects lexicographic by their {@link Object#toString} representation.
 *
 * @author Lucas Alber
 * @version 1.0
 * @param <T> the type of objects to sort
 */
public class LexicographicComparator<T> implements Comparator<T> {

    @Override
    public int compare(final T o1, final T o2) {
        return o1.toString().compareTo(o2.toString());
    }

}