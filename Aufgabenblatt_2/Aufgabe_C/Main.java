/**
 * This is the class Main for the interaction with the user.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Main {

    /**
     * This is the main method.
     * 
     * It handles the input and output of this program.
     * 
     * @param args are the console arguments which are used to get the user input
     */
    public static void main(String[] args) {
        String strOperation = args[0];
        String strInputArgs = args[1];
        int[][] matrix = fillMatrix(strInputArgs);

        MagicSquare mq = new MagicSquare(matrix);
        String strResult = mq.execute(strOperation, strInputArgs);
        System.out.println(strResult);
    }

    /**
     * This method turns a String into a multidimensional array of the type Integer.
     * 
     * @param argInput receives a String from the user input
     * @return Returns a multidimensional array filled with the numbers from the
     *         user input
     */
    private static int[][] fillMatrix(String argInput) {
        int[][] matrix;
        int size = 0;
        String[] arrElems;
        String[] arrMatrixRow = argInput.split(";");
        size = arrMatrixRow.length;
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            arrElems = arrMatrixRow[i].split(",");
            for (int j = 0; j < arrElems.length; j++) {
                matrix[i][j] = Integer.parseInt(arrElems[j]);
            }
        }
        return matrix;
    }
}