import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main 
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				//StartsFrame
				JFrame frame = new MainFrame("Virus Simulator");
				frame.setSize(1310, 1000);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
			}
		});
	}
}
