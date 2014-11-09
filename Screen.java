import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import javax.swing.ImageIcon;
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
	public static int screenHeight = 1000;
	private ArrayList<ScreenObject> screenObjects;
	private javax.swing.Timer timer;
	//public static ImageIcon backgroundImg = new ImageIcon("JIM'S BACKGROUND");
	public static ImageIcon invaderImg1 = new ImageIcon("invader1.jpg");
	public static ImageIcon invaderImg2 = new ImageIcon("invader2.jpg");
	public static ImageIcon invaderImg3 = new ImageIcon("invader3.gif");
	public static ImageIcon laserCannonImg = new ImageIcon("laserCannon.png");
	public static ImageIcon explosionImg = new ImageIcon("explosion.gif");
	public static ImageIcon bunkerImg = new ImageIcon("bunker.png");
	
	public static int invaderWidth = 50;
	public static int invaderHeight = 50;
	public static int invader1Points = 10;
	public static int invader2Points = 20;
	public static int invader3Points = 40;
	
	private int lives = 3;
	
	private boolean displayPlayNextLife = false;
	private boolean displayGameOver = false;
	private boolean displayNewLevel = false;
	
	public Screen()
	{
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		screenObjects = new ArrayList<ScreenObject>();
		Random generator = new Random();
		
		addShip();
		
		timer = new javax.swing.Timer(30, new TimerListener());
		timer.start();
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
		

	}
	
	public void addShip(){
		Iterator<ScreenObject> it = screenObjects.iterator();
		while (it.hasNext()) {
			ScreenObject obj = it.next();
			if (obj instanceof MovingScreenObject) {
				it.remove();
			}
		}
		
		Random generator = new Random();
		
		//add the laser cannon
		
		int x = screenWidth / 2;
		int y = 850;
		
		Ship laserCannon = new Ship(new Point(x, y), new Rectangle(60,60), laserCannonImg.getImage());
		laserCannon.setArbitraryVector(new ObjectVector(0,0));
		laserCannon.setAngle(90);
		screenObjects.add(0, laserCannon);
	}

	
// !!!STEVEN'S AREA!!!
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
