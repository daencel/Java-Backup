
public class Quadrat extends java.lang.Object {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quadrat ab = new Quadrat();
		ab.setSeiteA(10);
		Quadrat ac = new Quadrat();
		ac = ab.clone();
		System.out.println(ab.toString());
		System.out.println(ac.toString());
		System.out.println("ab.equals(ac) ergibt " + ab.equals(ac));
		ab.setFlaeche(-1);
		ac.setSeiteA(3);
		System.out.println("ab.compareTo(ac) ergibt " + ab.compareTo(ac));
		System.out.println(ab.toString());
		System.out.println(ac.toString());
	}
	
	/**
	 * Quadratvariablen
	 */
	private double a;
	private double b;
	private double umfang;
	private double flaeche;
	
	 /**
	  * Liefer die Laenge der Seite A zurueck
	  * @return Lange der Seite A
	  */
	public double getSeiteA() {
		return a;
	}
	
	/**
	 * Liefer die Laenge der Seite B zurueck
	 * @return Lange der Seite B
	 */
	public double getSeiteB() {
		return b;
	}
	
	/**
	 * Liefert den Umfang
	 * @return Umfang
	 */
	public double getUmfang() {
		return umfang;
	}
	
	/**
	 * Liefert den Flaeche
	 * @return Flaeche
	 */
	public double getFlaeche() {
		return flaeche;
	}
	 /**
	  * Setzt die Seite A des Quadrats auf die Lange a
	  * @param a Laenge der Seite
	  */
	public void setSeiteA(double a) {
		if (a >= 0) {
			this.a = a;
			this.b = a;
			this.flaeche = a*a;
			this.umfang= 4*a;
		}
	}
	
	/**
	 * Setzt die Seite B des Quadrats auf die Laenge b
	 * @param b Laenge der Seite
	 */
	public void setSeiteB(double b) {
		if (b >= 0) {
			this.a = b;
			this.b = b;
			this.flaeche = b*b;
			this.umfang= 4*b;
		}
	}
	
	/**
	 * Setzt den Umfang auf den Wert umfang
	 * @param umfang neuer Umfang
	 */
	public void setUmfang(double umfang) {
		if (umfang >= 0) {
			this.umfang = umfang;
			this.a = umfang/4;
			this.b = umfang/4;
			this.flaeche = (umfang/4)*(umfang/4);
		}
	}
	
	/**
	 * Setzt die Flaeche auf den Wert flaeche
	 * @param flaeche neue Flaeche
	 */
	public void setFlaeche(double flaeche) {
		if (flaeche >= 0) {
			this.flaeche = flaeche;
			this.a = Math.sqrt(flaeche);
			this.b = Math.sqrt(flaeche);
			this.umfang = 4*Math.sqrt(flaeche);
		}
	}
	
	/**
	 * Erstellt ein Duplikat des Quadrates auf den die Methode aufgerufen wird.
	 * Das Duplikat vom Typ Quadrat wird zurück geliefert 
	 * @return Duplikat des Quadrats
	 */
	@Override
	public Quadrat clone() {
		Quadrat ret = new Quadrat();
		ret.setFlaeche(this.flaeche);
		ret.setSeiteA(this.a);
		ret.setSeiteB(this.b);
		ret.setUmfang(this.umfang);
		return ret;
	}
	
	/**
	 * Kontrolliert ob das Quadrat die selbe Flaeche hat wie jenes Quadrat, 
	 * welcher der Methode übergeben wird 
	 * @param a Quadrat, das der Methode uebergeben wird
	 * @return true, falls die beiden Quadrate die selbe Flaeche haben
	 */
	public boolean equals(Quadrat a) {
		boolean ret = false;
		double diff = this.flaeche - a.getFlaeche();
		if (diff < 0.00001 && diff > - 0.00001) {
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Kontrolliert ob ein Quadrat groesser, kleiner oder gleich gross verglichen mit 
	 * einen andern Quadrat ist
	 * @param a uebergebenes Quadrat
	 * @return -1 falls das Objekt kleiner als das übergebene Objekt a ist
	 * 0 falls das Objekt gleich dem übergebenen Objekt a ist
	 * 1 falls das Objekt größer als das übergebene Objekt a ist
	 */
	public int compareTo(Quadrat a) {
		int ret = 0;
		double diff = this.flaeche - a.getFlaeche();
		if (diff < 0) {
			ret = -1;
		}
		if (diff > 0) {
			ret = 1;
		}
		return ret;
	}
	
	/**
	 * Gibt die Stringentsprechung des Quadrates zurück.
	 */
	@Override
	public java.lang.String toString() {
		String ret = ("a = " + this.a + ", b = " + this.b + ", U = " + this.umfang + ", F = " + this.flaeche);
		return ret;
	}
}
