
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
 * @author Derick Owens and J halstead
 *
 */
public class Screen extends JPanel implements KeyListener
{
	//initialize level
	static int level = 0;
	
	//Prepare the array, the screen, and the images for all moving objects
	public static int screenWidth = 800;
	public static int screenHeight = 700;
	private ArrayList<ScreenObject> screenObjects;
	private javax.swing.Timer timer;
	public static ImageIcon invaderImg1 = new ImageIcon("invader3.png");
	public static ImageIcon invaderImg2 = new ImageIcon("invader1.png");
	public static ImageIcon invaderImg3 = new ImageIcon("invader2.png");
	public static ImageIcon laserCannonImg = new ImageIcon("laserCannon.png");
	public static ImageIcon explosionImg = new ImageIcon("explosion.gif");
	public static ImageIcon bunkerImg = new ImageIcon("bunker.png");
	public static ImageIcon mysteryImg = new ImageIcon("mysteryShip.png");
	public static ImageIcon shipShotImg = new ImageIcon("shipShot.png");
	public static ImageIcon alienShotImg = new ImageIcon("alienShot.png");
	
	//initialize invader specifics
	public static int invaderWidth = 35;
	public static int invaderHeight = 35;
	public static int invader1Points = 10;
	public static int invader2Points = 20;
	public static int invader3Points = 40;
	public static ObjectVector invaderVector = new ObjectVector(1+(level/2),0);
	public static int invaderY = Invader.getBaseY();
	public int startAdding = screenWidth/5;
	public int endAdding = startAdding*4;
	
	//initialize mysteryShip specifics
	public static int mysteryY = screenHeight/10;
	public static int mysteryX = screenWidth;
	public static int mysteryWidth = 80;
	public static int mysteryHeight = 50;
	public static int mysteryPoints = 50;
	
	//initialize score
	public Score theScore;
	
	//initialize lives
	private int lives = 3;
	
	//initialize boolean values
	private boolean displayPlayNextLife = false;
	private boolean displayGameOver = false;
	private boolean displayNewLevel = false;
	
	//initialize shot vector and details
	public static ObjectVector projectileVector = new ObjectVector(0, -8);
	int fireFromX;
	int fireFromY;
	
	//set a static variable for height that works for all resolutions
	int bunkerHeight;
	int shipHeight;
	
