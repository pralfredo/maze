/**
 * File: MazeBreadthFirstSearch.java
 * Author: Pramithas Upreti
 * CS231
 * Section A
 * Project 7 ---> Searching on a Grid
 * Date: April 23, 2023
 * Purpose: To implement the Breadth First Search algorithm in solving the maze.
 */

public class MazeBreadthFirstSearch extends AbstractMazeSearch {

    private Queue<Cell> queue;

    /**
     * The Constructor for the Breadth First Search algorithm
     * 
     * @param maze the maze
     */
    public MazeBreadthFirstSearch(Maze maze) {
        super(maze);
        this.queue = new LinkedList<>();
    }

    /**
     * 
     * Adds the given cell to the end of the queue.
     * 
     * @param next the cell to add to the queue
     */
    public void addCell(Cell next) {
        queue.offer(next);
    }

    /**
     * 
     * Returns and removes the next cell from the front of the queue.
     * 
     * @return the next cell in the queue
     */
    public Cell findNextCell() {
        return queue.poll();
    }

    /**
     * 
     * Returns the number of cells currently in the queue.
     * 
     * @return the number of cells in the queue
     */
    public int numRemainingCells() {
        return queue.size();
    }
}
