public class Nr18_UmrechnungNachZehn
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Umrechnung ins Zehnersystem");
		System.out.println("===========================");
		int n;
		int x;
		int r = 0;
		int erg = 0;
		int z = TestScanner.readInt("Geben Sie die Zahl ein: ");
		int b = TestScanner.readInt("Geben Sie die Basis ein: ");
		while (b > 9 || b < 2) {
			System.out.println("Die Zahl muss zwischen 2 und 9 liegen");
			b = TestScanner.readInt("Geben Sie die Basis ein: ");
		}
		while (z > 0) {
			n = z%10;
			z = z / 10;
			x = (int)Math.pow(b, r);
			r++;
			erg = erg + x * n;
		}
		System.out.println();
		System.out.println("Die Zahl im Zehnersystem lautet " + erg);
	}
}