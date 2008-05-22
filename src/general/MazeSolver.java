package general;

//import TestMaze;
public class MazeSolver {

	private LegoNavigator captain;
	private Maze m;
	
	public MazeSolver(LegoNavigator _ln){
		captain = _ln;
		m = captain.getMaze();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Maze tm = new Maze();
		tm.compute();
		LegoNavigator ln = new LegoNavigator(tm,'S',0,0);
		MazeSolver ms = new MazeSolver(ln);
		ms.wallFlower();
	}
	
	public boolean wallFlower(){
		
		
		while(!captain.isExit()){
			if(captain.isTCross() || captain.isXCross() || captain.isTInvLeftCross()){
				captain.turnLeft();
				captain.inform();
			}
			else if(captain.isLCross() && (!captain.turnLeft())){
				captain.turnRight();
				captain.inform();
			}
			else if(captain.isDeadend() /*&& m.wallAt(captain.currentLocation.width, captain.currentLocation.height, captain.currentDirection)*/){
				captain.currentDirection = captain.turn180();
				System.out.println("Deadend Found!");
				captain.inform();
			}
			captain.forward();
			captain.inform();
		}
		
		return true;
	}

}
