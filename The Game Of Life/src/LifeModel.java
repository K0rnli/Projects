import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;
	private String start;
	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
		timer = new Timer(50, this);
		int r, c;
		start = fileName;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}
	
	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer.setCoalesce(true);
		timer.start();
	}
	
	/**
	 * Selects a new file to use as the map
	 * @param  c  the file
	 * @throws FileNotFoundException
	 */
	public void switchMap(int c) throws FileNotFoundException
	{
		switch(c)
    	{
    	case 0: start = null;
    		break;
    	case 1: start = "blinker.lif";
			break;
    	case 2: start = "glgun13.lif";
			break;
    	case 3: start = "penta.lif";
			break;
    	case 4: start = "rotate.lif";
			break;
    	case 5: start = "static.lif";
			break;
    	case 6: start = "tumbler.lif";
			break;
    	}
		if(timer.isRunning())
			timer.stop();
		reset();
	}
	/**
	 * Restarts the array
	 * If it is a random array it generates a new random array
	 * @throws FileNotFoundException
	 */
	public void reset() throws FileNotFoundException
	{
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( start == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(new File(start));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView.updateView(grid);
		if(timer.isRunning())
			timer.stop();
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
	}
	
	public void updateFrame()
	{
		oneGeneration();
		myView.updateView(grid);
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		for(int y = 0; y < SIZE; y++)
		{
			for(int x = 0; x < SIZE; x++)
			{
				int count = 0;
				
				for(int g = 0; g < 9; g++)
				{
					if((x-1)+g%3 >= 0 && (x-1)+g%3 < SIZE && (y-1)+g/3 >= 0 && (y-1)+g/3 < SIZE && g != 4 && grid[(y-1)+g/3][(x-1)+g%3].isAliveNow())
						count++;
				}
				//Makes a tile alive or dead in the next update
				grid[y][x].setAliveNext((count == 2 && grid[y][x].isAliveNow()) || count == 3);
			}
		}
		//Updates the table
		for(int x = 0; x < SIZE; x++)
		{
			for(int y = 0; y < SIZE; y++)
			{
				grid[y][x].setAliveNow(grid[y][x].isAliveNext());
			}
		}
	}
}

