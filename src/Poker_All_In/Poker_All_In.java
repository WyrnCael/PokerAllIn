package Poker_All_In;

import java.io.File;

import Cards.Hand;
import Game.EquityCalculator;
import Game.PokerGame;

public class Poker_All_In {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EquityCalculator equity = new EquityCalculator();
		equity.addPlayer("P1", new Hand("8h8d"));
		equity.addPlayer("P2", new Hand("AsAd"));
		equity.addPlayer("P3", new Hand("QhQd"));
		equity.addPlayer("P4", new Hand("AcKc"));
		equity.addPlayer("P5", new Hand("KsQc"));
		equity.addPlayer("P6", new Hand("6d7s"));
		equity.caclulateEquity();
	}

}
