package Cartas;

public enum Point {
	Ace("A", 14),
	King("K", 13),
	Queen("Q", 12),
	Jack("J", 11),
	Ten("T", 10),
	Nine("9", 9),
	Eight("8", 8),
	Seven("7", 7),
	Six("6", 6),
	Five("5", 5),
	Four("4", 4),
	Three("3", 3),
	Two("2", 2);
	
	private String name;
	private int value;
	
	
	Point(String name, int valor){
		this.name = name;
		this.value = valor;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getValor(){
		return this.value;
	}
	
	public static Point parsea(String string){
		switch(string){
			case "2": 	return Point.Two;
			case "3": 	return Point.Three;
			case "4": 	return Point.Four;
			case "5": 	return Point.Five;
			case "6": 	return Point.Six;
			case "7": 	return Point.Seven;
			case "8": 	return Point.Eight;
			case "9": 	return Point.Nine;
			case "T": 	return Point.Ten;
			case "J": 	return Point.Jack;
			case "Q": 	return Point.Queen;
			case "K": 	return Point.King;
			case "A": 	return Point.Ace;
		}
		
		return null;
	}
}
