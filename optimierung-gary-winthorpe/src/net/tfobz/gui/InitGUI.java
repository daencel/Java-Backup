package net.tfobz.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.tfobz.algorithm.*;

/**
 * 
 * @author Daniel Lechner, Noah Aberham
 * 
 *         Diese Klasse dien dazu, am Anfang die Voreinstellugen des Programms
 *         mithilfe einer GUI vorzunehmen
 * 
 */
public class InitGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	// Conatiner mit den Elementen
	private Container init;
	// Slider zum einstellen der Groesse
	private JSlider slider;
	// Check Box für diagonale Bewegungen
	private JCheckBox diagBox;
	// Button zum Starten den aufbauen
	private JButton build;
	// Button zum Laden von CSV Dateien
	private JButton load;
	// Punkt fuer den Start und das Ende
	private Point start, end;

	public static void main(String[] args) {
		new InitGUI();
	}

	/**
	 * Konstruktor der GUI, mit der man die Voreinstellungen bearbeiten kann
	 */
	public InitGUI() {
		setTitle("INITIALIZE");
		setSize(250, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		slider = new JSlider(5, 35, 20);
		slider.setPaintLabels(true);
		Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
		labelTable.put(5, new JLabel("5"));
		labelTable.put(20, new JLabel("20"));
		labelTable.put(35, new JLabel("35"));
		slider.setLabelTable(labelTable);
		build = new JButton("BUILD");
		diagBox = new JCheckBox("Diagonal Movements");
		init = new Container();
		init.setLayout(new FlowLayout());
		build.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GUI(slider.getValue(), diagBox.isSelected());
			}
		});

		load = new JButton("LOAD");
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JFileChooser f = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV und TXT Dateien", "csv", "txt");
				f.setFileFilter(filter);
				int ret = -10;
				do {
					ret = f.showOpenDialog(InitGUI.this);
				} while (ret != JFileChooser.APPROVE_OPTION && ret != JFileChooser.CANCEL_OPTION);
				if (ret == JFileChooser.APPROVE_OPTION) {
					Node[][] arr = convertCSV(f.getSelectedFile().getPath());
					Runnable r = new AStarAlgorithm(arr, (int) start.getX(), (int) start.getY(), (int) end.getX(),
							(int) end.getY(), diagBox.isSelected());
					Thread t = new Thread(r);
					t.start();
				} else {
					setVisible(true);
				}
			}
		});
		init.add(slider);
		init.add(diagBox);
		init.add(build);
		init.add(load);
		setContentPane(init);
		pack();
		setVisible(true);
	}

	/**
	 * Ruft die Methode readCSV auf und wandelt dann die String Array in eine Node
	 * Array um und gibt diese zurueck
	 * 
	 * @param path Pfad zur CSV Datei
	 * @return Node Array
	 */
	public Node[][] convertCSV(String path) {
		Node[][] ret;
		String[][] arr = null;
		try {
			arr = readCSV(path);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		ret = new Node[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (!arr[i][j].equals("x")) {
					if (arr[i][j].equals("start")) {
						start = new Point(j, i);
					} else if (arr[i][j].equals("end")) {
						end = new Point(j, i);
					}

					ret[j][i] = new Node(null, j, i, 0, Integer.MAX_VALUE);
				}
			}
		}
		return ret;
	}

	/**
	 * Liesst aus der CSV Datei vom uebergebenen Pfad die Daten Reihe fuer Reihe
	 * heraus und teilt diese. Das Ergebnis wird dann als Array zurueckgegeben
	 * 
	 * @param path Pfad zur CSV Datei
	 * @return String Array
	 * @throws Exception Datei wurde nicht gefunden oder es ist ein Fehler bei split
	 *                   aufgetreten
	 */
	public String[][] readCSV(String path) throws Exception {
		List<String[]> lines = new ArrayList<>();
		try (Scanner sc = new Scanner(new FileInputStream(path), "utf-8")) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] args = line.split(";");
				lines.add(args);
			}
			return lines.toArray(new String[0][0]);
		}
	}
}