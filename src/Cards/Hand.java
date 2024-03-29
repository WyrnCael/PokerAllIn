package Cards;

import java.util.*;

public class Hand {

	// Campos de la clase
	private List<Card> cardsList;
	private Map<Card, Integer> cardsValueMap;
	private Map<Card, Integer> cardSuitMap;

	/**
	 * Constructor sin parametro
	 */
	public Hand() {
		cardsList = new ArrayList<Card>();
	}

	/**
	 * Constructor
	 * 
	 * @param cards
	 *            El parametro cards definen las cartas que tienen en la mano
	 */
	public Hand(String cards) {
		cardsList = new ArrayList<Card>();
		cardsValueMap = new TreeMap<Card, Integer>();
		cardSuitMap = new TreeMap<Card, Integer>(new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				char p1 = ((Card) o1).getSuit().getChar();
				char p2 = ((Card) o2).getSuit().getChar();
				if (Character.compare(p1, p2) < 0)
					return 1;
				else if (Character.compare(p1, p2) > 0)
					return -1;
				else
					return 0;
			}

		});
		parseCard(cards);
	}

	/**
	 * El metodo que devuelve la carta de la posicion i
	 * 
	 * @param i
	 *            El parametro i define la posicion de la carta
	 * @return El objeto de carta de la posicion i, si no existe, null
	 */
	public Card getCard(int i) {
		try{
			return this.cardsList.get(i);
		}
		catch (Exception e){
			return null;
		}
	}

	/**
	 * El metodo que anade la carta a la mano
	 * 
	 * @param card
	 *            El parametro card define el objeto de carta que va a anadir
	 */
	public void add(Card card) {
		this.cardsList.add(card);
	}

	/**
	 * El metodo que anade una lista de cartas
	 * 
	 * @param cards
	 *            El parametro cards define el objeto de una lista de cartas
	 */
	public void addAll(List<Card> cards) {
		this.cardsList.addAll(cards);
	}

	/**
	 * El metodo que devuelve la lista de las cartas
	 * 
	 * @return cardsList es el objeto de la lista de cartas
	 */
	public List<Card> getCardsList() {
		return this.cardsList;
	}

	/**
	 * El metodo que devuelve el mapa de las cartas
	 * 
	 * @return cardsMap es el objeto del mapa de cartas
	 */
	public Map<Card, Integer> getCardsValueMap() {
		return this.cardsValueMap;
	}

	/**
	 * El metodo que devuelve el mapa del palo de las cartas
	 * 
	 * @return cardsMapSuit es el objeto del mapa de cartas
	 */
	public Map<Card, Integer> getCardsSuitMap() {
		return this.cardSuitMap;
	}

	/**
	 * El metodo para insertar la carta en la mano
	 * 
	 * @param input
	 *            El parametro input define los datos de entrada
	 */
	private void parseCard(String input) {
		for (int i = 0; i < input.length(); i = i + 2) {
			Card card = new Card(input.substring(i, i + 1), input.substring(i + 1, i + 2));
			this.cardsList.add(card);

			if (this.cardsValueMap.containsKey(card)) {
				this.cardsValueMap.put(card, this.cardsValueMap.get(card).intValue() + 1);
			} else {
				this.cardsValueMap.put(card, 1);
			}

			if (this.cardSuitMap.containsKey(card)) {
				this.cardSuitMap.put(card, cardSuitMap.get(card) + 1);
			} else {
				this.cardSuitMap.put(card, 1);
			}
		}
		Collections.sort(cardsList);
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < cardsList.size() && i < 5; i++) {
			str += cardsList.get(i);
		}
		return str;
	}
	
	public int size(){
		return cardsList.size();
	}

}
