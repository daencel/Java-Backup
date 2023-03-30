package fahrzeugverwaltung;

public class Bicycle extends Fahrzeug {

	protected boolean laemp;

	public boolean isLaemp() {
		return this.laemp;
	}

	public void setLaemp(boolean b) {
		this.laemp = b;
	}

	public Bicycle(int speed, int weight, boolean laemp) {
		super(speed, weight);
		this.setLaemp(laemp);
	}

	public String toString() {
		return (this.ID + ": Fahrrad: G: " + this.getSpeed() + " K: " + this.getWeight() + " B: " + this.isLaemp());
	}
}
