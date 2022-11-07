package application;

import java.awt.Point;

import searches.BFS;
import searches.DFS;
import searches.Greedy;
import searches.Magic;
import searches.RandomWalk;
import searches.SearchAlgorithm;

public class MazeController {
	
	// Where to start and stop the search
	private Point start;
	private Point goal;
	private Maze maze;
	private SearchAlgorithm search;		// This string tells which algorithm is currently chosen.  Anything other than 
	// the implemented search class names will result in no search happening.


	
	private MazeDisplay mazeDisplay;

	
	public MazeController(int numRows, int numColumns, MazeDisplay thisDisplay) {
		start = new Point(1,1);
		goal = new Point(numRows-2, numColumns-2);
		maze = new Maze(numRows, numColumns);
		mazeDisplay = thisDisplay;
	}
	
	/*
	 * Re-create the maze from scratch.
	 * When this happens, we should also stop the search.
	 */
	public void newMaze() {
		maze.createMaze(maze.getNumRows(),maze.getNumCols());
	//	search = "";
		mazeDisplay.redraw();
	}
	
	
	/*
	 * Does a step in the search regardless of pause status
	 */
	public void doOneStep(double elapsedTime){
		if (search != null) search.step();
		mazeDisplay.redraw();
	}
	
	
	public void startSearch(String searchType) {
		maze.reColorMaze();
	//	search = searchType;
		
		// Restart the search.  Since I don't know 
		// which one, I'll restart all of them.
		
		search = new BFS(maze, start, goal);	// start in upper left and end in lower right corner
		search = new DFS(maze, start, goal);
		search = new Greedy(maze, start, goal);
		search = new RandomWalk(maze, start, goal);
		search = new Magic(maze, start, goal);
	}
	

	public int getCellState(Point position) {
		return maze.get(position);
	}
	


}
