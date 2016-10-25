package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import Cards.Card;
import Cards.Hand;
import Players.Player;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {

	// campos de la clase
	private Image backgroundImage;
	private Hand hand;
	private Player player;

	/**
	 * Constructor
	 * 
	 * @param hand
	 *            El parametro hand define la mano de cartas que quiere poner
	 */
	public PlayerPanel(Player player) {
		this.hand = player.getHand();
		this.player = player;
		backgroundImage = new ImageIcon(
				".\\resources\\img\\player_background.png").getImage();
		initGUI();
	}

	/**
	 * El metodo que pinta el panel
	 */
	private void initGUI() {
		this.setLayout(new FlowLayout());
		JPanel cardsPanel = new JPanel();
		OverlayLayout overlay = new OverlayLayout(cardsPanel);
		cardsPanel.setLayout(overlay);
		for (int i = 0; i < this.hand.getCardsList().size(); i++) {
			String imgPath = ".\\resources\\img\\PNGCards\\";
			Card card = this.hand.getCard(i);
			String imgName = card.getValue() + "_of_" + card.getSuit() + ".png";
			imgName = imgName.toLowerCase();
			imgPath += imgName;
			ImageIcon img = new ImageIcon(new ImageIcon(imgPath).getImage()
					.getScaledInstance(70, 90, Image.SCALE_SMOOTH));
			JLabel imgLabel = new JLabel(img);
			imgLabel.setAlignmentX((float) i * 0.2f);
			imgLabel.setAlignmentY((float) i * 0.05f);
			cardsPanel.add(imgLabel);
		}
		cardsPanel.setOpaque(false);
		this.add(cardsPanel);

		JLabel nombreLabel = new JLabel(player.getName());
		nombreLabel.setFont(new Font("Courier New", Font.BOLD, 12));
		nombreLabel.setForeground(Color.WHITE);

		this.add(nombreLabel);

		this.setOpaque(false);
		this.setPreferredSize(new Dimension(120, 120));

		this.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
