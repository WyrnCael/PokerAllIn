package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cards.Card;
import Cards.Hand;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel{
	
	// campos de la clase
	private Image backgroundImage;
	private Hand hand;
	
	/**
	 * Constructor
	 * @param hand	El parametro hand define la mano de cartas que quiere poner
	 */
	public PlayerPanel(Hand hand){
		this.hand = hand;
		backgroundImage = new ImageIcon(".\\resources\\img\\player_background.png").getImage();
		initGUI();
	}
	
	/**
	 * El metodo que pinta el panel
	 */
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
		if(this.hand.getCardsList().size() == 4){
			this.setPreferredSize(new Dimension(240 , 120));
		} else{
			this.setPreferredSize(new Dimension(120 , 120));
		}
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
