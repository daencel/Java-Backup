package vererbung;

public class Quadrat extends Rechteck {

	private static final long serialVersionUID = 1L;

	public Quadrat(int x, int y, int breite, boolean gefuellt) {
		super(x, y, breite, breite, gefuellt);
	}
}