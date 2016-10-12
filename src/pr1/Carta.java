package pr1;

public class Carta {

	

	private char valor;
	private char color;
	
	
	Carta(char v, char c){
		this.valor=v;
		this.color=c;
	}
	
	public char getValor() {
		return valor + 's';
	}


	public void setValor(char valor) {
		this.valor = valor;
	}


	public char getColor() {
		return color;
	}


	public void setColor(char color) {
		this.color = color;
	}


	
	
}
