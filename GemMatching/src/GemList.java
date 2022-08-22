public class GemList 
{
	private MyLinkedList<Gem> list;
	/**
	 * the Constructor
	 */
	public GemList()
	{
		list = new MyLinkedList<Gem>();
	}
	/**
	 * gets the size of the list
	 * @return the size of the list
	 */
	public int size()
	{
		return list.size();
	}
	/**
	 * draws all of the gems on a specified y coordinate
	 * @param y the y coordinate
	 */
	public void draw(double y)
	{
		int counter = 0;
		for(int i = 0; i < size(); i++)
		{
			if(list.get(i) == null)
				break;
			list.get(i).draw(GemGame.indexToX(counter), y);
    		counter++;
		}
	}
	/**
	 * the toString
	 * @return the toString
	 */
	@Override
	public String toString()
	{
		String ret = "";
		
		for(int i = 0; i < size(); i++) 
		{
			if(list.get(i) == null)
				break;
    		ret = ret + list.get(i) + " -> ";
		}
		if(ret.length() > 2)
			return ret.substring(0, ret.length()-4);
		return "<none>";
	}
	/**
	 * inserts gem at the index and shifts whatever was there prior forward
	 * @param gem  the added gem
	 * @param index  target index
	 */
	public void insertBefore(Gem gem, int index)
	{
		while(index > size())
			index--;
		list.add(gem, index);
	}
	/**
	 * calculates the current score
	 * @return the current score
	 */
	public int score()
	{
		if(list.get(0) == null)
			return 0;
		GemType g = list.get(0).getType();
		int streak = 0;
		int streakTotal = 0;
		int score = 0;
		for(int i = 0; i < size(); i++) {
			if(list.get(i) == null)
				break;
    		if(list.get(i).getType() == g)
    		{
    			streak++;
    			streakTotal += list.get(i).getPoints();
    		}
    		else
    		{
    			score += streak * streakTotal;
    			streak = 1;
    			streakTotal = list.get(i).getPoints();
    			g = list.get(i).getType();
    		}
    	}
		score += streak * streakTotal;
		return score;
	}
	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);		
	}	
}
