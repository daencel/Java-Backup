
public class Kreis extends java.lang.Object {
	
	private double flaeche;
	private double umfang;
	private double radius;
	
	/**
	 * Erstellt ein Duplikat des Kreises auf den die Methode aufgerufen wird.
	 * Das Duplikat vom Typ Kreis wird zur�ck geliefert 
	 * @return das Duplikat des Kreises
	 */
	@Override
	public Kreis clone() {
		Kreis ret = new Kreis();
		ret.setFlaeche(this.flaeche);
		ret.setRadius(this.radius);
		ret.setUmfang(this.umfang);
		return ret;
	}
	
	/**
	 * Setzt die Fl�che des Kreises. Die �bergebene Fl�che darf nicht 0 sein. 
	 * Die Methode errechnet sich aus der Fl�che den Radius und setzt diesen 
	 * @param flaeche die zu setzende Fl�che
	 */
	public void setFlaeche(double flaeche) {
		if (flaeche > 0) {
			this.flaeche = flaeche;
			double r = Math.sqrt(flaeche/Math.PI);
			this.radius = r;
			this.umfang = (2 * Math.PI * r);
		}
		
	}
	 /**
	  * Liefert die Fl�che des Kreises zur�ck 
	  * @return die Fl�che des Kreises zur�ck 
	  */
	public double getFlaeche() {
		return flaeche;
	}

	
	/**
	 * Setzt den Radius. Der Radius wird nur gesetzt, falls der �bergebene 
	 * Parameter radius gr��er oder gleich 0 ist, ansonsten wird der Radius nicht 
	 * gesetzt und der alte Wert des Radiuses beibehalten. 
	 * @param radius der zu setzende Radius
	 */
	public void setRadius(double radius) {
		if (radius >= 0) {
			this.radius = radius;
			this.flaeche = (radius * radius * Math.PI);
			this.umfang = (2 * radius * Math.PI);
		}
	}
	
	/**
	 * Liefert den Radius des Kreises zur�ck 
	 * @return den Radius des Kreises
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Setzt den Umfang des Kreises. Der �bergebene Umfang darf nicht 0 sein.
	 * Die Methode errechnet sich aus dem Umfang den Radius und setzt diesen 
	 * @param umfang der zu setzende Umfang
	 */
	public void setUmfang(double umfang) {
		if (umfang > 0) {
			this.umfang = umfang;
			this.flaeche = (umfang/2 * umfang/2 *Math.PI);
			this.radius = umfang/2;
		}
	}
	
	/**
	 * Liefert den Umfang des Kreises zur�ck. Dieser wird aus dem Radius berechnet 
	 * @return den Umfang des Kreises
	 */
	public double getUmfang() {
		return umfang;
	}
	
	/**
	 * Gibt die Stringentsprechung des Kreises zur�ck. Ist der Radius beispielsweise gleich 1,
	 *  so wird folgender String zur�ck gegeben:
	 *  "r = 1.0, U = 6.283185307179586, F = 3.141592653589793" 
	 */
	@Override
	public java.lang.String toString() {
		String ret = ("r = " + this.radius + ", U = " + this.umfang + ", F = " + this.flaeche);
		return ret;
	}
	
	/**
	 * Kontrolliert ob der Kreis denselben Radius hat wie jener Kreis, 
	 * welcher der Methode �bergeben wird 
	 * @param k das zu vergleichende Objekt 
	 * @return true, falls der Kreis und das �bergebene Kreisobjekt denselben Radius haben
	 */
	public boolean equals(Kreis k) {
		double diff = this.radius - k.getRadius();
		boolean ret = false;
		if (diff < 0.00001 && diff > - 0.00001) {
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Kontrolliert, ob er Kreis kleiner als das �bergebene Kreisobjekt k ist (R�ckgabewert = -1),
	 * gr��er (R�ckgabewert = 1) oder gleich dem �bergebenen Kreisobjekt k ist (R�ckgabewert = 0) 
	 * @param k das zu vergleichende Objekt 
	 * @return -1 falls das Objekt kleiner als das �bergebene Objekt k ist
	 * 0 falls das Objekt gleich dem �bergebenen Objekt k ist
	 * 1 falls das Objekt gr��er als das �bergebene Objekt k ist
	 */
	public int compareTo(Kreis k) {
		double diff = this.flaeche - k.getFlaeche();
		int ret = 0;
		if (diff < 0) {
			ret = -1;
		}
		if (diff > 0) {
			ret = 1;
		}
		return ret;
	}

}
