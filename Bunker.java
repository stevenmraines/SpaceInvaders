import java.awt.Graphics;
//Bunkers are not moving objects and thus extends ScreenObject.
import java.awt.Point;
import java.awt.Rectangle;

public class Bunker extends ScreenObject {

	public Bunker(Point location, Rectangle size){
		super(location, size);
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}

