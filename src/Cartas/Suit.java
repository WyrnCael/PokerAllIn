package Cartas;

public enum Suit {
	Hearts("Red", 'h'),
	Diamonds("Red", 'd'),
	Clubs("Black", 'c'),
	Spades("Black", 's');
	
	
	private String color;
	private char letra;
	
	private Suit(String color, char letter){
		this.color = color;
		this.letra = letter;
	}
	
	public String getPalo(){
		return this.color;
	}
	
	public char getChar(){
		return this.letra;
	}
}
