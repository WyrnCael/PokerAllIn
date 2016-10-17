package Cartas;

import java.util.*;

public class Hand {

	// Campo de la clase
	private List<Card> cardsList; 
	private Map<Card,Integer> cardsMapValue;
	private Map<Card,Integer> cardsMapSuit;
	private Map<CardOnlyValue,Integer> cardsReference;
	
	/**
	 * Constructor
	 * @param datos El parametro datos definen las cartas que tienen en la mano
	 */
	public Hand(){
		cardsList = new ArrayList<Card>();
		cardsMapValue = new TreeMap<Card,Integer>();	
		cardsMapSuit = new TreeMap<Card, Integer>(new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				char p1 = ((Card) o1).getSuit().getChar();
				char p2 = ((Card) o2).getSuit().getChar();
				if(Character.compare(p1, p2) < 0)
					return 1;
				else if(Character.compare(p1, p2) > 0)
						return -1;
				else
					return 0;
			}

		});
		cardsReference = new TreeMap<CardOnlyValue,Integer>();	
	}
	
	/**
	 * El metodo para obtener la carta de la posicion i
	 * @param i El parametro i define la posicion de la carta
	 * @return cardsList es el objeto de carta en la posicion
	 */
	public Card getCard(int i){
		return this.cardsList.get(i);
	}
	
	/**
	 * El metodo para obtener la lista de las cartas
	 * @return cardsList es el objeto de la lista de cartas
	 */
	public List<Card> getCardsList() {
		return this.cardsList;
	}
	
	/**
	 * El metodo para obtener el mapa de las cartas
	 * @return cardsMap es el objeto del mapa de cartas
	 */
	public Map<Card,Integer> getCardsValueMap(){
		return this.cardsMapValue;
	}
	
	public Map<Card,Integer> getCardsSuitMap(){
		return this.cardsMapSuit;
	}
	
	public Map<CardOnlyValue,Integer> getReferenceCardsMap(){
		return this.cardsReference;
	}
	
	public void reverse(){
		Collections.sort(cardsList, new Comparator<Card>() {
	       	@Override
			public int compare(Card arg0, Card arg1) {
				// TODO Auto-generated method stub
				return arg0.getValue().getValue() - arg1.getValue().getValue();
			}           
	    });
	}
	
	/**
	 * Metodo para parsea los datos de entrada en forma de lista y mapa
	 * @param entrada El parametro entrada define String de cartas
	 */
	public void parseaEInserta(String entrada){
		for(int i = 0; i < entrada.length(); i=i+2){
			
			Card card = new Card(entrada.substring(i, i+1),entrada.substring(i+1, i+2));
			this.cardsList.add(card);
			CardOnlyValue cardAux = new CardOnlyValue(card.getValue());			
			if(this.cardsReference.containsKey(cardAux)){
				this.cardsMapValue.put(card, cardsReference.get(cardAux) + 1);
				this.cardsReference.put(cardAux, cardsReference.get(cardAux) + 1);
			} else {
				this.cardsReference.put(cardAux, 1);
				this.cardsMapValue.put(card, 1);
			}
			
			if(this.cardsMapSuit.containsKey(card)){
				this.cardsMapSuit.put(card, cardsMapSuit.get(card) + 1);
			} else {
				this.cardsMapSuit.put(card, 1);
			}
		}
		Collections.sort(cardsList, new Comparator<Card>() {
	       	@Override
			public int compare(Card arg0, Card arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue().getValue() - arg0.getValue().getValue();
			}
	    });
	}
	
	public String toString(){
		String str = "";
		for(int i=0 ; i < cardsList.size(); i++){
			str += cardsList.get(i);
		}
		return str;
	}
	
}
