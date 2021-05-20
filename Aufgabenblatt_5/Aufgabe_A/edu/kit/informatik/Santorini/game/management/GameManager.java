package edu.kit.informatik.Santorini.game.management;

import java.util.HashMap;

import edu.kit.informatik.Santorini.core.OutputManager;
import edu.kit.informatik.Santorini.game.Bag;
import edu.kit.informatik.Santorini.game.Board;
import edu.kit.informatik.Santorini.game.Player;
import edu.kit.informatik.Santorini.game.gamepieces.GamePieces;
import edu.kit.informatik.Santorini.game.gods.Apollo;
import edu.kit.informatik.Santorini.game.gods.Artemis;
import edu.kit.informatik.Santorini.game.gods.Athena;
import edu.kit.informatik.Santorini.game.gods.Atlas;
import edu.kit.informatik.Santorini.game.gods.Demeter;
import edu.kit.informatik.Santorini.game.gods.Hermes;
import edu.kit.informatik.Santorini.game.gods.IGivePower;
import edu.kit.informatik.Santorini.view.Arguments;
import edu.kit.informatik.Santorini.view.CardName;
import edu.kit.informatik.Santorini.view.InputCommands;
import edu.kit.informatik.Santorini.view.SpecialCharacters;
import edu.kit.informatik.Santorini.view.commandParser.Command;
import edu.kit.informatik.Santorini.view.exception.AlreadyMovedException;
import edu.kit.informatik.Santorini.view.messages.Errors;
import edu.kit.informatik.Santorini.view.messages.Messages;

public class GameManager {

    private OutputManager output = null;
    private Board board = new Board();
    private Bag bag = new Bag();
    private HashMap<String, Player> mapPlayer = new HashMap<String, Player>();
    private HashMap<String, IGivePower> godCards = new HashMap<>();

    private String recentlyMoved = null;
    private String recentlyBuilt = null;
    private final String playerOne = "P1";
    private final String playerTwo = "P2";
    private int count = 0;
    private int cardsUsed = 0;
    private String currentPlayer = "";
    private final int parameterSizeThree = 3;

    /**
     * 
     * @param output
     * @param arguments
     */
    public GameManager(OutputManager output, String[] arguments) {
        this.output = output;
        beginGame(arguments);
    }

    /**
     * 
     * @param arguments
     */
    private void beginGame(String[] arguments) {
        String[] thisInput = null;
        mapPlayer.put(playerOne, new Player(board, playerOne));
        mapPlayer.put(playerTwo, new Player(board, playerTwo));
        setCurrentPlayer(playerOne);
        for (String players : arguments) {
            thisInput = players.split(SpecialCharacters.SEMICOLON);
            addFigurineToPlayer(thisInput[Arguments.ARG0.ordinal()],
                    Integer.parseInt(thisInput[Arguments.ARG1.ordinal()]),
                    Integer.parseInt(thisInput[Arguments.ARG2.ordinal()]));
        }
        godCards.put(CardName.CARD_APOLLO, new Apollo(CardName.CARD_APOLLO));
        godCards.put(CardName.CARD_ARTEMIS, new Artemis(CardName.CARD_ARTEMIS));
        godCards.put(CardName.CARD_ATHENA, new Athena(CardName.CARD_ATHENA));
        godCards.put(CardName.CARD_ATLAS, new Atlas(CardName.CARD_ATLAS));
        godCards.put(CardName.CARD_DEMETER, new Demeter(CardName.CARD_DEMETER));
        godCards.put(CardName.CARD_HERMES, new Hermes(CardName.CARD_HERMES));
    }

