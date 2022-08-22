import java.awt.*;
import javax.swing.*;
import java.util.*;

/** The view (graphical) component */
public class BloxorzView extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static int SIZE = 30;

	/** store a reference to the current state of the grid */
    private Tile[][] grid;

    public BloxorzView()
    {
        grid = new Tile[SIZE][SIZE];
    }

    /** entry point from the model, requests grid be redisplayed */
    public void updateView( Tile[][] mg )
    {
        grid = mg;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int testWidth = getWidth() / (SIZE+1);
        int testHeight = getHeight() / (SIZE+1);
        // keep each life cell square
        int boxSize = Math.min(testHeight, testWidth);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, testWidth*35, testHeight*35);
        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
            	if(grid[r][c].isButtonNow())
            	{
            		ArrayList<Integer> t = grid[r][c].getTileList();
            		for(int i = 0; i < t.size(); i +=3)
            		{
            			grid[t.get(i+1)][t.get(i)].setSwitchable(true);
            		}
            	}
            }
        }
        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
            	int button = -1;
            	boolean split = false;
                if (grid[r][c] != null)
                {
                	if ((grid[r][c].isPlayerNow() && grid[r][c].isP1Now()) && grid[r][c].isSelectedNow())
                        g.setColor(Color.RED);
                	else if ((grid[r][c].isPlayerNow() && grid[r][c].isP2Now()) && grid[r][c].isSelectedNow())
                        g.setColor(Color.RED);
                	else if ((grid[r][c].isPlayerNow() && grid[r][c].isP1Now()) && !grid[r][c].isSelectedNow())
                        g.setColor(new Color(179, 0, 0));
                	else if ((grid[r][c].isPlayerNow() && grid[r][c].isP2Now()) && !grid[r][c].isSelectedNow())
                		g.setColor(new Color(179, 0, 0));
                	else if (grid[r][c].isTileNow() && (grid[r][c].isPlayerNow() || grid[r][c].isP1Now() || grid[r][c].isP2Now()))
                        g.setColor(Color.RED);
                	else if (grid[r][c].isButtonNow() && grid[r][c].isTileNow())
                	{
                        button = grid[r][c].getButtonType();
                        g.setColor(new Color(128, 128, 128));
                	}
                	else if (grid[r][c].isSplitterNow() && grid[r][c].isTileNow())
                	{
                		split = true;
                		g.setColor(new Color(128, 128, 128));
                	}
                	else if (grid[r][c].isFallingNow() && grid[r][c].isTileNow())
                        g.setColor(Color.ORANGE);
                	else if (grid[r][c].isGoalNow())
                        g.setColor(Color.YELLOW);
                	else if (grid[r][c].isTileNow() && grid[r][c].isSwitchableNow())
                		g.setColor( new Color(69,69,69));
                    else if (grid[r][c].isTileNow())
                    	g.setColor(new Color(128, 128, 128));
                    else
                        g.setColor( new Color(235,235,255) );
                	
                    g.fillRect( (c+1)*boxSize, (r+1)* boxSize, boxSize-2, boxSize-2);
                    if(button == 1)
                    {
                    	g.setColor(Color.BLACK);
                    	g.fillOval((c+1)*boxSize-1, (r+1)* boxSize-1, boxSize-1, boxSize-1);
                    }
                    else if(button == 2)
                    {
                    	int z = 1;
                    	Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(boxSize/5));
                    	g2.setColor(Color.BLACK);
                    	g2.drawLine((c+1)*boxSize - z, (r+1)* boxSize - z, (c+2)*boxSize - z, (r+2)* boxSize - z);
                    	g2.drawLine((c+1)*boxSize - z, (r+2)* boxSize - z, (c+2)*boxSize - z, (r+1)* boxSize - z);
                    }
                    else if(split)
                    {
                    	Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(boxSize/5));
                        g2.setColor(Color.BLACK);
                        g2.drawOval((c+1)*boxSize, (r+1)* boxSize, boxSize-1, boxSize-1);
                    	g2.setColor(new Color(128, 128, 128));
                    	g2.drawLine((c+1)*boxSize + boxSize/2, (r+1)* boxSize +boxSize/15, (c+1)*boxSize + boxSize/2, (r+2)* boxSize - boxSize/15);
                    }
                }
            }
        }
    }
}
