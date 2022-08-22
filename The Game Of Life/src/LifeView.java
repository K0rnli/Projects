import java.awt.*;
import javax.swing.*;
import java.util.*;

/** The view (graphical) component */
public class LifeView extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static int SIZE = 60;
	private Color color = Color.BLUE;
	private boolean randColor = false;

	/** store a reference to the current state of the grid */
    private LifeCell[][] grid;

    public LifeView()
    {
        grid = new LifeCell[SIZE][SIZE];
    }

    /** entry point from the model, requests grid be redisplayed */
    public void updateView( LifeCell[][] mg )
    {
        grid = mg;
        repaint();
    }
    /**
     * Selects a color
     * @param  c  the color
     */
    public void colorUpdate(int c)
    {
    	switch(c)
    	{
    	case 0: color = Color.BLUE;
    		break;
    	case 1: color = Color.RED;
    		break;
    	case 2: color = Color.BLACK;
    		break;
    	case 3 : break;
    	}
    	randColor = c == 3;
    	repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int testWidth = getWidth() / (SIZE+1);
        int testHeight = getHeight() / (SIZE+1);
        // keep each life cell square
        int boxSize = Math.min(testHeight, testWidth);
        Random rand = new Random();
        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
                if (grid[r][c] != null)
                {
                    if (grid[r][c].isAliveNow() && !randColor)
                        g.setColor(color);
                    else if (grid[r][c].isAliveNow() && randColor)
                    	g.setColor(new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
                    else
                        g.setColor( new Color(235,235,255) );

                    g.fillRect( (c+1)*boxSize, (r+1)* boxSize, boxSize-2, boxSize-2);
                }
            }
        }
    }
}
