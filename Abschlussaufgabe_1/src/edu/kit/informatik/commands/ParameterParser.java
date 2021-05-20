package edu.kit.informatik.commands;

import java.util.ArrayList;

import edu.kit.informatik.model.constants.Regex;

/**
 * This class parses the user input parameters for further usage in the program.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class ParameterParser {

    private RegexMatcher matcher = new RegexMatcher();
    private ArrayList<String> regexList = new ArrayList<>();
    private ArrayList<String> fromVertices = new ArrayList<>();
    private ArrayList<String> toVertices = new ArrayList<>();
    private ArrayList<String> capacity = new ArrayList<>();
    private String networkIdentifier = null;

    /**
     * This is the constructor of this class. It initializes the regex list with
     * some initial regex from the Regex class.
     * 
     * @param parameters the parameters that are to be parsed accepted as a string
     */
    public ParameterParser(String parameters) {
        regexList.add(Regex.NETWORK_IDENTIFIER);
        regexList.add(Regex.VERTEX_IDENTIFIER);
        regexList.add(Regex.ESCAPE_SECTION_CAPACITY);
        parse(parameters, Regex.SEPERATORS);
    }

    /**
     * This method gets the parsed network identifier and returns it as a string.
     * 
     * @return Returns the network identifier
     */
    public String getNetworkIdentifier() {
        return this.networkIdentifier;
    }

    /**
     * This method gets all the vertex identifiers from which a path leads away.
     * 
     * @return Returns all the vertex identifiers from which a path leads away as a
     *         string array
     */
    public String[] getFromVertices() {
        String[] result = new String[fromVertices.size()];
        for (int i = 0; i < fromVertices.size(); i++) {
            result[i] = fromVertices.get(i);
        }
        return result;
    }

    /**
     * This method gets all the vertex identifiers to which a path leads to
     * 
     * @return Returns all the vertex identifiers to which a path leads to as a
     *         string array
     */
    public String[] getToVertices() {
        String[] result = new String[toVertices.size()];
        for (int i = 0; i < toVertices.size(); i++) {
            result[i] = toVertices.get(i);
        }
        return result;
    }

    /**
     * This method gets the parsed capacity for each escape route section.
     * 
     * @return Returns the capacity for each escape section as an integer array
     */
    public int[] getCapacities() {
        int[] result = new int[capacity.size()];
        for (int i = 0; i < capacity.size(); i++) {
            result[i] = Integer.parseInt(capacity.get(i));
        }
        return result;
    }

    /**
     * This method is used to parse the user input parameters so that the command
     * can apply them onto the network logic commands and execute them.
     * 
     * @param parameters the user input parameters that have to be parsed. They are
     *                   accepted as a string
     * @param regex      the regex by which the parameters should be parsed
     */
    private void parse(String parameters, String regex) {
        for (String splitResult : split(parameters, regex)) {
            if (splitResult.equals(Regex.SPACE) || splitResult.equals("")) {
                continue;
            } else {
                match(splitResult);
            }
        }
    }

    private void match(String splitResult) {
        for (int i = 0; i < regexList.size(); i++) {
            if (matcher.checkIfMatches(splitResult, regexList.get(i))) {
                switch (regexList.get(i)) {
                    // if the parsed string is a network identifier then store it
                    case Regex.NETWORK_IDENTIFIER: {
                        this.networkIdentifier = splitResult;
                        return;
                    }
                    // if the parsed string is a vertex identifier then store it alternating in one
                    // of the array lists
                    case Regex.VERTEX_IDENTIFIER: {
                        if (fromVertices.size() == toVertices.size()) {
                            fromVertices.add(splitResult);
                        } else {
                            toVertices.add(splitResult);
                        }
                        return;
                    }
                    // if the parsed strting equals the regex of the capacity of an escape section
                    // then store it in an array list
                    case Regex.ESCAPE_SECTION_CAPACITY: {
                        this.capacity.add(splitResult);
                        return;
                    }
                    default: {
                        /* does nothing */
                    }
                }

                // if the parsed string equals nothing of the above than parse again
            } else if (i == regexList.size() - 1) {
                parse(splitResult, Regex.ESCAPE_SECTION_CAPACITY);
                parse(splitResult, Regex.VERTEX_IDENTIFIER);
            }
        }
    }

    private String[] split(String parameters, String splitByRegex) {
        String[] splitString = parameters.split(splitByRegex);
        return splitString;
    }
}
