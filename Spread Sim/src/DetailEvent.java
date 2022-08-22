import java.util.EventObject;
//Connects DetailsPannel to the MainFrame
public class DetailEvent extends EventObject 
{
	private int numPeople;
	private int distance;
	private Masks mask;
	private int outOf;
	public DetailEvent(Object source, int numPeople, int distance, Masks maskType, int o)
	{
		super(source);
		this.numPeople = numPeople;
		this.distance = distance;
		mask = maskType;
		outOf = o;
	}
	public int getNumPeople()
	{
		return numPeople;
	}
	public int getDistance()
	{
		return distance;
	}
	public Masks getMask()
	{
		return mask;
	}
	public int getOutOf()
	{
		return outOf;
	}
}
