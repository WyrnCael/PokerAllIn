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
import Players.OmahaPlayer;
import Players.Player;

public class EquityCalculator {
	private List<Player> players;
	private List<OmahaPlayer> omahaPlayers;

	public EquityCalculator(int tipo, Map<Object, Integer> mapPlayers) { // 0 poker, 1 omaha
		if(tipo == 0){
			players = new ArrayList<Player>();
			for (Entry<Object, Integer> entry : mapPlayers.entrySet()) {
				players.add((Player) entry.getKey());
			}
		}
		else{
			omahaPlayers = new ArrayList<OmahaPlayer>();
			for (Entry<Object, Integer> entry : mapPlayers.entrySet()) {
				omahaPlayers.add((OmahaPlayer) entry.getKey());
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
	public void CalculateEquity(Map<String, Card> deck, double numGame, String commonCardIni, int numCommon) {
		System.out.println("Calcular equity con " + numGame + " partida");
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
					// TODO Auto-generated method stub
					return p1.getBestHand().compareTo(p2.getBestHand());
				}
			});

			players.get(0).win();

			if (v % (numGame / 100) == 0) {
				System.out.println("Calculando..." + v * 100 / numGame + "%");
			} else if (v == numGame - 1) {
				System.out.println("Terminado...100%" + System.getProperty("line.separator"));
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
	public void CalculateEquityOmaha(Map<String, Card> deck, double numGame, String commonCardIni, int numCommon) {
		System.out.println("Calcular equity con " + numGame + " partida");
		for (int v = 0; v < numGame; v++) {
			String commonCard = commonCardIni;
			DeckCards vDeck = new DeckCards(deck);

			for (int i = 0; i < 5 - numCommon; i++) {
				commonCard += vDeck.getRandomCard().toString();
			}

			for (int i = 0; i < omahaPlayers.size(); i++) {
				OmahaPlayer p = omahaPlayers.get(i);
				p.calculateBestHand(p.getHand().toString(), commonCard);
			}

			Collections.sort(omahaPlayers, new Comparator<OmahaPlayer>() {

				@Override
				public int compare(OmahaPlayer p1, OmahaPlayer p2) {
					// TODO Auto-generated method stub
					return p1.getBestHand().compareTo(p2.getBestHand());
				}
			});

			omahaPlayers.get(0).win();

			if (v % (numGame / 100) == 0) {
				System.out.println("Calculando..." + v * 100 / numGame + "%");
			} else if (v == numGame - 1) {
				System.out.println("Terminado...100%" + System.getProperty("line.separator"));
			}
		}
	}

}
