import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * @author Derick Owens
 *
 */

public class Ship extends MovingScreenObject {
	private double angle;
	private int gattlingAmmo = 0;
	
	/**
	 * 
	 * @param location location of the ship
	 * @param size size of the ship
	 * @param i image of the ship
	 */
	public Ship(Point location, Rectangle size, Image i){
		super(location, size, i, 0);
		angle = 90;
	}
	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}
	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	@Override
	/**
	 * Simulate movement
	 */
	public void move() {
		
		// Check which direction the player is trying to move
		// If they're trying to go left...
		if(arbitraryVector.getChangeX() < 0) {
			
			// Make sure the laser cannon isn't already all the way to the left
			if(location.x > Screen.screenWidth / 8) {
				location.x += arbitraryVector.getChangeX();
			}
		}
		
		// If they're trying to go right...
		else {
			
			// Make sure the laser cannon isn't already all the way to the right
			if(location.x < Screen.screenWidth - 100) {
				location.x += arbitraryVector.getChangeX();
			}
		}
	}
	
	/**
	 * 
	 * @return see how much ammo there is
	 */
	public int getAmmo() {
		return gattlingAmmo;
	}
	
	/**
	 * uses the ammo available to the player
	 */
	public void decrementAmmo() {
		if(gattlingAmmo > 0) {
			gattlingAmmo--;
		}
	}
	
	/**
	 * adds ammo for the gattling gun when picked up
	 */
	public void addAmmo() {
		gattlingAmmo += 50;
	}
}
