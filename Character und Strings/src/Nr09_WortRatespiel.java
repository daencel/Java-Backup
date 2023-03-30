
public class Nr09_WortRatespiel
{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Wortratespiel");
		System.out.println("=============");
		char n = 'j';
		while (n == 'j') {
			String x = TestScannerErweitert.readString("Gesuchtes Wort:	");
			while (x.length() < 1) {
				System.out.println("Wort muss mindestens ein Zeichen enthalten");
				x = TestScannerErweitert.readString("Gesuchtes Wort:	");
			}
			x = Grossbuchstaben(x);
			String ausgabe = "";
			while (ausgabe.length() < x.length()) {
				ausgabe = ausgabe + '.';
			}
			System.out.println("Ihr Wort:	" + ausgabe);
			boolean gefunden = false;
			int v = 0;
			while (gefunden == false) {
				String b = TestScannerErweitert.readString("Buchstabe/Wort:	");
				if (b.length() == 1) {
					char charb = b.charAt(0);
					if (Character.isLowerCase(charb)) {
						charb = Character.toUpperCase(charb);
					}
					if (gefunden == false) {
						ausgabe = Suchen(charb,x,ausgabe);
						System.out.println("Ihr Wort:	" + ausgabe);
						v++;
					}
					if (ausgabe.equals(x)) {
						System.out.println("Sie haben in " + v + " Schritten das Wort erraten!");
						gefunden = true;
					}
				}
				if (b.length() > 1) {
					b = Grossbuchstaben(b);
					v++;
					if (b.equals(x)) {
						if (v <= 1) {
							System.out.println("Sie haben in nur einen Schritt das Wort erraten!");
							gefunden = true;
						}	
						else
							System.out.println("Sie haben in " + v + " Schritten das Wort erraten!");
							gefunden = true;
					}
				}
			}
			n = TestScannerErweitert.readChar("Nochmal (j/n)? ");
		}
	}

	/*
	 * Sucht im String x den char charb und fuellt ihn in den String Ausgabe
	 * Der Sting ausgabev ist der String, der erster mit dieser Methode erstellt wurde
	 * @param char charb eingegebener Buchstabe
	 * @param String x Loesungswort
	 * @param String ausgabev Ausgabe von erster
	 * @param return neue Ausgabe
	 */
	public static String Suchen(char charb, String x, String ausgabev) {
		int j = 0;
		String ausgabe = "";
		while (j < ausgabev.length()) {
			if (charb == x.charAt(j)) {
					ausgabe = ausgabe + charb;
			}
			else {
				if (ausgabev.charAt(j) != '.') {
					ausgabe = ausgabe + ausgabev.charAt(j);
				}
				if (ausgabev.charAt(j) == '.') {
					ausgabe = ausgabe + '.';
				}
			}
			j++;
		}
		return ausgabe;
	}
	
	/*
	 * Wandelt einen String in grossbuchstageb um
	 * @param String x String in Kleinbuchstaben
	 * @return String in Grossbuchstaben
	 */
	public static String Grossbuchstaben (String x) {
		x =  x.toUpperCase();
		return x;
	}
}