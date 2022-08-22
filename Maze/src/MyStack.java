import java.util.EmptyStackException;

public class MyStack 
{
	private Square[] stack;
	private int size;
	
	/**
	 * Constructs the stack without a initial size
	 */
	public MyStack()
	{
		stack = new Square[1];
		size = 0;
	}
	/**
	 * Constructs the stack with a initial size
	 */
	public MyStack(int initCap)
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
	 * gets the 
	 * @return
	 */
	public String path()
	{
		String ret = "";
		if(size == 0)
			return "Unsolveable";
		for(int i = 0; i < size; i++)
		{
			ret = ret + "[" + stack[i].getRow() + ", " + stack[i].getCol() + "]";	
			if(i != size-1)
				ret += "->";
		}
		return ret;
	}
	public void Solved()
	{
		for(int i = 0; i < size; i++)
		{
			stack[i].setStatus('x');
		}
	}
	
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
