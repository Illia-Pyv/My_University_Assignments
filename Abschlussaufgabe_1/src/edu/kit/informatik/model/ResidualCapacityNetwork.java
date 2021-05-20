package edu.kit.informatik.model;

import java.util.ArrayList;

/**
 * This class represents the residual capacity network which contains the escape
 * route sections there and back to calculate the residual capacity for all escape
 * route sections.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class ResidualCapacityNetwork {

    private ArrayList<EscapeRouteSection> temporaryList = new ArrayList<>();
    private ArrayList<EscapeRouteSection> allSections = new ArrayList<>();
    private ArrayList<EscapeRouteSection> residualCapacityGraph = new ArrayList<>();

    /**
     * This is the constructor of this class which initializes the residual capacity
     * network with both forward and backward sections which are essential for the
     * residual capacity network.
     * 
     * @param graph the graph which this residual capacity graph should take the
     */
    public ResidualCapacityNetwork(Graph graph) {
        setAllSections(graph.getSectionList(), setBackwardSections(graph.getSectionList()));
        initializeAllCurrentFlows();
    }

    /**
     * This method sets the current flow of a section and its backward section to
     * the current flow value.
     * 
     * @param section          the section and its backward counter section whose
     *                         current flow should be set
     * @param currentFlowValue the value that the current flow of the sections
     *                         should be set as
     */
    public void updateCurrentFlow(EscapeRouteSection section, int currentFlowValue) {
        // Search for the backward section partner of the section from the section that
        // was passed.
        for (EscapeRouteSection someSection : allSections) {
            if ((section.getFromVertex().equals(someSection.getToVertex()))
                    && (section.getToVertex().equals(someSection.getFromVertex()))) {
                // set the current flow for both section there and section back
                int newFlow = section.getCurrentFlow() + currentFlowValue;
                section.setCurrentFlow(newFlow);
                int newBackFlow = someSection.getCurrentFlow() - currentFlowValue;
                someSection.setCurrentFlow(newBackFlow);
                return;
            }
        }
    }

    /**
     * This method determines and returns the list of escape route sections which is
     * also known as the residual capacity graph.
     * 
     * <p>
     * All the possible escape route sections are calculated through analyzing the
     * residual capacity of every section in the residual capacity graph. If it is
     * greater than 0, then the section will be included in the residual capacity
     * graph.
     * 
     * @return Returns the residual capacity graph as a list of possible escape
     *         route sections that you can take
     */
    public ArrayList<EscapeRouteSection> determineResidualCapcityGraph() {

        if (residualCapacityGraph.isEmpty()) {
            initializeResidualCapacityGraph();
        }

        temporaryList.clear();
        for (EscapeRouteSection section : residualCapacityGraph) {
            if (calculateResidualCapacity(section) == 0) {
                temporaryList.add(section);
            }
        }
        
        for (EscapeRouteSection section : temporaryList) {
            residualCapacityGraph.remove(section);
        }

        temporaryList.clear();
        for (EscapeRouteSection section : allSections) {
            if ((calculateResidualCapacity(section) > 0) && !residualCapacityGraph.contains(section)) {
                temporaryList.add(section);
            }
        }

        for (EscapeRouteSection section : temporaryList) {
            residualCapacityGraph.add(section);
        }
        return residualCapacityGraph;
    }

    /**
     * This method gets every escape route section that leads to the end vertex.
     * 
     * @param endVertex the vertex from which no escape route sections go out of
     * @return Returns all escape route sections that lead to the end vertex as an
     *         escape route section list
     */
    public ArrayList<EscapeRouteSection> getAllSectionsToEndVertex(Vertex endVertex) {
        ArrayList<EscapeRouteSection> sectionsToEndVertex = new ArrayList<>();
        for (EscapeRouteSection section : allSections) {
            if (section.getToVertex().equals(endVertex)) {
                sectionsToEndVertex.add(section);
            }
        }
        return sectionsToEndVertex;
    }

    private ArrayList<EscapeRouteSection> setBackwardSections(ArrayList<EscapeRouteSection> graph) {
        ArrayList<EscapeRouteSection> resultSections = new ArrayList<>();

        /*
         * Adds a new section to the residual capacity graph with switched vertices and
         * initialized capacities with the value 0. The switched vertices do the job of
         * turning the section into the opposite direction which creates a section that
         * points backwards.
         */
        for (EscapeRouteSection section : graph) {
            EscapeRouteSection newBackwardSection = new EscapeRouteSection(section.getToVertex(),
                    section.getFromVertex());
            newBackwardSection.setCapacityValue(0);
            resultSections.add(newBackwardSection);
        }
        return resultSections;
    }

    private void setAllSections(ArrayList<EscapeRouteSection> forwardSections,
            ArrayList<EscapeRouteSection> backwardSections) {
        for (EscapeRouteSection section : forwardSections) {
            allSections.add(section);
        }
        for (EscapeRouteSection section : backwardSections) {
            allSections.add(section);
        }
    }

    private void initializeAllCurrentFlows() {
        for (EscapeRouteSection section : allSections) {
            section.setCurrentFlow(0);
        }
    }

    private ArrayList<EscapeRouteSection> initializeResidualCapacityGraph() {
        for (EscapeRouteSection section : allSections) {
            residualCapacityGraph.add(section);
        }
        return residualCapacityGraph;
    }

    /**
     * This method calculates the residual capacity of a section.
     * 
     * @param section the section for which the residual capacity should be
     *                calculated
     * @return Returns the residual capacity of a section as an integer value
     */
    public int calculateResidualCapacity(EscapeRouteSection section) {
        int residualCapacity = section.getCapacity() - section.getCurrentFlow();
        return residualCapacity;
    }

    /**
     * This method gets an escape route section by the vertices that make up an
     * escape route section.
     * 
     * @param fromVertex the vertex from which the escape route section leads away
     * @param toVertex   the vertex to which the escape route section leads to
     * @return Returns the object escape route section
     */
    public EscapeRouteSection getSection(Vertex fromVertex, Vertex toVertex) {
        for (EscapeRouteSection section : allSections) {
            if ((section.getFromVertex().equals(fromVertex)) && (section.getToVertex().equals(toVertex))) {
                return section;
            }
        }
        return null;
    }
}
