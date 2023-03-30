
public class Person {
	
	private int id;
	private String Vorname;
	private String Nachname;
	private Person Mutter;
	private Person Vater;
	private boolean weiblich;
	private static int idnr = 0;
	
	public Person(String Vorname, String Nachname, boolean weiblich, Person Mutter, Person Vater) {
		this.id = idnr;
		idnr++;
		this.Vorname = Vorname;
		this.Nachname = Nachname;
		this.weiblich = weiblich;
		this.Vater = Vater;
		this.Mutter = Mutter;
	}
	
	/**
	 * Setzt den Vater
	 * @param Vater Vater
	 */
	public void setVater(Person Vater) {
		this.Vater = Vater;
	}
	
	/**
	 * Setzt die Mutter
	 * @param Mutter Mutter
	 */
	public void setMutter(Person Mutter) {
		this.Mutter = Mutter;
	}
	
	/**
	 * Setzt den Vornamen
	 * @param a Vorname
	 */
	public void setVorname(String a) {
		this.Vorname = a;
	}
	
	/**
	 * Setzt den Nachnamen
	 * @param a Nachname
	 */
	public void setNachname(String a) {
		this.Nachname = a;
	}
	
	/**
	 * Setzt das Geschlecht
	 * @param a Geschlecht
	 */
	public void setWeiblich(boolean a) {
		this.weiblich = a;
	}
	
	/**
	 * Setzt den ID
	 * @return ID
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Gibt den Vornamen zurueck
	 * @return Vorname
	 */
	public String getVorname() {
		return this.Vorname;
	}
	
	/**
	 * Gibt den Nachnamen zurueck
	 * @return Nachname
	 */
	public String getNachname() {
		return this.Nachname;
	}
	/**
	 * Gibt das Geschlecht zurueck
	 * @return Geschlecht
	 */
	public boolean getWeiblich() {
		return this.weiblich;
	}
	
	/**
	 * Gibt die Mutter zurueck
	 * @return Mutter
	 */
	public Person getMutter() {
		if (this.Mutter != null) {
			return this.Mutter;
		}
		else {
			return null;
		}
		
	}
	
	/**
	 * Gibt den Vater zurueck
	 * @return Vater
	 */
	public Person getVater() {
		if (this.Vater != null) {
			return this.Vater;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Gibt die Eltern von this.Person zurueck
	 * @return die Eltern als Person
	 */
	public Person[] getEltern() {
		Person [] ret = new Person[2];
		if (this.getMutter() != null) {
			ret [0] = this.getMutter();
		}
		if (this.getVater() != null) {
			ret [1] = this.getVater();
		}
		return ret;
	}
	
	/**
	 * Gibt die Grosseltern von this.Person zurueck
	 * @return die Grosseltern als Person
	 */
	public Person[] getGrosseltern() {
		Person [] ret = new Person[4];
		if (this.getMutter() != null && this.getMutter().getMutter() != null) {
			ret [0] = this.getMutter().getMutter();
		}
		if (this.getMutter() != null && this.getMutter().getVater() != null) {
			ret [1] = this.getMutter().getVater();
		}
		if (this.getVater() != null && this.getVater().getMutter() != null) {
			ret [2] = this.getVater().getMutter();
		}
		if (this.getVater() != null && this.getVater().getVater() != null) {
			ret [3] = this.getVater().getVater();
		}
		return ret;
	}
	
	/**
	 * Gibt die ID der naechsten Person aus
	 * @return ID
	 */
	public static int getNextID() {
		return idnr;
	}
	
	/**
	 * Gibt den Vornamen und Nachnamen in ein String zurueck
	 * @return Nachname und Vorname
	 */
	public String LeseEigenschaft() {
		return (this.Vorname + " " + this.Nachname);
	}
	
	/**
	 * Estellt eine identische Kopie der Person ohne Eltern
	 * @return neue Person
	 */
	public Person clone() {
		return new Person(this.Vorname,this.Nachname,this.weiblich,null,null);
	}
	
	/**
	 * Gibt die Person als String zurueck
	 */
	@Override
	public java.lang.String toString() {
		String ret = this.id + ": " + this.LeseEigenschaft() + " w = " + this.weiblich;
		if (this.Mutter == null) {
			ret += " m = null";
		}
		else {
			ret += " m = " + this.Mutter.LeseEigenschaft();
		}
		if (this.Vater == null) {
			ret +=  " v = null";
		}
		else {
			ret += " v = " +this.Vater.LeseEigenschaft();
		}
		return ret;
	}
	
	/**
	 * Gibt alle Vorfahren als String zurueck
	 * @return Vorfahren
	 */
	public String getList() {
		String ret = this.toString() + "\n";
		Person Mutter = this.getMutter();
		Person Vater = this.getVater();
		if (Mutter != null || Vater != null) {
			ret += Mutter.getList();
			ret += Vater.getList();
		}
		return ret;
	}
}
