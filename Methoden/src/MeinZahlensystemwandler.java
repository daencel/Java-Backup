public class MeinZahlensystemwandler
{
	/**
	 * Wandelt das Zeichen nr in eine Zahl um, wobei die Gro�-/Klein-schreibung 
	 * ignoriert wird:
	 * '0' ergibt 0, '1' ergibt 1, ... '9' ergbit 9, 'A' ergibt 10, 'B' ergibt 11, ...,
	 * 'Z' ergibt 35
	 * Sollte ein ung�ltiges Zeichen �bergeben werden, so gibt die Methode -1 zurueck
	 * @param nr das umzuwandelnde Zeichen
	 * @return die Zahl f�r die das Zeichen steht
	 */
	public static int getDigit (char nr) {
		int x = 0;
		if (!(Character.isLetter(nr) || Character.isDigit(nr))) {
			x = -1;
		}
		else {
			if (Character.isDigit(nr)) {
				x = Character.getNumericValue(nr);
			}
			if (Character.isLetter(nr)) {
				if (Character.isLowerCase(nr)) {
					nr = Character.toUpperCase(nr);
				}
				int a = (int)nr - 65 + 10;
				x = a;
			}
		}
		return x;
	}
	/**
	 * Wandelt die Nummer der Ziffer nr in ein Zeichen um:
	 * 0 ergibt '0', 1 ergibt '1', ..., 9 ergibt '9', 10 ergibt 'A', 11 ergibt 'B', 
	 * ..., 35 ergibt 'Z'
	 * Sollte eine ungueltige Nummer uebergeben werden, so gibt die Methode einen Stern 
	 * '*' zurueck
	 * @param nr die Nummer welche in ein Zeichen umgewandelt werden soll
	 * @return das Zeichen das fuer die Nummer steht
	 */
	public static char getDigit (int nr) {
		char x = ' ';
		if (nr > 35 || nr < 0) {
			x = '*';
		}
		else {
			if (nr < 10) {
				x = (char)(nr+'0');
			}
			else {
				nr += 55;
				x = (char)nr;
			}
		}
		return x;
	}
	
	/**
	 * Wandelt die Zahl num mit der Basis basis in eine Dezimalzahl um und liefert
	 * diese zurueck. Falls die uebergebene Zahl num gleich null oder deren Luenge gleich
	 * 0 ist oder die Basis kleiner als 2 ist, wird -1 zurueck geliefert. Wenn eine
	 * Ziffer der uebergebenen Zahl nicht zur Basis pass, wird ebenfalls -1 zurueck
	 * geliefert
	 * Beispiel: numToDec("01110110", 2) ergibt 118
	 * numToDec("170712", 8) ergibt 61898
	 * numToDec("170712", 7) ergibt -1
	 * @param num die umzuwandelnde Zahl als String uebergeben
	 * @param basis die Basis der umzuwandelnden Zahl
	 * @return das Ergebnis im Dezimalsystem
	 */
	public static int numToDec(String num, int basis) {
		int x = 0;
		int e = 0;
		if (num == null || num.length() <= 0 || basis < 2) {
			x = -1;
		}
		else {
			int a = num.length()-1;
			int r = 0;
			int erg = 0;
			boolean wert = true;
			while (a >= 0 && wert == true) {
				if (Character.isLetter((num.charAt(a)))) {
					e = (num.charAt(a))-55;
					x = (int)Math.pow(basis, r);
					erg = erg + x * e;
					if (e >= basis) {
						erg = -1;
						wert = false;
					}
				}
				else {
					e = num.charAt(a)-'0';
					x = (int)Math.pow(basis, r);
					erg = erg + x * e;
					if (e >= basis) {
						erg = -1;
						wert = false;
					}
				}
				a--;
				e--;
				r++;
			}
			x = erg;
		}
		return x;
	}
	
	/**
	 * Wandelt die Dezimalzahl dec in eine Zahl mit der Basis basis um und gibt diese
	 * zurueck. Dabei muss die Dezimalzahl dec goesseer oder gleich 0 sein und die Basis
	 * muss groesser als 1 sein, ansonsten wird null zurueck geliefert.
	 * Beispiel: decToNum(118,2) ergibt 1110110
	 * decToNum(61898,8) ergibt 170712
	 * @param dec die umzuwandelnde Dezimalzahl
	 * @param basis nach welcher umgewandelt wird
	 * @return die umgewandelte Zahl
	 */
	public static String decToNum(int dec, int basis) {
		String ausgabe = "";
		if (dec < 0 || basis <= 1) {
			ausgabe = null;
		}
		else {
			boolean stop = false;
			int r;
			while (stop == false) {
				r = dec%basis;
				if (r >= 10) {
					char m = (char)(r+55);
					ausgabe = m + ausgabe;
				}
				else {
					ausgabe = r + ausgabe;
				}
				dec = (dec - r) / basis;
				if (dec == 0) {
					stop = true;
				}
			}
		}
		return ausgabe;
	}
	
	/**
	 * Wandelt die Hexadezimalzahl dec in eine Dezimalzahl um und gibt diese
	 * zurueck. Dabei muss die Hexadezimalzahl groesser oder gleich 0 sein,
	 * ansonsten wird -1 zurueck geliefert.
	 * Beispiel: hexToDec(5B) ergibt 91
	 * hexToDec(133) ergibt 307
	 * @param hex die umzuwandelnde Hexadezimalzahl
	 * @return die umgewandelte Zahl
	 */
	public static int hexToDec(String hex) {
		int x = 0;
		double base = 16;
		if (hex == null) {
			x = -1;
		}
		else {
			int i = hex.length()-1;
			double o = 0;
			int erg = 0;
			boolean wertebereich = true;
			while (i >= 0 && wertebereich == true) {
				char a = hex.charAt(i);
				if (Character.isDigit(a)) {
					int p = Character.getNumericValue(a);
					int e = p * (int)Math.pow(base, o);
					erg += e;
					x = erg;
				}
				if (Character.isLetter(a)) {
					int p = Character.valueOf(a);
					p -= 55;
					if (p <= 15) {
						int e = p * (int)Math.pow(base, o);
						erg += e;
						x = erg;
					}
					else {
						x = -1;
						wertebereich = false;
					}
				}
				i--;
				o++;
			}
		}
		
		return x;
	}
	
	/**
	 * Wandelt die Dezimalzahl dec in eine Hexadezimalzahl um und gibt diese
	 * zurueck. Dabei muss die Dezimalzahl groesser oder gleich 0 sein,
	 * ansonsten wird -1 zurueck geliefert.
	 * Beispiel: decToHex(300) ergibt 12C
	 * decToHex(700) ergibt 2BC
	 * @param dec die umzuwandelnde Dezimalzahl
	 * @return die umgewandelte Zahl
	 */
	public static String decToHex(int dec) {
		String x = "";
		if (dec == 0) {
			x = "0";
		}
		if (dec < 0) {
			x = null;
		}
		else {
			while (dec > 0) {
				int r = dec % 16;
				dec = dec / 16;
				if (r < 10) {
					x = r + x;
				}
				else {
					r += + 55;
					char a = (char)r;
					x = a + x;
				}
			}
		}
		return x;
	}
	
	/**
	 * Wandelt die Dualzahl dec in eine Dezimalzahl um und gibt diese
	 * zurueck. Dabei muss die Dualzahl groesser oder gleich 0 sein,
	 * ansonsten wird -1 zurueck geliefert.
	 * Beispiel: dualToDec(1010101) ergibt 85
	 * dualToDec(700) ergibt -1
	 * @param dual die umzuwandelnde Dualzahl
	 * @return die umgewandelte Zahl
	 */
	public static int dualToDec(int dual) {
		int erg = 0;
		int dual2 = dual;
		boolean wertebereich = true;
		while (dual2 > 0 ) {
			int r = dual2 % 10;
			if (r > 1) {
				wertebereich = false;
				erg = -1;
			}
			dual2 = dual2 / 10;
		}
		double basis = 2;
		double e = 0;
		while (dual > 0 && wertebereich == true) {
			int b = dual%10;
			dual = dual / 10;
			erg = erg + (b * ((int)Math.pow(basis, e)));
			e++;
		}
		
		return erg;
	}
	
	/** 
	 * Wandelt eine Zahl dec in die entsprechende Zahl im Dualsystem um,
	 * und giebt diese zurueck.
	 * @param dec die umzuwandelnde zahl
	 * @return die umgewandelte Zahl
	 */
	public static String decToDual(int dec) {
		String erg = "";
		if (dec < 0) {
			erg = null;
		}
		else {
			while (dec > 0) {
				int r = dec % 2;
				dec = dec/2;
				erg = r + erg;
			}
		}
		return erg;
	}
	/*
	 * Wandelt eine beliebige Zahl x in einen beliebigen Zahlensystem,
	 * in die entsprechende Zahl in einen anderen bleiebigem Zahlensystem um und gibt diese
	 * zurueck. Dabei muss die Zahl groesser oder gleich 0 sein,
	 * ansonsten wird -1 zurueck geliefert.
	 * Beispiel: numToNum("3A",16,2) ergibt 111010
	 * numToNum("3A",5,2) ergibt -1
	 * @param num die umzuwandelnde Zahl
	 * @param basis die Basis in der die Zahl geschrieben wurde
	 * @param basis2 die Basis, in der die Zahl x umgewandelt werden soll
	 * @return die umgewandelte Zahl
	 */
	public static String numToNum(String num, int basis, int basis2) {
		int x = 0;
		int e = 0;
		if (num == null || num.length() <= 0 || basis < 2) {
			x = -1;
		}
		else {
			int a = num.length()-1;
			int r = 0;
			int erg = 0;
			boolean wert = true;
			while (a >= 0 && wert == true) {
				if (Character.isLetter((num.charAt(a)))) {
					e = (num.charAt(a))-55;
					x = (int)Math.pow(basis, r);
					erg = erg + x * e;
					if (e >= basis) {
						erg = -1;
						wert = false;
					}
				}
				else {
					e = num.charAt(a)-'0';
					x = (int)Math.pow(basis, r);
					erg = erg + x * e;
					if (e >= basis) {
						erg = -1;
						wert = false;
					}
				}
				a--;
				e--;
				r++;
			}
			x = erg;
		}
		String ausgabe = "";
		if (x != -1) {
			if (basis2 <= 1) {
				ausgabe = null;
			}
			else {
				boolean stop = false;
				int r;
				while (stop == false) {
					r = x%basis2;
					if (r >= 10) {
						char m = (char)(r+55);
						ausgabe = m + ausgabe;
					}
					else {
						ausgabe = r + ausgabe;
					}
					x = (x - r) / basis2;
					if (x == 0) {
						stop = true;
					}
				}
			}
		}
		else {
			ausgabe = Integer.toString(x);
		}
		return ausgabe;
	}
}