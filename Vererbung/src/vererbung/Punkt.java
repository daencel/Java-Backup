package vererbung;

import javax.swing.*;
import java.awt.*;

public class Punkt extends JComponent {

	private static final long serialVersionUID = 1L;
	private static final int PUNKT_GROESSE = 8;
	protected Color farbe = Color.BLACK;

	public Punkt() {
	}

	public Punkt(int x, int y) {
		setBounds(x, y, PUNKT_GROESSE, PUNKT_GROESSE);
	}

	public void setFarbe(Color farbe) {
		this.farbe = farbe;
		repaint();
	}

	public Color getFarbe() {
		return this.farbe;
	}

	public void paint(Graphics g) {
		g.setColor(this.farbe);
		g.fillOval(0, 0, PUNKT_GROESSE, PUNKT_GROESSE);
	}
}