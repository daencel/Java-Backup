package net.tfobz.testprogramm;

/**
 * 
 * Aufgabe 1
 * 
 * @author Daniel Lechner
 *
 */
public class ThreadTest {

	public static void main(String[] args) {
		Runnable r = () -> {
			System.out.println("runnable");
		};

		for (int i = 0; i < 1000; i++)
			new Thread(r).start();
	}

}
