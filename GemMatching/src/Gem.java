enum GemType {
    GREEN, BLUE, ORANGE; 
}

public class Gem 
{	
	private int points; // the point value of the gem
	private GemType type; // the color of the gem
	
	/**
	 * constructs a gem with random color and point value
	 */
	public Gem()
	{
		points = ((int)(Math.random()*11))*5;
		int rand = (int)(Math.random()*3);
		if(rand == 0)
			type = GemType.GREEN;
		else if(rand == 1)
			type = GemType.BLUE;
		else
			type = GemType.ORANGE;
	}
	/**
	 * constructs a gem with a given color and points
	 * @param type  the color
	 * @param points  the points
	 */
	public Gem(GemType type, int points)
	{
		this.points = points;
		this.type = type;
	}
	/**
	 * the toString
	 * @return the toString
	 */
	@Override
	public String toString()
	{
		return type + " " + points;
	}
	/**
	 * gets the color of the gem
	 * @return  the color of the gem
	 */
	public GemType getType()
	{
		return type;
	}
	/**
	 * gets the points of the gem
	 * @return the points of the gem
	 */
	public int getPoints()
	{
		return points;
	}
	/**
	 * draws the gem at specified location
	 * @param x  the x
	 * @param y  the y
	 */
	public void draw(double x, double y)
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		if(type == GemType.GREEN)
		{
			StdDraw.picture(x, y, "gem_green.png");
			StdDraw.text(x, y,points + "");
		}
		else if(type == GemType.BLUE)
		{
			StdDraw.picture(x, y, "gem_blue.png");
			StdDraw.text(x, y,points + "");
		}
		else
		{
			StdDraw.picture(x, y, "gem_orange.png");
			StdDraw.text(x, y,points + "");
		}
	}
	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
