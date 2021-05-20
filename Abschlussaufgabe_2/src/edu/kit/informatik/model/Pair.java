package edu.kit.informatik.model;

/**
 * This class represents a pair of two generic objects.
 * 
 * @param <T> the first object that should be stored in the pair
 * @param <S> the second object that should be stored in the pair
 * @author Illia Pyvovar
 * @version 1.0
 */
public class Pair<T, S> {

    private T first;
    private S second;

    /**
     * This is the constructor of this class which sets the initial values for the
     * first and second object of this class.
     * 
     * @param firstObject  the object which will be stored in the first place
     * @param secondObject the object which will be stored in the second place
     */
    public Pair(T firstObject, S secondObject) {
        setFirst(firstObject);
        setSecond(secondObject);
    }

    /**
     * This method gets the element that is set to be the first element of this
     * pair.
     * 
     * @return Returns the first element of this pair as the object T
     */
    public T getFirst() {
        return first;
    }

    /**
     * This method sets the first element of this pair.
     * 
     * @param first the element that is to be set as the first element of this pair.
     */
    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * This method gets the element that is set to be the second element of this
     * pair.
     * 
     * @return Returns the second element of this pair as the object S
     */
    public S getSecond() {
        return second;
    }

    /**
     * This method sets the second element of this pair.
     * 
     * @param second the element that is to be set as the second element of this
     *               pair.
     */
    public void setSecond(S second) {
        this.second = second;
    }

}
