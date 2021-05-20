package edu.kit.informatik.model;

/**
 * This class represents a pair of a start and an end vertex.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class VertexPair {

    private Vertex startVertex;
    private Vertex endVertex;

    /**
     * This method sets the start and end vertices for this pair when this object is
     * created.
     * 
     * @param start the start vertex of this pair
     * @param end   the end vertex of this pair
     */
    public VertexPair(Vertex start, Vertex end) {
        this.startVertex = start;
        this.endVertex = end;
    }

    /**
     * This method gets the start vertex of this vertex pair.
     * 
     * @return Returns the object of the start vertex of this vertex pair
     */
    public Vertex getStartVertex() {
        return startVertex;
    }

    /**
     * This method gets the end vertex of this vertex pair.
     * 
     * @return Returns the object of the end vertex of this vertex pair
     */
    public Vertex getEndVertex() {
        return endVertex;
    }
}
