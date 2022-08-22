
public class MyLinkedList <T>
{
	Nonsense head; // the head of the list
	private int size = 0; // the size of the list
	public class Nonsense<T> 
	{
		private T value; // value of item in list
		Nonsense<T> next; // next item in the list
		
		/**
		 * The constructor
		 * @param value  value of item in the list
		 */
		public Nonsense(T value) { this.value = value; }
		
		/**
		 * gets the value of the item
		 * @return  the value
		 */
		public T getValue() { return this.value; }
		
		/**
		 * sets the value of the item
		 * @param v  the new value
		 */
		public void setValue(T v) { value = v;}
		
		/**
		 * gets the next item in the list
		 * @return  the next item
		 */
		public Nonsense<T> getNext() { return this.next; }
		
		/**
		 * sets the next item
		 * @param s  the next item
		 */
		public void setNext(Nonsense<T> s) { next = s; }
		
		/**
		 * toString
		 * @return the toString
		 */
		@Override 
		public String toString() 
		{ 
			return "" + value; 
		}
	}
	/**
	 * constructor
	 */
	public MyLinkedList()
	{
		head = null;
	}
	/**
	 * constructor with initial value
	 * @param newVal  the initial value
	 */
	public MyLinkedList(T newVal)
	{
		head = new Nonsense<T>(newVal);
	}
	
	/**
	 * adds one more item to the list
	 * @param val  the value of the item
	 */
	public void add(T val)
	{
		Nonsense newNode = new Nonsense<T>(val);
		if (head == null) 
            head = newNode;
        else {
        	Nonsense last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
		size++;
	}
	/**
	 * searches for target item within the list
	 * @param target  the target item
	 * @return  if the item exists
	 */
	public boolean contains(T target)
	{
		return indexOf(target) != -1;
	}
	/**
	 * gets the item at the specified index
	 * @param index  the index of item
	 * @return  the specified item
	 */
	public T get(int index)
	{
		Nonsense at = head;
		if(index == 0 && at != null)
		{
			return (T) at.getValue();
		}
		int counter = 0;
        while (at != null) {
 
            if (counter == index) {
                return (T)at.getValue();
            }
            else {
                at = at.next;
                counter++;
            }
        }
        throw new IndexOutOfBoundsException();
	}
	/**
	 * looks for a targeted item's location
	 * @param target  the targeted item
	 * @return the targeted items location or -1 if it doesn't exist
	 */
	public int indexOf(T target)
	{
		Nonsense at = head;
		for(int i = 0; at != null; i++)
			if(((T)at.getValue()).equals(target))
				return i;
			else 
				at = at.next;
		return -1;
	}
	/**
	 * sets a specified index to the new value
	 * @param newVal  the new value
	 * @param index  the index wanting to be changed
	 */
	public void set(T newVal, int index)
	{
		Nonsense at = head;
		while(index-- != 0 && at != null)
			at = at.next;
		if(!(index == 0 || index == -1))
			throw new IndexOutOfBoundsException();
		at.setValue(newVal);
	}
	/**
	 * gets the size of the list
	 * @return  the size of the list
	 */
	public int size()
	{
		return size;
	}
	/**
	 * returns whether the list is empty or not
	 * @return  the state of the list
	 */
	public boolean isEmpty()
	{
		
		return size == 0;
	}
	/**
	 * removes the specified index
	 * @param index  the index of the removed item
	 * @return  the removed item
	 */
	public T remove(int index)
	{
		Nonsense at = head;
		Nonsense prev = null;
		if(index == 0 && at != null)
		{
			size--;
			T val = (T)at.getValue();
			head = at.next;
			return val;
		}
		int counter = 0;
        while (at != null) {
 
            if (counter == index) {
            	size--;
            	T val = (T)at.getValue();
                prev.next = at.next;
                return val;
            }
            else {
                prev = at;
                at = at.next;
                counter++;
            }
        }
        throw new IndexOutOfBoundsException();
	}
	/**
	 * Adds a item at a specified index
	 * @param val  the new value
	 * @param index  the index
	 */
	public void add(int val, int index)
	{
		Nonsense at = head;
		Nonsense add = new Nonsense(val);
		Nonsense prev = null;
		if(index == 0)
		{
			head = add;
			add.next = at;
		}
		else
		{
			int counter = 0;
        	while (at != null) {
 
        		if (counter == index) {
        			
        			prev.next = add;
        			add.next = at;
        			break;
        		}
        		else {
        			prev = at;
        			at = at.next;
        			counter++;
        		}
        	}
		}
		size++;
	}
	
	/**
	 * the toString
	 * @return the toString
	 */
	@Override
	public String toString()
	{
		String ret = "[";
		Nonsense at = head;
		while (at != null) {
			 
    		ret = ret + at.getValue() + ", ";
    		at = at.next;
    	}
		if(ret.length() > 2)
			return ret.substring(0, ret.length()-2) + "]";
		return ret + "]";
	}
	
}
