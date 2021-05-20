package edu.kit.informatik.Santorini.game.gods;

/**
 * 
 * @author illya
 *
 */
public class Atlas extends GodCards {
    /**
     * 
     * @param name
     */
    public Atlas(String name) {
        super(name);
    }

    /**
     * @return Returns the command
     */
    @Override
    public MagicPower getMagicPower() {
        MagicPower magic = new MagicPower();
        magic.setBuildDomeAnywhere(true);
        return magic;
    }
}
