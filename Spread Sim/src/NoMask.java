
public class NoMask implements Masks
{
	//Changes distance effectiveness of the virus
	public double getRecieveRate()
	{
		return 1.00;
	}
	@Override
	public String toString()
	{
		return "None";
	}
}
