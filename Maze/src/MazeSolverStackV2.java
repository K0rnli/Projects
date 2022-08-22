public class MazeSolverStackV2 extends MazeSolverV2
{
	private MyStackV2 stack;
	public MazeSolverStackV2(Maze maze) {
		super(maze);
		stack = new MyStackV2();
		stack.push(maze.getStart());
	}

	@Override
	public void makeEmpty() 
	{
		stack = new MyStackV2();
	}

	@Override
	public boolean isEmpty() 
	{
		return stack.isEmpty();
	}

	@Override
	public void add(Square s) 
	{
		stack.push(s);
	}
	@Override
	public Square remove() {
		return stack.pop();
	}
	
	@Override
	public Square next() 
	{
		return stack.peek();
	}
	@Override
	public void setStack(MyStackV2 s) {
		stack = s;
	}

	@Override
	public MyStackV2 getStack() {
		return stack;
	}
}
