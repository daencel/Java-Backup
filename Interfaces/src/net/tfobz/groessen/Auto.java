package net.tfobz.groessen;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Auto extends Object implements Groesse, Comparable<Groesse> {

	protected int laenge;
	protected int breite;
	protected int hoehe;

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
		return "Auto L = " + getLaenge() + " B = " + getBreite() + " H = "
				+ getHoehe() + " G = " + getGrundflaeche();
	}

	/**
	 * @param laenge the laenge to set
	 */
	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	/**
	 * @param breite the breite to set
	 */
	public void setBreite(int breite) {
		this.breite = breite;
	}

	/**
	 * @param hoehe the hoehe to set
	 */
	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}
}
