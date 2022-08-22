import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BloxorzModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 30;
	private Tile[][] grid;
	private String start;
	private Player play;
	private int level;
	private int max;
	BloxorzView myView;

	/** Construct a new model using a particular file */
	public BloxorzModel(BloxorzView view, String fileName) throws IOException
	{       
		int r, c;
		level = 0;
		max = 0;
		start = fileName;
		grid = new Tile[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new Tile();

		if ( fileName == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setTile(true);
				}
			}
			play = new Player(0,0);
		}
		else
		{                 
			Scanner input = new Scanner(getClass().getResourceAsStream(start));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				int l = input.nextInt();
				if(l == 1)
					grid[count/30][count%30].setTile(true);
				else if(l == 2)
				{
					grid[count/30][count%30].setTile(true);
					grid[count/30][count%30].setGoal(true);
				}
				else if(l == 3)
				{
					grid[count/30][count%30].setTile(true);
					grid[count/30][count%30].setFalling(true);
				}
			}
			int x = input.nextInt();
			int y = input.nextInt();
			play = new Player(x, y);
			
			if(input.hasNextInt())
			{
				int t = input.nextInt();
				for(int i = 0; i < t; i++)
				{
					int bX = input.nextInt();
					int bY = input.nextInt();
					int type = input.nextInt();
					if(type == 1 || type == 2)
					{
						int t1 = input.nextInt();
						for(int j = 0; j < t1; j++)
						{
							int aX = input.nextInt();
							int aY = input.nextInt();
							int actType = input.nextInt();
							grid[bY][bX].setButton(true);
							grid[bY][bX].setButtonType(type);
							grid[bY][bX].addTile(aX, aY, actType);
						}
					}
					else if(type == 3)
					{
						int p1X = input.nextInt();
						int p1Y = input.nextInt();
						int p2X = input.nextInt();
						int p2Y = input.nextInt();
						grid[bY][bX].setSplitter(true);
						grid[bY][bX].addSplitLoc(p1X, p1Y, p2X, p2Y);
					}
				}
			}
			input.close();
		}
		oneGeneration();
		myView = view;
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public BloxorzModel(BloxorzView view) throws IOException
	{
		this(view, null);
	}
	
	public void switchMap() throws FileNotFoundException
	{
			start = "Level" + (level+1) + ".txt";
		System.out.println(start);
		reset();
	}
	

	public void reset() throws FileNotFoundException
	{
		int r, c;
		grid = new Tile[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new Tile();

		if ( start == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setTile(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(getClass().getResourceAsStream(start));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				int l = input.nextInt();
				if(l == 1)
					grid[count/30][count%30].setTile(true);
				else if(l == 2)
				{
					grid[count/30][count%30].setTile(true);
					grid[count/30][count%30].setGoal(true);
				}
				else if(l == 3)
				{
					grid[count/30][count%30].setTile(true);
					grid[count/30][count%30].setFalling(true);
				}
			}
			int x = input.nextInt();
			int y = input.nextInt();
			play = new Player(x, y);
			if(input.hasNextInt())
			{
				int t = input.nextInt();
				for(int i = 0; i < t; i++)
				{
					int bX = input.nextInt();
					int bY = input.nextInt();
					int type = input.nextInt();
					if(type == 1 || type == 2)
					{
						int t1 = input.nextInt();
						for(int j = 0; j < t1; j++)
						{
							int aX = input.nextInt();
							int aY = input.nextInt();
							int actType = input.nextInt();
							grid[bY][bX].setButton(true);
							grid[bY][bX].setButtonType(type);
							grid[bY][bX].addTile(aX, aY, actType);
						}
					}
					else if(type == 3)
					{
						int p1X = input.nextInt();
						int p1Y = input.nextInt();
						int p2X = input.nextInt();
						int p2Y = input.nextInt();
						grid[bY][bX].setSplitter(true);
						grid[bY][bX].addSplitLoc(p1X, p1Y, p2X, p2Y);
					}
				}
			}
			input.close();
		}
		oneGeneration();
		myView.updateView(grid);
	}

	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
	}
	
	public void updateFrame(int u)
	{
		boolean check = false;
		if(u == 37 || u == 38 || u == 39 || u == 40 || u == 32)
		{
			if(play.isConnected() && u != 32)
			{
				if(u == 37)
					play.left();
				else if(u == 38)
					play.up();
				else if(u == 39)
					play.right();
				else if(u == 40)
					play.down();
				play.setMovement(true);
				check = !check;
			}
			else if(u == 32 && !play.isConnected())
			{
				if(play.isSelected() == 1)
					play.setSelected(2);
				else
					play.setSelected(1);
				play.setMovement(false);
				check = !check;
			}
			else if(play.isSelected() == 1)
			{
				if(u == 37)
					play.left1();
				else if(u == 38)
					play.up1();
				else if(u == 39)
					play.right1();
				else if(u == 40)
					play.down1();
				play.setMovement(true);
				check = !check;
			}
			else if(play.isSelected() == 2)
			{
				if(u == 37)
					play.left2();
				else if(u == 38)
					play.up2();
				else if(u == 39)
					play.right2();
				else if(u == 40)
					play.down2();
				play.setMovement(true);
				check = !check;
			}
		}
		else if(u == 78)
		{
			if(level != 32 && level != max)
				level++;
			try {
				switchMap();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if(u == 66)
		{
			if(level != 0)
				level--;
			try {
				switchMap();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if(u == 82)
			try {
				reset();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		if(check)
			oneGeneration();
		myView.updateView(grid);
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		for(int x = 0; x < SIZE; x++)
		{
			for(int y = 0; y < SIZE; y++)
			{
				grid[y][x].setPlayerNext(false);
				grid[y][x].setP1Next(false);
				grid[y][x].setP2Next(false);
				grid[y][x].setPlayer(false);
				grid[y][x].setP1(false);
				grid[y][x].setP2(false);
				grid[y][x].setSelectedNext(false);
			}
		}
		
		if(grid[play.getP1Y()][play.getP1X()].isButtonNow() || grid[play.getP2Y()][play.getP2X()].isButtonNow() && play.getMovement())
		{
			if(grid[play.getP1Y()][play.getP1X()].getButtonType() == 2 && play.isStacked())
			{
				ArrayList<Integer> list = grid[play.getP1Y()][play.getP1X()].getTileList();
				for(int i = 0; i < list.size(); i += 3)
				{
					int act = list.get(i+2);
					int pX = list.get(i);
					int pY = list.get(i+1);
					if(act == 1)
						grid[pY][pX].setTile(!grid[pY][pX].isTileNow());
					else if(act == 2)
						grid[pY][pX].setTile(true);
					else if(act == 3)
						grid[pY][pX].setTile(false);
				}
			}
			else if(grid[play.getP1Y()][play.getP1X()].getButtonType() == 1 && (play.isSelected() == 1 || play.isSelected() == -1))
			{
				ArrayList<Integer> list = grid[play.getP1Y()][play.getP1X()].getTileList();
				for(int i = 0; i < list.size(); i += 3)
				{
					int act = list.get(i+2);
					int pX = list.get(i);
					int pY = list.get(i+1);
					if(act == 1)
						grid[pY][pX].setTile(!grid[pY][pX].isTileNow());
					else if(act == 2)
						grid[pY][pX].setTile(true);
					else if(act == 3)
						grid[pY][pX].setTile(false);
				}
			}
			else if(grid[play.getP2Y()][play.getP2X()].getButtonType() == 1 && (play.isSelected() == 2 || play.isSelected() == -1))
			{
				ArrayList<Integer> list = grid[play.getP2Y()][play.getP2X()].getTileList();
				for(int i = 0; i < list.size(); i += 3)
				{
					int act = list.get(i+2);
					int pX = list.get(i);
					int pY = list.get(i+1);
					if(act == 1)
						grid[pY][pX].setTile(!grid[pY][pX].isTileNow());
					else if(act == 2)
						grid[pY][pX].setTile(true);
					else if(act == 3)
						grid[pY][pX].setTile(false);
				}
			}
		}
		else if(grid[play.getP1Y()][play.getP1X()].isSplitterNow() && play.isStacked())
		{
			ArrayList<Integer> t = grid[play.getP1Y()][play.getP1X()].getSplitLoc();
			play.setConnected(false);
			play.setSelected(1);
			play.setP1X(t.get(0));
			play.setP1Y(t.get(1));
			play.setP2X(t.get(2));
			play.setP2Y(t.get(3));
		}
		if((play.getP1Y()+1 == play.getP2Y() && play.getP1X() == play.getP2X()) || (play.getP1Y()-1 == play.getP2Y() && play.getP1X() == play.getP2X()) || (play.getP1Y() == play.getP2Y() && play.getP1X()+1 == play.getP2X()) || (play.getP1Y() == play.getP2Y() && play.getP1X()-1 == play.getP2X()))
		{
			play.setConnected(true);
			play.setSelected(-1);
		}
		if(play.isSelected() == 1)
		{
			grid[play.getP1Y()][play.getP1X()].setSelectedNext(true);
		}
		else if(play.isSelected() == 2)
		{
			grid[play.getP2Y()][play.getP2X()].setSelectedNext(true);
		}
		if(!play.isConnected())
		{
			grid[play.getP1Y()][play.getP1X()].setP1Next(true);
			grid[play.getP2Y()][play.getP2X()].setP2Next(true);
		}
		grid[play.getP1Y()][play.getP1X()].setPlayerNext(true);
		grid[play.getP2Y()][play.getP2X()].setPlayerNext(true);
		
		for(int x = 0; x < SIZE; x++)
		{
			for(int y = 0; y < SIZE; y++)
			{
				grid[y][x].setPlayer(grid[y][x].isPlayerNextNow());
				if(!play.isConnected())
				{
					grid[y][x].setP1(grid[y][x].isP1NextNow());
					grid[y][x].setP2(grid[y][x].isP2NextNow());
					grid[y][x].setSelected(grid[y][x].isSelectedNextNow());
				}
			}
		}
		
		if(!grid[play.getP1Y()][play.getP1X()].isTileNow())
			try {
				this.reset();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		else if(!grid[play.getP2Y()][play.getP2X()].isTileNow())
			try {
				this.reset();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		else if(play.isStacked() && grid[play.getP1Y()][play.getP1X()].isFallingNow())
			try {
				this.reset();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		else if(play.isStacked() && grid[play.getP1Y()][play.getP1X()].isGoalNow())
		{
			max++;
			level++;
			try {
				switchMap();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

