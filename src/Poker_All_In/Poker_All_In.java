package Poker_All_In;

import java.io.File;

import Cards.Hand;
import Game.EquityCalculator;
import Game.PokerGame;
import Players.Player;

public class Poker_All_In {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EquityCalculator equity = new EquityCalculator();
		equity.addPlayer("P1", new Hand("8h8d"));
		equity.addPlayer("P2", new Hand("AcAd"));
		equity.addPlayer("P3", new Hand("QhQd"));
		equity.addPlayer("P4", new Hand("AsKs"));
		equity.addPlayer("P5", new Hand("KcQs"));
		equity.addPlayer("P6", new Hand("6d7c"));
		equity.caclulateEquity();
		
		/*PokerGame game = new PokerGame();
		game.addBoard(new Hand("7h7d4d3s3h"));
		Player player = new Player("P1");
		player.setHand(new Hand("8h8d"));
		player.setBoard(new Hand("7h7d4d3s3h"));
		game.addPlayer(player);
		player = new Player("P2");
		player.setHand(new Hand("AsAd"));
		player.setBoard(new Hand("7h7d4d3s3h"));
		game.addPlayer(player);
		player = new Player("P3");
		player.setHand(new Hand("QhQd"));
		player.setBoard(new Hand("7h7d4d3s3h"));
		game.addPlayer(player);
		player = new Player("P4");
		player.setHand(new Hand("AcKc"));
		player.setBoard(new Hand("7h7d4d3s3h"));
		game.addPlayer(player);
		player = new Player("P5");
		player.setHand(new Hand("KsQc"));
		player.setBoard(new Hand("7h7d4d3s3h"));
		game.addPlayer(player);
		player = new Player("P6");
		player.setHand(new Hand("7sAh"));
		player.setBoard(new Hand("7h7d4d3s3h"));
		game.addPlayer(player);
		
		game.processGame();
		
		System.out.println(game);*/
		
		/*EquityCalculator equity = new EquityCalculator();
		equity.addRandomPlayers(6);
		equity.caclulateEquity();*/
		
		/*EquityCalculator equity = new EquityCalculator();
		equity.addPlayer("P1", new Hand("QcKc"));		
		equity.addPlayer("P2", new Hand("AsAd"));
		equity.addPlayer("P3", new Hand("QhQd"));
		equity.addPlayer("P4", new Hand("8h8d"));;
		equity.addPlayer("P5", new Hand("5s6c"));
		equity.addPlayer("P6", new Hand("5d7h"));
		equity.addBoard(new Hand("Ah3d4dJhTc"));
		equity.caclulateEquity();*/
	}

}
