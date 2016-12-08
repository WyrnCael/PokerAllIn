package Poker_All_In;

import Cards.Hand;
import Game.PokerGame;

public class Poker_All_In {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		
		PokerGame pokerGame = new PokerGame();
		pokerGame.addPlayer("P1", new Hand("8d8h"));
		pokerGame.addPlayer("P2", new Hand("AdAc"));
		pokerGame.addPlayer("P3", new Hand("QhQd"));
		pokerGame.addPlayer("P4", new Hand("AsKs"));
		pokerGame.addPlayer("P5", new Hand("KcQs"));
		pokerGame.addPlayer("P6", new Hand("6d7c"));
		pokerGame.processGame();
		System.out.println(pokerGame.toString());
		
//		EquityCalculator equity = new EquityCalculator();
//		equity.addPlayer("P1", "8d8h");
//		equity.addPlayer("P2", "AdAc");
//		equity.addPlayer("P3", "QhQd");
//		equity.addPlayer("P4", "AsKs");
//		equity.addPlayer("P5", "KcQs");
//		equity.addPlayer("P6", "6d7c");
//		equity.CalculateEquity();
		
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
		
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("Tiempo de ejecucion: " + time + "ms " + time / 1000 + "s");
	}

}
