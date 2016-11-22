package Cards;

public enum RankingCombos {
	STRAIGHT_FLUSH("Straight flush", 14),
	FOUR_OF_A_KIND("Four of a kind", 13),
	FULL_HOUSE("Full House", 12),
	FLUSH("Flush", 11),
	STRAIGHT("Straight", 10),
	SET("Set", 9),
	TWO_PAIR("Two pair", 8),
	OVERPAIR("Over pair", 7),
	TOP_PAIR("Top pair", 6),
	PP_BELOW_TP("Pocket pair below top pair",5),
	MIDDLE_PAIR("Middle pair", 4),
	WEAK_PAIR("Weak pair", 3),
	ACE_HIGH("Ace high", 2),
	NO_MADE_HAND("No made hand", 1);

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
	private RankingCombos(String name, int value) {
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