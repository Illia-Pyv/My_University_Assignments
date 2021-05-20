package edu.kit.informatik.model;

import java.util.ArrayList;

import edu.kit.informatik.model.constants.CellProperties.CellType;

/**
 * This class represents the fire station cell on the board. It stores the which
 * fire engines were bought from this station, keeps track of the amount of fire
 * engines bought and gives them a name.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class FireStationCell extends Cell {

    private ArrayList<FireEngine> fireEngineList = new ArrayList<>();
    private int fireEngineCount = 0;

    /**
     * This is the constructor of this class. It creates a fire station cell and
     * registers a first fire engine.
     * 
     * @param row        the row where this cell will be set in
     * @param column     the column where this cell will be set in
     * @param identifier the name of this fire station cell
     */
    public FireStationCell(int row, int column, String identifier) {
        super(row, column, CellType.FIRE_STATION);
        setIdentifier(identifier);
        setCanMoveTo(false);
        setCanPassThrough(false);
        createNewFireEngine(this);
    }

    /**
     * This method gets the list of the fire engines that are registered in this
     * fire station.
     * 
     * @return Returns the list of fire engines as an array list
     */
    public ArrayList<FireEngine> getFireEngineList() {
        return this.fireEngineList;
    }

    /**
     * This method creates a new fire engine for this specific fire station and
     * registers it in the fire engine list.
     * 
     * @param cell the Cell object on which the new fire engine should be
     *             initialized on
     */
    public void createNewFireEngine(Cell cell) {
        String fireEngineName = getIdentifier() + fireEngineCount;
        fireEngineList.add(new FireEngine(cell, fireEngineName));
        fireEngineCount++;
    }

}
