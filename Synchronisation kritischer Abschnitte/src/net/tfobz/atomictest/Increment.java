package net.tfobz.atomictest;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class Increment extends Thread {

	private Int i;
	private JProgressBar bar;
	private JTextField text;

	public static void main(String[] args) {
		JFrame frame = new JFrame("IncrementAtomic");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel contentPane = new JPanel();
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel nort = new JPanel();
		contentPane.add(nort, BorderLayout.NORTH);
		JButton start = new JButton("Start incrementation");
		nort.add(start);

		JProgressBar progress = new JProgressBar();
		progress.setMaximum(2000000);
		progress.setStringPainted(true);
		contentPane.add(progress, BorderLayout.CENTER);

		JPanel south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.add(new JLabel("Result: "));

		JTextField result = new JTextField(20);
		result.setEditable(false);
		south.add(result);

		start.addActionListener(e -> {
			Int i = new Int(0);
			new Increment(i, progress, result).start();
			new Increment(i, progress, result).start();
			// new Increment(new Int(0), progress, result).start();
			// new Increment(new Int(0), progress, result).start();
		});

		frame.setVisible(true);
	}

	/**
	 * Constructor for Increment
	 * 
	 * @param i        Int value
	 * @param progress progressbar to show progress
	 * @param result   textfiel to show result
	 */
	public Increment(Int i, JProgressBar progress, JTextField result) {
		this.i = i;
		this.bar = progress;
		this.text = result;
	}

	@Override
	public void run() {
		for (int x = 0; x < 1000000; x++) {
			// synchronisation des Abschnittes stand nicht in der Übung, gehe aber davon
			// aus, dass es gefragt ist...
			synchronized (i) {
				this.i.i++;
			}
			this.bar.setValue(this.i.i);
			this.text.setText(this.i.i + "");
		}
	}
}