package net.tfobz.groessen;

public class Papierblatt extends Object implements Groesse, Comparable<Groesse> {

	protected int laenge;
	protected int breite;
	final protected int hoehe = 0;

	/**
	 * Erstellt ein Papierblatt. Das Maß kann A0 - A4 sein
	 * 
	 * @param mass Maß
	 */
	public Papierblatt(int mass) {
		if (mass == 0) {
			this.laenge = 1189;
			this.breite = 841;
		} else if (mass == 1) {
			this.laenge = 841;
			this.breite = 594;
		} else if (mass == 2) {
			this.laenge = 594;
			this.breite = 420;
		} else if (mass == 3) {
			this.laenge = 420;
			this.breite = 297;
		} else if (mass == 4) {
			this.laenge = 294;
			this.breite = 210;
		}
	}

	@Override
	public long getLaenge() {
		return laenge;
	}

	@Override
	public long getBreite() {
		return breite;
	}

	@Override
	public long getHoehe() {
		return hoehe;
	}

	@Override
	public long getGrundflaeche() {
		return this.getBreite() * this.getLaenge();
	}
	
	@Override
	public int compareTo(Groesse o) {
		long gf1 = this.getBreite() * this.getLaenge();
		long gf2 = o.getBreite() * o.getLaenge();
		if (gf1 < gf2)
			return -1;
		else if (gf1 > gf2)
			return 1;
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return "Papierblatt L = " + getLaenge() + " B = " + getBreite() + " H = "
				+ getHoehe() + " G = " + getGrundflaeche();
	}
}
