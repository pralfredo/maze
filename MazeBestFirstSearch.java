
/**
 * File: MazeBestFirstSearch.java
 * Author: Pramithas Upreti
 * CS231
 * Section A
 * Project 7 ---> Searching on a Grid
 * Date: April 23, 2023
 * Purpose: To implement the Best First Search algorithm in solving the maze. 
 * 
 * @extension
 */

import java.util.*;

public class MazeBestFirstSearch extends AbstractMazeSearch {

    private PriorityQueue<Cell> priorityQueue;

    public MazeBestFirstSearch(Maze maze) {
        super(maze);
        Comparator<Cell> comparator = new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                // cell 1
                int estimatedDistance1 = Math.abs(target.getRow() - c1.getRow())
                        + Math.abs(target.getCol() - c1.getCol());
                // cell 2
                int estimatedDistance2 = Math.abs(target.getRow() - c2.getRow())
                        + Math.abs(target.getCol() - c2.getCol());
                if (estimatedDistance1 == estimatedDistance2) {
                    return 0;
                } else if (estimatedDistance1 < estimatedDistance2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        this.priorityQueue = new Heap<Cell>(comparator, false);
        ;
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