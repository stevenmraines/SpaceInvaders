import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 */

/**
 * @author Derick Owens
 *
 */
public class Screen extends JPanel {
	public static int screenWidth = 800;
	public static int screenHeight = 400;
	private ArrayList<ScreenObject> screenObjects;
	private javax.swing.Timer timer;
	
	public Screen()
	{
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		screenObjects = new ArrayList<ScreenObject>();
		Random generator = new Random();
	}
	
	public void paintComponent(Graphics g)
	{
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		
		super.paintComponent(g);
		//JIM - THE COMMENTED LINE BELOW (LINE 41) IS WHERE YOUR BACKGROUND GOES
		//g.drawImage(backgroundImg.getImage(),0,0,screenWidth,screenHeight,null);
		for (ScreenObject obj : screenObjects) 
		{
			obj.draw(g);
		}
		
		timer = new javax.swing.Timer(30, new TimerListener());
		timer.start();
	}
	
	private class TimerListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			for (ScreenObject obj: screenObjects)
			{
				if (obj instanceof MovingScreenObject)
				{
					MovingScreenObject movingObj = (MovingScreenObject) obj;
					movingObj.move();
				}
			}
			repaint();
		}
	}
}
