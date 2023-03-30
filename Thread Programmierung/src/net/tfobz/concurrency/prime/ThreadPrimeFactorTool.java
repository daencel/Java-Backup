package net.tfobz.concurrency.prime;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class ThreadPrimeFactorTool extends PrimeFactorTool {

	private final int ANZ_THREADS = 4;

	public ThreadPrimeFactorTool() {
		// int bereich = PrimeFactorToolMain.MAX_NUM - PrimeFactorToolMain.MIN_NUM;
		for (int i = 0; i < ANZ_THREADS; i++) {
			new Thread();
		}
	}
}
