package net.tfobz.mathematischeausdruecke;

public abstract class ArgOperation extends Operand {

	// Operanden - Array
	private Operand[] operand = new Operand[2];

	/**
	 * Erstellt eine Operation
	 * 
	 * @param operand0 Operand 0
	 * @param operand1 Operand 1
	 */
	public ArgOperation(Operand operand0, Operand operand1) {
		this.setOperand(operand0);
		if (operand1.getErgebnis() < 0) {
			int temp = (int) Math.round(Math.abs(operand0.getErgebnis()));
			this.setOperand(new Konstante(temp));
		} else {
			this.setOperand(operand1);
		}
	}

	/**
	 * Standardkonstruktor
	 */
	public ArgOperation() {
		super();
	}

	/**
	 * Setzt den Operand an der naechst verfuegbaren Stelle
	 * 
	 * @param operand
	 */
	public void setOperand(Operand operand) {
		if (this.operand[0] == null)
			this.operand[0] = operand;
		else if (this.operand[1] == null) {
			if (operand.getErgebnis() < 0) {
				int temp = (int) Math.round(Math.abs(operand.getErgebnis()));
				this.operand[1] = new Konstante(temp);
			} else {
				this.operand[1] = operand;
			}
		}
	}

	/**
	 * Gibt den Operand an der uebergebenen Position zurueck
	 * 
	 * @param position position des Operand
	 * @return Operand
	 */
	public Operand getOperand(int position) {
		if (position >= 0 && position <= 1)
			return this.operand[position];
		else
			return null;
	}

	/**
	 * Vertauscht die beiden Operanden
	 */
	public void vertausche() {
		if (this.operand[0] != null && this.operand[1] != null) {
			Operand operand = this.operand[0];
			this.operand[0] = this.operand[1];
			this.operand[1] = operand;
		}
	}

	/**
	 * Loescht den Operand an der uebergeben Position
	 * 
	 * @param position
	 */
	public void loescheOperand(int position) {
		if (position == 0) {
			this.operand[0] = this.operand[1];
			this.operand[1] = null;
		} else if (position == 1)
			this.operand[1] = null;
	}
}
