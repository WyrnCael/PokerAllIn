package Game;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import Cards.Card;
import Cards.DeckCards;
import Cards.Hand;
import Players.HoldEmPlayer;
import Players.OmahaPlayer;
import Players.Player;

public class PokerGame {

	// Campos de la clase
	private String commonCard;
	private int numCommon;
	private DeckCards deck;
	private Map<Player, Integer> mapPlayers;
	private final double numHands = 100000;
	
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
	public Player addPlayer(Integer num, Hand hand) {
		Player player = new HoldEmPlayer("P" + num, hand);
		if (!mapPlayers.containsKey(player)){
			mapPlayers.put(player, 0);
			deck.removeAllCards(hand.getCardsList());
			return player;
		}
		return null;
	}
	/**
	 * Metodo que anade el jugador a la partida
	 * 
	 * @param name
	 *            El parametro name define el nombre(numero) del jugador
	 * @param hand
	 *            El parametro hand define las cartas que tiene el jugador
	 */
	public Player addOmahaPlayer(Integer num, Hand hand) {
		Player player = new OmahaPlayer("P" + num, hand);
		if (!mapPlayers.containsKey(player)){
			mapPlayers.put(player, 0);
			deck.removeAllCards(hand.getCardsList());
			return player;
		}
		return null;
	}
	
	/**
	 * Anadir un jugador aleatorio, solo si no esta se añade
	 * @return El jugador añadido o null si no se puede añadir
	 */
	public Player addPlayer(Integer num) {
		String cards = "";
		for(int i = 0; i < 2; i++) {
			cards += deck.getRandomCard().toString();
		}
		Hand hand = new Hand(cards);
		Player player = new HoldEmPlayer("P" + num, hand);
		if (!mapPlayers.containsKey(player)){
			mapPlayers.put(player, 0);
			deck.removeAllCards(hand.getCardsList());
			return player;
		}
		return null;
	}
	
	/**
	 * Anadir un jugador aleatorio de omaha
	 */
	public Player addOmahaPlayer(Integer num) {
		String cards = "";
		for(int i = 0; i < 4; i++) {
			cards += deck.getRandomCard().toString();
		}
		Hand hand = new Hand(cards);
		Player player = new OmahaPlayer("P" + num, hand);
		if (!mapPlayers.containsKey(player)){
			mapPlayers.put(player,0);
			deck.removeAllCards(hand.getCardsList());
			return player;
		}
		return null;
	}
	
	/**
	 * Anadir varios jugadores aleatorios
	 * @param numPlayers El numero de jugadores que anade
	 */
	public void addPlayers(int numPlayers) {
		for(int i = 0; i < numPlayers; i++) {
			addPlayer(i + 1);
		}
	}
	
	/**
	 * Eliminar un jugador de la partida
	 * @return Si ha borrado
	 * @param name El nombre del jugador
	 */
	public boolean foldPlayer(Integer num, boolean omaha) {
		Player player;
		if (omaha){
			player = new OmahaPlayer("P" + num,new Hand());
		}
		else {
			player = new HoldEmPlayer("P" + num,new Hand());
		}
		if(mapPlayers.remove(player) != null){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Repartir una carta aleatoria
	 */
	public String addCommonCard(){
		String carta = deck.getCardandRemove().toString();
		commonCard += carta;
		numCommon++;
		return carta;
	}
	
	public String getCommonCards(){
		return commonCard;
	}
	
	public int getNumCommon(){
		return numCommon;
	}
	
	/**
	 * Repartir una carta especifica
	 */
	public void addCommonCard(Card card){
		commonCard += card.toString();
		deck.removeCard(card);
		numCommon++;
	}
	
	public void addCommonCards(Hand hand) {
		int i = 0;
		Card card = hand.getCard(i);
		while (card != null){
			commonCard += card.toString();
			deck.removeCard(card);
			numCommon++;
			i++;
			card = hand.getCard(i);
		}
		
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
	public String[] processGame() {
		if (!mapPlayers.isEmpty()){
			EquityCalculator equity = new EquityCalculator(0, mapPlayers);
			equity.CalculateEquity(deck.getDeck(), numHands, commonCard , numCommon);
		}
		return obtainInfo();
	}
	
	/**
	 * El metodo que progresa la partida ordenando los jugadores segun el valor
	 * de su mejor mano que puede formar
	 */
	public String[] processGameOmaha() {
		if (!mapPlayers.isEmpty()){
			EquityCalculator equity = new EquityCalculator(1, mapPlayers);
			equity.CalculateEquityOmaha(deck.getDeck(), numHands, commonCard , numCommon);
		}
		return obtainInfo();
	}
	
	public String toString() {
		String str = "";
		try{
			for(Entry<Player, Integer> entry : mapPlayers.entrySet()) {		
			str += ((Player) entry.getKey()).getName() + " carta de mano " + 
					((Player) entry.getKey()).getHand() + " con probabilidad de " + 
					((Player) entry.getKey()).getWonGames() * 100 / numHands + "%" +
					System.getProperty("line.separator");
			
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
			/*
			for(Entry<Player, Integer> entry : mapPlayers.entrySet()) {		
				str += ((Player) entry.getKey()).getName() + " carta de mano " + 
						((Player) entry.getKey()).getHand() + " con probabilidad de " + 
						((Player) entry.getKey()).getWonGames() * 100 / numHands + "%" +
						System.getProperty("line.separator");
				}
			*/
		}
		return str;
	}
	

	public String[] obtainInfo() {
		int i = 0;
		String[] str = new String[mapPlayers.size()];
		try{
			for(Entry<Player, Integer> entry : mapPlayers.entrySet()) {		
			str[i] = ((Player) entry.getKey()).getName() + " " + 
					((Player) (entry.getKey())).getWonGames() * 100 / numHands + "%"; 
			i++;
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
			/*
			for(Entry<Player, Integer> entry : mapPlayers.entrySet()) {		
				str += ((Player) entry.getKey()).getName() + " carta de mano " + 
						((Player) entry.getKey()).getHand() + " con probabilidad de " + 
						((Player) entry.getKey()).getWonGames() * 100 / numHands + "%" +
						System.getProperty("line.separator");
				}
			*/
		}
		return str;
	}
	

	public void pruebasHoldEm(){
		addPlayer(1, new Hand("8d8h"));
		addPlayer(2, new Hand("AdAc"));
		addPlayer(3, new Hand("QhQd"));
		addPlayer(4, new Hand("AsKs"));
		addPlayer(5, new Hand("KcQs"));
		addPlayer(6, new Hand("6d7c"));
		
		addCommonCard(new Card("Q", "c"));
		addCommonCard(new Card("6", "s"));
		addCommonCard(new Card("8", "c"));	
		//addCommonCard(new Card("K", "d"));
		
	}
	
	/**
	 * Funcion con los casos de prueba de OMAHA
	 */
	public void pruebasOmaha(){
		addOmahaPlayer(1, new Hand("AdTdKsAs"));
		addOmahaPlayer(2, new Hand("Ts6s9hTc"));
		addOmahaPlayer(3, new Hand("JdAc5dTh"));
		addOmahaPlayer(4, new Hand("KdQd7c6c"));
		addOmahaPlayer(5, new Hand("9d8dQhAh"));
		addOmahaPlayer(6, new Hand("KhQc2h4h"));
		addCommonCard(new Card("4", "c"));
		addCommonCard(new Card("6", "h"));
		addCommonCard(new Card("Q", "s"));
	}

	public void reset() {
		commonCard = "";
		numCommon = 0;
		deck = new DeckCards();
		mapPlayers = new TreeMap<Player, Integer>();
	}

	
	
}
