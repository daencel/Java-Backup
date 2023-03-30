package net.tfobz.dining;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class ForkControl {

	private Fork left, rigth = null;
	private Philosopher p;

	public ForkControl(Fork left, Fork rigth, Philosopher p) {
		this.left = left;
		this.rigth = rigth;
		this.p = p;
		put();
	}

	public synchronized void get() {
		rigth.get(p);
		if (left.isAvailable()) {
			left.get(p);
		} else {
			rigth.put(p);
		}
	}

	public synchronized void put() {
		rigth.put(p);
		left.put(p);

	}
}
