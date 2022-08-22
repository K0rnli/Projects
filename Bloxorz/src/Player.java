
public class Player 
{
	private boolean connected;
	private int p1X, p1Y;
	private int p2X, p2Y;
	private int selected;
	private boolean movement;
	
	public Player(int x, int y)
	{
		p1X = x;
		p1Y = y;
		p2X = x;
		p2Y = y;
		connected = true;
		selected = -1;
	}
	public boolean isConnected()
	{
		return connected;
	}
	public void setConnected(boolean a)
	{
		connected = a;
	}
	public int isSelected()
	{
		return selected;
	}
	public void setSelected(int a)
	{
		selected = a;
	}
	public int getP1X()
	{
		return p1X;
	}
	public int getP1Y()
	{
		return p1Y;
	}
	public int getP2X()
	{
		return p2X;
	}
	public int getP2Y()
	{
		return p2Y;
	}
	public void setP1X(int a)
	{
		p1X = a;
	}
	public void setP1Y(int a)
	{
		p1Y = a;
	}
	public void setP2X(int a)
	{
		p2X = a;
	}
	public void setP2Y(int a)
	{
		p2Y = a;
	}
	
	public void up()
	{
		if(p1Y - 1 == p2Y - 2 || p2Y - 1 == p1Y - 2)
		{
			int tempY = Math.min(p1Y, p2Y) - 1;
			p1Y = tempY;
			p2Y = tempY;
		}
		else if(p1Y == p2Y && p1X == p2X)
		{
			p1Y -= 2;
			p2Y -= 1;
		}
		else
		{
			p1Y--;
			p2Y--;
		}
	}
	public void down()
	{
		if(p1Y + 1 == p2Y + 2 || p2Y + 1 == p1Y + 2)
		{
			int tempY = Math.max(p1Y, p2Y) + 1;
			p1Y = tempY;
			p2Y = tempY;
		}
		else if(p1Y == p2Y && p1X == p2X)
		{
			p1Y += 2;
			p2Y += 1;
		}
		else
		{
			p1Y++;
			p2Y++;
		}
	}
	public void right()
	{
		if(p1X + 1 == p2X + 2 || p2X + 1 == p1X + 2)
		{
			int tempX = Math.max(p1X, p2X) + 1;
			p1X = tempX;
			p2X = tempX;
		}
		else if(p1X == p2X && p1Y == p2Y)
		{
			p1X += 2;
			p2X += 1;
		}
		else
		{
			p1X++;
			p2X++;
		}
	}
	public void left()
	{
		if(p1X - 1 == p2X - 2 || p2X - 1 == p1X - 2)
		{
			int tempX = Math.min(p1X, p2X) - 1;
			p1X = tempX;
			p2X = tempX;
		}
		else if(p1X == p2X && p1Y == p2Y)
		{
			p1X -= 2;
			p2X -= 1;
		}
		else
		{
			p1X--;
			p2X--;
		}
	}
	
	public void up1()
	{
		p1Y--;
	}
	public void down1()
	{
		p1Y++;
	}
	public void left1()
	{
		p1X--;
	}
	public void right1()
	{
		p1X++;
	}
	
	public void up2()
	{
		p2Y--;
	}
	public void down2()
	{
		p2Y++;
	}
	public void left2()
	{
		p2X--;
	}
	public void right2()
	{
		p2X++;
	}
	public boolean isStacked()
	{
		return p1X == p2X && p1Y == p2Y;
	}
	public boolean getMovement()
	{
		return movement;
	}
	public void setMovement(boolean a)
	{
		movement = a;
	}
}
