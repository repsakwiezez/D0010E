package Lab3.modell;

/**
 * Representerar en passage (gång) mellan två rum i en viss riktning.
 */
public class Gång {

	private Rum från;
	private Väderstreck riktningUtUrFrån;
	private Rum till;
	private Väderstreck riktningInITill;

	/**
	 * Skapar en gång mellan två rum.
	 * 
	 * @param från             start-rummet
	 * @param riktningUtUrFrån riktningen ut från start-rummet
	 * @param till             mål-rummet
	 * @param riktningInITill  riktningen in i mål-rummet
	 */
	public Gång(Rum från, Väderstreck riktningUtUrFrån, Rum till, Väderstreck riktningInITill) {
		this.från = från;
		this.riktningUtUrFrån = riktningUtUrFrån;
		this.till = till;
		this.riktningInITill = riktningInITill;
	}

	/** @return start-rummet */
	public Rum getFrån() {
		return från;
	}

	/** @return riktningen ut ur start-rummet */
	public Väderstreck getRiktningUtUrFrån() {
		return riktningUtUrFrån;
	}

	/** @return målrummet */
	public Rum getTill() {
		return till;
	}

	/** @return riktningen in i målrummet */
	public Väderstreck getRiktningInITill() {
		return riktningInITill;
	}
}
