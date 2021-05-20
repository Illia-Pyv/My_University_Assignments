package edu.kit.informatik.Santorini.game.gamepieces;

/**
 * 
 * @author illya
 *
 */
public class GamePieces {
    
    private String name;
    
    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @return Returns the command
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * 
     */
    @Override
    public String toString() {
        return this.name;
    }
}
