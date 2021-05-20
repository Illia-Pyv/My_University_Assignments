package edu.kit.informatik.Santorini.game.gamepieces;

import edu.kit.informatik.Santorini.game.board.Coordinates;

/**
 * 
 * @author illya
 *
 */
public class Figurine extends GamePieces {
    private Coordinates figurineCoordinates;
    
    /**
     * 
     * @param name
     */
    public Figurine(String name) {
        setName(name);
    }
    
    /**
     * 
     * @return Returns the command
     */
    public Coordinates getCoordinates() {
        return this.figurineCoordinates;
    }
    
    /**
     * 
     * @param row
     * @param column
     */
    public void setCoordinates(int row, int column) {
        if (figurineCoordinates == null) {
            this.figurineCoordinates = new Coordinates(row, column);
        } else {
            this.figurineCoordinates.set(row, column);
        }
    }
}
