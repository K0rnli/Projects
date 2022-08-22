//this draws the new frame everytime constructor is called
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;
import java.util.Random;
import java.util.*;

public class DrawFrame extends Canvas implements Runnable
{

	public void paint( Graphics window )		//Note: java automatically calls paint method
	{
		window.clearRect(310, 0, 1250, 1000);
	}
	
	public void newFrame(Graphics window, ArrayList<Person> p)
	{
		window.setClip(310, 0, 1250, 1000);
		window.clearRect(310, 0, 1250, 1000);
		//Checks and draws lines connecting the infected to their infectees
		for(Person person : p)
		{
			if(person.getHasLine())
			drawLines(window, person.getXStart()+310, person.getYStart(), person.getXEnd()+310, person.getYEnd());
		}
		//Draws People
		for(Person person : p)
		{
			drawFrame(window, person.getPosX()+310, person.getPosY(), 5, person.getHealth(), person.getImmune());
		}
		for(Person person : p)
		{
			if(person.getSick() && person.getHealth() != 0)
				drawRange(window, person.getPosX()+310, person.getPosY(), (int)(20*(person.getDistance()*person.getMask().getRecieveRate())+5));
		}
	}
	
	//Draws People
	public void drawFrame(Graphics window, int x, int y, int radius, double health, boolean immune)
	{
		window.setColor(new Color(252 - (int)(187*(health/100)), 3 + (int)(249*(health/100)), 3));
		if(immune)
			window.setColor(new Color(32, 3, 252));
		window.fillOval(x-radius, y-radius, 2 * radius, 2 * radius);
	}
	
	//Draws Lines
	public void drawLines(Graphics window, int x1, int y1, int x2, int y2)
	{
		window.setColor(new Color(252, 3, 3));
		window.drawLine(x1, y1, x2, y2);
	}
	
	//Draws Range
	public void drawRange(Graphics window, int x, int y, int radius)
	{
		window.setColor(new Color(252, 3, 3));
		window.drawOval(x-radius, y-radius, 2 * radius, 2 * radius);
	}


	public void run()
	{
		try{
		  	Thread.currentThread().sleep(3);
		}
		catch(Exception e)
		{
			
		}
	}
}

