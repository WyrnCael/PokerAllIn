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
				Hand hand = new Hand("9cAhJh3d9s");
				
				BoardAp1Pr1 panel = new BoardAp1Pr1(hand);
				Controller.getInstance().setMainPanel(panel);
			}
		});
		
		this.add(botonApartado1);
		
		
	}
}
