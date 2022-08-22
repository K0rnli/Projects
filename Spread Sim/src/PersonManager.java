
import java.util.*;

public class PersonManager 
{
	private ArrayList<Person> CurrentHistory;
	private ArrayList<ArrayList<Person>> history;
	private int time;
	private int totalPeople;
	private int outOf;
	
	
	public PersonManager(int numPeople, int d, Masks m, int o)
	{
		//Makes Day 0
		CurrentHistory = new ArrayList<Person>();
		totalPeople = numPeople;
		history = new ArrayList<ArrayList<Person>>();
		time = 0;
		CurrentHistory = createNewPersonArray(numPeople, d, m, o);
		outOf = o;
		history.add(CurrentHistory);

	}
	
	//Creates new Day
	public void updateHistory(ArrayList<Person> temp)
	{
		//Blank day
		ArrayList<Person> list = new ArrayList<Person>();
		//Transfers Data from previous day to avoid aliasing
		for(int i = 0; i < temp.size(); i++)
		{
			list.add(new Person(temp.get(i).getMask(), temp.get(i).getHealth(), temp.get(i).getSick(), temp.get(i).getPosX(), temp.get(i).getPosY(), temp.get(i).getDistance(), temp.get(i).getHasLine(), temp.get(i).getXStart(), temp.get(i).getYStart(), temp.get(i).getXEnd(), temp.get(i).getYEnd(), temp.get(i).getImmune()));
		}
		//Updates Array
		for(int i = 0; i < list.size(); i++)
		{
			list.get(i).update(temp);
		}
		//Adds Array to list
		history.add(list);
	}
	
	//Finds designated day/ If it doesn't exist it creates it
	public ArrayList<Person> getHistory(int day)
	{
		time = day;
		//Checks if day Exists
		while(history.size() <= time)
		{
			//Creates it
			updateHistory(history.get(time-1));
		}
		//Returns Data on that day
		return history.get(time);
	}
	
	//Creates New array with 1 infected person containing parameters sent from the Details Page
	public ArrayList<Person> createNewPersonArray(int numPeople, int d, Masks m, int o)
	{
		int outOfN = o;
		int iCount = outOfN;
		ArrayList<Person> list = new ArrayList<Person>();
		for(int i = 0; i < numPeople; i++)
		{
			//Infected Person
			if(i == 0)
			{
				Person p = new Person(m, 50.0, true, (int)(Math.random()*991)+5, (int)(Math.random()*991)+5, d, false, 0, 0, 0, 0);
				list.add(p);
			}
			//Non Infected People
			else
			{
				Person p = new Person(m, 100.0, false, (int)(Math.random()*911)+30, (int)(Math.random()*911)+30, d, false, 0, 0, 0, 0);
				while(p.isOverlap(list))
				{
					p = new Person(m, 100.0, false, (int)(Math.random()*911)+30, (int)(Math.random()*911)+30, d, false, 0, 0, 0, 0);
				}
				if(iCount == 1)
				{
					p.setImmune(true);
					iCount = outOfN;
				}
				else
					iCount--;
				list.add(p);
			}
		}
		return list;
	}
}
