package Lab3.vy;

import java.awt.Graphics;
import javax.swing.JPanel;

import Lab3.modell.Gång;
import Lab3.modell.Nivå;
import Lab3.modell.Rum;
import Lab3.modell.Väderstreck;
import Lab3.verktyg.Punkt;
import static Lab3.GlobalaKonstanter.*;
import static Lab3.verktyg.Grafik.*;

/**
 * JPanel som ritar upp en komplett grafisk vy av en `Nivå`. Tar hjälp av
 * konstanter i `GlobalaKonstanter` och ritmetoder i `Grafik`.
 */
public class Målarduk extends JPanel {

	private final Nivå enNivå;

	/**
	 * Skapar en målarduk kopplad till en nivå.
	 * 
	 * @param enNivå nivån som ska ritas
	 */
	public Målarduk(Nivå enNivå) {
		this.enNivå = enNivå;

		this.setBackground(MARKFÄRG);

		this.setFocusable(true);

	}

	/**
	 * Ritar hela nivån inklusive rum, gångar och markör för användaren.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Rita rum
		for (Rum r : enNivå.getRum()) {
			ritaRum(g, r);
		}

		// Rita gångar
		for (Rum r : enNivå.getRum()) {
			ritaGångarFrånRum(g, r);
		}

		ritaMarkörFörVarAnvändarenÄr(g);
	}

	/** Ritar ett enskilt rum inklusive väggar. */
	private void ritaRum(Graphics g, Rum ettRum) {

		int x = ettRum.getÖvreVänstraHörnet().x();
		int y = ettRum.getÖvreVänstraHörnet().y();
		int b = ettRum.getBredd();
		int h = ettRum.getHöjd();
		int halv = HALV_VÄGGTJOCKLEK;

		g.setColor(ettRum.getGolvfärg());
		g.fillRect(x, y, b, h);

		drawThickLine(g, new Punkt(x - halv, y), new Punkt(x + b + halv, y), VÄGGTJOCKLEK, VÄGGFÄRG);
		drawThickLine(g, new Punkt(x + b, y - halv), new Punkt(x + b, y + h + halv), VÄGGTJOCKLEK, VÄGGFÄRG);
		drawThickLine(g, new Punkt(x - halv, y + h), new Punkt(x + b + halv, y + h), VÄGGTJOCKLEK, VÄGGFÄRG);
		drawThickLine(g, new Punkt(x, y - halv), new Punkt(x, y + h + halv), VÄGGTJOCKLEK, VÄGGFÄRG);
	}

	/** Itererar över möjliga riktningar och ritar gångar som finns. */
	private void ritaGångarFrånRum(Graphics g, Rum ettRum) {
		for (Väderstreck v : Väderstreck.values()) {
			if (ettRum.finnsUtgångÅt(v)) {
				Gång gång = ettRum.gångenÅt(v);
				ritaGång(g, gång);
			}
		}

	}

	/**
	 * Baspunkt på rummets kant för angiven riktning.
	 */
	private Punkt baspunkt(Rum ettRum, Väderstreck enRiktning) {

		int x = ettRum.getÖvreVänstraHörnet().x();
		int y = ettRum.getÖvreVänstraHörnet().y();
		int b = ettRum.getBredd();
		int h = ettRum.getHöjd();
		int halv = HALV_VÄGGTJOCKLEK;

		switch (enRiktning) {

		case NORR:
			return new Punkt(x + b / 2, y + halv);

		case ÖSTER:
			return new Punkt(x + b - halv, y + h / 2);

		case SÖDER:
			return new Punkt(x + b / 2, y + h - halv);

		case VÄSTER:
			return new Punkt(x + halv, y + h / 2);
		}

		return null;
	}

	/**
	 * Pivotpunkt utanför rummets kant för angiven riktning.
	 */
	private Punkt pivotpunkt(Rum ettRum, Väderstreck enRiktning) {

		int x = ettRum.getÖvreVänstraHörnet().x();
		int y = ettRum.getÖvreVänstraHörnet().y();
		int b = ettRum.getBredd();
		int h = ettRum.getHöjd();
		int vägg = VÄGGTJOCKLEK;

		switch (enRiktning) {

		case NORR:
			return new Punkt(x + b / 2, y - vägg);

		case ÖSTER:
			return new Punkt(x + b + vägg, y + h / 2);

		case SÖDER:
			return new Punkt(x + b / 2, y + h + vägg);

		case VÄSTER:
			return new Punkt(x - vägg, y + h / 2);
		}

		return null;
	}

	/** Ritar en gång mellan två rum. */
	private void ritaGång(Graphics g, Gång enGång) {

		Rum från = enGång.getFrån();
		Rum till = enGång.getTill();

		Väderstreck ut = enGång.getRiktningUtUrFrån();
		Väderstreck in = enGång.getRiktningInITill();

		Punkt bas1 = baspunkt(från, ut);
		Punkt pivot1 = pivotpunkt(från, ut);

		Punkt bas2 = baspunkt(till, in);
		Punkt pivot2 = pivotpunkt(till, in);

		drawThickLine(g, bas1, pivot1, VÄGGTJOCKLEK, GÅNGFÄRG);

		fillCircle(g, pivot1, HALV_VÄGGTJOCKLEK, GÅNGFÄRG);

		drawThickLine(g, pivot1, pivot2, VÄGGTJOCKLEK, GÅNGFÄRG);

		fillCircle(g, pivot2, HALV_VÄGGTJOCKLEK, GÅNGFÄRG);

		drawThickLine(g, pivot2, bas2, VÄGGTJOCKLEK, GÅNGFÄRG);

	}

	/** Ritar en markör för var användaren befinner sig i aktuellt rum. */
	private void ritaMarkörFörVarAnvändarenÄr(Graphics g) {
		Rum r = enNivå.getAktuelltRum();

		int x = r.getÖvreVänstraHörnet().x();
		int y = r.getÖvreVänstraHörnet().y();
		int b = r.getBredd();
		int h = r.getHöjd();

		Punkt mitten = new Punkt(x + b / 2, y + h / 2);

		fillCircle(g, mitten, ANVÄNDARRADIE, ANVÄNDARFÄRG);

	}
}
