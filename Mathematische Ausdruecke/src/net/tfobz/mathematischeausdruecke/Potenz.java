package net.tfobz.mathematischeausdruecke;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Potenz extends Operation {

	public static void main(String[] args) {
		Potenz a = new Potenz(new Konstante(5.0), new Konstante(-2.0));
		System.out.println(a.toString());
	}

	/**
	 * Erstellt eine Potenz
	 * 
	 * @param operand0 Operand 0
	 * @param operand1 Operand 1
	 */
	public Potenz(Operand operand0, Operand operand1) {
		super(operand0, operand1);
	}

	/**
	 * Standardkonstruktor
	 */
	public Potenz() {
		super();
	}

	/**
	 * Berechent das Ergebnis
	 * 
	 * @return gibt das Ergebnis als double Wert zurueck
	 */
	public double getErgebnis() {
		double ret = 0.0;
		if (this.getOperand(0) != null)
			ret = this.getOperand(0).getErgebnis();
		if (this.getOperand(1) != null)
			ret = Math.pow(ret, this.getOperand(1).getErgebnis());
		return ret;
	}

	/**
	 * Gibt das Ergebnis als String zurueck
	 * 
	 * @return Ergebnis als String
	 */
	public String toString() {
		String ret = null;
		ret = "(" + super.getOperand(0) + "^" + super.getOperand(1) + "=" + getErgebnis() + ")";
		return ret;
	}
}
