import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * @author Derick Owens
 *
 */

public abstract class ScreenObject {
	protected Point location;
	protected Rectangle size;
	
	/**
	 * Create the screen object and its size and location.
	 * @param location location of the screen object
	 * @param size size of the screen object
	 */
	
	public ScreenObject(Point location, Rectangle size){
		super();
		this.location = location;
		this.size = size;
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * @return the size
	 */
	public Rectangle getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Rectangle size) {
		this.size = size;
	}
	
	/**
	 * 
	 * @param g make all subclasses of screenobject have a draw method
	 */
	abstract public void draw(Graphics g);
}
