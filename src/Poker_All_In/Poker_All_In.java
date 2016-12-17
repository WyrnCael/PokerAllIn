package Poker_All_In;

import java.awt.EventQueue;

import Cards.Card;
import Cards.Hand;
import GUI.BoardGUI;
import Game.PokerGame;

public class Poker_All_In {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardGUI frame = new BoardGUI(new PokerGame());
					frame.setVisible(true);
					frame.pack();
					frame.setSize(1025, 550);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*
		// TODO Auto-generated method stub
		
		
		
		PokerGame pokerGame = new PokerGame();
		//pokerGame.pruebasHoldEm(); //Esto es para HOLD EM
		//pokerGame.processGame(); //Esto es para HOLD EM
		pokerGame.pruebasOmaha(); //Esto es para OMAHA, recordar cambiar numManos para que tarde 20 min
		pokerGame.processGameOmaha(); //Esto es para OMAHA
		System.out.println(pokerGame.toString());
		
		*/
	}

}
