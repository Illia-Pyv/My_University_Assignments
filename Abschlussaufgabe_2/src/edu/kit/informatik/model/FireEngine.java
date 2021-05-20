package edu.kit.informatik.model;

import java.util.ArrayList;

import edu.kit.informatik.model.constants.CellProperties;
import edu.kit.informatik.model.constants.CellProperties.CellType;
import edu.kit.informatik.model.constants.Errors;

/**
 * This class represents a fire engine which holds its current position as well
 * as its identifier and can
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class FireEngine {

    private static final int MAXIMAL_ACTION_POINTS = 3;
    private static final int MAXIMAL_WATER_LEVEL = 3;
    private static final int MOVE_RANGE = 2;
    private ArrayList<Cell> alreadyExtinguishedCells = new ArrayList<>();
    private String identifier;
    private Cell currentPosition;
    private boolean lastActionMove = true;
    private int actionPoints;
    private int waterLevel;

    /**
     * This method is the constructor of this class. It sets the coordinates of the
     * new fire engine when it is initialized as well as its identifier.
     * 
     * @param currentPosition the cell object on which this fire engine should be
     *                        initialized on
     * @param identifier      the identifier which the new fire engine will be set
     *                        with
     */
    public FireEngine(Cell currentPosition, String identifier) {
        this.identifier = identifier;
        this.currentPosition = currentPosition;
        this.actionPoints = MAXIMAL_ACTION_POINTS;
        this.waterLevel = MAXIMAL_WATER_LEVEL;
    }

    /**
     * This method gets the identifier of this fire engine.
     * 
     * @return Returns the identifier of this fire engine as a string
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * This method sets the current cell on which this fire engine currently stands
     * on
     * 
     * @param currentPosition is the cell onto which this fire engine should be set
     *                        on
     */
    public void setCurrentPosition(Cell currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * This method gets the current position of this fire engine.
     * 
     * @return Returns the cell object on which this fire engine stands on currently
     */
    public Cell getCurrentPosition() {
        return currentPosition;
    }

    /**
     * This method returns the remaining action points of this fire engine.
     * 
     * @return Returns the action points as an integer
     */
    public int getActionPoints() {
        return this.actionPoints;
    }

    /**
     * This method gets the list of all the cells that this fire engine already
     * extinguished.
     * 
     * @return Returns an array list of the object Cell of all extinguished cells
     */
    public ArrayList<Cell> getAlreadyExtinguishedCells() {
        return this.alreadyExtinguishedCells;
    }

    /**
     * This method gets the current water level of this fire engine.
     * 
     * @return Returns the current water level as an integer
     */
    public int getWaterLevel() {
        return this.waterLevel;
    }

    /**
     * This method initializes the action points and clears the list of already
     * extinguished cells by this fire engine for a new round.
     */
    public void newRound() {
        this.actionPoints = MAXIMAL_ACTION_POINTS;
        this.alreadyExtinguishedCells.clear();
        this.lastActionMove = true;
    }

    /**
     * This method refills this fire engine, meaning it sets the water level of this
     * fire engine back to its maximum.
     * 
     * @param canRefillAtStation a boolean that expresses if the fire engine can
     *                           refill at a fire station
     * @return Returns the amount of action points as an integer that this fire
     *         engine has left after refilling
     * @throws LogicException the exception that is thrown if an error occurs while
     *                        this fire engine tries to refill
     */
    public int refill(boolean canRefillAtStation) throws LogicException {
        if (canRefillAtStation) {
            fillUpFireEngine();
            return actionPoints;
        }
        boolean hasNearbyWatersource = false;
        for (Cell neighbor : getCurrentPosition().getNeighbourList()) {
            if (neighbor.getType().equals(CellType.LAKE)) {
                hasNearbyWatersource = true;
                break;
            }
        }

        if (!hasNearbyWatersource) {
            throw new LogicException(Errors.NO_WATER_SOURCE);
        }
        fillUpFireEngine();
        return actionPoints;
    }

    private void fillUpFireEngine() throws LogicException {
        if (hasActionPoints() && this.waterLevel != MAXIMAL_WATER_LEVEL) {
            this.waterLevel = MAXIMAL_WATER_LEVEL;
            this.actionPoints -= 1;
        } else {
            throw new LogicException(Errors.MAXIMAL_WATER_LEVEL);
        }
    }

    /**
     * This method calculates how the fire engine has to move to get to the wanted
     * cell and moves it to the cell if there is a path.
     * 
     * @param moveTo the cell which the fire engine should move to
     * @throws LogicException an exception which is thrown if an error occurs while
     *                        trying to move
     */
    public void move(Cell moveTo) throws LogicException {
        if (getCurrentPosition().equals(moveTo)) {
            throw new LogicException(Errors.STAYING_ON_CELL);
        } else if (!lastActionMove) {
            throw new LogicException(Errors.PERFORMED_OTHER_ACTION);
        }

        ArrayList<Cell> path = breadthFirstSearch(getCurrentPosition(), moveTo);
        if (path == null) {
            throw new LogicException(Errors.PATH_BLOCKED);
        } else if (path.size() <= MOVE_RANGE && hasActionPoints()) {
            for (Cell next : path) {
                getCurrentPosition().remove(this);
                setCurrentPosition(next);
                next.addToCell(this);
            }
            actionPoints -= 1;
        } else {
            throw new LogicException(Errors.CELL_OUTSIDE_OF_MOVE_RANGE);
        }
    }

    /**
     * This method extinguishes a forest cell that is adjacent to this fire engine.
     * 
     * @param extinguishCell the cell to be extinguished
     * @return Returns the state of the forest cell that the fire engine
     *         extinguished
     * @throws LogicException the exception that is thrown if an error occurs while
     *                        the fire engine tries to extinguish the cell
     */
    public String extinguish(Cell extinguishCell) throws LogicException {
        String stateBeforeExtinguishing = "";
        // the cases in which this command will not be processed and throws an exception
        // instead
        if (!getCurrentPosition().getNeighbourList().contains(extinguishCell)) {
            throw new LogicException(Errors.NOT_A_NEIGHBOR);
        } else if (!extinguishCell.getType().equals(CellType.FOREST)) {
            throw new LogicException(Errors.NOT_A_FOREST);
        } else if (alreadyExtinguishedCells.contains(extinguishCell)) {
            throw new LogicException(Errors.ALREADY_EXTINGUISHED);
        }
        ForestCell cell = (ForestCell) extinguishCell;
        if (cell.getForestState().equals(CellProperties.FOREST_WET)) {
            throw new LogicException(Errors.WET_FOREST);
        } else if (this.waterLevel != 0 && hasActionPoints()) {
            this.waterLevel -= 1;
            this.actionPoints -= 1;
            stateBeforeExtinguishing = cell.getForestState();
            cell.addWater();
            alreadyExtinguishedCells.add(cell);
            lastActionMove = false;
        } else {
            throw new LogicException(Errors.NO_WATER);
        }
        return stateBeforeExtinguishing;
    }

    private boolean hasActionPoints() throws LogicException {
        boolean hasActionPoints = false;
        if (actionPoints != 0) {
            hasActionPoints = true;
        } else {
            throw new LogicException(Errors.NOT_ENOUGH_ACTION_POINTS);
        }
        return hasActionPoints;
    }

    // this method calculates the shortest path that the fire engine can take
    private ArrayList<Cell> breadthFirstSearch(Cell start, Cell end) {
        Cell current;
        ArrayList<Cell> seen = new ArrayList<>();
        ArrayList<Cell> queue = new ArrayList<>();
        ArrayList<Pair<Cell, Cell>> path = new ArrayList<>();
        queue.add(start);
        seen.add(start);
        while (!queue.isEmpty()) {
            current = dequeue(queue);
            if (current.equals(end)) {
                return traceBackPath(start, end, path);
            }
            for (Cell successor : current.getNeighbourList()) {
                if (!seen.contains(successor) && successor.canPassThrough()) {
                    queue.add(successor);
                    seen.add(successor);
                    path.add(new Pair<Cell, Cell>(current, successor));
                }
            }
        }
        return null;
    }

    private Cell dequeue(ArrayList<Cell> queue) {
        Cell result = queue.get(0);
        queue.remove(0);
        return result;
    }

    private ArrayList<Cell> traceBackPath(Cell start, Cell end, ArrayList<Pair<Cell, Cell>> path) {
        ArrayList<Cell> result = new ArrayList<>();
        result.add(end);
        Cell current = end;
        // trace back the path from the end and add it to the result
        while (!current.equals(start)) {
            for (Pair<Cell, Cell> pair : path) {
                if (pair.getSecond().equals(current)) {
                    result.add(pair.getFirst());
                    current = pair.getFirst();
                    break;
                }
            }
        }

        // reverse the resulting path
        int loopVariable = 0;
        int loopReverse;
        while (loopVariable < result.size() - 1 - loopVariable) {
            loopReverse = result.size() - 1 - loopVariable;
            Cell temporary = result.get(loopVariable);
            result.set(loopVariable, result.get(loopReverse));
            result.set(loopReverse, temporary);
            loopVariable++;
        }
        result.remove(start);
        return result;
    }
}
