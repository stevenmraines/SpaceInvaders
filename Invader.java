import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
/**
 * 
 * @author Derick Owens
 *
 */

public class Invader extends Enemy {
	public static int baseY = 325;
	public static ObjectVector invaderVector = new ObjectVector(1,0);
	public int invaderPoints;
	
	/**
	 * 
	 * @param p location of the invader
	 * @param s size of the invader
	 * @param points value of the invader
	 * @param i image of the invader
	 */
	public Invader(Point p, Rectangle s, int points, Image i){
		super(p, s, points, i);
		invaderPoints = points;
	}
	/**
	 * Move the invader
	 */
	@Override
	public void move() 
	{
		location.x += invaderVector.getChangeX();
		location.y += invaderVector.getChangeY();
	}
	/**
	 * @return the baseY
	 */
	public static int getBaseY() {
		return baseY;
	}
	/**
	 * @param baseY the baseY to set
	 */
	public void setBaseY(int baseY) {
		this.baseY = baseY;
	}
	/**
	 * @return the invaderVector
	 */
	public static ObjectVector getInvaderVector() {
		return invaderVector;
	}
	/**
	 * @param invaderVector the invaderVector to set
	 */
	public static void setInvaderVector(ObjectVector invaderVector) {
		Invader.invaderVector = invaderVector;
	}
	/**
	 * @return the invaderPoints
	 */
	public int getInvaderPoints() {
		return invaderPoints;
	}
	/**
	 * @param invaderPoints the invaderPoints to set
	 */
	public void setInvaderPoints(int iP) {
		invaderPoints = iP;
	}
}
