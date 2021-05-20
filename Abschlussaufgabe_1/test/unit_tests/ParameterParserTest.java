package unit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.commands.ParameterParser;

public class ParameterParserTest {

    ParameterParser parser;

    @Test
    public void parserTest() {
        parser = new ParameterParser("ABC alskdj10b");
        assertEquals("alskdj", parser.getFromVertices()[0]);
        assertEquals("b", parser.getToVertices()[0]);
        assertEquals("ABC", parser.getNetworkIdentifier());
        assertEquals(10, parser.getCapacities()[0]);
        System.out.println("\ntest1:\n" + parser.getNetworkIdentifier() + "\n" + parser.getFromVertices()[0] + "\n"
                + parser.getToVertices()[0] + "\n" + parser.getCapacities()[0]);
    }

    @Test
    public void parserTest2() {
        parser = new ParameterParser("ABC");
        assertEquals("ABC", parser.getNetworkIdentifier());
        System.out.println("\ntest2:\n" + parser.getNetworkIdentifier());
    }

    @Test
    public void parserTest3() {
        parser = new ParameterParser("a205567b");
        assertEquals("a", parser.getFromVertices()[0]);
        assertEquals("b", parser.getToVertices()[0]);
        System.out.println("\ntest3:\n" + parser.getFromVertices()[0] + "\n" + parser.getToVertices()[0]);
    }

    @Test
    public void parserTest4() {
        parser = new ParameterParser("a205567b");
        assertEquals(205567, parser.getCapacities()[0]);
        System.out.println("\ntest4:\n" + parser.getCapacities()[0]);
    }

    @Test
    public void parserTest5() {
        String parameter = "ABC a5k;b2c";
        parser = new ParameterParser(parameter);
        String[] fromVertexArray = { "a", "b" };
        String[] toVertexArray = { "k", "c" };
        int[] capacityArray = { 5, 2 };

        assertEquals("ABC", parser.getNetworkIdentifier());
        System.out.println("\ntest5:\n" + parameter + "\n");
        for (int i = 0; i < parser.getFromVertices().length; i++) {
            assertEquals(fromVertexArray[i], parser.getFromVertices()[i]);
            assertEquals(toVertexArray[i], parser.getToVertices()[i]);
            assertEquals(capacityArray[i], parser.getCapacities()[i]);
            System.out.println(String.format("%s => fromVertex: %s, toVertex: %s, capacity: %s",
                    parser.getNetworkIdentifier(), parser.getFromVertices()[i],
                    parser.getToVertices()[i], parser.getCapacities()[i]));
        }
    }

}
