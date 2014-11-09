import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

//Enemies contain points to add to score upon destruction
public class Enemy extends MovingScreenObject {
	/**
	 * @author Derick Owens
	 */
	protected int pointValue;
	
	/**
	 * 
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
