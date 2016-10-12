package Cartas;

import java.util.*;

public class Mano {

	
	private ArrayList<Carta> cartas; 
	
	public Mano(){
		cartas = new ArrayList<Carta>();
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}
	
	public void addCartas(Carta c){
		cartas.add(c);
	}
	
	public void deleteCartas(Carta c){
		cartas.remove(c);
	}
	
	public void ordenaPorValorMayorAMenor(){
		Collections.sort(cartas, new Comparator<Carta>() {
	       	@Override
			public int compare(Carta arg0, Carta arg1) {
				// TODO Auto-generated method stub
				return arg0.getValor().getPoint() - arg1.getValor().getPoint();
			}           
	    });
	}
	
	public void ordenaPorValorMenorAMayor(){
		Collections.sort(cartas, new Comparator<Carta>() {
	       	@Override
			public int compare(Carta arg0, Carta arg1) {
				// TODO Auto-generated method stub
				return arg1.getValor().getPoint() - arg0.getValor().getPoint();
			}
	    });
	}
	
	
}
