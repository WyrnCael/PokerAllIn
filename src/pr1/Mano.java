package pr1;

import java.util.*;

public class Mano {

	
	private Vector<Carta> cartas; 
	
	public Mano(){
		cartas = new Vector<Carta>();
	}

	public Vector<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(Vector<Carta> cartas) {
		this.cartas = cartas;
	}
	
	public void addCartas(Carta c){
		cartas.add(c);
	}
	public void deleteCartas(Carta c){
		cartas.remove(c);
	}
	
}
