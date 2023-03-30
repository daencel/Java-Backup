import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class SevenSegment extends JFrame{

	private static final long serialVersionUID = 1L;
	private char segments [] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	/**
	 * Konstruktor des Fensters
	 */
	public SevenSegment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 900);
		setResizable(false);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		setVisible(true);
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		int pos = 0;
		while (true) {
			char actual = segments[pos];
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 600, 900);
			if (actual == '0' || actual == '2' || actual == '3'|| actual == '5'|| actual == '6'|| actual == '7'|| actual == '8'|| actual == '9'|| actual == 'A'|| actual == 'C'|| actual == 'E'|| actual == 'F') {
				g.setColor(Color.RED);
				g.fillRect(110, 90, 280, 20); //a
			} else {
				g.setColor(new Color(220,220,220,30));
				g.fillRect(110, 90, 280, 20); //a
			}
			if (actual == '0' || actual == '4' || actual == '5'|| actual == '6'|| actual == '8' || actual == '9' || actual == 'A' || actual == 'B' || actual == 'C' || actual == 'E' || actual == 'F') {
				g.setColor(Color.RED);
				g.fillRect(90, 110, 20, 320);	// f
			} else {
				g.setColor(new Color(220,220,220,30));
				g.fillRect(90, 110, 20, 320);	// f
			}
			if (actual == '0'|| actual == '1'|| actual == '2'|| actual == '3'|| actual == '4'|| actual == '7'|| actual == '8'|| actual == '9'|| actual == 'A'|| actual == 'D') {
				g.setColor(Color.RED);
				g.fillRect(390, 110, 20, 320);	// b
			} else {
				g.setColor(new Color(220,220,220,30));
				g.fillRect(390, 110, 20, 320);	//b
			}
			if (actual == '2' || actual == '3' || actual == '4'|| actual == '5'|| actual == '6'|| actual == '8'|| actual == '9'|| actual == 'A'|| actual == 'B' || actual == 'D'|| actual == 'E'|| actual == 'F') {
				g.setColor(Color.RED);
				g.fillRect(110, 430, 280, 20);	//g
			} else {
				g.setColor(new Color(220,220,220,30));
				g.fillRect(110, 430, 280, 20);	//g
			}
			if (actual == '0' || actual == '2' || actual == '6' || actual == '8' || actual == 'A'|| actual == 'B'|| actual == 'C'|| actual == 'D'|| actual == 'E'|| actual == 'F') {
				g.setColor(Color.RED);
				g.fillRect(90, 450, 20, 320);	//e
			} else {
				g.setColor(new Color(220,220,220,30));
				g.fillRect(90, 450, 20, 320);	//e
			}
			if (actual == '0' || actual == '1'|| actual == '3' || actual == '4' || actual == '5' || actual == '6' || actual == '7' || actual == '8' || actual == '9' || actual == 'A' || actual == 'B' || actual == 'D') {
				g.setColor(Color.RED);
				g.fillRect(390, 450, 20, 320);	//c
			} else {
				g.setColor(new Color(220,220,220,30));
				g.fillRect(390, 450, 20, 320);	//c
			}
			if (actual == '0' || actual == '2' || actual == '3' || actual == '5' || actual == '6' || actual == '8' || actual == '9' || actual == 'B' || actual == 'C' || actual == 'D' || actual == 'E') {
				g.setColor(Color.RED);
				g.fillRect(110, 770, 280, 20);	//d
			} else {
				g.setColor(new Color(220,220,220,30));
				g.fillRect(110, 770, 280, 20);	//d
			}
			bremse(600);
			pos++;
			if (pos == segments.length) {
				pos = 0;
			}
			
		}
	}
	
	/**
	 * Veranlasst dass das Programm millis Millisekunden pausiert
	 * @param millis Anzahl der Millisekunden die zu warten sind
	 */
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
	
	public static void main(String[] args) {
		new SevenSegment();
	}

}
