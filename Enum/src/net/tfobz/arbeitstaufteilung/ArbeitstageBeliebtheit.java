package net.tfobz.arbeitstaufteilung;

import java.util.EnumMap;
import java.util.Iterator;

/**
 * 
 * @author Daniel Lechner
 *
 * @param <ArbeitstageBeliebtheit>
 */
public class ArbeitstageBeliebtheit implements Iterable<Beliebtheit> {

	/**
	 * Enum Map
	 */
	private EnumMap<Wochentage, Beliebtheit> map;

	/**
	 * Leerer Konstruktor Legt eine leere EnumMap an
	 */
	public ArbeitstageBeliebtheit() {
		map = new EnumMap<Wochentage, Beliebtheit>(Wochentage.class);
	}

	/**
	 * Legt eine EnumMap an und füllt alle Arbeitstage tage mit der Beliebtheit bel
	 * 
	 * @param tage Arbeitstage
	 * @param bel  Beliebtheit
	 */
	public ArbeitstageBeliebtheit(Arbeitstage tage, Beliebtheit bel) {
		map = new EnumMap<Wochentage, Beliebtheit>(Wochentage.class);
		for (Wochentage wochentage : tage) {
			map.put(wochentage, bel);
		}
	}

	/**
	 * Setzt die beliebtheit an einen bestimmten Tag
	 * 
	 * @param tag Tag
	 * @param add Beliebtheit
	 * @return die Beliebtheit falls gesetzt
	 */
	public Beliebtheit put(Wochentage tag, Beliebtheit add) {
		return map.put(tag, add);
	}

	/**
	 * Löscht eine bestimmte Beliebtheit
	 * 
	 * @param remove Arbeitstag
	 * @return false falls der Arbeitstag nicht vorhanden ist
	 */
	public boolean remove(Beliebtheit remove) {
		return map.remove(Wochentage.class, remove);
	}

	/**
	 * Kontrolliert ob ein bestimmter Beliebtheit in der Arbeitswoche vorhanden ist
	 * 
	 * @param contains Beliebtheit
	 * @return true falls er vorhanden ist
	 */
	public boolean contains(Beliebtheit contains) {
		return map.containsValue(contains);
	}

	@Override
	public String toString() {
		return "ArbeitstageBeliebtheit " + map;
	}

	@Override
	public Iterator<Beliebtheit> iterator() {
		return new Iterator<Beliebtheit>() {

			int pos = 0;

			@Override
			public boolean hasNext() {
				return pos < map.size();
			}

			@Override
			public Beliebtheit next() {
				if (pos >= map.size())
					throw new NullPointerException();
				else {
					return map.get((Object)pos++);
				}
			}
		};
	}

}
