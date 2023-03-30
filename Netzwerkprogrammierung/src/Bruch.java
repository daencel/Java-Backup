public class Bruch {

	private int Nenner;
	private int Zaehler;

	/**
	 * Konstruktor von bruch
	 * 
	 * @param zaehler Zaehler des Bruch
	 * @param nenner  Nenner des Bruch
	 * @throws BruchException falls der Nenner Null is BruchException
	 */
	public Bruch(int zaehler, int nenner) throws BruchException {
		this.setZaehler(zaehler);
		if (nenner == 0) {
			throw new BruchException("Nenner ist 0");
		} else {
			this.setNenner(nenner);
		}
		this.kuerzen();
	}

	/**
	 * Kuerzt den Bruch
	 */
	private void kuerzen() {
		if (this.getNenner() != 0 && this.getZaehler() != 0) {
			if (this.getNenner() % this.getZaehler() == 0 || this.getZaehler() % this.getNenner() == 0) {
				int ggt = ggt(this.getNenner(), this.getZaehler());
				this.setNenner(this.getNenner() / ggt);
				this.setZaehler(this.getZaehler() / ggt);
			}
		}
	}

	/**
	 * Gibt den ggT von Zwei Zahlen zurueck
	 * 
	 * @param a Zahl 1
	 * @param b Zahl 2
	 * @return ggT
	 */
	private int ggt(int a, int b) {
		if (a == 0)
			return b;
		while (b != 0) {
			if (a > b)
				a = a - b;
			else
				b = b - a;
		}
		return a;
	}

	@Override
	/**
	 * Gibt den Bruch als String in der form 1/2 zurueck
	 */
	public String toString() {
		return (this.getZaehler() + "/" + this.getNenner());
	}

	/**
	 * Vergleicht zwei Brueche miteinander
	 * 
	 * @param b zweiter Bruch
	 * @return true falls sie die gleichen Werte haben
	 * @throws Exception wenn der zweite Bruch leer ist oder kein Bruch ist
	 */
	public boolean compareTo(Object b) throws Exception {
		if (b == null) {
			throw new NullPointerException("Zweiter Bruch ist leer");
		} else if (!(b instanceof Bruch)) {
			throw new ClassCastException("Typ stimmt nicht ueberein");
		} else {
			Bruch b1 = (Bruch) b;
			if (this.getNenner() == b1.getNenner() && this.getZaehler() == this.getZaehler()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Klont den Bruch und gibt eine identische Kopie zurueck
	 */
	protected Bruch clone() {
		Bruch a = null;
		try {
			a = new Bruch(this.getZaehler(), this.getNenner());
		} catch (BruchException e) {
			e.printStackTrace();
		}
		return a;
	}

	/**
	 * Addiert den Bruch zum uebergebenen und schreibt die Loesung in den ersten
	 * Bruch
	 * 
	 * @param b zweiter bruch
	 */
	public void addiere(Bruch b) {
		if (b == null) {
			throw new NullPointerException("Zweieter Bruch ist leer");
		} else {
			int neuerZaehler = this.getZaehler() * b.getNenner() + b.getZaehler() * this.getNenner();
			int neuerNenner = this.getNenner() * b.getNenner();
			this.setNenner(neuerNenner);
			this.setZaehler(neuerZaehler);
			this.kuerzen();
		}
	}

	/**
	 * Subrahiert den Bruch zum uebergebenen und schreibt die Loesung in den ersten
	 * Bruch
	 * 
	 * @param b zweiter bruch
	 */
	public void subdrahiere(Bruch b) {
		if (b == null) {
			throw new NullPointerException("Zweieter Bruch ist leer");
		} else {
			int neuerZaehler = this.getZaehler() * b.getNenner() - b.getZaehler() * this.getNenner();
			int neuerNenner = this.getNenner() * b.getNenner();
			this.setNenner(neuerNenner);
			this.setZaehler(neuerZaehler);
			this.kuerzen();
		}
	}

	/**
	 * Multipliziere den Bruch zum uebergebenen und schreibt die Loesung in den
	 * ersten Bruch
	 * 
	 * @param b zweiter bruch
	 */
	public void multipliziere(Bruch b) {
		if (b == null) {
			throw new NullPointerException("Zweieter Bruch ist leer");
		} else {
			int neuerNenner = this.getNenner() * b.getNenner();
			int neuerZaehler = this.getZaehler() * b.getZaehler();
			this.setNenner(neuerNenner);
			this.setZaehler(neuerZaehler);
			this.kuerzen();
		}
	}

	/**
	 * Dividiert den Bruch zum uebergebenen und schreibt die Loesung in den ersten
	 * Bruch
	 * 
	 * @param b zweiter bruch
	 */
	public void dividiere(Bruch b) {
		if (b == null) {
			throw new NullPointerException("Zweieter Bruch ist leer");
		} else {
			int neuerNenner = this.getNenner() * b.getZaehler();
			int neuerZaehler = this.getZaehler() * b.getNenner();
			this.setNenner(neuerNenner);
			this.setZaehler(neuerZaehler);
			this.kuerzen();
		}
	}

	/**
	 * Gibt den Nenner zurueck
	 * 
	 * @return Nenner
	 */
	public int getNenner() {
		return Nenner;
	}

	/**
	 * Setzt den Nenner
	 * 
	 * @param nenner
	 */
	public void setNenner(int nenner) {
		Nenner = nenner;
	}

	/**
	 * Gibt den zaehler zurueck
	 * 
	 * @return Zaehler
	 */
	public int getZaehler() {
		return Zaehler;
	}

	/**
	 * Setzt den Zaehler
	 * 
	 * @param zaehler
	 */
	public void setZaehler(int zaehler) {
		Zaehler = zaehler;
	}
}
