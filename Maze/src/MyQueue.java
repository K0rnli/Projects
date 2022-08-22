import java.util.EmptyStackException;

public class MyQueue<T>
{
	private MyLinkedList<T> queue;
	/**
	 * Constructs the queue without a initial size
	 */
	public MyQueue()
	{
		queue = new MyLinkedList<T>();
	}
	/**
	 * returns whether the queue is empty or not
	 * @return  if the stack is empty or not
	 */
	public boolean isEmpty()
	{
		return queue.isEmpty();
	}
	/**
	 * peeks at the front of the queue
	 * @return  the square at the front of the queue
	 */
	public T peek()
	{
		try
		{
			return queue.get(0);
		}
		catch(Exception e)
		{
			throw new EmptyStackException();
		}
	}
	/**
	 * removes the square at the front of the queue and returns it
	 * @return  the removed square
	 */
	public T remove()
	{
		try
		{
			return queue.remove(0);
		}
		catch(Exception e)
		{
			throw new EmptyStackException();
		}
	}
	/**
	 * adds a square to the back of the queue
	 * @param item  the square at the back of the queue
	 */
	public void add(T item)
	{
		queue.add(item);
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
		for(int i = 0; i < queue.size(); i++)
		{
			ret += queue.get(i);
			if(i != queue.size()-1)
				ret += ", ";
		}
		return ret + "]";
	}
}