package net.tfobz.mathematischeausdruecke;

import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

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

	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public boolean isLeaf() {
		return true;
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
		try {
			this.setErgebnis(Double.parseDouble(object.toString()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Konnte Nummer nicht auslesen","Error",JOptionPane.ERROR_MESSAGE);
		}
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
