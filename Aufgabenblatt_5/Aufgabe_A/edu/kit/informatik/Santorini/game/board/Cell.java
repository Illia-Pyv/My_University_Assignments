package edu.kit.informatik.Santorini.game.board;

import java.util.ArrayList;

import edu.kit.informatik.Santorini.game.gamepieces.GamePieces;

/**
 * 
 * @author illya
 *
 */
public class Cell {

    private ArrayList<GamePieces> gamePieceList = new ArrayList<GamePieces>();
    private ArrayList<Cell> neighboursList = new ArrayList<Cell>();
    private boolean isOccupied;
    private Coordinates coordinates;
    
    /**
     * 
     * @param row
     * @param column
     */
    public Cell(int row, int column) {
        Coordinates coordinates = new Coordinates(row, column);
        this.coordinates = coordinates;
        this.isOccupied = false;
    }

    /**
     * 
     * @param index
     * @return Returns the command
     */
    public Cell getNeighbour(int index) {
        return neighboursList.get(index);
    }

    /**
     * 
     * @return Returns the command
     */
    public int getNeighbourListSize() {
        return neighboursList.size();
    }

    /**
     * 
     * @param cell
     */
    public void addNeighbours(Cell cell) {
        this.neighboursList.add(cell);
    }

    /**
     * 
     * @param gamePiece
     */
    public void addToCell(GamePieces gamePiece) {
        this.gamePieceList.add(gamePiece);
    }

    /**
     * 
     * @param gamePiece
     */
    public void remove(GamePieces gamePiece) {
        if (this.gamePieceList.contains(gamePiece)) {
            this.gamePieceList.remove(gamePiece);
        }
    }

    /**
     * 
     * @return Returns the command
     */
    public int getSize() {
        return gamePieceList.size();
    }

    /**
     * 
     * @param index
     * @return Returns the command
     */
    public GamePieces gamePieceGet(int index) {
        return gamePieceList.get(index);
    }
    
    /**
     * 
     * @return Returns the command
     */
    public GamePieces getLast() {
        int i = 0;
        GamePieces piece = null;
        while (i < gamePieceList.size()) {
            piece =  gamePieceList.get(i);
            i++;
        }
        return piece;
    }

    /**
     * 
     * @return Returns the command
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * 
     * @return Returns the command
     */
    public boolean isEmpty() {
        if (gamePieceList.isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param occupied
     */
    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }
    
    /**
     * 
     * @return Returns the command
     */
    public boolean isOccupied() {
        return this.isOccupied;
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
            if (getNeighbour(i).coordinates.areSame(neighbourCoordinates)) { //Problem
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param neighbor
     * @return Returns the command
     */
    public boolean neighborHasTower(Cell neighbor) {
        boolean hasTower = false;
        int neighborSize = neighbor.getSize();
        int thisCellSize = this.getSize() - 1;
        if ((neighborSize - thisCellSize) > 1) {
            hasTower = true;
        }
        return hasTower;
    }
}
