/**
 * File: Queue.java
 * Author: Pramithas Upreti
 * CS231
 * Section A
 * Project 7 ---> Searching on a Grid
 * Date: April 23, 2023
 * Purpose: To represent the Queue Interface.
 */

public interface Queue<T> {

    /**
     * Adds the given {@code item} to the end of this queue.
     * 
     * @param item the item to add to the queue.
     */
    public void offer(T item);

    /**
     * Returns the number of items in the queue.
     * 
     * @return the number of items in the queue.
     */
    public int size();

    /**
     * Returns the item at the front of the queue.
     * 
     * @return the item at the front of the queue.
     */
    public T peek();

    /**
     * Returns and removes the item at the front of the queue.
     * 
     * @return the item at the front of the queue.
     */
    public T poll();
}
