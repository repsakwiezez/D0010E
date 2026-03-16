package Lab3;

import java.util.Observable;
import javax.swing.JFrame;
import java.util.Observer;
import java.awt.Dimension;

import Lab3.kontroll.Tangentbordslyssnare;
import Lab3.modell.Nivå;
import Lab3.vy.Målarduk;

/**
 * Grafiskt användargränssnitt för att visa en nivå och lyssna på förändringar i
 * modellen. Klassen är en JFrame och en Observer som uppdateras när nivån
 * förändras.
 */
public class GUI extends JFrame implements Observer {

	private Målarduk målarduk;

	/**
	 * Skapar ett nytt GUI som visar den givna nivån.
	 * 
	 * @param enNivå nivån som ska visas
	 */
	public GUI(Nivå enNivå) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		enNivå.addObserver(this);

		målarduk = new Målarduk(enNivå);

		målarduk.setPreferredSize(new Dimension(550, 450));

		målarduk.addKeyListener(new Tangentbordslyssnare(enNivå));

		setContentPane(målarduk);

		setVisible(true);

		pack();

	}

	/**
	 * Anropas när en observerad Observable (nivån) ändras. Uppdaterar
	 * vy-komponenten så att ändringarna syns.
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.målarduk.repaint();
	}
}
