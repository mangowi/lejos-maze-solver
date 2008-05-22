package general;

import java.awt.Dimension;


public class LegoNavigator extends AbstractMazeNavigator {
	
	public LegoNavigator(Maze m, char direction, int x, int y){
		maze = m;
		currentDirection = direction;
		currentLocation = new Dimension(x, y);
	}
	
	public LegoNavigator(Maze m) {
		this(m, 'E', 0, 0);
	}

	boolean back() {
		
		if(currentDirection == 'N'){
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'S')){
				currentLocation.height = currentLocation.height+1;
				return true;
			}

		}
		if(currentDirection == 'E'){
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'W')){
				currentLocation.width = currentLocation.width-1;
				return true;
			}
			
		}
		if(currentDirection == 'S'){
//			System.out.println("wallAt: "+currentLocation.width+", "+currentLocation.height);
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'N')){
				currentLocation.height = currentLocation.height-1;
				return true;
			}
		}
		if(currentDirection == 'W'){
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'E')){
				currentLocation.width = currentLocation.width+1;
				return true;
			}
		}
		return false;
	}

	boolean forward() {
		switch (currentDirection){
		case 'N':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'N')){
				currentLocation.height = currentLocation.height-1;
				return true;
			}
			break;
		case 'E':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'E')){
				currentLocation.width = currentLocation.width+1;
				return true;
			}
			break;
		case 'S':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'S')){
				currentLocation.height = currentLocation.height+1;
				return true;
			}
			break;
		case 'W':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'W')){
				currentLocation.width = currentLocation.width-1;
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}

	boolean isDeadend() {
		int walls = 0;
		if(maze.wallAt(currentLocation.width, currentLocation.height, NORTH)){
			walls = walls+1;
		}
		if(maze.wallAt(currentLocation.width, currentLocation.height, EAST)){
			walls = walls+1;
		}
		if(maze.wallAt(currentLocation.width, currentLocation.height, SOUTH)){
			walls = walls+1;
		}
		if(maze.wallAt(currentLocation.width, currentLocation.height, WEST)){
			walls = walls+1;
		}
//		System.out.println("walls: "+walls);
		if(walls >= 3){
			return true;
		}else{
			return false;
		}
	}

	boolean isExit() {
		if((currentLocation.height == maze.getExit().height) && 
		   (currentLocation.width == maze.getExit().width)){
			return true;
		}
		else{
			return false;
		}
	}

	boolean isLCross() {
		LegoNavigator dummy = new LegoNavigator(maze, currentDirection, currentLocation.width, currentLocation.height); 
		if((!dummy.forward()) && 
		  ( ((!maze.wallAt(currentLocation.width, currentLocation.height, left()))&&(!dummy.turnRight())) ||
		  ((!maze.wallAt(currentLocation.width, currentLocation.height, right()))&&!dummy.turnLeft()) )){
			return true;
		}
		else{
			return false;
		}
	}

	boolean isOnLine() {
		LegoNavigator dummy = new LegoNavigator(maze, currentDirection, currentLocation.width, currentLocation.height); 
		if((!maze.wallAt(currentLocation.width, currentLocation.height, currentDirection))
		  && (!dummy.turnLeft()) && (!dummy.turnRight()) && dummy.back()){
			return true;
		}
		else{
			return false;
		}
	}

	boolean isTCross() {
		LegoNavigator dummy = new LegoNavigator(maze, currentDirection, currentLocation.width, currentLocation.height);
//		System.out.println("se puede dummy right? "+dummy.turnRight());
		if(maze.wallAt(currentLocation.width, currentLocation.height, currentDirection) &&
		  (!maze.wallAt(currentLocation.width, currentLocation.height, left())) && 
		  (!maze.wallAt(currentLocation.width, currentLocation.height, right()))){
			return true;
		}
		else{
			return false;
		}
	}

	public char right() {
		switch(currentDirection){
		case 'N':
			return 'E';
		case 'E':
			return 'S';
		case 'S':
			return 'W';
		case 'W':
			return 'N';
	}
	return 'X';
	}

	public char left() {
		switch(currentDirection){
			case 'N':
				return 'W';
			case 'E':
				return 'N';
			case 'S':
				return 'E';
			case 'W':
				return 'S';
		}
		return 'X';
	}
	
	public char turn180(){
		switch(currentDirection){
		case 'N':
			return 'S';
		case 'E':
			return 'W';
		case 'S':
			return 'N';
		case 'W':
			return 'E';
		}
		return 'X';
	}

	boolean isTInvLeftCross(){
		LegoNavigator dummy = new LegoNavigator(maze, right(), currentLocation.width, currentLocation.height);
		if(dummy.isTCross()){
			return true;
		}
		else{
			return false;
		}
	}
	
	boolean isTInvRightCross(){
		LegoNavigator dummy = new LegoNavigator(maze, left(), currentLocation.width, currentLocation.height);
		if(dummy.isTCross()){
			return true;
		}
		else{
			return false;
		}
	}
	
	boolean isXCross() {
		LegoNavigator dummy = new LegoNavigator(maze, currentDirection, currentLocation.width, currentLocation.height); 
		if(dummy.forward() && dummy.turnLeft() && dummy.turnRight()){
			return true;
		}
		else{
			return false;
		}
	}

	void stop() {
		for(int i = 0; i < 35535; i++){}
	}
	
	//Verifies if there is a wall on the left, turns left and advances one position.
	boolean turnLeft() {
		switch (currentDirection){
		case 'N':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'W')){
				currentDirection = WEST;
				forward();
				return true;
			}
			break;
		case 'E':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'N')){
				currentDirection = NORTH;
				forward();
				return true;
			}
			break;
		case 'S':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'E')){
				currentDirection = EAST;
				forward();
				return true;
			}
			break;
		case 'W':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'S')){
				currentDirection = SOUTH;
				forward();
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}

	//Verifies if there is a wall on the right, turns right and advances one position.
	boolean turnRight() {
		switch (currentDirection){
		case 'N':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'E')){
				currentDirection = EAST;
				forward();
				return true;
			}
			break;
		case 'E':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'S')){
				currentDirection = SOUTH;
				forward();
				return true;
			}
			break;
		case 'S':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'W')){
				currentDirection = WEST;
				forward();
				return true;
			}
			break;
		case 'W':
			if(!maze.wallAt(currentLocation.width, currentLocation.height, 'N')){
				currentDirection = NORTH;
				forward();
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}
	
	public Maze getMaze(){
		return maze;
	}

	public void inform() {
		System.out.println("Location: "+currentLocation.width+", "+currentLocation.height);
		System.out.println("Direction: "+currentDirection);
		
	}

	public char getDirection(){
		return currentDirection;
	}
	
	public boolean setDirection(char dir){
		if(dir == 'N' || dir == 'E' || dir == 'S' || dir == 'W'){
			currentDirection = dir;
			return true;
		}
		return false;
	}
	
}
