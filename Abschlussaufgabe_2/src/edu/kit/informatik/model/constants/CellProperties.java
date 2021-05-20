package edu.kit.informatik.model.constants;

/**
 * This class represents the properties that a cell can have.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class CellProperties {

    /**
     * This regex represents the wet state of a forest field.
     */
    public static final String FOREST_WET = "w";

    /**
     * This regex represents the dry state of a forest field.
     */
    public static final String FOREST_DRY = "d";

    /**
     * This regex represents the lightly burning state of a forest field.
     */
    public static final String FOREST_LIGHTLY_BURNING = "+";

    /**
     * This regex represents the strongly burning state of a forest field.
     */
    public static final String FOREST_STRONGLY_BURNING = "*";

    /**
     * This enum represents the type which a cell will be set to.
     * 
     * @author Illia Pyvovar
     * @version 1.0
     */
    public enum CellType {
        /**
         * This element sets the type of a cell to be a forest cell.
         */
        FOREST,
        /**
         * This element sets the type of a cell to be a lake cell.
         */
        LAKE,
        /**
         * This element sets the type of a cell to be a fire station.
         */
        FIRE_STATION
    }

    // Utility classes have a private constructor
    private CellProperties() {
    }
}
