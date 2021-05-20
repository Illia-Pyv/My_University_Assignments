package edu.kit.informatik;

import edu.kit.informatik.ioProcessing.RuntimeManager;

/**
 * This class is the starting point of the application.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Main {

    /**
     * This is the main method which starts the program and thereby allows for all
     * the calculations.
     * 
     * @param args the arguments the
     */
    public static void main(String[] args) {
        RuntimeManager manager = new RuntimeManager(args);
        manager.start();
    }

}
