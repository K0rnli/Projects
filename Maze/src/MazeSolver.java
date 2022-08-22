import java.util.List;

public abstract class MazeSolver 
{
	private Maze maze;
	private MyStack stack;
	public MazeSolver(Maze maze)
	{
		this.maze = maze;
		stack = new MyStack();
		stack.push(maze.getStart());
	}
	public abstract void makeEmpty();
	public abstract boolean isEmpty();
	public abstract void add(Square s);
	public abstract Square remove();
	public abstract Square next();
	public abstract void setStack(MyStack s);
	public abstract MyStack getStack();
	public boolean isSolved()
	{
		if(isEmpty() || next().getType() == 3)
		{
			MyStack temp = getStack();
			temp.Solved();
			setStack(temp);
			return true;
		}
		return false;
	}
	public void step()
	{
		Square currentTile = next();
		if(!maze.getNeighbors(currentTile).isEmpty())
		{
			List<Square> list = maze.getNeighbors(currentTile);
			for(int i = 0; i < list.size(); i++)
				if(list.get(i).getType() != 1 && list.get(i).getStatus() != '.' && list.get(i).getStatus() != 'o' && list.get(i).getType() != 2)
				{
					list.get(i).setStatus('o');
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
	}
	public String getPath()
	{
		try
		{
		return getStack().path();
		}
		catch(Exception e)
		{
			return "";
		}
	}
	public void solve()
	{
		while(!isSolved())
			step();
	}
}
