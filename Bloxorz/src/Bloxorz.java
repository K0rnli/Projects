import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
    this is the Controller component
 */

public class Bloxorz extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private BloxorzView view;
	private BloxorzModel model;

	/** construct a randomized starting grid */
	Bloxorz() throws IOException
	{
		this(null);
	}
	
	/** construct a grid using the info in text file */
	Bloxorz(String fileName) throws IOException
	{
		super("Bloxorz");
		this.addKeyListener(this);
		// build the buttons
		JPanel controlPanel = 
				new JPanel(new FlowLayout(FlowLayout.CENTER));


		// build the view
		view = new BloxorzView();
		view.setBackground(Color.white);

		// put buttons, view together
		Container c = getContentPane();
		c.add(controlPanel, BorderLayout.NORTH);
		c.add(view, BorderLayout.CENTER);

		// build the model
		model = new BloxorzModel(view, fileName);
	}
	
	public static void main(String[] args) throws IOException
	{
		Bloxorz block = new Bloxorz("Level1.txt"); //parameterize to start w/ a particular file
		
		block.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		block.setSize(570, 640);
		//block.show(); //deprecated, added call below
		block.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		model.updateFrame(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}