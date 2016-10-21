package Jugada;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import Cartas.BestHand;
import Cartas.Card;
import Cartas.Hand;
import Cartas.Ranking;
import Cartas.Suit;

public class JugadaMejorCartas {

	// Campos de clase
	private Map<Card, Integer> mapValue;
	private Hand hand;
	private BestHand bestHand;
	private Suit suit1;
	private Ranking rank;

	/**
	 * Constructor
	 * 
	 * @param hand El parametro hand define el objeto de mano
	 */
	public JugadaMejorCartas(Hand hand) {
		this.mapValue = hand.getCardsValueMap();
		this.hand = hand;
		this.bestHand = new BestHand(this.hand);
		bestHand();
	}

	private void bestHand() {
		List<Card> cards;
		Card aux2 = entriesSortedByValues(this.hand.getCardsSuitMap()).first().getKey();
		this.suit1 = aux2.getSuit();
		for (int i = 0; i < this.hand.getCardsList().size(); i++) {

			int numCard = this.mapValue.get(this.hand.getCard(i));
			
			cards = new ArrayList<Card>();
			cards.add(this.hand.getCard(i));
			bestHand.setJugada(Ranking.HIGH_CARD, cards);
			
			switch (numCard) {
			case 1:
				bestHand.anadeAEscalera(this.hand.getCard(i));
				comprobarColor(this.hand.getCard(i));
				break;
			case 2:
				// Pareja!
				cards = new ArrayList<Card>();

				// Comprobamos si hay posiblidad de escalera:
				// Leemos todas las cartas con este valor.
				int j;
				for (j = 0; j < 2; j++) {
					bestHand.anadeAEscalera(this.hand.getCard(i + j));
					cards.add(this.hand.getCard(i + j));
					comprobarColor(this.hand.getCard(i + j));
				}
				i += j - 1;

				bestHand.setJugada(Ranking.PAIR, cards);
				break;
			case 3:
				// Trio!
				cards = new ArrayList<Card>();

				// Leemos todas las cartas con este valor.

				for (j = 0; j < 3; j++) {
					bestHand.anadeAEscalera(this.hand.getCard(i + j));
					cards.add(this.hand.getCard(i + j));
					comprobarColor(this.hand.getCard(i + j));
				}
				i += j - 1;

				bestHand.setJugada(Ranking.THREE_OF_A_KIND, cards);
				break;
			case 4:
				// Poker!
				cards = new ArrayList<Card>();

				// Leemos todas las cartas con este valor.
				for (j = 0; j < 4; j++) {
					bestHand.anadeAEscalera(this.hand.getCard(i + j));
					cards.add(this.hand.getCard(i + j));
					comprobarColor(this.hand.getCard(i + j));
				}
				i += j - 1;
				bestHand.setJugada(Ranking.FOUR_OF_A_KIND, cards);
				break;
			default:
				break;
			}
		}
		this.rank = this.bestHand.getJugada();
	}
	
	@SuppressWarnings("serial")
	private void comprobarColor(Card card){
		if(this.suit1 == card.getSuit()){
			this.bestHand.setJugada(Ranking.FLUSH, new ArrayList<Card>(){{ add(card); }});
		}
	}
	
	public BestHand getBestHand() {
		return this.bestHand;
	}
	
	public Ranking getRank(){
		return this.rank;
	}

	/**
	 * Metodo que devuelve los draws
	 * 
	 * @return El vector de String de los draws
	 */
	 public List<String> getDraws(){
		 
		 return this.bestHand.getDraws();
	 }
	 
	 public String toString(){
		return this.rank.getName() + this.bestHand.toString();
	 }

	static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				int res = e2.getValue().compareTo(e1.getValue());
				return res != 0 ? res : 1;
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}
}
