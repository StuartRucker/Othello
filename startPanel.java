//startPanel.java
/*
*
* @Author: Vinayak Kurup
* @Version: February 26, 2015
*
* Start Screen that decides what happens (what mode of game) !!!
*/

//necessary imports
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
 
public class startPanel extends JPanel implements ActionListener //Start menu
{
	JComboBox selection; 
	JButton onwards;
	int W;
	int H;

	public startPanel(int w, int h)
	{
		String[] modes = {"Depth 1", "Depth 2", "Depth 3", "Depth 4", "Depth 5", "Depth 6", "Depth 7"};
	 	selection = new JComboBox(modes);
	 	selection.setVisible(true);
		this.add(selection);
		
		onwards = new JButton("play");
		onwards.addActionListener(this);
		this.add(onwards);
		
		this.setBackground(new Color(93,207,255));
		W = w;
		H = h;
			

	}
	
	
    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Font c = new Font("Courier", Font.PLAIN, 100);
        graph.setFont(c);
        graph.drawString("OTHELLO", W/2 - 200, H/2 - 100);
        graph.setFont(new Font("times",Font.PLAIN, 45));
        graph.drawString("Stuart",W/2 - 50, H/2);
        int spacing = 50; 
        graph.drawString("Oscar",W/2 - 50, H/2 + spacing);
        graph.drawString("Vinny",W/2 - 50, H/2 + 2 * spacing);
        graph.drawString("Daniel",W/2 - 50, H/2 + 3 * spacing);
    }
 
    /** Listens to the combo box. */
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == onwards)
        {
        	String s = (String)selection.getSelectedItem();
        	int depth;
        	if(s.equals("Depth 7")) depth = 7;
        	else if(s.equals("Depth 6")) depth = 6;
        	else if(s.equals("Depth 5")) depth = 5;
        	else if(s.equals("Depth 4")) depth = 4;
        	else if(s.equals("Depth 3")) depth = 3;
        	else if(s.equals("Depth 2")) depth = 2;
        	else depth = 1;
        	
        	this.setVisible(false);
        	Runner.startGame(depth);
        }
    }
 

}
