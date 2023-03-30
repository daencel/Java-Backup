
public class Nr05_Zeichensatzanalyse
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char a = (char)33;
		int b = 0;
		System.out.println("isLetter");
		while (a < 256) {
			if (Character.isLetter(a)) {
				System.out.print(a);
				b++;
			}
			if (b == 30) {
				System.out.print("\n");
				b = 0;
			}
			a++;
		}
		System.out.println();
		System.out.println();
		a = (char)33;
		b = 0;
		System.out.println("isDigit");
		while (a < 256) {
			if (Character.isDigit(a)) {
				System.out.print(a);
				b++;
			}
			if (b == 30) {
				System.out.print("\n");
				b = 0;
			}
			a++;
		}
		System.out.println();
		System.out.println();
		a = (char)33;
		b = 0;
		System.out.println("isWhitespace");
		while (a < 256) {
			if (Character.isWhitespace(a)) {
				System.out.print(a);
				b++;
			}
			if (b == 30) {
				System.out.print("\n");
				b = 0;
			}
			a++;
		}
		System.out.println();
		System.out.println();
		a = (char)33;
		b = 0;
		System.out.println("isLowerCase");
		while (a < 256) {
			if (Character.isLowerCase(a)) {
				System.out.print(a);
				b++;
			}
			if (b == 30) {
				System.out.print("\n");
				b = 0;
			}
			a++;
		}
		System.out.println();
		a = (char)33;
		b = 0;
		System.out.println("isUpperCase");
		while (a < 256) {
			if (Character.isUpperCase(a)) {
				System.out.print(a);
				b++;
			}
			if (b == 30) {
				System.out.print("\n");
				b = 0;
			}
			a++;
		}
	}
}