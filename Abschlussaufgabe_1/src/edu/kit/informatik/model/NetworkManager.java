package edu.kit.informatik.model;

import java.util.ArrayList;
import java.util.HashMap;

import edu.kit.informatik.model.constants.Errors;

/**
 * This class manages all of the escape networks that exist in this system.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class NetworkManager {

    private static final int COLUMNS_IN_RESULT_ARRAY = 2;
    private static final int COLUMNS_IN_PRINT_AND_LIST = 3;
    private HashMap<String, EscapeNetwork> networkMap = new HashMap<>();
    private InsertionSort sorter = new InsertionSort();

    /**
     * This method adds a new network to the system if the graph fulfills all the
     * requirements needed.
     * 
     * @param networkId      the name of the network which it identifies with
     * @param fromVerticesId the parsed vertices from the user input
     * @param toVerticesId   the parsed vertices from the user input
     * @param capacities     the parsed capacities from the user input
     * @throws LogicException this exception is thrown if an error occurred when
     *                        adding a new network
     */
    public void addNewNetwork(String networkId, String[] fromVerticesId, String[] toVerticesId, int[] capacities)
            throws LogicException {

        if (networkMap.containsKey(networkId)) {
            throw new LogicException(String.format(Errors.NETWORK_EXISTS_ALREADY, networkId));
        }

        GraphRequirements requirements = new GraphRequirements();

        if (requirements.formalRequirementsFulfilled(fromVerticesId, toVerticesId, capacities)
                && requirements.hasNoDoubledSections(fromVerticesId, toVerticesId, capacities)) {
            EscapeNetwork network = new EscapeNetwork();
            networkMap.put(networkId, network);
            network.addGraph(fromVerticesId, toVerticesId, capacities);
        }
    }

    /**
     * This method adds a new section to an already existing network.
     * 
     * @param networkIdentifier the name of the network which it identifies with
     * @param fromVertex        the parsed vertices from the user input
     * @param toVertex          the parsed vertices from the user input
     * @param capacity          the parsed capacities from the user input
     * @throws LogicException this exception is thrown if an error occurred when
     *                        adding a new section to the graph of an already
     *                        existing network
     */
    public void addNewSection(String networkIdentifier, String fromVertex, String toVertex, int capacity)
            throws LogicException {
        if (networkMap.containsKey(networkIdentifier)) {
            networkMap.get(networkIdentifier).addSection(fromVertex, toVertex, capacity);
        } else {
            throw new LogicException(String.format(Errors.NO_SUCH_NETWORK, networkIdentifier));
        }
    }

    /**
     * This method sorts the sections ascending by the vertex where the path leads
     * away and then ascending by the vertex where the path leads to.
     * 
     * @param networkId the identifier of the network from which the sections should
     *                  be printed
     * @return Returns a multidimensional string array with all the sections stored
     *         and sorted
     * @throws LogicException this exception is thrown if an error occurred when
     *                        executing this command
     */
    public String[][] print(String networkId) throws LogicException {
        EscapeNetwork network;
        if (networkMap.containsKey(networkId)) {
            network = networkMap.get(networkId);
        } else {
            throw new LogicException(String.format(Errors.NO_SUCH_NETWORK, networkId));
        }

        Graph graph = network.getGraph();
        ArrayList<String> seen = new ArrayList<>();
        ArrayList<EscapeRouteSection> sortedList = new ArrayList<>();
        String[] fromVertexIds = new String[graph.getSectionList().size()];
        String[] toVertexIds = new String[graph.getSectionList().size()];
        int[] capacities = new int[graph.getSectionList().size()];
        String[][] result = new String[graph.getSectionList().size()][COLUMNS_IN_PRINT_AND_LIST];

        int loopVariable = 0;
        for (EscapeRouteSection section : graph.getSectionList()) {
            fromVertexIds[loopVariable] = section.getFromVertex().getVertexId();
            loopVariable++;
        }
        sorter.sortAscending(fromVertexIds);
        for (String from : fromVertexIds) {
            if (!seen.contains(from)) {
                ArrayList<EscapeRouteSection> thisSectionList = new ArrayList<>();
                String[] toVertices;
                for (EscapeRouteSection section : graph.getSectionList()) {
                    if (from.equals(section.getFromVertex().getVertexId())) {
                        thisSectionList.add(section);
                    }
                }
                toVertices = new String[thisSectionList.size()];
                int thisSectionListSize = thisSectionList.size();
                for (int i = 0; i < thisSectionListSize; i++) {
                    toVertices[i] = thisSectionList.get(i).getToVertex().getVertexId();
                }
                sorter.sortAscending(toVertices);
                for (String vertex : toVertices) {
                    for (EscapeRouteSection section : thisSectionList) {
                        if (vertex.equals(section.getToVertex().getVertexId())) {
                            sortedList.add(section);
                            break;
                        }
                    }
                }
                seen.add(from);
            }
        }
        loopVariable = 0;
        for (EscapeRouteSection section : sortedList) {
            toVertexIds[loopVariable] = section.getToVertex().getVertexId();
            capacities[loopVariable] = section.getCapacity();
            loopVariable++;
        }

        for (int i = 0; i < fromVertexIds.length; i++) {
            result[i][0] = fromVertexIds[i];
            result[i][1] = String.format("%s", capacities[i]);
            result[i][2] = toVertexIds[i];
        }

        return result;
    }

    /**
     * This method sorts all the networks in the system and returns them.
     * 
     * @return Returns a sorted two dimensional string array of all the networks and
     *         their vertex count in the system.
     */
    public String[][] list() {
        String[][] result = new String[networkMap.size()][COLUMNS_IN_RESULT_ARRAY];
        String[] keys = new String[networkMap.size()];
        long[] amountOfVertices = new long[networkMap.size()];
        int loopVariable = 0;

        if (networkMap.isEmpty()) {
            return null;
        }

        for (String key : networkMap.keySet()) {
            keys[loopVariable] = key;
            loopVariable++;
        }
        sorter.sortAscending(keys);
        for (int i = 0; i < keys.length; i++) {
            amountOfVertices[i] = (long) networkMap.get(keys[i]).getAmountOfVertices();
        }
        sorter.sortDescending(amountOfVertices);
        loopVariable = 0;
        for (long amount : amountOfVertices) {
            for (int i = 0; i < keys.length; i++) {
                if (networkMap.containsKey(keys[i]) && networkMap.get(keys[i]).getAmountOfVertices() == amount) {
                    result[loopVariable][0] = keys[i];
                    keys[i] = "";
                    result[loopVariable][1] = String.format("%s", amount);
                    loopVariable++;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This method lists all the already calculated flows from a start vertex to an
     * end vertex.
     * 
     * @param networkId the identifier of a network by which the right network is
     *                  gotten from the map
     * @return Returns a multidimensional string array which contains the calculated
     *         flows as well as the vertices or null if no flows were calculated
     */
    public String[][] list(String networkId) throws LogicException {
        EscapeNetwork network;
        if (networkMap.containsKey(networkId)) {
            network = networkMap.get(networkId);
        } else {
            throw new LogicException(String.format(Errors.NO_SUCH_NETWORK, networkId));
        }

        if (network.isEmptyMaximumFlowList()) {
            return null;
        }

        HashMap<VertexPair, Long> flowMap = network.getMaximumFlowMap();
        String[][] result = new String[flowMap.size()][COLUMNS_IN_PRINT_AND_LIST];
        String[] startVertexIds = new String[flowMap.size()];
        String[] endVertexIds = new String[flowMap.size()];
        long[] flowValues = new long[flowMap.size()];

        int loopVariable = 0;
        for (VertexPair pair : flowMap.keySet()) {
            flowValues[loopVariable] = network.getMaximumFlow(pair.getStartVertex(), pair.getEndVertex());
            loopVariable++;
        }
        sorter.sortAscending(flowValues);
        loopVariable = 0;
        for (VertexPair pair : sortList(network, flowMap, flowValues)) {
            startVertexIds[loopVariable] = pair.getStartVertex().getVertexId();
            endVertexIds[loopVariable] = pair.getEndVertex().getVertexId();
            loopVariable++;
        }

        for (int i = 0; i < flowValues.length; i++) {
            result[i][0] = String.format("%s", flowValues[i]);
            result[i][1] = startVertexIds[i];
            result[i][2] = endVertexIds[i];
        }

        return result;
    }

    private ArrayList<VertexPair> sortList(EscapeNetwork network, HashMap<VertexPair, Long> flowMap,
            long[] flowValues) {
        ArrayList<VertexPair> allKeys = new ArrayList<>();
        ArrayList<VertexPair> result = new ArrayList<>();
        ArrayList<Long> seenNumber = new ArrayList<>();
        ArrayList<String> seenVertex = new ArrayList<>();
        for (long value : flowValues) {
            seenVertex.clear();
            if (!seenNumber.contains(value)) {
                ArrayList<VertexPair> pairList = new ArrayList<>();
                String[] startVertices;
                for (VertexPair pair : flowMap.keySet()) {
                    if (value == network.getMaximumFlow(pair.getStartVertex(), pair.getEndVertex())) {
                        pairList.add(pair);
                    }
                }
                startVertices = new String[pairList.size()];
                int pairListSize = pairList.size();
                for (int i = 0; i < pairListSize; i++) {
                    startVertices[i] = pairList.get(i).getStartVertex().getVertexId();
                }
                sorter.sortAscending(startVertices);
                for (String vertex : startVertices) {
                    for (VertexPair pair : pairList) {
                        if (vertex.equals(pair.getStartVertex().getVertexId()) && !allKeys.contains(pair)) {
                            allKeys.add(pair);
                            break;
                        }
                    }
                }
                String[] endVertices;
                for (VertexPair secondPair : allKeys) {
                    if (!seenVertex.contains(secondPair.getStartVertex().getVertexId())) {
                        endVertices = new String[pairList.size()];
                        for (int i = 0; i < pairListSize; i++) {
                            endVertices[i] = pairList.get(i).getEndVertex().getVertexId();
                        }
                        sorter.sortAscending(endVertices);
                        for (String vertex : endVertices) {
                            for (VertexPair pair : pairList) {
                                if (vertex.equals(pair.getEndVertex().getVertexId())) {
                                    result.add(pair);
                                    seenNumber.add(value);
                                    break;
                                }
                            }
                        }
                    }
                    seenVertex.add(secondPair.getStartVertex().getVertexId());
                }
                seenNumber.add(value);
            }
        }
        return result;
    }
    
    /**
     * This method calculates the flow from the user specified start and end
     * vertices in the network.
     * 
     * @param networkId the identifier of the network from which the graphs flow
     *                  should be calculated
     * @param startId   the identifier of the start vertex of the graph from where
     *                  the flow calculation begins
     * @param endId     the identifier of the end vertex of the graph where the flow
     *                  calculation should stop
     * @return Returns a number of type {@code long} which represents the flow
     *         between a valid start and a valid end vertex in the graph
     * @throws LogicException this exception is thrown if an error occurred when
     *                        calculating the flow
     */
    public long flow(String networkId, String startId, String endId) throws LogicException {
        EscapeNetwork network;
        // if the network does not exist then throw an exception
        if (networkMap.containsKey(networkId)) {
            network = networkMap.get(networkId);
        } else {
            throw new LogicException(String.format(Errors.NO_SUCH_NETWORK, networkId));
        }

        Vertex start = null;
        Vertex end = null;
        boolean validStartVertex = false;
        boolean validEndVertex = false;

        // check if such a start vertex exists in the graph
        for (Vertex startVertex : network.getStartVerticesList()) {
            if (startVertex.getVertexId().equals(startId)) {
                validStartVertex = true;
                start = startVertex;
            }
        }

        // check if such an end vertex exists in the graph
        for (Vertex endVertex : network.getEndVerticesList()) {
            if (endVertex.getVertexId().equals(endId)) {
                validEndVertex = true;
                end = endVertex;
            }
        }

        // throws an exception if either one of the vertices or both the start and the
        // end vertex do not exist in the graph
        if (!validStartVertex && !validEndVertex) {
            throw new LogicException(String.format(Errors.FLOW_INVALID_BOTH_VERTICES, startId, endId));
        } else if (!validStartVertex && validEndVertex) {
            throw new LogicException(String.format(Errors.FLOW_INVALID_START_VERTEX, startId));
        } else if (validStartVertex && !validEndVertex) {
            throw new LogicException(String.format(Errors.FLOW_INVALID_END_VERTEX, endId));
        }

        // checks if the flow between these two vertices was already calculated and
        // returns the flow if it was
        long maximumFlow = network.getMaximumFlow(start, end);
        if (maximumFlow != 0) {
            return maximumFlow;
        }

        // calculates the flow
        EdmondKarpAlgorithm algorithm = new EdmondKarpAlgorithm(network, start, end);
        maximumFlow = algorithm.maximumFlow();
        network.setMaximumFlow(start, end, maximumFlow);
        return maximumFlow;
    }
}
