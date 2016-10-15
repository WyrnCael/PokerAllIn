package Cartas;

public enum Value {
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
	
	
	Value(String name, int valor){
		this.name = name;
		this.value = valor;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getValor(){
		return this.value;
	}
	
	public static Value parsea(String string){
		switch(string){
			case "2": 	return Value.Two;
			case "3": 	return Value.Three;
			case "4": 	return Value.Four;
			case "5": 	return Value.Five;
			case "6": 	return Value.Six;
			case "7": 	return Value.Seven;
			case "8": 	return Value.Eight;
			case "9": 	return Value.Nine;
			case "T": 	return Value.Ten;
			case "J": 	return Value.Jack;
			case "Q": 	return Value.Queen;
			case "K": 	return Value.King;
			case "A": 	return Value.Ace;
		}
		
		return null;
	}
}
