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
	
	
	private Value(String name, int valor){
		this.name = name;
		this.value = valor;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getValor(){
		return this.value;
	}
}
