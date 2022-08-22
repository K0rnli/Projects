

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.EventListenerList;

public class DetailsPanel extends JPanel 
{
	private int days = 0;
	//For Slider
	static final int PEOPLE_MIN = 0;
	static final int PEOPLE_MAX = 100;
	static final int PEOPLE_INIT = 50;
	static final int DISTANCE_MIN = 5;
	static final int DISTANCE_MAX = 15;
	static final int DISTANCE_INIT = 10;
	static final int OUTOF_MIN = 0;
	static final int OUTOF_MAX = 10;
	static final int OUTOF_INIT = 5;
	//For JComboBox
	static final String[] MASK_TYPES = { "No Mask", "Cloth Mask", "N95"};
	static final Masks[] MASK_LIST = {new NoMask(), new ClothMask(), new N95()};

	private EventListenerList listenerList = new EventListenerList();
	public DetailsPanel()
	{
		//Create Dimension of Control Panel
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);
		setBorder(BorderFactory.createTitledBorder("Controls"));
		
		//Buttons
		TypomaticButton backBtn = new TypomaticButton("Back");
		JButton generateBtn = new JButton("Generate");
		TypomaticButton nextBtn = new TypomaticButton("Next");

		//Labels
		JLabel blankLabel = new JLabel("");
		JLabel peopleLabel = new JLabel("# of People : ");
		JLabel distanceLabel = new JLabel("Virus Range(ft) : ");
		JLabel immuneLabel = new JLabel("Immunity ratio : ");
		JLabel maskLabel = new JLabel("Mask Type : ");
		JLabel greenLabel = new JLabel("Green - Healthy");
		JLabel brownLabel = new JLabel("Brown - Infected");
		JLabel redLabel = new JLabel("Red - Dead");
		JLabel dayCounter = new JLabel("Day : " + days);

		//Slider
		final JSlider peopleCount = new JSlider(JSlider.HORIZONTAL, PEOPLE_MIN, PEOPLE_MAX, PEOPLE_INIT);
		peopleCount.setMajorTickSpacing(50);
		peopleCount.setMinorTickSpacing(10);
		peopleCount.setPaintTicks(true);
		peopleCount.setPaintLabels(true);
		final JSlider distanceCount = new JSlider(JSlider.HORIZONTAL, DISTANCE_MIN, DISTANCE_MAX, DISTANCE_INIT);
		distanceCount.setMajorTickSpacing(5);
		distanceCount.setMinorTickSpacing(1);
		distanceCount.setPaintTicks(true);
		distanceCount.setPaintLabels(true);
		final JSlider outOfCount = new JSlider(JSlider.HORIZONTAL, OUTOF_MIN, OUTOF_MAX, OUTOF_INIT);
		outOfCount.setMajorTickSpacing(5);
		outOfCount.setMinorTickSpacing(1);
		outOfCount.setPaintTicks(true);
		outOfCount.setPaintLabels(true);
		
		//JComboBox
		final JComboBox maskList = new JComboBox(MASK_TYPES);
		maskList.setSelectedIndex(0);
		
		
		//Action Methods

		//GenerateButton ACTION
		generateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Obtains all the Info inputed from Sliders and JComboBox
				int numOfPeople = peopleCount.getValue();
				int distance = distanceCount.getValue();
				int outOf = outOfCount.getValue();
				days = 0;
				dayCounter.setText("Day : " + days);
				Masks mask = MASK_LIST[maskList.getSelectedIndex()];
				//Sends Info
				fireDetailEvent(new DetailEvent(this, numOfPeople, distance, mask, outOf));
			}
		});


		//BackButton ACTION
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(days > 0)
				{
					days -= 1;
				}
				dayCounter.setText("Day : " + days);
				//Sends day
				fireDetailEvent(new DetailEventDayChange(this, days));
			}
		});

		//NextButton ACTION
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				days += 1;	
				dayCounter.setText("Day : " + days);
				fireDetailEvent(new DetailEventDayChange(this, days));
			}
		});
		

		//GRAPHICS
		//Graphic Location of Labels/Buttons/Text Fields	
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = .5;
		gc.weighty = .5;
		//////////////////////////////////////
		
		//PeopleCount Label
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridx = 0;
		gc.gridy = 0;
		add(peopleLabel, gc);

		//////////////////////////////////////
		
		//PeopleCount Slider
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		gc.gridy = 1;
		add(peopleCount, gc);

		//////////////////////////////////////
		
		//DistanceCount Label
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridx = 0;
		gc.gridy = 2;
		add(distanceLabel, gc);

		//////////////////////////////////////
		
		//DistanceCount Slider
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		gc.gridy = 3;
		add(distanceCount, gc);

		//////////////////////////////////////
		
		//Immune Label
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridx = 0;
		gc.gridy = 4;
		add(immuneLabel, gc);

		//////////////////////////////////////
				
		//Immune Slider
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		gc.gridy = 5;
		add(outOfCount, gc);

		//////////////////////////////////////
		
		//Mask Label
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridx = 0;
		gc.gridy = 6;
		add(maskLabel, gc);

		//////////////////////////////////////
				
		//DistanceCount Slider
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.gridx = 0;
		gc.gridy = 7;
		add(maskList, gc);

		//////////////////////////////////////
		
		
		
		
		//Line Spacer
		gc.weighty = 10;
		gc.gridx = 0;
		gc.gridy = 8;
		add(blankLabel, gc);
		gc.weighty = 0;
		gc.gridx = 0;
		gc.gridy = 9;
		add(blankLabel, gc);
		
		//Labels
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 0;
		gc.gridy = 10;
		add(greenLabel, gc);
		//////////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy = 11;
		add(brownLabel, gc);
		//////////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy = 12;
		add(redLabel, gc);
		
		//Day Count
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridx = 0;
		gc.gridy = 13;
		add(dayCounter, gc);
		
		//Save Button
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridx = 0;
		gc.gridy = 14;
		add(generateBtn, gc);
		
		//"Back" Button
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 0;
		gc.gridy = 15;
		add(backBtn, gc);
		
		//"Next" Button
		gc.anchor = GridBagConstraints.WEST;
		gc.gridx = 1;
		gc.gridy = 15;
		add(nextBtn, gc);		
	}
	//Casts the information up
	public void fireDetailEvent(DetailEvent event) 
	{
		Object[] listeners = listenerList.getListenerList();
		
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == DetailListener.class)
			{
				((DetailListener)listeners[i+1]).detailEventOccurred(event);
			}
		}
	}
	//Casts the information up
	public void fireDetailEvent(DetailEventDayChange event) 
	{
		Object[] listeners = listenerList.getListenerList();
		
		for(int i = 0; i < listeners.length; i += 2)
		{
			if(listeners[i] == DetailListener.class)
			{
				((DetailListener)listeners[i+1]).detailEventOccurred(event);
			}
		}
	}
	//Listens for input
	public void addDetailListener(DetailListener listener)
	{
		listenerList.add(DetailListener.class, listener);
	}
	public void removeDetailListener(DetailListener listener)
	{
		listenerList.remove(DetailListener.class, listener);
	}
}
   