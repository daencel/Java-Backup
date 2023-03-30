package net.tfobz.kontoverwaltung;

//@formatter:off

/**
 * Ein Sparkonto hat keinen Überziehungsrahmen, dafür aber eine Sparrate und darf keine negativen
 * Kontostände aufweisen. Bei jedem Sparkonto kann eine individuelle Sparrate eingestellt werden,
 * welche durch eine eigene Operation (buchenSparrate) zum Kontostand dazugebucht wird.
 * Es kann nur unter Berücksichtigung bestimmter Bedingungen vom Sparkonto abgebucht werden
 * <ul>
 * 	<li>
 * 		Ein Sparkonto darf keine negativen Kontostände aufweisen. Es hat zum Unterschied zum
 * 		Gehaltskonto keinen Überziehungsrahmen
 * 	</li>
 * 	<li>
 * 		Es kann eine individuelle Sparrate eingestellt werden, welche durch eine eigene Operation zum
 * 		Kontostand dazugebucht wird
 * 	</li>
 * 	<li>
 * 		Beim Anlegen eines Sparkontos wird der positive Startkontostand und die positive Sparrate eingestellt
 * 	</li>
 * 	<li>
 * 		Beim Buchen vom Sparkonto muss berücksichtig werden, dass der Kontostand nicht negativ sein darf.
 * 		Wird abgebucht, so muss anhand einer Buchung der Kontostand auf 0 reduziert werden.
 * 		Abbuchungen bis 3000 sind erlaubt. Dabei kann der Kontostand auch nicht auf 0 zurück gehen
 * 	</li>
 * 	<li>
 * 		Die Zinsen des Sparkontos werden immer vom aktuellen Kontostand auf ein Jahr berechnet
 * 	</li>
 * 	<li>
 * 		Die Spesen des Sparkontos belaufen sich auf 0.1% des aktuellen Kontostandes
 * 	</li>
 * </ul>
 *
 * @author Daniel Lechner
 */
public class Sparkonto extends Konto {

    //@formatter:on

    /**
     * Die für jedes Sparkonto festlegbare Sparrate
     */
    protected double sparrate;

    /**
     * Ein Sparkonto wird erstellt und die erste Zahlung wird auf das Sparkonto
     * gebucht. Dabei ist zu berücksichtigen, dass die erste Zahlung größer als 0
     * sein muss. Ist dies nicht der Fall, so darf das Sparkonto nicht angelegt
     * werden. Weiter wird dem Konstruktor die Höhe der Sparrate übergeben. Auch
     * diese muss größer als 0 sein. In beiden Fällen muss ein KontoException
     * geworfen werden und eine bereits für das Sparkonto reservierte Kontonummer
     * muss wiederum freigegeben werden.
     *
     * @param ersteZahlung stellt den Betrag dar, welcher beim Anlegen des
     *                     Sparkontos auf das Konto gebucht werden soll
     * @param sparrate     legt fest wie hoch die Sparrate des Kontos sein soll
     * @throws KontoException wird ausgelöst, wenn die Erste Zahlung oder die
     *                        Sparrate nicht größer als 0 sind
     */
    public Sparkonto(double ersteZahlung, double sparrate) throws KontoException {
        super();
        if (ersteZahlung <= 0) {
            Konto.naechsteKontonummer--;
            throw new KontoException("Die erste Zahlung muss groesser als 0 sein");
        }
        if (sparrate <= 0) {
            Konto.naechsteKontonummer--;
            throw new KontoException("Die Sparrate muss groesser als 0 sein");
        }
        this.setSparrate(sparrate);
        this.kontostand = ersteZahlung;
        buchenSparrate();
    }

    /**
     * Setzt die Sparrate für das Konto. Die zu setzende Sparrate darf nicht kleiner
     * oder gleich 0 sein
     *
     * @param sparrate die zu setzende Sparrate
     * @throws KontoException wenn die zu setzende Sparrate kleiner oder gleich 0
     *                        ist
     */
    public void setSparrate(double sparrate) throws KontoException {
        this.sparrate = sparrate;
    }

    /**
     * Liefert die für das Sparkonto eingestellte Sparrate zurück
     *
     * @return die eingestellte Sperrate
     */
    public double getSparrate() {
        return this.sparrate;
    }

    /**
     * Rechnet vom aktuellen Kontostand die Jahreszinsen aus, also jene Zinsen die
     * für ein gesamtes Jahr anfallen
     *
     * @return die Jahreszinsen
     */
    public double getZinsen() {
        return this.kontostand * this.getZinssatz() / 100;
    }

    /**
     * Die Spesen des Sparkontos belaufen sich auf 0.1% des aktuellen Kontostandes
     *
     * @return die Spesen des Sparkontos
     */
    public double getSpesen() {
        return this.kontostand * 0.001;
    }

    /**
     * Die für das Sparkonto eingestellte Sparrate wird gebucht. Dabei tritt kein
     * KontoException auf weil die zu verbuchende Sparrate immer positiv ist
     */
    public void buchenSparrate() {
        //
    }

    /**
     * Bucht den übergebenen Betrag zum Sparkonto dazu bzw. vom Sparkonto ab. Dabei
     * wird berücksichtig, dass der Kontostand nicht negativ sein darf. Weiters darf
     * immer nur zum Sparkonto dazugebucht werden. Wird abgebucht, dann muss der
     * Kontostand mit einer einzigen Buchung auf 0 zurückgesetzt werden. Abbuchungen
     * von bis zu 3000 sind erlaubt. Dabei kann der Kontostand auch nicht bis auf 0
     * zurückgehen
     *
     * @param betrag der zum Sparkonto dazugebucht bzw. abgebucht wird
     * @throws KontoException wird ausgelöst, wenn beim Buchen obige Bedingungen
     *                        nicht eingehalten werden
     */
    public void buchen(double betrag) throws KontoException {
        if (this.kontostand + betrag < 0)
            throw new KontoException("Der Betrag ist zu hoch, Kontostand geht ins negativ");
        if (betrag < -3000) {
            if (this.kontostand + betrag != 0) {
                throw new KontoException("Beim Betrag über 3000, muss der Kontostand 0 sein");
            } else {
                this.kontostand += betrag;
            }
        } else {
            this.kontostand += betrag;
        }
    }

    /**
     * Ausgabe der Sparkontodaten. Es wird zusätzlich noch die Sparrate ausgegeben
     */
    @Override
    public String toString() {
        return "KontoNummer: " + kontonummer + ", Kontostand: " + kontostand + ", Zinssatz: " + zinssatz + ", Zinsen: "
                + this.getZinsen() + ", Spesen: " + getSpesen() + ", Sparrate: " + sparrate;
    }
}