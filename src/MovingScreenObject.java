import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * @author Derick Owens
 *
 */
public abstract class MovingScreenObject extends ScreenObject 
{
	
	protected ObjectVector arbitraryVector;
	protected Image arbitraryImage;
	protected double angle;
	
	
	/**
	 * 
	 * @param location location of the moving screen object
	 * @param size size of the object
	 * @param i image of the object
	 */
	
	public MovingScreenObject(Point location, Rectangle size, Image i, double angle)
	{
		super(location, size);
		arbitraryImage = i;
		this.angle = angle;
	}
	/**
	 * Move the screen object
	 */
	public void move() 
	{
		location.x += arbitraryVector.getChangeX();
		location.y += arbitraryVector.getChangeY();
		
		if (location.x > Screen.screenWidth) {
			location.x -= Screen.screenWidth;
		}
		if (location.x < 0) {
			location.x += Screen.screenWidth;
		}
		if (location.y > Screen.screenHeight) {
			location.y -= Screen.screenHeight;
		}
		if (location.y < 0){
			location.y += Screen.screenHeight;
		}
	}
	
	/**
	 * Draw the screen object
	 */
	public void draw(Graphics g)
	{
		g.drawImage(arbitraryImage,  location.x,  location.y, size.width, size.height, null);
	}

	/**
	 * @return the arbitraryVector
	 */
	public ObjectVector getArbitraryVector() {
		return arbitraryVector;
	}

	/**
	 * @param arbitraryVector the arbitraryVector to set
	 */
	public void setArbitraryVector(ObjectVector arbitraryVector) {
		this.arbitraryVector = arbitraryVector;
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

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setImage(Image i) {
		arbitraryImage = i;
	}
	
	/**
	 * Return true if the objects collide.
	 * 
	 * @param obj The object whose position we're comparing.
	 * @return True if there is a collision; false, otherwise. 
	 */
	public boolean collide(MovingScreenObject otherObj) {
		Rectangle objRect = this.getSize();
		Rectangle otherObjRect = otherObj.getSize();
		objRect.setLocation(this.getLocation());
		otherObjRect.setLocation(otherObj.getLocation());
		if(otherObjRect.intersects(objRect)) {
			return true;
		}
		return false;
	}
}
