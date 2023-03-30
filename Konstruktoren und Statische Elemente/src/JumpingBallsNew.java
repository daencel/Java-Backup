import java.awt.*;
import javax.swing.*;
 
public class JumpingBallsNew extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Statische Membervariable, welche sich in einem Array die zu bewegenden Baelle
	 * merkt. Diese Variable darf nicht als lokale Variable innerhalb der paint-Methode
	 * definiert werden, weil sie ansonsten nach jedem Aufruf der Methode zerstoert 
	 * wuede. Da aber die einzelnen Ballobjekte nach dem Ende der Methode und beim
	 * naechsten Auruf der paint-Methode noch leben muessen, werden diese in einer
	 * statischen Membervariable der Klasse Applet abgelegt, die auch dann noch
	 * existiert und ihre Inhalte behaelt, wenn ein Aufruf der paint-Methode fertig 
	 * abgearbeitet wurde 
	 */
	private static Ball[] ball = null;
	private static	final int ANZAHL_BAELLE = 18;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Jumpingballs");
		frame.setVisible(true);
		frame.setLocation(10, 10);
		frame.setSize(1600,1200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new JumpingBallsNew());
	}
	
	@Override
	public void paint(Graphics g)	{
		super.paint(g);
		if (ball == null) {
			ball = new Ball[ANZAHL_BAELLE];
			for (int i = 0; i < ANZAHL_BAELLE; i = i + 1) {
				ball[i] = new Ball();
				ball[i].setZufaellig();
			}
		}
		for (int i = 0; i < ANZAHL_BAELLE; i = i + 1) {
			ball[i].bewege(g, getWidth(), getHeight());
			g.setColor(ball[i].getColor());
			g.fillOval(ball[i].getXpos(), ball[i].getYpos(), ball[i].getRadius()*2, ball[i].getRadius()*2);
		}
		// Das Programm wartet eine gewisse Anzahl von Millisekunden
		bremse(10);
		repaint();
    }
	
	/**
	 * Veranlasst dass das Programm millis Millisekunden pausiert
	 * @param millis Anzahl der Millisekunden die gewartet werden
	 */
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}