package Cartas;

public class Carta implements Comparable{

	private Point valor;
	private Palo palo;
	
	
	public Carta(Point v, Palo c){
		this.valor=v;
		this.palo=c;
	}
	
	public Point getValor() {
		return this.valor;
	}

	public Palo getColor() {
		return palo;
	}
	
	public String toString(){
		return this.valor.getName() + this.palo.getLetra();
	}
	
	@Override
	public int compareTo(Object o){
		int n1 = valor.getValor();
		int n2 = ((Carta) o).getValor().getValor();
		if(n1 == n2)
			return 0;
		else if( n1 > n2)
			return -1;
		else
			return 1;
	}

}
