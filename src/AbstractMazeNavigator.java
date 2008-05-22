import java.awt.Dimension;


public abstract class AbstractMazeNavigator {
	
	protected char currentDirection;
	protected Dimension currentLocation;
	protected Maze maze;
	
	protected final char NORTH = 'N';
	protected final char EAST = 'E';
	protected final char SOUTH = 'S';
	protected final char WEST = 'W';
	
	//Actions the Navigator can execute
	
	abstract boolean forward();
	
	abstract boolean back();
	
	abstract boolean turnLeft();
	
	abstract boolean turnRight();
	
	abstract void stop();
	
	//Signals representing sensor lectures
	
	abstract boolean isOnLine();
	
	abstract boolean isLCross();
	
	abstract boolean isTCross();
	
	abstract boolean isXCross();
	
	abstract boolean isDeadend();
	
	abstract boolean isExit();
	
}
