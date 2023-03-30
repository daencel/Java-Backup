package Sorter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Stoppuhr {
	
	public long startpunkt;
	public long endpunkt;
	public String dateiname;
	public long [] a = new long [9001];
	public int pos = 0;
	
	/**
	 * Startet die Stoppuhr
	 */
	public void starteStoppuhr() {
		this.startpunkt = new java.util.GregorianCalendar().getTimeInMillis();
	}
	
	/**
	 * Stoppt die Stopuhr
	 */
	public void stoppeStoppuhr() {
		this.endpunkt = new java.util.GregorianCalendar().getTimeInMillis();
	}
	
	/**
	 * Brechnet die gestoppte Zeit
	 * @return
	 */
	public long getGestoppteZeit() {
		if (pos < 9000) {
			a[pos] = this.endpunkt - this.startpunkt;
			pos++;
		}
		return this.endpunkt - this.startpunkt;
	}
	
	/**
	 * Setzt den Pfad
	 * @param a Pfad
	 */
	public void setPath(String a) {
		this.dateiname = a;
	}
	
	/**
	 * Schreibt die Zeiten in die Datei des Pfades
	 * @return 1 bei Fehler, 0 bei Erfolg
	 */
	public int schreibeZeiten() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.dateiname));
			writer.write("Size Array;Insertion Sort;Bubble Sort;Selection Sort\n");
			int x = 0;
			int l = 0;
			for (int i = 0; i < pos; i++) {
				x++;
				if (x == 3) {
					writer.write(";" + a[i] + "\n");
					x = 0;
				} 
				if (x == 1){
					writer.write(l + ";" + a[i]);
					l+= 5000;
				}
				if (x == 2) {
					writer.write(";" + a[i]);
				}
			}
			writer.close();
			return 0;
		} catch (IOException e) {
			return 1;
		}
	}
}
