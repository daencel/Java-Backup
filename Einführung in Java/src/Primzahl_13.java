
public class Primzahl_13
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int z = Integer.parseInt(args[0]);
		int x = 2;
		int erg;
		boolean istprimzahl = true;
			while (x<z && istprimzahl) {
				erg = z % x;
				x = x + 1;
				if (erg == 0) 
					istprimzahl = false;
		}
		if (istprimzahl)
			System.out.println(z + " ist eine Primzahl");
		else
			System.out.println(z + " ist keine Primzahl");
	}
}
