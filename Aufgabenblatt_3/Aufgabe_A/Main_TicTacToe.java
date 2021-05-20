/**
 * This class is for the interaction with the user.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Main_TicTacToe {
    /**
     * This method outputs the winner of a Tic-Tac-Toe game and on which turn they
     * won.
     * 
     * @param args Arguments used to enter 9 numbers which represent each field
     *             number of the game
     */
    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe(args);
        System.out.println(ttt.getWinner());
    }
}
