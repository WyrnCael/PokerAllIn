package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cards.Card;
import Cards.Hand;

public class PlayerPanel extends JPanel{
	
	private Image backgroundImage;
	private Hand hand;
	
	public PlayerPanel(){
		backgroundImage = new ImageIcon(".\\resources\\img\\player_background.png").getImage();
	}
	
	public PlayerPanel(Hand hand){
		this.hand = hand;
		backgroundImage = new ImageIcon(".\\resources\\img\\player_background.png").getImage();
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel cardsPanel = new JPanel();
		for(int i = 0; i < this.hand.getCardsList().size() ; i++) {
			String imgPath = ".\\resources\\img\\PNGCards\\";
			Card card = this.hand.getCard(i);
			String imgName= card.getValue() + "_of_" + card.getSuit() + ".png";
			imgName = imgName.toLowerCase();
			imgPath += imgName;
			ImageIcon img = new ImageIcon (new ImageIcon(imgPath).getImage().getScaledInstance(50, 70, Image.SCALE_SMOOTH));
			JLabel imgLabel = new JLabel(img);
			cardsPanel.add(imgLabel);
		}
		cardsPanel.setOpaque(false);
		
		this.add(cardsPanel, c);
		
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(120 , 120));
		this.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(backgroundImage != null){
			g.drawImage(backgroundImage, 0, 0, getWidth(),getHeight(),this);
		}
	}
}
