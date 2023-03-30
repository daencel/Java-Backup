package net.tfobz.testprogramm;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class MyThreadStateAnalyzer extends Thread {

	private Thread t;

	public MyThreadStateAnalyzer(Thread t) {
		this.t = t;
	}

	@Override
	public void run() {
		while (t.getState() == State.NEW || t.isAlive()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(t.getState());
		}
	}
}