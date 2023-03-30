package net.tfobz.dining;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Fork {

	private String name = null;
	private boolean available = false;

	public Fork(String name) {
		this.name = name;
	}

	/**
	 * someone takes the Fork
	 * 
	 * @param p the Philosopher, who takes down the Fork
	 */
	public synchronized void get(Philosopher p) {
		while (!available)
			try {
				wait();
			} catch (Exception e) {
			}
		available = false;
		System.out.println(p.getName() + " takes " + name);
		notifyAll();
	}

	/**
	 * someone puts down the Fork
	 * 
	 * @param p the Philosopher, who puts down the Fork
	 */
	public synchronized void put(Philosopher p) {
		available = true;
		System.out.println(p.getName() + " puts down " + name);
		notifyAll();
	}

	/**
	 * checks if the Fork is available
	 * 
	 * @return true if the Fork is available
	 */
	public boolean isAvailable() {
		return this.available;
	}

}
