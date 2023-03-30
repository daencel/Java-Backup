package fahrzeugverwaltung;

public class Fahrzeug {

	protected int speed;
	protected int weight;
	protected static int counter = -1;
	protected final int ID;

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		if (weight > 0) {
			this.weight = weight;
		} else {
			this.weight = 0;
		}
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getID() {
		return this.ID;
	}

	public void setSpeed(int speed) {
		if (speed > 0) {
			this.speed = speed;
		} else {
			this.speed = 0;
		}
	}

	public Fahrzeug(int speed, int weight) {
		this.setSpeed(speed);
		this.setWeight(weight);
		counter++;
		this.ID = counter;
	}
}
