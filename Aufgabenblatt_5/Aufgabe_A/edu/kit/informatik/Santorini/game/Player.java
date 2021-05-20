package edu.kit.informatik.Santorini.game;

import java.util.HashMap;

import edu.kit.informatik.Santorini.game.board.Coordinates;
import edu.kit.informatik.Santorini.game.gamepieces.Figurine;
import edu.kit.informatik.Santorini.game.gamepieces.GamePieces;
import edu.kit.informatik.Santorini.game.gods.IGivePower;
import edu.kit.informatik.Santorini.game.gods.MagicPower;

/**
 * 
 * @author illya
 *
 */
public class Player {
    
    private Board board;
    private String name = "";
    private HashMap<String, Figurine> mapFigurine = new HashMap<String, Figurine>(); 
    private IGivePower godCard = null;
    private MagicPower magic = null;
    
    /**
     * 
     * @param board
     * @param name
     */
    public Player(Board board, String name) {
        this.board = board;
        this.name = name;
    }
    
    /**
     * 
     * @param godCard
     */
    public void setGodCard(IGivePower godCard) {
        if (godCard != null) {
            magic = godCard.getMagicPower();
        }
        this.godCard = godCard;
    }
    
    /**
     * 
     * @param magic
     */
    public void setMagic(MagicPower magic) {
        this.magic = magic;
    }
    
    /**
     * 
     * @param name
     * @param row
     * @param column
     * @return Returns the command
     */
    public boolean move(String name, int row, int column) throws IllegalArgumentException {
        boolean hasWon = false;
        Figurine figurine = null;  
        figurine = mapFigurine.get(name);
        // gets the coordinates of the cell where the player wants to move to
        Coordinates cellCoordinates = board.getCoordinatesFromCell(row, column);
        if (board.setFigurine(cellCoordinates, figurine)) {
            board.removePieceAt(figurine.getCoordinates(), figurine);
            figurine.getCoordinates().set(row, column);
            
            if (board.maximumHeight(cellCoordinates)) {
                hasWon = true;
            }
        }
        return hasWon;
    }
    
    /**
     * 
     * @param name
     * @param buildingPiece
     * @param row
     * @param column
     */
    public void build(String name, GamePieces buildingPiece, int row, int column) {
        Figurine figurine = mapFigurine.get(name);
        // gets the coordinates of the cell where the player wants to move to
        Coordinates cellCoordinates = board.getCoordinatesFromCell(row, column);
        
        if (board.maximumHeight(cellCoordinates) && !buildingPiece.equals(Bag.domes.get(0))) {
            // TODO exception: has to be dome
        } else if (!board.maximumHeight(cellCoordinates) && buildingPiece.equals(Bag.domes.get(0))) {
            // TODO exception: has to be cuboid
        } else {
            board.setBuildingPiece(figurine.getCoordinates(), cellCoordinates, buildingPiece);
        }
    }
    
    /**
     * 
     * @param name
     * @param row
     * @param column
     */
    public void addFigurine(String name, int row, int column) {
        mapFigurine.put(name, new Figurine(name));
        setOnCell(row, column, mapFigurine.get(name));
    }

    public boolean canGetFigurine(String figureName) {
        boolean canReturn = false;
        Figurine nextFigurine = null;
        for (String name : mapFigurine.keySet()) {
            nextFigurine = mapFigurine.get(name);
            if (nextFigurine.getName().equals(figureName)) {
                return true;
            } else {
                canReturn = false;
            }
        }
        return canReturn;
    }
    
    /**
     * 
     * @return Returns the command
     */
    public String getName() {
        return this.name;
    }
    
    /*public Coordinates getPosition(String name) {
        return this.mapFigurine.get(name).getCoordinates();
    }*/
    
    private void setOnCell(int row, int column, Figurine figure) {
        Coordinates cellCoordinates =  board.getCoordinatesFromCell(row, column);
        board.setFigurine(cellCoordinates, figure);
        figure.setCoordinates(row, column);
    }
}
