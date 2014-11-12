import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


public class Invader extends Enemy {
	public static int baseY = 500;
	public Invader(Point p, Rectangle s, int points, Image i){
		super(p, s, points, i);
	}
	public void move() 
	{
		location.x += arbitraryVector.getChangeX();
		location.y += arbitraryVector.getChangeY();
		
		if (location.x == Screen.screenWidth-100) {
			arbitraryVector.setChangeX(-1);
			this.setBaseY(baseY - 20); 
		}
		if (location.x == 100) {
			arbitraryVector.setChangeX(1);
			this.setBaseY(baseY - 20);
		}
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
}
