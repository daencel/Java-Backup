package MonteCarlo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonteCarlo extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedImage bf;
	private double counter = 0;
	private double pi;
	private double n = 0;

	public static void main(String[] args) {
		new MonteCarlo();
	}

	public MonteCarlo() {
		super("Monte Carlo");
		setSize(1000, 1100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

		setVisible(true);
	}

	/**
	 * ruft zeichne auf und gibt das Buffered image aus
	 */
	public void paint(Graphics g) {
		run(bf.getGraphics());
		g.drawImage(bf, 0, 0, null);
		repaint();
	}

	public void run(Graphics g) {

		g.setColor(Color.WHITE);

		g.drawLine(0, 100, getWidth(), 100);
		g.drawOval(0, 100, 1000, 1000);

		n++;

		double x = Math.random();
		double y = Math.random();
		double z = x * x + y * y;

		g.fillRect((int) (x * 1000), (int) (y * 1000) + 100, 1, 1);

		if (z <= 1) {
			counter++;
		}

		if (counter != 0) {
			pi = 4.0 * (counter / n);
		}
		
		JPanel panel = (JPanel) getContentPane();
	    panel.setLayout(null);

		double pi2 = Math.round(pi * 100000.0) / 100000.0;
		JLabel label = new JLabel(pi2 + "");
		label.setBounds(10, 70, 700, 70);
		label.setFont(new Font("Courier New", Font.PLAIN, 70));
		panel.add(label);
		
		System.out.println(pi2);
	}
}
