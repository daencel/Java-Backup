package net.tfobz.mathematischeausdruecke;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Subtraktion extends Operation {

	public static void main(String[] args) {
		Subtraktion a = new Subtraktion(new Konstante(9.0), new Konstante(2.4));
		System.out.println(a.toString());
	}

	/**
	 * Erstellt eine Subtraktion
	 * 
	 * @param operand0 Operand 0
	 * @param operand1 Operand 1
	 */
	public Subtraktion(Operand operand0, Operand operand1) {
		super(operand0, operand1);
	}

	/**
	 * Standardkonstruktor
	 */
	public Subtraktion() {
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
			ret = ret - this.getOperand(1).getErgebnis();
		return ret;
	}

	/**
	 * Gibt das Ergebnis als String zurueck
	 * 
	 * @return Ergebnis als String
	 */
	public String toString() {
		String ret = null;
		ret = "(" + super.getOperand(0) + "-" + super.getOperand(1) + "=" + getErgebnis() + ")";
		return ret;
	}
}
