import java.awt.Dimension;
import java.util.Random;
import java.util.Vector;



public class Maze{ 

	private int ROWS = 10;
	private int COLS = 10;                                 // dimensions of maze
	private int[][] Maze;                                       // the maze of cells
	private Vector<int[]> stack;                                      // cell stack to hold a list of cell locations
	private final int N = 1;                          // direction constants
	private final int E = 2;
	private final int S = 4;
	private final int W = 8;
	
	private final Dimension exit = new Dimension(ROWS-1, COLS-1);

//	generate and display a maze
	public void compute()
	{
//		ROWS = parseInt(calc.rows.value, 10);       // size of maze
//		COLS = parseInt(calc.cols.value, 10);

//		if (isNaN(ROWS)||(ROWS < 0)||
//				isNaN(COLS)||(COLS < 0))                // check input
//		{
//			window.alert("Invalid input!");
//			return;
//		}

		init_cells();                               // create and display a maze
		generate_maze();
//		open_window();
		writeout_maze();
//		close_window();
	}

//	initialize variable sized multidimensional arrays
	public void init_cells()
	{
		int i, j;

		// create a maze of cells
		Maze = new int[ROWS][COLS];
//		for (i = 0; i < ROWS; i++)
//			Maze[i] = new Array(COLS);

		// set all walls of each cell in maze by setting bits :  N E S W
		for (i = 0; i < ROWS; i++)
			for (j = 0; j < COLS; j++)
				Maze[i][j] = (N + E + S + W);
		
		// create stack for storing previously visited locations
		stack = new Vector<int[]>(ROWS*COLS);
		for (i = 0; i < ROWS*COLS; i++)
			stack.add(i, new int[2]);

		// initialize stack
		for (i = 0; i < ROWS*COLS; i++)
			for (j = 0; j < 2; j++)
				stack.elementAt(i)[j] = 0;
	}

//	use depth first search to create a maze
	public void generate_maze()
	{
		int i, j, r, c;

		// choose a cell at random and make it the current cell
		Random rand = new Random(666);
		r = (int)(rand.nextFloat() * (ROWS - 1));
		c = (int)(rand.nextFloat() * (COLS - 1));
		int[] curr = {r,c};                            // current search location
		int visited  = 1;
		int total = ROWS*COLS;
		int tos   = 0;                              // index for top of cell stack 

		// arrays of single step movements between cells
		//          north    east     south    west
		int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0,-1}};
		int[][] next = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};

		while (visited < total)
		{
			//  find all neighbors of current cell with all walls intact
			j = 0;
			for (i = 0; i < 4; i++)
			{
				r = curr[0] + move[i][0];
				c = curr[1] + move[i][1];

				//  check for valid next cell
				if ((0 <= r) && (r < ROWS) && (0 <= c) && (c < COLS))
				{
					// check if previously visited
					if ((Maze[r][c] == 15))//N) && (Maze[r][c] == E) && (Maze[r][c] == S) && (Maze[r][c] == W))
					{
						// not visited, so add to possible next cells
						next[j][0] = r;
						next[j][1] = c;
						j++;
					}
				}
			}

			if (j > 0)
			{
				// current cell has one or more unvisited neighbors, so choose one at random  
				// and knock down the wall between it and the current cell
				i = (int)(rand.nextFloat() * (j-1));

				if ((next[i][0] - curr[0]) == 0)    // next on same row
				{
					r = next[i][0];
					if (next[i][1] > curr[1])       // move east
					{
						c = curr[1];
						Maze[r][c] &= ~E;           // clear E wall
						c = next[i][1];
						Maze[r][c] &= ~W;           // clear W wall
					}
					else                            // move west
					{
						c = curr[1];
						Maze[r][c] &= ~W;           // clear W wall
						c = next[i][1];
						Maze[r][c] &= ~E;           // clear E wall
					}
				}
				else                                // next on same column
				{
					c = next[i][1];
					            if (next[i][0] > curr[0])       // move south    
					            {
					            	r = curr[0];
					            	Maze[r][c] &= ~S;           // clear S wall
					            	r = next[i][0];
					            	Maze[r][c] &= ~N;           // clear N wall
					            }
					            else                            // move north
					            {
					            	r = curr[0];
					            	Maze[r][c] &= ~N;           // clear N wall
					            	r = next[i][0];
					            	Maze[r][c] &= ~S;           // clear S wall
					            }
				}

				tos++;                              // push current cell location
				stack.elementAt(tos)[0] = curr[0];
				stack.elementAt(tos)[1] = curr[1];

				curr[0] = next[i][0];               // make next cell the current cell
				curr[1] = next[i][1];

				visited++;                          // increment count of visited cells
			}
			else
			{
				// reached dead end, backtrack
				// pop the most recent cell from the cell stack            
				// and make it the current cell
				curr[0] = stack.elementAt(tos)[0];
				curr[1] = stack.elementAt(tos)[1];
				tos--;
			}
		}
	}
	
	public int[][] getMaze()
	{
		return Maze;
	}
	
	public void writeout_maze()
	{
	    int i, j, k;

	    for (i = 0; i < ROWS; i++)
	    {
	        for (k = 1; k <= 2; k++)
	        {
	            for (j = 0; j < COLS; j++)
	            {
	                if (k == 1)              // upper corners and walls
	                {
	                    if ((Maze[i][j] & N)  == N)
	                        System.out.print( "+---" );
	                    else
	                    	System.out.print( "+   " );

	                    if ((j + 1) == COLS)
	                    	System.out.print( "+" );

	                }
	                else if (k == 2)         // center walls and open areas
	                {
	                    if ((Maze[i][j] & W) == W)
	                    	System.out.print( "|   " );
	                    else
	                    	System.out.print( "    " );

	                    if ((j + 1) == COLS)
	                    	System.out.print( "|" );

	                }
	            }
	            System.out.println( "" );
	        }
	    }
	    for (j = 0; j < COLS; j++)          // bottom-most corners and walls
	    	System.out.print( "+---" );

	    System.out.println( "+" );  // last corner on bottom
	}
	
	public boolean wallAt(int x, int y, char direction){
		char c = Character.toUpperCase(direction);

		if(c == 'N'){
			if((Maze[y][x] & 1) == 1){
				
				return true;
			}
		}
		if(c == 'E'){
			if((Maze[y][x] & 2) == 2){
				
				return true;
			}
		}
		if(c == 'S'){
			if((Maze[y][x] & 4) == 4){
				
				return true;
			}
		}
		if(c == 'W'){
			if((Maze[y][x] & 8) == 8){
				
				return true;
			}
		}
		return false;
	}
	
	public Dimension getExit(){
		return exit;
	}
}