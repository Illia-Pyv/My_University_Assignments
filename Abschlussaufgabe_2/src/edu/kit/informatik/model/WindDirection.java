package edu.kit.informatik.model;

/**
 * This class represents all the directions in which the wind can blow.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class WindDirection {
    /**
     * This number represents the wind that blows in all directions simultaneously.
     */
    public static final int ALL_DIRECTIONS = 1;
    /**
     * This number represents the wind that blows to the north.
     */
    public static final int NORTH = 2;
    /**
     * This number represents the wind that blows to the east.
     */
    public static final int EAST = 3;
    /**
     * This number represents the wind that blows to the south.
     */
    public static final int SOUTH = 4;
    /**
     * This number represents the wind that blows to the west.
     */
    public static final int WEST = 5;

    // Utility classes have a private constructor
    private WindDirection() {

    }
}
