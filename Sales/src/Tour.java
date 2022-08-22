public class Tour 
{
	private Node head;
	private class Node
	{
		Point p;
		Node next;
		/**
		 * constructs a node with
		 * @param p
		 */
		public Node(Point p)
		{
			this.p = p;
		}
		/**
		 * constructs the node with a next node
		 * @param p  the node
		 * @param n  the next node
		 */
		public Node(Point p, Node n)
		{
			this.p = p;
			next = n;
		}
		
		/**
		 * inserts a node at a specified index using recursion
		 * @param point the node
		 * @param i the index
		 */
		public void insert(Point point, int i)
		{
			i--;
			if(i >= 0)
			{
				next.insert(point, i);
			}
			else
			{
				Node n = next;
				next = new Node( point, n );
			}
		}
		
		/**
		 * gets item in list using recursion
		 * @param index  the index of the item
		 * @return  the node at the location
		 */
		public Node get(int index)
		{
			index--;
			if(index >= 0)
			{ 
				return next.get(index); 
			}
			
			return this;
		}
	}
	/** create an empty tour */
	public Tour()
	{
		head = null;
	}
	
	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d)
	{
		head = new Node(a);
		head.next = new Node(b);
		head.next.next = new Node(c);
		head.next.next.next = new Node(d);
	}
	
	/** print tour (one point per line) to std output */
	public void show()
	{
		Node at = head;
		while (at != null) {
			System.out.println(at.p);
            at = at.next;
        }
	}
	
	/** draw the tour using StdDraw */
	public void draw()
	{
		Node at = head;
		while (at.next != null) {
			at.p.drawTo(at.next.p);
            at = at.next;
        }
		at.p.drawTo(head.p);
	}
	
	/** return number of nodes in the tour */
	public int size()
	{
		int counter = 0;
		Node at = head;
		while (at != null) {
			counter++;
            at = at.next;
        }
		return counter;
	}
	
	/** return the total distance "traveled", from start to all nodes and back to start */
	public double distance()
	{
		double distance = 0;
		Node at = head;
		if(at != null)
		{
			while (at.next != null) {
				distance += at.p.distanceTo(at.next.p);
	            at = at.next;
	        }
			distance += at.p.distanceTo(head.p);
		}
		return distance;

	}
	
	/** insert p using nearest neighbor heuristic */
    public void insertNearest(Point p) 
    {
    	Node at = head;
    	double near = Double.MAX_VALUE;
    	Node nearest = null;
    	Node nearestNext = null;
    	while (at != null) {
			if( near > at.p.distanceTo(p))
			{
            	near = at.p.distanceTo(p);
            	nearest = at;
            	nearestNext = at.next;
			}
            at = at.next;
        }
    	Node d = new Node(p);
    	if(nearest == null)
    		head = d;
    	else
    	{
    		nearest.next = d;
    		if(nearestNext != null)
    			d.next = nearestNext;
    	}
    }

	/** insert p using smallest increase heuristic */
    public void insertSmallest(Point p) 
    {
    	if( size() == 0 )
		{
			head = new Node(p);
			return;
		}
		
		double smallestIncrease = Double.POSITIVE_INFINITY;
		int index = 0;
		double originalD, newD;
		int size = size();
		for( int i = 1; i < size; i++)
		{
			originalD = head.get( i ).p.distanceTo( head.get( i - 1 ).p );//distance from A to B
			newD = p.distanceTo(head.get( i ).p) + p.distanceTo(head.get( i - 1 ).p);//distance from A to P to B
			if( newD - originalD <= smallestIncrease )
			{
				smallestIncrease = newD - originalD;
				index = i - 1;
			}
		}
		originalD = head.p.distanceTo( head.get( size - 1 ).p);//distance from first to last
		newD = p.distanceTo(head.p) + p.distanceTo(head.get( size - 1 ).p);//distance from first to P to last
		if( newD - originalD <= smallestIncrease )
		{
			smallestIncrease = newD - originalD;
			index = size - 1;
		}
		
		head.insert( p, index );
		size++;
    }
}