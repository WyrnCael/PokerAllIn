package Poker_All_In;

import java.util.Scanner;

import GUI.TableGUI;
import Range.Range;

public class Poker_All_In {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		TableGUI tabla = new TableGUI();
		new Range(tabla, in);
	}

}
