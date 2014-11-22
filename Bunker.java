import java.awt.Graphics;
import java.awt.Image;
//Bunkers are not moving objects and thus extends ScreenObject.
import java.awt.Point;
import java.awt.Rectangle;

public class Bunker extends MovingScreenObject {
	private int hitPoints = 40;
	
	public Bunker(Point location, Rectangle size, Image i){
		super(location, size, i, 0);
	}
	
	public int getHitPoints() {
		return hitPoints;
	}
	
	public void setHitPoints(int hp) {
		hitPoints = hp;
	}
}
