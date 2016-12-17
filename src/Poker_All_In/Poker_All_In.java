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
					frame.setResizable(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
