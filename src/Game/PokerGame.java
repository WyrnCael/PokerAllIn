package Game;

import java.util.Map;
import java.util.TreeMap;

import Cards.Card;
import Cards.DeckCards;
import Cards.Hand;
import Players.Player;

public class PokerGame {

	// Campos de la clase
	private String commonCard;
	private int numCommon;
	private DeckCards deck;
	private Map<Player, Integer> mapPlayers;
	private double numGame = 1000000;
	
	/**
	 * Constructor
	 */
	public PokerGame() {
		commonCard = "";
		numCommon = 0;
		deck = new DeckCards();
		mapPlayers = new TreeMap<Player, Integer>();
	}

	/**
	 * Metodo que anade el jugador a la partida
	 * 
	 * @param name
	 *            El parametro name define el nombre(numero) del jugador
	 * @param hand
	 *            El parametro hand define las cartas que tiene el jugador
	 */
	public void addPlayer(String name, Hand hand) {
		mapPlayers.put(new Player(name, hand), 0);
		deck.removeAllCards(hand.getCardsList());
	}
	
	/**
	 * Anadir un jugador aleatorio
	 */
	public void addPlayer() {
		String cards = "";
		for(int i = 0; i < 2; i++) {
			cards += deck.getRandomCard().toString();
		}
		Hand hand = new Hand(cards);
		mapPlayers.put(new Player("P" + mapPlayers.size()+1, hand), 0);
	}
	
	/**
	 * Anadir varios jugadores aleatorios
	 * @param numPlayers El numero de jugadores que anade
	 */
	public void addPlayers(int numPlayers) {
		for(int i = 0; i < numPlayers; i++) {
			addPlayer();
		}
	}
	
	/**
	 * Eliminar un jugador de la partida
	 * @param name El nombre del jugador
	 */
	public void foldPlayer(String name) {
		mapPlayers.remove(name);
	}
	
	/**
	 * Repartir una carta aleatoria
	 */
	public void addCommonCard(){
		commonCard += deck.getRandomCard().toString();
		numCommon++;
	}
	
	
	/**
	 * Repartir una carta especifica
	 */
	public void addCommonCard(Card card){
		commonCard += card.toString();
		deck.removeCard(card);
		numCommon++;
	}
	
	/**
	 * Flop aleatorio
	 */
	public void flop(){
		for (int i =0; i < 3; i++){
			addCommonCard();
		}
	}

	/**
	 * El metodo que progresa la partida ordenando los jugadores segun el valor
	 * de su mejor mano que puede formar
	 */
	public void processGame() {
		
		EquityCalculator equity = new EquityCalculator(mapPlayers);
		equity.CalculateEquity(deck.getDeck(), numGame, commonCard , numCommon);
	}

	public String toString() {
		String str = "";
		for(Map.Entry<Player, Integer> entry : mapPlayers.entrySet()) {
			str += entry.getKey().getName() + " carta de mano " + 
					entry.getKey().getHand() + " con probabilidad de " + 
					entry.getKey().getWonGames() * 100 / numGame + "%" +
					System.getProperty("line.separator");
		}
		return str;
	}

}
