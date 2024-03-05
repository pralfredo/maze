/**
 * File: Stack.java
 * Author: Pramithas Upreti
 * CS231
 * Section A
 * Project 7 ---> Searching on a Grid
 * Date: April 23, 2023
 * Purpose: To represent the Stack interface.
 */

public interface Stack<T> {

    /**
     * Returns the number of items in the stack.
     * 
     * @return the number of items in the stack.
     */
    public int size();

    /**
     * Returns the item on the top of the stack.
     * 
     * @return the item on the top of the stack.
     */
    public T peek();

    /**
     * Returns and removes the item on the top of the stack.
     * 
     * @return the item on the top of the stack.
     */
    public T pop();

    /**
     * Adds the given {@code item} to the top of the stack.
     * 
     * @param item the item to be added.
     */
    public void push(T item);
}
