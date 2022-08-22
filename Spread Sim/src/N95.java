public class N95 implements Masks
{
	//Changes distance effectiveness of the virus
	public double getRecieveRate()
	{
		return 0.1;
	}
	@Override
	public String toString()
	{
		return "N95";
	}
}
