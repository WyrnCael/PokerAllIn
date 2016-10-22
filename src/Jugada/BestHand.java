package Jugada;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import Cartas.Card;
import Cartas.Hand;
import Cartas.Ranking;
import Cartas.Suit;
import Cartas.Value;

public class BestHand {

	// Campos de clase
	private Hand hand;
	private Map<Card, Integer> mapValue;
	private Suit suit;
	private Hand bestHand;
	private Ranking rank;
	private List<String> draws;

	private List<Card> highCards;
	private List<Card> pair;
	private List<Card> threeKind;
	private List<Card> flush;
	private List<Card> poker;
	private List<Card> finalStraight;
	private List<Card> straightFlush;
	private List<List<Card>> straight; // En el 0 estara la escalera original
										// y luego por colores segun el valor
										// asociado.

	private List<List<Card>> straightGutshot;

	private int gutshot;
	private int flushGutshot;
	private boolean isStraightGutshot;
	private boolean isStraightOpenEnded;
	private boolean isStraightFlushGutshot;
	private boolean isStraightFlushOpenEnded;

	/**
	 * Constructor
	 * 
	 * @param hand
	 *            El parametro hand define el objeto de mano
	 */
	public BestHand(Hand hand) {
		this.hand = hand;
		mapValue = hand.getCardsValueMap();
		draws = new ArrayList<String>();

		bestHand = new Hand();

		highCards = new ArrayList<Card>();
		pair = new ArrayList<Card>();
		threeKind = new ArrayList<Card>();
		flush = new ArrayList<Card>();
		poker = new ArrayList<Card>();
		finalStraight = new ArrayList<Card>();
		straightFlush = new ArrayList<Card>();

		straight = new ArrayList<List<Card>>();
		straight.add(new ArrayList<Card>());
		straight.add(new ArrayList<Card>());
		straight.add(new ArrayList<Card>());
		straight.add(new ArrayList<Card>());
		straight.add(new ArrayList<Card>());

		straightGutshot = new ArrayList<List<Card>>();
		straightGutshot.add(new ArrayList<Card>());
		straightGutshot.add(new ArrayList<Card>());
		straightGutshot.add(new ArrayList<Card>());
		straightGutshot.add(new ArrayList<Card>());
		straightGutshot.add(new ArrayList<Card>());
		gutshot = 0;
		flushGutshot = 0;
		isStraightGutshot = false;
		isStraightOpenEnded = false;
		isStraightFlushGutshot = false;
		isStraightFlushOpenEnded = false;

		insertCardsToList();
		bestHand();
		Draws();
	}

