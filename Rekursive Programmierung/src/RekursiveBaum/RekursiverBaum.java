package RekursiveBaum;

import java.awt.Graphics;

import javax.swing.JFrame;

public class RekursiverBaum extends JFrame {

	private static final long serialVersionUID = 1L;
	private Turtle t = null;

	/**
	 * Constructer
	 */
	public RekursiverBaum() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 1500);
		setResizable(false);
		repaint();
		setVisible(true);
	}

	/**
	 * Rekursive Methode, welche den Baum zeichnet
	 * 
	 * @param l Laenge des Stammes
	 */
	public void erstelleBaum(int l) {
		if (l > 1) {
			t.vor(l);
			t.drehe(30);
			erstelleBaum((int) (l * 0.75));
			t.drehe(-60);
			erstelleBaum((int) (l * 0.75));
			t.drehe(30);
			t.stiftHoch();
			t.drehe(180);
			t.vor(l);
			t.drehe(180);
			t.stiftRunter();
		}
	}

	/**
	 * Zeichnet den Baum
	 */
	public void paint(Graphics g) {
		super.paint(g);
		t = new Turtle(this, getWidth() / 2, getHeight() - 100, 90);
		erstelleBaum(300);
	}

	public static void main(String[] args) {
		new RekursiverBaum();
	}

}
