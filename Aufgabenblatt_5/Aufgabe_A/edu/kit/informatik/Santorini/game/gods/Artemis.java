package edu.kit.informatik.Santorini.game.gods;

/**
 * 
 * @author illya
 *
 */
public class Artemis extends GodCards {
    
    /**
     * 
     * @param name
     * 
     */
    public Artemis(String name) {
        super(name);
    }

    /**
     * @return Returns the command
     */
    @Override
    public MagicPower getMagicPower() {
        MagicPower magic = new MagicPower();
        magic.setMoveTwoTimes(true);
        return magic;
    }
}
