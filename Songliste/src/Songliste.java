import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Songliste {
	// Anzahl der Songs die im Array aktuell gespeichert sind (anzahlSongs <=
	// this.songs.length)
	private int anzahl = 0;
	// Wenn dem Konstruktor eine nicht korrekte Anzahl von Songs die verwaltet
	// werden koennen uebergeben wird,
	// wird Platz fuer DEFAULT_MAXANZAHL Songs reserviert
	private static int DEFAULT_MAXANZAHL = 1000;
	// Nummer des aktuellen Songs im Array
	private int nummeraktuell = -1;
	// Pfad und Dateiname jener Datei, aus welcher die Songs gelesen bzw. in welche
	// die Songs geschrieben werden
	private java.lang.String pfad;
	// Speichert in einem internen Array die Songs ab.
	private Song[] songs;

	/**
	 * Konstruktor, der ein privates Array initialisiert in welchem maxAnzahl Songs
	 * aufgenommen werden koennen.
	 * 
	 * @param maxAnzahl
	 */
	Songliste(int maxAnzahl) {
		if (maxAnzahl < 1 || maxAnzahl > 1000) {
			this.songs = new Song[DEFAULT_MAXANZAHL];
		} else {
			this.songs = new Song[maxAnzahl];
		}
		anzahl = this.songs.length;
	}

	/**
	 * Liefert die Maximale Anzahl von Songs zurueck, die im Array abgespeichert
	 * werden koennen
	 * 
	 * @return die Anzahl der im Array maximal eintragbaren Songs
	 */
	public int getMaxAnzahl() {
		return this.songs.length;
	}

	/**
	 * Gibt die Anzahl der Songs zurueck, die momentan im Array vorhanden sind
	 * 
	 * @return die Anzahl der Songs die im Array momentan eingetragen sind 0 falls
	 *         das Array zwar angelegt wurde aber noch keine Elemente enthalten sind
	 */
	public int getAnzahl() {
		return this.anzahl;
	}

	/**
	 * Liefert den aktuellen Song zurueck auf den der Songzeiger nummerAktueller
	 * zeigt
	 * 
	 * @return den aktuellen Song oder null, falls kein Song in der Songliste
	 *         enthalten ist
	 */
	public Song getAktueller() {
		if (this.songs[0] == null) {
			return null;
		} else {
			return this.songs[this.nummeraktuell];
		}
	}

	/**
	 * Liefert den naechsten Song - falls vorhanden - zurueck und erhaeht den
	 * Songzeiger nummerAktueller um Eins. Gibt es keinen naechsten Song, wird null
	 * zurueck geliefert und der Songzeiger nicht erhaeht
	 * 
	 * @return liefert den nächsten Song zurueck oder null, falls dieser Song nicht
	 *         vorhanden ist
	 */
	public Song getNaechster() {
		if (this.songs[nummeraktuell + 1] != null && this.nummeraktuell + 1 <= this.songs.length) {
			this.nummeraktuell++;
			return this.songs[this.nummeraktuell];
		} else {
			return null;
		}
	}

	/**
	 * Liefert den vorigen Song - falls vorhanden - zurueck und vermindert den
	 * Songzeiger um Eins. Gibt es keinen vorigen Song wird null zurueck geliefert
	 * und der Songzeiger nicht vermidert
	 * 
	 * @return den vorigen Song
	 */
	public Song getVoriger() {
		if (this.nummeraktuell - 1 >= 0 && this.songs[this.nummeraktuell - 1] != null) {
			this.nummeraktuell--;
			return this.songs[this.nummeraktuell];
		} else {
			return null;
		}
	}

	/**
	 * Liefert den ersten Song in der Songliste zurueck und setzt den Songzeiger auf
	 * diesen Song. Ist die Liste leer, wird null zurueck geliefert.
	 * 
	 * @return den ersten Song in der Liste Ist die Liste leer, wird null zurueck
	 *         geliefert
	 */
	public Song getErster() {
		if (this.songs[0] != null) {
			this.nummeraktuell = 0;
			return this.songs[0];
		} else {
			return null;
		}
	}

	/**
	 * Liefert den letzten Song in der Songliste zurueck und setzt den Songzeiger
	 * auf diesen Song. Ist die Liste leer, wird null zurueck geliefert
	 * 
	 * @return den letzten Song
	 */
	public Song getLetzter() {
		if (this.songs[0] != null) {
			this.nummeraktuell = this.anzahl - 1;
			return this.songs[this.anzahl - 1];
		} else {
			return null;
		}
	}

	/**
	 * Liest den Dateipfad aus
	 * 
	 * @return dateipfad
	 */
	public java.lang.String getPfad() {
		return this.pfad;
	}

	/**
	 * Setzt den Dateipfad auf jene Datei aus welcher die Songs gelesen bzw. in
	 * welche die Songs geschrieben werden soll
	 * 
	 * @param pfad pfad
	 */
	public void setPfad(java.lang.String pfad) {
		if (pfad != null) {
			this.pfad = pfad;
		}
	}

	/**
	 * Aendert den aktuellen Song auf den uebergebenen Song ab und sortiert ihn
	 * nicht ein
	 * 
	 * @param a der Song der die zu Aendernden Werte enthaelt
	 * @return 0 falls die Aenderung erfolgreich durchgefuehrt werden konnte -1
	 *         falls kein zu Aendernder Song uebergeben wurde -2 falls der aktuelle
	 *         Song nicht bekannt ist
	 */
	public int aendernAktuellen(Song s) {
		int ret;
		if (s != null) {
			this.songs[nummeraktuell] = s;
			ret = 0;
		} else
			ret = -1;
		if (nummeraktuell < 0) {
			ret = -2;
		}
		return ret;
	}

	/**
	 * Traegt einen neuen Song am Ende der Songliste ein. Die Anzahl der Songs wird
	 * um Eins erhoeht und die nummerAktueller wird auf diesen Song gesetzt. Der
	 * Song wird nicht eingetragen, wenn im Array kein Platz mehr ist
	 * 
	 * @param s der einzutragende Song
	 * @return 0 falls das Eintragen erfolgreich war -1 falls kein einzutragender
	 *         Song uebergeben wurde -2 falls die Songliste keinen Platz fuer einen
	 *         weiteren Song hat
	 */
	public int anfuegenNeuen(Song s) {
		if (s == null) {
			return -1;
		}
		if (this.anzahl < this.songs.length - 2) {
			this.anzahl++;
			this.nummeraktuell = this.anzahl;
			this.songs[this.anzahl - 1] = new Song();
			this.songs[this.anzahl - 1].setTitle(s.getTitle());
			this.songs[this.anzahl - 1].setInterpret(s.getInterpret());
			this.songs[this.anzahl - 1].setAlbum(s.getAlbum());
			this.songs[this.anzahl - 1].setErscheinungsjahr(s.getErscheinungsjahr());
			return 0;
		} else {
			return -2;
		}

	}

	/**
	 * Loescht den aktuellen Song aus der Liste. Dies kann nur passieren, wenn die
	 * Nummer des aktuellen Songs gesetzt ist. Da im Array eine Luecke entsteht,
	 * muessen alle nachfolgenden Songs um eine Stelle nach vorne geschoben werden.
	 * Der aktuelle Song wird jener Song der dem zu loeschenden Song folgt. Ist der
	 * zu loeschende Song der letzte Song in der Liste, so wird der aktuelle Song
	 * jener Song der vor dem zu loeschenden Song vorhanden ist. Ist der zu
	 * loeschende Song der einzige in der Liste, so wird die nummerAktueller auf -1
	 * gesetzt. Beim Loeschen wird anzahl um Eins verringert
	 * 
	 * @return 0 falls das Loeschen erfolgreich durchgefuehrt werden konnte -1 falls
	 *         der aktuelle Song noch nicht gesetzt wurde
	 */
	public int loeschenAktuellen() {
		if (this.songs[this.nummeraktuell] != null) {
			this.songs[this.nummeraktuell] = null;
			for (int i = this.nummeraktuell + 1; i <= this.anzahl - 1; i++) {
				this.songs[i - 1] = this.songs[i].clone();
			}
			this.songs[this.anzahl] = null;
			this.anzahl--;
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * Loescht alle Songs aus der Liste. Setzt die Anzahl auf 0 und die Nummer des
	 * aktuellen Songs auf -1
	 * 
	 * @return 0 falls das Loeschen erfolgreich war -1 falls die Liste bereits leer
	 *         ist
	 */
	public int loeschenAlle() {
		if (this.songs[1] == null) {
			return -1;
		} else {
			for (int i = 0; i < this.songs.length; i++) {
				this.songs[i] = null;
			}
			this.songs[0] = new Song();
			this.anzahl = 0;
			this.nummeraktuell = 0;
			return 0;
		}
	}

	/**
	 * Laedt die Songs aus der Textdatei die in pfad vorhanden ist. Dabei werden die
	 * bereits in der Songliste gespeicherten Songs geloescht. Nach dem Einfuegen
	 * sind die Songs in sortierter Reihenfolge vorhanden. Als aktueller Song wird
	 * der Erste angesprungen
	 * 
	 * @return 0 falls die Songs erfolgreich ein gefuegt werden konnten -1 falls der
	 *         Pfad nicht gesetzt ist -2 falls die Datei nicht zu finden ist -3
	 *         falls ein Lesefehler in der Datei vorhanden ist -4 das interner Array
	 *         kann nicht alle Songs aufnehmen, weil es zu klein dimensioniert ist
	 */
	public int lesenSongs() {
		Song x = new Song();
		int k = 0;
		for (int i = 0; i < anzahl; i++) {
			this.songs[i] = null;
		}
		if (this.pfad == null) {
			return -1;
		} else
			try {
				BufferedReader reader = new BufferedReader(new FileReader(this.pfad));
				while (true) {
					String line = reader.readLine();
					if (k >= anzahl) {
						reader.close();
						return -4;
					}
					if (line == null) {
						reader.close();
						break;
					} else
						x.setSong(line);
					this.songs[k] = x.clone();
					k++;
				}
				reader.close();
				this.nummeraktuell = 0;
				this.anzahl = k;
				return 0;
			} catch (FileNotFoundException e) {
				return -2;
			} catch (IOException i) {
				return -3;
			}
	}

	/**
	 * Schreibt die Songs in die Textdatei. Dabei werden die in der Datei
	 * gespeicherten Songs geloescht
	 * 
	 * @return 0 falls die Songs erfolgreich ein gefuegt werden konnten -1 falls der
	 *         Pfad nicht gesetzt wurde -2 falls die Datei nicht angelegt werden
	 *         konnte
	 */
	public int schreibenSongs() {
		int ret = -3;
		if (pfad != null) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(pfad));
				for (int k = 0; songs[k] != null && k < this.anzahl; k++) {
					writer.write(songs[k].toString() + "\n");
				}
				writer.close();
			} catch (IOException e) {
				ret = -2;
			}
			ret = 0;
		} else
			ret = -1;
		return ret;
	}

	/**
	 * Sortiert die Song Arra und gibt sie zurueck
	 */
	public void sort() {
		Song ret[] = new Song[this.anzahl];
		for (int i = 0; i < this.anzahl; i++) {
			ret[i] = this.songs[i];
			int x = i;
			while (x > 0 && ret[x - 1].compareTo(ret[x]) > 0) {
				Song h = ret[x];
				ret[x] = ret[x - 1];
				ret[x - 1] = h;
				--x;
			}
		}
		for (int i = 0; i < this.anzahl; i++) {
			this.songs[i] = ret[i];
		}
	}
}
