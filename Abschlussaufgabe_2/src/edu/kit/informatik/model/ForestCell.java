package edu.kit.informatik.model;

import edu.kit.informatik.model.constants.CellProperties;
import edu.kit.informatik.model.constants.CellProperties.CellType;

/**
 * This class represents a forest cell on the board.
 * 
 * <p>
 * This cell stores its state and can burn either stronger or weaker, but can
 * also be dry or wet.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class ForestCell extends Cell {

    private String forestState = "";
    private boolean alreadyAddedFire = false;

    /**
     * This is the constructor of this class which sets the state of the forest
     * which it is in. For example is the forest burning or is it dry or even wet.
     * 
     * @param row    the row at which this cell will be set in
     * @param column the column at which this cell will be set in
     * @param state  the state at which this cell will be initialized, which can be
     *               either one of the burning states or one of the non-burning
     *               states
     */
    public ForestCell(int row, int column, String state) {
        super(row, column, CellType.FOREST);
        setState(state);
    }

    /**
     * This method resets the added fire attribute to false to prepare for the next
     * "fire-to-roll" call.
     */
    public void resetAlreadyAddedFire() {
        alreadyAddedFire = false;
    }

    /**
     * This method gets the already added fire attribute to check if fire was
     * already added to this forest in this turn.
     * 
     * @return Returns true if fire was already added to this forest and false if it
     *         was not
     */
    public boolean getAlreadyAddedFire() {
        return alreadyAddedFire;
    }

    /**
     * This method adds fire to this forest cell, meaning that the forest will flame
     * up and start burning stronger using this method.
     */
    public void addFire() {
        if (alreadyAddedFire) {
            return;
        }
        String state = this.forestState;
        switch (state) {
            case CellProperties.FOREST_WET: {
                setState(CellProperties.FOREST_DRY);
                alreadyAddedFire = true;
                break;
            }
            case CellProperties.FOREST_DRY: {
                setState(CellProperties.FOREST_LIGHTLY_BURNING);
                alreadyAddedFire = true;
                break;
            }
            case CellProperties.FOREST_LIGHTLY_BURNING: {
                setState(CellProperties.FOREST_STRONGLY_BURNING);
                alreadyAddedFire = true;
                break;
            }
            default: {
                setState(CellProperties.FOREST_STRONGLY_BURNING);
            }
        }
    }

    /**
     * This method adds water to this cell, meaning that fire can be extinguished
     * using this method.
     */
    public void addWater() {
        String state = this.forestState;
        switch (state) {
            case CellProperties.FOREST_STRONGLY_BURNING: {
                setState(CellProperties.FOREST_LIGHTLY_BURNING);
                break;
            }
            default: {
                setState(CellProperties.FOREST_WET);
            }
        }
    }

    /**
     * This method sets the state of the forest to four possible states.
     * 
     * @param state represents the state of the forest which can be either wet, dry,
     *              lightly burning or strongly burning
     */
    private void setState(String state) {
        this.forestState = state;
        update();
    }

    /**
     * This method gets the state in which the forest is in. For example if the
     * forest is burning or not.
     * 
     * @return Returns the forest state as a string
     */
    public String getForestState() {
        return this.forestState;
    }

    private void update() {
        if (this.forestState.equals(CellProperties.FOREST_LIGHTLY_BURNING)) {
            setCanPassThrough(true);
            setCanMoveTo(false);
        } else if (this.forestState.equals(CellProperties.FOREST_STRONGLY_BURNING)) {
            setCanPassThrough(false);
            setCanMoveTo(false);
        } else {
            setCanPassThrough(true);
            setCanMoveTo(true);
        }
    }

}
