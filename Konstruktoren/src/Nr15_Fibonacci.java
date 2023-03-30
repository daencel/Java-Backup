
public class Nr15_Fibonacci
{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Fibonacci - Zahlen");
		System.out.println("==================");
		int a = -1;
		while (a<0) {
			a = TestScanner.readInt("Die wievielte Zahl? ");
			if (a<0)
				System.out.println("Die Zahl muss groesser oder gleich Null sein.");
		}
		System.out.println();
		System.out.println("Die " + a + ". Fibonacci - Zahl lautet " + fib (a + 1));
	}
	/*
	 * Berechnet die Fibonacci Zahl bei der Position int pos
	 * @param int pos Position der Zahl
	 * @return Fibonacci Zahl bei der Position
	 */
	public static int fib(int pos) {
		int ret = 1;
		if (pos > 2)
			ret = fib(pos - 1) + fib(pos - 2);
		return ret;
	}
}