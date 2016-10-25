package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cards.Card;
import Cards.Hand;

@SuppressWarnings("serial")
public class CommonCardsPanel extends JPanel {

	// campos de la clase
	private Hand hand;

	/**
	 * Constructor
	 * 
	 * @param hand
	 *            El parametro hand define el mano que quiere pintar
	 */
	public CommonCardsPanel(Hand hand) {
		this.hand = hand;
		initGUI();
	}

	/**
	 * El metodo que pinta el panel
	 */
	private void initGUI() {
		for (int i = 0; i < this.hand.getCardsList().size(); i++) {
			String imgPath = "img" + File.separator + "PNGCards" + File.separator;
			Card card = this.hand.getCard(i);
			String imgName = card.getValue() + "_of_" + card.getSuit() + ".png";
			imgName = imgName.toLowerCase();
			imgPath += imgName;
			
			Image image = null;
			try {
				URL url = this.getClass().getClassLoader().getResource(imgPath);
				BufferedImage buffImage = ImageIO.read(url);
				image = new ImageIcon(buffImage).getImage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ImageIcon img = new ImageIcon(image.getScaledInstance(100, 140, Image.SCALE_SMOOTH));
			JLabel imgLabel = new JLabel(img);
			this.add(imgLabel);
		}

		this.setOpaque(false);
		this.setVisible(true);
	}
}
