package Jugada;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import Cartas.Card;
import Cartas.Hand;
import Cartas.Ranking;
import Cartas.Suit;

public class JugadaMejor5Cartas {
	
	// Campos de clase
	private Map<Card, Integer> map;
	private Hand hand;
	private String bestHand;
	private Vector<String> draws;
	
	/**
	 * Constructor
	 * @param hand El parametro hand define el objeto de mano
	 */
	public JugadaMejor5Cartas(Hand hand){
		this.map = hand.getCardsMap();
		this.hand = hand;
		this.draws = new Vector<String>();
		bestHand();
	}
	
	/**
	 * Metodo para calcular el mejor mano a partir del String de cartas
	 * Utiliza la estructura de datos Treemap para facilitar el calculo
	 */
	private void bestHand(){
		
		int bestValue = 0;			// Valor de mejor mano
		int count = 0;				// Contador para escalera count=4 (straight gutshot) count=5 (straight)
		boolean color = true;		// Boolean para comprobar si es del mismo color
		int gutshot = 0;			// Numero de hueco del mano, gutshot <= 1 puede tener escalera
		boolean straight = true;	// Boolean para comprobar si es una escalera
		Suit colorAnterior = null;  // Contiene el coloranterior de la escalera
		boolean straightColor = true;// Escalera de color?
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
			
			/* Esto es un poco feo, pero no se me ocurre nada ahora */
			if(colorAnterior == null) colorAnterior = aux.getSuit();
			
			switch(map.get(aux)){
			case 1:
				/* Este era el principal problema, ponias straight a false en cuanto no se cumplia una vez,
				 * y asi estabas evitando comprobar que apareciese una escalera despues.
				 * 
				 * Si hay diferencia de 1, volvemos a comrpobarla
				 */
				if(!straight && value - aux.getValue().getValue() == 1){
					straight = true;
				}
				
				if(bestValue == 0){		// Primera vuelta
					bestValue = Ranking.HIGH_CARD.getValor();
					palo = aux.getSuit().getPalo();
					value = aux.getValue().getValue();
					this.bestHand = Ranking.HIGH_CARD.getName() + " " + aux.getValue() + " (" + aux + ")";					
				} else if(straight || color){
					
					
					
					if(value - aux.getValue().getValue() > 2){
						// Si la diferencia de valor de carta anterio y ahora es mayor que 2, no hay escalera ni gutshot
						straight = false;
						gutshot = 2;
					} else if (value - aux.getValue().getValue() == 2){
						// Si la diferencia es igual a 2, no hay escalera pero puede ser un gutshot
						straight = false;
						gutshot++;
					}
					/* Este else if es lo nuevo, creo que se entiende */
					else if(value - aux.getValue().getValue() == 1){
						if(colorAnterior != aux.getSuit()){
							straightColor = false;
						}
					}				
					
					// Si el palo de la carta anterio es diferente que la de ahora, no hay color
					if(!palo.equals(aux.getSuit().getPalo())){
						color = false;
					}
					
					if(count == 5){
						// caso 1+1+1+1+1
						/* Ahora comprobamos con la variable straightColor */
						if(straightColor && straight){
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
					value = aux.getValue().getValue();
				}
				
				break;
			case 2:
				count = 1;
				
				// two pair or pair
				if(bestValue == 0){
					
					// Primera vuelta (2+1+1+1, 2+2+1, 2+1+2)
					palo = aux.getSuit().getPalo();
					bestValue = Ranking.PAIR.getValor();
					value = aux.getValue().getValue();
					this.bestHand = Ranking.PAIR.getName() + " of " + aux.getValue() + "s";
					this.bestHand += " (" + this.hand.getCard(0) + this.hand.getCard(1) + ")";
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
							if(this.hand.getCard(0).getValue().getValue() > this.hand.getCard(1).getValue().getValue()){
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
						this.bestHand = Ranking.FULL_HOUSE.getName() + this.bestHand.substring(15, this.bestHand.length() - 12) + aux.getValue() + "s";
						this.bestHand += " (" + this.hand + ")";
					} else {
						
						// El mejor mano que lleva hasta ahora es carta mas alta
						bestValue = Ranking.PAIR.getValor();
						this.bestHand = Ranking.PAIR.getName() + " of " + aux.getValue() + "s";
						this.bestHand += " (" + this.hand.getCard(count2-1) + this.hand.getCard(count2) + ")";
					}
				}
				break;
			case 3:
				color = false;		// no puede ser color
				count = 1;
				
				// full house or three of a kind
				if(bestValue == 0){
					// Primera vuelta (3+1+1 o 3+2)
					bestValue = Ranking.THREE_OF_A_KIND.getValor();
					value = aux.getValue().getValue();
					this.bestHand = Ranking.THREE_OF_A_KIND.getName() + " " + aux.getValue() + "s";
					this.bestHand += " (" + this.hand + ")";
				}
				else{
					// Segunda o Tercera vuelta
					if(bestValue == Ranking.PAIR.getValor()){
						
						// Mejor mano que lleva hasta ahora es pareja (2+3)
						bestValue = Ranking.FULL_HOUSE.getValor();
						this.bestHand = Ranking.FULL_HOUSE.getName() + " " + aux.getValue() + "s" + this.bestHand.substring(7, this.bestHand.length() - 7);
						this.hand.reverse();
						this.bestHand += " (" + this.hand + ")";
					} else{
						
						// Mejor mano que lleva hasta ahora es carta mas alta (1+3+1 o 1+1+3)
						bestValue = Ranking.THREE_OF_A_KIND.getValor();
						this.bestHand = Ranking.THREE_OF_A_KIND.getName() + " " + aux.getValue();
						if(count2 == 3){	// 1+1+3
							this.hand.reverse();
							this.bestHand += " (" + this.hand + ")";				
						} else{
							this.bestHand += " (" + this.hand.getCard(1) + this.hand.getCard(2) +
									this.hand.getCard(3) + this.hand.getCard(0) + this.hand.getCard(4) + ")";
						}
					}
				}
				
				break;
			case 4:
				// Si entrado en caso 4 carta del mismo valor, solo puede ser poker
				color = false;
				count = 1;
				
				bestValue = Ranking.FOUR_OF_A_KIND.getValor();
				this.bestHand = Ranking.FOUR_OF_A_KIND.getName()+ " " + aux.getValue();
				if(count2 == 2){
					this.hand.reverse();
					this.bestHand += " (" + this.hand + ")";
				} else{
					this.bestHand += " (" + this.hand + ")";
				}
				
				break;
			default:
				break;
			}
			
			colorAnterior = aux.getSuit();
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
