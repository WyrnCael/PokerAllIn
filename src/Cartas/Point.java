package Cartas;

public enum Point {
	ACE('A', 14),
	KING('K', 13),
	QUEEN('Q', 12),
	JACK('J', 11),
	TEN('T', 10),
	NINE('9', 9),
	EIGHT('8', 8),
	SEVEN('7', 7),
	SIX('6', 6),
	FIVE('5', 5),
	FOUR('4', 4),
	THREE('3', 3),
	TWO('2', 2);
	
	private char nombre;
	private int valor;
	
	public char toChar(){
		return this.nombre;
	}
	
	Point(char point, int valor){
		this.nombre = point;
		this.valor = valor;
	}
	
	public int getPoint(){
		return this.nombre;
	}
	
	public int getValor(){
		return this.valor;
	}
	
	public static Point parsea(String string){
		switch(string){
			case "2": 	return Point.TWO;
			case "3": 	return Point.THREE;
			case "4": 	return Point.FOUR;
			case "5": 	return Point.FIVE;
			case "6": 	return Point.SIX;
			case "7": 	return Point.SEVEN;
			case "8": 	return Point.EIGHT;
			case "9": 	return Point.NINE;
			case "T": 	return Point.TEN;
			case "J": 	return Point.JACK;
			case "Q": 	return Point.QUEEN;
			case "K": 	return Point.KING;
			case "A": 	return Point.ACE;
		}
		
		return null;
	}
}
