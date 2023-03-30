package RekursiveKreise;

import java.awt.Graphics;
import javax.swing.JFrame;

public class Kreise extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor des Fensters
	 */
	public Kreise() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 1500);
		setResizable(false);
		repaint();
		setVisible(true);
	}

	/**
	 * Zeichnet die Kreise
	 * 
	 * @param g Graphics
	 * @param x X - Position
	 * @param y Y - Position
	 * @param r Radius
	 */
	public void drawCircles(Graphics g, int x, int y, int r) {
		if (r > 15) {
			g.drawOval(x, y, r * 2, r * 2);
			drawCircles(g, x - r / 2, y + r / 2, r / 2); // links
			drawCircles(g, x + r / 2, y - r / 2, r / 2); // oben
			drawCircles(g, x + r * 3 / 2, y + r / 2, r / 2);// rechts
			drawCircles(g, x + r / 2, y + r * 3 / 2, r / 2);// unten
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int r = 350;
		drawCircles(g, getWidth() / 2 - r, getHeight() / 2 - r + 20, r);
	}

	public static void main(String[] args) {
		new Kreise();
	}

}
