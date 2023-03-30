package vererbung;

import java.awt.*;

public class Rechteck extends Punkt {

	private static final long serialVersionUID = 1L;

	protected boolean gefuellt;

	public void setGefuellt(boolean gefuellt) {
		this.gefuellt = gefuellt;
	}

	public boolean isGefuellt() {
		return this.gefuellt;
	}

	public Rechteck(int x, int y, int breite, int hoehe, boolean gefuellt) {
		setBounds(x, y, breite, hoehe);
		this.setGefuellt(gefuellt);
	}

	public void paint(Graphics g) {
		g.setColor(this.farbe);
		if (this.gefuellt) {
			g.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		} else {
			g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		}
	}
}