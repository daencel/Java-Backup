package net.tfobz.kontoverwaltung;

import java.util.Date;

//@formatter:off

/**
 * Die Klasse GehaltsKonto hat zum Unterschied zu einem normalen Konto einen Überziehungsrahmen,
 * welcher beim Buchen nicht unterschritten werden darf. Ein Startüberziehungsrahmen kann für
 * alle anzulegenden Gehaltskonten vorab eingestellt werden
 * <ul>
 * 	<li>
 * 		Jedes Gehaltkonto hat einen Überziehungsrahmen der beim Buchen nicht unterschritten werden darf
 * 	</li>
 * 	<li>
 * 		Überziehungsrahmen werden immer als negative Zahlen angegeben
 * 	</li>
 * 	<li>
 * 		Ein Startüberziehungsrahmen kann für alle anzulegenden Gehaltskonten vorab eingestellt werden
 * 	</li>
 * 	<li>
 * 		Gebucht wird auf dem Konto immer unter Berücksichtigung des Überziehungsrahmens.
 * 		Der Kontostand darf diesen nicht unterschreiten
 * 	</li>
 * 	<li>
 * 		Die Zinsen des Gehaltskonto werden auf dem aktuellen Kontostand gerechnet. Ist dieser negativ,
 * 		sind die Zinsen 0 ansonsten werden vom heutigen Tag bis zum Jahresende die Zinsen berechnet
 * 	</li>
 * 	<li>
 * 		Die Spesen des Gehaltskontos sind 0, wenn der Kontostand nicht negativ ist,
 * 		ansonsten belaufen sie sich auf 50
 * 	</li>
 * </ul>
 *
 * @author Daniel Lechner
 */
public class Gehaltskonto extends Konto {

    //@formatter:on

    /**
     * Legt den Startüberziehungsrahmen für alle neu anzulegenden Gehaltskonten fest
     */
    protected static double startueberziehung;

    /**
     * Der Überziehungsrahmen des Gehaltskontos
     */
    protected double ueberziehung;

    /**
     * Konstruktor, welcher beim Anlegen des Gehaltskontos den Überziehungsrahmen
     * automatisch vergibt. Beim Anlegen des Gehaltskontos wird automatisch der
     * Überziehungsrahmen, welcher durch setStartueberziehung gesetzt wurde, für das
     * Gehaltskonto eingestellt
     *
     * @throws KontoException wenn die Überziehung positiv ist
     */
    public Gehaltskonto() throws KontoException {
        setUeberziehung(startueberziehung);
    }

    /**
     * Setzt den Startüberziehungsrahmen für alle neu anzulegenden Gehaltskonten
     *
     * @param sstartueberziehung der zu setzen ist
     * @throws KontoException falls der Überziehungsrahmen größer als Null ist
     */
    public static void setStartueberziehung(double sstartueberziehung) throws KontoException {
        if (sstartueberziehung > 0)
            throw new KontoException("Der Startüberziehungsrahmen darf nicht größer als 0 sein");
        startueberziehung = sstartueberziehung;
    }

    /**
     * Gibt den Startüberziehungsrahmen zurück, der für alle neu zu erstellenden
     * Konten verwendet wird
     *
     * @return den Startüberziehungsrahmen für alle neu anzulegenden Konten
     */
    public static double getStartueberziehung() {
        return startueberziehung;
    }

    /**
     * Setzt für das Konto den Überziehungsrahmen. Der Überziehungsrahmen darf nicht
     * positiv sein
     *
     * @param sueberziehung darf nicht größer als Null sein
     * @throws KontoException falls der einzustellende Überziehungsrahmen größer als
     *                        Null ist
     */
    public void setUeberziehung(double sueberziehung) throws KontoException {
        if (sueberziehung > 0)
            throw new KontoException("Der Startüberziehungsrahmen darf nicht größer als 0 sein");
        this.ueberziehung = sueberziehung;
    }

    /**
     * Liefert den Überziehungsrahmen des Kontos zurück
     *
     * @return den Überziehungsrahmen des Kontos
     */
    public double getUeberziehung() {
        return ueberziehung;
    }

    /**
     * Rechnet vom heutigen Datum bis zum Jahresende die Zinsen des Kontos aus, aber
     * nur dann wenn der Kontostand positiv ist. Ansonsten sind die Zinsen 0.
     * Befindet man sich z. B. genau in der Mitte des Jahres (30.6.) dann werden
     * zinnsatz/2 Zinsen berechnet
     *
     * @return die angefallenen Zinsen von heute bis zum Jahresende
     */
    @SuppressWarnings("deprecation")
	public double getZinsen() {
        if (this.kontostand > 0) {
            Date date = new Date();
            long now = date.getTime();
			double beginOfYear = new Date(date.getYear(), 1, 1, 0, 0).getTime();
            double endOfYear = new Date(date.getYear(), 12, 31, 23, 59).getTime();

            double proportion = (now - beginOfYear) / (endOfYear - beginOfYear);
            return this.getZinssatz() / proportion;
        } else
            return 0;
    }

    /**
     * Ermittelt die Spesen des Gehaltskonto. Diese sind 50, wenn das Gehaltskonto
     * einen negativen Kontostand ausweist, ansonsten sind diese 0
     *
     * @return die Spesen des Gehaltskontos
     */
    public double getSpesen() {
        if (this.kontostand < 0)
            return 50;
        else
            return 0;
    }

    /**
     * Bucht unter Berücksichtigung des Überziehungsrahmens. Wenn eine Buchung den
     * Überziehungsrahmen des Gehaltskontos sprengt, wird ein KontoException
     * ausgelöst
     *
     * @param betrag - der zu verbuchende Betrag
     * @return wenn der Überziehungsrahmen gesprengt wird
     */
    public void buchen(double betrag) throws KontoException {
        if (betrag < this.ueberziehung) {
            throw new KontoException("Betrag sprängt den Überziehungsrahmen");
        } else {
            this.kontostand += betrag;
        }
    }

    /**
     * Ausgabe der Kontodaten. Es wird zusätzlich noch die eingstellte Überziehung
     * ausgegeben
     *
     * @return Kontodaten
     */
    @Override
    public String toString() {
        return "Kontonummer: " + kontonummer + ", Kontostand: " + kontostand + ", Zinssatz: " + zinssatz + ", Zinsen: "
                + Math.round(getZinsen() * 1000.0) / 1000.0 + ", Spesen: " + getSpesen() + ", Überzeihung: "
                + ueberziehung;
    }

}
