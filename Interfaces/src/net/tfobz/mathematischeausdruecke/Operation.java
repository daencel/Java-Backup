package net.tfobz.mathematischeausdruecke;

import javax.swing.tree.TreeNode;

/**
 * Achtung: Die Operanden werden immer der Reihe nach in die Operation gehaengt.
 * Mit setOperand kann man beim ersten Aufruf den ersten Operanden fuellen, ruft
 * man setOperand nochmals auf, so wird der zweite Operand gefuellt. Beim
 * Loeschen werden die Operanden unter Umstaenden nach vorne verschoben.
 * 
 * @author Michael Wild
 */
public abstract class Operation extends Operand {

	// Operanden - Array
	private Operand[] operand = new Operand[2];

	/**
	 * Erstellt eine Operation
	 * 
	 * @param operand0 Operand 0
	 * @param operand1 Operand 1
	 */
	public Operation(Operand operand0, Operand operand1) {
		this.setOperand(operand0);
		this.setOperand(operand1);
	}

	/**
	 * Standardkonstruktor
	 */
	public Operation() {
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
		else if (this.operand[1] == null)
			this.operand[1] = operand;
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

	public int getOperandId(double nummer) {
		if (this.operand[0].getErgebnis() ==nummer)
			return 0;
		if (this.operand[1].getErgebnis() == nummer)
			return 1;
		return -1;
	}

	@Override
	public int getIndex(TreeNode node) {
		if (node.equals(this.getChildAt(0))) {
			return 0;
		} else if (node.equals(this.getChildAt(1))) {
			return 1;
		}
		return -1;
	}
}
