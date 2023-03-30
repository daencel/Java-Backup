package net.tfobz.minmaxfinder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class MinMaxAvgSearch extends JFrame {

	// Serial Version ID
	private static final long serialVersionUID = 1L;
	// Content Pane
	private JPanel contentPane;
	// Feld der Random Zahlen
	private static int[] field;
	// Text Field des Averagewertes
	private JTextField txtMin;
	// Text Field des Minimalwertes
	private JTextField txtMax;
	// Text Field des Maximalwertes
	private JTextField txtAvg;

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
					MinMaxAvgSearch frame = new MinMaxAvgSearch();
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
	public MinMaxAvgSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(918, 342);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMin.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMax.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblAvg = new JLabel("Avg:");
		lblAvg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAvg.setHorizontalAlignment(SwingConstants.RIGHT);

		txtMin = new JTextField();
		txtMin.setEditable(false);
		txtMin.setColumns(10);

		txtMax = new JTextField();
		txtMax.setEditable(false);
		txtMax.setColumns(10);

		txtAvg = new JTextField();
		txtAvg.setEditable(false);
		txtAvg.setColumns(10);

		JProgressBar pbMin = new JProgressBar();
		pbMin.setStringPainted(true);

		JProgressBar pbMax = new JProgressBar();
		pbMax.setStringPainted(true);

		JProgressBar pbAvg = new JProgressBar();
		pbAvg.setStringPainted(true);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblMax, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(lblMin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAvg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtAvg, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE).addComponent(txtMax)
						.addComponent(txtMin))
				.addGap(30)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(pbMin, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)
						.addComponent(pbMax, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)
						.addComponent(pbAvg, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(29, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(54)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(pbMin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(Alignment.LEADING,
								gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblMin).addComponent(
										txtMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(37)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblMax).addComponent(
								txtMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addComponent(pbMax, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
				.addGap(42)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(pbAvg, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblAvg).addComponent(
								txtAvg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(74, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
				JButton start = new JButton("Start searching");
				start.setFont(new Font("Tahoma", Font.PLAIN, 17));
				panel_1.add(start);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FindMin(field, txtMin, pbMin);
				new FindMax(field, txtMax, pbMax);
				new FindAvg(field, txtAvg, pbAvg);
			}
		});
	}
}
