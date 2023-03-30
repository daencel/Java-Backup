
public class Teiler
{

	public static void main(String[] args) {
		int z = Integer.parseInt(args[0]);
		int x = 1;
		int erg;
		while (x<=z) {
			erg = z % x;
			if (erg==0) {
				System.out.print(+ x + "; ");
			}
			x = x + 1;
		}
	}
}
