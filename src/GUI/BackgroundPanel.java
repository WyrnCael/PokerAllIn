package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel{
	private Image backgroundImage;
	
	/**
	 * Constructor
	 */
	BackgroundPanel(){		
		try {
			URL url = this.getClass().getClassLoader().getResource("img" + File.separator + "poker_board.png");
			BufferedImage image = ImageIO.read(url);
			backgroundImage = new ImageIcon(image).getImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(backgroundImage != null){
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),this);
		}
	}
}
