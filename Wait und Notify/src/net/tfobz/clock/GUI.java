package net.tfobz.clock;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setSize(400, 200);
		setLocationRelativeTo(null);
		setTitle("Digital Clock");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JDigitalClock clock = new JDigitalClock();
		contentPane.add(clock, BorderLayout.CENTER);
		new Thread(clock).start();

		JPanel buttons = new JPanel();
		contentPane.add(buttons, BorderLayout.SOUTH);

		JButton btnStop = new JButton("Stop");
		buttons.add(btnStop);
		btnStop.setMnemonic('s');
		btnStop.addActionListener(e -> {
			clock.setStopped(true);
		});

		JButton btnContinue = new JButton("Continue");
		buttons.add(btnContinue);
		btnContinue.setMnemonic('c');
		btnContinue.addActionListener(e -> {
			clock.setStopped(false);
		});
	}

}
