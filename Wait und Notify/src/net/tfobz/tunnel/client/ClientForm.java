package net.tfobz.tunnel.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

/**
 * Diese Klasse erstellt die Benutzerschnittstelle und den GuidesMonitor zur
 * Verwaltung der Gruppenführer pro Eingang. Sie enthält auch die
 * Ereignisbehandlungsmethoden für die beiden Knöpfe. In diesen Methoden werden
 * die Objekte vom Typ ClientThread zur Behandlung der Clientanfragen angelegt
 * und die Threads gestartet.<br>
 * <br>
 * <b>Ereignisbehandlungsmethode Besichtigung anfordern</b><br>
 * Diese Methode kontrolliert zuerst, ob eine Besucherzahl ins Textfeld
 * eingegeben wurde und konvertiert den Inhalt in eine Zahl. Diese Zahl darf
 * nicht größer sein als das maximale Fassungsvermögen des Tunnels. Dann wird
 * der ClientThread gestartet, dem diese Besucheranzahl und die Referenzen auf
 * das ClientForm sowie auf den GuidesMonitor übergeben werden.<br>
 * <br>
 * <b>Ereignisbehandlungsmethode Besichtigung beenden</b><br>
 * Zuerst wird kontrolliert ob es überhaupt Aktive Besichtigungen gibt, welche
 * von diesem Eingang aus den Tunnel betreten haben. Sind solche vorhanden, dann
 * wird kontrolliert, ob eine aktive Besichtigung ausgewählt wurde. Ist dies der
 * Fall so wird aus dem Text des ausgewählten JList-Eintrages die Anzahl der
 * Besucher ermittelt und in eine Zahl konvertiert. Dann wird der ClientThred
 * gestartet, dem diese negative (!) Anzahl und Referenzen auf ClientForm und
 * GuidesMonitor übergeben werden
 */
