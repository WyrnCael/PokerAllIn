package Cartas;

public enum Palo {
	Hearts("Red", 'h'),
	Diamonds("Red", 'd'),
	Clubs("Black", 'c'),
	Spades("Black", 's');
	
	
	private String color;
	private char letra;
	
	Palo(String color, char letra){
		this.color = color;
		this.letra = letra;
	}
	
	public String getPalo(){
		return this.color;
	}
	
	public char getLetra(){
		return this.letra;
	}
	
	public static Palo parsea(String c){
		switch(c){
			case "h":	return Palo.Hearts;
			case "d":	return Palo.Diamonds;
			case "c":	return Palo.Clubs;
			case "s":	return Palo.Spades;
		}
		
		return null;
	}
}
