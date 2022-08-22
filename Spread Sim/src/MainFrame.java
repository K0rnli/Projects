
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame 
{
	private DetailsPanel details;
	private PersonManager pM;
	private ArrayList<Person> p; 
	public MainFrame(String title) 
	{
		super(title);
		
		// Set Layout
		setLayout(new BorderLayout());
		
		// Create Component
		DrawFrame d = new DrawFrame();
		details = new DetailsPanel();
		
		//Listeners
		details.addDetailListener(new DetailListener() {
			public void detailEventOccurred(DetailEvent event){
				//Creates new Simulation
				pM = new PersonManager(event.getNumPeople(), event.getDistance(), event.getMask(), event.getOutOf());
				//Draws First Frame
				p = pM.getHistory(0);
				d.newFrame(getGraphics(), p);
			}
			public void detailEventOccurred(DetailEventDayChange event) {
				//Draws Current Frame
				p = pM.getHistory(event.getDay());
				d.newFrame(getGraphics(), p);
			}
		});
		// Add Components to Plane
		Container contain = getContentPane();
		contain.add(details, BorderLayout.WEST);
	}

}
