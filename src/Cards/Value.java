package Cards;

public enum Value {
	Ace("A", 0),
	King("K", 1),
	Queen("Q", 2),
	Jack("J", 3),
	Ten("T", 4),
	Nine("9", 5),
	Eight("8", 6),
	Seven("7", 7),
	Six("6", 8),
	Five("5", 9),
	Four("4", 10),
	Three("3", 11),
	Two("2", 12);

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
	
	private Value(String name) {
		this.name = name;
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
