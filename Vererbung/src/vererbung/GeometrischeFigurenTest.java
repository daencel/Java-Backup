package vererbung;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class GeometrischeFigurenTest extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new GeometrischeFigurenTest();
	}

	public GeometrischeFigurenTest() {
		super("GeometrischeFigurenTest");
		setBounds(0, 0, 1000, 1000);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		Punkt p = new Punkt(10, 10);
		p.setFarbe(Color.RED);
		p.setBounds(10, 10, 100, 100);
		contentPane.add(p);

		Rechteck r = new Rechteck(85, 10, 200, 300, false);
		r.setFarbe(Color.BLUE);
		contentPane.add(r);
		Quadrat q = new Quadrat(300, 10, 150, false);
		q.setFarbe(Color.GREEN);
		contentPane.add(q);
		Ellipse e = new Ellipse(500, 10, 200, 400, false);
		e.setFarbe(Color.BLACK);
		contentPane.add(e);
		Kreis k = new Kreis(720, 10, 200, false);
		k.setFarbe(Color.ORANGE);
		contentPane.add(k);

		Rechteck r2 = new Rechteck(85, 500, 200, 300, true);
		r2.setFarbe(Color.BLUE);
		contentPane.add(r2);
		Quadrat q2 = new Quadrat(300, 500, 150, true);
		q2.setFarbe(Color.GREEN);
		contentPane.add(q2);
		Ellipse e2 = new Ellipse(500, 500, 200, 400, true);
		e2.setFarbe(Color.BLACK);
		contentPane.add(e2);
		Kreis k2 = new Kreis(720, 500, 200, true);
		k2.setFarbe(Color.ORANGE);
		contentPane.add(k2);

		setVisible(true);
	}
}
