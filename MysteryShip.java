import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 * 
 * @author Derick Owens
 *
 */

public class MysteryShip extends Enemy {
	
	//TODO: Figure out how to generate random values in increments of 50.
	protected int randomValue = 50;
	
	
	public MysteryShip(Point p, Rectangle s, int points, Image i){
		super(p, s, points, i);
		randomValue = points;
	}
	public void move() 
	{
		location.x += arbitraryVector.getChangeX();
		location.y += arbitraryVector.getChangeY();
	}


	/**
	 * @return the randomValue
	 */
	public int getRandomValue() {
		return randomValue;
	}


	/**
	 * @param randomValue the randomValue to set
	 */
	public void setRandomValue(int randomValue) {
		this.randomValue = randomValue;
	}
}
