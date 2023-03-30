
public class Nr23_PythagoreischeTripel
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.setOut(new java.io.PrintStream("C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\PythagoreischeTripel.csv"));
			System.out.println("Seite a;Seite b;Seite c");
			int a = 1;
			int b = 1;
			int c = 1;
			int d = 0;
			int f = 0;
			final double EPSILON = 1E-10;
			while (a <= 1000) {
				if (a >= 10) {
					if (c == 1000) {
						b++;
						c = 0;
					}
						if (b == 1000) {
							a++;
							b = 0;
							c = 0;
						}
				}
				else {
					c++;
				}
				if (a < 10) {
					if (c == 10) {
						b++;
						c = 0;
					}
						if (b == 10) {
							a++;
							b = 0;
							c = 0;
						}
				}
					else {
						c++;
					}
				if (a <= b || b <= c) {
					double r1 = Math.sqrt(Math.pow(a,2) - Math.pow(b, 2));
					if ((Math.abs(r1 - c) < EPSILON) && !(a == b && b == c) && !(a == b && b != c || b == c && c != a || c == a && a != b) && !(c == d && b ==f)) {
						System.out.println(a + ";" + b + ";" + c);
						d = b;
						f = c;
					}
				}
			}
		} catch (java.io.FileNotFoundException e) {
			System.out.println("Fehler beim erstellen der Datei");
		}
  }
}