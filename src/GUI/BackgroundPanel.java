package GUI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{
	private Image backgroundImage;
	
	/**
	 * Constructor
	 */
	BackgroundPanel(){
		backgroundImage = new ImageIcon(".\\resources\\img\\poker_board.png").getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(backgroundImage != null){
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),this);
		}
	}
}
