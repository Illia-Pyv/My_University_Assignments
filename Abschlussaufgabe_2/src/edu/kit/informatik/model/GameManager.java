package edu.kit.informatik.model;

import java.util.ArrayList;

import edu.kit.informatik.model.constants.CellProperties;
import edu.kit.informatik.model.constants.Errors;

/**
 * This class manages the processes of the game, as well as all its functions.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class GameManager {

    private InitializationParameters initialParameters;
    private BoardRequirements requirements = new BoardRequirements();
    private InsertionSort sorter = new InsertionSort();
    // the order of how the players begin a new round
    private ArrayList<Player> orderOfFirstTurns;
    // the list of players in the current round
    private ArrayList<Player> playersInCurrentRound;
    // the player that started the current round
    private Player previousRoundStarter;
    private Player currentPlayer;
    private Board board;
    private Wind wind;
    private boolean waitForFireToRoll = false;

    /**
     * This method initializes the board at the start of the game with the initial
     * parameters from the command line arguments.
     * 
     * @param rows       the amount of rows that the board will have
     * @param columns    the amount of columns that the board will have
     * @param parameters the parameters that determine the start configuration of
     *                   the board
     */
    public void initialize(int rows, int columns, String[] parameters) throws LogicException {
        requirements.fulfillsRequirements(rows, columns, parameters);
        board = new Board(rows, columns, parameters);
        wind = new Wind(board);
        fillFirstTurns(board.getFireStations());
        playersInCurrentRound = new ArrayList<>();
        newRound();
        /*
         * the parameters are then stored after initializing the game, so that the reset
         * command can execute the same parameters
         */
        initialParameters = new InitializationParameters(rows, columns, parameters);
    }

    /**
     * This method resets the currently running game to the initial state that was
     * stored when the first game was initialized.
     * 
     * @throws LogicException the exception that is thrown if something goes wrong
     *                        when reseting the game
     */
    public void reset() throws LogicException {
        initialize(initialParameters.getRows(), initialParameters.getColumns(),
                initialParameters.getStringParameters());
    }

    /**
     * This method passes the turn to the next player in the queue.
     * 
     * @return Returns the player that the turn has been passed to
     * @throws LogicException the exception that is thrown if an error occurs when
     *                        trying to pass the turn
     */
    public Player turn() throws LogicException {
        checkIfWaitsForFireToRoll();
        if (playersInCurrentRound.isEmpty()) {
            waitForFireToRoll = true;
            newRound();
            return currentPlayer;
        }
        this.currentPlayer = dequeueNextPlayer();
        resetCurrentPlayersFireEngines();
        return this.currentPlayer;
    }

    private void checkIfWaitsForFireToRoll() throws LogicException {
        if (waitForFireToRoll) {
            throw new LogicException(Errors.FIRE_NOT_ROLLED);
        }
    }

    /**
     * This method moves a fire engine that belongs to the current player around the
     * board, as long as the fire engine that is moved has enough action points and
     * the fire engine hasn't done any other actions yet.
     * 
     * @param fireEngineID the identifier of the fire engine that is to be moved
     * @param row          the row on the board to which the fire engine should be
     *                     moved to
     * @param column       the column on the board to which the fire engine should
     *                     be moved to
     * @throws LogicException the exception that is thrown if an error occurs while
     *                        trying to move the fire engine, like the cell cannot
     *                        be moved to and many others
     */
    public void move(String fireEngineID, int row, int column) throws LogicException {
        checkIfWaitsForFireToRoll();
        Cell targetCell = getCell(row, column);
        if (targetCell.canMoveTo()) {
            getFireEngine(fireEngineID).move(targetCell);
        } else {
            throw new LogicException(Errors.CANNOT_MOVE_TO_BURNING_CELL);
        }
    }

    /**
     * This method gets the current board configuration.
     * 
     * @return Returns the board as the Board object
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * This method gets the cell from the board object by the handed over
     * coordinates row and column.
     * 
     * @param row    the row of the cell which should be returned
     * @param column the column of the cell which should be returned
     * @return Returns the cell as a cell object which is wanted from the board
     * @throws LogicException this exception handles the case if the cell that is
     *                        searched for does not exist on the board
     */
    public Cell getCell(int row, int column) throws LogicException {
        if (row >= 0 && row < board.getAmountOfRows() && column >= 0 && column < board.getAmountOfColumns()) {
            return board.getCellFromCoordinates(row, column);
        } else {
            throw new LogicException(Errors.CELL_NOT_ON_BOARD);
        }
    }

    /**
     * This method gets the current player of this round.
     * 
     * @return Returns the player as a Player object
     */
    public Player getPlayer() {
        return this.currentPlayer;
    }

    /**
     * This method represents rolling a die and thus determining the direction in
     * which the wind will blow.
     * 
     * @param windDirection the direction of the wind represented as the number of
     *                      the die
     * @return Returns the list of players that are left in the game after this
     *         method was executed
     * @throws LogicException the exception that is thrown if an error occurs while
     *                        trying to roll a fire
     */
    public ArrayList<Player> fireToRoll(int windDirection) throws LogicException {
        if (!waitForFireToRoll) {
            throw new LogicException(Errors.PLAYERS_NOT_PLAYED);
        }
        ArrayList<Player> temporary = new ArrayList<>();
        switch (windDirection) {
            case WindDirection.ALL_DIRECTIONS: {
                wind.allDirections();
                break;
            }
            case WindDirection.NORTH: {
                wind.north();
                break;
            }
            case WindDirection.EAST: {
                wind.east();
                break;
            }
            case WindDirection.SOUTH: {
                wind.south();
                break;
            }
            case WindDirection.WEST: {
                wind.west();
                break;
            }
            default: {
                break;
            }
        }
        removeBurningFireEngines();
        waitForFireToRoll = false;
        // determines what list of players should be returned
        if (orderOfFirstTurns.isEmpty()) {
            return orderOfFirstTurns;
        } else if (orderOfFirstTurns.contains(currentPlayer)) {
            temporary.add(currentPlayer);
            for (Player player : playersInCurrentRound) {
                temporary.add(player);
            }
            return temporary;
        }
        for (Player player : playersInCurrentRound) {
            temporary.add(player);
        }
        currentPlayer = dequeueNextPlayer();
        return temporary;
    }

    /**
     * This method is used to buy a new fire engine given the player has enough
     * reputation points.
     * 
     * <p>
     * It removes the cost of the fire engine in reputation points from the player
     * if he has enough of them and sets the fire engine to an adjacent cell of the
     * fire station that belongs to the player.
     * 
     * @param row    the row of the cell on which the new fire engine should be
     *               initialized
     * @param column the column of the cell on which the new fire engine should be
     *               initialized
     * @return Returns the remaining reputation points of the player after a
     *         successful purchase
     * @throws LogicException an exception is thrown if an error occurs while trying
     *                        to buy a new fire engine or initializing it on the
     *                        board
     */
    public int buyFireEngine(int row, int column) throws LogicException {
        checkIfWaitsForFireToRoll();
        Cell cell = getCell(row, column);
        if (cell.canMoveTo() && this.currentPlayer.getStation().getNeighbourList().contains(cell)) {
            this.currentPlayer.buyNewFireEngine(cell);
            int fireEngineListSize = this.currentPlayer.getStation().getFireEngineList().size();
            cell.addToCell(this.currentPlayer.getStation().getFireEngineList().get(fireEngineListSize - 1));
        } else {
            throw new LogicException(Errors.CANNOT_INITIALIZE_FIRE_ENGINE);
        }
        return this.currentPlayer.getReputation();
    }

    /**
     * This method extinguishes a cell which is adjacent to a fire engine.
     * 
     * @param fireEngineID the identifier of the fire engine that should extinguish
     *                     another cell
     * @param row          the row of the cell on the board which is to be
     *                     extinguished
     * @param column       the column of the cell on the board which is to be
     *                     extinguished
     * @return Returns the fire Engine as the fire engine object if the action was
     *         successful
     * @throws LogicException the exception that is thrown if an error occurs while
     *                        a fire engine tries to extinguish a cell
     */
    public FireEngine extinguish(String fireEngineID, int row, int column) throws LogicException {
        checkIfWaitsForFireToRoll();
        FireEngine fireEngine = getFireEngine(fireEngineID);
        String forestState = fireEngine.extinguish(getCell(row, column));
        if (forestState.equals(CellProperties.FOREST_LIGHTLY_BURNING)
                || forestState.equals(CellProperties.FOREST_STRONGLY_BURNING)) {
            this.currentPlayer.addReputation();
        }
        board.updateFires();
        if (board.allFiresExstinguished()) {
            return null;
        }
        return fireEngine;
    }

    /**
     * This method refills the water tank of a fire engine of the current player if
     * it is nearby a water source.
     * 
     * @param fireEngineID the identifier of the fire engine that has to be refilled
     * @return Returns the amount of action points of the fire engine that has
     *         executed this action
     * @throws LogicException the exception that is thrown if an error occurs while
     *                        a player is trying to refill his fire engine
     */
    public int refill(String fireEngineID) throws LogicException {
        checkIfWaitsForFireToRoll();
        FireEngine fireEngine = getFireEngine(fireEngineID);
        boolean canRefillAtStation = false;
        for (Cell cell : board.getFireStations()) {
            if (canRefillAtStation) {
                break;
            }
            for (Cell neighbor : cell.getNeighbourList()) {
                if (neighbor.getObjectList().contains(fireEngine)) {
                    canRefillAtStation = true;
                    break;
                }
            }
        }
        return fireEngine.refill(canRefillAtStation);
    }

    private FireEngine getFireEngine(String fireEngineID) throws LogicException {
        return currentPlayer.getFireEngine(fireEngineID);
    }

    // removes fire engines from the board if they stand directly on a strongly
    // burning forest cell
    private void removeBurningFireEngines() {
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        for (Player player : orderOfFirstTurns) {
            listOfPlayers.add(player);
        }
        for (Player player : listOfPlayers) {
            for (FireEngine fireEngine : player.getStation().getFireEngineList()) {
                ForestCell fireEnginePosition = (ForestCell) fireEngine.getCurrentPosition();
                // checks if the cell on which the fire engine is standing is burning in that
                // case the fire engine would be removed
                if (fireEnginePosition.getForestState().equals(CellProperties.FOREST_STRONGLY_BURNING)) {
                    fireEnginePosition.remove(fireEngine);
                    player.getStation().getFireEngineList().remove(fireEngine);
                }
                // checks if the player was removed
                if (removedPlayer(player)) {
                    break;
                }
            }
        }
    }

    // removes a player from the game if he has no more fire engines on the board
    private boolean removedPlayer(Player player) {
        boolean removed = false;
        if (player.getStation().getFireEngineList().isEmpty()) {
            orderOfFirstTurns.remove(player);
            playersInCurrentRound.remove(player);
            removed = true;
        }
        return removed;
    }

    // dequeues the player that is to move next
    private Player dequeueNextPlayer() {
        Player next = this.playersInCurrentRound.get(0);
        this.playersInCurrentRound.remove(next);
        if (playersInCurrentRound.isEmpty()) {
            // waitForFireToRoll = true;
        }
        return next;
    }

    private void newRound() {
        fillPlayersForNextRound();
        currentPlayer = dequeueNextPlayer();
        previousRoundStarter = currentPlayer;
        resetCurrentPlayersFireEngines();
    }

    private void resetCurrentPlayersFireEngines() {
        for (FireEngine fireEngine : currentPlayer.getStation().getFireEngineList()) {
            fireEngine.newRound();
        }
    }

    private void fillPlayersForNextRound() {
        int indexOfCurrentPlayer = orderOfFirstTurns.indexOf(previousRoundStarter);
        for (int i = indexOfCurrentPlayer + 1; i < orderOfFirstTurns.size(); i++) {
            playersInCurrentRound.add(orderOfFirstTurns.get(i));
        }
        for (int i = 0; i <= indexOfCurrentPlayer; i++) {
            playersInCurrentRound.add(orderOfFirstTurns.get(i));
        }
    }

    // fills the list of players in which order the players will have the first turn
    // of the new round
    private void fillFirstTurns(ArrayList<FireStationCell> allStations) {
        orderOfFirstTurns = new ArrayList<>();
        ArrayList<FireStationCell> stations = new ArrayList<>();
        for (FireStationCell stationCell : allStations) {
            stations.add(stationCell);
        }
        String[] fireStationArray = new String[stations.size()];
        int loopVariable = 0;
        for (FireStationCell fireStationCell : stations) {
            fireStationArray[loopVariable] = fireStationCell.getIdentifier();
            loopVariable++;
        }
        sorter.sortAscending(fireStationArray);
        for (String identifier : fireStationArray) {
            for (FireStationCell fireStation : stations) {
                if (fireStation.getIdentifier().equals(identifier)) {
                    orderOfFirstTurns.add(new Player(fireStation));
                    stations.remove(fireStation);
                    break;
                }
            }
        }
    }
}
