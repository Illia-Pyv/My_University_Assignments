package unit_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.kit.informatik.commands.RegexMatcher;
import edu.kit.informatik.model.constants.Regex;

public class RegexMatcherTest {

    private RegexMatcher regMatcher;
    private String regCapacity = Regex.ESCAPE_SECTION_CAPACITY;
    private String regEscapeSection = Regex.ESCAPE_SECTION;
    private String regGraph = Regex.GRAPH;
    
    @Before
    public void init() {
        regMatcher = new RegexMatcher();
    }

    @Test
    public void matchesMaxCapacityRegexTest() {
        assertEquals(regMatcher.checkIfMatches("12205863", regCapacity),
                regMatcher.checkIfMatches("12205863", regCapacity));
        assertEquals(true, regMatcher.checkIfMatches("12205863", regCapacity));
        assertEquals(true, regMatcher.checkIfMatches("2147483647", regCapacity));
        assertEquals(false, regMatcher.checkIfMatches("2147483648", regCapacity));

        for (int i = 2147483000; i < 2147483647; i++) {
            System.out.println(i + 1);
            String input = i + "";
            assertEquals(true, regMatcher.checkIfMatches(input, regCapacity));
        }
        
        assertEquals(false, regMatcher.checkIfMatches("e10b", regCapacity));
    }
    
    @Test
    public void matchesEscapeSectionRegexTest() {
        assertEquals(regMatcher.checkIfMatches("aok9836o;owi88eiui", regEscapeSection),
                regMatcher.checkIfMatches("aok9836o;owi88eiui", regEscapeSection));
        assertEquals(true, regMatcher.checkIfMatches("aok9836o", regEscapeSection));
        assertEquals(true, regMatcher.checkIfMatches("ouwi2147483647uiz", regEscapeSection));
        assertEquals(false, regMatcher.checkIfMatches("ouwi2147483648uiz", regEscapeSection));
        assertEquals(false, regMatcher.checkIfMatches("ouwi2147483647uiz;", regEscapeSection));
        assertEquals(false, regMatcher.checkIfMatches("aok9836o;owi88eiui;ouwi2147483647uiz;", regEscapeSection));
    }  
    
    @Test
    public void matchesGraphRegexTest() {
        assertEquals(regMatcher.checkIfMatches("aok9836o;owi88eiui", regGraph),
                regMatcher.checkIfMatches("aok9836o;owi88eiui", regGraph));
        assertEquals(true, regMatcher.checkIfMatches("aok9836o;owi88eiui", regGraph));
        assertEquals(true, regMatcher.checkIfMatches("aok9836o;owi88eiui;ouwi2147483647uiz", regGraph));
        assertEquals(false, regMatcher.checkIfMatches("aok9836o;owi88eiui;ouwi2147483647uiz;", regGraph));
    }
    
}
