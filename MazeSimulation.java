
/**
 * File: MazeSimulation.java
 * Author: Pramithas Upreti
 * CS231
 * Section A
 * Project 7 ---> Searching on a Grid
 * Date: April 23, 2023
 * Purpose: To explore different search algorithms on maze 
 */

import java.util.Random;

public class MazeSimulation {

    /**
     * The exploration, i.e. the main method itself
     * 
     * @param args
     */
    public static void main(String[] args) {

        // Change the value if you want multiple iterations
        for (int i = 0; i < 1; i++) {
            Maze maze = new Maze(40, 80, 0.2);

            Cell start; // The start cell
            Cell target; // The target cell
            // Randomizing the positions of start and target cells
            Random rand = new Random();
            int randRowStart = rand.nextInt(0, 40);
            int randColStart = rand.nextInt(0, 80);
            int randRowTarget = rand.nextInt(0, 40);
            int randColTarget = rand.nextInt(0, 80);

            // Two while loops to ensure that start/target aren't obstacles
            while (maze.landscape[randRowStart][randColStart].getType() != CellType.FREE) {
                randRowStart = rand.nextInt(0, 40);
                randColStart = rand.nextInt(0, 80);
            }
            while (maze.landscape[randRowTarget][randColTarget].getType() != CellType.FREE) {
                randRowTarget = rand.nextInt(0, 40);
                randColTarget = rand.nextInt(0, 80);
            }

            // Instantiations of the start and target cells
            start = new Cell(randRowStart, randColStart, CellType.FREE);
            target = new Cell(randRowTarget, randColTarget, CellType.FREE);

            // Instantiating different search methods
            AbstractMazeSearch DFSmaze = new MazeDepthFirstSearch(maze);
            AbstractMazeSearch BFSmaze = new MazeBreadthFirstSearch(maze);
            AbstractMazeSearch AStarmaze = new MazeAStarSearch(maze);
            AbstractMazeSearch BestFirstmaze = new MazeBestFirstSearch(maze);
            AbstractMazeSearch UniformCostmaze = new MazeUniformCostSearch(maze);

            // Searching on a particular maze
            DFSmaze.search(start, target, true, 100); 
            // change display to false if iterating over a large number for sanity

        }
    }
}