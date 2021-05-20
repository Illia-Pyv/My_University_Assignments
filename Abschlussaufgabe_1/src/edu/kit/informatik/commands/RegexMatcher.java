package edu.kit.informatik.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class checks whether the passed parameters match the regex.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class RegexMatcher {
    private Pattern pattern;
    private Matcher match;
    
    /**
     * This method tries to match an input with a specific regex.
     * 
     * @param input the parameters that should be matched to the regex
     * @param regex the regex which is used to match the input
     * @return Returns true if input matches the regex and false if it does not
     */
    public boolean checkIfMatches(String input, String regex) {
        boolean isMatching = false;
        pattern = Pattern.compile(regex);
        match = pattern.matcher(input);
        if (match.matches()) {
            isMatching = true;
        }
        return isMatching;
    }
}
