import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze 
{
	private boolean setup;
	private Square[][] maze;
	private Square start;
	private Square exit;
	/**
	 * Constructs the maze
	 */
	public Maze()
	{
		setup = false;
		maze = new Square[0][0];
		start = new Square(0, 0, 0);
		exit = new Square(0, 0, 0);
	}
	/**
	 * loads the maze
	 * @param fileName  the maze
	 * @return  if the maze was loaded
	 * @throws FileNotFoundException
	 */
	public boolean loadMaze(String fileName) throws FileNotFoundException
	{
		try
		{
			Scanner scan = new Scanner(new File(fileName));
			int h = scan.nextInt();
			int l = scan.nextInt();
			maze = new Square[h][l];
			for(int i = 0; i < h * l; i++)
			{
				int type = scan.nextInt();
				maze[i/l][i%l] = new Square(i%l, i/l, type);
				if(type == 2)
					start = maze[i/l][i%l];
				else if(type == 3)
					exit = maze[i/l][i%l];
			}
			scan.close();
			setup = true;
			return true;
		}
		catch(Exception e)
		{
			setup = false;
			return false;
		}
	}
	/**
	 * creates a list of neighbors to the square
	 * @param s  the square
	 * @return a list of neighbors
	 */
	public List<Square> getNeighbors(Square s)
	{
		List<Square> l = new ArrayList<Square>();
		try
		{
			if(maze[s.getCol()-1][s.getRow()].isMoveable())
				l.add(maze[s.getCol()-1][s.getRow()]);
		}
		catch(Exception e)
		{}
		try
		{
			if(maze[s.getCol()][s.getRow()+1].isMoveable())
				l.add(maze[s.getCol()][s.getRow()+1]);
		}
		catch(Exception e)
		{}
		try
		{
			if(maze[s.getCol()+1][s.getRow()].isMoveable())
				l.add(maze[s.getCol()+1][s.getRow()]);
		}
		catch(Exception e)
		{}
		try
		{
			if(maze[s.getCol()][s.getRow()-1].isMoveable())
				l.add(maze[s.getCol()][s.getRow()-1]);
		}
		catch(Exception e)
		{}
		return l;
	}
	/**
	 * restes the maze
	 */
	public void reset()
	{
		int l = maze[0].length;
		for(int i = 0; i < maze.length * maze[0].length; i++)
		{
			maze[i/l][i%l].setStatus('_');
		}
	}
	/**
	 * sets a square
	 * @param s  the square
	 */
	public void setSquare(Square s)
	{
		int row = s.getRow();
		int col = s.getCol();
		maze[col][row] = s;
	}
	/**
	 * gets a square
	 * @param x  the x
	 * @param y  the y
	 * @return  the square
	 */
	public Square getSquare(int x, int y)
	{
		return maze[y][x];
	}
	/**
	 * gets the length of the array
	 * @return  the length of the array
	 */
	public int getLength()
	{
		return maze[0].length;
	}
	/**
	 * gets the height of the array
	 * @return  the height of the array
	 */
	public int getHeight()
	{
		return maze.length;
	}
	/**
	 * changes all working tiles to explored tiles
	 */
	public void workToExplored()
	{
		int l = maze[0].length;
		for(int i = 0; i < maze.length * maze[0].length; i++)
			if(maze[i/l][i%l].getStatus() == 'o')
				maze[i/l][i%l].setStatus('.');;
	}
	@Override
	/**
	 * The toString
	 * @return the toString
	 */
	public String toString()
	{
		String ret = "";
		int l = maze[0].length;
		for(int i = 0; i < maze.length * l; i++)
		{
			ret += maze[i/l][i%l];
			if(i%l == l-1)
				ret += "\n";
		}
		return ret;
	}

	/**
	 * gets the start
	 * @return the start
	 */
	public Square getStart() {
		return start;
	}
	/**
	 * gets the exit
	 * @return the exit
	 */
	public Square getExit() {
		return exit;
	}
	/**
	 * gets if the maze has been loaded
	 * @return if the maze is loaded
	 */
	public boolean getSetup() {
		return setup;
	}
}
