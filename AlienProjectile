
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
/**
 * 
 * @author Derick Owens
 *
 */

/**
 * 
 * This class creates projectiles shot by aliens
 *
 */
public class AlienProjectile extends MovingScreenObject {
	
	/**
	 * 
	 * @param location location of the shot when it first spawns
	 * @param size size of the shot
	 * @param i Image used to represent the shot
	 * @param angle angle at which the shot is aimed
	 */
	public AlienProjectile(Point location, Rectangle size, Image i, double angle){
		super(location, size, i, angle);
		
		double endX = location.x;
		double endY = location.y;
		
		double newEndX = location.x + (endX - location.x)*Math.cos(Math.toRadians(angle));
		double newEndY = location.y + (endY - location.y)*Math.sin(Math.toRadians(angle));
		
		double changeX = (newEndX - location.x);
		double changeY = (newEndY - location.y);
		arbitraryVector = new ObjectVector(changeX, changeY);
	}
	
	/**
	 * How the shot moves
	 */
	public void move() 
		{
			location.x += arbitraryVector.getChangeX();
			location.y += arbitraryVector.getChangeY();
		}
		
}
