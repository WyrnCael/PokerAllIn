package GUI;

import java.awt.Color;

import javax.swing.JPanel;

import Cards.Hand;

public class Board extends JPanel{

	private Hand hand;
	
	public Board(Hand hand){
		this.hand = hand;
		create();
	}
	
	private void create(){
		int numCartas = hand.getCardsList().size();
		for(int i = 0; i < numCartas; i++){
			CardPanel carta = new CardPanel(hand.getCard(i));
			this.add(carta);
		}
		
		this.setBackground(new Color(102,155,118));
		
		this.setVisible(true);
	}
}
