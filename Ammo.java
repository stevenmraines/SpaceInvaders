import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

/**
 * Displays the ammo count for the special gattling laser on the screen.
 * @author Steven Raines
 *
 */
public class Ammo extends ScreenObject {
	
	public Ammo(Point location, Rectangle size) {
		super(location, size);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		try{
			   Font finalFont = null;
			   File gameFont = new File("PressStart2P.ttf");
			   if (gameFont.exists()){
				   finalFont = Font.createFont(Font.TRUETYPE_FONT, gameFont).deriveFont(Font.PLAIN, 22f);
				   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				   ge.registerFont(finalFont);
			   }
			   }catch (FontFormatException e){
				   e.printStackTrace();
			   }catch (IOException f){
			   f.printStackTrace();
		   }
	      g.setColor(Color.white);
	      g.setFont(new Font("Press Start 2P",Font.BOLD, 24));
	      g.drawString("GATTLING AMMO:" + Screen.getAmmo(), location.x, location.y);
	}
}
