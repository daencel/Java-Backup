package net.tfobz.mathematischeausdruecke;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class TreeImage extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	private ImageIcon add;
	private ImageIcon sub;
	private ImageIcon mult;
	private ImageIcon div;
	private ImageIcon pow;
	private ImageIcon sqrt;
	private ImageIcon log;
	private ImageIcon cons;

	public TreeImage() {
		add = new ImageIcon("Images/addition.gif");
		sub = new ImageIcon("Images/subtraktion.gif");
		mult = new ImageIcon("Images/multiplikation.gif");
		div = new ImageIcon("Images/division.gif");
		pow = new ImageIcon("Images/pow.png");
		sqrt = new ImageIcon("Images/sqrt.png");
		log = new ImageIcon("Images/log.png");
		cons = new ImageIcon("Images/konstante.gif");

		add = new ImageIcon(add.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		sub = new ImageIcon(sub.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		mult = new ImageIcon(mult.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		div = new ImageIcon(div.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		pow = new ImageIcon(pow.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		sqrt = new ImageIcon(sqrt.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		log = new ImageIcon(log.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		cons = new ImageIcon(cons.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
	}

	/**
	 * Returns the corresponding Icon
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {

		Component ret = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		if (value instanceof Addition) {
			setIcon(add);
		} else if (value instanceof Subtraktion) {
			setIcon(sub);
		} else if (value instanceof Multiplikation) {
			setIcon(mult);
		} else if (value instanceof Division) {
			setIcon(div);
		} else if (value instanceof Potenz) {
			setIcon(pow);
		} else if (value instanceof Wurzel) {
			setIcon(sqrt);
		} else if (value instanceof Logarithmus) {
			setIcon(log);
		} else if (value instanceof Konstante) {
			setIcon(cons);
		}
		return ret;
	}
}