package Lab3.verktyg;

/**
 * Icke muterbar klass för punkter.
 *
 * @author Håkan Jonsson
 */
public class Punkt {

	private final int x, y;

	/**
	 * Skapar en punkt med angivna koordinater.
	 * 
	 * @param x x-koordinat
	 * @param y y-koordinat
	 */
	public Punkt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** @return x-koordinaten */
	public int x() {
		return x;
	}

	/** @return y-koordinaten */
	public int y() {
		return y;
	}

	/** @return strängrepresentation av punkten */
	public String toString() {
		return "(" + x() + "," + y() + ")";
	}
}
