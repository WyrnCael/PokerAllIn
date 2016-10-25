package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Cards.Hand;
import Players.Player;

public class OmahaGame extends Game {

	// Campos de la clase
	private List<BestHand> bestHandList;
	private String gameInfo;
	private Hand hand;
	private Hand sharedHand;
	private Player player;
	private Map<String,Integer> mapDraws;

	/**
	 * Constructor
	 */
	public OmahaGame() {
		this.bestHandList = new ArrayList<BestHand>();
		this.mapDraws = new TreeMap<String, Integer>();
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
		String playerCards = gameInfo.substring(0, 8);
		String CommonCards = gameInfo.substring(11, gameInfo.length());
		this.hand = new Hand(playerCards);
		this.sharedHand = new Hand(CommonCards);
		this.player = new Player("", this.hand, new Hand(playerCards
				+ CommonCards));

		int holeCardsSize = playerCards.length();
		int CommunCardsSize = CommonCards.length();

		// coger dos carta de la mano
		for (int i = 0; i < holeCardsSize - 2; i += 2) {
			for (int j = i + 2; j < holeCardsSize; j += 2) {
				// coger tres cartas comunes
				for (int p = 0; p < CommunCardsSize - 2; p += 2) {
					for (int q = p + 2; q < CommunCardsSize; q += 2) {
						for (int s = q + 2; s < CommunCardsSize; s += 2) {
							String HoleCardsSelected = playerCards.substring(i,
									i + 2) + playerCards.substring(j, j + 2);
							String CommunityCardsSelected = CommonCards
									.substring(p, p + 2)
									+ CommonCards.substring(q, q + 2)
									+ CommonCards.substring(s, s + 2);
							Hand hand = new Hand(HoleCardsSelected
									+ CommunityCardsSelected);
							BestHand bestHand = new BestHand(hand);
							List<String> list = bestHand.getDraws();
							for(int w= 0; w < list.size(); w++){
								if(!mapDraws.containsKey(list.get(w))){
									this.mapDraws.put(list.get(w), 1);
								}
							}
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
		String str = "";
		str = this.gameInfo + System.getProperty("line.separator") + this.bestHandList.get(0).toString();
		List<String> list = this.bestHandList.get(0).getDraws();
		if(mapDraws.containsKey(this.bestHandList.get(0).getRank().getName())){
			mapDraws.remove(this.bestHandList.get(0).getRank().getName());
		}
		for(int i = 0; i < list.size(); i++){
			if(mapDraws.containsKey(list.get(i))){
				mapDraws.remove(list.get(i));
			}
		}
		for(Map.Entry<String, Integer> entry : mapDraws.entrySet()){
			str += " - Draw: " + entry.getKey() + System.getProperty("line.separator");
		}
		return  str;
	}

	@Override
	public void clear() {
		this.bestHandList.clear();
		this.mapDraws.clear();
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
