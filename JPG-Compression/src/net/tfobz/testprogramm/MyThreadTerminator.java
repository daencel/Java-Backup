package net.tfobz.testprogramm;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class MyThreadTerminator extends Thread {
	
	Thread t = new Thread();
	
	public MyThreadTerminator(Thread t) {
		this.t = t;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			t.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
