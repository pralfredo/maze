
/**
 * File: AbstractMazeSearch.java
 * Author: Pramithas Upreti
 * Class: CS231
 * Section: A
 * Project 7 ---> Searching on a Grid
 * Date: April 23, 2023
 * Purpose: This class is an abstract class that defines the common methods and properties for a Maze search.
 * It is implemented by various search methods for runnings their algorithms.
 */

//import statements
import java.awt.Color;
import java.awt.Graphics;

public abstract class AbstractMazeSearch {

    protected Maze maze; // the maze
    protected Cell start; // the starting cell
    protected Cell target; // the target cell
    private Cell cur; // the current cell
    protected MazeSearchDisplay mazedisplay; // the display
    private int numVisited; // number of cells visited

    /**
     * This constructor creates a new instance of AbstractMazeSearch and initializes
     * the properties of the object.
     * 
     * @param maze The maze object to search.
     */
    public AbstractMazeSearch(Maze maze) {
        this.maze = maze;
        this.cur = null;
        this.target = null;
        this.start = null;
        this.numVisited = 0;
    }

    /**
     * This method must be implemented by a subclass to find the next cell to
     * explore.
     * 
     * @return the next cell to explore.
     */
    public abstract Cell findNextCell();

    /**
     * This method must be implemented by a subclass to add the given Cell to
     * whatever structure is storing the future
     * Cells to explore.
     * 
     * @param next the Cell to add to the structure.
     */
    public abstract void addCell(Cell next);

    /**
     * This method returns the number of future Cells to explore
     * (so just the size of whatever structure is storing the future Cells).
     * 
     * @return the number of future cells to explore.
     */
    public abstract int numRemainingCells();

    /**
     * This method returns the maze object being searched.
     * 
     * @return the maze object being searched.
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * This method sets the target cell to the given Cell object.
     * 
     * @param target the Cell object to set as the target.
     */
    public void setTarget(Cell target) {
        this.target = target;
    }

    /**
     * This method returns the target cell being searched for.
     * 
     * @return the target cell being searched for.
     */
    public Cell getTarget() {
        return target;
    }

    /**
     * This method sets the current cell to the given Cell object.
     * 
     * @param cell the Cell object to set as the current cell.
     */
    public void setCur(Cell cell) {
        this.cur = cell;
    }

    /**
     * This method returns the current cell being searched from.
     * 
     * @return the current cell being searched from.
     */
    public Cell getCur() {
        return cur;
    }

    /**
     * This method sets the start cell to the given Cell object.
     * 
     * @param start the Cell object to set as the start cell.
     */
    public void setStart(Cell start) {
        this.start = start;
        start.setPrev(start);
    }

    /**
     * This method returns the start cell of the search.
     * 
     * @return the start cell of the search.
     */
    public Cell getStart() {
        return start;
    }

    /**
     * This method returns the number of cells visited during the search.
     * 
     * @return the number of cells visited during the search.
     */
    public int numVisited() {
        return numVisited;
    }

    /**
     * This method resets the properties of the search object.
     */
    public void reset() {
        this.cur = null;
        this.start = null;
        this.target = null;
    }

    /**
     * Returns the path from the start cell to the specified cell by tracing back
     * through the "previous" pointers in each cell.
     *
     * @param cell the cell to trace back to from the start cell
     * @return a linked list of cells representing the path from the start cell to
     *         the specified cell,
     *         or null if no path exists.
     */
    public LinkedList<Cell> traceback(Cell cell) {
        Cell curCell = cell;
        LinkedList<Cell> path = new LinkedList<>();
        while (curCell != null) {
            // System.out.println("Tracing back... " + curCell);
            path.addFirst(curCell);
            if (curCell.getRow() == start.getRow() && curCell.getCol() == start.getCol()) {
                // System.out.println((path.size()-2));
                return path; // we've completed the path from the start to the specified cell
            }
            curCell = curCell.getPrev();
        }
        return null; // we weren't able to find a path, so we return null
    }

    /**
     * Searches for a path from the start cell to the target cell in the maze.
     * 
     * @param start   the starting cell for the search
     * @param target  the target cell for the search
     * @param display whether or not to display the maze search progress
     * @param delay   the delay between updates in milliseconds if display is true
     * @return a linked list of cells representing the path from the start cell to
     *         the target cell,
     *         or null if no path exists.
     */
    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) {
        setStart(start);
        setTarget(target);
        setCur(start);
        addCell(start);
        if (display == true) {
            mazedisplay = new MazeSearchDisplay(this, 15);
        }
        while (numRemainingCells() != 0) {
            if (display == true) {
                try {
                    Thread.sleep(delay);
                    mazedisplay.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            setCur(findNextCell());
            for (Cell neighbor : maze.getNeighbors(cur)) {
                if (neighbor.getPrev() == null) {
                    neighbor.setPrev(cur);
                    addCell(neighbor);
                    numVisited++; // Increment numVisited for start cell
                    if (neighbor.getRow() == target.getRow() && neighbor.getCol() == target.getCol()) {
                        target.setPrev(cur);
                        LinkedList<Cell> path = traceback(target);
                        if (path != null) {
                            System.out.println("Path length: " + (path.size() - 2)); // -2 accounts for start & target
                        }
                        System.out.println("Number of cells visited: " + (numVisited() - 2)); // -2 for start & target
                        System.out.println("Ratio: " + ((double) numVisited() / (double) path.size()));
                        return traceback(neighbor);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Draws the maze and search progress on the specified graphics object with the
     * given scale.
     * 
     * @param g     the graphics object to draw on
     * @param scale the scale to draw the maze at
     */
    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);
        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        // System.out.println(getTarget() + "painting");
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                // System.out.println("Coloring path green!");
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }
}