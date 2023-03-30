package net.tfobz.testprogramm;

import java.time.Instant;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class MyThread extends Thread {

	public static void main(String[] args) {
		MyThread t = new MyThread();
		t.start();
		try {
			Thread.sleep(5236);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.interrupt();
	}

	@Override
	public void run() {
		Instant until = Instant.now();
		while (!isInterrupted()) {
			until = until.plusSeconds(1);
			try {
				sleepUntil(until);
			} catch (InterruptedException e1) {
				interrupt();
			}
			for (int i = 0; i < 10000000; i++)
				System.out.print("");
		}
	}

	/**
	 * Lässt den Thread bis zur übergebenen Zeit schlafen
	 * 
	 * @param until Zeit bis zum aufwecken
	 * @throws InterruptedException
	 */
	public static void sleepUntil(Instant until) throws InterruptedException {
		long now = Instant.now().toEpochMilli();
		if (until.toEpochMilli() - now > 0)
			Thread.sleep(until.toEpochMilli() - now);
	}
}