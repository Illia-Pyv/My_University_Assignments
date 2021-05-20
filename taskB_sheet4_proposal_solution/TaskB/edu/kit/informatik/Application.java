/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik;

import edu.kit.informatik.core.Input;
import edu.kit.informatik.core.Output;
import edu.kit.informatik.model.authentication.AccessControl;
import edu.kit.informatik.model.post.PostSystem;
import edu.kit.informatik.presenter.Quit;
import edu.kit.informatik.presenter.authentication.AuthenticationCommandSupplier;
import edu.kit.informatik.presenter.post.PostCommandSupplier;
import edu.kit.informatik.view.Session;
import edu.kit.informatik.view.command.IPDCommandParser;

/**
 * The Application. Creates the needed instances and runs the interactive command processing.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public final class Application {

    // Abstraction from Terminal class, 
    // With method references the anonymous inner classes can be replaced by Terminal::printLine.
    // This is not part of the programming lecture
    private static final Output OUTPUT = new Output() {
        @Override
        public void output(final String string) {
            Terminal.printLine(string);
        }
    };
    private static final Output ERROR_OUTPUT = new Output() {
        @Override
        public void output(final String string) {
            Terminal.printError(string);
        }
    };
    private static final Input INPUT = new Input() {
        @Override
        public String read() {
            return Terminal.readLine();
        }
    };

    // utility-class constructor
    private Application() { }

    /**
     * The main entry point to the application.
     *
     * @param args the command-line arguments, not used
     */
    public static void main(final String[] args) {
        final Session session = new Session(OUTPUT, ERROR_OUTPUT, 
                INPUT, new IPDCommandParser());
        final AccessControl accessControl = new AccessControl();
        final PostSystem postSystem = new PostSystem(accessControl);
        session.addCommandSupplier(new AuthenticationCommandSupplier(accessControl));
        session.addCommandSupplier(new PostCommandSupplier(postSystem));
        session.addCommandSupplier(new Quit(session));
        session.interactive();
    }
}