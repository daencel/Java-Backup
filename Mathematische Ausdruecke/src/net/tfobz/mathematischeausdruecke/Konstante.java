package net.tfobz.mathematischeausdruecke;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Konstante extends Operand {

	// Ergebins
	private double ergebnis = 0.0;

	/**
	 * Erstellt eine Konstante
	 * 
	 * @param ergebnis ergebnis
	 */
	public Konstante(double ergebnis) {
		this.ergebnis = ergebnis;
	}

	/**
	 * Standardkonstruktor
	 */
	public Konstante() {
		super();
	}

	/**
	 * Setzt das Ergebnis
	 * 
	 * @param ergebnis Ergebnis
	 */
	public void setErgebnis(double ergebnis) {
		this.ergebnis = ergebnis;
	}

	/**
	 * Gibt das Ergebnis zurueck
	 * 
	 * @return ergebnis
	 */
	public double getErgebnis() {
		return this.ergebnis;
	}

	/**
	 * Gibt das Ergebnis als String zurueck
	 * 
	 * @return Ergebnis
	 */
	public String toString() {
		return String.valueOf(this.ergebnis);
	}
}
