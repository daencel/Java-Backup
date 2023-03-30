public class Nr16_FlexWeihnachtsbaum
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int anzL;
		int anzS = 1;
		int x = 0;
		int l2;
		System.out.println("Der flexible Weihnachtsbaum");
		System.out.println("===========================");
		System.out.println();
		anzL = TestScanner.readInt("Geben Sie die Hoehe vom Baum an: ");
		l2 = TestScanner.readInt("Geben Sie die Hoehe vom Stamm an: ");
		System.out.println();
		int l = anzL;
		while (x<l) {
			printLeerzeichen(anzL);
			printStern(anzS);
			printLeerzeichen(anzL);
			System.out.println();
			x++;
			anzL--;
			anzS = anzS + 2;
		}
		x = 0;
		while (x<l2) {
			anzS = 3;
			anzL = l - 1;
			printLeerzeichen(anzL);
			printStern(anzS);
			printLeerzeichen(anzL);
			System.out.println();
			x++;
		}
	}
	
	/*
	 * Gibt Leerzeichen in der Methode aus.
	 * @param int anzahl Anzahl der Leerzeichen
	 */	
	public static void printLeerzeichen (int anzahl) {
		while (anzahl > 1) {
			System.out.print(" ");
			anzahl --;
		}
	}
	
	/*
	 * Gibt den Baum mit Sternen aus
	 * @param int anzahl Anzahl der Sterne
	 */	
	public static void printStern (int anzahl) {
		while (anzahl > 0) {
			System.out.print("*");
			anzahl --;
		}
	}
}