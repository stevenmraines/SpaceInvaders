import java.awt.Graphics;
import java.awt.Image;
//Bunkers are not moving objects and thus extends ScreenObject.
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * @author Derick Owens
 *
 */
public class Bunker extends MovingScreenObject {
	private int hitPoints = 40;
	
	/**
	 * 
	 * @param location location of the Bunker
	 * @param size size of the Bunker
	 * @param i image of the Bunker
	 */
	public Bunker(Point location, Rectangle size, Image i){
		super(location, size, i, 0);
	}
	
	/**
	 * 
	 * @return hitPoints, which are reduced when a bunker is struck by a shot
	 */
	public int getHitPoints() {
		return hitPoints;
	}
	
	/**
	 * 
	 * @param hp set the hit points
	 */
	public void setHitPoints(int hp) {
		hitPoints = hp;
	}
}
