package edu.kit.informatik.model;

import edu.kit.informatik.model.constants.Errors;

/**
 * This class checks if all the requirements for the graph are fulfilled.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class GraphRequirements {

    /**
     * This method executes all the private requirements one by one and then returns
     * true if all are fulfilled. Or throws an exception if they are not
     * 
     * @param fromVertices the vertices which have paths that lead away
     * @param toVertices   the vertices which have paths that lead to them
     * @param capacities   the integer value which determine what the maximum
     *                     capacity of the section should be
     * @return Returns true if all the requirements are fulfilled. The exception
     *         handles the rest if the requirements are not fulfilled
     * @throws LogicException an exception that can be thrown by private requirements.
     *                     In the case of an error this method will not be further
     *                     processed
     */
    public boolean formalRequirementsFulfilled(String[] fromVertices, String[] toVertices, int[] capacities)
            throws LogicException {
        boolean hasVertexPair = hasStartAndEndVertex(fromVertices, toVertices);
        boolean hasNoLoops = graphHasNoLoops(fromVertices, toVertices);
        boolean hasNoParallelSections = hasNoParallelSections(fromVertices, toVertices, capacities);
        boolean requirementsFulfilled = false;
        if (hasVertexPair && hasNoLoops && hasNoParallelSections) {
            requirementsFulfilled = true;
        }
        return requirementsFulfilled;
    }

    private boolean hasNoParallelSections(String[] fromVertices, String[] toVertices, int[] capacities)
            throws LogicException {
        boolean hasNoPathsBack = false;
        for (int i = 0; i < fromVertices.length; i++) {
            for (int n = 0; n < toVertices.length; n++) {
                if ((n != i) && fromVertices[i].equals(toVertices[n]) && toVertices[i].equals(fromVertices[n])) {
                    String section = fromVertices[i] + capacities[i] + toVertices[i];
                    String sectionBack = fromVertices[n] + capacities[n] + toVertices[n];
                    throw new LogicException(String.format(Errors.HAS_BACK_SECTION, section, sectionBack));
                }
            }
        }
        hasNoPathsBack = true;
        return hasNoPathsBack;
    }

    /**
     * This method has to be checked separately for a graph because a new added
     * section can have the same section which will just overwrite the already
     * existing section, but a graph cannot.
     * 
     * @param fromVertices the vertices which have paths that lead away
     * @param toVertices   the vertices which have paths that lead to them
     * @param capacities   the integer value which determine what the maximum
     *                     capacity of the section should be
     * @return Returns true if the resulting graph does not have any sections that
     *         double
     * @throws LogicException an exception that can be thrown by private requirements.
     *                     In the case of an error an error message will be thrown
     *                     and the method will not be further processed
     */
    public boolean hasNoDoubledSections(String[] fromVertices, String[] toVertices, int[] capacities)
            throws LogicException {
        boolean hasNoDoubledSections = false;
        for (int i = 0; i < fromVertices.length; i++) {
            for (int n = 0; n < toVertices.length; n++) {
                if ((n != i) && fromVertices[i].equals(fromVertices[n]) && toVertices[i].equals(toVertices[n])) {
                    String section = fromVertices[i] + capacities[i] + toVertices[i];
                    throw new LogicException(String.format(Errors.HAS_DOUBLED_SECTION, section));
                }
            }
        }
        hasNoDoubledSections = true;
        return hasNoDoubledSections;
    }

    private boolean graphHasNoLoops(String[] fromVertices, String[] toVertices) throws LogicException {
        boolean hasNoLoops = true;
        for (int i = 0; i < fromVertices.length; i++) {
            if (fromVertices[i].equals(toVertices[i])) {
                throw new LogicException(String.format(Errors.HAS_LOOPS, fromVertices[i]));
            }
        }
        return hasNoLoops;
    }

    private boolean hasStartAndEndVertex(String[] fromVertices, String[] toVertices) throws LogicException {
        boolean hasStartVertex = false;
        boolean hasEndVertex = false;
        boolean hasBoth = false;

        for (String fromVertex : fromVertices) {
            if (!containsString(fromVertex, toVertices)) {
                hasStartVertex = true;
                break;
            }
        }

        for (String toVertex : toVertices) {
            if (!containsString(toVertex, fromVertices)) {
                hasEndVertex = true;
                break;
            }
        }

        if (hasStartVertex && hasEndVertex) {
            hasBoth = true;
        } else {
            throw new LogicException(Errors.VERTEX_PAIR);
        }

        return hasBoth;
    }

    private boolean containsString(String string, String[] stringArray) {
        boolean hasString = false;
        for (String elementOfArray : stringArray) {
            if (string.equals(elementOfArray)) {
                hasString = true;
                return hasString;
            } else {
                hasString = false;
            }
        }
        return hasString;
    }
}
