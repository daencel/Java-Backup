package net.tfobz.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.tfobz.algorithm.AStarAlgorithm;
import net.tfobz.algorithm.Node;

/**
 * 
 * @author Daniel Lechner
 * 
 *         Mithilfe dieser Klasse, kann der Benutzer das Feld aufbauen und
 *         Hindernisse setzten, sowie Start und Ziel angeben
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	// Diagonale Bewegungen
	private boolean diag;
	// Container fuer die ganzen Elemnte
	private Container border;
	// Container fuer das Button Grid
	private Container buttonGrid;
	// Button Array zum aufbauen des Feldes
	private JButton[][] buttonArr;
	// Flag, ob der Startpunkt gesetzt wurde
	private boolean startSet = false;
	// Flag, ob der Zielpunkt gesetzt wurde
	private boolean endSet = false;
	// Button zum starten des Algorithmus
	private JButton start;
	// Start Node
	private Node startP = new Node(null, -1, -1, Integer.MAX_VALUE, 0);
	// Ziel Node
	private Node endP = new Node(null, -1, -1, Integer.MAX_VALUE, 0);
	// Button um die Vorlage zu speichern
	private JButton save;

	/**
	 * Konstruktor der GUI zum aufbauen der Wegsuche. Enthaelt ebenfalls die Buttons
	 * Save und Start. Save speichert die Vorlage als CSV Datei im Dokumente
	 * Verzeichnis, Start startet den AStern Algorithmus
	 * 
	 * @param size     Groesse der Node Array
	 * @param diagonal diagonale Bewegungen
	 */
	public GUI(int size, boolean diagonal) {
		setTitle("BUILD");
		setSize(750, 750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		diag = diagonal;
		buttonArr = new JButton[size][size];
		buttonGrid = new Container();
		border = new Container();
		border.setLayout(new BorderLayout());
		border.add(buttonGrid, BorderLayout.CENTER);
		save = new JButton("SAVE");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int ret = saveFile();
				if (ret == 0) {
					JOptionPane.showConfirmDialog(null, "File was successfully saved", "Confirm",
							JOptionPane.DEFAULT_OPTION);
				} else if (ret == -1) {
					JOptionPane.showMessageDialog(null, "You can't save without having set a Start- and End-Point",
							"WARNING", JOptionPane.WARNING_MESSAGE);
				} else if (ret == -3) {
					JOptionPane.showMessageDialog(null, "Only .txt or .csv Files", "dismiss",
							JOptionPane.ERROR_MESSAGE);
				} else if (ret == -4) {
					JOptionPane.showMessageDialog(null, "You have to add the Extension .txt or .csv to save the Layout",
							"Ok", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		border.add(save, BorderLayout.NORTH);
		start = new JButton("START");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (startSet && endSet) {
					dispose();
					Runnable r = new AStarAlgorithm(convertArr(buttonArr), startP.getX(), startP.getY(), endP.getX(),
							endP.getY(), diag);
					Thread t = new Thread(r);
					t.start();
				}
			}
		});
		border.add(start, BorderLayout.SOUTH);
		buttonGrid.setLayout(new GridLayout(size, size));
		for (int j = 0; j < buttonArr[0].length; j++) {
			for (int i = 0; i < buttonArr.length; i++) {
				buttonArr[i][j] = new JButton("");
				buttonArr[i][j].setBackground(Color.WHITE);
				buttonArr[i][j].addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3) {
							if ((((JButton) e.getSource()).getBackground().equals(Color.WHITE)
									|| ((JButton) e.getSource()).getBackground().equals(Color.GRAY))) {
								if (!startSet && !endSet) {
									startSet = true;
									((JButton) e.getSource()).setBackground(Color.GREEN);
								} else if (startSet && !endSet) {
									endSet = true;
									((JButton) e.getSource()).setBackground(Color.PINK);
								} else if (!startSet && endSet) {
									startSet = true;
									((JButton) e.getSource()).setBackground(Color.GREEN);
								}
							} else {
								if (((JButton) e.getSource()).getBackground().equals(Color.GREEN)) {
									startSet = false;
									((JButton) e.getSource()).setBackground(Color.WHITE);
									((JButton) e.getSource()).setText("");
								} else if (((JButton) e.getSource()).getBackground().equals(Color.PINK)) {
									endSet = false;
									((JButton) e.getSource()).setBackground(Color.WHITE);
									((JButton) e.getSource()).setText("");
								}
							}

						} else if (e.getButton() == MouseEvent.BUTTON1) {
							if (!(((JButton) e.getSource()).getBackground().equals(Color.GREEN))
									&& !(((JButton) e.getSource()).getBackground().equals(Color.PINK))) {
								if (((JButton) e.getSource()).getBackground().equals(Color.WHITE)) {
									((JButton) e.getSource()).setBackground(Color.GRAY);
								} else if (((JButton) e.getSource()).getBackground().equals(Color.GRAY)) {
									((JButton) e.getSource()).setBackground(Color.WHITE);
								}
							}
						}
					}

				});
				buttonGrid.add(buttonArr[i][j]);
			}
		}
		setContentPane(border);
		setVisible(true);
	}

	/**
	 * Schreibt das gezeichnete Feld in eine auswaehlbare *.csv oder *.txt Datei,
	 * worin "start" die StartNode ist, "end" die EndNode ist, "x" sind Barrieren
	 * und "o" sind normale Nodes
	 * 
	 * @return -1 falls Start- oder End-Punkt nicht gesetzt sind; -2 falls ein
	 *         Fehler mit dem FileWriter aufgetreten ist; -3 falls die ausgewaehlte
	 *         Datei keine *.csv oder *.txt Datei ist; -4 falls die angelegte Datei
	 *         keine Endung hat.
	 */
	public int saveFile() {
		int ret = -1;
		JFileChooser f = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV und TXT Dateien", "csv", "txt");
		f.setFileFilter(filter);
		if (startSet && endSet) {
			int fRet = -10;
			ret = 0;
			do {
				fRet = f.showSaveDialog(GUI.this);
			} while (fRet != JFileChooser.APPROVE_OPTION && fRet != JFileChooser.CANCEL_OPTION);
			if (fRet == JFileChooser.APPROVE_OPTION) {
				File file = f.getSelectedFile();
				boolean temp;
				try {
					temp = !file.getName().split("\\.", 2)[1].equals("csv")
							&& !file.getName().split("\\.", 2)[1].equals("txt");
				} catch (Exception e) {
					return -4;
				}
				if (temp) {
					ret = -3;
				} else {
					try (FileWriter fw = new FileWriter(file)) {
						for (int j = 0; j < buttonArr.length; j++) {
							String line = "";
							for (int i = 0; i < buttonArr.length; i++) {
								if (buttonArr[i][j].getBackground().equals(Color.WHITE)) {
									line += "o;";
								} else if (buttonArr[i][j].getBackground().equals(Color.GREEN)) {
									line += "start;";
								} else if (buttonArr[i][j].getBackground().equals(Color.PINK)) {
									line += "end;";
								} else
									line += "x;";
							}
							line += "\n";
							fw.write(line);
						}
						fw.close();
					} catch (Exception ex) {
						ex.printStackTrace();
						ret = -2;
					}
				}
			} else if (fRet == JFileChooser.ERROR_OPTION) {
				ret = -2;
			}
		} else {
			ret = -1;
		}
		return ret;
	}

	/**
	 * Konvertiert das Button Array in ein Node Array anhand der Farben der Buttons.
	 * Der Start ist gruen, das Ziel ist Pink, eine barriere grau und ein Knoten
	 * weiss
	 * 
	 * @param arr Button Array
	 * @return Node Array
	 */
	public Node[][] convertArr(JButton[][] arr) {

		Node[][] ret = new Node[arr.length][arr[0].length];

		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret[i].length; j++) {
				if (arr[i][j].getBackground().equals(Color.WHITE)) {
					ret[i][j] = new Node(null, i, j, 0, Integer.MAX_VALUE);
				} else if (arr[i][j].getBackground().equals(Color.GRAY)) {
					ret[i][j] = null;
				} else if (arr[i][j].getBackground().equals(Color.GREEN)) {
					ret[i][j] = new Node(null, i, j, 0, Integer.MAX_VALUE);
					startP = new Node(null, i, j, Integer.MAX_VALUE, 0);
				} else if (arr[i][j].getBackground().equals(Color.PINK)) {
					ret[i][j] = new Node(null, i, j, 0, Integer.MAX_VALUE);
					endP = new Node(null, i, j, Integer.MAX_VALUE, 0);
				}
			}
		}
		return ret;
	}
}