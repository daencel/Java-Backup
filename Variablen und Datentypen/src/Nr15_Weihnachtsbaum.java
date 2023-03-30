public class Nr15_Weihnachtsbaum
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int anzL = 15;
		int anzS = 1;
		int x = 0;
		while (x<15) {
			printLeerzeichen(anzL);
			printStern(anzS);
			printLeerzeichen(anzL);
			System.out.println();
			x++;
			anzL--;
			anzS = anzS + 2;
		}
		x = 0;
		while (x<3) {
			anzS = 3;
			anzL = 14;
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
		while (anzahl > 0) {
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
