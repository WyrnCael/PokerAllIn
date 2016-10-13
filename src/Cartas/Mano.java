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
	
	public void ordenaPorValorMenorAMayor(){
		Collections.sort(cartas, new Comparator<Carta>() {
	       	@Override
			public int compare(Carta arg0, Carta arg1) {
				// TODO Auto-generated method stub
				return arg0.getValor().getValor() - arg1.getValor().getValor();
			}           
	    });
	}
	
	public void ordenaPorValorMayorAMenor(){
		Collections.sort(cartas, new Comparator<Carta>() {
	       	@Override
			public int compare(Carta arg0, Carta arg1) {
				// TODO Auto-generated method stub
				return arg1.getValor().getValor() - arg0.getValor().getValor();
			}
	    });
	}
	
	public void parseaMano(String entrada){
		for(int i = 0; i < entrada.length(); i=i+2){
			Carta carta = new Carta(Point.parsea(entrada.substring(i, i+1)), Palo.parsea(entrada.substring(i+1, i+2)));
			this.addCartas(carta);
		}
	}
	
	public String toString(){
		String r = "";
		for(int i=0 ; i < cartas.size(); i++){
			r += " " + cartas.get(i).toString();
		}
		
		return r;
	}
	
	
}
