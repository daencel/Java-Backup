package net.tfobz.funktion;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class FunktionsPlotter {

	public static void printTable(Funktion funktion) {
		System.out.println("Wertetabelle - " + funktion.getClass().getSimpleName());
		for (double x = 0.0; x <= 5; x += 1) {
			System.out.println(x + " -> " + funktion.compute(x));
		}
	}

	public static void main(String[] args) {
		printTable(new Wurzel());
		System.out.println();
		printTable(new Quadrat());
	}
}
