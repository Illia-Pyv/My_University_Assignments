import edu.kit.informatik.Terminal;

/**
 * This class is for the interaction with the user.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Main {
    /**
     * This method prints out the number of paths.
     * 
     * @param args
     */
    public static void main(String[] args) {
        int numberPaths = 0;
        RoutePlanner routePlanner = new RoutePlanner(args);
        routePlanner.parse();
        numberPaths = routePlanner.getNumberOfPaths();
        Terminal.printLine(numberPaths);
    }

}
