package edu.kit.informatik.Santorini.game.gods;
/**
 * 
 * @author illya
 *
 */
public class MagicPower {
    private boolean moveToOccupiedCell = false;
    private boolean moveTwoTimes = false;
    private boolean opponentNotAllowedUp = false;
    private boolean buildDomeAnywhere = false;
    private boolean buildTwoTimes = false;
    private boolean teleport = false;
    
    
//////////////////////////////////////    GETTER    /////////////////////////////////////////////
    
    /**
     * 
     * @return Returns the command
     */
    public boolean isMoveToOccupiedCell() {
        return moveToOccupiedCell;
    }
    
    /**
     * 
     * @return Returns the command
     */
    public boolean isMoveTwoTimes() {
        return moveTwoTimes;
    }
    
    /**
     * 
     * @return Returns the command
     */
    public boolean isOpponentNotAllowedUp() {
        return opponentNotAllowedUp;
    }
    
    /**
     * 
     * @return Returns the command
     */
    public boolean isBuildDomeAnywhere() {
        return buildDomeAnywhere;
    }
    
    /**
     * 
     * @return Returns the command
     */
    public boolean isBuildTwoTimes() {
        return buildTwoTimes;
    }
    
    /**
     * 
     * @return Returns the command
     */
    public boolean isTeleport() {
        return teleport;
    }
    
    //////////////////////////////////////    SETTER    /////////////////////////////////////////////
    
    /**
     * 
     * @param isMoved
     */
    public void setMoveToOccupiedCell(boolean isMoved) {
        this.moveToOccupiedCell = isMoved;
    }
    
    /**
     * 
     * @param isMoved
     */
    public void setMoveTwoTimes(boolean isMoved) {
        this.moveTwoTimes = isMoved;
    }
    
    /**
     * 
     * @param notAllowed
     */
    public void setOpponentNotAllowedUp(boolean notAllowed) {
        this.opponentNotAllowedUp = notAllowed;
    }
    
    /**
     * 
     * @param buildAnywhere
     */
    public void setBuildDomeAnywhere(boolean buildAnywhere) {
        this.buildDomeAnywhere = buildAnywhere;
    }
    
    /**
     * 
     * @param buildTwoTimes
     */
    public void setBuildTwoTimes(boolean buildTwoTimes) {
        this.buildDomeAnywhere = buildTwoTimes;
    }
    
    /**
     * 
     * @param teleport
     */
    public void setTeleport(boolean teleport) {
        this.teleport = teleport;
    }
}
