package net.tfobz.arbeitstaufteilung;

import java.util.EnumSet;
import java.util.Iterator;

/**
 * 
 * @author Daniel Lechner
 *
 * @param <ArbeitstageBeliebtheit>
 */
public class Arbeitstage implements Iterable<Wochentage> {

	/**
	 * Arbeitswoche
	 */
	public EnumSet<Wochentage> arbeitswoche;
	
	/**
	 * Füllt die Arebitswoche mit allen Wochentagen
	 */
	public Arbeitstage() {
		arbeitswoche = EnumSet.allOf(Wochentage.class);
	}

	/**
	 * Füllt die Arbeitswoche mit den Tagen zwischen den beiden angegebenen
	 * 
	 * @param tag1 Start
	 * @param tag2 Ende
	 */
	public Arbeitstage(Wochentage tag1, Wochentage tag2) {
		arbeitswoche = EnumSet.range(tag1, tag2);
	}

	/**
	 * Fügt einen bestimmten Arebitstag hinzu
	 * 
	 * @param add Arbeitstag
	 * @return false falls der Arbeitstag bereits vorhanden ist
	 */
	public boolean add(Wochentage add) {
		return arbeitswoche.add(add);
	}

	/**
	 * Löscht einen bestimmten Arbeitstag
	 * 
	 * @param remove Arbeitstag
	 * @return false falls der Arbeitstag nicht vorhanden ist
	 */
	public boolean remove(Wochentage remove) {
		return arbeitswoche.remove(remove);
	}

	/**
	 * Kontrolliert ob ein bestimmter Arebitstag in der Arbeitswoche vorhanden ist
	 * 
	 * @param contains Arbeitstag
	 * @return true falls er vorhanden ist
	 */
	public boolean contains(Wochentage contains) {
		return arbeitswoche.contains(contains);
	}

	/**
	 * Kontrolliert ob eine Arbeitswoche eine Fünftage-Woche oder eine Sechs-Tage
	 * Woche ist
	 * 
	 * @return Wochenart
	 */
	public Wochenart getWochenart() {
		if (arbeitswoche.size() > 4 && !arbeitswoche.contains(Wochentage.SONNTAG)) {
			if (!arbeitswoche.contains(Wochentage.SAMSTAG)) 
				return Wochenart.FUENFTAGE;
			else
				return Wochenart.SECHSTAGE;
		} else
			return null;
	}

	@Override
	public String toString() {
		return "Arbeitstage " + arbeitswoche;
	}

	@Override
	public Iterator<Wochentage> iterator() {
		return new Iterator<Wochentage>() {
			
			int pos = 0;

			@Override
			public Wochentage next() {
				if (pos >= arbeitswoche.size())
					throw new NullPointerException();
				else {
					return (Wochentage)arbeitswoche.toArray()[pos++];
				}
			}

			@Override
			public boolean hasNext() {
				return pos < arbeitswoche.size();
			}
		};
	}
}
