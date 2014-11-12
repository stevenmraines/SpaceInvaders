import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Derick Owens
 *
 */
public class Screen extends JPanel
{
	public static int screenWidth = 800;
	public static int screenHeight = 1000;
	private ArrayList<ScreenObject> screenObjects;
	private javax.swing.Timer timer;
	public static ImageIcon invaderImg1 = new ImageIcon("invader3.jpg");
	public static ImageIcon invaderImg2 = new ImageIcon("invader1.gif");
	public static ImageIcon invaderImg3 = new ImageIcon("invader2.jpg");
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
		
		addMovingObjects();
		
		timer = new javax.swing.Timer(30, new TimerListener());
		timer.start();
	}
	
	public void paintComponent(Graphics g)
	{
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		
		super.paintComponent(g);
		for (ScreenObject obj : screenObjects) 
		{
			obj.draw(g);
		}
	}

	public void addMovingObjects()
	{
		Iterator<ScreenObject> it = screenObjects.iterator();
		while (it.hasNext()) {
			ScreenObject obj = it.next();
			if (obj instanceof MovingScreenObject) {
				it.remove();
			}
		}
		
		//add the laser cannon
		
		int x = screenWidth / 2;
		int y = 850;
		
		Ship laserCannon = new Ship(new Point(x, y), new Rectangle(60,40), laserCannonImg.getImage());
		laserCannon.setArbitraryVector(new ObjectVector(0,0));
		laserCannon.setAngle(90);
		screenObjects.add(laserCannon);
		
		//add the bunkers
		
		int bunkerX = screenWidth / 5;
		int bunkerY = 750;
		
		Bunker bunker1 = new Bunker(new Point(bunkerX,bunkerY), new Rectangle(80, 60), bunkerImg.getImage());
		Bunker bunker2 = new Bunker(new Point(bunkerX*2,bunkerY), new Rectangle(80, 60), bunkerImg.getImage());
		Bunker bunker3 = new Bunker(new Point(bunkerX*3,bunkerY), new Rectangle(80, 60), bunkerImg.getImage());
		Bunker bunker4 = new Bunker(new Point(bunkerX*4,bunkerY), new Rectangle(80,60), bunkerImg.getImage());
		bunker1.setArbitraryVector(new ObjectVector(0,0));
		bunker1.setAngle(90);
		screenObjects.add(bunker1);
		screenObjects.add(bunker2);
		screenObjects.add(bunker3);
		screenObjects.add(bunker4);
		
		//add the invaders
		
		int startAdding = screenWidth / 5;
		int endAdding = startAdding * 4;
		
		//add the first row of invaders
		
		for (int addPoint = endAdding; startAdding < endAdding; endAdding = endAdding - 50)
		{
			
		int	invaderX = endAdding;
		int	invaderY = 500;
			
		int invaderWidth = 45;
		int invaderHeight = 45;
		int invader1Points = 10;
		
		Invader invader1 = new Invader(new Point(invaderX,invaderY), new Rectangle(invaderWidth, invaderHeight), invader1Points, invaderImg1.getImage());
		screenObjects.add(invader1);
		}
		
		//add the second row of invaders
		
		int startAdding2 = screenWidth/5;
		int endAdding2 = startAdding*4;
		
		for (int addPoint = endAdding2; startAdding2 < endAdding2; endAdding2 = endAdding2 - 50)
		{
		int	invaderX = endAdding2;
		int	invaderY = 450;
			
		int invaderWidth = 45;
		int invaderHeight = 45;
		int invader1Points = 10;
		
		Invader invader1 = new Invader(new Point(invaderX,invaderY), new Rectangle(invaderWidth, invaderHeight), invader1Points, invaderImg1.getImage());
		screenObjects.add(invader1);
		}
		
		//add the third row of invaders
		int startAdding3 = screenWidth/5;
		int endAdding3 = startAdding*4;
		
		for (int addPoint = endAdding3; startAdding3 < endAdding3; endAdding3 = endAdding3 - 50)
		{
		int	invaderX = endAdding3;
		int	invaderY = 400;
			
		int invaderWidth = 45;
		int invaderHeight = 45;
		int invader2Points = 20;
		
		Invader invader2 = new Invader(new Point(invaderX,invaderY), new Rectangle(invaderWidth, invaderHeight), invader2Points, invaderImg2.getImage());
		screenObjects.add(invader2);
		}
		
		//add the fourth row of invaders
		int startAdding4 = screenWidth/5;
		int endAdding4 = startAdding*4;
		
		for (int addPoint = endAdding4; startAdding4 < endAdding4; endAdding4 = endAdding4 - 50)
		{
		int	invaderX = endAdding4;
		int	invaderY = 350;
			
		int invaderWidth = 45;
		int invaderHeight = 45;
		int invader2Points = 20;
		
		Invader invader2 = new Invader(new Point(invaderX,invaderY), new Rectangle(invaderWidth, invaderHeight), invader2Points, invaderImg2.getImage());
		screenObjects.add(invader2);
		}
		
		//add the last row of invaders
		
		int startAdding5 = screenWidth/5;
		int endAdding5 = startAdding*4;
		
		for (int addPoint = endAdding5; startAdding5 < endAdding5; endAdding5 = endAdding5 - 50)
		{
		int	invaderX = endAdding5;
		int	invaderY = 300;
			
		int invaderWidth = 45;
		int invaderHeight = 45;
		int invader2Points = 40;
		
		Invader invader3 = new Invader(new Point(invaderX,invaderY), new Rectangle(invaderWidth, invaderHeight), invader3Points, invaderImg3.getImage());
		screenObjects.add(invader3);
		}
		
	}
		
	
	
// !!!STEVEN'S AREA!!!
	private class TimerListener implements ActionListener 
	{
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
