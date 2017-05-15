import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * @author Derick Owens
 *
 */

public class MysteryShip extends Enemy {
	
	//Generate a randomly selected mystery value between 50 and 250 

	protected int mysteryValue;
	
	/**
	 * 
	 * @param p location of the mystery ship
	 * @param s size of the mystery ship
	 * @param points value of the mystery ship
	 * @param i image of the mystery ship
	 */
	public MysteryShip(Point p, Rectangle s, int points, Image i){
		super(p, s, points, i);
		mysteryValue = points;
	}
	
	/**
	 * Move the mystery ship
	 */
	public void move() 
	{
		location.x += arbitraryVector.getChangeX();
		location.y += arbitraryVector.getChangeY();
	}

	/**
	 * @return the randomValue
	 */
	public int getMysteryValue() {
		return mysteryValue;
	}


	/**
	 * @param randomValue the randomValue to set
	 */
	public void setMysteryValue(int randomValue) {
		this.mysteryValue = randomValue;
	}
}
