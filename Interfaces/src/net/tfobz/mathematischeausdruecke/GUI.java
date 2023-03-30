package net.tfobz.mathematischeausdruecke;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Content Pane
	 */
	private Container contentPane;

	/**
	 * JMenueItem Addition
	 */
	private JMenuItem addition;

	/**
	 * JMenueItem Subdraktion
	 */
	private JMenuItem subdraktion;

	/**
	 * JMenueItem Division
	 */
	private JMenuItem division;

	/**
	 * JMenueItem Multiplikation
	 */
	private JMenuItem multiplikation;

	/**
	 * JMenueItem Konstante
	 */
	private JMenuItem konstante;

	/**
	 * JMenueItem Potenz
	 */
	private JMenuItem potenz;

	/**
	 * JMenue Neu
	 */
	private JMenu neu;

	/**
	 * JMenueItem Löschen
	 */
	private JMenuItem loeschen;

	/**
	 * JMenueItem Vertauschen
	 */
	private JMenuItem vertauschen;

	/**
	 * JTree
	 */
	private JTree tree;

	/**
	 * Operation
	 */
	private Operation operation;

	/**
	 * Menü für Bearbeitung
	 */
	private JPopupMenu menue;

	/**
	 * ScrollPane
	 */
	private JScrollPane scrollpane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setIconImage(new ImageIcon("Images/mathematik.png").getImage());
		setTitle("Mathematische Ausdrücke");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 450);
		setLocationRelativeTo(null);

		// contentPane aufbauen
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(0, 0));

		// Standardoperation initialisieren
		operation = new Addition(new Konstante(0), new Konstante(0));

		// Tree Root
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("unsichtbare Wurzel");
		root.add(operation);
		TreeModel model = new DefaultTreeModel(root);

		// Menue action listener
		ActionListerner alistener = new ActionListerner();

		// Menue erstellen
		menue = new JPopupMenu();
		neu = new JMenu("Neu");
		konstante = new JMenuItem("Konstante");
		neu.add(konstante);
		neu.addSeparator();
		konstante.addActionListener(alistener);
		addition = new JMenuItem("Addition");
		neu.add(addition);
		addition.addActionListener(alistener);
		subdraktion = new JMenuItem("Subdraktion");
		neu.add(subdraktion);
		subdraktion.addActionListener(alistener);
		multiplikation = new JMenuItem("Multiplikation");
		neu.add(multiplikation);
		multiplikation.addActionListener(alistener);
		division = new JMenuItem("Division");
		neu.add(division);
		division.addActionListener(alistener);
		potenz = new JMenuItem("Potenz");
		neu.add(potenz);
		potenz.addActionListener(alistener);
		menue.add(neu);
		menue.addSeparator();
		loeschen = new JMenuItem("Löschen");
		menue.add(loeschen);
		loeschen.addActionListener(alistener);
		vertauschen = new JMenuItem("Vertauschen");
		menue.add(vertauschen);
		vertauschen.addActionListener(alistener);

		// JTree einstellungen vornehmen
		tree = new JTree(model);
		tree.setEditable(true);
		tree.setRootVisible(false);
		tree.setComponentPopupMenu(menue);
		tree.setCellRenderer(new TreeImage());

		scrollpane = new JScrollPane(tree);
		contentPane.add(scrollpane, BorderLayout.CENTER);
	}

	/**
	 * 
	 * @author Daniel Lechner
	 *
	 */
	public class ActionListerner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (tree.getSelectionPath() != null
					&& tree.getSelectionPath().getLastPathComponent() instanceof Operation) {
				operation = (Operation) tree.getSelectionPath().getLastPathComponent();
			}

			if (source.equals(konstante)) {
				try {
					operation.setOperand(new Konstante());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(GUI.this, "No operation selected", "INFORMATION",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			else if (source.equals(addition)) {
				if (operation == null)
					operation = new Addition();
				else {
					Operation temp = new Addition();
					operation.setOperand(temp);
				}
			}

			else if (source.equals(division)) {
				if (operation == null)
					operation = new Division();
				else {
					Operation temp = new Division();
					operation.setOperand(temp);
				}
			}

			else if (source.equals(multiplikation)) {
				if (operation == null)
					operation = new Multiplikation();
				else {
					Operation temp = new Multiplikation();
					operation.setOperand(temp);
				}
			}

			else if (source.equals(subdraktion)) {
				if (operation == null)
					operation = new Subtraktion();
				else {
					Operation temp = new Subtraktion();
					operation.setOperand(temp);
				}
			}

			else if (source.equals(potenz)) {
				if (operation == null)
					operation = new Potenz();
				else {
					Operation temp = new Potenz();
					operation.setOperand(temp);
				}
			}

			else if (source.equals(loeschen)) {
				if ((tree.getSelectionPath().getLastPathComponent()) instanceof Konstante) {
					TreePath path = tree.getSelectionPath();
					Operation temp = (Operation) path.getPathComponent(path.getPathCount() - 2);
					try {
						if (temp.getOperand(0).equals((Konstante) tree.getSelectionPath().getLastPathComponent())) {
							((Operation) path.getPathComponent(path.getPathCount() - 2)).loescheOperand(0);
						} else if (temp.getOperand(1)
								.equals((Konstante) tree.getSelectionPath().getLastPathComponent())) {
							((Operation) path.getPathComponent(path.getPathCount() - 2)).loescheOperand(1);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(GUI.this, "No Item selected", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					try {
						TreePath path = tree.getSelectionPath();
						Operation temp = (Operation) path.getPathComponent(path.getPathCount() - 2);
						if (temp.getOperand(0).equals((Operation) tree.getSelectionPath().getLastPathComponent())) {
							((Operation) path.getPathComponent(path.getPathCount() - 2)).loescheOperand(0);
						} else if ((temp.getOperand(1)
								.equals((Operation) tree.getSelectionPath().getLastPathComponent()))) {
							((Operation) path.getPathComponent(path.getPathCount() - 2)).loescheOperand(1);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(GUI.this, "Cannot delete root", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			else if (source.equals(vertauschen)) {
				operation.vertausche();
			}

			tree.expandPath(tree.getSelectionPath());
			tree.updateUI();
		}
	}
}