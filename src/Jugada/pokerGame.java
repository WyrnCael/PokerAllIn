package Jugada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cartas.Hand;
import Jugador.player;

public class pokerGame {
	
	private List<player> players;
	
	public pokerGame(){
		this.players = new ArrayList<player>();
	}
	
	public void addPlayer(String name, Hand hand){
		players.add(new player(name, hand));
	}
	
	public List<player> getGameResult(){
		// Ordenamos
		Collections.sort(players);
		return players;		
	}
}
