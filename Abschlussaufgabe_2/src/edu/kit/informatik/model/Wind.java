package edu.kit.informatik.model;

import edu.kit.informatik.model.constants.CellProperties;
import edu.kit.informatik.model.constants.CellProperties.CellType;

/**
 * This class represents the wind object which can blow into different
 * directions like north or south or all directions at once.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Wind {

    private Board board;

    /**
     * This is the constructor of this class. It initializes the board on which the
     * wind will determine if a forest is burning or not.
     * 
     * @param board the board which is needed for further calculations of the wind
     *              direction and the fire spread
     */
    public Wind(Board board) {
        this.board = board;
    }

    /**
     * This method calculates which forest cells are going to be burning next when
     * the wind blows to the north.
     */
    public void north() {
        spread(-1, 0);
    }

    /**
     * This method calculates which forest cells are going to be burning next when
     * the wind blows to the south.
     */
    public void south() {
        spread(1, 0);
    }

    /**
     * This method calculates which forest cells are going to be burning next when
     * the wind blows to the east.
     */
    public void east() {
        spread(0, 1);
    }

    /**
     * This method calculates which forest cells are going to be burning next when
     * the wind blows to the west.
     */
    public void west() {
        spread(0, -1);
    }

    private void spread(int addToRow, int addToColumn) {
        ForestCell currentForestCell;
        boolean isNotInBoundsOfTheBoard = false;
        int newRow;
        int newColumn;
        for (ForestCell burningCell : board.getAllFires()) {
            newRow = burningCell.getCoordinates().getRow() + addToRow;
            newColumn = burningCell.getCoordinates().getColumn() + addToColumn;
            if (addToRow < 0) {
                isNotInBoundsOfTheBoard = newRow < 0;
            } else if (addToRow > 0) {
                isNotInBoundsOfTheBoard = newRow >= board.getAmountOfRows();
            } else if (addToColumn < 0) {
                isNotInBoundsOfTheBoard = newColumn < 0;
            } else if (addToColumn > 0) {
                isNotInBoundsOfTheBoard = newColumn >= board.getAmountOfColumns();
            }
            // checks if fire can be added to the neighbor cell
            if (burningCell.getAlreadyAddedFire() || setToStronglyBurning(burningCell) || isNotInBoundsOfTheBoard
                    || !board.getCellFromCoordinates(newRow, newColumn).getType().equals(CellType.FOREST)) {
                continue;
            } else {
                currentForestCell = (ForestCell) board.getCellFromCoordinates(newRow, newColumn);
                currentForestCell.addFire();
            }
        }
        board.updateFires();
    }

    /**
     * This method calculates which forest cells are going to be burning next when
     * the wind blows into all four direction.
     */
    public void allDirections() {
        ForestCell currentForestCell;
        for (ForestCell burningCell : board.getAllFires()) {
            if (burningCell.getAlreadyAddedFire() || setToStronglyBurning(burningCell)) {
                continue;
            }
            // adds fire to all neighbor cells
            for (Cell cell : burningCell.getNeighbourList()) {
                if (!cell.getType().equals(CellType.FOREST)) {
                    continue;
                } else {
                    currentForestCell = (ForestCell) cell;
                    currentForestCell.addFire();
                }
            }
        }
        board.updateFires();
    }

    // adds fire to a lightly burning forest cell
    private boolean setToStronglyBurning(ForestCell forestCell) {
        boolean setToStronglyBurning = false;
        if (forestCell.getForestState().equals(CellProperties.FOREST_LIGHTLY_BURNING)) {
            forestCell.addFire();
            setToStronglyBurning = true;
        }
        return setToStronglyBurning;
    }

}
