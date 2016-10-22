package GUI;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cards.Card;
import GUI.Helpers.CardImageName;

public class CardPanel extends JPanel {

	private Card card;
	
	public CardPanel(Card card){
		this.card = card;
		create();
	}
	
	private void create(){
		ImageIcon cardImage = new ImageIcon(CardImageName.getImageName(card));
		Image newimg = cardImage.getImage().getScaledInstance(100, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		cardImage = new ImageIcon(newimg); 
		
		JLabel cardLabel = new JLabel(cardImage);
		this.add(cardLabel);
		this.setVisible(true);
	}
}
