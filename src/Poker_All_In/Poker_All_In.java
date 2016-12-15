package Poker_All_In;

import Cards.Card;
import Cards.Hand;
import Game.PokerGame;

public class Poker_All_In {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		
		PokerGame pokerGame = new PokerGame();
		//pokerGame.pruebasHoldEm(); //Esto es para HOLD EM
		//pokerGame.processGame(); //Esto es para HOLD EM
		pokerGame.pruebasOmaha(); //Esto es para OMAHA, recordar cambiar numManos para que tarde 20 min
		pokerGame.processGameOmaha(); //Esto es para OMAHA
		System.out.println(pokerGame.toString());
		
		//OMAHA
		/*
		pokerGame.addPlayer("P1", new Hand("AdTdKsAs"));
		pokerGame.addPlayer("P2", new Hand("Ts6s9hTc"));
		pokerGame.addPlayer("P3", new Hand("JdAc5dTh"));
		pokerGame.addPlayer("P4", new Hand("KdQd7c6c"));
		pokerGame.addPlayer("P5", new Hand("9d8dQhAh"));
		pokerGame.addPlayer("P6", new Hand("KhQc2h4h"));
		pokerGame.addCommonCard(new Card("4", "c"));
		pokerGame.addCommonCard(new Card("6", "h"));
		pokerGame.addCommonCard(new Card("Q", "s"));
		
		
		pokerGame.processGameOmaha();
		System.out.println(pokerGame.toString());
		*/
		
		//HOLD´EM
		/*
		pokerGame.addPlayer("P1", new Hand("8d8h"));
		pokerGame.addPlayer("P2", new Hand("AdAc"));
		pokerGame.addPlayer("P3", new Hand("QhQd"));
		pokerGame.addPlayer("P4", new Hand("AsKs"));
		pokerGame.addPlayer("P5", new Hand("KcQs"));
		pokerGame.addPlayer("P6", new Hand("6d7c"));
		
		pokerGame.addCommonCard(new Card("Q", "c"));
		pokerGame.addCommonCard(new Card("6", "s"));
		pokerGame.addCommonCard(new Card("8", "c"));
		//pokerGame.addCommonCard(new Card("K", "d"));
		//pokerGame.flop();
		*/
		
		
		
		
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