	public Screen()
	{
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		screenObjects = new ArrayList<ScreenObject>();
		addMovingObjects();
		
	    Score theScore = new Score(new Point(screenWidth/2 - 400, 30), new Rectangle(0,0));
		screenObjects.add(theScore);
		
	    Lives playerLives = new Lives(new Point(screenWidth/2 -100, 30), new Rectangle(0,0));
		screenObjects.add(playerLives);
		//Level playerlevel = new Level (new Point(screenWidth/2 -50, 30), new Rectangle(0,0));
		
		timer = new javax.swing.Timer(25, new TimerListener());
		timer.start();
		
		// Was having a weird problem where the KeyPressed
		// method was never getting called, this fixes it
		this.setFocusable(true);
		this.requestFocus();
		
		// Add the key listener to control the laser cannon
		this.addKeyListener(this);
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
		
	if (this.displayPlayNextLife){
	   g.setColor(Color.white);
	   g.setFont(new Font ("Serif", Font.BOLD, 36));
	   g.drawString("You have " + lives + "Cannons left ", 150,(int)(0.4*screenHeight));
	   g.drawString("Press enter to  Continue", 135, (int)(0.6*screenHeight));
	}
	
	if (this.displayGameOver){
	   g.setColor(Color.white);
	   g.setFont(new Font("Serif", Font.BOLD, 36));
	   g.drawString("Game Over", 205, (int)(0.4*screenHeight));
	   g.drawString("Would you like to play again? (Y/N)", 30, (int)(0.6*screenHeight));   
	}
	   
	 if (this.displayNewLevel) {
	    g.setColor(Color.white);
	    g.setFont(new Font ("Serif", Font.BOLD, 36));
	    //g.drawString("you have won "+ ((Object) currentLevel.getlevelNumber()), 190, (int)(0.6*screenHeight));
	    g.drawString("Press enter to  Continue", 135, (int)(0.6*screenHeight));
	       
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
		
		Rectangle shipDimensions = new Rectangle(60,40);
		shipHeight = (int) (screenHeight - shipDimensions.getHeight()*2.5);
		int x = (screenWidth / 2) - 30;
		int y = shipHeight;
		Ship laserCannon = new Ship(new Point(x, y), new Rectangle(60,40), laserCannonImg.getImage());
		laserCannon.setArbitraryVector(new ObjectVector(0,0));
		laserCannon.setAngle(90);
		screenObjects.add(0,laserCannon);
		
		//add the bunkers
		
		Rectangle bunkerDimensions = new Rectangle(80,60);
		bunkerHeight = (int) (screenHeight - bunkerDimensions.getHeight()*3);
		int bunkerX = (screenWidth / 5) - 15;
		int bunkerY = bunkerHeight;
		Bunker bunker1 = new Bunker(new Point(bunkerX,bunkerY), new Rectangle(80, 60), bunkerImg.getImage());
		Bunker bunker2 = new Bunker(new Point(bunkerX*2,bunkerY), new Rectangle(80, 60), bunkerImg.getImage());
		Bunker bunker3 = new Bunker(new Point(bunkerX*3,bunkerY), new Rectangle(80, 60), bunkerImg.getImage());
		Bunker bunker4 = new Bunker(new Point(bunkerX*4,bunkerY), new Rectangle(80,60), bunkerImg.getImage());
		ObjectVector bunkerVector = new ObjectVector(0,0);
		bunker1.setArbitraryVector(bunkerVector);
		bunker2.setArbitraryVector(bunkerVector);
		bunker3.setArbitraryVector(bunkerVector);
		bunker4.setArbitraryVector(bunkerVector);
		screenObjects.add(bunker1);
		screenObjects.add(bunker2);
		screenObjects.add(bunker3);
		screenObjects.add(bunker4);

				
		//add the invaders
		
		for (int addPoint = endAdding; startAdding < endAdding; endAdding = endAdding - 50)
		{
		int	invaderX = endAdding;
		int	invaderY1 = invaderY;
		int invaderY2 = invaderY - 50;
		int invaderY3 = invaderY - 100;
		int invaderY4 = invaderY - 150;
		int invaderY5 = invaderY - 200;
		Invader invader1 = new Invader(new Point(invaderX,invaderY1), new Rectangle(invaderWidth, invaderHeight), invader1Points, invaderImg1.getImage());
		Invader invader2 = new Invader(new Point(invaderX,invaderY2), new Rectangle(invaderWidth, invaderHeight), invader1Points, invaderImg1.getImage());
		Invader invader3 = new Invader(new Point(invaderX,invaderY3), new Rectangle(invaderWidth, invaderHeight), invader2Points, invaderImg2.getImage());
		Invader invader4 = new Invader(new Point(invaderX,invaderY4), new Rectangle(invaderWidth, invaderHeight), invader2Points, invaderImg2.getImage());
		Invader invader5 = new Invader(new Point(invaderX,invaderY5), new Rectangle(invaderWidth, invaderHeight), invader3Points, invaderImg3.getImage());
		invader1.setArbitraryVector(invaderVector);
		invader2.setArbitraryVector(invaderVector);
		invader3.setArbitraryVector(invaderVector);
		invader4.setArbitraryVector(invaderVector);
		invader5.setArbitraryVector(invaderVector);
		screenObjects.add(invader1);
		screenObjects.add(invader2);
		screenObjects.add(invader3);
		screenObjects.add(invader4);
		screenObjects.add(invader5);
		}
	}
		
	private class TimerListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//make the invader oscillate
			for (ScreenObject obj: screenObjects) {
				if (obj instanceof Invader) {
					if (obj.getLocation().getX() == 100) {
						Invader.invaderVector.setChangeX(1 + (level/4));
						Invader.invaderVector.setChangeY(5);
					}
					else if (obj.getLocation().getX() == 
							Screen.screenWidth - 100) {
						Invader.invaderVector.setChangeX(-1 - (level/4));
						Invader.invaderVector.setChangeY(5);
					}
				}
			}
			//make all screenObjects move
			for (ScreenObject obj: screenObjects)
			{
				if (obj instanceof MovingScreenObject)
				{
					MovingScreenObject movingObj = (MovingScreenObject) obj;
					movingObj.move();
				}
			}
			Invader.invaderVector.setChangeY(0);
			
			//randomly generate a mystery ship
			MysteryShip mysteryShip = new MysteryShip(new Point(mysteryX, mysteryY), new Rectangle(mysteryWidth, mysteryHeight), mysteryPoints, mysteryImg.getImage());
			mysteryShip.setArbitraryVector(new ObjectVector(-4-(level/4),0));
			
			if (Math.random() < 0.001+(.001*level/2)){
				screenObjects.add(mysteryShip);
				
			}
			
			//randomly generate an alien shot
			
			for (ScreenObject obj: screenObjects) {
				if (obj instanceof Invader) {
					if (Math.random() < .0005 + (.0005*level/2)){
					timer.stop();
					fireFromX = (int) obj.getLocation().getX();
					fireFromY = (int) obj.getLocation().getY();
					AlienProjectile alienShot = new AlienProjectile(new Point(fireFromX, fireFromY), new Rectangle(7, 30), alienShotImg.getImage(), 180);
					alienShot.setArbitraryVector(new ObjectVector(0,7));
					screenObjects.add(alienShot);
					timer.start();
					}
				}
			}
		
			repaint();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent event){
		int keyCode = event.getKeyCode();
		Ship playerShip = null;
		if (screenObjects.get(0) instanceof Ship) {
			playerShip = (Ship) screenObjects.get(0);
		}
		switch (keyCode) {
			//shoot if space bar is pressed
			case KeyEvent.VK_SPACE:
				if (playerShip != null) {
					Point p = playerShip.getLocation();
					double a = playerShip.getAngle();
					Rectangle r = playerShip.getSize();
					ShipProjectile shot = new ShipProjectile(new Point (p.x + r.width/2, shipHeight-12), new Rectangle(7,30), shipShotImg.getImage(), a);
					shot.setArbitraryVector(projectileVector);
					screenObjects.add(shot);
				}
				break;
			
			case KeyEvent.VK_LEFT:
				playerShip.setArbitraryVector(new ObjectVector(-2, 0));
				playerShip.move();
				break;
				
			case KeyEvent.VK_RIGHT:
				playerShip.setArbitraryVector(new ObjectVector(2, 0));
				playerShip.move();
				break;
		}
		
		repaint();
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		// Store the key that is pressed
		int key = e.getKeyCode();
		
		// Reset the vector to 0, 0
		if(screenObjects.get(0) instanceof Ship) {
			if(key == e.VK_LEFT || key == e.VK_RIGHT) {
				Ship playerShip = (Ship) screenObjects.get(0);
				playerShip.setArbitraryVector(new ObjectVector(0, 0));
				playerShip.move();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}
