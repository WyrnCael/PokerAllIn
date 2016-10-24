package Game;

import java.util.List;

import Cards.Hand;
import Players.Player;

public abstract class Game {
	/**
	 * El metodo que parsea los datos de la partida
	 * @param gameInfo El parametro gameInfo define los datos de la partida
	 */
	public abstract void parseGame(String gameInfo);
	
	/**
	 * El metodo que procesa la partida
	 */
	public abstract void processGame();
	
	/**
	 * El metodo que clear la lista de la clase
	 */
	public abstract void clear();
	
	/**
	 * El metodo que devuelve el mejor mano
	 * @return devuelve el objeto de mejor mano
	 */
	public abstract BestHand getBestHand();
	
	/**
	 * El metodo que devuelve las cartas compartidas
	 * @return devuelve el objeto de las cartas compartidas
	 */
	public abstract Hand getSharedHand();
	
	/**
	 * El metodo que devuelve las cartas en la mano
	 * @return devuelve el objto de las cartas en la mano
	 */
	public abstract Hand getHand();
	
	/**
	 * El metodo que devuelve los jugadores
	 * @return devuelve el objeto de lista de los jugadores
	 */
	public abstract List<Player> getPlayers();
}