    /**
     * 
     * @param command
     * @return Returns the command
     * @throws SantoriniException
     */
    public boolean executeCommand(Command command) {
        boolean executeReturn = true;
        String thisCommand = command.getCommand();
        String[] parameters = command.getParameters();
        String message = "";
        switch (thisCommand) {
        case InputCommands.BAG: {
            message = printBag(parameters);
            break;
        }
        case InputCommands.BUILD: {
            try {
                message = build(command.splitByEachParameter(parameters));
            } catch (AlreadyBuiltException a) {
                output.retrieveErrorMessage(a.getMessage());
            }
            break;
        }
        case InputCommands.CELLPRINT: {
            message = printCell(command.splitByEachParameter(parameters));
            break;
        }
        case InputCommands.DRAW_CARD: {
            message = drawCard(parameters);
            break;
        }
        case InputCommands.MOVE: {
            try {
                message = move(command.splitByEachParameter(parameters));
            } catch (NullPointerException e) {
                output.retrieveErrorMessage(e.getMessage());
            } catch (IllegalArgumentException i) {
                output.retrieveErrorMessage(i.getMessage());
            } catch (AlreadyMovedException a) {
                output.retrieveErrorMessage(a.getMessage());
            } catch (IndexOutOfBoundsException outOfBounds) {
                output.retrieveErrorMessage(outOfBounds.getMessage());
            }
            break;
        }
        case InputCommands.PRINT: {
            message = printBoard(parameters);
            break;
        }
        case InputCommands.QUIT: {
            executeReturn = false;
            try {
                quit(parameters);
            } catch (IllegalArgumentException e) {
                output.retrieveErrorMessage(e.getMessage());
                executeReturn = true;
            }
            break;
        }
        case InputCommands.SURRENDER: {
            executeReturn = false;
            try {
                message = surrender(parameters);
            } catch (IllegalArgumentException e) {
                output.retrieveErrorMessage(e.getMessage());
                executeReturn = true;
            }
            break;
        }
        case InputCommands.TURN: {
            try {
                message = turn(parameters);
            } catch (IllegalArgumentException e) {
                output.retrieveErrorMessage(e.getMessage());
            }
            break;
        }
        default: {
            /* do nothing */ }
        }
        output.retrieveMessage(message);
        return executeReturn;
    }

    private void setRecentlyMoved(String name) {
        this.recentlyMoved = name;
    }

    private void setRecentlyBuilt(String name) {
        this.recentlyBuilt = name;
    }

    private void setCurrentPlayer(String name) {
        this.currentPlayer = name;
    }

    private String move(String[] parameters)
            throws IllegalArgumentException, NullPointerException, AlreadyMovedException, IndexOutOfBoundsException {
        checkThreeParameters(parameters);
        String name = parameters[Arguments.ARG0.ordinal()];
        if (mapPlayer.get(currentPlayer).canGetFigurine(name) == false) {
            throw new NullPointerException(Errors.INVALID_FIGURE);
        }
        int row = Integer.parseInt(parameters[Arguments.ARG1.ordinal()]);
        int column = Integer.parseInt(parameters[Arguments.ARG2.ordinal()]);
        boolean hasWon = false;
        if (this.recentlyMoved == null) {
            hasWon = mapPlayer.get(currentPlayer).move(name, row, column);
            setRecentlyMoved(name);
            return hasWon(hasWon);
        } else {
            throw new AlreadyMovedException(Errors.ALREADY_MOVED);
        }
    }

    private void checkThreeParameters(String[] parameters) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (parameters.length < parameterSizeThree || parameters.length > parameterSizeThree) {
            throw new IllegalArgumentException(Errors.INVALID_PARAMETERS);
        }
        int row = Integer.parseInt(parameters[Arguments.ARG1.ordinal()]);
        int column = Integer.parseInt(parameters[Arguments.ARG2.ordinal()]);

        // This

