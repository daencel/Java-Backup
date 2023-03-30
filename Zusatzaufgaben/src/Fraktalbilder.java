import java.awt.*;
import java.applet.*;

public class Fraktalbilder extends Applet
{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		double x = WELT_X0;
		double y = WELT_Y0;
		//Ermittle die Breite und Hoehe des Applets in Pixel
		do {
			int i = calcIt(x,y,1000);
			int rgb [] = RGB(i);
			//Setzt die Farbe auf die Farben der berechneten Array
			g.setColor(new Color(rgb [0], rgb [1], rgb [2]));
			//Ausgabe eines gefuellten Rechtecks
			int xh = convertX(x);
			int yh = convertY(y);
			g.fillRect(xh, yh, 1, 1);
			x += 0.002;
			if (x > WELT_X1) {
				x = WELT_X0;
				y -= 0.002;
			}
		}while (y > WELT_Y1);
	}
	
	@Override
	public void init() {
		setSize(1200,1200);
	}
	
	/**
	 * Festlegen der Weltkoordinaten
	 */
	private final double WELT_X0 = -2.0;
	private final double WELT_Y0 = 1.25;
	private final double WELT_X1 = 0.5;
	private final double WELT_Y1 = -1.25;
	
	/**
	 * Berechnet die Iterationen die notwendig sind, damit x*x+y*y>4 ergibt.
	 * @param x X-Wert
	 * @param y Y-Wert
	 * @param max Maximale Anzahl der Iterationen
	 * @return Iterationen
	 */
	public int calcIt(double x, double y, int max) {
		int i = 1;
		double xh = x;
		double yh = y;
		double erg;
		do {
			double xv = x;
			x = (Math.pow(x, 2) - Math.pow(y, 2) + xh);
			y = ((2*xv*y) + yh);
			erg = (x*x)+(y*y);
			max--;
			i++;
		} while (erg <= 4 && max > 0);
		return i;
	}
	
	/**
	 * Giebt die RGB Werte zurueck, mithilfe der Iterationen
	 * Sien die Iterationen ueber 50 wird Schwarz zurueckgegeben
	 * @param i Iterationen
	 * @return Array mit den dre RGB Werten
	 */
	public int[] RGB(int i) {
		int ret [] = new int [3];
		if (i >= 50) {
			ret [0] = 0;
			ret [1] = 0;
			ret [2] = 0;
		}
		else {
			int matrix [][] = new int [50][3];
			int rgb = 255;
			int z = 0;
			int u = 0;
			while (rgb > 0) {
				matrix [0+z][0] = rgb+u;
				matrix [1+z][1] = rgb+u;
				matrix [2+z][2] = rgb+u;
				matrix [3+z][1] = rgb+u;
				matrix [3+z][2] = rgb+u;
				matrix [4+z][0] = rgb+u;
				matrix [4+z][2] = rgb+u;
				matrix [5+z][0] = rgb+u;
				matrix [5+z][1] = rgb+u;
				u = 1;
				rgb = rgb/2;
				z += 6;
			}
			ret [0] = matrix [i-1][0];
			ret [1] = matrix [i-1][1];
			ret [2] = matrix [i-1][2];
		}
		return ret;
	}
	
	/**
	 * Umwandlung Welt-X-Koordinaten in Bildschirmkoordinaten. Da die Methoden
	 * getHeight und getWith auch die Ränder und insbesondere die Titelleiste in die
	 * Höhe und Breite des Fensters einrechnen, müssen mit Insets diese Ränder
	 * weggezählt werden.
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
	 * Höhe und Breite des Fensters einrechnen, müssen mit Insets diese Ränder
	 * weggezählt werden.
	 * @param ywert die umzuwandelnde Welt-Y-Koordinate
	 * @param return die Bildschrimkoordinate
	 */
	private int convertY(double ywert) {
		Insets i = getInsets();
		return i.top + (int) (getHeight() - i.top - i.bottom - (ywert - WELT_Y0) * (getHeight() - i.top - i.bottom) / (WELT_Y1 - WELT_Y0));
	}
}
