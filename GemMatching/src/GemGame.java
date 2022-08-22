/*************************************************************************
 * Name        : Keith Vertanen 
 * Username    : kvertanen
 * Description : Gem Matching game.  Two players alternate choosing 
 *               where to place the next gem.  Blocks of the same color
 *               get a score multiplier equal to the number of gems in 
 *               that block.  The game is over when either players row
 *               reaches 16 gems.  
 *************************************************************************/

import java.awt.Font;

public class GemGame 
{
 
	static final int MAX_GEMS = 16;

	public static double indexToX(int i)
	{
		return (0.5 + i) * (1.0 / MAX_GEMS);
	}


	public static int xToIndex(double x)
	{
		return (int) ((x + 0.5 / MAX_GEMS) / (1.0 / MAX_GEMS));
	}

	public static void main(String [] args)
	{

		final double PLAYER1_Y 			= 0.7;
		final double PLAYER2_Y 			= 0.3;
		final double PLAYER_HALF_HEIGHT = 0.15;
		final double TEXT_HEIGHT 		= 0.05;
		final double SCORE_Y 			= 0.95;
		final double INDICATOR_X 		= -0.025;
		final double INDICATOR_RADIUS 	= 0.01;
		

		GemList player1 = new GemList();
		GemList player2 = new GemList();
		
		Gem current = null;
		boolean mouseDown = false;
		boolean turn1 = true;
		
		int score1 = 0;
		int score2 = 0;
		
		while ((player1.size() < MAX_GEMS) && (player2.size() < MAX_GEMS))
		{
			StdDraw.clear();
			double mouseX = StdDraw.mouseX();
			double mouseY = StdDraw.mouseY();

			if (current == null)
				current = new Gem();
	
			if (StdDraw.mousePressed())
			{
				mouseDown = true;
			}			
			else if (mouseDown)
			{		
				if ((mouseY > PLAYER1_Y - PLAYER_HALF_HEIGHT) &&
				    (mouseY < PLAYER1_Y + PLAYER_HALF_HEIGHT))
				{
					player1.insertBefore(current, xToIndex(mouseX));
					current = null;
					turn1 = !turn1;
				}
				else if ((mouseY > PLAYER2_Y - PLAYER_HALF_HEIGHT) &&
				         (mouseY < PLAYER2_Y + PLAYER_HALF_HEIGHT))
				{
					player2.insertBefore(current, xToIndex(mouseX));
					current = null;
					turn1 = !turn1;
				}

				mouseDown = false;
			}		
			
			score1 = player1.score();
			score2 = player2.score();
			

			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			StdDraw.filledRectangle(0.5, PLAYER2_Y, 1.0, PLAYER_HALF_HEIGHT);
			StdDraw.filledRectangle(0.5, PLAYER1_Y, 1.0, PLAYER_HALF_HEIGHT);


			StdDraw.setPenColor(StdDraw.RED);	
			StdDraw.setFont(new Font("SansSerif", Font.BOLD, 16));
			StdDraw.textLeft(0.0, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, "Player 1: " + score1);
			StdDraw.textLeft(0.0, PLAYER2_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, "Player 2: " + score2);

			StdDraw.setPenColor(StdDraw.BLUE);
			if (turn1)
				StdDraw.filledCircle(INDICATOR_X, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, INDICATOR_RADIUS);
			else
				StdDraw.filledCircle(INDICATOR_X, PLAYER2_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, INDICATOR_RADIUS);
			
			player1.draw(PLAYER1_Y);
			player2.draw(PLAYER2_Y);

			if (current != null)
				current.draw(mouseX, mouseY);			
						
			StdDraw.show(10);
		}

		StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
        if (score1 == score2)
        	StdDraw.text(0.5, SCORE_Y, "Tie game!");
        else if (score1 > score2)
        	StdDraw.text(0.5, SCORE_Y, "Player 1 wins!");
        else 
        	StdDraw.text(0.5, SCORE_Y, "Player 2 wins!");
        
		StdDraw.show(0);
	}
}
