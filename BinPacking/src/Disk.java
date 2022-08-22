import java.util.ArrayList;

public class Disk implements Comparable<Disk>
{
	private ArrayList<Integer> memory;
	private static int count = 0; // current count for disks
	private int id;  // the disks id
	/**
	 * the constructor
	 */
	public Disk()
	{
		memory = new ArrayList<Integer>();
		id = count++;
	}
	/**
	 * checks if the data can fit into the disk
	 * @param i  the size of the data
	 * @return  if the data can fit
	 */
	public boolean fit(int i)
	{
		return total() + i <= 1000000;
	}
	/**
	 * the total data in the disk
	 * @return  the total data
	 */
	public int total()
	{
		int t = 0;
		for(int g : memory)
			t += g;
		return t;
	}
	/**
	 * inserts specified data into the disk
	 * @param i  the data
	 */
	public void insert(int i)
	{
		memory.add(i);
	}
	@Override
	public int compareTo(Disk i) {
		return total() - i.total();
	}
	@Override
	public String toString()
	{
		String s = (1000000- total()) + ": ";
		for(int g : memory)
			s += g + " ";
		return id + " " + s;
	}
	/**
	 * gets the number of disks
	 * @return  the number of disks
	 */
	public static int getCount() 
	{
		return count;
	}
}
