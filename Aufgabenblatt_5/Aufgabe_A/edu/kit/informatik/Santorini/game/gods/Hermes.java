package edu.kit.informatik.Santorini.game.gods;

/**
 * 
 * @author illya
 *
 */
public class Hermes extends GodCards {
    /**
     * 
     * @param name
     */
    public Hermes(String name) {
        super(name);
    }

    /**
     * 
     */
    @Override
    public MagicPower getMagicPower() {
        MagicPower magic = new MagicPower();
        magic.setTeleport(true);
        return magic;
    }
}
