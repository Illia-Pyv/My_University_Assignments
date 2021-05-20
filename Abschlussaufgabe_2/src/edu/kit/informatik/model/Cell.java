package edu.kit.informatik.model;

import java.util.ArrayList;

import edu.kit.informatik.model.constants.CellProperties.CellType;

/**
 * This class represents a cell on the board.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Cell {

    private ArrayList<FireEngine> objectList = new ArrayList<>();
    private ArrayList<Cell> allNeighboursList = new ArrayList<>();
    private CellType type;
    private String identifier;
    private boolean canMoveTo;
    private boolean canPassThrough;
    private Coordinates coordinates;

    /**
     * This is the constructor of this class and it sets the coordinates of this
     * cell as well as the cell type.
     * 
     * @param row    the row on which this cell is located
     * @param column the column on which this cell is located
     * @param type   the cell type which will be initialized, it can be whether a
     *               fire station cell, a forest cell or a lake cell
     */
    public Cell(int row, int column, CellType type) {
        this.coordinates = new Coordinates(row, column);
        this.type = type;
    }

    /**
     * This method gets the identifier of this cell.
     * 
     * @return Returns the identifier of this cell as a string
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * This method sets the identifier of this cell to the handed over parameter.
     * 
     * @param identifier the parameter which the identifier of this cell should be
     *                   set to
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * This method gets the type of this cell.
     * 
     * @return Returns the cell type as a cell type object
     */
    public CellType getType() {
        return this.type;
    }

    /**
     * This method sets the value of occupied to whatever was handed over.
     * 
     * @param canMoveTo the value that is to be set for this cell if a fire engine
     *                  can move to it
     */
    public void setCanMoveTo(boolean canMoveTo) {
        this.canMoveTo = canMoveTo;
    }

    /**
     * This method sets the value of occupied to whatever was handed over.
     * 
     * @param canPassThrough the value that is to be set for this cell if a fire
     *                       engine can pass through a cell
     */
    public void setCanPassThrough(boolean canPassThrough) {
        this.canPassThrough = canPassThrough;
    }

    /**
     * This method checks if this cell can be moved to or not.
     * 
     * @return Returns true if a fire engine can move to this cell or false if it
     *         cannot
     */
    public boolean canMoveTo() {
        return this.canMoveTo;
    }

    /**
     * This method checks if this cell is occupied or not.
     * 
     * @return Returns true if this cell is occupied and false if it is not
     */
    public boolean canPassThrough() {
        return this.canPassThrough;
    }

    /**
     * This method adds a new object to stand on this cell which can be any object
     * type that this cell was initialized with.
     * 
     * @param object the object which should be added to this cell to stand on
     */
    public void addToCell(FireEngine object) {
        this.objectList.add(object);
    }

    /**
     * This method removes an object from this cell which can be any object that
     * this cell was initialized with.
     * 
     * @param object the object which should be removed from this cell
     */
    public void remove(FireEngine object) {
        if (this.objectList.contains(object)) {
            this.objectList.remove(object);
        }
    }

    /**
     * This method gets the list of objects that are standing on this cell.
     * 
     * @return Returns an array list of fire engines that stand on this cell
     */
    public ArrayList<FireEngine> getObjectList() {
        return this.objectList;
    }

    /**
     * This method gets element of all the objects that stand on this cell.
     * 
     * @return Returns the object of the last element of the list of objects that
     *         stand on this cell
     */
    public FireEngine getLast() {
        int i = 0;
        FireEngine piece = null;
        while (i < objectList.size()) {
            piece = objectList.get(i);
            i++;
        }
        return piece;
    }

    /**
     * This method gets the coordinates of this cell.
     * 
     * @return Returns the coordinates as a Coordinates object which contains the
     *         row and column of this cell
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * This method checks if any objects are standing on this cell or if it is
     * empty.
     * 
     * @return Returns true if this cell is empty and false if it is not
     */
    public boolean isEmpty() {
        if (objectList.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * This method returns the list of all neighbors of this cell.
     * 
     * @return Returns an ArrayList object which contains cell objects which are
     *         adjacent to this cell
     */
    public ArrayList<Cell> getNeighbourList() {
        return this.allNeighboursList;
    }

    /**
     * This method gets the size of the list of neighbors of this cell.
     * 
     * @return Returns the size of the list of neighbors as an integer
     */
    public int getNeighbourListSize() {
        return allNeighboursList.size();
    }

    /**
     * This method adds a new neighbor to this cells neighbor list.
     * 
     * @param cell the cell object that should be added to the neighbor list
     */
    public void addNeighbours(Cell cell) {
        this.allNeighboursList.add(cell);
    }

    /**
     * This method checks whether the neighbors for this cell were already
     * calculated or not.
     * 
     * @return Returns true if the list of neighbors for this cell is empty and
     *         false if it is not empty
     */
    public boolean isNeighborListEmpty() {
        if (this.allNeighboursList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if a cell has a neighbor cell with the coordinates
     * received from the parameters.
     * 
     * @param neighbourCoordinates the coordinates of a potential neighbor cell
     * @return Returns true if there is a neighbor cell of this cell with the given
     *         coordinates and false if such a neighbor is not in the list.
     */
    public boolean hasNeighbourCell(Coordinates neighbourCoordinates) {
        for (int i = 0; i < getNeighbourListSize(); i++) {
            if (getNeighbour(i).coordinates.areSame(neighbourCoordinates)) {
                return true;
            }
        }
        return false;
    }

    private Cell getNeighbour(int index) {
        return allNeighboursList.get(index);
    }
}
