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
	
	ObjectVector arbitraryVector;
	Image arbitraryImage;
	
	/**
	 * 
	 * @param location
	 * @param size
	 * @param i
	 */
	
	public MovingScreenObject(Point location, Rectangle size, Image i)
	{
		super(location, size);
		arbitraryImage = i;
	}
	/* (non-Javadoc)
	 * @see ScreenObject#draw(java.awt.Graphics)
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
	
	public void draw(Graphics g)
	{
		g.drawImage(arbitraryImage,  location.x,  location.y, size.width, size.height, null);
	}
	
	public void collide(MovingScreenObject otherObj){
		
	}
	
	public void destruct(){
		
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
	
}
