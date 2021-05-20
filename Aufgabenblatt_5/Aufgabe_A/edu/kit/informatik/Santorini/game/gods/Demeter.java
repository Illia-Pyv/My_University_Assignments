package edu.kit.informatik.Santorini.game.gods;

/**
 * 
 * @author illya
 *
 */
public class Demeter extends GodCards {
    /**
     * 
     * @param name
     */
    public Demeter(String name) {
        super(name);
    }

    /**
     * 
     */
    @Override
    public MagicPower getMagicPower() {
        MagicPower magic = new MagicPower();
        magic.setBuildTwoTimes(true);
        return magic;
    }
}
