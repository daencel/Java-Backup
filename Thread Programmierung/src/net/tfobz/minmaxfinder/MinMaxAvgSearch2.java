package net.tfobz.minmaxfinder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;

public class MinMaxAvgSearch2 extends JFrame {

	// Serial Version ID
	private static final long serialVersionUID = 1L;
	// Content Pane
	private JPanel contentPane;
	// Feld der Random Zahlen
	private static int[] field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Random ran = new Random();
		field = new int[50000000];
		for (int i = 0; i < field.length; i++) {
			field[i] = ran.nextInt(Integer.MAX_VALUE - 1);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinMaxAvgSearch2 frame = new MinMaxAvgSearch2();
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
	public MinMaxAvgSearch2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(918, 342);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(btnPanel, BorderLayout.NORTH);

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 3, 40, 40));
		contentPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

		JLabel lblMin = new JLabel("Min:");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblMin);

		JTextField txtMin = new JTextField();
		txtMin.setEditable(false);
		contentPanel.add(txtMin);

		JProgressBar pbMin = new JProgressBar();
		pbMin.setStringPainted(true);
		contentPanel.add(pbMin);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblMax);

		JTextField txtMax = new JTextField();
		txtMax.setEditable(false);
		contentPanel.add(txtMax);

		JProgressBar pbMax = new JProgressBar();
		pbMax.setStringPainted(true);
		contentPanel.add(pbMax);

		JLabel lblAvg = new JLabel("Avg:");
		lblAvg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAvg.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(lblAvg);

		JTextField txtAvg = new JTextField();
		txtAvg.setEditable(false);
		contentPanel.add(txtAvg);

		JProgressBar pbAvg = new JProgressBar();
		pbAvg.setStringPainted(true);
		contentPanel.add(pbAvg);

		JButton btnStartSearching = new JButton("start searching");
		btnPanel.add(btnStartSearching);
		btnStartSearching.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FindMin(field, txtMin, pbMin);
				new FindMax(field, txtMax, pbMax);
				new FindAvg(field, txtAvg, pbAvg);
			}
		});
	}

}
