package Cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DeckCards {
	private Map<String,Card> cards;
	private Random randomGenerator;
	
	public DeckCards(){
		cards = new HashMap<String,Card>();
		randomGenerator = new Random();
		
		for(int i = 2; i < 15; i++){ // Value
			for(int j = 0; j < 4; j++){ // Suit
				Card aux = new Card(i, j);
				cards.put(aux.toString(), aux);
			}			
		}		
	}
	
	public Card getRandomCard(){
		Object[] values = cards.values().toArray();
		Object card = values[randomGenerator.nextInt(values.length)];
		this.cards.remove(card);
		return (Card) card;
	}
	
	public void removeCard(Card card){
		this.cards.remove(card.toString());
	}
	
	public void insertCard(Card card){
		cards.put(card.toString(), card);
	}
	
	public int size(){
		return cards.size();
	}
}
