package Lab3.verktyg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Hjälpklass med enkla ritmetoder som förenklar ritandet i vyerna. Metoderna
 * tar generella Graphics-objekt men arbetar internt med Graphics2D för bättre
 * kontroll över tjocklek och pensel.
 *
 * @author Håkan Jonsson
 */
public class Grafik {
	/**
	 * Ritar en linje med angiven tjocklek och färg.
	 * 
	 * @param g     Graphics-objekt
	 * @param start startpunkt
	 * @param slut  slutpunkt
	 * @param width tjocklek i pixlar
	 * @param c     färg
	 */
	public static void drawThickLine(Graphics g, Punkt start, Punkt slut, int width, Color c) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(c);
		g2d.setStroke(new BasicStroke((float) width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
		g2d.drawLine(start.x(), start.y(), slut.x(), slut.y());
		g2d.setStroke(new BasicStroke(1));
	}

	/**
	 * Fyller en cirkel (oval) med angiven mittpunkt, radie och färg.
	 * 
	 * @param g         Graphics-objekt
	 * @param mittpunkt mittpunkt
	 * @param radie     radie i pixlar
	 * @param c         färg
	 */
	public static void fillCircle(Graphics g, Punkt mittpunkt, int radie, Color c) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(c);
		g2d.setStroke(new BasicStroke(1));
		g2d.fillOval(mittpunkt.x() - radie, mittpunkt.y() - radie, 2 * radie, 2 * radie);
	}
}
