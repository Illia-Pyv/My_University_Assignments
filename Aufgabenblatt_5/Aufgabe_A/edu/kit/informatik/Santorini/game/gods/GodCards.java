package edu.kit.informatik.Santorini.game.gods;

/**
 * 
 * @author illya
 *
 */
public abstract class GodCards implements IGivePower {
    private String name = "";
    
    /**
     * 
     * @param name
     */
    public GodCards(String name) {
        this.name = name;
    }
    
    /**
     * 
     */
    @Override
    public String toString() {
        return this.name;
    }
}
