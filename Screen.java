import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @author Derick Owens and Steven Raines
 *
 */
public class Screen extends JPanel implements KeyListener
{
	// Audio stuffs
	public static Clip clip;
	public static File gattlingShotSound = new File("gattlingShot.WAV");
	public static File invaderExplosionSound = new File("invaderExplosion.WAV");
	public static File invaderShotSound = new File("invaderShot.WAV");
	public static File mysteryShipSound = new File("mysteryShip.WAV");
	public static File noAmmoSound = new File("noAmmo.WAV");
	public static File powerupPickupSound = new File("powerupPickup.WAV");
	public static File shipExplosionSound = new File("shipExplosion.WAV");
	public static File shipShotSound = new File("shipShot.WAV");
	public static File shotCollisionSound = new File("shotCollision.WAV");
	
	// create boolean to handle pause stuff
	public static boolean isPaused = false;
	
	//Prepare the array, the screen, and the images for all moving objects
	public static int screenWidth = 800;
	public static int screenHeight = 700;
	private static ArrayList<ScreenObject> screenObjects;
	private static javax.swing.Timer timer;
	public static ImageIcon invaderImg1 = new ImageIcon("invader3.png");
	public static ImageIcon invaderImg2 = new ImageIcon("invader1.png");
	public static ImageIcon invaderImg3 = new ImageIcon("invader2.png");
	public static ImageIcon laserCannonImg = new ImageIcon("laserCannon.png");
	public static ImageIcon explosionImg = new ImageIcon("explosion.gif");
	public static ImageIcon shotCollisionImg = new ImageIcon("shotCollision.gif");
	public static ImageIcon powerupImg = new ImageIcon("gattlingPowerup.gif");
	public static ImageIcon bunkerImg = new ImageIcon("bunker40.png");
	public static ImageIcon mysteryImg = new ImageIcon("mysteryShip.png");
	public static ImageIcon shipShotImg = new ImageIcon("shipShot.png");
	public static ImageIcon shipGattlingShotImg = new ImageIcon("gattlingShot.png");
	public static ImageIcon alienShotImg = new ImageIcon("alienShot.png");
	
	//initialize level
	static int currentLevel = 0;
	static Level level = new Level(currentLevel, new Point(screenWidth-200, 30), new Rectangle(0,0));
	static int levelDisplay = level.getLevelNumber() + 1;
	
	//initialize screen ammo count
	static int currentAmmo = 0;
	static Ammo ammo = new Ammo(new Point(0, screenHeight - 3), new Rectangle(0,0));
	
	//initialize invader specifics
	public static int invaderWidth = 35;
	public static int invaderHeight = 35;
	public static int invader1Points = 10;
	public static int invader2Points = 20;
	public static int invader3Points = 40;
	public static ObjectVector invaderVector = new ObjectVector(1+level.getLevelNumber()/2,0);
	public static int invaderY = Invader.getBaseY();
	public int startAdding;
	public int endAdding;
	
	//initialize mysteryShip specifics
	public static int mysteryY = screenHeight/10;
	public static int mysteryX = screenWidth;
	public static int mysteryWidth = 60;
	public static int mysteryHeight = 40;
	Random generator = new Random();
	public static int mysteryPoints = 50;
	
	//initialize score
	public Score theScore; 
	
	//initialize lives
	public static int lives = 3;
	
	//initialize boolean values
	protected static boolean displayPlayNextLife = false;
	protected static boolean displayGameOver = false;
	protected static boolean displayNewLevel = false;
	
	//initialize shot vector and details
	public static ObjectVector projectileVector = new ObjectVector(0, -8);
	int fireFromX;
	int fireFromY;
	
	//set a static variable for height that works for all resolutions
	int bunkerHeight;
	int shipHeight;
	
