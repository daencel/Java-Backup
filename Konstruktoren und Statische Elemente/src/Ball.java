import java.awt.*;

public class Ball {
	
	private int radius = 0;
	private int xposition = 500;//60;
	private int yposition = 500;//80;
	private int xrichtung = 0;
	private int yrichtung = 0;
	private java.awt.Color farbe = java.awt.Color.BLACK;
	
	/**
	 * Liefert die Position X des Balles
	 * @return Position X
	 */
	public int getXpos() {
		return xposition;
	}
	
	/**
	 * liefert die Postition Y des Balles
	 * @return Position Y
	 */
	public int getYpos() {
		return yposition;
	}
	
	 /**
	  * Liefert den Radius des Balles 
	  * @return Radius
	  */
	public int getRadius() {
		return radius;
	}
	
	 /**
	  * Liefert den Farbcode des Balles (z.B 255,255,255)
	  * @return Farbcode
	  */
	public Color getColor() {
		return farbe;
	}
	
	
	/**
	 * Gibt den String zurueck
	 * @return String der zurueck gegeben wird
	 */
	public String toString() {
		String ret = ("r = " + radius + ", xposition = " + xposition + ", yposition = " + yposition + ", xrichtung = " + xrichtung + ", yrichtung = " + yrichtung);
		return ret;
	}
	
	/**
	 * Radius: Zufaellig von 2 bis 40
	 * Richtung: Zufaellig von -10 bis 10
	 * Farbe: 3 mal Zufaellig von 0 bis 255
	 */
	public void setZufaellig() {
		this.radius = (int)(Math.random()*70/*39*/)+2;
		int ran = (int)(Math.random()*21)-10;
		while (ran == 0) {
			ran = (int)(Math.random()*21)-10;
		}
		this.xrichtung = ran;
		ran = (int)(Math.random()*21)-10;
		while (ran == 0) {
			ran = (int)(Math.random()*21)-10;
		}
		this.yrichtung = ran;
		ran = (int)(Math.random()*256);
		this.farbe = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
	}
	
	/**
	 * Bewegt den Ball auf der x und y Achse, kontrolliert jedoch, ob die Werte nicht über oder 
	 * unter der Applet - Flaeche reichen. Berechnet somit die neuen Werte.
	 */
	public void bewege(Graphics g, int breite, int hoehe) {
		int x = this.xposition + this.xrichtung;
		this.xposition = this.getXpos() + this.xrichtung;
		int y = this.yposition + this.yrichtung;
		this.yposition = this.getYpos() + this.yrichtung;
		if (x > (breite - this.radius*2)) {
			this.xrichtung = 0 - this.xrichtung;
			this.xposition = this.getXpos() + this.xrichtung;
		}
		if (x < 0) {
			this.xrichtung = Math.abs(this.xrichtung);
			this.xposition = this.getXpos() + this.xrichtung;
		}
		if (y > (hoehe - this.radius*2)) {
			this.yrichtung = 0 - this.yrichtung;
			this.yposition = this.getYpos() + this.yrichtung;
		}
		if (y < 0) {
			this.yrichtung = Math.abs(this.yrichtung);
			this.yposition = this.getYpos() + this.yrichtung;
		}
	}
}