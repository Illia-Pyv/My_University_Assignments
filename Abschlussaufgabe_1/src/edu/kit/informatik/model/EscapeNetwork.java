package edu.kit.informatik.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class stores the start vertex as well as the end vertex where the people
 * should be brought to safety. It also stores the graph of all escape routes
 * that are possible.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class EscapeNetwork {

    private ArrayList<Vertex> startVertex = new ArrayList<>();
    private ArrayList<Vertex> endVertex = new ArrayList<>();
    private HashMap<VertexPair, Long> maximumFlowMap = new HashMap<>();
    private Graph graph = new Graph();

    /**
     * This method gets the start vertex list of this escape network list.
     * 
     * @return Returns the list of all start vertices
     */
    public ArrayList<Vertex> getStartVerticesList() {
        return this.startVertex;
    }

    /**
     * This method gets the end vertex list of this escape network list.
     * 
     * @return Returns the list of all end vertices
     */
    public ArrayList<Vertex> getEndVerticesList() {
        return this.endVertex;
    }

    /**
     * This method gets the maximum flow map from this network.
     * 
     * @return Returns the maximum flow map as a map object
     */
    public HashMap<VertexPair, Long> getMaximumFlowMap() {
        return this.maximumFlowMap;
    }

    /**
     * This method sets a new entry for the calculated maximum flow from a start
     * vertex to an end vertex.
     * 
     * @param start       the start vertex of the vertex pair to which the maximum
     *                    flow is attributed
     * @param end         the end vertex of the vertex pair to which the maximum
     *                    flow is attributed
     * @param maximumFlow the calculated maximum flow which is to be stored
     */
    public void setMaximumFlow(Vertex start, Vertex end, long maximumFlow) {
        this.maximumFlowMap.put(new VertexPair(start, end), maximumFlow);
    }

    /**
     * This method gets an already calculated flow from two vertices. If the flow
     * from a start vertex to an end vertex is not stored in the map then 0 is
     * returned.
     * 
     * @param start the start vertex of the vertex pair from which the flow should
     *              be gotten
     * @param end   the end vertex of the vertex pair from which the flow should be
     *              gotten
     * @return Returns the flow for the two vertices in the parameters
     */
    public long getMaximumFlow(Vertex start, Vertex end) {
        for (VertexPair pair : maximumFlowMap.keySet()) {
            if (pair.getStartVertex().equals(start) && pair.getEndVertex().equals(end)) {
                return maximumFlowMap.get(pair);
            }
        }
        return 0;
    }

    /**
     * This method checks if the list that contains the already calculated flows of
     * the graph, is empty or not.
     * 
     * @return Returns true if the list is empty and returns false if the list is
     *         not empty
     */
    public boolean isEmptyMaximumFlowList() {
        if (maximumFlowMap.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method assigns a capacity to an escape route section that represents the
     * maximum number of people that can use an escape route section per minute.
     * 
     * @param section       the escape route section to which the capacity value
     *                      should be assigned
     * @param capacityValue the integer value which describes how many people can
     *                      use an escape route section per minute
     * @return Returns the same escape route section but now with an assigned
     *         capacity
     */
    public EscapeRouteSection assignCapacity(EscapeRouteSection section, int capacityValue) {
        int capacity = capacityValue;
        section.setCapacityValue(capacity);
        return section;
    }

    /**
     * This method adds an escape route section to the system.
     * 
     * @param fromVertexId the vertex from which the path leads away
     * @param toVertexId   the vertex to which the path leads to
     * @param capacity     the capacity which represents the maximum amount of
     *                     people per minute can pass an escape route section
     */
    public void addSection(String fromVertexId, String toVertexId, int capacity) throws LogicException {
        sectionFulfillsRequirements(fromVertexId, toVertexId, capacity);
        addToGraph(fromVertexId, toVertexId, capacity);
        maximumFlowMap.clear();
        setStartEndVertices();
    }

    /**
     * This method adds a graph to the network.
     * 
     * @param fromVerticesId the identifiers of the vertices from which a path leads
     *                       away
     * @param toVerticesId   the identifiers of the vertices from which a path leads
     *                       to
     * @param capacities     the capacities that are later assigned to an escape
     *                       route sections
     */
    public void addGraph(String[] fromVerticesId, String[] toVerticesId, int[] capacities) {
        for (int i = 0; i < fromVerticesId.length; i++) {
            addToGraph(fromVerticesId[i], toVerticesId[i], capacities[i]);
        }
        setStartEndVertices();
    }

    /**
     * This method gets the graph of this network.
     * 
     * @return Returns the object graph of this network
     */
    public Graph getGraph() {
        return this.graph;
    }

    /**
     * This method gets the amount of vertices in the graph.
     * 
     * @return Returns the amount of vertices as an integer
     */
    public int getAmountOfVertices() {
        return graph.getAmountOfVertices();
    }

    private void addToGraph(String fromVertexId, String toVertexId, int capacity) {
        graph.addVertices(fromVertexId, toVertexId);
        EscapeRouteSection section = new EscapeRouteSection(graph.getVertex(fromVertexId), graph.getVertex(toVertexId));
        graph.addEscapeSection(assignCapacity(section, capacity));
    }

    private void setStartEndVertices() {
        startVertex.clear();
        endVertex.clear();

        for (Vertex vertex : graph.getStartVertex()) {
            startVertex.add(vertex);
        }

        for (Vertex vertex : graph.getEndVertex()) {
            endVertex.add(vertex);
        }
    }

    // adds a new section to the graph
    private boolean sectionFulfillsRequirements(String fromVertexId, String toVertexId, int capacity)
            throws LogicException {
        GraphRequirements requirements = new GraphRequirements();
        ArrayList<EscapeRouteSection> list = graph.getSectionList();
        String[] fromVertexIds = new String[list.size() + 1];
        String[] toVertexIds = new String[list.size() + 1];
        int[] capacities = new int[list.size() + 1];
        /*
         * separates all the sections from the already existing graph into the from
         * vertices, end vertices and the capacities of each section
         */
        for (int i = 0; i < list.size(); i++) {
            fromVertexIds[i] = list.get(i).getFromVertex().getVertexId();
            toVertexIds[i] = list.get(i).getToVertex().getVertexId();
            capacities[i] = list.get(i).getCapacity();
        }
        // add the new section
        fromVertexIds[fromVertexIds.length - 1] = fromVertexId;
        toVertexIds[toVertexIds.length - 1] = toVertexId;
        capacities[capacities.length - 1] = capacity;
        /*
         * checks if the graph still fulfills the formal graph requirements after adding
         * the new section
         */
        return requirements.formalRequirementsFulfilled(fromVertexIds, toVertexIds, capacities);
    }
}
