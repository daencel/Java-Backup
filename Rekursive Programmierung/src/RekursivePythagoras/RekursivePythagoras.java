
package RekursivePythagoras;

import java.awt.Graphics;
import javax.swing.JFrame;

public class RekursivePythagoras extends JFrame {

	private static final long serialVersionUID = 1L;
	private Turtle t = null;

	/**
	 * Konstruktor
	 */
	public RekursivePythagoras() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 1500);
		setResizable(false);
		repaint();
		setVisible(true);
	}

	/**
	 * Erstellt den Baum
	 * 
	 * @param l Laenge des ersten Quadrats
	 */
	public void erstelleBaum(double l) {
		t.vor(l);
		t.drehe(-90);
		t.vor(l);
		t.drehe(-90);
		t.vor(l);
		t.drehe(-90);
		t.vor(l);
		t.drehe(-90);
		if (l > 1) {
			t.vor(l);
			t.drehe(30);
			erstelleBaum(Math.cos(Math.toRadians(30)) * l);
			t.drehe(90);
			erstelleBaum((Math.sin(Math.toRadians(30)) * l));
			t.stiftHoch();
			t.drehe(60);
			t.vor(l);
			t.stiftRunter();

		} else {
			t.stiftHoch();
			t.drehe(-90);
			t.vor(l);
			t.drehe(-90);
			t.stiftRunter();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		t = new Turtle(this, getWidth() / 2 + 110, getHeight() - 300, 90);
		erstelleBaum(210);
	}

	public static void main(String[] args) {
		new RekursivePythagoras();
	}

}
