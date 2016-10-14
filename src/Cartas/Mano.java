package Cartas;

import java.util.*;

public class Mano {

	
	private ArrayList<Carta> card; 
	
	public Mano(){
		card = new ArrayList<Carta>();
	}

	public Carta getCarta(int i){
		return this.card.get(i);
	}
	
	public List<Carta> getCartas() {
		return card;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.card = cartas;
	}
	
	public void addCartas(Carta c){
		card.add(c);
	}
	
	public void deleteCartas(Carta c){
		card.remove(c);
	}
	
	public void ordenaPorValorMenorAMayor(){
		Collections.sort(card, new Comparator<Carta>() {
	       	@Override
			public int compare(Carta arg0, Carta arg1) {
				// TODO Auto-generated method stub
				return arg0.getValor().getValor() - arg1.getValor().getValor();
			}           
	    });
	}
	
	public void ordenaPorValorMayorAMenor(){
		Collections.sort(card, new Comparator<Carta>() {
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
		String str = "";
		for(int i=0 ; i < card.size(); i++){
			str += card.get(i);
		}
		return str;
	}
	
	
}
