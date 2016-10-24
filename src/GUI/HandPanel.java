package GUI;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cards.Card;
import Cards.Hand;

@SuppressWarnings("serial")
public class HandPanel extends JPanel{
	
	private Hand hand;
	
	public HandPanel(){
		
	}
	
	public HandPanel(Hand hand){
		this.hand = hand;
		initGUI();
	}
	
	private void initGUI(){
		for(int i = 0; i < this.hand.getCardsList().size() ; i++) {
			String imgPath = ".\\resources\\img\\PNGCards\\";
			Card card = this.hand.getCard(i);
			String imgName= card.getValue() + "_of_" + card.getSuit() + ".png";
			imgName = imgName.toLowerCase();
			imgPath += imgName;
			ImageIcon img = new ImageIcon (new ImageIcon(imgPath).getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH));
			JLabel imgLabel = new JLabel(img);
			this.add(imgLabel);
		}
		
		this.setOpaque(false);
		this.setVisible(true);
	}
}
