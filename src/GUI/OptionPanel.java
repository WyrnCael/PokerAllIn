package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Cards.Card;
import Cards.Hand;
import GUI.Helpers.Controller;

public class OptionPanel extends JPanel{

	private JButton botonApartado1;
	
	public OptionPanel(){
		create();
	}
	
	private void create(){
		botonApartado1 = new JButton("Apartado 1 (Mejor jugada de 5 cartas)");
		botonApartado1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/* PRUEBA!!!!! */
				Hand hand = new Hand();
				Card prueba = new Card("9", "c");
				hand.add(prueba);
				prueba = new Card("A", "h");
				hand.add(prueba);
				prueba = new Card("J", "h");
				hand.add(prueba);
				prueba = new Card("3", "d");
				hand.add(prueba);
				prueba = new Card("9", "s");
				hand.add(prueba);
				
				Board panel = new Board(hand);
				Controller.getInstance().setMainPanel(panel);
			}
		});
		
		this.add(botonApartado1);
		
		
	}
}
