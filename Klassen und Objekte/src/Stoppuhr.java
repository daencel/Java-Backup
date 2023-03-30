import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Stoppuhr {
	
	public long startpunkt;
	public long endpunkt;
	public String dateiname;
	public long [] a = new long [1000];
	public int pos = -1;
	
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
		if (pos < 999) {
			pos++;
			a[pos] = this.endpunkt - this.startpunkt;
		}
		return this.endpunkt - this.startpunkt;
	}
	
	/**
	 * 
	 * @param a
	 */
	public void setDateiname(String a) {
		this.dateiname = a;
	}
	
	public int schreibeZeiten() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.dateiname));
			// ACHTUNG: Am Ende jeder Zeile muss eine Zeilenschaltung \n eingefügt werden
			pos = 0;
			long x = a[pos];
			writer.write(x + "\n");
			pos++;
			x = a[pos];
			while (x > 0 && pos < 999) {
				writer.write(x + "\n");
				pos++;
				x = a[pos];
			}	
			writer.close();
			return 0;
		} catch (IOException e) {
			return 1;
		}
	}
}
