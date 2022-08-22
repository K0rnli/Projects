import java.util.EmptyStackException;

public class MyStackV2 
{
	private Square[] stack;
	private int size;
	/**
	 * Constructs the stack without a initial size
	 */
	public MyStackV2()
	{
		stack = new Square[1];
		size = 0;
	}
	/**
	 * Constructs the stack with a initial size
	 */
	public MyStackV2(int initCap)
	{
		stack = new Square[initCap];
		size = 0;
	}
	/**
	 * returns whether the stack is empty or not
	 * @return  if the stack is empty or not
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
	/**
	 * peeks at the top of the Stack
	 * @return  the square at the top of the Stack
	 */
	public Square peek()
	{
		try
		{
			return stack[size-1];
		}
		catch(Exception e)
		{
			throw new EmptyStackException();
		}
	}
	/**
	 * removes the square at the top of the stack and returns it
	 * @return  the removed square
	 */
	public Square pop()
	{
		try
		{
			size--;
			return stack[size];
		}
		catch(Exception e)
		{
			throw new EmptyStackException();
		}
	}
	/**
	 * adds a square to the top of the stack
	 * @param item  the square at the top of the stack
	 */
	public void push(Square item)
	{
		if(size == stack.length)
			doubleCapacity();
		stack[size] = item;
		size++;
	}
	/**
	 * doubles the size of the array
	 */
	private void doubleCapacity()
	{
		
		Square[] temp = new Square[size*2];
		for(int i = 0; i < size; i++)
		{
			temp[i] = stack[i];
		}
		stack = temp;
	}
	/**
	 * gets the path of the squares
	 * @param s  the square
	 * @param c  for formating
	 * @return  string of the path
	 */
	public String path(Square s, int c)
	{
		if(s.getPre() == null)
			return "[" + s.getRow() + ", " + s.getCol() + "]";
		if(c == 8)
			return path(s.getPre(), 0) +  "->" + "\n" + "[" + s.getRow() + ", " + s.getCol() + "]" ;
		return path(s.getPre(), c+1) + "->[" + s.getRow() + ", " + s.getCol() + "]";
	}
	/**
	 * changes tile and all preceding tiles to solve
	 * @param s  the solved square
	 */
	public void Solved(Square s)
	{
		try 
		{
			s.setStatus('x');
			Solved(s.getPre());
		} 
		catch (Exception e) 
		{}
	}
	/**
	 * The toString
	 * @return  string of the stack
	 */
	@Override
	public String toString()
	{
		String ret = "[";
		for(int i = 0; i < size; i++)
		{
			ret += stack[i];
			if(i != size-1)
				ret += ", ";
		}
		return ret + "]";
	}
}
