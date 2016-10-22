package GUI;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cards.Hand;
import Game.BestHand;

public class BoardAp1Pr1 extends JPanel{

	private Hand hand;
	private BestHand bestHand;
	
	public BoardAp1Pr1(Hand hand){
		this.hand = hand;
		this.bestHand = new BestHand(hand);
		create();
	}
	
	private void create(){
		this.setBackground(new Color(102,155,118));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel handLabel = new JLabel("Hand:");
		this.add(handLabel);
		
		HandPanel handPanel = new HandPanel(this.hand);
		this.add(handPanel);
		
		JLabel bestHandLabel = new JLabel("Best hand: " + bestHand.toStringOnlyBestHand());
		this.add(bestHandLabel);
		
		HandPanel bestHandPanel = new HandPanel(this.bestHand.getBestHand());
		this.add(bestHandPanel);
		
		JLabel drawsLabel = new JLabel("Draws: " + bestHand.toStringOnlyDraws());
		this.add(drawsLabel);
		
		this.setVisible(true);
	}
}
