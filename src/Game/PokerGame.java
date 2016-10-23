package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cards.Hand;
import Players.Player;

public class PokerGame extends Game{

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
		String CommunityCards = gameInfo.substring(2 + (7 * numPlayers), gameInfo.length());
		this.sharedHand = new Hand(CommunityCards);

		// Leemos los jugadores
		for (int i = 2; i < 2 + (7 * numPlayers); i = i + 7) {
			String name = gameInfo.substring(i, i + 2);
			String HoleCards = gameInfo.substring(i + 2, i + 6);
			Hand hand = new Hand(HoleCards + CommunityCards);
			this.playersCards.add(new Player(name, HoleCards));
			addPlayer(name, hand);
		}
	}

	/**
	 * Metodo que anade el jugador a la partida
	 * 
	 * @param name
	 *            El parametro name define el nombre(numero) del jugador
	 * @param hand
	 *            El parametro hand define las cartas que tiene el jugador
	 */
	private void addPlayer(String name, Hand hand) {
		players.add(new Player(name, hand));
	}

	/**
	 * El metodo que progresa la partida ordenando los jugadores segun el valor
	 * de su mejor mano que puede formar
	 */
	public void processGame() {
		// Ordenamos
		Collections.sort(players);
	}

	public String toString() {
		String str = this.gameInfo + System.getProperty("line.separator");
		for (int i = 0; i < this.players.size(); i++) {
			str += this.players.get(i).toString() + System.getProperty("line.separator");
		}
		return str;
	}

	@Override
	public void clear() {
		this.players.clear();
	}

	@Override
	public BestHand getBestHand() {
		return this.players.get(0).getBestHand();
	}

	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return playersCards;
	}

	@Override
	public Hand getSharedHand() {
		// TODO Auto-generated method stub
		return sharedHand;
	}
}
