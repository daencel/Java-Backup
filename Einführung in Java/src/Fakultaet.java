
public class Fakultaet
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		int x = 1;
		int erg = 1;
		while (x <= n) {
			erg = erg * x;
			x = x + 1;
		}
		System.out.print("Ergebnis: " +erg);
			
	}
}
