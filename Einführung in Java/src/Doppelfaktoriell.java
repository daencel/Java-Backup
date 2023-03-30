
public class Doppelfaktoriell
{

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int x = 2;
		int erg = 1;
		if (n % 2 == 0) {
			while (x <= n) {
				erg = erg * x;
				x = x + 2;
			}
		}
			else
				x = 1;
				while (x <= n) {
					erg = erg * x;
					x = x + 2;
				}
		System.out.print("Ergebnis: " +erg);
	}
}
