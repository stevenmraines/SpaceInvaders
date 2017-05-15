import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class StartScreen extends JPanel {
	private ImageIcon startScreenImg = new ImageIcon("images/startScreen.png");
	
	public StartScreen() {
		setPreferredSize(new Dimension(800, 700));
		setBackground(Color.black);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(startScreenImg.getImage(), 0, 0, this);
	}
}