package RekursiveFiguren;

import java.awt.Graphics;

import javax.swing.JFrame;

public class RekursiveFiguren extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int x = 20;
	private static int y = 60;

	/**
	 * Konstruktor des Fensters
	 */
	public RekursiveFiguren() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 1300);
		setResizable(false);
		setVisible(true);
		repaint();
	}

	/**
	 * Zeichnet das Quadrat rekursiv
	 * 
	 * @param g      Graphics g
	 * @param anzahl groesse des Quadrat
	 */
	public void drawRect(Graphics g, int anzahl) {
		if (anzahl > 10) {
			g.drawRect(x, y, anzahl, anzahl);
			x += 2;
			y += 2;
			anzahl -= 4;
			drawRect(g, anzahl);
		}
	}

	/**
	 * Zeichnet den Kreis rekursiv
	 * 
	 * @param g      Graphics g
	 * @param anzahl groesse des Kreises
	 */
	public void drawCircle(Graphics g, int anzahl) {
		if (anzahl > 10) {
			g.drawOval(x, y, anzahl, anzahl);
			x += 2;
			y += 2;
			anzahl -= 4;
			drawCircle(g, anzahl);
		}
	}

	/**
	 * Zeichnet Quadrate
	 * 
	 * @param g      Graphics g
	 * @param anzahl Anzahl der Kreise
	 * @param a      Groesse der Quadrate
	 */
	public void drawSquares(Graphics g, int anzahl, int a) {
		if (anzahl > 0) {
			g.drawRect(x, y, a, a);
			x += a;
			a -= 10;
			anzahl--;
			drawSquares(g, anzahl, a);
		}
	}

	/**
	 * Zeichent ein Rohr aus Kreisen
	 * 
	 * @param g graphics g
	 * @param r Radius des Kreises
	 */
	public void drawPipe(Graphics g, int r) {
		if (x < (getWidth() - r) - 10) {
			g.drawOval(x, y, r, r);
			x += 5;
			drawPipe(g, r);
		}
	}

	/**
	 * Benutzt die Methoden
	 */
	public void paint(Graphics g) {
		drawRect(g, 400);
		x = 650;
		y = 60;
		drawCircle(g, 400);
		x = 10;
		y = 600;
		drawSquares(g, 6, 220);
		x = 10;
		y = 900;
		drawPipe(g, 300);
	}

	public static void main(String[] args) {
		new RekursiveFiguren();
	}
}
