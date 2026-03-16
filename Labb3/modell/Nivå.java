package Lab3.modell;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Modellklass som representerar en nivå bestående av flera rum. Klassen är en
 * Observable så att vyer kan observera förändringar i vilket rum som är aktivt.
 */
public class Nivå extends Observable {

	private ArrayList<Rum> rum;
	private Rum aktuelltRum;

	/**
	 * Skapar en nivå med ett start-rum och en lista av rum.
	 * 
	 * @param startrum det rum användaren börjar i
	 * @param rum      listan över rum på nivån
	 * @throws IllegalArgumentException om startrum inte finns i listan eller om två
	 *                                  rum överlappar
	 */
	public Nivå(Rum startrum, ArrayList<Rum> rum) {
		this.rum = rum;
		this.aktuelltRum = startrum;

		if (!this.rum.contains(startrum)) {
			throw new IllegalArgumentException("Startrummet finns inte med i listan av rum på nivån.");
		}

		for (int i = 0; i < this.rum.size(); i++) {
			for (int j = i + 1; j < this.rum.size(); j++) {
				Rum r1 = this.rum.get(i);
				Rum r2 = this.rum.get(j);

				boolean överlapparInte = r1.getÖvreVänstraHörnet().x() + r1.getBredd() <= r2.getÖvreVänstraHörnet().x()
						|| r2.getÖvreVänstraHörnet().x() + r2.getBredd() <= r1.getÖvreVänstraHörnet().x()
						|| r1.getÖvreVänstraHörnet().y() + r1.getHöjd() <= r2.getÖvreVänstraHörnet().y()
						|| r2.getÖvreVänstraHörnet().y() + r2.getHöjd() <= r1.getÖvreVänstraHörnet().y();

				if (!överlapparInte) {
					throw new IllegalArgumentException("Rummen på nivån får inte överlappa varandra.");
				}
			}
		}
	}

	/**
	 * Returnerar listan med rum som finns på nivån.
	 * 
	 * @return lista med rum
	 */
	public ArrayList<Rum> getRum() {
		return rum;
	}

	/**
	 * Returnerar det rum som användaren för närvarande befinner sig i.
	 * 
	 * @return aktuellt rum
	 */
	public Rum getAktuelltRum() {
		return aktuelltRum;
	}

	/**
	 * Försöker hoppa åt angivet väderstreck. Om det finns en gång i den riktningen
	 * så flyttas användaren till målrummet och observerande vyer notifieras.
	 * 
	 * @param väderstreck riktning att hoppa åt
	 */
	public void hoppaÅt(Väderstreck väderstreck) {
		if (aktuelltRum.finnsUtgångÅt(väderstreck)) {
			Gång gången = aktuelltRum.gångenÅt(väderstreck);
			aktuelltRum = gången.getTill();

			setChanged();
			notifyObservers();
		}

	}
}
