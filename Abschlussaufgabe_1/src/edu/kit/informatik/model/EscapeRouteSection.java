package edu.kit.informatik.model;

/**
 * This class represents an escape route section that is made up of two nodes.
 * It is part of an escape route network.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public class EscapeRouteSection {
    private Vertex fromVertex;
    private Vertex toVertex;
    private int capacity;
    private int currentFlow;

    /**
     * This is the constructor of this class and it sets the both vertices that are
     * needed for an escape route section to be complete.
     * 
     * @param fromVertex the vertex from which the path leads away
     * @param toVertex the vertex to which the path leads to
     */
    public EscapeRouteSection(Vertex fromVertex, Vertex toVertex) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
    }

    /**
     * This method returns the vertex from which the path leads away.
     * 
     * @return Returns a Vertex object from which the path leads away
     */
    public Vertex getFromVertex() {
        return this.fromVertex;
    }

    /**
     * This method returns the vertex to which the path leads to.
     * 
     * @return Returns a Vertex object to which the path leads to
     */
    public Vertex getToVertex() {
        return this.toVertex;
    }

    /**
     * This method sets the capacity value for this section.
     * 
     * @param capacity the capacity value which the section should be set on
     */
    public void setCapacityValue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * This method gets the capacity value for this section.
     * 
     * @return Returns the capacity value as an integer 
     */
    public int getCapacity() {
        return this.capacity;
    }
    
    /**
     * This method gets the current flow of this section.
     * 
     * @return Returns the current flow of this section as an integer
     */
    public int getCurrentFlow() {
        return currentFlow;
    }

    /**
     * This method sets the current flow for this section.
     * 
     * @param currentFlow the current flow to set for this section
     */
    public void setCurrentFlow(int currentFlow) {
        this.currentFlow = currentFlow;
    }
}
