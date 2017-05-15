import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/**
 * @author Derick Owens
 *
 */
public class GameWindow extends JFrame {
	/**
	 * Call screen to produce the game in a window
	 */
	public GameWindow(){
		setTitle("Space Invaders");
		Screen screen = new Screen();
		add(screen);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	/**
	 * Create SpaceInvaders window.
	 * @param args Ignored.
	 */
	public static void main(String[] args){
		JFrame startFrame = new JFrame("Space Invaders");
		StartScreen start = new StartScreen();
		startFrame.add(start);
		startFrame.pack();
		startFrame.setResizable(false);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setVisible(true);
		
		try {
			TimeUnit.SECONDS.sleep(4);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		startFrame.dispose();
		
		new GameWindow();
	}
}