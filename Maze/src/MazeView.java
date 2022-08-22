import java.awt.*;
import javax.swing.*;

/** The view (graphical) component */
public class MazeView extends JPanel
{
	private static final long serialVersionUID = 1L;

	/** store a reference to the current state of the grid */
    private Maze maze;

    public MazeView()
    {
        maze = new Maze();
        updateView(maze);
    }

    /** entry point from the model, requests grid be redisplayed */
    public void updateView( Maze m )
    {
        maze = m;
        repaint();
    }
    /**
     * Selects a color
     * @param  c  the color
     */

    public void paintComponent(Graphics g)
    {
    	if(!maze.getSetup())
    	{
    		g.setColor(Color.black);
    		g.drawRect(0, 0, getWidth(), getHeight());
    	}
    	else
    	{
	        super.paintComponent(g);
	        int testWidth = getWidth() / (Math.max(maze.getHeight(), maze.getLength())+2);
	        int testHeight = getHeight() / (Math.max(maze.getHeight(), maze.getLength())+2);
	        int boxSize = Math.min(testHeight, testWidth);
	        for ( int r = 0; r < maze.getLength(); r++ )
	        {
	            for (int c = 0; c < maze.getHeight(); c++ )
	            {
	                if (maze.getSquare(r, c) != null)
	                {
	                    boolean moveable = false;
	                    switch(maze.getSquare(r, c).getType())
	            		{
	            			case 0 : moveable = true; break;
	            			case 1 : g.setColor(Color.black); break;
	            			case 2 : g.setColor(Color.green); break;
	            			case 3 : g.setColor(Color.yellow); break;
	            		}
	                    if(moveable)
	                    {
	                    	char status = maze.getSquare(r, c).getStatus();
	                    	if(status == '.')
	                    		g.setColor(Color.red);
	                    	else if(status == '_')
	                    		g.setColor(Color.white);
	                    	else if(status == 'o')
	                    		g.setColor(new Color(217, 255, 254));
	                    	else if(status == 'x')
	                    		g.setColor(new Color(97, 92, 255));
	                    }
	
	                    g.fillRect( (r)*boxSize, (c)* boxSize, boxSize, boxSize);
	                }
	            }
	        }
    	}
    }
}
