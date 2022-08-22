import java.util.EventObject;
//Connects DetailsPannel to the MainFrame
public class DetailEventDayChange extends EventObject 
{
	private int day;
	public DetailEventDayChange(Object source, int day)
	{
		super(source);
		this.day = day;
	}
	public int getDay()
	{
		return day;
	}
}
