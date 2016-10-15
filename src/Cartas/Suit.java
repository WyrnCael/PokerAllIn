package Cartas;

public enum Suit {
	Hearts("Red", 'h'),
	Diamonds("Red", 'd'),
	Clubs("Black", 'c'),
	Spades("Black", 's');
	
	
	private String color;
	private char letra;
	
	Suit(String color, char letra){
		this.color = color;
		this.letra = letra;
	}
	
	public String getPalo(){
		return this.color;
	}
	
	public char getChar(){
		return this.letra;
	}
	
	public static Suit parsea(String c){
		switch(c){
			case "h":	return Suit.Hearts;
			case "d":	return Suit.Diamonds;
			case "c":	return Suit.Clubs;
			case "s":	return Suit.Spades;
		}
		
		return null;
	}
}
