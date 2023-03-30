
public class Nr22_EigenschaftenDreieck
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Eigenschaften eines Dreiecks");
		System.out.println("============================");
		int q = 0;
		double hyp = 0;
		final double EPSILON = 1E-3;
		while (q == 0) {
			double zw = 0;
			double dr = 0;
			boolean pd = false;
			System.out.println();
			double a = TestScanner.readDouble("Seite a: ");
			double b = TestScanner.readDouble("Seite b: ");
			double c = TestScanner.readDouble("Seite c: ");
			if (!(a + b <= c || b + c <= a || c + a <= b)) {
				System.out.println();
				double u = a + b + c;
				System.out.println("Umfang: " + u);
				double s = (a + b + c )/2;
				double f = Math.sqrt(s*(s-a)*(s-b)*(s-c));
				System.out.println("Flaeche: " + Math.round(f*100.0)/100.0);
				if (a > b && a > c) {
					hyp = a;
					System.out.println("Hypothenuse: " + hyp);
					if (b > a) {
						zw = b;
						dr = c;
					}
					else {
						zw = c;
						dr = b;
					}
				}
				if (b > a && b > c) {
					hyp = b;
					System.out.println("Hypothenuse: " + hyp);
					if (a > c) {
						zw = a;
						dr = c;
					}
					else {
						zw = c;
						dr = a;
					}
				}
				if (c > b && c > a) {
					hyp = c;
					System.out.println("Hypothenuse: " + hyp);
					if (b > a) {
						zw = b;
						dr = a;
					}
					else {
						zw = a;
						dr = b;
					}
				}
				if (a == b && b == c) {
					System.out.println("Gleichseitiges Dreieck");
				}
				if (a == b && b != c || b == c && c != a || c == a && a != b) {
					System.out.println("Gleichschenkliges Dreieck"); 
				}
				double r1 = Math.sqrt(Math.pow(hyp,2) - Math.pow(zw, 2));
				if (((a - (int)a) == 0) && ((b - (int)b) == 0) && ((c - (int)c) == 0) && (Math.abs(r1 - dr) < EPSILON)) {
					System.out.println("pythagoreisches dreieck");
					pd = true;
				}
				if ((Math.abs(r1 - a) < EPSILON || Math.abs(r1 - b) < EPSILON || Math.abs(r1 - c) < EPSILON) && (pd == false))  {
					System.out.println("rechtwinkeliges Dreieck");
				}
			}
			else {
				System.out.println();
				System.out.println("unmoegliches Dreieck");
			}
			System.out.println();
			q = TestScanner.readInt("Nochmal (0), Beenden (1): ");
		}
	}
}