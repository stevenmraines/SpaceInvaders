import java.awt.Graphics;
import java.awt.Image;
//Bunkers are not moving objects and thus extends ScreenObject.
import java.awt.Point;
import java.awt.Rectangle;

public class Bunker extends MovingScreenObject {
	public Bunker(Point location, Rectangle size, Image i){
		super(location, size, i, 0);
	}
	}


