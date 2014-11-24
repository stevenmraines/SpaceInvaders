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
		GameWindow window = new GameWindow();
	}
}

