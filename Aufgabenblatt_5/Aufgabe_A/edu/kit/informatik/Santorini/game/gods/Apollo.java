package edu.kit.informatik.Santorini.game.gods;

/**
 * 
 * @author illya
 *
 */
public class Apollo extends GodCards {

    /**
     * 
     * @param name
     */
    public Apollo(String name) {
        super(name);
    }

    /**
     * @return Returns the command
     */
    @Override
    public MagicPower getMagicPower() {
        MagicPower magic = new MagicPower();
        magic.setMoveToOccupiedCell(true);
        return magic;
    }

}