	/**
	 * This handles all of the components of the game. It adds the moving objects and non-moving objects and also contains the timer that
	 * simulates movement.
	 */
	public Screen()
	{
		explosionImg.setImageObserver(this);
		shotCollisionImg.setImageObserver(this);
		powerupImg.setImageObserver(this);
		
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		screenObjects = new ArrayList<ScreenObject>();
		addMovingObjects();
		
	    Score theScore = new Score(new Point(screenWidth/2 - 400, 30), new Rectangle(0,0));
		screenObjects.add(theScore);
		
	    Lives playerLives = new Lives(new Point(screenWidth/2 - 100, 30), new Rectangle(0,0));
		screenObjects.add(playerLives);
		
		screenObjects.add(ammo);
		
		screenObjects.add(level);
		
		timer = new javax.swing.Timer(25, new TimerListener());
		timer.start();

		this.setFocusable(true);
		this.requestFocus();
		
		// Add the key listener to control the laser cannon
		this.addKeyListener(this);
	}
	/**
	 * Draw everything in the screenObjects array
	 */
	public void paintComponent(Graphics g)
	{
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		super.paintComponent(g);
		for (ScreenObject obj : screenObjects) 
		{
			obj.draw(g);
		}
	//import font
	   try{
		   Font finalFont = null;
		   File gameFont = new File("PressStart2P.ttf");
		   if (gameFont.exists()){
			   finalFont = Font.createFont(Font.TRUETYPE_FONT, gameFont).deriveFont(Font.PLAIN, 22f);
			   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			   ge.registerFont(finalFont);
		   }
		   }catch (FontFormatException e){
			   e.printStackTrace();
		   }catch (IOException f){
		   f.printStackTrace();
	   }
		
	if (displayPlayNextLife){
		if (lives > 0 && lives < 3){
		timer.stop();
	   g.setColor(Color.white);
	   g.setFont(new Font ("Press Start 2P", Font.BOLD, 24));
	   g.drawString("YOU HAVE " + lives + " CANNONS LEFT", 125,(int)(0.1*screenHeight));
	   g.drawString("PRESS ENTER TO CONTINUE", 129, (int)(0.15*screenHeight));
	}}
	
	if (displayGameOver){
		timer.stop();
		for(int i = 0; i < screenObjects.size()-1; i++) {
			screenObjects.remove(i);
		repaint();
		}
		
	   g.setColor(Color.white);
	   g.setFont(new Font("Press Start 2P", Font.BOLD, 36));
	   g.drawString("GAME OVER", 230, (int)(0.5*screenHeight));
	   g.setFont(new Font("Press Start 2P", Font.BOLD, 20));
	   g.drawString("WOULD YOU LIKE TO PLAY AGAIN? (Y/N)", 50, (int)(0.6*screenHeight));   
	}
	   
	 if (displayNewLevel) {
		timer.stop();
	    g.setColor(Color.white);
	    g.setFont(new Font ("Press Start 2P", Font.BOLD, 30));
	    g.drawString("YOU WON LEVEL " + (levelDisplay-1)+"!", 145, (int)(0.5*screenHeight));
	    g.setFont(new Font ("Press Start 2P", Font.BOLD, 24));
	    g.drawString("PRESS ENTER TO CONTINUE", 120, (int)(0.6*screenHeight));
	       
	 }
	}
	/**
	 * Add the moving objects to an array
	 */
	
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
		addInvaders();
	}
	
	/**
	 * Add the invaders to an array
	 */
	
	public void addInvaders() {
		startAdding = screenWidth/5;
		endAdding = startAdding*4;
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
	
	/**
	 * 
	 * @author Derick Owens and Steven Raines
	 * 
	 * This class handles all events that need to simulate movement and some game sounds
	 *
	 */
	private class TimerListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(screenObjects.get(0) instanceof Ship) {
				Ship playerShip = (Ship) screenObjects.get(0);
				currentAmmo = playerShip.getAmmo();
			}
			
			mysteryPoints = 50;
			int rand = generator.nextInt(5) + 1;
			mysteryPoints = mysteryPoints * rand;
			
			// Check for collisions
			for(int i = 0; i < screenObjects.size(); i++) {
				
				// Check if the item being checked for collisions is a MovingScreenObject
				// If it isn't, skip right over it
				if(!(screenObjects.get(i) instanceof MovingScreenObject)) {
					continue;
				}
				else {
					ScreenObject obj = screenObjects.get(i);
					MovingScreenObject movingObj = (MovingScreenObject) obj;
					
					for(int j = i + 1; j < screenObjects.size(); j++) {
						
						// Check whether MovingScreenObject again
						if(!(screenObjects.get(j) instanceof MovingScreenObject)) {
							continue;
						}
						else {
							ScreenObject otherObj = screenObjects.get(j);
							MovingScreenObject otherMovingObj = (MovingScreenObject) otherObj;
							
							// Make sure they're not the same object
							if(movingObj == otherMovingObj) {
								continue;
							}
							
							// Check for collisions
							if(movingObj.collide(otherMovingObj)) {
								
								// If a player's shot and an invader shot collide
								if(movingObj instanceof ShipProjectile && otherMovingObj instanceof AlienProjectile) {
									// remove the ship projectile
									screenObjects.remove(j);
									
									// remove the alien projectile
									screenObjects.remove(i);
									
									// play sound
									playSound(shotCollisionSound);
									
									// explosion effect
									Explosion shotCollision = new Explosion(movingObj.getLocation(), 
											new Rectangle(100, 100), shotCollisionImg.getImage(), 0, 35);
									shotCollision.setArbitraryVector(new ObjectVector(0, 0));
									screenObjects.add(shotCollision);
								}
								
								// If the player picks up a powerup
								if(movingObj instanceof Ship && otherMovingObj instanceof Powerup) {
									// remove the powerup
									screenObjects.remove(j);
									
									// play sound
									playSound(powerupPickupSound);
									
									// addAmmo
									Ship playerShip = (Ship) movingObj;
									playerShip.addAmmo();
								}
								
								// If your ship was hit
								if(movingObj instanceof Ship && otherMovingObj instanceof AlienProjectile) {
									// remove the shot that hit you
									screenObjects.remove(j);
																		
									// explosion effect
									Explosion explosion = new Explosion(movingObj.getLocation(), 
											movingObj.getSize(), explosionImg.getImage(), 0, 10);
									explosion.setArbitraryVector(new ObjectVector(0, 0));
									screenObjects.add(explosion);
									
									// remove the ship
									screenObjects.remove(movingObj);
									
									// play sound
									playSound(shipExplosionSound);
									
									displayPlayNextLife = true;
									
									// decrement lives
									Screen.setLives(Screen.getLives() - 1);
									
									// if lives is still > 0, delete all alien
									// shots, and remove all ammo
									if(Screen.getLives() > 0) {
										// delete all alien shots on the screen
										for(int m = 0; m < screenObjects.size(); m++) {
											if(screenObjects.get(m) instanceof AlienProjectile) {
												screenObjects.remove(m);
											}
										}
										
										// add a new ship
										Rectangle shipDimensions = new Rectangle(60,40);
										shipHeight = (int) (screenHeight - shipDimensions.getHeight()*2.5);
										int x = (screenWidth / 2) - 30;
										int y = shipHeight;
										Ship laserCannon = new Ship(new Point(x, y), new Rectangle(60,40), laserCannonImg.getImage());
										laserCannon.setArbitraryVector(new ObjectVector(0,0));
										laserCannon.setAngle(90);
										screenObjects.add(0,laserCannon);
									}
									if(Screen.getLives() == 0) {
										displayGameOver = true;
									}
								}
								
								// If an invader was hit
								if(movingObj instanceof Invader && otherMovingObj instanceof ShipProjectile) {							
									// remove the shot that hit the invader
									screenObjects.remove(j);
									
									// remove the invader
									screenObjects.remove(i);
									
									// explosion effect
									Explosion explosion = new Explosion(movingObj.getLocation(), 
											movingObj.getSize(), explosionImg.getImage(), 0, 10);
									explosion.setArbitraryVector(new ObjectVector(0, 0));
									screenObjects.add(explosion);
									
									// play sound
									playSound(invaderExplosionSound);
									
									// increment score
									Invader deadInvader = (Invader) movingObj;

									for(int k = 0; k < screenObjects.size(); k++) {
										if(screenObjects.get(k) instanceof Score) {
											ScreenObject score = screenObjects.get(k);
											Score currentScore = (Score) score;
											currentScore.setScore(currentScore.getScore() + deadInvader.getInvaderPoints());
											break;
										}
									}
									
									// check to see if any other invaders are left
									// if there aren't, start the next level and increment lives
									int numOfInvadersLeft = 0;
									
									for(int k = 0; k < screenObjects.size(); k++) {
										if(screenObjects.get(k) instanceof Invader) {
											numOfInvadersLeft++;
											break;
										}
									}
									
									if(numOfInvadersLeft == 0) {
										if (!displayNewLevel){
										displayNewLevel = true;
										// start next level
										int nextLevel = level.getLevelNumber() + 1;
										level.setLevelNumber(nextLevel);
										setLevelDisplay(nextLevel + 1);
										// increment lives
										if(Screen.getLives() < 3) {
											Screen.setLives(Screen.getLives() + 1);
										}
										
										// give the player a powerup
										Random randomGen = new Random();
										int powerupX = 100 + randomGen.nextInt(600);
										Powerup gattlingPowerup = new Powerup(new Point(powerupX, shipHeight), new Rectangle(40, 40), powerupImg.getImage());
										screenObjects.add(gattlingPowerup);
										}
									}
								}
								
								// If a bunker was hit by a player or invader shot
								if(movingObj instanceof Bunker && (otherMovingObj instanceof ShipProjectile 
										|| otherMovingObj instanceof AlienProjectile)) {
									// remove the shot that hit the bunker
									screenObjects.remove(j);
									
									// decrement hitPoints by 1
									Bunker damagedBunker = (Bunker) movingObj;
									damagedBunker.setHitPoints(damagedBunker.getHitPoints() - 1);
									
									// check if the bunker's hitPoints are divisible by 5
									// and swap in the appropriate image
									if(damagedBunker.getHitPoints() % 5 == 0) {
										String imgName = "bunker" + String.valueOf(damagedBunker.getHitPoints()) + ".png";
										ImageIcon newBunkerImg = new ImageIcon(imgName);
										damagedBunker.setImage(newBunkerImg.getImage());
									}
									
									// if it's == 0, remove the bunker
									if(damagedBunker.getHitPoints() == 0) {
										screenObjects.remove(i);
										
										// play destroyed sound
										playSound(shipExplosionSound);
									}
								}
								
								// If a mystery ship was hit
								if(movingObj instanceof MysteryShip && otherMovingObj instanceof ShipProjectile) {
									// remove the shot that hit the ship
									screenObjects.remove(j);
									
									// remove the ship
									screenObjects.remove(i);
									
									// explosion effect
									Explosion explosion = new Explosion(movingObj.getLocation(), 
											movingObj.getSize(), explosionImg.getImage(), 0, 10);
									explosion.setArbitraryVector(new ObjectVector(0, 0));
									screenObjects.add(explosion);
									
									// play sound
									playSound(invaderExplosionSound);
									
									// increment score
									MysteryShip deadShip = (MysteryShip) movingObj;

									for(int k = 0; k < screenObjects.size(); k++) {
										if(screenObjects.get(k) instanceof Score) {
											ScreenObject score = screenObjects.get(k);
											Score currentScore = (Score) score;
											currentScore.setScore(currentScore.getScore() + deadShip.getMysteryValue());
											break;
										}
									}
								}
							}
						}
					}
				}
			}
						
			//make the invaders oscillate
			for (ScreenObject obj: screenObjects) {
				if (obj instanceof Invader) {
					if (obj.getLocation().getX() <= 100) {
						Invader.invaderVector.setChangeX(1 + (level.getLevelNumber()/4));
						Invader.invaderVector.setChangeY(5);
					}
					else if (obj.getLocation().getX() >= 
							Screen.screenWidth - 100) {
						Invader.invaderVector.setChangeX(-1 - (level.getLevelNumber()/4));
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
			
			// delete any explosions whose age is == 0
			for(int i = 0; i < screenObjects.size(); i++) {
				ScreenObject obj = screenObjects.get(i);
				if(obj instanceof Explosion) {
					Explosion exp = (Explosion) obj;
					if(exp.getAge() <= 0) {
						screenObjects.remove(obj);
					}
				}
			}
			
			//delete any objects that fly off the screen (projectiles)
			for(int i = 0; i < screenObjects.size(); i++) {
				if (screenObjects.get(i).getLocation().getY() >= (screenHeight + invaderHeight) || (screenObjects.get(i).getLocation().getY()<= (0 - invaderHeight))){
					screenObjects.remove(i);
				}
			}
			
			// Delete any mystery ships that go off the screen
			for(int i = 0; i < screenObjects.size(); i++) {
				if(screenObjects.get(i) instanceof MysteryShip) {
					MysteryShip currentShip = (MysteryShip) screenObjects.get(i);
					
					if(currentShip.getLocation().getX() <= 0 - mysteryWidth || currentShip.getLocation().getX() >= screenWidth + mysteryWidth) {
						screenObjects.remove(i);
					}
				}
			}
			
			Invader.invaderVector.setChangeY(0);
			
			//randomly generate a mystery ship
			MysteryShip mysteryShip = new MysteryShip(new Point(mysteryX, mysteryY), new Rectangle(mysteryWidth, mysteryHeight), mysteryPoints, mysteryImg.getImage());
			mysteryShip.setArbitraryVector(new ObjectVector(-4 - (level.getLevelNumber()/4),0));
			
			if (Math.random() < 0.001+(.001*(level.getLevelNumber()/2))){
				screenObjects.add(mysteryShip);
				playSound(mysteryShipSound);				
			}
			
			//randomly generate an alien shot
			for (int i = 0; i < screenObjects.size(); i++) {
				ScreenObject obj = screenObjects.get(i);
				if (obj instanceof Invader) {
					if (Math.random() < .0005 + (.0005*(level.getLevelNumber()/2))){
						timer.stop();
						fireFromX = (int) obj.getLocation().getX();
						fireFromY = (int) obj.getLocation().getY();
						AlienProjectile alienShot = new AlienProjectile(new Point(fireFromX, fireFromY), new Rectangle(7, 30), alienShotImg.getImage(), 180);
						alienShot.setArbitraryVector(new ObjectVector(0,7));
						screenObjects.add(alienShot);
						timer.start();
						File shotSound = new File("invaderShot.WAV");
						playSound(shotSound);
					}
				}
			}			
			
			repaint();
		}
	}
	
	@Override
	
	/**
	 * Handles key events
	 */
	public void keyPressed(KeyEvent event){
		int keyCode = event.getKeyCode();
		Ship playerShip = null;
		if (screenObjects.get(0) instanceof Ship) {
			playerShip = (Ship) screenObjects.get(0);
		}
		switch (keyCode) {
		
		//press x to fire with the gattling gun
		
			case KeyEvent.VK_X:
				if (playerShip != null && playerShip.getAmmo() > 0) {
					Random gattlingRand = new Random();
					int gatVecY = gattlingRand.nextInt(10) - 15;
					ObjectVector randomVector = new ObjectVector(0, gatVecY);
					int gattlingX = gattlingRand.nextInt(39) - 20;
					Point p = playerShip.getLocation();
					double a = playerShip.getAngle();
					Rectangle r = playerShip.getSize();
					ShipProjectile shot = new ShipProjectile(new Point (p.x + r.width/2 + gattlingX, shipHeight-20), new Rectangle(7,30), shipGattlingShotImg.getImage(), a);
					shot.setArbitraryVector(randomVector);
					screenObjects.add(shot);
					File shotSound = new File("gattlingShot.WAV");
					playSound(shotSound);
					playerShip.decrementAmmo();
				}
				else if(playerShip != null && playerShip.getAmmo() == 0) {
					// play out of ammo sound
					File noAmmoSound = new File("noAmmo.WAV");
					playSound(noAmmoSound);
				}
				break;
				
			//press left arrow to go left
			case KeyEvent.VK_LEFT:
				playerShip.setArbitraryVector(new ObjectVector(-3, 0));
				playerShip.move();
				break;
			
			//press right arrow to go right
			case KeyEvent.VK_RIGHT:
				playerShip.setArbitraryVector(new ObjectVector(3, 0));
				playerShip.move();
				break;
			
			//press p to pause and unpause
			case KeyEvent.VK_P:
				if(isPaused) {
					timer.start();
					isPaused = false;
				}
				else {
					timer.stop();
					isPaused = true;
				}
				break;
				
			case KeyEvent.VK_ENTER:
				if (displayNewLevel){
					addInvaders();
				}
				displayNewLevel = false;
				displayPlayNextLife = false;
				timer.start();
				break;
			
			case KeyEvent.VK_Y:
				if (displayGameOver){
					currentLevel = 0;
					lives = 3;
					addMovingObjects();
				    Score theScore = new Score(new Point(screenWidth/2 - 400, 30), new Rectangle(0,0));
					screenObjects.add(theScore);
					
				    Lives playerLives = new Lives(new Point(screenWidth/2 - 100, 30), new Rectangle(0,0));
					screenObjects.add(playerLives);
					
					screenObjects.add(level);
					displayGameOver = false;
					timer.start();
				break;
				}
			
			case KeyEvent.VK_N:
				if (displayGameOver = true){
					System.exit(0);
				}
				
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
		}
		
		repaint();
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// Store the key that is pressed
		int key = e.getKeyCode();
		
		Ship playerShip = null;
		if (screenObjects.get(0) instanceof Ship) {
			playerShip = (Ship) screenObjects.get(0);
		}
		
		//press space to shoot
		if(key == KeyEvent.VK_SPACE) {
			if (playerShip != null) {
				Point p = playerShip.getLocation();
				double a = playerShip.getAngle();
				Rectangle r = playerShip.getSize();
				ShipProjectile shot = new ShipProjectile(new Point (p.x + (r.width/2 - 3), shipHeight-20), new Rectangle(7,30), shipShotImg.getImage(), a);
				shot.setArbitraryVector(projectileVector);
				screenObjects.add(shot);
				File shotSound = new File("shipShot.WAV");
				playSound(shotSound);
			}
		}
		
		// Reset the vector to 0, 0
		if(screenObjects.get(0) instanceof Ship) {
			if(key == e.VK_LEFT || key == e.VK_RIGHT) {
				playerShip.setArbitraryVector(new ObjectVector(0, 0));
				playerShip.move();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	/**
	 * @return the currentLevel
	 */
	public static int getLevelDisplay() {
		return levelDisplay;
	}

	/**
	 * @param currentLevel the currentLevel to set
	 */
	public static void setLevelDisplay(int currentLevel) {
		Screen.levelDisplay = currentLevel;
	}
	
	public static int getAmmo() {
		return currentAmmo;
	}

	/**
	 * @return the lives
	 */
	public static int getLives() {
		return lives;
	}

	/**
	 * @param lives the lives to set
	 */
	public static void setLives(int numLives) {
		lives = numLives;
	}
	
	/**
	 * Gets the sound file to be played.
	 * @param sound sound to be played.
	 */
	static void playSound(File sound){
		try{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength()/100000);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @return the currentLevel
	 */
	public static int getCurrentLevel() {
		return currentLevel;
	}
	/**
	 * @param currentLevel the currentLevel to set
	 */
	public static void setCurrentLevel(int currentLevel) {
		Screen.currentLevel = currentLevel;
	}
}
