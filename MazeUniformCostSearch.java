
/**
 * File: MazeUniformCostSearch.java
 * Author: Pramithas Upreti
 * CS231
 * Section A
 * Project 7 ---> Searching on a Grid
 * Date: April 23, 2023
 * Purpose: To implement the Uniform Cost Search algorithm in solving the maze. 
 * 
 * @extension
 */

import java.util.Comparator;

public class MazeUniformCostSearch extends AbstractMazeSearch {

    private PriorityQueue<Cell> priorityQueue;

    /**
     * The Constructor for the Uniform Cost Search algorithm
     * It alters the comparator
     * @param maze the maze
     */
    public MazeUniformCostSearch(Maze maze) {
        super(maze);
        Comparator<Cell> comparator = new Comparator<Cell>() {

            @Override
            public int compare(Cell cell1, Cell cell2) {
                int cost1 = traceback(cell1).size();
                int cost2 = traceback(cell2).size();
                if (cost1 == cost2) {
                    return 0;
                } else if (cost1 < cost2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        this.priorityQueue = new Heap<Cell>(comparator, false);
    }

    /**
     * 
     * Adds a cell to the priority queue.
     * 
     * @param next the cell to be added to the priority queue.
     */
    public void addCell(Cell next) {
        priorityQueue.offer(next);
    }

    /**
     * 
     * Removes and returns the next cell in the priority queue.
     * 
     * @return the next cell in the priority queue.
     */
    public Cell findNextCell() {
        return priorityQueue.poll();
    }

    /**
     * 
     * Returns the number of cells remaining in the priority queue.
     * 
     * @return the number of cells remaining in the priority queue.
     */
    public int numRemainingCells() {
        return priorityQueue.size();
    }
}
