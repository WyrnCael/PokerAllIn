package Cartas;

public enum Ranking {
	STRAIGHT_FLUSH("Straight Flush", 9),
	FOUR_OF_A_KIND("Four of a kind", 8),
	FULL_HOUSE("Full House", 7),
	FLUSH("Flush", 6),
	STRAIGHT("Straight", 5),
	THREE_OF_A_KIND("Three of a kind",4),
	TWO_PAIR("Two pair", 3),
	PAIR("Pair", 2),
	HIGH_CARD("High card", 1);

	// Campos de la clase
	private String name;
	private int value;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            El parametro name define el nombre de la jugada
	 * @param value
	 *            el parametro value define el valor de la jugada
	 */
	private Ranking(String name, int value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * El metodo que devuelve el nombre de la jugada
	 * 
	 * @return name es el nombre de la jugada
	 */
	public String getName() {
		return name;
	}

	/**
	 * El metodo que devuelve el valor de la jugada
	 * 
	 * @return value es el valor de la jugada
	 */
	public int getValue() {
		return value;
	}

}