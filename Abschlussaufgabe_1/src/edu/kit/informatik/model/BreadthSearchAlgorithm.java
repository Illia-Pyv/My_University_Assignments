package edu.kit.informatik.model;

import java.util.ArrayList;

/**
 * This class represents the breadth first search algorithm.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class BreadthSearchAlgorithm {

    /**
     * This method starts the search algorithm and it searches in a graph for the
     * end vertex.
     * 
     * @param graph all the escape route sections that can be searched by the
     *              algorithm
     * @param start the vertex where the algorithm should start to search
     * @param end   the vertex where the algorithm stops searching after finding
     *              this vertex
     * @return Returns a list of lists of vertices which represents the path the
     *         algorithm found
     */
    public ArrayList<ArrayList<Vertex>> search(ArrayList<EscapeRouteSection> graph, Vertex start, Vertex end) {
        ArrayList<VertexPair> path = new ArrayList<>();
        ArrayList<Vertex> queue = new ArrayList<>();
        ArrayList<Vertex> seen = new ArrayList<>();
        Vertex current;
        queue.add(start);
        seen.add(start);
        path.add(new VertexPair(start, start));
        while (!queue.isEmpty()) {
            current = dequeue(queue);

            // checks if the dequed vertex is already the end vertex that is searched for
            // and builds a path to it if the end vertex is found
            if (current.equals(end)) {
                return buildPath(path, start, end);
            }
            for (Vertex successorVertex : successor(current, graph)) {
                if (!seen.contains(successorVertex)) {
                    queue.add(successorVertex);
                    seen.add(successorVertex);
                    path.add(new VertexPair(current, successorVertex));
                }
            }
        }
        return null;
    }

    private Vertex dequeue(ArrayList<Vertex> queue) {
        Vertex result = queue.get(0);
        queue.remove(0);
        return result;
    }

    // returns the successor vertices of each parent vertex
    private ArrayList<Vertex> successor(Vertex vertex, ArrayList<EscapeRouteSection> graph) {
        ArrayList<Vertex> thisVertexList = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            if (vertex.equals(graph.get(i).getFromVertex())) {
                thisVertexList.add(graph.get(i).getToVertex());
            }
        }
        return thisVertexList;
    }

    private ArrayList<Vertex> tracePath(ArrayList<VertexPair> path, Vertex start, Vertex end) {
        ArrayList<Vertex> result = new ArrayList<>();
        result.add(end);
        Vertex current = end;
        // trace back the path from the end and add it to the result
        while (!current.equals(start)) {
            for (VertexPair pair : path) {
                if (pair.getEndVertex().equals(current)) {
                    result.add(pair.getStartVertex());
                    current = pair.getStartVertex();
                    break;
                }
            }
        }
        
        // reverse the resulting path
        int loopVariable = 0;
        while (loopVariable < result.size() - 1 - loopVariable) {
            Vertex temporary = result.get(loopVariable);
            result.set(loopVariable, result.get(result.size() - 1 - loopVariable));
            result.set(result.size() - 1 - loopVariable, temporary);
            loopVariable++;
        }
        return result;
    }

    private ArrayList<ArrayList<Vertex>> buildPath(ArrayList<VertexPair> path, Vertex start, Vertex end) {
        ArrayList<Vertex> secondary = null;
        ArrayList<ArrayList<Vertex>> result = new ArrayList<>();
        ArrayList<Vertex> tracedPath = tracePath(path, start, end);
        Vertex previous = null;
        // builds a path that is easier to process in the endmond karp algorithm
        for (Vertex vertex : tracedPath) {
            if (vertex == null) {
                return null;
            } else if (previous == null) {
                secondary = new ArrayList<>();
                secondary.add(vertex);
                previous = vertex;
            } else if (secondary.size() > 1) {
                result.add(secondary);
                secondary = new ArrayList<>();
                secondary.add(previous);
                secondary.add(vertex);
                previous = vertex;
            } else {
                secondary.add(vertex);
                previous = vertex;
            }
        }
        result.add(secondary);
        return result;
    }
}
