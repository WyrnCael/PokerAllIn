package Cartas;

import java.util.*;

public class Hand {

	
	private ArrayList<Card> card; 
	
	public Hand(){
		card = new ArrayList<Card>();
	}

	public Card getCarta(int i){
		return this.card.get(i);
	}
	
	public List<Card> getCartas() {
		return card;
	}

	public void setCartas(ArrayList<Card> cartas) {
		this.card = cartas;
	}
	
	public void addCartas(Card c){
		card.add(c);
	}
	
	public void deleteCartas(Card c){
		card.remove(c);
	}
	
	public void ordenaPorValorMenorAMayor(){
		Collections.sort(card, new Comparator<Card>() {
	       	@Override
			public int compare(Card arg0, Card arg1) {
				// TODO Auto-generated method stub
				return arg0.getValue().getValor() - arg1.getValue().getValor();
			}           
	    });
	}
	
	public void ordenaPorValorMayorAMenor(){
		Collections.sort(card, new Comparator<Card>() {
	       	@Override
			public int compare(Card arg0, Card arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue().getValor() - arg0.getValue().getValor();
			}
	    });
	}
	
	public void parseaMano(String entrada){
		for(int i = 0; i < entrada.length(); i=i+2){
			Card carta = new Card(Value.parsea(entrada.substring(i, i+1)), Suit.parsea(entrada.substring(i+1, i+2)));
			this.addCartas(carta);
		}
	}
	
	public String toString(){
		String str = "";
		for(int i=0 ; i < card.size(); i++){
			str += card.get(i);
		}
		return str;
	}
	
	
}