        if ((row < 0) || (column < 0) || (row > board.getBoardSize()) || (column > board.getBoardSize())) {
            throw new IndexOutOfBoundsException(Errors.INVALID_CELL);
        }
    }

    private String hasWon(boolean hasWon) {
        if (hasWon) {
            return currentPlayer + Messages.WINNER;
        }
        return Messages.SUCCESS;
    }

    private String build(String[] parameters) throws AlreadyBuiltException {
        checkThreeParameters(parameters);
        String buildingPiece = parameters[Arguments.ARG0.ordinal()];
        int row = Integer.parseInt(parameters[Arguments.ARG1.ordinal()]);
        int column = Integer.parseInt(parameters[Arguments.ARG2.ordinal()]);
        if (this.recentlyBuilt == null) {
            GamePieces buildingBlock = this.bag.getFromBag(buildingPiece);
            mapPlayer.get(currentPlayer).build(recentlyMoved, buildingBlock, row, column);
            setRecentlyBuilt(recentlyMoved);
            return Messages.SUCCESS;
        } else {
            throw new AlreadyBuiltException(Errors.ALREADY_BUILT);
        }
    }

    private void addFigurineToPlayer(String name, int row, int column) throws IndexOutOfBoundsException {
        if (count < mapPlayer.size()) {
            mapPlayer.get(playerOne).addFigurine(name, row, column);
        } else {
            mapPlayer.get(playerTwo).addFigurine(name, row, column);
        }
        count++;
    }

    private String printBag(String[] parameters) throws IllegalArgumentException {
        if (hasMoreParameters(parameters)) {
            throw new IllegalArgumentException(Errors.NOPARAMETER);
        }
        return bag.printElements();
    }

    private String printCell(String[] parameters) {
        int row = Integer.parseInt(parameters[Arguments.ARG0.ordinal()]);
        int column = Integer.parseInt(parameters[Arguments.ARG1.ordinal()]);
        return board.printCell(row, column);
    }

    private String drawCard(String[] parameters) {
        String name = parameters[Arguments.ARG0.ordinal()];
        boolean cardUsed = false;
        if (cardUsed) {
            return "You have already used this card.";
        }
        if (cardsUsed > 2) {
            return "You have already used all of your cards for this game.";
        }
        if (recentlyMoved == null) {
            IGivePower god = shiftGodCard(name);
            mapPlayer.get(currentPlayer).setGodCard(god);
            cardsUsed++;
            return Messages.SUCCESS;
        } else {
            return "You cannot draw card, because you already moved.";
        }
    }

    private IGivePower shiftGodCard(String name) {
        IGivePower god = godCards.get(name);
        godCards.remove(name);
        return god;
    }

    private String printBoard(String[] parameters) throws IllegalArgumentException {
        if (hasMoreParameters(parameters)) {
            throw new IllegalArgumentException(Errors.NOPARAMETER);
        }
        return board.printBoard();
    }

    private void quit(String[] parameters) throws IllegalArgumentException {
        if (hasMoreParameters(parameters)) {
            throw new IllegalArgumentException(Errors.NOPARAMETER);
        }
    }

    private String surrender(String[] parameters) throws IllegalArgumentException {
        if (hasMoreParameters(parameters)) {
            throw new IllegalArgumentException(Errors.NOPARAMETER);
        }
        return nextPlayer().getName() + Messages.WINNER;
    }

    private boolean hasMoreParameters(String[] parameters) {
        if (parameters.length > 0) {
            return true;
        }
        return false;
    }

    private Player nextPlayer() {
        Player thisCurrentPlayer = mapPlayer.get(currentPlayer);
        Player nextPlayer = null;
        for (String name : mapPlayer.keySet()) {
            nextPlayer = mapPlayer.get(name);
            if (!nextPlayer.equals(thisCurrentPlayer)) {
                break;
            }
        }
        return nextPlayer;
    }

    private String turn(String[] parameters) {
        if (hasMoreParameters(parameters)) {
            throw new IllegalArgumentException(Errors.NOPARAMETER);
        }
        setRecentlyMoved(null);
        setRecentlyBuilt(null);
        mapPlayer.get(currentPlayer).setGodCard(null);
        mapPlayer.get(currentPlayer).setMagic(null);
        setCurrentPlayer(nextPlayer().getName());
        return this.currentPlayer;
    }
}
