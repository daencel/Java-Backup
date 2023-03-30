
public class Nr13_QuersummenQuersumme
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int z;
		int q = 0;
		System.out.println("Quersumme");
		System.out.println("=========");
		z = TestScanner.readInt("Geben Sie die Zahl ein: ");
		System.out.println();
		String ausgabe = "";
		while (z>0) {
			q = q + z % 10;
			if (z / 10 > 0)
				ausgabe = " + " + z%10 + ausgabe;
			else
				ausgabe = z%10 + ausgabe;
			z = z / 10;
		}	
		System.out.print(ausgabe);
		System.out.print(" = " + q);
		System.out.print(" = ");
		z = q;
		q = 0;
		ausgabe = "";
		while (z>0) {
			q = q + z % 10;
			if (z / 10 > 0)
				ausgabe = " + " + z%10 + ausgabe;
			else
				ausgabe = z%10 + ausgabe;
			z = z / 10;
		}
		System.out.print(ausgabe);
		System.out.print(" = " + q);
	}
}