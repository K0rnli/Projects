import java.util.ArrayList;

public class Person 
{
	private Masks mask;
	private double health;
	private boolean sick;
	private int posx;
	private int posy;
	private int distance;
	private int lineXStart;
	private int lineYStart;
	private int lineXEnd;
	private int lineYEnd;
	private boolean hasLine;
	private boolean isImmune;
	
	public Person(Masks m, double h, boolean s, int x, int y, int d, boolean hl, int lsx, int lsy, int lex, int ley)
	{
		mask = m;
		health = h;
		sick = s;
		posx = x;
		posy = y;
		distance = d;
		lineXStart = lsx;
		lineYStart = lsy;
		lineXEnd = lex;
		lineYEnd = ley;
		hasLine = hl;
		isImmune = false;
	}
	public Person(Masks m, double h, boolean s, int x, int y, int d, boolean hl, int lsx, int lsy, int lex, int ley, boolean i)
	{
		mask = m;
		health = h;
		sick = s;
		posx = x;
		posy = y;
		distance = d;
		lineXStart = lsx;
		lineYStart = lsy;
		lineXEnd = lex;
		lineYEnd = ley;
		hasLine = hl;
		isImmune = i;
	}
	
	//GETTER METHODS
	
	public Masks getMask()
	{
		return mask;
	}
	public double getHealth()
	{
		return health;
	}
	public boolean getSick()
	{
		return sick;
	}
	public int getPosX()
	{
		return posx;
	}
	public int getPosY()
	{
		return posy;
	}
	public int getDistance()
	{
		return distance;
	}
	public int getXStart()
	{
		return lineXStart;
	}
	public int getYStart()
	{
		return lineYStart;
	}
	public int getXEnd()
	{
		return lineXEnd;
	}
	public int getYEnd()
	{
		return lineYEnd;
	}
	public boolean getHasLine()
	{
		return hasLine;
	}
	public boolean getImmune()
	{
		return isImmune;
	}
	public void setImmune(boolean i)
	{
		isImmune = i;
	}
	
	//Checks for Infected within area around itself / decrements health if infected found / makes line connecting itself to the infected infecting it
	public void update(ArrayList<Person> p)
	{
		boolean near = false;
		if(!sick)
			for(int i = 0; i < p.size(); i++)
			{
				//Checks around in a circle for infected
				if(Math.sqrt(Math.pow(getPosX() - p.get(i).getPosX(), 2) + Math.pow(getPosY() - p.get(i).getPosY(), 2)) <= 20*(distance*mask.getRecieveRate()) && p.get(i).getSick() && !isImmune)
				{
					near = true;
					//Checking if it is the first Infected infecting them
					if(!hasLine)
					{
						//Making connection between them
						hasLine = true;
						lineXStart = getPosX();
						lineXEnd = p.get(i).getPosX();
						lineYStart = getPosY();
						lineYEnd = p.get(i).getPosY();
					}
				}
			}
		//Makes health drop and become infected
		if(near || sick)
		{
			reduceHealth();
			isSick();
		}
	}
	//Checks if People are going to overlap bodies
	public boolean isOverlap(ArrayList<Person> p)
	{
		for(int i = 0; i < p.size(); i++)
		{
			//Searches area around itself to make sure no overlaps
			if(Math.sqrt(Math.pow(getPosX() - p.get(i).getPosX(), 2) + Math.pow(getPosY() - p.get(i).getPosY(), 2)) > 10)
			{
				return false;
			}
		}
		return true;
	}
	//Reduces health by random amount
	public void reduceHealth()
	{
		if(health > 0)
			health -= (int)(Math.random()*6);
		if(health < 0)
			health = 0;
	}
	//Makes sick
	public void isSick()
	{
		if(health <= 50)
			sick = true;
	}
	
	@Override
	public String toString()
	{
		return "[ " + health + " ]";
	}
}
