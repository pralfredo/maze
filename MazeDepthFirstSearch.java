/**
 * File: MazeDepthFirstSearch.java
 * Author: Pramithas Upreti
 * CS231
 * Section A
 * Project 7 ---> Searching on a Grid
 * Date: April 23, 2023
 * Purpose: To implement the Depth First Search algorithm in solving the maze.
 */

public class MazeDepthFirstSearch extends AbstractMazeSearch {

    private Stack<Cell> stack;

    /**
     * The Constructor for thr Depth First Search algorithm
     * 
     * @param maze the maze
     */
    public MazeDepthFirstSearch(Maze maze) {
        super(maze);
        this.stack = new LinkedList<>();
    }

    /**
     * 
     * Adds the given cell to the stack of cells to be explored.
     * 
     * @param next the cell to be added to the stack
     */
    public void addCell(Cell next) {
        stack.push(next);
    }

    /**
     * 
     * Removes and returns the next cell to be explored from the stack.
     * 
     * @return the next cell to be explored
     */
    public Cell findNextCell() {
        return stack.pop();
    }

    /**
     * 
     * Returns the number of remaining cells to be explored.
     * 
     * @return the number of remaining cells
     */
    public int numRemainingCells() {
        return stack.size();
    }

}