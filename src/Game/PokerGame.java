package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cards.Hand;
import Players.Player;

public class PokerGame{

	// Campos de la clase
	private List<Player> players;
	private String gameInfo;
	private Hand sharedHand;
	private List<Player> playersCards;

	/**
	 * Constructor
	 */
	public PokerGame() {
		this.players = new ArrayList<Player>();
		this.playersCards = new ArrayList<Player>();
	}

	/**
	 * El metodo para parsear el dato de la entrada
	 * 
	 * @param gameInfo
	 *            El parametro gameInfo contiene los datos de la partida
	 */
	public void parseGame(String gameInfo) {
		this.gameInfo = gameInfo;
		// Leemos el numero de jugadores
		int numPlayers = Integer.valueOf(gameInfo.substring(0, 1));

		// Leemos las cartas comunes
		String CommonCards = gameInfo.substring(2 + (7 * numPlayers), gameInfo.length());
		this.sharedHand = new Hand(CommonCards);

		// Leemos los jugadores
		for (int i = 2; i < 2 + (7 * numPlayers); i = i + 7) {
			String name = gameInfo.substring(i, i + 2);
			String playerCards = gameInfo.substring(i + 2, i + 6);
			Hand hand = new Hand(playerCards);
			Hand allCards = new Hand(playerCards + CommonCards);
			Player player = new Player(name);
			player.setHand(hand);
			player.setBoard(allCards);
			this.playersCards.add(player);
			addPlayer(name, hand, allCards);
		}
	}

	/**
	 * Metodo que anade el jugador a la partida
	 * 
	 * @param name
	 *            El parametro name define el nombre(numero) del jugador
	 * @param hand
	 *            El parametro hand define las cartas que tiene el jugador
	 * @param allCards
	 *            El parametro allCards define todas las cartas que pueda tener
	 *            el jugador
	 */
	private void addPlayer(String name, Hand hand, Hand allCards) {
		Player player = new Player(name);
		player.setHand(hand);
		player.setBoard(allCards);;
		players.add(player);
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void addBoard(Hand board){
		this.sharedHand = board;
	}

	/**
	 * El metodo que progresa la partida ordenando los jugadores segun el valor
	 * de su mejor mano que puede formar
	 */
	public void processGame() {
		// Ordenamos
		Collections.sort(players);
	}
	
	public Player winner(){
		return players.get(0);
	}
	
	public String toString() {
		String str = this.gameInfo + System.getProperty("line.separator");
		for (int i = 0; i < this.players.size(); i++) {
			str += this.players.get(i).toString() + System.getProperty("line.separator");
		}
		return str;
	}

	
}