public class ClientForm extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Monitor durch welchen am Eingang ein Führer reserviert werden kann
	 */
	private GuidesMonitor guidesMonitor = null;
	/**
	 * Modell zur Verwaltung der Inhalte der JList
	 */
	protected DefaultListModel<String> mActiveVisits = null;
	/**
	 * Ausgabe der Statusmeldungen
	 */
	private JEditorPane consol;
	/**
	 * Label der verfügbaren Besucher
	 */
	public JLabel availableVisitors;
	/**
	 * label der vefügbaren Guides
	 */
	public JLabel available;

	/**
	 * Legt das Formular an und macht es sichtbar. Beim Anlegen des Forumulas wird
	 * auch der GuidesMonitor angelegt. Nachdem das Formular angelegt wurde, werden
	 * in Abständen von einer Sekunde Serveranfragen geschickt zur Ermittlung der
	 * verfügbaren Besucher, d. h. der Server antwortet und liefert die Anzahl je
	 * Besucheranzahl zurück die noch in den Tunnel eingelassen werden kann
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientForm one = new ClientForm(2);
					one.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientForm(int entrance) {
		guidesMonitor = new GuidesMonitor(this);
		mActiveVisits = new DefaultListModel<String>();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(700, 700);
		setLocationRelativeTo(null);
		setTitle("Entrance " + entrance);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[30%][70%]", "[grow]"));

		JPanel west = new JPanel();
		contentPane.add(west, "cell 0 0,grow");
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));

		JLabel lblEntrance = new JLabel("Entrance " + entrance);
		lblEntrance.setFont(new Font("Serif", Font.BOLD, 24));
		west.add(lblEntrance);

		JPanel rVisit = new JPanel();
		rVisit.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		west.add(rVisit);
		rVisit.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow][grow]"));

		JLabel lblavailable = new JLabel("Available guides:");
		lblavailable.setFont(new Font("Serif", Font.PLAIN, 14));
		rVisit.add(lblavailable, "cell 0 0");

		available = new JLabel("4");
		available.setFont(new Font("Serif", Font.PLAIN, 14));
		rVisit.add(available, "cell 1 0,alignx trailing");

		JLabel lblvisitors = new JLabel("Visitors:");
		lblvisitors.setFont(new Font("Serif", Font.PLAIN, 14));
		rVisit.add(lblvisitors, "cell 0 1,alignx leading");

		JTextField visitors = new JTextField();
		rVisit.add(visitors, "cell 1 1,growx");
		visitors.setColumns(10);

		JButton btnrequest = new JButton("Request visit");
		btnrequest.setMnemonic('r');
		btnrequest.addActionListener(r -> {
			try {
				int anzahl = Integer.parseInt(visitors.getText());
				visitors.setText("");
				if (anzahl > 0)
					new ClientThread(anzahl, this, guidesMonitor, entrance);
			} catch (NumberFormatException e) {
			}
		});
		btnrequest.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					int anzahl = Integer.parseInt(visitors.getText());
					if (anzahl > 0)
						btnrequest.setBackground(Color.green);
				} catch (NumberFormatException e1) {
					btnrequest.setBackground(Color.red);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnrequest.setBackground(new JButton().getBackground());
			}

		});
		rVisit.add(btnrequest, "cell 0 2 2 1,growx");

		JPanel aVisit = new JPanel();
		aVisit.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		west.add(aVisit);
		aVisit.setLayout(new MigLayout("", "[grow]", "[20%][grow][20%]"));

		JLabel lblActiveVisits = new JLabel("Active visits:");
		lblActiveVisits.setFont(new Font("Serif", Font.PLAIN, 14));
		lblActiveVisits.setHorizontalAlignment(SwingConstants.LEFT);
		aVisit.add(lblActiveVisits, "cell 0 0,alignx left,aligny center");

		JList<String> list = new JList<String>();
		list.setModel(mActiveVisits);
		JScrollPane scroll = new JScrollPane(list);
		aVisit.add(scroll, "cell 0 1,grow");

		JButton btnFinishVisit = new JButton("Finish visit");
		btnFinishVisit.setMnemonic('f');
		btnFinishVisit.addActionListener(e -> {
			int ret = Integer.parseInt(list.getSelectedValue().replaceAll("(\\d+).+", "$1"));
			new ClientThread(-ret, this, guidesMonitor, entrance);
		});
		aVisit.add(btnFinishVisit, "cell 0 2,growx,aligny center");

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		west.add(panel);
		panel.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));

		JLabel lblAvailableVisitors = new JLabel("Available Visitors: ");
		lblAvailableVisitors.setFont(new Font("Serif", Font.PLAIN, 14));
		panel.add(lblAvailableVisitors, "cell 0 0,alignx left,aligny center");

		availableVisitors = new JLabel("50");
		new Thread(() -> {
			while (true)
				try {
					Thread.sleep(1000);
					new ClientThread(0, this, guidesMonitor, entrance);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		}).start();
		availableVisitors.setFont(new Font("Serif", Font.PLAIN, 14));
		panel.add(availableVisitors, "cell 1 0,alignx trailing,aligny center");

		JPanel east = new JPanel();
		east.setBorder(new EmptyBorder(12, 7, 0, 0));
		contentPane.add(east, "cell 1 0,grow");
		east.setLayout(new BorderLayout(0, 0));

		consol = new JEditorPane();
		consol.setFont(new Font("Courier New", Font.PLAIN, 13));
		consol.setText("System ready....");
		consol.setEditable(false);
		JScrollPane scoll2 = new JScrollPane(consol);
		east.add(scoll2);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Serif", Font.PLAIN, 14));
		east.add(lblStatus, BorderLayout.NORTH);

		JButton save = new JButton("save protocol...");
		save.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save Protocol");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);

			int ret = fileChooser.showSaveDialog(this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				String path = fileToSave.getAbsolutePath();
				if (!fileToSave.getName().contains(".txt"))
					path += ".txt";
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(path));
					writer.write(consol.getText());
					writer.close();
					write("Protocol saved");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		east.add(save, BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				if (Integer.parseInt(availableVisitors.getText()) != 50) {
					String ObjButtons[] = { "Yes", "No" };
					int PromptResult = JOptionPane.showOptionDialog(ClientForm.this,
							"Are you sure you want to exit? There are still activ visits", "Entrance " + entrance,
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
					if (PromptResult == JOptionPane.YES_OPTION)
						System.exit(0);
					else
						setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				} else
					System.exit(0);
			}
		});
	}

	/**
	 * Fügt zur JListe einen neuen Trip hinzu
	 * 
	 * @param a String
	 */
	public void addVisit(String a) {
		mActiveVisits.addElement(a);
	}

	/**
	 * Löscht von der JListe einen Trip
	 * 
	 * @param a String
	 */
	public void removeVisit(String a) {
		mActiveVisits.removeElement(a);
	}

	/**
	 * Schreibt den übergebenen Text in die Konsole
	 * 
	 * @param s
	 */
	public void write(String s) {
		consol.setText(s + "\n" + consol.getText());
	}

}