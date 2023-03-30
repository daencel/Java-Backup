package net.tfobz.mathematischeausdruecke;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Wurzel extends ArgOperation {

	public static void main(String[] args) {
		Wurzel a = new Wurzel(new Konstante(100.0), new Konstante(2.0));
		System.out.println(a.toString());
	}

	/**
	 * Erstellt eine Wurzel
	 * 
	 * @param operand0 Operand 0
	 * @param operand1 Operand 1
	 */
	public Wurzel(Operand operand0, Operand operand1) {
		super(operand0, operand1);
	}

	/**
	 * Standardkonstruktor
	 */
	public Wurzel() {
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
			ret = Math.pow(ret, 1 / this.getOperand(1).getErgebnis());
		return ret;
	}

	/**
	 * Gibt das Ergebnis als String zurueck
	 * 
	 * @return Ergebnis als String
	 */
	public String toString() {
		String ret = null;
		ret = "(Wurzel(" + super.getOperand(1) + "," + super.getOperand(0) + "=" + getErgebnis() + "))";
		return ret;
	}
}
