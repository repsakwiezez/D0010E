package Lab3.modell;

import java.awt.Color;
import Lab3.GlobalaKonstanter;
import Lab3.verktyg.Punkt;

/**
 * Modellklass som representerar ett rektangulärt rum på en nivå.
 */
public class Rum {

	private Color golvfärg;
	private int bredd;
	private int höjd;
	private Punkt övreVänstraHörnet;
	private Gång[] gångar;

	/**
	 * Skapar ett rum med angiven golvfärg, storlek och övre-vänstra hörn.
	 * 
	 * @param golvfärg färg på golvet
	 * @param bredd    bredd i pixlar
	 * @param höjd     höjd i pixlar
	 * @param övX      x-koordinat för övre-vänstra hörnet
	 * @param övY      y-koordinat för övre-vänstra hörnet
	 */
	public Rum(Color golvfärg, int bredd, int höjd, int övX, int övY) {
		this.golvfärg = golvfärg;
		this.bredd = bredd;
		this.höjd = höjd;
		this.övreVänstraHörnet = new Punkt(övX, övY);
		this.gångar = new Gång[GlobalaKonstanter.ANTAL_VÄDERSTRECK];

	}

	/** @return golvfärgen */
	public Color getGolvfärg() {
		return golvfärg;
	}

	/** @return höjden */
	public int getHöjd() {
		return höjd;
	}

	/** @return bredden */
	public int getBredd() {
		return bredd;
	}

	/** @return övre-vänstra hörnet som en Punkt */
	public Punkt getÖvreVänstraHörnet() {
		return övreVänstraHörnet;
	}

	/**
	 * Kontrollerar om det finns en utgång i angiven riktning.
	 * 
	 * @param v väderstreck
	 * @return true om en utgång finns
	 */
	public boolean finnsUtgångÅt(Väderstreck v) {
		return gångar[v.index()] != null;
	}

	/**
	 * Returnerar gången i angiven riktning från detta rum.
	 * 
	 * @param v väderstreck
	 * @return gången i den riktningen
	 * @throws IllegalArgumentException om ingen utgång finns åt det hållet
	 */
	public Gång gångenÅt(Väderstreck v) {
		if (!finnsUtgångÅt(v)) {
			throw new IllegalArgumentException("Det finns ingen utgång åt " + v);
		}
		return gångar[v.index()];
	}

	/**
	 * Kopplar ihop två rum genom att skapa gångar åt båda håll.
	 * 
	 * @param från             start-rummet
	 * @param riktningUtUrFrån riktningen ut ur start-rummet
	 * @param till             målrummet
	 * @param riktningInITill  riktningen in i målrummet
	 */
	public static void kopplaIhop(Rum från, Väderstreck riktningUtUrFrån, Rum till, Väderstreck riktningInITill) {
		if (från.finnsUtgångÅt(riktningUtUrFrån)) {
			throw new IllegalArgumentException("Startrummet har redan en utgång åt det hållet");
		}

		if (till.finnsUtgångÅt(riktningInITill)) {
			throw new IllegalArgumentException("Målrummet har redan en ingång åt det hållet");
		}

		Gång gångFrån = new Gång(från, riktningUtUrFrån, till, riktningInITill);
		Gång gångTill = new Gång(till, riktningInITill, från, riktningUtUrFrån);

		från.gångar[riktningUtUrFrån.index()] = gångFrån;
		till.gångar[riktningInITill.index()] = gångTill;
	}
}
