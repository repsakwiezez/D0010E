package Lab3.modell;

/**
 * Uppräkning av de fyra väderstrecken som används i modellen.
 */
public enum Väderstreck {
	NORR, ÖSTER, SÖDER, VÄSTER;

	/**
	 * Returnerar ett index som kan användas för att adressera arrayer med
	 * information per väderstreck.
	 * 
	 * @return index för detta väderstreck
	 */
	public int index() {
		switch (this) {
		case NORR:
			return 0;
		case ÖSTER:
			return 1;
		case SÖDER:
			return 2;
		case VÄSTER:
			return 3;
		default:
			return -1;

		}
	}

}
