import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
/**
 * 
 * @author Derick Owens
 *
 */

//There are multiple types of projectiles to be accounted for
public class Projectile extends MovingScreenObject {
	
	public Projectile(Point location, Rectangle size, Image i, double angle){
		super(location, size, i, angle);
		
		double endX = location.x;
		double endY = location.y;
		
		double newEndX = location.x + (endX - location.x)*Math.cos(Math.toRadians(angle));
		double newEndY = location.y + (endY - location.y)*Math.sin(Math.toRadians(angle));
		
		double changeX = (newEndX - location.x);
		double changeY = (newEndY - location.y);
		arbitraryVector = new ObjectVector(changeX, changeY);
	}
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old = g2.getTransform();
		g2.setColor(Color.white);
		Rectangle2D.Double ray = new Rectangle2D.Double(location.x, location.y, size.getWidth(), size.getHeight());
		AffineTransform at = g2.getTransform();
		at.rotate(Math.toRadians(angle), location.x, location.y);
		g2.setTransform(at);
		g2.fill(ray);
		g2.setTransform(old);
	}
}
