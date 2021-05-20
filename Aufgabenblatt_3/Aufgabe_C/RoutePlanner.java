import edu.kit.informatik.Terminal;

/**
 * This class calculates the number of paths that are possible given a certain
 * path length.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class RoutePlanner {
    private final static int MINIMUM_PATH_LENGTH_FACTOR = 2;

    // value of the 1st command line argument
    private String pathRouteFile;
    // value of the 2nd command line argument
    private int startVertex;
    // value of the 3rd command line argument
    private int destVertex;
    // value of the 4th command line argument
    private int pathLength;

    private String[] cmdArgs;
    private int[][] adjacencyMatrix;
    private int nMaxVertex;

    /**
     * This is the constructor of the class.
     * 
     * It receives a user Input as an Array of Strings and stores it in a variable.
     * It also initializes a few other attributes.
     * 
     * @param argCmdArgs is a String Array which is received from the user
     */
    public RoutePlanner(String[] argCmdArgs) {
        cmdArgs = argCmdArgs;
        nMaxVertex = 0;
        pathLength = 0;
        startVertex = 0;
        destVertex = 0;
    }

    /**
     * This method parses the command line arguments entered by the user into their
     * right data types.
     */
    public void parse() {
        pathRouteFile = cmdArgs[0];
        startVertex = Integer.parseInt(cmdArgs[1]);
        destVertex = Integer.parseInt(cmdArgs[2]);
        pathLength = Integer.parseInt(cmdArgs[3]);

        getAdjacencyMatrix();
    }

    /**
     * This method calculates the number of paths that are possible given a certain
     * path length.
     * 
     * @return Returns an Integer which represents the number of paths that are
     *         possible
     */
    public int getNumberOfPaths() {
        int nRet = 0;
        int nSize = adjacencyMatrix.length;
        int[][] resMx = new int[nSize][nSize];

        resMx = adjacencyMatrix;

        for (int i = MINIMUM_PATH_LENGTH_FACTOR; i <= pathLength; i++) {
            resMx = mux(resMx, adjacencyMatrix);
        }

        nRet = resMx[startVertex][destVertex];
        return nRet;
    }

    /**
     * This method searches for the biggest Vertex in the file that the user gave
     * us.
     * 
     * @param argRoutes Receives a String Array of all routes from the file in the
     *                  user Input
     * @return Returns a number which represents the city with the biggest number
     */
    private int findMaxVertex(String[] argRoutes) {
        int nStart = 0;
        int nEnd = 0;
        int nMax = 0;
        String[] line;

        for (int i = 0; i < argRoutes.length; i++) {
            line = argRoutes[i].split(" ");
            nStart = Integer.parseInt(line[0]);
            nEnd = Integer.parseInt(line[1]);
            if (nStart > nEnd) {
                nMax = nStart;
            } else {
                nMax = nEnd;
            }
            if (nMax > nMaxVertex) {
                nMaxVertex = nMax;
            }
        }
        return nMaxVertex;
    }

    /**
     * This method calculates the Adjacency Matrix.
     */
    private void getAdjacencyMatrix() {
        int row = 0;
        int column = 0;
        int length = 0;
        String[] routes;
        String[] line;

        routes = Terminal.readFile(pathRouteFile);
        nMaxVertex = findMaxVertex(routes);
        length = nMaxVertex + 1;
        adjacencyMatrix = new int[length][length];
        adjacencyMatrix = initializeMatrix(adjacencyMatrix);

        for (int i = 0; i < routes.length; i++) {
            line = routes[i].split(" ");
            row = Integer.parseInt(line[0]);
            column = Integer.parseInt(line[1]);

            adjacencyMatrix[row][column] = 1;
        }
    }

    /**
     * This method multiplies two matrices and returns the result.
     * 
     * @param argMatrix1 is the first matrix
     * @param argMatrix2 is the second matrix
     * @return Returns the product of two matrices
     */
    private int[][] mux(int[][] argMatrix1, int[][] argMatrix2) {
        int[][] resMatrix;
        int[][] matrix1 = argMatrix1;
        int[][] matrix2 = argMatrix2;
        int length = argMatrix1.length;
        resMatrix = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    resMatrix[i][j] += (matrix1[i][k] * matrix2[k][j]);
                }
            }
        }

        return resMatrix;
    }

    /**
     * This method initializes a matrix by filling it with zeros.
     * 
     * @param argMatrix This parameter is the matrix which will be initialized
     * @return Returns a matrix filled with zeros
     */
    private int[][] initializeMatrix(int[][] argMatrix) {
        int[][] matrix = argMatrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }
}
