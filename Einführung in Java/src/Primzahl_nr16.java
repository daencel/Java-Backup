
public class Primzahl_nr16
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int z = 89;
		int erg;
		boolean istprimzahl = true;
		System.out.println("    Primzahlentabelle:");
		System.out.println("===========================");
		while (z < 120) {
			z = z + 1;
			int x = 2;
			istprimzahl = true;
			while (x<z && istprimzahl) {
				erg = z % x;
				x = x + 1;
				if (erg == 0) 
					istprimzahl = false;
			}
			if (istprimzahl)
				System.out.println(z + "	 ist eine Primzahl!");
			else
				System.out.println(z + "	ist keine Primzahl!");
		}
	}
}
