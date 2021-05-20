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
    private ArrayList<String> parsedAllParameters = new ArrayList<>();
    private ArrayList<String> parsedStringParameters = new ArrayList<>();
    private ArrayList<Integer> parsedNumberParameters = new ArrayList<>();

    /**
     * This is the constructor of this class. It initializes the regex list with
     * some initial regex from the Regex class.
     * 
     * @param parameters the parameters that are to be parsed accepted as a string
     */
    public ParameterParser(String parameters) {
        regexList.add(Regex.POSITIVE_NUMBERS);
        regexList.add(Regex.FIRE_STATION_ID);
        regexList.add(Regex.FIRE_ENGINE);
        regexList.add(Regex.FOREST_STATE);
        regexList.add(Regex.LAKE);
        parse(parameters, Regex.SEPERATORS);
    }

    /**
     * This method gets all parameters which were parsed as a string.
     * 
     * @return Returns a string array with all of the parameters
     */
    public String[] getAllParameters() {
        String[] result = new String[parsedAllParameters.size()];
        int loopVariable = 0;
        for (String parameter : parsedAllParameters) {
            result[loopVariable] = parameter;
            loopVariable++;
        }
        return result;
    }

    /**
     * This method gets all parameters which are represented as a string, which
     * means that numbers are not included.
     * 
     * @return Returns a string array with all of the parameters
     */
    public String[] getStringParameters() {
        String[] result = new String[parsedStringParameters.size()];
        int loopVariable = 0;
        for (String parameter : parsedStringParameters) {
            result[loopVariable] = parameter;
            loopVariable++;
        }
        return result;
    }

    /**
     * This method gets all parameters which are represented as a number, which
     * means that string objects are not included.
     * 
     * @return Returns a string array with all of the parameters
     */
    public int[] getNumberParameters() {
        int[] result = new int[parsedNumberParameters.size()];
        int loopVariable = 0;
        for (int parameter : parsedNumberParameters) {
            result[loopVariable] = parameter;
            loopVariable++;
        }
        return result;
    }

    /**
     * This method is used to parse the user input parameters so that the command
     * can apply and execute them.
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
                    /*
                     * if the parameter is a String and more specifically a fire department and add
                     * it to the string parameters list and to the all parameters list
                     */
                    case (Regex.FIRE_STATION_ID + Regex.POSITIVE_NUMBERS): {
                        parsedStringParameters.add(splitResult);
                        parsedAllParameters.add(splitResult);
                        return;
                    }
                    /*
                     * if the parameter is a positive number add it to the number parameter list and
                     * to the all parameters list
                     */
                    case Regex.POSITIVE_NUMBERS: {
                        parsedNumberParameters.add(Integer.parseInt(splitResult));
                        parsedAllParameters.add(splitResult);
                        return;
                    }
                    // just add to the all parameters list for all the other parameters
                    default: {
                        parsedAllParameters.add(splitResult);
                        parsedStringParameters.add(splitResult);
                    }
                }
            }
        }
    }

    private String[] split(String parameters, String splitByRegex) {
        String[] splitString = parameters.split(splitByRegex);
        return splitString;
    }
}
