
public class Song {

	private String title;
	private String interpret;
	private String album;
	private int erscheinungsjahr;

	/**
	 * Gibt den Titel zurueck
	 * 
	 * @return Titel
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gibt den Interpreten zurueck
	 * 
	 * @return Interpret
	 */
	public String getInterpret() {
		return this.interpret;
	}

	/**
	 * Gibt das Album zurueck
	 * 
	 * @return Album
	 */
	public String getAlbum() {
		return this.album;
	}

	/**
	 * Gibt das Erscheinungsjahr zurueck
	 * 
	 * @return Erscheinungsjahr
	 */
	public int getErscheinungsjahr() {
		return this.erscheinungsjahr;
	}

	/**
	 * Setzt den Titel
	 * 
	 * @param a Titel
	 */
	public void setTitle(String a) {
		this.title = a;
	}

	/**
	 * Setzt den Interpreten
	 * 
	 * @param a Interpret
	 */
	public void setInterpret(String a) {
		this.interpret = a;
	}

	/**
	 * Setzt das Album
	 * 
	 * @param a Album
	 */
	public void setAlbum(String a) {
		this.album = a;
	}

	/**
	 * Setzt das Erscheinungsjahr
	 * 
	 * @param Erscheinungsjahr
	 */
	public void setErscheinungsjahr(Integer a) {
		if (a > 0) {
			this.erscheinungsjahr = a;
		}
	}

	/**
	 * Vergleicht das Lied mit dem uebergebenen Lied b und gibt true oder false
	 * zurueck
	 * 
	 * @param b Lieb d, mit dem verglichen wird
	 * @return true oder false
	 */
	public boolean equals(Song b) {
		boolean equals = true;
		if (!this.album.equals(b.getAlbum())) {
			equals = false;
		}
		if (equals == true && !this.interpret.equals(b.getInterpret())) {
			equals = false;
		}
		if (equals == true && this.erscheinungsjahr != b.getErscheinungsjahr()) {
			equals = false;
		}
		if (equals == true && !this.title.equals(b.getTitle())) {
			equals = false;
		}
		return equals;
	}

	/**
	 * Erzeugt eine exakte Kopie vom Lied und gibnt dieses zurueck
	 */
	public Song clone() {
		Song neu = new Song();
		neu.setAlbum(this.getAlbum());
		neu.setErscheinungsjahr(this.getErscheinungsjahr());
		neu.setInterpret(this.getInterpret());
		neu.setTitle(this.getTitle());
		return neu;
	}

	/**
	 * Verglicht das Lied mit dem uebergebenen Lied
	 * 
	 * @param b uebergebenes Lied
	 * @return
	 */
	public int compareTo(Song b) {
		return this.toString().compareTo(b.toString());
	}

	/**
	 * Gibt das Lied als String mit: Titel, Album, Interpret und Erscheinungsjahr
	 * zurueck
	 */
	@Override
	public java.lang.String toString() {
		return this.title + ";" + this.album + ";" + this.interpret + ";" + this.erscheinungsjahr;
	}

	/**
	 * teilt den String als formatierten String auf und giebt diesen zurueck
	 * 
	 * @param str String der formatiert wird
	 * @return formatierter String
	 */
	public void setSong(String str) {
		String[] parts = str.split(";");
		this.title = parts[0];
		this.album = parts[1];
		this.interpret = parts[2];
		this.erscheinungsjahr = Integer.parseInt(parts[3]);
	}
}
