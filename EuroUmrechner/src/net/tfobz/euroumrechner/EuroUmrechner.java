package net.tfobz.euroumrechner;

/**
 * Dieser Euroumrechner stellt Konstanten fuer die Kurse und die Namen der
 * Waehrungen bereit. Weiters kann dem Rechner ein Betrag und die
 * Ausgangswaehrung uebergeben werden in der dieser Betrag vorliegt: z. B.
 * betrag = 1000, waehrung = 7 fuer Italienische Lire. Der Umrechner kann dann
 * diesen Betrag in eine andere Waehrung durch die Methode getBetrag umrechnen.
 * Dieser Methode wird die Waehrung uebergeben in die umgerechnet werden soll:
 * z. B. getBetrag(1) wuerde im obigen Fall 7,106 ergeben weil 1 fuer
 * oesterreichische Schillinge steht.
 * 
 * @author Michael Wild
 */
public class EuroUmrechner {
	/*
	 * Private Membervariablen
	 */

	/**
	 * Speichert die Waehrung ab in welcher der Betrag eingegeben wurde
	 */
	private int waehrung = -1;
	/**
	 * Speichert den umzurechnenden Betrag ab, wobei in waehrung die Waehrung des
	 * umzurechnenden Betrages eingestellt wurde
	 */
	private double betrag = 0;

	/*
	 * oeffentlichen Konstanten
	 */
	/**
	 * Die Umrechnungskurse der einzelnen Waerungen in einem konstanten Feld
	 */
	public final double[] KURSE = { 1, 13.7603, 40.3399, 1.95583, 166.386, 6.55957, 0.787564, 1936.27, 40.399, 2.20371,
			200.482, 5.94573, 340.75 };
	/**
	 * Die Namen der einzelnen Waehrungen in einem konstanten Feld
	 */
	public final String[] WAEHRUNGEN = { "Euro", "Oesterreichische Schilling", "Belgische Franc", "Deutsche Mark",
			"Spanische Peseten", "Franzoesische Franc", "Irische Pfund", "Italienische Lire", "Luxenburgische Franc",
			"Niederlaendische Gulden", "Portugiesische Escudos", "Finnmark", "Griechische Drachmen" };
	public final int EURO = 0;
	public final int OESTERREICHISCHE_SCHILLING = 1;
	public final int BELGISCHE_FRANC = 2;
	public final int DEUTSCHE_MARK = 3;
	public final int SPANISCHE_PESETEN = 4;
	public final int FRANZOESISCHE_FRANC = 5;
	public final int IRISCHE_PFUND = 6;
	public final int ITALIENISCHE_LIRE = 7;
	public final int LUXENBURGISCHE_FRANC = 8;
	public final int NIEDERLAENDISCHE_GULDEN = 9;
	public final int PORTUGIESISCHE_ESCUDO = 10;
	public final int FINMARK = 11;
	public final int GRIECHISCHE_DRACHMEN = 12;

	/**
	 * Setzt die Waehrung, welche den Waehrungsbetrag entspricht. Es muss eine gueltige
	 * Waehrungsnummer eingegeben werden, ansonsten macht die Methode nichts
	 * 
	 * @param waehrung die zu setzen ist
	 */
	public void setWaehrung(int waehrung) {
		if (waehrung >= 0 && waehrung < KURSE.length)
			this.waehrung = waehrung;
	}

	/**
	 * Liefert die Nummer der Waehrung, welcher der eingegebene Waehrungsbetrag
	 * entspricht
	 * 
	 * @return die Nummer der Waehrung
	 */
	public int getWaehrung() {
		int ret = this.waehrung;
		return ret;
	}

	/**
	 * Setzt den umzurechnenden Betrag. Der Betrag muss groesser oder gleich 0 sein
	 * 
	 * @param betrag der gesetzt werden soll
	 */
	public void setBetrag(double betrag) {
		if (betrag >= 0)
			this.betrag = betrag;
	}

	/**
	 * Liefert den in die uebergebene Waehrung umgerechneten Waehrungsbetrag zurueck.
	 * Dabei muss Waehrung die Nummer einer gueltigen Waehrung sein
	 * 
	 * @param waehrung in die umgerechnet werden soll
	 * @return den in die uebergebene Waehrung umgerechnete Waehrungsbetrag
	 */
	public double getBetrag(int waehrung) {
		double ret = 0;
		if (waehrung >= 0 && waehrung < KURSE.length && this.waehrung >= 0)
			ret = this.betrag / KURSE[this.waehrung] * KURSE[waehrung];
		return ret;
	}
}
