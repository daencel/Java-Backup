package net.tfobz.tunnel.client;

/**
 * An ihm kann ein Führer angefordert aber auch ein solcher zurückgegeben
 * werden. Dieser muss eine Referenz auf ClientForm haben, damit die
 * Statusmeldungen dort angezeigt werden können
 */
public class GuidesMonitor {
	/**
	 * Maximalanzahl der am Eingang vorhanden Führer
	 */
	protected final int MAX_GUIDES = 4;
	/**
	 * Anzahl der momentan verfügbaren Führer
	 */
	protected int availableGuides = MAX_GUIDES;
	/**
	 * Referenz auf das ClientForm um Statustexte auszugeben
	 */
	protected ClientForm clientForm = null;

	/**
	 * Konstruktor, dem eine Referenz auf das ClientForm übergeben wird
	 * 
	 * @param clientForm
	 */
	public GuidesMonitor(ClientForm clientForm) {
		this.clientForm = clientForm;
	}

	/**
	 * Ein Führer wird angefordert, gleichzeitig werden die Statusmeldungen im
	 * ClientForm ausgegeben und die Benutzerschnittstelle angepasst
	 */
	public synchronized void request() {
		clientForm.write("Guide requested...");
		while (getAvailableGuides() - 1 < 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		availableGuides--;
		clientForm.available.setText(availableGuides + "");
		clientForm.write("Guide reserved. " + getAvailableGuides() + " guides now available");
	}

	/**
	 * Führer wird bei Beendigung einer Führung zurück gegeben. Statusmeldungen
	 * werden ausgegeben und die Benutzerschnittstelle angepasst
	 */
	public synchronized void release() {
		if (getAvailableGuides() < MAX_GUIDES) {
			availableGuides++;
			clientForm.available.setText(availableGuides + "");
			clientForm.write("Guide released. " + getAvailableGuides() + " guides now available");
			notifyAll();
		}
	}

	/**
	 * Die Anzahl der momentan verfügbaren Führer wird zurück geliefert
	 * 
	 * @return Anzahl der momentan verfügbaren Führer
	 */
	public synchronized int getAvailableGuides() {
		return availableGuides;
	}
}
