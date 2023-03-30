package net.gobbz.fahrzeuge;

public class Car extends Fahrzeug {

	protected int ps = 0;

	public int getPs() {
		return this.ps;
	}

	public void setPs(int ps) {
		if (ps > 150) {
			this.ps = ps;
		} else {
			this.ps = 150;
		}
	}

	public Car(int speed, int weight, int ps) {
		super(speed, weight);
		this.setPs(speed);
	}

	@Override
	public String toString() {
		return (this.ID + ": Auto: G: " + this.getSpeed() + " K: " + this.getWeight() + " PS: " + this.getPs());
	}
}
