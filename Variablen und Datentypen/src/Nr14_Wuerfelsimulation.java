
public class Nr14_Wuerfelsimulation
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 0;
		int r;
		int z;
		int i = 1;
		int anz1 = 0;
		int anz2 = 0;
		int anz3 = 0;
		int anz4 = 0;
		int anz5 = 0;
		int anz6 = 0;
		System.out.println("Wuerfelsimulation");
		System.out.println("=================");
		z = TestScanner.readInt("Geben sie die Anzahl der Wuerfe an: ");
		System.out.println("Bitte warten");
		while (x<=z) {
			x = x + 1;
			r = (int)(Math.random()*6+1);
			if (x == (i*(z/15))) {
				System.out.print(".");
				i = i + 1;
			}
			switch (r) {
				case 1: {
					anz1 = anz1 + 1;
					break;
				}
				case 2: {
					anz2 = anz2 + 1;
					break;
				}
				case 3: {
					anz3 = anz3 + 1;
					break;
				}
				case 4: {
					anz4 = anz4 + 1;
					break;
				}
				case 5: {
					anz5 = anz5 + 1;
					break;
				}
				case 6: {
					anz6 = anz6 + 1;
					break;
				}
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("Zahln 1: " + anz1);
		System.out.println("Zahln 2: " + anz2);
		System.out.println("Zahln 3: " + anz3);
		System.out.println("Zahln 4: " + anz4);
		System.out.println("Zahln 5: " + anz5);
		System.out.println("Zahln 6: " + anz6);
	}
}