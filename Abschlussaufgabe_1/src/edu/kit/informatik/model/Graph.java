package edu.kit.informatik.model;

import java.util.ArrayList;

/**
 * This class represents the graph of an escape route network. It contains all
 * nodes as well as all escape route sections.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Graph {
    private ArrayList<Vertex> vertexList = new ArrayList<>();
    private ArrayList<EscapeRouteSection> sectionList = new ArrayList<>();

    /**
     * This method adds an escape route section if it not already exists. Otherwise
     * the escape route will be overwritten.
     * 
     * @param section this parameter represents the escape route section which is to
     *                be added to the system.
     */
    public void addEscapeSection(EscapeRouteSection section) {
        Vertex fromVertex = section.getFromVertex();
        Vertex toVertex = section.getToVertex();

        if (escapeRouteSectionExists(fromVertex, toVertex)) {
            sectionList.remove(getSection(fromVertex, toVertex));
            sectionList.add(section);
        } else {
            sectionList.add(section);
        }
    }

    /**
     * This method gets the start vertex, which is the starting point of the graph.
     * 
     * @return Returns the start vertex of the graph as a vertex object
     */
    public ArrayList<Vertex> getStartVertex() {
        ArrayList<Vertex> vertex = new ArrayList<>();
        for (int i = 0; i < sectionList.size(); i++) {
            for (int n = 0; n < sectionList.size(); n++) {
                if (sectionList.get(i).getFromVertex().equals(sectionList.get(n).getToVertex())) {
                    break;
                } else if ((n == sectionList.size() - 1) && !vertex.contains(sectionList.get(i).getFromVertex())) {
                    vertex.add(sectionList.get(i).getFromVertex());
                }
            }
        }
        return vertex;
    }

    /**
     * This method gets the end vertex, which is the ending point of the graph.
     * 
     * @return Returns the end vertex of the graph as a vertex object
     */
    public ArrayList<Vertex> getEndVertex() {
        ArrayList<Vertex> vertex = new ArrayList<>();
        for (int i = 0; i < sectionList.size(); i++) {
            for (int n = 0; n < sectionList.size(); n++) {
                if (sectionList.get(i).getToVertex().equals(sectionList.get(n).getFromVertex())) {
                    break;
                } else if ((n == sectionList.size() - 1) && !vertex.contains(sectionList.get(i).getToVertex())) {
                    vertex.add(sectionList.get(i).getToVertex());
                }
            }
        }
        return vertex;
    }

    /**
     * This method checks whether an escape route section is already stored in the
     * system or not.
     * 
     * @param fromVertex the vertex from which the path leads away
     * @param toVertex   the vertex to which the path leads to
     * @return Returns either true if an escape route section exists or false if it
     *         does not exist
     */
    private boolean escapeRouteSectionExists(Vertex fromVertex, Vertex toVertex) {
        boolean containsEscapeSection = false;
        EscapeRouteSection storedSection;
        for (int i = 0; i < sectionList.size(); i++) {
            storedSection = sectionList.get(i);
            if (fromVertex.equals(storedSection.getFromVertex()) && toVertex.equals(storedSection.getToVertex())) {
                containsEscapeSection = true;
            }
        }
        return containsEscapeSection;
    }

    /**
     * This method gets the desired section from the list of all section in the
     * graph.
     * 
     * It checks if a section between two vertices that were handed over as
     * parameters exists in the list.
     * 
     * @param fromVertex the vertex from which the path leads away
     * @param toVertex   the vertex to which the path leads to
     * @return Returns an escape route section from the system as EscapeRouteSection
     */
    public EscapeRouteSection getSection(Vertex fromVertex, Vertex toVertex) {
        EscapeRouteSection storedSection;
        for (int i = 0; i < sectionList.size(); i++) {
            storedSection = sectionList.get(i);
            if (fromVertex.equals(storedSection.getFromVertex()) && toVertex.equals(storedSection.getToVertex())) {
                return storedSection;
            }
        }
        return null;
    }

    /**
     * This method returns the whole escape route section list.
     * 
     * @return Returns the escape route section list as an ArrayList
     */
    public ArrayList<EscapeRouteSection> getSectionList() {
        return this.sectionList;
    }

    /**
     * This method gets the amount of vertices in the graph of the escape route
     * network.
     * 
     * @return Returns the amount of vertices in the graph as an integer
     */
    public int getAmountOfVertices() {
        return vertexList.size();
    }

    /**
     * This method adds vertices to a list of all vertices that exist in the graph
     * if they are not already listed.
     * 
     * @param fromVertexId the identifier of the vertex from which the path leads
     *                     away which should be added to the list
     * @param toVertexId   the identifier of the vertex to which the path leads to
     *                     which should be added to the list
     */
    public void addVertices(String fromVertexId, String toVertexId) {
        if (!vertexExists(fromVertexId)) {
            Vertex fromVertex = new Vertex(fromVertexId);
            addVertex(fromVertex);
        }

        if (!vertexExists(toVertexId)) {
            Vertex toVertex = new Vertex(toVertexId);
            addVertex(toVertex);
        }
    }

    private boolean vertexExists(String vertexId) {
        boolean containsVertex = false;

        if (vertexList.isEmpty()) {
            return containsVertex;
        }
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getVertexId().equals(vertexId)) {
                containsVertex = true;
                return containsVertex;
            }
        }
        return containsVertex;
    }

    /**
     * This method gets a vertex from the vertex list which has the vertex
     * identifier.
     * 
     * @param vertexId the vertex identifier that is needed to get the wanted vertex
     *                 out of the vertex list.
     * @return Returns the vertex searched for as a Vertex object from the vertex
     *         list
     */
    public Vertex getVertex(String vertexId) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getVertexId().equals(vertexId)) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void addVertex(Vertex vertex) {
        vertexList.add(vertex);
    }
}
