package net.gobbz.geometrischefiguren;

import java.awt.Graphics;

public class Ellipse extends Rechteck {

	private static final long serialVersionUID = 1L;

	public Ellipse(int x, int y, int breite, int hoehe, boolean gefuellt) {
		super(x, y, breite, hoehe, gefuellt);
	}

	public void paint(Graphics g) {
		g.setColor(this.farbe);
		if (this.gefuellt) {
			g.fillOval(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		} else {
			g.drawOval(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		}
	}
}
