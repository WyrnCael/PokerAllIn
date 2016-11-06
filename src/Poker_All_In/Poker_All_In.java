package Poker_All_In;

import GUI.TableGUI;
import Range.Range;

public class Poker_All_In {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Range range = new Range();
		range.parseRange("AA,JJ,ATs-A8s,76o,54o");
		new TableGUI(range);
	}

}
