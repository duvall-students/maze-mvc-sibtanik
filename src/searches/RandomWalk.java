package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import application.Maze;

public class RandomWalk extends SearchAlgorithm {
	public final double EXPLORE_BIAS = .999;
	private Point next;
	private Random rand;

	
	public RandomWalk(Maze maze, Point start, Point goal){
		super(maze, start, goal);
		next = start;
		rand = new Random();
	}

	
	/*
	 * Choose a random neighbor out of all the non-wall neighbors. 
	 * To make the algorithm prefer going to unexplored areas, make the EXPLORE_BIAS higher.
	 */
	public Point chooseNeighbor(Collection<Point> neighbors){
		List<Point> empties = new ArrayList<>();
		List<Point> possibles = new ArrayList<>();
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				empties.add(p);
			}
			if(maze.get(p) != Maze.WALL){	// includes empties - all candidates
				possibles.add(p);
			}
		}
		if((rand.nextDouble()<EXPLORE_BIAS && !empties.isEmpty())) {
			int randIndex = rand.nextInt(empties.size());
			return empties.get(randIndex);
		}
		if(!possibles.isEmpty()){
			int randIndex = rand.nextInt(possibles.size());
			return possibles.get(randIndex);
		}
		return null;
	}
	
	public void recordLink(Point next){	
		this.next = next;
		maze.markVisited(current);
	}
	
	public void resetCurrent(){
		current = next;
	}
	
	public void searchType() {}
	public void colorPath() {}
	

}
