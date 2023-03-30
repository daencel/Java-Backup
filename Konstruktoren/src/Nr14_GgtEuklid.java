
public class Nr14_GgtEuklid
{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Ggt von Euklid");
		System.out.println("==============");
		int a = TestScanner.readInt("1. Zahl: ");
		int b = TestScanner.readInt("2. Zahl: ");
		int r;
		int h = 10;
		while (h != 0) {
			r = a%b;
			a = b;
			b = r;
			h = r;
			if (r == 0) {
				System.out.println();
				System.out.println("Der groesste gemeinsame Teiler lautet " + a);
			}
		}
	}

}
