import java.awt.*;
import javax.swing.*;

public class CosSinFenster extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CosSinFenster frame = new CosSinFenster();
		frame.setTitle("Cosinus - Sinusberechnung");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocation(70, 200);
		frame.setSize(2400,400);
		frame.repaint();
	}
	
	/**
	 * Zeichnet die Funktion
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawLine(convertX(WELT_X0),convertY((WELT_Y0+WELT_Y1)/2),convertX(WELT_X1),convertY((WELT_Y0+WELT_Y1)/2));
		g.drawLine(convertX((WELT_X0+WELT_X1)/2), convertY(WELT_Y0), convertX((WELT_X0+WELT_X1)/2), convertY(WELT_Y1));
		int X0 = convertX((WELT_X0+WELT_X1)/2);
		int x = 0;
		int X1 = convertX(WELT_X1);
		while (X0 < X1) {
			int y = convertY(Math.sin(Math.toRadians((double)x)));
			g.fillRect(X0, y, 3, 3);
			y = convertY(Math.cos(Math.toRadians((double)x)));
			g.fillRect(X0, y, 3, 3);
			X0++;
			x++;
		}
		x = 0;
		X0 = convertX((WELT_X0+WELT_X1)/2);
		while (X0 >= 0) {
			int y = convertY(Math.sin(Math.toRadians((double)x)));
			g.fillRect(X0, y, 3, 3);
			y = convertY(Math.cos(Math.toRadians((double)x)));
			g.fillRect(X0, y, 3, 3);
			X0--;
			x--;
		}
	}

	/**
	 * Festlegen der Weltkoordinaten
	 */
	private final double WELT_X0 = -10.0;
	private final double WELT_Y0 = -01.0;
	private final double WELT_X1 = +10.0;
	private final double WELT_Y1 = +01.0;
	
	/**
	 * Umwandlung Welt-X-Koordinaten in Bildschirmkoordinaten. Da die Methoden
	 * getHeight und getWith auch die Raender und insbesondere die Titelleiste in die
	 * Hoehe und Breite des Fensters einrechnen, muessen mit Insets diese Raender
	 * weggezaehlt werden.
	 * @param xwert Die umzuwandelnde Welt-X-Koordinate
	 * @param return die Bildschrimkoordinate
	 */
	private int convertX(double xwert) {
		Insets i = getInsets();
		return i.left + (int) ((xwert - WELT_X0) * (getWidth() - i.left - i.right) / (WELT_X1 - WELT_X0));
	}
	
	/**
	 * Umwandlung Welt-Y-Koordinaten in Bildschirmkoordinaten. Da die Methoden
	 * getHeight und getWith auch die Ränder und insbesondere die Titelleiste in die
	 * Hoehe und Breite des Fensters einrechnen, muessen mit Insets diese Raender
	 * weggezaehlt werden.
	 * @param ywert die umzuwandelnde Welt-Y-Koordinate
	 * @param return die Bildschrimkoordinate
	 */
	private int convertY(double ywert) {
		Insets i = getInsets();
		return i.top + (int) (getHeight() - i.top - i.bottom - (ywert - WELT_Y0) * (getHeight() - i.top - i.bottom) / (WELT_Y1 - WELT_Y0));
	}
}
