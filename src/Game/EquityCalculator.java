package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import Cards.Card;
import Cards.DeckCards;
import Cards.Hand;
import Players.Player;

public class EquityCalculator {
	private List<Player> players;

	public EquityCalculator(Map<Player, Integer> mapPlayers) {
		players = new ArrayList<Player>();
		for (Map.Entry<Player, Integer> entry : mapPlayers.entrySet()) {
			players.add(entry.getKey());
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
			
			for (int i = 0; i < players.size(); i++) {
				Player p = players.get(i);
				String mano = p.getHand().toString();
				List<BestHand> bestHandList = new ArrayList<BestHand>();
				for (int j = 0; j < 6; j+= 2) {
					for (int k = j + 2; k < 8; k+=2){
						String manoElegida = mano.substring(j, j+2) + mano.substring(k, k+2);
						//5 sobre 3 formas, 10 formas distintas de juntar cartas de la mano y de la mesa
						for (int l = 0; l < 8; l += 2) {
							for (int q = l + 2; q < 9; q += 2) {
								for (int s = q + 2; s < 10; s += 2){
									String cartasComunes = commonCard.substring(l, l+2) + 
											commonCard.substring(q, q+2) + commonCard.substring(s, s+2);
									Hand hand = new Hand(manoElegida + cartasComunes);
									BestHand bestHand = new BestHand(hand);
									bestHandList.add(bestHand);
								}
							}
						}
					}
				}
				Collections.sort(bestHandList);
				p.setBestHand(bestHandList.get(0));
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

}
