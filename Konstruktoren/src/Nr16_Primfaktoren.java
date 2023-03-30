
public class Nr16_Primfaktoren
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Primfaktorzerlegung");
		System.out.println("===================");
		int a = -5;
		int t = 2;
		int r;
		int erg = 1;
		String ausgabe = "";
		while (a<2) {
			a = TestScanner.readInt("Geben Sie die Zahl ein: ");
			if (a<2)
				System.out.println("Die Zahl muss groesser als 1 sein.");
		}
		int h = a;
		while (t <= a) {
			r = a%t;
			if ((erg * t) == h) {
				ausgabe = ausgabe + t;
				break;
			}
			if (r == 0) {
				a = a/t;
				ausgabe = ausgabe + t + " * ";
				erg = erg * t;
			}
			if (r != 0)	{
				t++;
			}
		}
		System.out.println();
		System.out.println(ausgabe + " = " + h);
	}
}