package Jugada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cartas.Hand;
import Jugador.player;

public class pokerGame {

	// Campos de la clase
	private List<player> players;

	/**
	 * Constructor
	 */
	public pokerGame(String game) {
		this.players = new ArrayList<player>();
		parseGame(game);
		processGame();
	}

	private void parseGame(String game) {
		// Leemos el numero de jugadores
		int numPlayers = Integer.valueOf(game.substring(0, 1));

		// Leemos las cartas comunes
		String Communitycards = game.substring(2 + (7 * numPlayers), game.length());

		// Leemos los jugadores
		for (int i = 2; i < 2 + (7 * numPlayers); i = i + 7) {
			String name = game.substring(i, i + 2);
			String Holecards = game.substring(i + 2, i + 6);
			Hand hand = new Hand(Holecards + Communitycards);
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
		players.add(new player(name, hand));
	}

	/**
	 * Metodo que devuelve el resultado de la partida
	 * 
	 * @return players es una lista ordenada segun el valor del mejor mano que
	 *         tiene cada jugador
	 */
	private void processGame() {
		// Ordenamos
		Collections.sort(players);
	}
	
	public String toString(){
		String str = "";
		for(int i = 0; i < this.players.size(); i++){
			str += this.players.get(i).toString() + "\n";
		}
		return str;
	}
}
