import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Enemy extends MovingScreenObject {
	/**
	 * @author Derick Owens
	 */
	protected int pointValue;
	
	/**
	 * 
	 * @param p location of the Enemy
	 * @param s size of the enemy
	 * @param points how much the enemy is worth
	 * @param i image of the enemy
	 */
	public Enemy(Point p, Rectangle s, int points, Image i){
		super(p, s, i, 0);
		pointValue = points;
	}

	/**
	 * @return the pointValue
	 */
	public int getPointValue() {
		return pointValue;
	}

	/**
	 * @param pointValue the pointValue to set
	 */
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
}
