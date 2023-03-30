
public class Nr19_UmrechnungVonZehn
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Umrechnung vom Zehnersystem");
		System.out.println("===========================");
		int r;
		boolean stop = false;
		String erg = "";
		int z = TestScanner.readInt("Geben Sie die Zahl ein: ");
		int b = TestScanner.readInt("Geben Sie die Basis ein: ");
		while (b > 9 || b < 2) {
			System.out.println("Die Zahl muss zwischen 2 und 9 liegen");
			b = TestScanner.readInt("Geben Sie die Basis ein: ");
		}
		while (stop == false) {
			r = z%b;
			erg = r + erg;
			z = (z - r) / b;
			if (z == 0) {
				stop = true;
			}
		}
		System.out.println();
		System.out.println("Die Zahl im " + b + "-ersystem lautet " + erg);
	}
}