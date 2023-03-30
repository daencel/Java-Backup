package net.tfobz.euroumrechner;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	// ContentPane mit den Layouts
	private JPanel contentPane;
	// Array der Waehrungen als JLabels
	private JLabel[] currency = null;
	// Array der Betraege als TextFields
	private JTextField[] amount = null;
	// Euro Umrechner
	private EuroUmrechner eu = null;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Euro Umrechner");

		eu = new EuroUmrechner();
		amount = new JTextField[eu.WAEHRUNGEN.length];
		currency = new JLabel[eu.WAEHRUNGEN.length];

		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		JPanel west = new JPanel();
		west.setBackground(Color.LIGHT_GRAY);
		contentPane.add(west, BorderLayout.WEST);
		west.setLayout(new GridLayout(eu.WAEHRUNGEN.length, 1, 10, 10));

		JPanel center = new JPanel();
		center.setBackground(Color.LIGHT_GRAY);
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new GridLayout(eu.WAEHRUNGEN.length, 1, 10, 10));

		for (int i = 0; i < eu.WAEHRUNGEN.length; i++) {
			currency[i] = new JLabel(eu.WAEHRUNGEN[i] + ":         ");
			currency[i].setHorizontalAlignment(SwingConstants.RIGHT);
			west.add(currency[i]);
		}

		for (int i = 0; i < eu.WAEHRUNGEN.length; i++) {
			eu.setWaehrung(0);
			eu.setBetrag(1);
			amount[i] = new JTextField(Math.round(eu.getBetrag(i) * 100.0) / 100.0 + "");
			amount[i].setHorizontalAlignment(SwingConstants.LEFT);
			amount[i].addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent e) {
					double temp;
					try {
						temp = Double.parseDouble(((JTextField) e.getSource()).getText());
						int index = -1;
						for (int j = 0; j < amount.length; j++) {
							if (e.getSource().equals(amount[j])) {
								index = j;
							}
						}
						calcCurrency(temp, index);
					} catch (Exception e2) {
						// nothing
					}
				}

			});
			center.add(amount[i]);
		}
	}

	/**
	 * Berechnet die Betraege der anderen Waehrungen und fuellt diese in das Feld,
	 * ausser in jenes, welches gerade bearbeitet wird
	 * 
	 * @param temp  Betrag des aktuellen Feldes
	 * @param index Index des Feldes
	 */
	private void calcCurrency(double betrag, int index) {
		eu.setWaehrung(index);
		eu.setBetrag(betrag);
		for (int i = 0; i < amount.length; i++) {
			if (i != index) {
				amount[i].setText(Math.round(eu.getBetrag(i) * 100.0) / 100.0 + "");
			}
		}
	}

}
