package net.gobbz.fahrzeuge;

public class Semi extends Car {

	protected int capacity;

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		if (capacity > 0) {
			this.capacity = capacity;
		} else {
			this.capacity = 0;
		}
	}

	public Semi(int speed, int weight, int ps, int capacity) {
		super(speed, weight, ps);
		this.setCapacity(capacity);
	}

	public Semi(int speed, int ps, int capacity) {
		super(speed, 0, ps);
		this.setCapacity(capacity);
	}

	public String toString() {
		return (this.ID + ": Lastwagen: G: " + this.getSpeed() + " K: " + this.getWeight() + " PS: " + this.getPs()
				+ " L: " + this.getCapacity());
	}
}
