package Cards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DeckCards {

	// Campos de la clase
	private Map<String, Card> cards;
	private Random randomGenerator;
	private String[] value = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A" };
	private String[] suit = { "h", "d", "c", "s" };

	/**
	 * Constructor
	 */
	public DeckCards() {
		cards = new HashMap<String, Card>();
		randomGenerator = new Random();

		for (int i = 0; i < value.length; i++) { // Value
			for (int j = 0; j < suit.length; j++) { // Suit
				Card aux = new Card(value[i], suit[j]);
				cards.put(aux.toString(), aux);
			}
		}
	}

	/**
	 * Constructor
	 * 
	 * @param deck
	 *            las cartas de la baraja
	 */
	public DeckCards(Map<String, Card> deck) {
		cards = new HashMap<String, Card>(deck);
		randomGenerator = new Random();
	}

	/**
	 * Coger aleatoriamente una carta de la baraja
	 * 
	 * @return La carta que ha sacado
	 */
	public Card getRandomCard() {
		Object[] values = cards.values().toArray();
		Card card = (Card) values[randomGenerator.nextInt(values.length)];
		return card;
	}
	
	public Card getCardandRemove() {
		Object[] values = cards.values().toArray();
		Card card = (Card) values[randomGenerator.nextInt(values.length)];
		removeCard(card);
		return card;
	}
	
	
	

	/**
	 * Sacar la carta de la baraja
	 * 
	 * @param card
	 *            La carta que quiere sacar
	 */
	public void removeCard(Card card) {
		this.cards.remove(card.toString());
	}

	/**
	 * Sacar todas las carta de la lista
	 * 
	 * @param cardsList
	 *            La lista de cartas
	 */
	public void removeAllCards(List<Card> cardsList) {
		for (int i = 0; i < cardsList.size(); i++) {
			removeCard(cardsList.get(i));
		}
	}

	/**
	 * Devolver la baraja
	 * 
	 * @return la baraja de cartas
	 */
	public Map<String, Card> getDeck() {
		return cards;
	}
}
