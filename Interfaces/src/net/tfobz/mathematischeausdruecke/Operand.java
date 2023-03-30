package net.tfobz.mathematischeausdruecke;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public abstract class Operand implements TreeNode, MutableTreeNode {

	public abstract double getErgebnis();

}
