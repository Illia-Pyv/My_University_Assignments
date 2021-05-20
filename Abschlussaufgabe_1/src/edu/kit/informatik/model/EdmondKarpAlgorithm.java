package edu.kit.informatik.model;

import java.util.ArrayList;

/**
 * This class represents the Edmond-Karp-algorithm which is an essential part of
 * this program.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class EdmondKarpAlgorithm {

    private Vertex start;
    private Vertex end;
    private EscapeNetwork network;

    /**
     * This is the constructor of this class. It initializes the start and end
     * vertices for which the algorithm should be run.
     * 
     * @param network the network in which the calculation of the maximum flow
     *                should be performed
     * @param start   the start vertex from where the algorithm starts calculating
     *                the maximum flow
     * @param end     the end vertex from where the algorithm ends the calculation
     *                of the maximum flow
     */
    public EdmondKarpAlgorithm(EscapeNetwork network, Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        this.network = network;
    }

    /**
     * This method starts the calculation of the maximum flow from a start vertex to
     * an end vertex in the escape network.
     * 
     * @return Returns the maximum flow as an integer value
     */
    public long maximumFlow() {
        ResidualCapacityNetwork residualNetwork = new ResidualCapacityNetwork(network.getGraph());
        BreadthSearchAlgorithm breadthSearch = new BreadthSearchAlgorithm();
        ArrayList<ArrayList<Vertex>> path = null;
        ArrayList<EscapeRouteSection> pathSections = new ArrayList<>();
        do {
            pathSections.clear();
            // calculate the path with the breadth-first-search algorithm
            path = breadthSearch.search(residualNetwork.determineResidualCapcityGraph(), start, end);
            if (!(path == null)) {
                for (ArrayList<Vertex> vertexPair : path) {
                    pathSections.add(residualNetwork.getSection(vertexPair.get(0), vertexPair.get(1)));
                }
                // gets the bottleneck capacity for the path
                int minimumResidualCapacity = getMinimumResidualCapacity(pathSections, residualNetwork);

                // updates all the flow values of each section based on the bottleneck capacity
                for (EscapeRouteSection section : pathSections) {
                    residualNetwork.updateCurrentFlow(section, minimumResidualCapacity);
                } 
            }
        } while (!(path == null));
        return calculateSumOfEndSections(residualNetwork, end);
    }

    private int getMinimumResidualCapacity(ArrayList<EscapeRouteSection> sections,
            ResidualCapacityNetwork residualNetwork) {
        int minimumResidualCapacity = 0;
        for (EscapeRouteSection section : sections) {
            if (minimumResidualCapacity == 0) {
                minimumResidualCapacity = residualNetwork.calculateResidualCapacity(section);
            } else if (minimumResidualCapacity > residualNetwork.calculateResidualCapacity(section)) {
                minimumResidualCapacity = residualNetwork.calculateResidualCapacity(section);
            }
        }
        return minimumResidualCapacity;
    }

    private long calculateSumOfEndSections(ResidualCapacityNetwork residualNetwork, Vertex endVertex) {
        long result = 0;
        for (EscapeRouteSection section : residualNetwork.getAllSectionsToEndVertex(endVertex)) {
            result += section.getCurrentFlow();
        }
        return result;
    }
}
