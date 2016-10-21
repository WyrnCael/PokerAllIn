package Cartas;

public enum Ranking {
	STRAIGHT_FLUSH("Straight flush", 9),
	FOUR_OF_A_KIND("Four of a kind", 8),
	FULL_HOUSE("Full house", 7),
	FLUSH("Flush", 6),
	STRAIGHT("Straight", 5),
	THREE_OF_A_KIND("Three of a kind", 4),
	TWO_PAIR("Two pair", 3),
	PAIR("Pair", 2),
	HIGH_CARD("High card", 1);
	
	private String name;
	private int value;
	
	private Ranking(String name, int value){
		this.name = name;
		this.value = value;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getValue(){
		return this.value;
	}
	
}