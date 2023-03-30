package net.tfobz.ratenrechner;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class RatenRechner {

	// Gibt an ob das Bezahlen nachschuessig ist oder nicht
	private boolean nachschuessig;
	// Barwert
	private double barwert;
	// Jahreszinssatz
	private double jahreszinssatz;
	// Laufzeit in Jahren
	private double laufzeitInJahren;
	// Rate pro Jahr
	private double ratenProJahr;
	// Rate
	private double rate;
	// Konstante fuer Anzahl der Kommastellen
	private final double DIVIDENT = 100.0;

	/**
	 * Berechnet die Raten anhand der anderen Werte. Ist ein Wert nicht
	 * ordnungsgemaes gefuellt wird ein Fehler ausgegeben
	 * 
	 * @return Rate als String
	 * @throws RatenRechnerException
	 */
	public String calcRaten() throws RatenRechnerException {
		if (barwert <= 0.)
			throw new RatenRechnerException("Barwert nicht gesetzt");
		if (jahreszinssatz <= 0.)
			throw new RatenRechnerException("Jahreszinssatz nicht gesetzt");
		if (laufzeitInJahren <= 0.)
			throw new RatenRechnerException("Laufzeit in Jahren nicht gesetzt");
		if (ratenProJahr <= 0)
			throw new RatenRechnerException("Raten pro Jahr nicht gesetzt");

		final double n = laufzeitInJahren * ratenProJahr;
		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			rate = barwert * (Math.pow(q, n) * (q - 1.)) / (Math.pow(q, n) - 1.);
		else
			rate = barwert * (Math.pow(q, n - 1.) * (q - 1.)) / (Math.pow(q, n) - 1.);
		return Math.round(rate * DIVIDENT) / DIVIDENT + "";
	}

	/**
	 * Berechnet die Laufzeit in Jahren anhand der anderen Werte. Ist ein Wert nicht
	 * ordnungsgemaes gefuellt wird ein Fehler ausgegeben
	 * 
	 * @return Laufzeit in Jahren als String
	 * @throws RatenRechnerException
	 */
	public String calcLaufzeitInJahren() throws RatenRechnerException {
		if (barwert <= 0.)
			throw new RatenRechnerException("Barwert nicht gesetzt");
		if (jahreszinssatz <= 0.)
			throw new RatenRechnerException("Jahreszinssatz nicht gesetzt");
		if (ratenProJahr <= 0)
			throw new RatenRechnerException("Raten pro Jahr nicht gesetzt");
		if (rate <= 0.)
			throw new RatenRechnerException("Rate nicht gesetzt");

		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;

		if (nachschuessig)
			laufzeitInJahren = (-Math.log((rate - barwert * (q - 1.)) / rate) / Math.log(q)) / ratenProJahr;
		else
			laufzeitInJahren = (1. - Math.log((q * rate - barwert * (q - 1.)) / rate) / Math.log(q)) / ratenProJahr;

		return Math.round(laufzeitInJahren * DIVIDENT) / DIVIDENT + "";
	}

	/**
	 * Berechnet den Barwert anhand der anderen Werte. Ist ein Wert nicht
	 * ordnungsgemaes gefuellt wird ein Fehler ausgegeben
	 * 
	 * @return Barwert als String
	 * @throws RatenRechnerException
	 */
	public String calcBarwert() throws RatenRechnerException {
		if (jahreszinssatz <= 0.)
			throw new RatenRechnerException("Jahreszinssatz nicht gesetzt");
		if (laufzeitInJahren <= 0.)
			throw new RatenRechnerException("Laufzeit nicht gesetzt");
		if (ratenProJahr <= 0)
			throw new RatenRechnerException("Raten pro Jahr nicht gesetzt");
		if (rate <= 0.)
			throw new RatenRechnerException("Rate nicht gesetzt");

		final double n = laufzeitInJahren * ratenProJahr;
		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			barwert = rate * (Math.pow(q, n) - 1.) / (Math.pow(q, n) * (q - 1.));
		else
			barwert = rate * (Math.pow(q, n) - 1.) / (Math.pow(q, n - 1.) * (q - 1.));
		return Math.round(barwert * DIVIDENT) / DIVIDENT + "";
	}

	/**
	 * Gibt den Tilgungsplan in HTML-Format als String zurueck
	 * 
	 * @return Tilgungsplan im HTML Format
	 */
	public String getTilgungsplan() throws RatenRechnerException {
		//@formatter:off
		String ret = "<!DOCTYPE html>\n"
				+ "<html>\n"
					+ "<head>\n"
						+ "<title> Tilgungsplan </title>\n"
					+ "</head>\n"
					+ "<body>\n";
		String status;
		if(isNachschuessig())
			status = "Nachschuessig";
		else
			status = "Vorsschuessig";
		ret += "<h1> Tilgungsplan </h1>\n"
			+ "<table border=\"3\" frame=\"void\">\n" 
				+ "<tr>\n" 
					+ "<td>Zahlungsart</td>\n" 
					+ "<td>" + status + "</td>"
				+ "</tr>\n"
				+ "<tr>\n" 
					+ "<td>Barwert</td>\n" 
					+ "<td>" + getBarwert() + "</td>"
				+ "</tr>\n"
				+ "<tr>\n" 
					+ "<td>Jahreszinssatz</td>\n" 
					+ "<td>" + getJahreszinssatz() + "%</td>\n" 
				+ "</tr>\n"
				+ "<tr>\n" 
					+ "<td>Laufzeit in Jahren</td>\n" 
					+ "<td>" + getLaufzeitInJahren() + "</td>"
				+ "</tr>\n"
				+ "<tr>\n" 
					+ "<td>Rueckzahlungsart</td>\n" 
					+ "<td>" + getRatenProJahr() + "</td>"
				+ "</tr>\n"
				+ "<tr>\n" 
					+ "<td>Rate</td>\n" 
					+ "<td>" + getRate() + "</td>"
				+ "</tr>\n"
			+ "</table>";
		ret += "<br><br><table border=\"1\" frame=\"void\" width=\"500\">\n"
			+ "<tr>\n" 
				+ "<th>Periode</th>\n" 
				+ "<th>Rate</th>\n" 
				+ "<th>Restkapital</th>\n" 
				+ "<th>Zinsen</th>\n" 
			+ "</tr>\n";
		//@formatter:on
		double restkapital = this.barwert;
		final double q = 1. + (this.jahreszinssatz / this.ratenProJahr) / 100.;
		for (int i = 0; i < (int) (this.laufzeitInJahren * this.ratenProJahr); i++) {
			final double zinsen;
			if (this.nachschuessig) {
				zinsen = restkapital * (q - 1);
				restkapital = restkapital * q - this.rate;
			} else {
				zinsen = (restkapital - this.rate) * (q - 1);
				restkapital = restkapital - this.rate + zinsen;
			}
			if (Math.abs(restkapital) < 1) {
				restkapital = 0;
			}
			//@formatter:off
			ret+= "<tr>\n" 
					+ "<td>" + (i + 1) + "</td>"
					+ "<td>" + this.getRate() + " &#8364</td>"
					+ "<td>" + Math.round(restkapital * DIVIDENT) / DIVIDENT + " &#8364</td>"
					+ "<td>" + Math.round(zinsen * DIVIDENT) / DIVIDENT + " &#8364</td>"
				+ "</tr>\n";
		}
		ret += "</table>"
			+"</body>\n"
		+ "</html>\n";
		return ret;
		//@formatter:on
	}

	/**
	 * Gibt den Status von Nachschuessig zurueck
	 * 
	 * @return the nachschuessig
	 */
	public boolean isNachschuessig() {
		return nachschuessig;
	}

	/**
	 * Setzt den Status von Nachschuessig
	 * 
	 * @param nachschuessig the nachschuessig to set
	 */
	public void setNachschuessig(boolean nachschuessig) {
		this.nachschuessig = nachschuessig;
	}

	/**
	 * Gibt den Barwert zurueck
	 * 
	 * @return the barwert
	 */
	public String getBarwert() {
		return barwert + "";
	}

	/**
	 * Setzt den barwert
	 * 
	 * @param barwert the barwert to set
	 */
	public void setBarwert(String barwert) throws RatenRechnerException {
		if (barwert == null) {
			throw new RatenRechnerException("Barwert kann nicht null");
		} else {
			double temp = -1;
			try {
				temp = Double.parseDouble(barwert);
			} catch (Exception e) {
				throw new RatenRechnerException("Fehler beim cast");
			}
			if (temp <= 0) {
				throw new RatenRechnerException("Barwert kann nicht kleiner gleich 0 sein");
			} else {
				this.barwert = temp;
			}
		}
	}

	/**
	 * Gibt den jahreszinssatz zurueck
	 * 
	 * @return the jahreszinssatz
	 */
	public String getJahreszinssatz() {
		return jahreszinssatz / 100 + "";
	}

	/**
	 * Setzt den jahreszinssatz
	 * 
	 * @param jahreszinssatz the jahreszinssatz to set
	 */
	public void setJahreszinssatz(String jahreszinssatz) throws RatenRechnerException {
		if (jahreszinssatz == null) {
			throw new RatenRechnerException("Jahreszinssatz kann nicht null");
		} else {
			double temp = -1;
			try {
				temp = Double.parseDouble(jahreszinssatz);
			} catch (Exception e) {
				throw new RatenRechnerException("Fehler beim cast");
			}
			if (temp <= 0) {
				throw new RatenRechnerException("Jahreszinssatz kann nicht kleiner gleich 0 sein");
			} else {
				this.jahreszinssatz = temp;
			}
		}
	}

	/**
	 * Gibt die Laufzeit in Jahren zurueck
	 * 
	 * @return the laufzeitInJahren
	 */
	public String getLaufzeitInJahren() {
		return laufzeitInJahren + "";
	}

	/**
	 * Setzt die Laufzeit in Jahren
	 * 
	 * @param laufzeitInJahren the laufzeitInJahren to set
	 */
	public void setLaufzeitInJahren(String laufzeitInJahren) throws RatenRechnerException {
		if (laufzeitInJahren == null) {
			throw new RatenRechnerException("Die Laufzeit in Jahren kann nicht null");
		} else {
			double temp = -1;
			try {
				temp = Double.parseDouble(laufzeitInJahren);
			} catch (Exception e) {
				throw new RatenRechnerException("Fehler beim cast");
			}
			if (temp <= 0) {
				throw new RatenRechnerException("Die Laufzeit in Jahren kann nicht kleiner gleich 0 sein");
			} else {
				this.laufzeitInJahren = temp;
			}
		}
	}

	/**
	 * Gibt die Raten pro Jahr zurueck
	 * 
	 * @return the ratenProJahr
	 */
	public String getRatenProJahr() {
		return (Math.round(ratenProJahr) + " Raten pro Jahr");
	}

	/**
	 * Setzt die Raten pro Jahr
	 * 
	 * @param ratenProJahr the ratenProJahr to set
	 */
	public void setRatenProJahr(String srate) throws RatenRechnerException {
		if (srate == null) {
			throw new RatenRechnerException("Die Raten pro Jahr koennen nicht null");
		} else {
			if (srate.contains("12"))
				this.ratenProJahr = 12;
			else if (srate.contains("4"))
				this.ratenProJahr = 4;
			else if (srate.contains("6"))
				this.ratenProJahr = 6;
			else if (srate.contains("1"))
				this.ratenProJahr = 1;
		}
	}

	/**
	 * Gibt die Rate zurueck
	 * 
	 * @return the rate
	 */
	public String getRate() {
		return (rate + "");
	}

	/**
	 * Setzt die Rate
	 * 
	 * @param srate the rate to set
	 */
	public void setRate(String srate) throws RatenRechnerException {
		if (srate == null) {
			throw new RatenRechnerException("Die Rate kann nicht null");
		} else {
			double temp = -1;
			try {
				temp = Double.parseDouble(srate);
			} catch (Exception e) {
				throw new RatenRechnerException("Fehler beim cast");
			}
			if (temp <= 0) {
				throw new RatenRechnerException("Die Raten pro Jahr koennen nicht kleiner gleich 0 sein");
			} else {
				this.rate = temp;
			}
		}
	}
}