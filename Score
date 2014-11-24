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
 * The Score is a screen object that displays the current number
 * of points a player has achieved in the current game.
 * @author j halstead
 *
 */
public class Score extends ScreenObject
{
   private int score;
   /**
    * Create the score at a particular location and size.
    * The score starts at zero.
    * @param location The starting location.
    * @param size The size.
    */
   public Score(Point location, Rectangle size)
   {
      super(location, size);
      score = 0;
      // TODO Auto-generated constructor stub
   }
   /**
    * To draw the score, convert the integer to a string.
    */
   @Override
   public void draw(Graphics g)
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
	   //draw the score display
         g.setColor(Color.white);
         g.setFont(new Font("Press Start 2P",Font.BOLD, 24));
         g.drawString("SCORE:"+ score, location.x, location.y);
             
   }
   /**
    * Retrieve the score.
    * @return the score
    */
   public int getScore() {
       return score;
   }

   /**
    * Change the score.
    * @param score the score to set
    */
   public void setScore(int score) {
       this.score = score;
   }

}
