package net.tfobz.dining;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Philosopher extends Thread {

	public static final int MAX_THINK_TIME = 100;
	public static final int MAX_EAT_TIME = 50;

	private ForkControl fc;

	public Philosopher(String name, Fork left, Fork rigth) {
		setName(name);
		fc = new ForkControl(left, rigth, this);
	}

	@Override
	public void run() {
		while (true) {
			try {
				sleep((int) (Math.random() * MAX_THINK_TIME));
			} catch (InterruptedException e) {
			}
			fc.get();
			try {
				sleep((int) (Math.random() * MAX_EAT_TIME));
			} catch (InterruptedException e) {
			}
			fc.put();
		}
	}
}
