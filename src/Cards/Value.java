package Cards;

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

	// Campos de la clase
	private String name;
	private int value;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            El parametro name es el string del valor
	 * @param valor
	 *            El parametro es el valor
	 */
	private Value(String name, int valor) {
		this.name = name;
		this.value = valor;
	}

	/**
	 * Metodo que devuelve el string del valor
	 * 
	 * @return name es el string del valor
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo que devuelve el valor
	 * 
	 * @return value es el valor
	 */
	public int getValue() {
		return value;
	}
}
