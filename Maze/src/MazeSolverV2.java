import java.util.List;

public abstract class MazeSolverV2 
{
	private Maze maze;
	private MyStackV2 stack;
	/**
	 * Constructs the Solver with a maze
	 * @param  maze  the maze
	 */
	public MazeSolverV2(Maze maze)
	{
		this.maze = maze;
		stack = new MyStackV2();
		stack.push(maze.getStart());
	}
	/**
	 * makes the stack empty
	 */
	public abstract void makeEmpty();
	/**
	 * Checks if the stack is empty
	 * @return  if the stack is empty
	 */
	public abstract boolean isEmpty();
	/**
	 * adds a square to the Stack
	 * @param s  the square
	 */
	public abstract void add(Square s);
	/**
	 * removes the square at the top of the stack and returns it
	 * @return  the removed square
	 */
	public abstract Square remove();
	/**
	 * gets the next square in the Stack
	 * @return  the next square
	 */
	public abstract Square next();
	/**
	 * sets the stack to a new stack
	 * @param s  the stack
	 */
	public abstract void setStack(MyStackV2 s);
	/**
	 * gets the stack
	 * @return  the stack
	 */
	public abstract MyStackV2 getStack();
	
	/**
	 * checks if the maze in its current state is solved
	 * @return  if the maze is solved
	 */
	public boolean isSolved()
	{
		if(isEmpty() || next().getType() == 3)
		{
			if(isEmpty())
			{
				maze.workToExplored();
				return true;
			}
			MyStackV2 temp = getStack();
			maze.workToExplored();
			temp.Solved(next());
			setStack(temp);
			return true;
		}
		return false;
	}
	
	/**
	 * Performs one iteration of the stack progressing through the maze
	 */
	public void step()
	{
		//Slower but correct solver
		Square currentTile = next();
		if(!maze.getNeighbors(currentTile).isEmpty())
		{
			List<Square> list = maze.getNeighbors(currentTile);
			for(int i = 0; i < list.size(); i++)
				if(list.get(i).getType() != 1 && list.get(i).getStatus() != '.' && list.get(i).getStatus() != 'o' && list.get(i).getType() != 2)
				{
					list.get(i).setStatus('o');
					list.get(i).setPre(currentTile);
					maze.setSquare(list.get(i));
					add(list.get(i));
					break;
				}
		}
		else
		{
			next().setStatus('.');
			maze.setSquare(next());
			remove();
		}
		
		//Much faster solver but not in order																
		/*
		Square temp	= remove();
		temp.setStatus('.');
		for(Square s:maze.getNeighbors(temp)) {
			if(s.getStatus() == '_') {
				s.setStatus('o');
				s.setPre(temp);
				add(s);
			}
		}
		*/
	}
	/**
	 * gets the path of the Stack
	 * @return  the path of the stack
	 */
	public String getPath()
	{
		try
		{
		return getStack().path(next(), 0);
		}
		catch(Exception e)
		{
			return "Unsolveable";
		}
	}
	/**
	 * solves the maze until
	 */
	public void solve()
	{
		while(!isSolved())
			step();
	}
}
