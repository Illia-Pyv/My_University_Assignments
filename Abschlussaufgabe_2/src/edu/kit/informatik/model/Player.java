package edu.kit.informatik.model;

import edu.kit.informatik.model.constants.Errors;

/**
 * This class represents a player of this game. The player object stores its own
 * name, its associated fire station and reputation points.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Player {

    private static final int COST_OF_FIRE_ENGINE = 5;
    private String name;
    private int reputationPoints = 0;
    private FireStationCell station;

    /**
     * This is the constructor of this class. It sets the name and the station for
     * this player.
     * 
     * @param station the station from which the name for the player is gotten and
     *                which is assigned to the player
     */
    public Player(FireStationCell station) {
        this.name = station.getIdentifier();
        this.station = station;
    }

    /**
     * This method gets the name of this player, which is the same as the name of
     * his station.
     * 
     * @return Returns the name of this player as a string
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method adds one reputation point to the amount of reputation points that
     * the player currently has.
     */
    public void addReputation() {
        this.reputationPoints += 1;
    }

    /**
     * This method gets the amount of reputation points that the player currently
     * has.
     * 
     * @return Returns the amount of reputation points as an integer
     */
    public int getReputation() {
        return this.reputationPoints;
    }

    private boolean hasEnoughReputationPoints(int amount) throws LogicException {
        boolean hasReputationPoints = false;
        if (this.reputationPoints >= amount) {
            hasReputationPoints = true;
        } else {
            throw new LogicException(Errors.NOT_ENOUGH_REPUTATION);
        }
        return hasReputationPoints;
    }

    private void removeReputation(int amount) throws LogicException {
        if (hasEnoughReputationPoints(amount)) {
            this.reputationPoints -= amount;
        }
    }

    /**
     * This method represents the process of buying a new fire engine.
     * 
     * <p>
     * It removes five reputation points if the player has enough and sets the new
     * fire engine on an adjacent cell.
     * 
     * @param cell the Cell object on which the new fire engine should be
     *             initialized
     * @throws LogicException the exception that will be thrown if the player tries
     *                        to buy a new fire engine but has not enough reputation
     *                        points
     */
    public void buyNewFireEngine(Cell cell) throws LogicException {
        removeReputation(COST_OF_FIRE_ENGINE);
        this.station.createNewFireEngine(cell);
    }

    /**
     * This method gets the fire engine that is wanted from the fire station that is
     * assigned to this player.
     * 
     * @param fireEngineID the identifier of the fire engine that should be gotten
     *                     from the fire station
     * @return Returns the fire engine as a FireEngine object
     * @throws LogicException the exception that will be thrown if the player tries
     *                        to access a fire engine that isn't his
     */
    public FireEngine getFireEngine(String fireEngineID) throws LogicException {
        FireEngine thisFireEngine = null;
        for (FireEngine fireEngine : station.getFireEngineList()) {
            if (fireEngine.getIdentifier().equals(fireEngineID)) {
                thisFireEngine = fireEngine;
                break;
            }
        }
        if (thisFireEngine == null) {
            throw new LogicException(String.format(Errors.FIRE_ENGINE_DOES_NOT_EXIST, fireEngineID));
        }
        return thisFireEngine;
    }

    /**
     * This method gets the fire station assigned to the player.
     * 
     * @return Returns the fire station that is assigned to the player as a
     *         FireStationCell object
     */
    public FireStationCell getStation() {
        return this.station;
    }
}
