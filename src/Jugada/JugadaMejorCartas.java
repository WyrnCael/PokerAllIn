package Jugada;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import Cartas.BestHand;
import Cartas.Card;
import Cartas.CardOnlyValue;
import Cartas.Hand;
import Cartas.Ranking;
import Cartas.Suit;

public class JugadaMejorCartas {
	
	// Campos de clase
	private Map<Card, Integer> mapValue;	
	private Hand hand;
	private BestHand bestHand;
	
	/**
	 * Constructor
	 * @param hand El parametro hand define el objeto de mano
	 */
	public JugadaMejorCartas(Hand hand){
		this.mapValue = hand.getCardsValueMap();
		this.hand = hand;
		this.bestHand = new BestHand();
		bestHand();
	}
	
	/**
	 * Metodo para calcular el mejor mano a partir del String de cartas
	 * Utiliza la estructura de datos Treemap para facilitar el calculo
	 */
	private void bestHand(){
		
		Card cartaAnterior = null;
		List<Card> cards;
		Iterator<Card> it = mapValue.keySet().iterator();
		
		while(it.hasNext()){
		Card aux = it.next();	
		
			// Si es la primera, es carta alta.
			if(cartaAnterior == null){
				cards = new ArrayList<Card>(); 
				cards.add(aux);
				bestHand.setJugada(Ranking.HIGH_CARD, cards);					
			}
			
			switch(this.hand.getReferenceCardsMap().get(new CardOnlyValue(aux.getValue()))){
			case 1:				
				bestHand.anadeAEscalera(aux);
				cartaAnterior = aux;	
				break;
			case 2:
				// Pareja! 
				cards = new ArrayList<Card>();				
				
				// Comprobamos si hay posiblidad de escalera:
				bestHand.anadeAEscalera(aux);
				cards.add(aux);
				
				// Leemos todas las cartas con este valor.
				for(int i = 0; i < 1; i++){
					aux = it.next();
					bestHand.anadeAEscalera(aux);
					cards.add(aux);				
				}
				
				bestHand.setJugada(Ranking.PAIR, cards);
				cartaAnterior = aux;
				break;
			case 3:
				// Trio! 
				cards = new ArrayList<Card>();
				
				// Comprobamos si hay posiblidad de escalera:
				bestHand.anadeAEscalera(aux);
				cards.add(aux);
				
				// Leemos todas las cartas con este valor.
				for(int i = 0; i < 2; i++){
					aux = it.next();
					bestHand.anadeAEscalera(aux);
					cards.add(aux);					
				}
				
				bestHand.setJugada(Ranking.THREE_OF_A_KIND, cards);		
				cartaAnterior = aux;
				break;
			case 4:
				// Poker! 
				cards = new ArrayList<Card>();		
				
				// Comprobamos si hay posiblidad de escalera:
				bestHand.anadeAEscalera(aux);
				cards.add(aux);
				
				// Leemos todas las cartas con este valor.
				for(int i = 0; i < 3; i++){
					aux = it.next();
					bestHand.anadeAEscalera(aux);
					cards.add(aux);					
				}
				bestHand.setJugada(Ranking.FOUR_OF_A_KIND, cards);	
				cartaAnterior = aux;
				break;
			default:
				aux = it.next();
				break;
			}					
		}
		
		// Comprobamos color
		Card aux2 = entriesSortedByValues(this.hand.getCardsSuitMap()).first().getKey();
		// Comrpobamos que hay 5 o mas cartas del mismo color
		if(this.hand.getCardsSuitMap().get(aux2) >= 5){
			// Hay color, cogemos las 5 cartas mas altas:
			Suit color = aux2.getSuit();
			cards = new ArrayList<Card>();		
			
			// Para ello se recorren todas las cartas de ese color
			// hacia abajo, a partir de la que teniamos guardad que 
			// será la mas alta.				
			int i = aux2.getValue().getValue(), cont = 0;
			// Mientras no haya 5 cartas cogidas
			while(cont < 5){
				Card existeCarta = new Card(i, color);
				// Si la carta existe la guardamos
				if(this.mapValue.containsKey(existeCarta)){
					cards.add(existeCarta);
					cont++;
				}
				i--;
			}
			bestHand.setJugada(Ranking.FLUSH, cards);
		}		
	}
	
	/**
	 * Metodo que devuelve el mejor mano
	 * @return El String del mejor mano
	 */
	public String getBestHand(){
		return bestHand.getJugada().getName() + bestHand.getBestCards().toString();
	}
	
	/**
	 * Metodo que devuelve los draws
	 * @return El vector de String de los draws
	 *
	public Vector<String> getDraws(){
		
		return this.draws;
	}
	*/
	
	static <K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                int res = e2.getValue().compareTo(e1.getValue());
	                return res != 0 ? res : 1;
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
}
