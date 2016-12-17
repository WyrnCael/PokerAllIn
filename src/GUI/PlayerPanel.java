package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import Cards.Card;
import Cards.Hand;
import Game.PokerGame;
import Players.HoldEmPlayer;
import Players.Player;


public class PlayerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// campos de la clase
	private Image backgroundImage;
	private Hand hand;
	private Player player;

	public PlayerPanel() {
		super();
		this.setVisible(false);
	}
	
	/**
	 * Constructor
	 * 
	 * @param hand
	 *            El parametro hand define la mano de cartas que quiere poner
	 */
	public void addPlayer(Player player) {
		//Necesario para borrar lo que ya habia antes de volver a crear el nuevo panel
		this.removeAll();
		
		this.hand = player.getHand();
		this.player = player;
		try {
			URL url = this.getClass().getClassLoader().getResource("img" + File.separator + "player_background.png");
			BufferedImage image = ImageIO.read(url);
			backgroundImage = new ImageIcon(image).getImage();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		initGUI();
		
	}
	/*No se por que no funciona, pero con addPlayer si
	public PlayerPanel(Player player) {
		super();
		this.removeAll();
		this.hand = player.getHand();
		this.player = player;
		try {
			URL url = this.getClass().getClassLoader().getResource("img" + File.separator + "player_background.png");
			BufferedImage image = ImageIO.read(url);
			backgroundImage = new ImageIcon(image).getImage();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		initGUI();
		
	}
	*/

	/**
	 * El metodo que pinta el panel
	 */
	private void initGUI() {
		this.setLayout(new FlowLayout());
		JPanel cardsPanel = new JPanel();
		OverlayLayout overlay = new OverlayLayout(cardsPanel);
		cardsPanel.setLayout(overlay);
		//this.player.getHand();
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
				
				e.printStackTrace();
			}
			ImageIcon img = new ImageIcon(image.getScaledInstance(47, 67, Image.SCALE_SMOOTH));
			JLabel imgLabel = new JLabel(img);
			imgLabel.setAlignmentX((float) i * 0.2f);
			imgLabel.setAlignmentY((float) i * 0.05f);
			cardsPanel.add(imgLabel);
		}
		cardsPanel.setOpaque(false);
		this.add(cardsPanel);

		JLabel nombreLabel = new JLabel(player.getName());
		nombreLabel.setFont(new Font("Courier New", Font.BOLD, 10));
		nombreLabel.setForeground(Color.WHITE);
		
		this.add(nombreLabel);

		this.setOpaque(false);
		this.setPreferredSize(new Dimension(220, 220));

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