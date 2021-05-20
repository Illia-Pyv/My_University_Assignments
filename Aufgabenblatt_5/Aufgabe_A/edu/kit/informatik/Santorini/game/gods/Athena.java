package edu.kit.informatik.Santorini.game.gods;

/**
 * 
 * @author illya
 *
 */
public class Athena extends GodCards {
    public Athena(String name) {
        super(name);
    }

    /**
     * @return Returns the command
     */
    @Override
    public MagicPower getMagicPower() {
        MagicPower magic = new MagicPower();
        magic.setOpponentNotAllowedUp(true);
        return magic;
    }
}
