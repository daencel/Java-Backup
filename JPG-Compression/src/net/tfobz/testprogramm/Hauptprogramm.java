package net.tfobz.testprogramm;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Hauptprogramm {

	public static void main(String[] args) {
		MyThread thread = new MyThread();
		MyThreadStateAnalyzer analyzer = new MyThreadStateAnalyzer(thread);
		MyThreadTerminator terminator = new MyThreadTerminator(thread);

		analyzer.start();
		terminator.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.start();
	}
}