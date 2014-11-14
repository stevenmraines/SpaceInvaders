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
	/**
	 * 
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
}
