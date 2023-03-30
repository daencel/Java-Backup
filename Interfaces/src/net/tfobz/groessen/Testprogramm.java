package net.tfobz.groessen;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Testprogramm {

	public static void main(String[] args) {
		Groesse[] g = new Groesse[10];

		Auto a1 = new Auto();
		a1.setBreite(2500);
		a1.setLaenge(4000);
		a1.setHoehe(1500);
		g[0] = a1;

		Auto a2 = new Auto();
		a2.setBreite(2800);
		a2.setLaenge(5600);
		a2.setHoehe(1000);
		g[1] = a2;

		Auto a3 = new Auto();
		a3.setBreite(2100);
		a3.setLaenge(2200);
		a3.setHoehe(1800);
		g[2] = a3;

		Fussballfeld f0 = new Fussballfeld();
		g[3] = f0;

		Fussballfeld f1 = new Fussballfeld();
		g[4] = f1;

		Papierblatt p0 = new Papierblatt(0);
		g[5] = p0;

		Papierblatt p1 = new Papierblatt(1);
		g[6] = p1;

		Papierblatt p2 = new Papierblatt(2);
		g[7] = p2;

		Papierblatt p3 = new Papierblatt(3);
		g[8] = p3;

		Papierblatt p4 = new Papierblatt(4);
		g[9] = p4;

		System.out.println("Ausgabe unsortiert");
		for (int i = 0; i < g.length; i++) {
			System.out.println(i + ". " + g[i].toString());
		}
		sort(g);

		System.out.println();
		System.out.println("Ausgabe sortiert");
		for (int i = 0; i < g.length; i++) {
			System.out.println(i + ". " + g[i].toString());
		}

	}

	/**
	 * Sortiert die uebergebene Array anhan des Selection Sort Algorithmus
	 * 
	 * @param arr Array
	 */
	public static void sort(Groesse arr[]) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (arr[j].getGrundflaeche() < arr[min_idx].getGrundflaeche())
					min_idx = j;

			Groesse temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
		}
	}
}
