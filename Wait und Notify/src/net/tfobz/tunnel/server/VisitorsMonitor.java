package net.tfobz.tunnel.server;

/**
 * Diese Klasse verwaltet die verf�gbaren Besucher, welche eingelassen werden
 * k�nnen
 */
public class VisitorsMonitor {
	/**
	 * Maximalanzahl der im Tunnel vorhanden Besucher
	 */
	protected final int MAX_VISITORS = 50;
	/**
	 * Anzahl der Besucher die in den Tunnel noch eingelassen werden k�nnen
	 */
	protected int availableVisitors = MAX_VISITORS;

	/**
	 * Fordert count Besucher an und gibt Statusmeldungen an der Serverkonsole aus
	 * 
	 * @param count
	 */
	public synchronized void request(int count) {
		System.out.println(ServerThread.currentThread().getName() + " requests " + count + " visitors. ");
		while (getAvailableVisitors() - count < 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		availableVisitors -= count;
		System.out.println(ServerThread.currentThread().getName() + " receives " + count + " visitors. "
				+ getAvailableVisitors() + " visitors available");
	}

	/**
	 * Gibt count Besucher an den VisitorsMonitor zur�ck und gibt Statusmeldungen an
	 * der Serverkonsole aus
	 * 
	 * @param count
	 */
	public synchronized void release(int count) {
		availableVisitors += count;
		System.out.println(ServerThread.currentThread().getName() + " releases " + count + " visitors. "
				+ getAvailableVisitors() + " visitors available");
		notifyAll();
	}

	/**
	 * Liefert die Anzahl der momentan noch verf�gbaren Besucher zur�ck, die in den
	 * Tunnel eingelassen werden k�nnen
	 * 
	 * @return Anzahl der noch in den Tunnel einlassbaren Besucher
	 */
	public synchronized int getAvailableVisitors() {
		return availableVisitors;
	}
}
