package public_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.kit.informatik.ioProcessing.InvalidCommandException;
import edu.kit.informatik.ioProcessing.InvalidParametersException;
import edu.kit.informatik.ioProcessing.RuntimeManager;

public class PublicTests {
    RuntimeManager mgr;

    @Before
    public void initialize() {
        mgr = new RuntimeManager();
    }

    @Test
    public void publicTest() throws InvalidParametersException, InvalidCommandException {
//        assertEquals("Added new escape network with identifier ABC.",
//                mgr.processIO("add ABC a4b;a2c;b1d;b3c;c6d"));
//        assertEquals("ABC 4", mgr.processIO("list"));
//        assertEquals("a4b\na2c\nb3c\nb1d\nc6d", mgr.processIO("print ABC"));
//        assertEquals("6", mgr.processIO("flow ABC a d"));
//        assertEquals("6 a d", mgr.processIO("list ABC"));
//        assertEquals("Added new section a5d to escape network ABC.", mgr.processIO("add ABC a5d"));
//        assertEquals("a4b\na2c\na5d\nb3c\nb1d\nc6d", mgr.processIO("print ABC"));
//        assertEquals("EMPTY", mgr.processIO("list ABC"));
//        assertEquals("Added new escape network with identifier XYZ.",
//                mgr.processIO("add XYZ a10b;a10c;b2c;b4d;b8e;c9e;d10f;e10f;e6d"));
//        assertEquals("XYZ 6\nABC 4", mgr.processIO("list"));
//        assertEquals("a10b\na10c\nb2c\nb4d\nb8e\nc9e\nd10f\ne6d\ne10f", mgr.processIO("print XYZ"));
//        assertEquals("19", mgr.processIO("flow XYZ a f"));
//        assertEquals("19 a f", mgr.processIO("list XYZ"));
    }

}
