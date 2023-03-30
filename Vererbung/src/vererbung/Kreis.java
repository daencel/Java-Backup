package vererbung;

import java.awt.Graphics;

public class Kreis extends Ellipse {

	private static final long serialVersionUID = 1L;

	public Kreis(int x, int y, int radius, boolean gefuellt) {
		super(x, y, radius, radius, gefuellt);
	}

	public void paint(Graphics g) {
		g.setColor(this.farbe);
		super.paint(g);
	}
}
