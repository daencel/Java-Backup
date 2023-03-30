package net.tfobz.liste;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class AutoListeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * JTextField vom Namen
	 */
	private JTextField txtName;
	/**
	 * JTextField von der Erstulassung
	 */
	private JTextField txtErstzulassung;
	/**
	 * Content Pane
	 */
	private JPanel contentPane;
	/**
	 * Meine default Liste
	 */
	private MeineDefaultListe autoliste;
	/**
	 * Mein Iterator
	 */
	private MeinIterator autoiterator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoListeGUI frame = new AutoListeGUI();
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
	public AutoListeGUI() {
		KeyListener l = new Listener();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AutoListe");
		setSize(500, 250);
		setLocationRelativeTo(null);
		setResizable(false);
		addKeyListener(l);

		autoliste = new MeineDefaultListe();
		autoiterator = autoliste.elemente();

		contentPane = new JPanel();
		contentPane.addKeyListener(l);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel south = new JPanel();
		south.setBackground(Color.LIGHT_GRAY);
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		south.addKeyListener(l);

		JButton btnNext = new JButton("Next");
		south.add(btnNext);
		btnNext.setMnemonic('x');
		btnNext.addKeyListener(l);

		JButton btnNew = new JButton("New");
		south.add(btnNew);
		btnNew.setMnemonic('w');
		btnNew.addKeyListener(l);

		JButton btnDelete = new JButton("Delete");
		south.add(btnDelete);
		btnDelete.setMnemonic('d');
		btnDelete.addKeyListener(l);

		JPanel west = new JPanel();
		west.setBackground(Color.LIGHT_GRAY);
		contentPane.add(west, BorderLayout.WEST);
		west.setLayout(new GridLayout(2, 0, 0, 30));
		west.setBorder(new EmptyBorder(40, 20, 40, 0));
		west.addKeyListener(l);

		JPanel center = new JPanel();
		center.setBackground(Color.LIGHT_GRAY);
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new GridLayout(2, 0, 0, 30));
		center.setBorder(new EmptyBorder(40, 20, 40, 100));
		center.addKeyListener(l);

		JLabel lblName = new JLabel("<html><u>N</u>ame: </html>");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		west.add(lblName);
		lblName.addKeyListener(l);

		JLabel lblErstzulassung = new JLabel("<html><u>E</u>rstzulassung: </html>");
		lblErstzulassung.setHorizontalAlignment(SwingConstants.RIGHT);
		west.add(lblErstzulassung);
		lblErstzulassung.addKeyListener(l);

		txtName = new JTextField();
		center.add(txtName);
		txtName.addKeyListener(l);

		txtErstzulassung = new JTextField();
		center.add(txtErstzulassung);
		txtErstzulassung.addKeyListener(l);

		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (autoliste.istLeer())
					JOptionPane.showMessageDialog(AutoListeGUI.this, "Die Liste ist leer", "Information",
							JOptionPane.INFORMATION_MESSAGE);
				else {
					if (autoiterator.hatNaechstesElement()) {
						Auto temp = (Auto) autoiterator.naechstesElement();
						autoiterator.setzenAktuellesElement(temp);
						txtName.setText(temp.getName());
						txtErstzulassung.setText(temp.getErstzulassung() + "");
					} else {
						JOptionPane.showMessageDialog(AutoListeGUI.this, "Ende der Liste wurde erreicht", "Information",
								JOptionPane.INFORMATION_MESSAGE);
						Auto temp = (Auto) autoiterator.naechstesElement();
						autoiterator.setzenAktuellesElement(temp);
						txtName.setText(temp.getName());
						txtErstzulassung.setText(temp.getErstzulassung() + "");
					}
				}
			}
		});

		btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (autoliste.istLeer()) {
						autoliste = new MeineDefaultListe();
						autoiterator = autoliste.elemente();
						Auto temp = new Auto(txtName.getText(), Integer.parseInt(txtErstzulassung.getText()));
						autoiterator.einfuegenElement(temp);
						txtErstzulassung.setText("");
						txtName.setText("");
					} else {
						Auto temp = new Auto(txtName.getText(), Integer.parseInt(txtErstzulassung.getText()));
						autoiterator.einfuegenElement(temp);
						if (autoiterator.hatNaechstesElement()) {
							Auto temp2 = (Auto) autoiterator.naechstesElement();
							autoiterator.setzenAktuellesElement(temp2);
							txtErstzulassung.setText(temp2.getErstzulassung() + "");
							txtName.setText(temp2.getName());
						} else {
							txtErstzulassung.setText("");
							txtName.setText("");
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(AutoListeGUI.this, "Nicht alle Felder wurden richtig gefuellt",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!autoliste.istLeer()) {
					boolean ret = autoiterator.loeschenAktuellesElement();
					if (!ret)
						JOptionPane.showMessageDialog(AutoListeGUI.this, "Kein aktuelles Element gesetzt", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {
						if (autoiterator.hatNaechstesElement()) {
							Auto temp = (Auto) autoiterator.naechstesElement();
							txtName.setText(temp.getName());
							txtErstzulassung.setText(temp.getErstzulassung() + "");
						} else {
							txtErstzulassung.setText("");
							txtName.setText("");
						}
					}
				} else {
					JOptionPane.showMessageDialog(AutoListeGUI.this, "Liste ist bereits leers", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public class Listener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			char temp = e.getKeyChar();
			if (e.isAltDown()) {
				if (temp == 'n') {
					txtName.requestFocusInWindow();
					txtName.selectAll();
				} else if (temp == 'e') {
					txtErstzulassung.requestFocusInWindow();
					txtErstzulassung.selectAll();
				}
			}
		}
	}
}
