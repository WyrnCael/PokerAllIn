package Game;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import Cards.Card;
import Cards.DeckCards;
import Cards.Hand;
import Players.OmahaPlayer;
import Players.Player;

public class PokerGame {

	// Campos de la clase
	private String commonCard;
	private int numCommon;
	private DeckCards deck;
	private Map<Object, Integer> mapPlayers;
	private double numGame = 100000;
	
	/**
	 * Constructor
	 */
	public PokerGame() {
		commonCard = "";
		numCommon = 0;
		deck = new DeckCards();
		mapPlayers = new TreeMap<Object, Integer>();
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
	 * Metodo que anade el jugador a la partida
	 * 
	 * @param name
	 *            El parametro name define el nombre(numero) del jugador
	 * @param hand
	 *            El parametro hand define las cartas que tiene el jugador
	 */
	public void addOmahaPlayer(String name, Hand hand) {
		mapPlayers.put(new OmahaPlayer(name, hand), 0);
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
	 * Anadir un jugador aleatorio
	 */
	public void addOmahaPlayer() {
		String cards = "";
		for(int i = 0; i < 2; i++) {
			cards += deck.getRandomCard().toString();
		}
		Hand hand = new Hand(cards);
		mapPlayers.put(new OmahaPlayer("P" + mapPlayers.size()+1, hand), 0);
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
		
		EquityCalculator equity = new EquityCalculator(0, mapPlayers);
		equity.CalculateEquity(deck.getDeck(), numGame, commonCard , numCommon);
	}
	
	/**
	 * El metodo que progresa la partida ordenando los jugadores segun el valor
	 * de su mejor mano que puede formar
	 */
	public void processGameOmaha() {
		
		EquityCalculator equity = new EquityCalculator(1, mapPlayers);
		equity.CalculateEquityOmaha(deck.getDeck(), numGame, commonCard , numCommon);
	}
	

	public String toString() {
		String str = "";
		try{
			for(Entry<Object, Integer> entry : mapPlayers.entrySet()) {		
			str += ((Player) entry.getKey()).getName() + " carta de mano " + 
					((Player) entry.getKey()).getHand() + " con probabilidad de " + 
					((Player) entry.getKey()).getWonGames() * 100 / numGame + "%" +
					System.getProperty("line.separator");
			}
		} catch(Exception e){
			for(Entry<Object, Integer> entry : mapPlayers.entrySet()) {		
				str += ((OmahaPlayer) entry.getKey()).getName() + " carta de mano " + 
						((OmahaPlayer) entry.getKey()).getHand() + " con probabilidad de " + 
						((OmahaPlayer) entry.getKey()).getWonGames() * 100 / numGame + "%" +
						System.getProperty("line.separator");
				}
		}
		return str;
	}

	public void pruebasHoldEm(){
		addPlayer("P1", new Hand("8d8h"));
		addPlayer("P2", new Hand("AdAc"));
		addPlayer("P3", new Hand("QhQd"));
		addPlayer("P4", new Hand("AsKs"));
		addPlayer("P5", new Hand("KcQs"));
		addPlayer("P6", new Hand("6d7c"));
		
		addCommonCard(new Card("Q", "c"));
		addCommonCard(new Card("6", "s"));
		addCommonCard(new Card("8", "c"));	
		//addCommonCard(new Card("K", "d"));
		
	}
	
	/**
	 * Funcion con los casos de prueba de OMAHA
	 */
	public void pruebasOmaha(){
		addOmahaPlayer("P1", new Hand("AdTdKsAs"));
		addOmahaPlayer("P2", new Hand("Ts6s9hTc"));
		addOmahaPlayer("P3", new Hand("JdAc5dTh"));
		addOmahaPlayer("P4", new Hand("KdQd7c6c"));
		addOmahaPlayer("P5", new Hand("9d8dQhAh"));
		addOmahaPlayer("P6", new Hand("KhQc2h4h"));
		addCommonCard(new Card("4", "c"));
		addCommonCard(new Card("6", "h"));
		addCommonCard(new Card("Q", "s"));
	}
	
}
