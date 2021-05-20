/**
 * This class contains all the methods necessary to do calculations with magic
 * squares.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class MagicSquare {

    private int magConst;
    private static final String[] operations = { "showMagicNumbers", "isMagicSquare?", "complement" };
    private int[][] matrix;

    /**
     * This is the constructor of the class.
     * 
     * It receives a multidimensional array and stores it in a private variable
     * called matrix.
     * 
     * @param square is a multidimensional which is received from the from the user
     */
    public MagicSquare(int[][] square) {
        this.matrix = square;
        this.magConst = 0;
    }

    /**
     * This method determines on two user inputs which method to call.
     * 
     * @param argOperation receives the operation as String from the user
     * @param argInput     receives a number from the user
     * @return Returns a String which can be printed in the Main class
     */
    public String execute(String argOperation, String argInput) {
        boolean isSemimagicSquare = false;
        boolean isMagicSquare = false;
        int magNum = 0;
        String op = "";

        if (argOperation.equals(operations[0])) {
            magNum = Integer.parseInt(argInput);
            op = showMagicNumbers(magNum);
        } else if (argOperation.equals(operations[1])) {
            isSemimagicSquare = isSemimagicSquare();
            isMagicSquare = isMagicSquare();
            op = checkIfMagic(isSemimagicSquare, isMagicSquare);
        } else if (argOperation.equals(operations[2])) {
            op = complement();
        } else {
            op = "Incorrect operation type";
        }
        return op;
    }

    /**
     * This method calculates a magic number.
     * 
     * @param argNum receives an Integer to calculate the magic number
     * @return Returns the magic number as an Integer
     */
    private int getMagicNumber(int argNum) {
        int temp = (power(argNum, 3) + argNum) / 2;
        return temp;
    }

    /**
     * This method calculates all the magic numbers from 1 to k.
     * 
     * @param k is the amount of Magic numbers that will be displayed and it is
     *          determined by the user input
     * @return Returns a String which by printing out displays all magic numbers
     *         from n = 1 to k separated by a ","
     */
    public String showMagicNumbers(int k) {
        String result = "";

        for (int n = 1; n <= k; n++) {
            int temp = getMagicNumber(n);
            if (n != k) {
                result += temp + ",";
            } else {
                result += temp;
            }
        }
        return result;
    }

    /**
     * This method checks whether the users input is a magic square or not.
     * 
     * @return Returns "true" if the users input is a magic square and returns
     *         "false" otherwise
     */
    public boolean isMagicSquare() {
        boolean isMagic = false;
        int sum = 0;
        if (isSemimagicSquare()) {
            for (int i = 0; i < matrix.length; i++) {
                sum += matrix[i][i];
            }
            if (sum == magConst) {
                int col = 0;
                sum = 0;
                for (int i = 0; i < matrix.length; i++) {
                    col = matrix.length - (i + 1);
                    sum += matrix[i][col];
                }
                if (sum == magConst) {
                    isMagic = true;
                }
            }
        }
        return isMagic;
    }

    /**
     * This method checks whether the users input is a semi magical square or not.
     * 
     * @return Returns "true" if the users input is a semi magical square and
     *         returns "false" otherwise
     */
    public boolean isSemimagicSquare() {
        boolean isSemimagic = true;
        int size = matrix.length;
        int sum = 0;
        for (int i = 1; i <= (power(size, 2)); i++) {
            sum += i;
        }
        magConst = sum / size;

        // Sum Rows
        for (int i = 0; i < size; i++) {
            sum = 0;
            for (int j = 0; j < size; j++) {
                sum += matrix[i][j];
            }
            if (sum != magConst) {
                isSemimagic = false;
                break;
            }
        }

        // Sum Columns
        if (isSemimagic) {
            for (int j = 0; j < size; j++) {
                sum = 0;
                for (int i = 0; i < size; i++) {
                    sum += matrix[i][j];
                }
                if (sum != magConst) {
                    isSemimagic = false;
                    break;
                }
            }
        }
        return isSemimagic;
    }

    /**
     * This method calculates the complement of a magic Square.
     * 
     * @return Returns a string, which by printing out, displays it as a matrix with
     *         an equal amount of rows and columns
     */
    public String complement() {
        int length = matrix.length;
        String strOut = "";
        int[][] compMatrix = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                compMatrix[i][j] = (matrix[i][j] * (-1) + (power(length, 2) + 1));
                strOut += (compMatrix[i][j] + " ");
            }
            if (i < (matrix.length - 1)) {
                strOut += "\n";
            }
        }
        return strOut;
    }

    /**
     * This is a method which turns a multidimensional array into a matrix.
     * 
     * @return Returns a String, which by printing out, displays it as a matrix with
     *         an equal amount of rows and columns
     */
    public String toString() {
        String strMatrix = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                strMatrix += matrix[i][j] + " ";
            }
            if (i < (matrix.length - 1)) {
                strMatrix += "\n";
            }
        }

        return strMatrix;
    }

    /**
     * This method checks if the users input is a magic square, a Semi magical
     * square or a not magical one.
     * 
     * @param argIsSemiMagic gets the value from the method "isSemimagicSquare"
     * @param argIsMagic     gets the value from the method "isMagicSquare"
     * @return Returns: "magic square" if the users input is a magic square,
     *         "semimagic square" if the users input is a semi magical square and
     *         "not magical" if the users input is not a magical square
     */
    private String checkIfMagic(boolean argIsSemiMagic, boolean argIsMagic) {
        String result = "";

        if (argIsSemiMagic && argIsMagic) {
            result = "magic square";
        } else if (argIsSemiMagic && (argIsMagic != true)) {
            result = "semimagic square";
        } else {
            result = "not magical";
        }
        return result;
    }

    /**
     * This method calculates the a number to the power of another one.
     * 
     * @param argNum   is any number to the power of argPower
     * @param argPower is any number which is the power of argNum
     * @return Returns "argNum" to the power of "argPower"
     */
    private int power(int argNum, int argPower) {
        int power = argNum;
        for (int i = 0; i < argPower - 1; i++) {
            power = power * argNum;
        }
        return power;
    }
}
