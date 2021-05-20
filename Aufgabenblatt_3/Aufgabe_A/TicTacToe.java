/**
 * This class calculates the winner of a Tic-Tac-Toe game.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class TicTacToe {
    // The size of the field is constant
    private static final int SIZE = 3;
    private String[] userInput;
    private String[] args;

    /**
     * This is the constructor of the class.
     * 
     * It receives the user Input as a String, splits it by spaces and stores it in
     * a private Array called userInput.
     * 
     * @param argPlayerTurns is a String which is received from the user
     */
    public TicTacToe(String[] argPlayerTurns) {
        this.args = argPlayerTurns;
        splitArgs();
    }

    /**
     * This method processes the input to be ready for use in further calculations.
     * 
     * @return Returns the processed user Input 
     */
    private void splitArgs() {
        String s = "";
        int numberOfArgs = SIZE * SIZE;
        for (int i = 0; i < numberOfArgs; ++i) {
            s += (this.args[i] + " ");
        }
        userInput = s.split(" ");
    }

    /**
     * This method assigns the calculated winner to a variable and returns it.
     * 
     * @return Returns a String which can be printed out in the Main class
     */
    public String getWinner() {
        String winner = whoIsWinner();
        return winner;
    }

    /**
     * This method calculates who the winner is and returns a String.
     * 
     * @return Returns a String which says which player won at which turn or if the
     *         game is a draw
     */
    private String whoIsWinner() {
        int turn;
        String result = "";
        char[][] myPlayField = new char[SIZE][SIZE];

        for (int n = 0; n < SIZE; n++) {
            for (int m = 0; m < SIZE; m++) {
                myPlayField[n][m] = '.';
            }
        }

        for (int i = 0; i < userInput.length; i++) {
            turn = i + 1;
            myPlayField = tickField(myPlayField, turn, i);
            result = checkIfTicTacToe(myPlayField, turn, result);
        }
        if (result == "") {
            result = "draw";
        }

        return result;
    }

    /**
     * This method fills a matrix with numbers from 0 to 8.
     * 
     * @return Returns a matrix with numbers from 0 to 8
     */
    private char[][] initializeMatrix() {
        int v = 1;
        String str = "";
        String[] arrNumbers;
        String[] arrElems;
        char[][] initMatrix = new char[SIZE][SIZE];
        for (int u = 0; u < userInput.length; u++) {
            if (modulo(v, SIZE) == 0) {
                str += u + ";";
            } else if (modulo(v, SIZE) != 0) {
                str += u + ",";
            } else {
                str += u;
            }
            v++;
        }
        arrNumbers = str.split(";");

        for (int i = 0; i < SIZE; i++) {
            arrElems = arrNumbers[i].split(",");
            for (int j = 0; j < SIZE; j++) {
                initMatrix[i][j] = arrElems[j].charAt(0);
            }
        }
        return initMatrix;
    }

    /**
     * This method calculates which field has to be ticked with a "0" or an "X".
     * 
     * @param argField The number of the field which was received from the user
     * @return Returns a String which has the coordinates of the field in the
     *         matrix, which has to be ticked, separated by a comma
     */
    private String calculateField(char argField) {
        String field = "";
        char[][] initMatrix = initializeMatrix();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (initMatrix[i][j] == argField) {
                    field = i + "," + j;
                    return field;
                } else {
                    field = "";
                }
            }
        }
        return field;
    }

    /**
     * This method ticks a field in the matrix and returns it.
     * 
     * @param argMatrix    A Matrix received from the method whoIsWinner(), which
     *                     initially is filled with "." but gradually fills up with
     *                     "X" and "0"
     * @param argTurn      The current turn received from the method whoIsWinner()
     * @param argIteration A number received from the method whoIsWinner(), which is
     *                     needed to iterate through userInput
     * @return Returns a multidimensional Array
     */
    private char[][] tickField(char[][] argMatrix, int argTurn, int argIteration) {
        int u;
        int v;
        char[][] thisMatrix = argMatrix;
        if (isOddNumber(argTurn)) {
            String[] coordinates = calculateField(userInput[argIteration].charAt(0)).split(",");
            u = Integer.parseInt(coordinates[0]);
            v = Integer.parseInt(coordinates[1]);
            thisMatrix[u][v] = 'X';
        } else {
            String[] coordinates = calculateField(userInput[argIteration].charAt(0)).split(",");
            u = Integer.parseInt(coordinates[0]);
            v = Integer.parseInt(coordinates[1]);
            thisMatrix[u][v] = '0';
        }
        return thisMatrix;
    }

    /**
     * This method checks if a player has won.
     * 
     * @param argMatrix    A Matrix received from the method whoIsWinner() which
     *                     will never be filled completely with "." but there will
     *                     be at least one "X"
     * @param argTurn      The current turn received from the method whoIsWinner()
     * @param argStrResult Is a String received from the method whoIsWinner()
     * @return Returns a String which is either empty, or it says which player has
     *         won and on which turn
     */
    private String checkIfTicTacToe(char[][] argMatrix, int argTurn, String argStrResult) {
        boolean isTicTacToe = false;
        char[] c = new char[SIZE];

        if (argStrResult.equals("")) {
            if (isTicTacToe == true) {
                return buildString(argTurn, argStrResult);
            } else {
                // check rows
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        c[j] = argMatrix[i][j];
                    }
                    isTicTacToe = sameElems(c);
                    if (isTicTacToe == true) {
                        return buildString(argTurn, argStrResult);
                    } else {
                        // check diagonals
                        for (int k = 0; k < SIZE; k++) {
                            c[k] = argMatrix[k][k];
                        }
                        isTicTacToe = sameElems(c);
                        if (isTicTacToe == true) {
                            return buildString(argTurn, argStrResult);
                        } else {
                            int col;
                            for (int n = 0; n < SIZE; n++) {
                                col = SIZE - (n + 1);
                                c[n] = argMatrix[n][col];
                            }
                            isTicTacToe = sameElems(c);
                            if (isTicTacToe == true) {
                                return buildString(argTurn, argStrResult);
                            } else {
                                isTicTacToe = false;
                            }
                        }
                    }
                }
            }
        } else {
            isTicTacToe = false;
        }
        return argStrResult;
    }

    /**
     * This method calculates if all the elements in an Array are the same.
     * 
     * @param argC Is a character Array received from the method checkIfTicTacToe()
     * @return Returns either "true" if all of the Elements in the Array are the
     *         same and returns "false" if at least one character is not the same as
     *         the others
     */
    private boolean sameElems(char[] argC) {
        char emptyField = '.';
        boolean areSame = false;
        for (int i = 0; i < argC.length - 1; i++) {
            if (argC[i] != emptyField) {
                if (argC[i] == argC[i + 1]) {
                    areSame = true;
                } else {
                    areSame = false;
                    return areSame;
                }
            } else {
                areSame = false;
                return areSame;
            }
        }
        return areSame;
    }

    /**
     * This method builds a String.
     * 
     * @param argTurn      Is the current turn
     * @param argStrResult Is an empty String
     * @return Returns a String which says which Player has won on which turn
     */
    private String buildString(int argTurn, String argStrResult) {
        String result = argStrResult;
        if (isOddNumber(argTurn)) {
            result = "P1 wins " + argTurn;
        } else {
            result = "P2 wins " + argTurn;
        }
        return result;
    }

    /**
     * This method calculates if a number is odd or even.
     * 
     * @param argNum The number which has to be checked if its odd or even
     * @return Returns "true" if the number is odd and it returns "false" if the
     *         number is even
     */
    private boolean isOddNumber(int argNum) {
        boolean isOdd = false;
        for (int i = 1; i <= userInput.length; i += 2) {
            if (argNum == i) {
                isOdd = true;
                return isOdd;
            } else {
                isOdd = false;
            }
        }
        return isOdd;
    }

    /**
     * This method calculates the number which is left after dividing two numbers
     * 
     * @param argNum1 This number is the Numerator of the fraction
     * @param argNum2 This number is the Denominator of the fraction
     * @return Returns the remainder after a division
     */
    private int modulo(int argNum1, int argNum2) {
        int result;
        int num = argNum1 / argNum2;
        if (num * argNum2 != argNum1) {
            result = argNum1 - (num * argNum2);
        } else {
            result = 0;
        }
        return result;
    }
}
