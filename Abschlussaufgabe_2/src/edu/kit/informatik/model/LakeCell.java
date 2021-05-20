package edu.kit.informatik.model;

import edu.kit.informatik.model.constants.CellProperties.CellType;

/**
 * This class represents the lake cell on the board.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class LakeCell extends Cell {

    /**
     * This is the constructor of this class and it sets the occupied value of this
     * cell to always true as the player cannot move to it.
     * 
     * @param row        the row at which this cell should be initialized at
     * @param column     the column at which this cell should be initialized at
     * @param identifier the identifier which this cell should be set to
     */
    public LakeCell(int row, int column, String identifier) {
        super(row, column, CellType.LAKE);
        setIdentifier(identifier);
        setCanMoveTo(false);
        setCanPassThrough(false);
    }

}
