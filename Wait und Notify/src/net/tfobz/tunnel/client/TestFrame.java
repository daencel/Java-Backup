package net.tfobz.tunnel.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JEditorPane;

public class TestFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField visitors;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame(1);
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
	public TestFrame(int entrance) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		JLabel available = new JLabel("4");
		available.setFont(new Font("Serif", Font.PLAIN, 14));
		rVisit.add(available, "cell 1 0,alignx trailing");

		JLabel lblvisitors = new JLabel("Visitors:");
		lblvisitors.setFont(new Font("Serif", Font.PLAIN, 14));
		rVisit.add(lblvisitors, "cell 0 1,alignx leading");

		visitors = new JTextField();
		rVisit.add(visitors, "cell 1 1,growx");
		visitors.setColumns(10);

		JButton btnrequest = new JButton("Request visit");
		rVisit.add(btnrequest, "cell 0 2 2 1,growx");

		JPanel aVisit = new JPanel();
		aVisit.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		west.add(aVisit);
		aVisit.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));

		JLabel lblActiveVisits = new JLabel("Active visits:");
		lblActiveVisits.setFont(new Font("Serif", Font.PLAIN, 14));
		lblActiveVisits.setHorizontalAlignment(SwingConstants.LEFT);
		aVisit.add(lblActiveVisits, "cell 0 0,alignx left,aligny center");

		JButton btnNewButton = new JButton("New button");
		aVisit.add(btnNewButton, "cell 0 2,growx,aligny center");

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		west.add(panel);
		panel.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));

		JLabel lblAvailableVisitors = new JLabel("Available Visitors: ");
		lblAvailableVisitors.setFont(new Font("Serif", Font.PLAIN, 14));
		panel.add(lblAvailableVisitors, "cell 0 0,alignx left,aligny center");

		JLabel availableVisitors = new JLabel("3");
		availableVisitors.setFont(new Font("Serif", Font.PLAIN, 14));
		panel.add(availableVisitors, "cell 1 0,alignx trailing,aligny center");

		JPanel east = new JPanel();
		east.setBorder(new EmptyBorder(12, 7, 0, 0));
		contentPane.add(east, "cell 1 0,grow");
		east.setLayout(new BorderLayout(0, 0));

		JEditorPane consol = new JEditorPane();
		consol.setFont(new Font("Courier New", Font.PLAIN, 13));
		consol.setText("System ready....");
		consol.setEditable(false);
		east.add(consol);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Serif", Font.PLAIN, 14));
		east.add(lblStatus, BorderLayout.NORTH);
	}

}
