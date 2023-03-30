package net.tfobz.mathematischeausdruecke;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Division extends Operation {

	/**
	 * Erstellt eine Division
	 * 
	 * @param operand0 Operand 0
	 * @param operand1 Operand 1
	 */
	public Division(Operand operand0, Operand operand1) {
		super(operand0, operand1);
	}

	/**
	 * Standardkonstruktor
	 */
	public Division() {
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
			ret = ret / this.getOperand(1).getErgebnis();
		return ret;
	}

	/**
	 * Gibt das Ergebnis als String zurueck
	 * 
	 * @return Ergebnis als String
	 */
	public String toString() {
		String ret = null;
		ret = "(" + super.getOperand(0) + "/" + super.getOperand(1) + "=" + getErgebnis() + ")";
		return ret;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return this.getOperand(childIndex);
	}

	@Override
	public int getChildCount() {
		if (this.getOperand(0) != null)
			if (this.getOperand(1) != null)
				return 2;
			else
				return 1;
		else
			return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		return -1;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public Enumeration<? extends TreeNode> children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(MutableTreeNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserObject(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		// TODO Auto-generated method stub
		
	}
}
