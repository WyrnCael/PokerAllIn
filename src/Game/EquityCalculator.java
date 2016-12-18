package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Cards.Card;
import Cards.DeckCards;
import Cards.Hand;
import GUI.BoardGUI;
import Players.OmahaPlayer;
import Players.Player;
import Players.HoldEmPlayer;

public class EquityCalculator {
	private List<Player> players;
	private List<Player> omahaPlayers;

	public EquityCalculator(int tipo, Map<Player, Integer> mapPlayers) { // 0 poker, 1 omaha
		if(tipo == 0){
			players = new ArrayList<Player>();
			for (Entry<Player, Integer> entry : mapPlayers.entrySet()) {
				Player player = entry.getKey();
				player.resetGames();
				players.add(player);
			}
		}
		else{
			omahaPlayers = new ArrayList<Player>();
			for (Entry<Player, Integer> entry : mapPlayers.entrySet()) {
				Player player = entry.getKey();
				player.resetGames();
				omahaPlayers.add(player);
			}
		}		
	}

	/**
	 * Calcular los porcentaje del equity
	 * 
	 * @param deck
	 *            Mapa de las cartas de la baraja
	 * @param numGame
	 *            El numero de partida a simular
	 * @param numCommon
	 *            El numero de las cartas en la mesa
	 */
	public void CalculateEquity(Map<String, Card> deck, double numGame, String commonCardIni, int numCommon, BoardGUI board) {
		long start = System.currentTimeMillis();
		System.out.println("Calcular equity con " + numGame + " manos");
		for (int v = 0; v < numGame; v++) {
			String commonCard = commonCardIni;
			DeckCards vDeck = new DeckCards(deck);

			for (int i = 0; i < 5 - numCommon; i++) {
				commonCard += vDeck.getRandomCard().toString();
			}

			for (int i = 0; i < players.size(); i++) {
				Player p = players.get(i);
				Hand hand = new Hand(p.getHand().toString() + commonCard);
				BestHand bestHand = new BestHand(hand);
				p.setBestHand(bestHand);
			}

			Collections.sort(players, new Comparator<Player>() {

				@Override
				public int compare(Player p1, Player p2) {
					
					return p1.getBestHand().compareTo(p2.getBestHand());
				}
			});

			players.get(0).win();

			if (v % (numGame / 100) == 0) {
				System.out.println("Calculando..." + v * 100 / numGame + "%");
				board.setBarPercent(v * 100 / numGame);
			} else if (v == numGame - 1) {
				System.out.println("Terminado...100%" + System.getProperty("line.separator"));
			}
		}
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("Tiempo de ejecucion: " + time + "ms " + time / 1000 + "s");
	}
	
	/**
	 * Calcular los porcentaje del equity
	 * 
	 * @param deck
	 *            Mapa de las cartas de la baraja
	 * @param numGame
	 *            El numero de partida a simular
	 * @param numCommon
	 *            El numero de las cartas en la mesa
	 */
	public void CalculateEquityOmaha(Map<String, Card> deck, double numGame, String commonCardIni, int numCommon, BoardGUI board) {
		long start = System.currentTimeMillis();
		System.out.println("Calcular equity con " + numGame + " manos");
		for (int v = 0; v < numGame; v++) {
			String commonCard = commonCardIni;
			DeckCards vDeck = new DeckCards(deck);

			for (int i = 0; i < 5 - numCommon; i++) {
				commonCard += vDeck.getRandomCard().toString();
			}

			for (int i = 0; i < omahaPlayers.size(); i++) {
				Player p = omahaPlayers.get(i);
				p.calculateBestHand(p.getHand().toString(), commonCard);
			}

			Collections.sort(omahaPlayers, new Comparator<Player>() {

				@Override
				public int compare(Player p1, Player p2) {
					
					return p1.getBestHand().compareTo(p2.getBestHand());
				}
			});

			omahaPlayers.get(0).win();

			if (v % (numGame / 100) == 0) {
				System.out.println("Calculando..." + v * 100 / numGame + "%");
				board.setBarPercent(v * 100 / numGame);
			} else if (v == numGame - 1) {
				System.out.println("Terminado...100%" + System.getProperty("line.separator"));
			}
		}
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("Tiempo de ejecucion: " + time + "ms " + time / 1000 + "s");
	}

}
