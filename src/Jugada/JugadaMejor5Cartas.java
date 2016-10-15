package Jugada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import Cartas.Card;
import Cartas.Hand;
import Cartas.Suit;
import Cartas.Value;
import Cartas.Ranking;

public class JugadaMejor5Cartas {
	
	// Campos de clase
	private String cards;
	private Map<Card, Integer> map;
	private Hand hand;
	private String bestHand;
	private Vector<String> draws;
	
	/**
	 * Constructor
	 * 
	 * @param datos El parametro datos definen el String de cartas
	 */
	public JugadaMejor5Cartas(String datos){
		this.cards = datos;
		this.map = new TreeMap<Card, Integer>();
		this.hand = new Hand();
		this.draws = new Vector<String>();
		bestHand();
	}
	
	/**
	 * Metodo para calcular el mejor mano a partir del String de cartas
	 * Utiliza la estructura de datos Treemap para facilitar el calculo
	 */
	private void bestHand(){
		this.hand.parseaMano(this.cards);
		for(int i = 0; i < this.cards.length(); i=i+2){
			Value point = Value.parsea(this.cards.substring(i, i+1));
			Suit palo = Suit.parsea(this.cards.substring(i+1, i+2));
			
			Card carta = new Card(point, palo);
			if(this.map.containsKey(carta)){
				this.map.put(carta, map.get(carta).intValue()+1);
			} else {
				this.map.put(carta, 1);
			}
		}
		this.hand.ordenaPorValorMayorAMenor();
//		Collections.sort(this.hand.getCartas());		// Otra forma de ordenar, puede quitar
		
		int bestValue = 0;			// Valor de mejor mano
		int count = 0;				// Contador para escalera count=4 (straight gutshot) count=5 (straight)
		boolean color = true;		// Boolean para comprobar si es del mismo color
		int gutshot = 0;			// Numero de hueco del mano, gutshot <= 1 puede tener escalera
		boolean straight = true;	// Boolean para comprobar si es una escalera
		int value = 0;				// Variable auxiliar para guardar valor de la carta anterio, para poder comparar despues
		String palo = "";			// Variable auxiliar para guardar palo de la carta anterio, para poder comparar despues
		
		/* Contador para caso de doble pareja, segun entrada de cartas puede tener 3 casos:
				- 1+2+2 : count2 = 3
				- 2+1+2 : count2 = 3
				- 2+2+1 : count2 = 2
		   y con el valor de count2 podemos sacar cartas que queremos desde String cards
		*/
		int count2 = 0;
		
		
		Iterator<Card> it = map.keySet().iterator();
		while(it.hasNext()){
			Card aux = it.next();
			count++;
			count2++;
			
			switch(map.get(aux)){
			case 1:
				if(bestValue == 0){		// Primera vuelta
					bestValue = Ranking.HIGH_CARD.getValor();
					palo = aux.getSuit().getPalo();
					value = aux.getValue().getValor();
					this.bestHand = Ranking.HIGH_CARD.getName() + " " + aux.getValue() + " (" + aux + ")";
				} else if(straight || color){
					
					if(value - aux.getValue().getValor() > 2){
						// Si la diferencia de valor de carta anterio y ahora es mayor que 2, no hay escalera ni gutshot
						straight = false;
						gutshot = 2;
					} else if (value - aux.getValue().getValor() == 2){
						// Si la diferencia es igual a 2, no hay escalera pero puede ser un gutshot
						straight = false;
						gutshot++;
					}
					
					// Si el palo de la carta anterio es diferente que la de ahora, no hay color
					if(!palo.equals(aux.getSuit().getPalo())){
						color = false;
					}
					
					if(count == 5){
						// caso 1+1+1+1+1
						if(color && straight){
								// color y straight son true, escalera de color
								bestValue = Ranking.STRAIGHT_FLUSH.getValor();
								this.bestHand = Ranking.STRAIGHT_FLUSH.getName() + " (" + this.hand + ")";
						}
						else if(color){
							// solo color es true, color
							bestValue = Ranking.FLUSH.getValor();
							this.bestHand = Ranking.FLUSH.getName() + " (" + this.hand + ")";
						}
						else if (straight){
							// solo straight es true, escalera
							bestValue = Ranking.STRAIGHT.getValor();
							this.bestHand = Ranking.STRAIGHT.getName() + " (" + this.hand + ")";
						}
					}
					value = aux.getValue().getValor();
				}
				
				break;
			case 2:
				// two pair or pair
				if(bestValue == 0){
					
					// Primera vuelta (2+1+1+1, 2+2+1, 2+1+2)
					palo = aux.getSuit().getPalo();
					bestValue = Ranking.PAIR.getValor();
					value = aux.getValue().getValor();
					this.bestHand = Ranking.PAIR.getName() + " of " + aux.getValue() + "s";
					this.bestHand += " (" + this.hand.getCarta(0) + this.hand.getCarta(1) + ")";
				}
				else{
					// Segunda, Tercera o Cuarta vuelta
					if(Ranking.PAIR.getValor() == bestValue){
						
						// El mejor mano que lleva hasta ahora es pareja (1+2+2, 2+1+2, 2+1+1)
						bestValue = Ranking.TWO_PAIR.getValor();
						this.bestHand = Ranking.TWO_PAIR.getName() + this.bestHand.substring(4, this.bestHand.length() - 6) + aux.getValue() + "s";
						String str = this.hand.toString();
						this.bestHand += " (";
						
						if(count2 == 2){
							// caso 2+2+1
							this.bestHand += str.substring(0,str.length()-2);
						} else{
							// count2 == 3
							if(this.hand.getCarta(0).getValue().getValor() > this.hand.getCarta(1).getValue().getValor()){
								// caso 1+2+2
								str = this.hand.toString();
								this.bestHand += str.substring(2,str.length());
							} else{
								// caso 2+1+2 
								str = this.hand.toString();
								this.bestHand += str.substring(0,4) + str.substring(6,str.length());
							}
						}
						this.bestHand += ")";
					} else if(Ranking.PAIR.getValor() < bestValue){
						
						// El mejor mano que lleva hasta ahora es un trio (3+2)
						bestValue = Ranking.FULL_HOUSE.getValor();
						this.bestHand = Ranking.FULL_HOUSE.getName() + this.bestHand.substring(15, this.bestHand.length()) + "s " + aux.getValue() + "s";
						this.bestHand += " (" + this.hand + ")";
					} else {
						
						// El mejor mano que lleva hasta ahora es carta mas alta
						bestValue = Ranking.PAIR.getValor();
						this.bestHand = Ranking.PAIR.getName() + " of " + aux.getValue() + "s";
						this.bestHand += " (" + this.hand.getCarta(count2-1) + this.hand.getCarta(count2) + ")";
					}
				}
				break;
			case 3:
				color = false;		// no puede ser color
				
				// full house or three of a kind
				if(bestValue == 0){
					// Primera vuelta (3+1+1 o 3+2)
					bestValue = Ranking.THREE_OF_A_KIND.getValor();
					value = aux.getValue().getValor();
					this.bestHand = Ranking.THREE_OF_A_KIND.getName() + " " + aux.getValue();
				}
				else{
					// Segunda o Tercera vuelta
					if(bestValue == Ranking.PAIR.getValor()){
						
						// Mejor mano que lleva hasta ahora es pareja (2+3)
						bestValue = Ranking.FULL_HOUSE.getValor();
						this.bestHand = Ranking.FULL_HOUSE.getName() + " " + aux.getValue() + "s" + this.bestHand.substring(7, this.bestHand.length() - 7);
						this.hand.ordenaPorValorMenorAMayor();
						this.bestHand += " (" + this.hand + ")";
					} else{
						
						// Mejor mano que lleva hasta ahora es carta mas alta (1+3+1 o 1+1+3)
						bestValue = Ranking.THREE_OF_A_KIND.getValor();
						this.bestHand = Ranking.THREE_OF_A_KIND.getName() + " " + aux.getValue();
					}
				}
				
				break;
			case 4:
				// Si entrado en caso 4 carta del mismo valor, solo puede ser poker
				color = false;
				bestValue = Ranking.FOUR_OF_A_KIND.getValor();
				this.bestHand = Ranking.FOUR_OF_A_KIND.getName()+ " " + aux.getValue();
				
				break;
			default:
				break;
			}
		}
		
		// cuatro cartas diferentes y gutshot < 2, draw Straight Gutshot y mejor mano es peor que escalera
		if(count == 4 && gutshot < 2 && bestValue < Ranking.STRAIGHT.getValor()){
			draws.add("Straight Gutshot");
		}
		
		// color es true y mejor mano es peor que color
		if(color && bestValue < Ranking.FLUSH.getValor()){
			draws.add("Flush");
		}
		
	}
	
	/**
	 * Metodo que devuelve el mejor mano
	 * @return El String del mejor mano
	 */
	public String getBestHand(){
		return this.bestHand;
	}
	
	/**
	 * Metodo que devuelve los draws
	 * @return El vector de String de los draws
	 */
	public Vector<String> getDraws(){
		
		return this.draws;
	}
}
