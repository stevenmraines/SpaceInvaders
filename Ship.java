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
}
