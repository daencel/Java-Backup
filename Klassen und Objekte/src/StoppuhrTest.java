/**
 * Programm zum Testen der Stoppuhr
 * @author Michael Wild
 *
 */
public class StoppuhrTest
{
	public static void main(String[] args) {
		// Anlegen des Stoppuhr-Objektes
		Stoppuhr uhr = new Stoppuhr();
		// Setzen des Pfades und des Dateinamens jener Datei, in welche die Stoppzeiten
		// geschrieben werden sollen
		uhr.setDateiname("C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\zeiten.csv");
		// Fünfmal wir die Stoppuhr gestartet und gestoppt und dabei die Zeit gemessen
		for (int i = 0; i < 5; i++) {
			// Starten der Stoppuhr
			uhr.starteStoppuhr();
			// Es wird eine Pause von 0, 1000, 2000, 3000, 4000 Millisekunden gemacht
			pause(i * 1000);
			// Nach der Pause wird die Stoppuhr gestoppt
			uhr.stoppeStoppuhr();
			// Die gestoppte Zeit wird ausgegeben
			long zeit = uhr.getGestoppteZeit();
			if (zeit == -1)
				// Dies deshalb, weil beispielsweise die Stoppuhr nicht gestartet wurde
				System.out.println("Gestoppte Zeit konnte nicht ermittelt werden");
			else
				System.out.println (zeit);
		}
		// Die fünf gestoppten Zeiten werden in die csv-Datei geschrieben
		if (uhr.schreibeZeiten() != 0)
			System.out.println("Stoppzeiten konnten nicht in die Datei geschrieben werden");
	}
	
	/**
	 * Veranlasst eine Programmpause von millis Millisekunden
	 * @param millis
	 */
	public static void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}