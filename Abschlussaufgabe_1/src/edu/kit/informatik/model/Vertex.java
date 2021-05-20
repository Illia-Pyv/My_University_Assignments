package edu.kit.informatik.model;

/**
 * This class represents a vertex of a graph.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Vertex {

    private String vertexIdentifier = "";

    /**
     * This is the constructor of this class and it sets the identifier of the
     * vertex when the vertex is created.
     * 
     * @param vertexIdentifier the identifier of the vertex, the string with which
     *                         the vertex is identified with
     */
    public Vertex(String vertexIdentifier) {
        setVertexId(vertexIdentifier);
    }

    /**
     * This method returns the vertex identifier.
     * 
     * @return Returns the vertex identifier as a string.
     */
    public String getVertexId() {
        return vertexIdentifier;
    }

    private void setVertexId(String vertexIdentifier) {
        this.vertexIdentifier = vertexIdentifier;
    }
}
