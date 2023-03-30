package net.tfobz.ratenrechner;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.*;

/**
 * @author Daniel Lechner
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Boolean um zu sehen ob man gerade Barwert bearbeitet hat
	 */
	private boolean bBarwert = false;
	/**
	 * Boolean um zu sehen ob man gerade Laufzeit bearbeitet hat
	 */
	private boolean bLaufzeit = false;
	/**
	 * Boolean um zu sehen ob man gerade raten bearbeitet hat
	 */
	private boolean bRate = true;
	/**
	 * TextField Barwert
	 */
	private JTextField txtBarwert;
	/**
	 * TextField Jahreszinssatz
	 */
	private JTextField txtJahreszinssatz;
	/**
	 * JComboBox Raten pro Jahr
	 */
	private JComboBox<String> ratenProJahr;
	/**
	 * TextField Rate
	 */
	private JTextField txtRate;
	/**
	 * TextField Laufzeit in Jahren
	 */
	private JTextField txtLaufzeitInJahren;
	/**
	 * Radio Button nachschuessig
	 */
	private JRadioButton nachschuessig;
	/**
	 * Radio Button vorschuessig
	 */
	private JRadioButton vorschuessig;
	/**
	 * Key Listener fuer alt Befehle
	 */
	private KeyListener alt = new Listener();

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
	 * Erstellt die GUI und implementiert alle Mathoden
	 */
	private GUI() {
		setTitle("Ratenrechner");
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 443);
		setLocationRelativeTo(null);
		addKeyListener(alt);

		RatenRechner rr = new RatenRechner();

		// Content Pane
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(10, 10, 5, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
		contentPanel.setLayout(new GridLayout(0, 3, 30, 30));

		JLabel lblBarwert = new JLabel("<html><u>B</u>arwert</html>:");
		contentPanel.add(lblBarwert);
		lblBarwert.setHorizontalAlignment(SwingConstants.RIGHT);

		txtBarwert = new JTextField("50000");
		txtBarwert.addKeyListener(alt);
		contentPanel.add(txtBarwert);
		txtBarwert.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBarwert.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				bBarwert = true;
				bRate = false;
				bLaufzeit = false;
			}

		});

		JButton btnBerechneBarwert = new JButton("Berechne Barwert");
		btnBerechneBarwert.addKeyListener(alt);
		btnBerechneBarwert.setMnemonic('e');
		contentPanel.add(btnBerechneBarwert);

		JLabel lblJahreszinssatz = new JLabel("<html><u>J</u>ahreszinssatz</html>");
		contentPanel.add(lblJahreszinssatz);
		lblJahreszinssatz.setHorizontalAlignment(SwingConstants.RIGHT);

		txtJahreszinssatz = new JTextField("5");
		txtJahreszinssatz.addKeyListener(alt);
		contentPanel.add(txtJahreszinssatz);
		txtJahreszinssatz.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel null1 = new JLabel();
		contentPanel.add(null1);

		JLabel lblLaufzeitInJahren = new JLabel("<html><u>L</u>aufzeit in Jahren</html>");
		contentPanel.add(lblLaufzeitInJahren);
		lblLaufzeitInJahren.setHorizontalAlignment(SwingConstants.RIGHT);

		txtLaufzeitInJahren = new JTextField("10");
		txtLaufzeitInJahren.addKeyListener(alt);
		contentPanel.add(txtLaufzeitInJahren);
		txtLaufzeitInJahren.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLaufzeitInJahren.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				bBarwert = false;
				bRate = false;
				bLaufzeit = true;
			}

		});

		JButton btnBerechneLaufzeitInJahren = new JButton("Berechne Laufzeit in Jahren");
		btnBerechneLaufzeitInJahren.addKeyListener(alt);
		btnBerechneLaufzeitInJahren.setMnemonic('c');
		contentPanel.add(btnBerechneLaufzeitInJahren);

		JLabel lblRatenProJahr = new JLabel("<html>Raten pr<u>o</u> Jahr</html>");
		contentPanel.add(lblRatenProJahr);
		lblRatenProJahr.setHorizontalAlignment(SwingConstants.RIGHT);

		ratenProJahr = new JComboBox<String>();
		ratenProJahr.addKeyListener(alt);
		contentPanel.add(ratenProJahr);
		ratenProJahr.setModel(
				new DefaultComboBoxModel<String>(new String[] { "12 Raten", "6 Raten", "4 Raten", "1 Rate" }));

		JLabel null2 = new JLabel();
		contentPanel.add(null2);

		JLabel lblRate = new JLabel("<html><u>R</u>ate");
		contentPanel.add(lblRate);
		lblRate.setHorizontalAlignment(SwingConstants.RIGHT);

		txtRate = new JTextField("528.13");
		txtRate.addKeyListener(alt);
		contentPanel.add(txtRate);
		txtRate.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRate.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				bBarwert = false;
				bRate = true;
				bLaufzeit = false;
			}

		});

		JButton btnBerechneRate = new JButton("Berechne Rate");
		btnBerechneRate.addKeyListener(alt);
		btnBerechneRate.setMnemonic('a');
		contentPanel.add(btnBerechneRate);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setLayout(new FlowLayout(SwingConstants.RIGHT));
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		JButton btnZeigeTilgungsplan = new JButton("ZEIGE TILGUNGSPLAN");
		btnZeigeTilgungsplan.setMnemonic('z');
		btnZeigeTilgungsplan.addKeyListener(alt);
		btnZeigeTilgungsplan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonPanel.add(btnZeigeTilgungsplan);

		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblRatenrechner = new JLabel("Ratenrechner");
		northPanel.add(lblRatenrechner, BorderLayout.NORTH);
		lblRatenrechner.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblRatenrechner.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel radioPanel = new JPanel();
		radioPanel.setBackground(Color.LIGHT_GRAY);
		northPanel.add(radioPanel, BorderLayout.SOUTH);

		vorschuessig = new JRadioButton("<html><u>v</u>orschuessig</html>");
		vorschuessig.addKeyListener(alt);
		vorschuessig.setSelected(true);
		vorschuessig.setBackground(Color.LIGHT_GRAY);
		radioPanel.add(vorschuessig);

		nachschuessig = new JRadioButton("<html><u>n</u>achschuessig</html>");
		nachschuessig.addKeyListener(alt);
		nachschuessig.setBackground(Color.LIGHT_GRAY);
		radioPanel.add(nachschuessig);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(nachschuessig);
		radioGroup.add(vorschuessig);

		btnZeigeTilgungsplan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rr.setBarwert(txtBarwert.getText());
					rr.setJahreszinssatz(txtJahreszinssatz.getText());
					rr.setLaufzeitInJahren(txtLaufzeitInJahren.getText());
					rr.setNachschuessig(nachschuessig.isSelected());
					rr.setRate(txtRate.getText());
					rr.setRatenProJahr(ratenProJahr.getSelectedItem().toString());

					if (bBarwert) {
						String laufzeit = rr.calcLaufzeitInJahren();
						txtLaufzeitInJahren.setText(laufzeit);
						rr.setLaufzeitInJahren(laufzeit);

						String raten = rr.calcRaten();
						txtRate.setText(raten);
						rr.setRate(raten);

						String barwert = rr.calcBarwert();
						txtBarwert.setText(barwert);
						rr.setBarwert(barwert);
					} else if (bLaufzeit) {
						String raten = rr.calcRaten();
						txtRate.setText(raten);
						rr.setRate(raten);

						String barwert = rr.calcBarwert();
						txtBarwert.setText(barwert);
						rr.setBarwert(barwert);

						String laufzeit = rr.calcLaufzeitInJahren();
						txtLaufzeitInJahren.setText(laufzeit);
						rr.setLaufzeitInJahren(laufzeit);
					} else if (bRate) {
						String barwert = rr.calcBarwert();
						txtBarwert.setText(barwert);
						rr.setBarwert(barwert);

						String laufzeit = rr.calcLaufzeitInJahren();
						txtLaufzeitInJahren.setText(laufzeit);
						rr.setLaufzeitInJahren(laufzeit);

						String raten = rr.calcRaten();
						txtRate.setText(raten);
						rr.setRate(raten);
					}
					try {
						String tilgungsplan = rr.getTilgungsplan();
						setVisible(false);
						dispose();
						new TilgungsplanGUI(tilgungsplan);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(GUI.this, "Nicht alle Felder wurden richtig oder mit 0 gefuellt",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(GUI.this, "Nicht alle Felder wurden richtig oder mit 0 gefuellt",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnBerechneBarwert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rr.setJahreszinssatz(txtJahreszinssatz.getText());
					rr.setLaufzeitInJahren(txtLaufzeitInJahren.getText());
					rr.setNachschuessig(nachschuessig.isSelected());
					rr.setRate(txtRate.getText());
					rr.setRatenProJahr(ratenProJahr.getSelectedItem().toString());
					String barwert = rr.calcBarwert();
					txtBarwert.setText(barwert);
					rr.setBarwert(barwert);
				} catch (RatenRechnerException e1) {
					JOptionPane.showMessageDialog(GUI.this, "Nicht alle Felder wurden richtig oder mit 0 gefuellt",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnBerechneLaufzeitInJahren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rr.setBarwert(txtBarwert.getText());
					rr.setJahreszinssatz(txtJahreszinssatz.getText());
					rr.setNachschuessig(nachschuessig.isSelected());
					rr.setRate(txtRate.getText());
					rr.setRatenProJahr(ratenProJahr.getSelectedItem().toString());
					String laufzeit = rr.calcLaufzeitInJahren();
					txtLaufzeitInJahren.setText(laufzeit);
					rr.setLaufzeitInJahren(laufzeit);
				} catch (RatenRechnerException e1) {
					JOptionPane.showMessageDialog(GUI.this, "Nicht alle Felder wurden richtig oder mit 0 gefuellt",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnBerechneRate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rr.setBarwert(txtBarwert.getText());
					rr.setJahreszinssatz(txtJahreszinssatz.getText());
					rr.setNachschuessig(nachschuessig.isSelected());
					rr.setRatenProJahr(ratenProJahr.getSelectedItem().toString());
					rr.setLaufzeitInJahren(txtLaufzeitInJahren.getText());
					String raten = rr.calcRaten();
					txtRate.setText(raten);
					rr.setRate(raten);
				} catch (RatenRechnerException e1) {
					JOptionPane.showMessageDialog(GUI.this, "Nicht alle Felder wurden richtig oder mit 0 gefuellt",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public class Listener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			char temp = e.getKeyChar();
			if (e.isAltDown()) {
				if (temp == 'b') {
					txtBarwert.requestFocusInWindow();
					txtBarwert.selectAll();
				} else if (temp == 'j') {
					txtJahreszinssatz.requestFocusInWindow();
					txtJahreszinssatz.selectAll();
				} else if (temp == 'l') {
					txtLaufzeitInJahren.requestFocusInWindow();
					txtLaufzeitInJahren.selectAll();
				} else if (temp == 'o') {
					ratenProJahr.requestFocusInWindow();
					ratenProJahr.showPopup();
				} else if (temp == 'r') {
					txtRate.requestFocusInWindow();
					txtRate.selectAll();
				} else if (temp == 'n') {
					nachschuessig.requestFocusInWindow();
					nachschuessig.setSelected(true);
				} else if (temp == 'v') {
					vorschuessig.requestFocusInWindow();
					vorschuessig.setSelected(true);
				} else if (temp == 'z') {

				}
			}
		}
	}
}