package Cartas;

import java.util.*;

public class Hand {

	// Campo de la clase
	private List<Card> cardsList; 
	private Map<Card,Integer> cardsMap;
	
	/**
	 * Constructor
	 * @param datos El parametro datos definen las cartas que tienen en la mano
	 */
	public Hand(String datos){
		cardsList = new ArrayList<Card>();
		cardsMap = new TreeMap<Card,Integer>();
		parse(datos);
	}

	/**
	 * El metodo para obtener la carta de la posicion i
	 * @param i El parametro i define la posicion de la carta
	 * @return cardsList es el objeto de carta en la posicion
	 */
	public Card getCarta(int i){
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
	public Map<Card,Integer> getCardsMap(){
		return this.cardsMap;
	}
	
	public void reverse(){
		Collections.sort(cardsList, new Comparator<Card>() {
	       	@Override
			public int compare(Card arg0, Card arg1) {
				// TODO Auto-generated method stub
				return arg0.getValue().getValor() - arg1.getValue().getValor();
			}           
	    });
	}
	
	/**
	 * Metodo para parsea los datos de entrada en forma de lista y mapa
	 * @param entrada El parametro entrada define String de cartas
	 */
	private void parse(String entrada){
		for(int i = 0; i < entrada.length(); i=i+2){
			
			Card card = new Card(entrada.substring(i, i+1),entrada.substring(i+1, i+2));
			this.cardsList.add(card);
			if(this.cardsMap.containsKey(card)){
				this.cardsMap.put(card, cardsMap.get(card).intValue()+1);
			} else {
				this.cardsMap.put(card, 1);
			}
		}
		Collections.sort(cardsList, new Comparator<Card>() {
	       	@Override
			public int compare(Card arg0, Card arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue().getValor() - arg0.getValue().getValor();
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
