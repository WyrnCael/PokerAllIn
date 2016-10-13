package Cartas;

public enum Point {
	ACE('A'),
	KING('K'),
	QUEEN('Q'),
	JACK('J'),
	TEN('T'),
	NINE('9'),
	EIGHT('8'),
	SEVEN('7'),
	SIX('6'),
	FIVE('5'),
	FOUR('4'),
	THREE('3'),
	TWO('2');
	
	private char point;
	private String desc;
	
	public String toString(){
		return this.desc;
	}
	
	Point(char point){
		this.point = point;
	}
	
	public int getPoint(){
		return this.point;
	}
}
