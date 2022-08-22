public class MazeSolverStack extends MazeSolver
{
	private MyStack stack;
	
	/**
	 * constructs the Stack
	 * @param maze  the maze		
	 */
	public MazeSolverStack(Maze maze) {
		super(maze);
		stack = new MyStack();
		stack.push(maze.getStart());
	}

	/**
	 * makes the stack empty
	 */
	@Override
	public void makeEmpty() 
	{
		stack = new MyStack();
	}
	/**
	 * Checks if the stack is empty
	 * @return  if the stack is empty
	 */
	@Override
	public boolean isEmpty() 
	{
		return stack.isEmpty();
	}
	/**
	 * adds a square to the Stack
	 * @param s  the square
	 */
	@Override
	public void add(Square s) 
	{
		stack.push(s);
	}
	/**
	 * removes the square at the top of the stack and returns it
	 * @return  the removed square
	 */
	@Override
	public Square remove() {
		return stack.pop();
	}
	/**
	 * gets the next square in the Stack
	 * @return  the next square
	 */
	@Override
	public Square next() 
	{
		return stack.peek();
	}
	/**
	 * sets the stack to a new stack
	 * @param s  the stack
	 */
	@Override
	public void setStack(MyStack s) {
		stack = s;
	}
	/**
	 * gets the stack
	 * @return  the stack
	 */
	@Override
	public MyStack getStack() {
		return stack;
	}
}
