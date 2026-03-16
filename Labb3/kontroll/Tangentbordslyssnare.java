package Lab3.kontroll;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Lab3.modell.Nivå;
import Lab3.modell.Väderstreck;

/**
 * Lyssnar på tangentbordstryckningar och styr hoppen i en nivå.
 */
public class Tangentbordslyssnare implements KeyListener {
	private Nivå enNivå;

	/**
	 * Skapar en lyssnare som påverkar den angivna nivån.
	 * 
	 * @param enNivå nivån som ska påverkas av tangenttryckningar
	 */
	public Tangentbordslyssnare(Nivå enNivå) {
		this.enNivå = enNivå;
	}

	/**
	 * Hanterar nedtryckta tangenter och initierar hopp i lämplig riktning.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		char tangent = Character.toLowerCase(e.getKeyChar());
		System.out.println(tangent);

		switch (tangent) {
		case 'w':
			enNivå.hoppaÅt(Väderstreck.NORR);
			break;
		case 'd':
			enNivå.hoppaÅt(Väderstreck.ÖSTER);
			break;
		case 's':
			enNivå.hoppaÅt(Väderstreck.SÖDER);
			break;
		case 'a':
			enNivå.hoppaÅt(Väderstreck.VÄSTER);
			break;
		default:
			break;

		}
	}

	/**
	 * Ej använd men måste implementeras för KeyListener.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("key typed");
	}

	/**
	 * Ej använd men måste implementeras för KeyListener.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("key released");
	}
}
