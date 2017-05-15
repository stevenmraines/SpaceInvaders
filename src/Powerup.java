import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * @author Steven Raines
 *
 */
public class Powerup extends MovingScreenObject {
	protected ObjectVector arbitraryVector;
	protected Image arbitraryImage;
	
	/**
	 * 
	 * @param location location of the powerup
	 * @param size size of the powerup
	 * @param i image of the powerup
	 */
	public Powerup(Point location, Rectangle size, Image i) {
		super(location, size, i, 0);
		arbitraryImage = i;
		this.setArbitraryVector(new ObjectVector(0, 0));
	}
	
	/**
	 * draw the powerup
	 */
	public void draw(Graphics g)
	{
		g.drawImage(arbitraryImage,  location.x,  location.y, size.width, size.height, null);
	}
	
	/**
	 * @return the arbitraryImage
	 */
	public Image getArbitraryImage() {
		return arbitraryImage;
	}

	/**
	 * @param arbitraryImage the arbitraryImage to set
	 */
	public void setArbitraryImage(Image arbitraryImage) {
		this.arbitraryImage = arbitraryImage;
	}
}
