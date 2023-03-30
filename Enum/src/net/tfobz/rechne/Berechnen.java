package net.tfobz.rechne;

import java.util.Hashtable;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Berechnen {

	public static void main(String[] args) {
		rechne(1.45, 0.79, 19.9, "Ware", -3.0, 1.5, "Pfand", -10.0, "Gutschein");
		zaehleZahlen(6, 6, 3, 2, 4, 0, 1, 8, 1, 4);
	}

	public static void rechne(Object... o) {
		double sum = 0;
		double gesamt = 0;
		for (int i = 0; i < o.length; i++) {
			if (o[i] instanceof Number) {
				sum += (double) o[i];
			} else {
				System.out.println(o[i] + " : " + sum);
				gesamt += sum;
				sum = 0;
			}
		}
		System.out.println("Gesamtsumme: " + gesamt);
	}

	public static void zaehleZahlen(int... i) {
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
		for (int j : i) {
			if (!table.containsKey(j))
				table.put(j, 1);
			else
				table.put(j, table.get(j) + 1);
		}
		System.out.println(table);
	}
}
