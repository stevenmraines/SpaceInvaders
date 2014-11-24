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
 * The game's Level determines how hard the game is for the user.
 * For instance, at each level, there may be more invader shots.
 * @author Derick Owens
 *
 */

public class Level extends ScreenObject{

	private int levelNumber;
	
	/**
	 * Each Level has an associated number starting at "number".
	 * Subsequent levels are higher.  
	 * @param number The level value.
	 * @param location the location of the level output
	 * @param size the size of the output box
	 */
	public Level(int number, Point location, Rectangle size) {
		super(location, size);
		levelNumber = number;
	}
	/**
	 * Retrieves the level number.
	 * @return the levelNumber
	 */
	public int getLevelNumber() {
		return levelNumber;
	}
	/**
	 * Changes the level number.
	 * @param levelNumber the levelNumber to set
	 */
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	/**
	 * draw the Level
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
		   //write the Level
	      g.setColor(Color.white);
	      g.setFont(new Font("Press Start 2P",Font.BOLD, 24));
	      g.drawString("LEVEL:"+ Screen.getLevelDisplay() , location.x, location.y);
	   }
}
