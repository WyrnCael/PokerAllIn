package pr1;

import java.util.*;

public class Mano {

	
	private List<Carta> cartas; 
	
	public Mano(){
		cartas = new ArrayList<Carta>();
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
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
				return arg0.getValor() - arg1.getValor();
			}           
	    });
	}
	
	public void ordenaPorValorMenorAMayor(){
		Collections.sort(cartas, new Comparator<Carta>() {
	       	@Override
			public int compare(Carta arg0, Carta arg1) {
				// TODO Auto-generated method stub
				return arg1.getValor() - arg0.getValor();
			}           
	    });
	}
	
	
}
