package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import edu.kit.informatik.commands.CommandAdd;

public class CommandAddTest {

    CommandAdd add;

    @Before
    public void init() throws Exception {
        add = new CommandAdd();
    }

    @Test
    public void matchesRegexAddEscapeSectionTest() {
        assertEquals(add.matchesRegex("ABC f5k"), add.matchesRegex("ABC f5k"));
        assertEquals(1, add.matchesRegex("ABC f5k"));

        // everything that can go wrong
        assertEquals(-1, add.matchesRegex("ACZTRED f5k"));
        assertEquals(-1, add.matchesRegex("5ABC f5k"));
        assertEquals(-1, add.matchesRegex("ABC5 f5k"));
        assertEquals(-1, add.matchesRegex("AB5C f5k"));
        assertEquals(-1, add.matchesRegex("ABCf5k"));
        assertEquals(-1, add.matchesRegex("ABC f5k;"));
        assertEquals(-1, add.matchesRegex("ABC f;5k"));
        assertEquals(-1, add.matchesRegex("ABC;f5k"));

        assertEquals(-1, add.matchesRegex("ABC f5k ABC f5k"));
    }

    @Test
    public void matchesRegexAddNetworkTest() {
        assertEquals(add.matchesRegex("ABC f5k;oiw88399id;ow8o;ow88w"),
                add.matchesRegex("ABC f5k;oiw88399id;ow8o;ow88w"));
        assertEquals(0, add.matchesRegex("ABC f5k;oiw88399id;ow8o;ow88w"));

        // everything that can go wrong
        assertEquals(-1, add.matchesRegex("ACZTRED f5k;oiw3889id;owe883l"));
        assertEquals(-1, add.matchesRegex("5ABC f5k;oiw88399id;ow8o;ow88w"));
        assertEquals(-1, add.matchesRegex("ABC5 f5k;oiw88399id;ow8o;ow88w"));
        assertEquals(-1, add.matchesRegex("AB5C f5k;oiw88399id;ow8o;ow88w"));
        assertEquals(-1, add.matchesRegex("ABCf5k;oiw88399id;ow8o;ow88w"));
        assertEquals(-1, add.matchesRegex("ABC f5k;oiw88399id;ow8o;ow88w;"));
        assertEquals(-1, add.matchesRegex("ABC f;5k;oiw88399id;ow8o;ow88w"));
        assertEquals(-1, add.matchesRegex("ABC;f5k;oiw88399id;ow8o;ow88w"));

        assertEquals(-1, add.matchesRegex("ABC f5k;oiw88399id;ow8o;ow88w ABC f5k;oiw88399id;ow8o;ow88w"));
    }
}
