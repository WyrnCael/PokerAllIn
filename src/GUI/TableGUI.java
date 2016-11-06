package GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Cards.Value;
import Range.Range;

public class TableGUI extends JFrame { // This is the window class
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1450488406876998792L;

	public TableGUI(Range range) {
	    this.setLayout(new GridLayout(13, 13)); // This makes the frame into a 10 x 10 grid
        for (int i = 0; i < 13; i++) {
            for(int j = 0; j < 13; j++){
            	this.add(new Casilla(i, j, range));
            }
        }
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 3
		this.pack();
		this.setVisible(true);
	}
		
	public static class Casilla extends JPanel { // This is the random number grid space class
	    /**
		 * 
		 */
		private static final long serialVersionUID = -4240331162241079211L;

		public Casilla(int i, int j, Range range) {
	    	int value = range.getValue(i, j);
	    	
	    	int highValue = Math.max(i , j);
			int lowValue = Math.min(i, j);

			String cellName = Value.values()[lowValue].getName() + Value.values()[highValue].getName();
			if(i > j) cellName += "o";
			else if (j > i) cellName += "s";
			
			JTextArea area = new JTextArea(cellName); // This will contain the number
	        if (value == 1) area.setBackground(Color.GREEN);
	        else if (value == 2) area.setBackground(Color.RED);
	        else if (value == 3) area.setBackground(Color.GRAY);
	        else if (value == 4) area.setBackground(Color.YELLOW);
	        area.setEditable(false); // This prevents the user from changing the matrix
	        area.setVisible(true);
	        this.add(area); // This puts the number into the gridspace
	    }
	}
}

