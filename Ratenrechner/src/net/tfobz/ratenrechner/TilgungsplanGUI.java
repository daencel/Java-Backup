package net.tfobz.ratenrechner;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class TilgungsplanGUI extends JFrame {

	// Serial Version UID
	private static final long serialVersionUID = 1L;
	// Content Pane
	private JPanel contentPane;
	// HTML String
	private String htmlString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String test = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<body>\r\n" + "\r\n" + "<h1>My First Heading</h1>\r\n"
				+ "\r\n" + "<p>My first paragraph.</p>\r\n" + "\r\n" + "</body>\r\n" + "</html>";
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TilgungsplanGUI frame = new TilgungsplanGUI(test);
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
	public TilgungsplanGUI(String html) {
		htmlString = html;
		setTitle("Tilgungsplan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 700);
		setLocationRelativeTo(null);
		setVisible(true);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel savePanel = new JPanel();
		savePanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(savePanel, BorderLayout.SOUTH);
		savePanel.setLayout(new GridLayout(0, 3));

		JLabel null1 = new JLabel();
		savePanel.add(null1);

		JLabel null2 = new JLabel();
		savePanel.add(null2);

		JButton save = new JButton("save");
		save.setMnemonic('s');
		save.setFont(new Font("Tahoma", Font.PLAIN, 18));
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int ret;
				do {
					ret = showSaveDialog();
					if (ret == -1) {
						JOptionPane.showMessageDialog(TilgungsplanGUI.this, "Kein Dateinamen angegeben", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else if (ret == -2) {
						JOptionPane.showMessageDialog(TilgungsplanGUI.this, "Dateiname muss Endung .html haben",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} while (ret != 0);
			}
		});
		savePanel.add(save);

		JEditorPane jEditorPane = new JEditorPane();
		jEditorPane.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(jEditorPane);

		contentPane.add(scrollPane, BorderLayout.NORTH);
		contentPane.add(scrollPane);

		HTMLEditorKit kit = new HTMLEditorKit();
		jEditorPane.setEditorKit(kit);

		Document doc = kit.createDefaultDocument();
		jEditorPane.setDocument(doc);
		jEditorPane.setText(html);

		jEditorPane.setCaretPosition(0);
	}

	/**
	 * Oeffnet den FileChooser
	 * 
	 * @return 0 falls erfolgreich; -1 falls der Name leer ist; -2 falls die Endung
	 *         eine Andere als .hmtl ist
	 */
	private int showSaveDialog() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML Dateien", "html");
		fc.setFileFilter(filter);
		fc.setDialogTitle("Choose a Name and Directory to save file");
		int userSelection = fc.showSaveDialog(TilgungsplanGUI.this);

		String filepath = null;
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			filepath = file.getAbsolutePath();
			if (file.getName().equals("")) {
				return -1;
			}
			try {
				if (!file.getName().split("\\.", 2)[1].equals("html")) {
					return -2;
				}
			} catch (Exception e) {
				filepath = file.getAbsolutePath() + ".html";
			}
			File tmp = new File(filepath);
			boolean exists = false;
			if (tmp.exists()) {
				exists = true;
				int input = JOptionPane.showConfirmDialog(TilgungsplanGUI.this,
						"Es existiert bereits eine Datei mit dem selben namen. Moechten Sie diese ueberschreiben?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (input == JOptionPane.OK_OPTION) {
					writeFile(filepath);
				}
			}
			if (!exists) {
				writeFile(filepath);
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param path
	 */
	private void writeFile(String path) {
		try (FileWriter fw = new FileWriter(path)) {
			fw.write(htmlString);
			fw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(TilgungsplanGUI.this, "Error while saving the file", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}