	/**
	 * El metodo para buscar el mejor mano insertando a las listas del jugada
	 * Iterar todas las cartas de la mano, coger mapValue para encontrar las
	 * parejas, trios, y poker Tambien pasar las cartas al metodo addToStraight
	 * y addToFlush para comprobar si existe escalera o color
	 */
	private void insertCardsToList() {
		List<Card> cards;
		int j;
		Card tmpCard = entriesSortedByValues(this.hand.getCardsSuitMap()).first().getKey();
		this.suit = tmpCard.getSuit();
		for (int i = 0; i < this.hand.getCardsList().size(); i++) {

			// numero de cartas de este valor
			int numCard = this.mapValue.get(this.hand.getCard(i));

			cards = new ArrayList<Card>();
			cards.add(this.hand.getCard(i));
			setHand(Ranking.HIGH_CARD, cards);

			switch (numCard) {
			case 1:
				// Comprobamos si hay posibilidad de escalera
				addToStraight(this.hand.getCard(i));
				// Comprobarmos is hay posibilidad de color
				addListFlush(this.hand.getCard(i));
				break;
			case 2:
				// Pareja!
				cards = new ArrayList<Card>();

				// Leemos todas las cartas con este valor
				for (j = 0; j < 2; j++) {
					cards.add(this.hand.getCard(i + j));
					// Comprobamos si hay posibilidad de escalera
					addToStraight(this.hand.getCard(i + j));
					// Comprobamos si hay posibilidad de color
					addListFlush(this.hand.getCard(i + j));
				}
				i += j - 1;

				setHand(Ranking.PAIR, cards);
				break;
			case 3:
				// Trio!
				cards = new ArrayList<Card>();

				// Leemos todas las cartas con este valor.
				for (j = 0; j < 3; j++) {
					cards.add(this.hand.getCard(i + j));
					// Comprobamos si hay posibilidad de escalera
					addToStraight(this.hand.getCard(i + j));
					// Comprobamos si hay posibilidad de color
					addListFlush(this.hand.getCard(i + j));
				}
				i += j - 1;

				setHand(Ranking.THREE_OF_A_KIND, cards);
				break;
			case 4:
				// Poker!
				cards = new ArrayList<Card>();

				// Leemos todas las cartas con este valor.
				for (j = 0; j < 4; j++) {
					cards.add(this.hand.getCard(i + j));
					// Comprobamos si hay posibilidad de escalera
					addToStraight(this.hand.getCard(i + j));
					// anadimos la carta a la lista del color
					addListFlush(this.hand.getCard(i + j));
				}
				i += j - 1;

				setHand(Ranking.FOUR_OF_A_KIND, cards);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * El metodo que anade la carta a la lista del color
	 * 
	 * @param card
	 *            El parametro card define el objeto de la carta que va anadir
	 */
	@SuppressWarnings("serial")
	private void addListFlush(Card card) {
		if (this.suit == card.getSuit()) {
			setHand(Ranking.FLUSH, new ArrayList<Card>() {
				{
					add(card);
				}
			});
		}
	}

	/**
	 * Metodo que pone las cartas a la lista del jugada que pueda formar
	 * 
	 * @param jugada
	 *            El parametro jugada define el enumerado de la jugada
	 * @param cards
	 *            El parametro cards definen las carta que forma esta jugada
	 */
	private void setHand(Ranking jugada, List<Card> cards) {
		switch (jugada) {
		case HIGH_CARD:
			highCards.addAll(cards);
			break;
		case PAIR:
			pair.addAll(cards);
			break;
		case THREE_OF_A_KIND:
			threeKind.addAll(cards);
			break;
		case FOUR_OF_A_KIND:
			poker.addAll(cards);
			break;
		case STRAIGHT:
			finalStraight.addAll(cards);
			break;
		case FLUSH:
			flush.addAll(cards);
			break;
		case STRAIGHT_FLUSH:
			straightFlush.addAll(cards);
			break;
		default:
			break;
		}
	}

	/**
	 * El metodo que calcula el mejor mano que pueda formar
	 */
	private void bestHand() {
		// si el tamano de la lista de la escalera del color es 5, hay escalera
		// del color
		if (straightFlush.size() >= 5) {

			bestHand.addAll(straightFlush);
			rank = Ranking.STRAIGHT_FLUSH;

		}
		// si el tamano de la lista del poker es 4, hay poker
		else if (poker.size() >= 4) {

			// completar mano insertando una carta mas alta y distinta al valor
			// del poker
			for (int i = 0; i < highCards.size() && poker.size() != 5; i++) {
				if (this.highCards.get(i).getValue() != poker.get(0).getValue()) {
					poker.add(highCards.get(i));
				}
			}

			bestHand.addAll(poker);
			rank = Ranking.FOUR_OF_A_KIND;

		}
		// si el tamano de la lista del trios mayor que 3 y el de parejas es
		// mayor que 2, hay full
		else if (threeKind.size() >= 3 && (pair.size() >= 2 || threeKind.size() >= 6)) {

			// dos trios
			if (threeKind.size() == 6) {
				// el valor del segundo trio es mayor que el de pareja,
				// completamos la mano con dos cartas del segundo trio
				if (threeKind.get(3).getValue().getValue() > pair.get(0).getValue().getValue()) {
					bestHand.addAll(threeKind);
				}
				// completamos la mano con pareja
				else {
					bestHand.add(pair.get(0));
					bestHand.add(pair.get(1));
				}
			}
			// un trio
			else {
				bestHand.addAll(threeKind);
				if (pair.size() >= 2) {
					bestHand.add(pair.get(0));
					bestHand.add(pair.get(1));
				}
			}

			rank = Ranking.FULL_HOUSE;

		}
		// si el tamano de la lista del color es mayor o igual que 5, hay color
		else if (flush.size() >= 5) {

			bestHand.addAll(flush);
			rank = Ranking.FLUSH;

		}
		// si el tamano de la lista de la escalera es mayor o igual que 5, hay
		// escalera
		else if (finalStraight.size() >= 5) {

			bestHand.addAll(finalStraight);
			rank = Ranking.STRAIGHT;

		}
		// si el tamano de la lista del trios es mayor o igual que 3, hay trios
		else if (threeKind.size() >= 3) {

			// Completamos la mano con dos cartas mas altas y distintas al del
			// trio
			for (int i = 0; i < highCards.size() && threeKind.size() != 5; i++) {
				if (highCards.get(i).getValue() != threeKind.get(0).getValue()) {
					threeKind.add(highCards.get(i));
				}
			}

			bestHand.addAll(threeKind);
			rank = Ranking.THREE_OF_A_KIND;

		}
		// si el tamano de la lista de las parejas es mayor o igual que 4, hay
		// doble pareja
		else if (pair.size() >= 4) {

			// cojer dos parejas
			for (int i = 0; i < 4; i++) {
				bestHand.add(pair.get(i));
			}

			// si el valor de la carta alta es mayor que la de tercer pareja,
			// completamos la mano con carta alta
			for (int i = 0; i < highCards.size() && pair.size() != 5; i++) {
				if (this.highCards.get(i).getValue() != pair.get(0).getValue()
						&& highCards.get(i).getValue() != pair.get(2).getValue()) {
					if (pair.size() >= 6
							&& highCards.get(i).getValue().getValue() < pair.get(4).getValue().getValue()) {
						bestHand.add(pair.get(4));
					} else {
						bestHand.add(highCards.get(i));
					}
				}
			}

			this.rank = Ranking.TWO_PAIR;

		}
		// si el tamano de la lista de parejas es igual a dos, hay pareja
		else if (pair.size() >= 2) {

			// Completamos la mano con cartas altas y distintas al de pareja
			for (int i = 0; i < highCards.size() && pair.size() != 5; i++) {
				if (highCards.get(i).getValue() != pair.get(0).getValue()) {
					pair.add(highCards.get(i));
				}
			}

			bestHand.addAll(pair);
			this.rank = Ranking.PAIR;

		}
		// tenemos carta alta
		else {
			// Completamos la mano cogiendo los cinco primeros
			for (int i = 0; i < 5; i++) {
				bestHand.add(highCards.get(i));
			}
			rank = Ranking.HIGH_CARD;
		}
	}

	/**
	 * El metodo que calcula los draws que pueda formar
	 */
	private void Draws() {
		if (this.rank != Ranking.STRAIGHT_FLUSH) {

			if (this.isStraightFlushOpenEnded) {
				draws.add(Ranking.STRAIGHT_FLUSH.getName() + " OpenEnded");
			}
			if (this.isStraightFlushGutshot && flushGutshot == 1) {
				draws.add(Ranking.STRAIGHT_FLUSH.getName() + " Gutshot");
			}

			if (this.rank.getValue() < Ranking.FLUSH.getValue() && flush.size() == 4) {

				draws.add(Ranking.FLUSH.getName());
			}

			if (!this.isStraightFlushOpenEnded && !this.isStraightFlushGutshot
					&& this.rank.getValue() < Ranking.STRAIGHT.getValue()) {
				if (this.isStraightOpenEnded) {
					draws.add(Ranking.STRAIGHT.getName() + " OpenEnded");
				}
				if (this.isStraightGutshot && gutshot == 1) {
					draws.add(Ranking.STRAIGHT.getName() + " Gutshot");
				}
			}

		}
	}

	/**
	 * El metodo que anade la carta a la lista de escaleras
	 * 
	 * @param card
	 *            El parametro card define la carta que va anadir
	 */
	private void addToStraight(Card card) {
		// Comprobamos en la escalera principal que la diferencia es 1 con la
		// ultima insertada.
		// Si no existia
		List<Card> tmpStraight = straight.get(0);

		if (this.straightGutshot.get(0).size() == 0) {
			this.straightGutshot.get(0).add(card);
		}
		if (tmpStraight.size() == 0) {
			tmpStraight.add(card);
		} else if (tmpStraight.get(tmpStraight.size() - 1).getValue().getValue()
				- card.getValue().getValue() == 1) {
			tmpStraight.add(card);
			this.straightGutshot.get(0).add(card);
			if (tmpStraight.size() == 4) {
				this.isStraightOpenEnded = true;
			}
			if (card.getValue().getValue() == 2) {
				Card as = checkAce();
				if (as != null) {
					tmpStraight.add(as);
					this.straightGutshot.get(0).add(as);
				}

			}
		} else if (tmpStraight.get(tmpStraight.size() - 1).getValue().getValue()
				- card.getValue().getValue() > 1) {
			if (tmpStraight.get(tmpStraight.size() - 1).getValue().getValue()
					- card.getValue().getValue() > 2) {
				if (!isStraightGutshot) {
					this.straightGutshot.get(0).clear();
					this.gutshot = 0;
				}

			} else {
				if (gutshot == 0) { // no habia gutshot antes
					gutshot++;
					this.straightGutshot.get(0).add(card);
				} else {
					straightGutshot.get(0).clear();
					gutshot = 0;
				}
			}
			straight.get(0).clear();
			straight.get(0).add(card);
		}

		if (straightGutshot.get(0).size() == 4) {
			this.isStraightGutshot = true;
		}

		// La insertamos en su color si es la siguiente a la ultima, sino
		// empezamos de 0 esa escalera
		int numSuit = card.getSuit().getNum();
		List<Card> tmpStraightFlush = straight.get(numSuit);

		if (this.straightGutshot.get(numSuit).size() == 0) {
			this.straightGutshot.get(numSuit).add(card);
		}

		if (tmpStraightFlush.size() == 0) {
			tmpStraightFlush.add(card);
		}
		// Caso especial, que sea 2, por lo que un As del mismo color puede
		// valer:
		else if (tmpStraightFlush.get(tmpStraightFlush.size() - 1).getValue().getValue()
				- card.getValue().getValue() == 1) {
			tmpStraightFlush.add(card);
			this.straightGutshot.get(numSuit).add(card);
			if (tmpStraightFlush.size() == 4) {
				this.isStraightFlushOpenEnded = true;
			}
			if (card.getValue().getValue() == 2) {
				Card as = checkAceSuit(card.getSuit());
				if (as != null) {
					tmpStraightFlush.add(as);
					this.straightGutshot.get(numSuit).add(as);
				}
			}
		} else if (tmpStraightFlush.get(tmpStraightFlush.size() - 1).getValue().getValue()
				- card.getValue().getValue() > 1) {
			if (tmpStraightFlush.get(tmpStraightFlush.size() - 1).getValue().getValue()
					- card.getValue().getValue() > 2) {
				if (!isStraightFlushGutshot) {
					this.straightGutshot.get(numSuit).clear();
					this.flushGutshot = 0;
				}

			} else {
				if (flushGutshot == 0) { // no habia gutshot antes
					flushGutshot++;
					this.straightGutshot.get(numSuit).add(card);
				} else {
					straightGutshot.get(numSuit).clear();
					flushGutshot = 0;
				}
			}
			tmpStraightFlush.clear();
			tmpStraightFlush.add(card);
		}

		if (straightGutshot.get(numSuit).size() == 4) {
			this.isStraightFlushGutshot = true;
		}

		checkStraight();
	}

	/**
	 * El metodo que comprueba si existe escalera
	 */
	private void checkStraight() {
		if (straight.get(0).size() >= 5) {
			// Comprobamos la escalera de color
			for (int i = 1; i <= 4; i++) {
				if (straight.get(i).size() >= 5) {
					// Si no habia escalera de color o la actual es superior a
					// la guardada
					if (straightFlush.size() < 5 || straightFlush.get(0).getValue().getValue() < straight.get(i).get(0)
							.getValue().getValue()) {
						ArrayList<Card> escaleraColorAux = new ArrayList<Card>();
						for (int j = 0; j < 5; j++) {
							escaleraColorAux.add(straight.get(i).get(j));
						}
						straightFlush = escaleraColorAux;
					}
				}
			}
			// Asignamos a la escalera final si es superior a la que habia y es
			// escalera
			if (straight.get(0).size() >= 5 && (finalStraight.size() < 5
					|| finalStraight.get(0).getValue().getValue() < straight.get(0).get(0).getValue().getValue())) {
				finalStraight = straight.get(0);
			}
		}
	}

	/**
	 * El metodo que comprueba si existe as en la mano
	 * 
	 * @return si existe as devuelve la carta, sino devuelve null
	 */
	private Card checkAce() {
		if (this.hand.getCard(0).getValue() == Value.Ace)
			return this.hand.getCard(0);
		else
			return null;
	}

	/**
	 * El metodo que comprueba si existe as de un palo
	 * 
	 * @param suit
	 *            El parametro suit define el palo del que quiere buscar
	 * @return si existe as de ese palo devuelve el objeto, sino devuelve null
	 */
	private Card checkAceSuit(Suit suit) {
		int i = 0;
		for (i = 0; i < this.hand.getCardsList().size() && i < 4; i++) {
			if (this.hand.getCard(i).getValue() == Value.Ace && suit == this.hand.getCard(i).getSuit())
				return this.hand.getCard(i);
		}
		return null;
	}

	/**
	 * El metodo que devuelve el mejor mano
	 * 
	 * @return bestHand es el objeto de clase Hand
	 */
	public Hand getBestHand() {
		return this.bestHand;
	}

	/**
	 * El metodo que devuelve el rank del mejor mano
	 * 
	 * @return rank es el objeto del mejor rank
	 */
	public Ranking getRank() {
		return this.rank;
	}

	public String toString() {
		String str = " - Best hand: " + this.rank.getName();
		str += " (";
		for (int i = 0; i < this.bestHand.getCardsList().size() && i < 5; i++) {
			str += this.bestHand.getCard(i);
		}
		str += ")\n";
		if (this.hand.size() < 7) {
			for (int i = 0; i < draws.size(); i++) {
				str += " - Draw: " + draws.get(i) + "\n";
			}
		}
		return str;
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
