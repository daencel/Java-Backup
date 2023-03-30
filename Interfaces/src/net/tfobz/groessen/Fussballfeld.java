package net.tfobz.groessen;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Fussballfeld extends Object implements Groesse, Comparable<Groesse> {

	@Override
	public long getLaenge() {
		return 105000;
	}

	@Override
	public long getBreite() {
		return 70000;
	}

	@Override
	public long getHoehe() {
		return 0;
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
		return "Fussballfeld L = " + getLaenge() + " B = " + getBreite() + " H = "
				+ getHoehe() + " G = " + getGrundflaeche();
	}
}
