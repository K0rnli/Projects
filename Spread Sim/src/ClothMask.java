public class ClothMask implements Masks
{
	//Changes distance effectiveness of the virus
	public double getRecieveRate()
	{
		return 0.5;
	}
	@Override
	public String toString()
	{
		return "Cloth Mask";
	}
}
