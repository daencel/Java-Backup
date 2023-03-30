public class Nr13_Median
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Median");
		System.out.println("======");
		int a = TestScanner.readInt("1. Zahl: ");
		int b = TestScanner.readInt("2. Zahl: ");
		int c = TestScanner.readInt("3. Zahl: ");
		if (!(a == b && b == c)) {
			if ((a == b && a >= c) || (b == c && b <= a) || (a < b && a > c) || (a > b && a < c)) {
				System.out.println();
				System.out.println("Der Median lautet " + a);
			}
			if ((b == c && b >= a) || (a == c && c <= b) || (b < a && b > c) || (b > a && b < c)) {
				System.out.println();
				System.out.println("Der Median lautet " + b);
			}
			if ((c == a && c >= b) || (a == b && a <= c) || (c < a && c > b) || (c > a && c < b)) {
				System.out.println();
				System.out.println("Der Median lautet " + c);
			}	
		}
		if (a == b && b == c) {
			System.out.println();
			System.out.println("Der Median lautet " + b);
		}
	}
}