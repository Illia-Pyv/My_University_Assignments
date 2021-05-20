package edu.kit.informatik.Santorini.game;

import java.util.ArrayList;

import edu.kit.informatik.Santorini.game.gamepieces.Cuboid;
import edu.kit.informatik.Santorini.game.gamepieces.Dome;
import edu.kit.informatik.Santorini.game.gamepieces.GamePieces;
import edu.kit.informatik.Santorini.view.SpecialCharacters;

/**
 * 
 * @author illya
 */
public class Bag {
    /**
     * 
     */
    static ArrayList<GamePieces> cuboids = new ArrayList<>();
    /**
     * 
     */
    static ArrayList<GamePieces> domes = new ArrayList<>();
    /**
     * This is the amount of cuboids that the bag holds at the start of the game.
     */
    private static final int AMOUNT_OF_CUBOIDS = 54;
    /** This is the amount of domes that the bag holds at the start of the game. */
    private static final int AMOUNT_OF_DOMES = 18;
    
    /**
     * 
     */
    public Bag() {
        fillBag();
    }

    /**
     * 
     * @return Returns the command
     */
    public String printElements() {
        int amountOfCuboids = cuboids.size();
        int amountOfDomes = domes.size();
        String printBag = cuboids.get(0).toString() + SpecialCharacters.SEMICOLON + amountOfCuboids
                + SpecialCharacters.NEWLINE + domes.get(0).toString() + SpecialCharacters.SEMICOLON + amountOfDomes;
        return printBag;
    }

    /**
     * 
     * @param gamePiece
     * @return Returns the command
     */
    public GamePieces getFromBag(String gamePiece) {
        GamePieces piece = null;
        if (cuboids.get(0).getName().equals(gamePiece)) {
            piece = cuboids.get(0);
            cuboids.remove(0);
            return piece;
        } else {
            piece = domes.get(0);
            domes.remove(0);
            return piece;
        }
    }
    
    private void fillBag() {
        for (int i = 0; i < AMOUNT_OF_CUBOIDS; i++) {
            cuboids.add(new Cuboid());
        }

        // Here I can use the variable "i" again, because the scope of the variable was
        // closed by a curly bracket in the last loop.
        for (int i = 0; i < AMOUNT_OF_DOMES; i++) {
            domes.add(new Dome());
        }
    }
}
