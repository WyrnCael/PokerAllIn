package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cards.Hand;
import Players.Player;

public class OmahaGame extends Game {

	// Campos de la clase
	private List<BestHand> bestHandList;
	private String gameInfo;
	private Hand hand;
	private Hand sharedHand;
	private Player player;

	/**
	 * Constructor
	 */
	public OmahaGame() {
		this.bestHandList = new ArrayList<BestHand>();
	}

	/**
	 * El metodo que parsea la informacion del juego e insertar los mejores
	 * manos que pueda formar a la lista
	 * 
	 * @param gameInfo
	 *            El parametro gameInfo define la informacion de la partida
	 */
	public void parseGame(String gameInfo) {
		this.gameInfo = gameInfo;
		String HoleCards = gameInfo.substring(0, 8);
		String CommunityCards = gameInfo.substring(11, gameInfo.length());
		this.hand = new Hand(HoleCards);
		this.sharedHand = new Hand(CommunityCards);
		this.player = new Player("", this.hand, new Hand(HoleCards + CommunityCards));

		int holeCardsSize = HoleCards.length();
		int CommunCardsSize = CommunityCards.length();

		// coger dos carta de la mano
		for (int i = 0; i < holeCardsSize - 2; i += 2) {
			for (int j = i + 2; j < holeCardsSize; j += 2) {
				// coger tres cartas comunes
				for (int p = 0; p < CommunCardsSize - 2; p += 2) {
					for (int q = p + 2; q < CommunCardsSize; q += 2) {
						for (int s = q + 2; s < CommunCardsSize; s += 2) {
							String HoleCardsSelected = HoleCards.substring(i, i + 2) + HoleCards.substring(j, j + 2);
							String CommunityCardsSelected = CommunityCards.substring(p, p + 2)
									+ CommunityCards.substring(q, q + 2) + CommunityCards.substring(s, s + 2);
							// System.out.print(HoleCardsSelected);
							// System.out.print(" + ");
							// System.out.println(CommunityCardsSelected);
							Hand hand = new Hand(HoleCardsSelected + CommunityCardsSelected);
							BestHand bestHand = new BestHand(hand);
							addBestHand(bestHand);
						}
					}
				}
			}
		}
	}

	/**
	 * El metodo para anadir el mejor mano a la lista
	 * 
	 * @param bestHand
	 *            El parametro bestHand define el mejor mano
	 */
	private void addBestHand(BestHand bestHand) {
		this.bestHandList.add(bestHand);
	}

	/**
	 * El metodo para ordenar todos los mejores manos
	 */
	public void processGame() {
		Collections.sort(this.bestHandList);
	}

	public String toString() {
		return this.gameInfo + System.getProperty("line.separator") + this.bestHandList.get(0).toString();
	}

	@Override
	public void clear() {
		this.bestHandList.clear();
	}

	@Override
	public Hand getSharedHand() {
		// TODO Auto-generated method stub
		return this.sharedHand;
	}

	@Override
	public Hand getHand() {
		// TODO Auto-generated method stub
		return this.hand;
	}

	@SuppressWarnings("serial")
	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return new ArrayList<Player>() {
			{
				add(player);
			}
		};
	}
}
