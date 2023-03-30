package net.tfobz.mathematischeausdruecke;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Logarithmus extends ArgOperation {

	public static void main(String[] args) {
		Logarithmus a = new Logarithmus(new Konstante(10.0), new Konstante(-10.0));
		System.out.println(a.toString());
	}

	/**
	 * Erstellt eine Logarithmus
	 * 
	 * @param operand0 Operand 0
	 * @param operand1 Operand 1
	 */
	public Logarithmus(Operand operand0, Operand operand1) {
		super(operand0, operand1);
	}

	/**
	 * Standardkonstruktor
	 */
	public Logarithmus() {
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
			ret = Math.log(ret) / Math.log(this.getOperand(1).getErgebnis());
		return ret;
	}

	/**
	 * Gibt das Ergebnis als String zurueck
	 * 
	 * @return Ergebnis als String
	 */
	public String toString() {
		String ret = null;
		ret = "(Log(" + super.getOperand(1) + "," + super.getOperand(0) + "=" + getErgebnis() + "))";
		return ret;
	}
}
