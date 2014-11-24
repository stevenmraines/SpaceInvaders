/** the number of laser cannons per game 
 * author J Halstead
 */

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
 * 
 * This class displays the number of lives on the screen to the player
 *
 */
public class Lives extends ScreenObject
{
	/**
	 * 
	 * @param location location of the display
	 * @param size size of the display
	 */
   public Lives(Point location, Rectangle size)
   {
      super (location, size);
   }

   @Override
   
   /**
    * Draws the display of lives
    */
   public void draw (Graphics g)
   {
	   //import font
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
	   //draw the LIVES and add the lives from the screen class
      g.setColor(Color.white);
      g.setFont(new Font("Press Start 2P",Font.BOLD, 24));
      g.drawString("LIVES:" + Screen.getLives(), location.x, location.y);
   }
}
