import java.util.List;
public class MazeSolverQueue extends MazeSolverV2
{
	private Maze maze;
	private MyQueue<Square> queue;
	
	/**
	 * constructs the Queue
	 * @param maze  the maze		
	 */
	public MazeSolverQueue(Maze maze) {
		super(maze);
		this.maze = maze;
		queue = new MyQueue<Square>();
		queue.add(maze.getStart());
	}
	/**
	 * makes the queue empty
	 */
	@Override
	public void makeEmpty() 
	{
		queue = new MyQueue();
	}
	/**
	 * Checks if the queue is empty
	 * @return  if the queue is empty
	 */
	@Override
	public boolean isEmpty() 
	{
		return queue.isEmpty();
	}
	/**
	 * adds a square to the queue
	 * @param s  the square
	 */
	@Override
	public void add(Square s) 
	{
		queue.add(s);	
	}
	/**
	 * gets the next square in the queue
	 * @return  the next square
	 */
	@Override
	public Square next() 
	{
		return queue.peek();
	}
	/**
	 * Performs one iteration of the queue progressing through the maze
	 */
	@Override
	public void step()
	{
		Square currentTile = queue.peek();
		if(queue.isEmpty() || currentTile.getType() == 3)
		{
			
		}
		else if(!maze.getNeighbors(currentTile).isEmpty())
		{
			List<Square> list = maze.getNeighbors(currentTile);
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i).getType() != 1 && list.get(i).getStatus() != '.' && list.get(i).getStatus() != 'o' && list.get(i).getType() != 2)
				{
					list.get(i).setStatus('o');
					list.get(i).setPre(currentTile);
					maze.setSquare(list.get(i));
					queue.add(list.get(i));
				}
			}
		}
		else
		{
			queue.peek().setStatus('.');
			maze.setSquare(queue.peek());
		}
		queue.remove();
	}
	/**
	 * checks if the maze in its current state is solved
	 * @return  if the maze is solved
	 */
	@Override
	public boolean isSolved()
	{
		if(isEmpty() || next().getType() == 3)
		{
			if(isEmpty())
			{
				maze.workToExplored();
				queue = new MyQueue();
				return true;
			}
			maze.workToExplored();
			queue.Solved(queue.peek());
			return true;
		}
		return false;
	}
	/**
	 * gets the path of the Stack
	 * @return  the path of the stack
	 */
	@Override
	public String getPath()
	{
		try
		{
			return queue.path(queue.peek(), 0);
		}
		catch(Exception e)
		{
			return "Unsolveable";
		}
	}
	/**
	 * removes the square at the front of the queue and returns it
	 * @return  the removed square
	 */
	@Override
	public Square remove() {
		return queue.remove();
	}
	/**
	 * nothing
	 */
	@Override
	public void setStack(MyStackV2 s) {
		
	}
	/**
	 * nothing
	 */
	@Override
	public MyStackV2 getStack() {
		return null;
	}
}
