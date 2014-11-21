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
	
	//Generate a randomly selected mystery value between 50 and 250 
	Random generator = new Random();
	int i = generator.nextInt(5) + 1;
	protected int mysteryValue = 50*i;
	
	
	public MysteryShip(Point p, Rectangle s, int points, Image i){
		super(p, s, points, i);
		mysteryValue = points;
	}
	
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
