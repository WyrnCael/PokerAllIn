package Cartas;

public class Carta {

	private Point valor;
	private Palo color;
	
	
	Carta(Point v, Palo c){
		this.valor=v;
		this.color=c;
	}
	
	public Point getValor() {
		return this.valor;
	}

	public Palo getColor() {
		return color;
	}

}
