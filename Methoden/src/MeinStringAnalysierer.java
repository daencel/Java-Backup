public class MeinStringAnalysierer
{
	/**
	 * Liefert true zurï¿½ck, wenn der String nicht null ist und nur Ziffern enthï¿½lt.
	 * Beispiel: hatNurZiffern("12a43") ergibt false 
	 * hatNurZiffern("1242332322129") ergibt true
	 * @param s der zu untersuchende String
	 * @return true, falls der String nicht leer ist und nur aus Ziffern besteht
	 */
	public static boolean hatNurZiffern (String s) {
		int a = 0;
		boolean zahl = true;
		if (s == null || s.length() == 0 ) {
			zahl = false;
		}
		else {
			while (a < s.length() && zahl == true) {
				if (!(Character.isDigit(s.charAt(a)))) {
					zahl = false;
				}
				a++;
			}
		}
		return zahl;
	}
	
	/**
	 * liefert den ï¿½bergebenen String in umgekehrter Reihenfolge zurï¿½ck. Falls der
	 * String null ist, wird null zurï¿½ck geliefert.
	 * Beispiel: umdrehenString("Rudi") ergibt "iduR"
	 * @param s der umzudrehende String
	 * @return null falls der String s null ist, ansonsten den umgedrehten String
	 */
	public static String umdrehenString (String s) {
		String umgekehrt = new String();
		if (s == null || s.length() == 0) {
			umgekehrt = null;
		}
		else {
			for (int j = s.length()-1; j >= 0; j--) {
				umgekehrt += s.charAt(j);
			}
		}
		return umgekehrt;
	}


	/**
	 * Liefert den um die Leerzeichen befreiten String zurï¿½ck. Wird null ï¿½bergeben,
	 * so liefert die Methode null zurï¿½ck.
	 * Beispiel: loeschenLeerzeichen("Susi Sorglos sitzt") ergibt "SusiSorglossitzt"
	 * @param s der von Leerzeichen zu sï¿½ubernde String
	 * @return null falls der ï¿½bergebene String null ist, ansonsten den von Leerzeichen
	 * gesï¿½uberten String
	 */
	public static String loeschenLeerzeichen (String s) {
		if (s == null || s.length() == 0) {
			s = null;
		}
		else {
			s = s.replace(" ", "");
		}
		return s;
	}
	
	/**
	 * Testet ob der ï¿½bergebene String ein Palidrom ist, d. h. von hinten gelesen das 
	 * Selbe bedeutet wie von vorne gelesen. Falls null ï¿½bergeben wurde oder die Lï¿½nge
	 * des String 0 ist, wird false zurï¿½ck geliefert.
	 * Bevor auf Palindrom getestet wird, werden alle Leerzeichen aus dem String 
	 * entfernt. Groï¿½-/Kleinschreibung wird bei der Analyse nicht beachtet
	 * Beispiel: istPalindrom("Otto") ergibt true
	 * istPalindrom("Ei nie") ergibt true
	 * istPalindrom("Ein Neger mit Gazelle zagt im Regen nie") ergibt true
	 * @param s der zu untersuchende String
	 * @return true, falls der String nicht null und eine Lï¿½nge grï¿½ï¿½er als 0 und ein
	 * Palidrom ist
	 */
	public static boolean istPalindrom (String s) {
		boolean Palindrom = true;
		if (s == null || s.length() == 0) {
			Palindrom = false;
		}
		else {
			String s2 = umdrehenString(s);
			s2 = loeschenLeerzeichen(s2);
			s = loeschenLeerzeichen(s);
			int a = 0;
			while (a < s.length() && Palindrom == true) {
				char x2 = s2.charAt(a);
				if (Character.isLowerCase(x2)) {
					x2 = Character.toUpperCase(x2);
				}
				char x = s.charAt(a);
				if (Character.isLowerCase(x)) {
					x = Character.toUpperCase(x);
				}
				if (x == x2) {
					Palindrom = true;
				}
				else {
					Palindrom = false;
				}
				a++;
			}
		}
		
		return Palindrom;
	}
		
	/**
	 * Gibt einen String zurï¿½ckgibt, in dem die Zahl zahl linksbï¿½ndig steht. Der String 
	 * soll anzahl Zeichen lang sein. Es mï¿½ssen also rechts solange Leerzeichen ergï¿½nzt 
	 * werden, bis der String die passende Lï¿½nge hat.
	 * Ist die Zahl lï¿½nger als anzahl, so wird die Zahl in ihrer gesamten Lï¿½nge zurï¿½ck
	 * gegeben
	 * Beispiel: links(15,3) ergibt "15 "
	 * links(15,4) ergibt "15  "
	 * @param zahl die linksbï¿½ndig ausgegeben werden soll
	 * @param anzahl die Lï¿½nge des auszugebenden Strings
	 * @return den String, der ganz links die Zahl enthï¿½lt und der auf anzahl Stellen
	 * bei Bedarf aufgefï¿½llt wurde
	 */
	public static String links (int zahl, int l) {
		String s = String.valueOf(zahl);
		int l1 = s.length();
		int l2 = l - l1;
		String ausgabe = s;
		if (l < s.length()) {
			ausgabe = s.substring(0, l);
		}
		else {
			while (l2 > 0) {
				ausgabe += " ";
				l2--;
			}
		}
		return ausgabe;
	}
	
	/**
	 * Gibt einen String zurï¿½ckgibt, in dem die Zahl zahl rechtsbï¿½ndig steht. Der String 
	 * soll anzahl Zeichen haben. Es mï¿½ssen also links solange Leerzeichen ergï¿½nzt 
	 * werden, bis der String die passende Lï¿½nge hat.
	 * Ist die Zahl lï¿½nger als anzahl, so wird die Zahl in ihrer gesamten Lï¿½nge zurï¿½ck
	 * gegeben
	 * Beispiel: rechts(15,3) ergibt " 15"
	 * rechts(15,4) ergibt "  15"
	 * @param zahl die im String rechtsbï¿½ndig ausgegeben werden soll
	 * @param anzahl die Lï¿½nge des auszugebenden Strings
	 * @return den String, der ganz rechts die Zahl enthï¿½lt und der auf anzahl Stellen
	 * bei Bedarf aufgefï¿½llt wurde
	 */
	public static String rechts (int zahl, int l) {
		String s = String.valueOf(zahl);
		int l1 = s.length();
		int l2 = l - l1;
		String ausgabe = "";
		if (l < s.length()) {
			ausgabe = s.substring(0, l);
		}
		else {
			while (l2 > 0) {
				ausgabe += " ";
				l2--;
			}
			ausgabe += s;
		}
		return ausgabe;
	}
	
	/**
	 * Zï¿½hlt wie viele Buchstaben im String s vorhanden sind. Die deutschen Umlaute 
	 * werden nicht mitgezï¿½hlt. Groï¿½-/Kleinschreibung wird ignoriert. 
	 * Ist s gleich null, so wird -1 als Ergebnis zurï¿½ck geliefert
	 * Beispiel: zaehleBuchstaben("Hallo Mï¿½ller06"))ergibt 10
	 * @param s der auf Buchstaben hin zu testenden String
	 * @return -1 falls s gleich null ist, ansonsten die Anzahl der Buchstaben die in
	 * s zu finden sind 
	 */
	public static int zaehleBuchstaben (String s){
		int x = 0;
		int i = 0;
		if (s == null || s.length()==0) {
			x = -1;
		}
		else {
			while (i < s.length()) {
				char b = s.charAt(i);
				if (Character.isLetter(b)) {
					if (!(b == 'Ä' || b == 'ä' || b == 'Ö' || b == 'ö' || b == 'Ö' || b == 'ö')) {
						x++;
					}
				}
				i++;
			}
		}
		return x;
	}
	
	/**
	 * Zählt wie viele Zeichen im String s keine Buchstaben sind. Die deutschen Umlaute 
	 * werden mit gezählt (d.h. als keine Buchstaben interpretiert). Die 
	 * Groß/Kleinschreibung wird ignoriert. 
	 * Ist s gleich null, so wird -1 als Ergebnis zurück geliefert
	 * Beispiel: zaehleNichtBuchstaben("Hallo Müller06")) ergibt 4
	 * @param s der auf Buchstaben hin zu testenden String
	 * @return -1 falls s gleich null ist, ansonsten die Anzahl der Zeichen im String,
	 * die nicht Buchstaben sind 
	 */
	public static int zaehleNichtBuchstaben (String s) {
		int x = 0;
		int i = 0;
		if (s == null || s.length()==0) {
			x = -1;
		}
		else {
			while (i < s.length()) {
				char b = s.charAt(i);
				if ((!(Character.isLetter(b))) || (b == 'Ä' || b == 'ä' || b == 'Ü' || b == 'ü' || b == 'Ö' || b == 'ö')) {
						x++;
				}
				i++;
			}
		}
		return x;
	}
	
	/**
	 * Zählt wie oft das Zeichen c im String s vorkommt. Die Groß/Kleinschreibung 
	 * wird ignoriert.
	 * Ist s gleich null, so wird -1 als Ergebnis zurück geliefert
	 * Beispiel: zaehleZeichen("Alle Bananen sind krumm!", 'a') ergibt 3
	 * @param s der zu durchsuchende String
	 * @param c das Zeichen, welches im String gesucht werden soll
	 * @return -1 falls der übergebenen String null ist, ansonsten die Anzahl wie oft
	 * das Zeichen c im String s vorkommt
	 */
	public static int zaehleZeichen (String s, char c) {
		int x = 0;
		if (s == null || s.length() == 0) {
			x = -1;
		}
		else {
			String s2 = "";
			int i = 0;
			while (s2.length() < s.length()) {
				if(Character.isLowerCase(s.charAt(i))){
					s2 = s2 + (Character.toUpperCase(s.charAt(i)));
				} 	
				else {
		    	s2 = s2 + s.charAt(i);
				}
				i++;
			}
			s = s2;
			i = 0;
			if (Character.isLowerCase(c)) {
				c = Character.toUpperCase(c);
			}
			while (i < s.length()) {
				char b = s.charAt(i);
				if (b == c) {
					x++;
				}
				i++;
			}
		}
		return x;
	}
	
	/**
	 * Lï¿½scht aus dem String s das Zeichen an der Position p.
	 * Falls der ï¿½bergebene String gleich null ist, die ï¿½bergebene Position < 0 oder
	 * ï¿½ber die Lï¿½nge des Strings hinaus geht, wird null zurï¿½ck geliefert 
	 * Beispiel: loescheZeichenAnPosition("Peters@platz", 6) ergibt "Petersplatz"
	 * @param s der String in welchem das Zeichen gelï¿½scht werden soll
	 * @param p die Position an welcher das Zeichen gelï¿½scht werden soll
	 * @return den String, der das zu lï¿½schende Zeichen an der Position nicht mehr
	 * enthï¿½lt
	 */
	public static String loescheZeichenAnPosition (String s, int p) {
		String ausgabe = "";
		if (s == null || s.length() == 0 || p < 0 || p > s.length()) {
			ausgabe = null;
		}
		else {
			String sa = s.substring(0, p);
			String se = s.substring(p+1);
			ausgabe = sa + se;
		}
		return ausgabe;
	}
	
	/**
	 * Lï¿½scht alle Zeichen c aus dem String s. Groï¿½-/Kleinschreibung wird dabei beachtet.
	 * Falls als String null ï¿½bergeben wird, so wird null zurï¿½ck geliefert
	 * Beispiel: loescheZeichen("Ein Keller", 'e') ergibt "Ein Kllr"
	 * @param s der String in welchem das Zeichen c gelï¿½scht werden soll
	 * @param c das zu lï¿½schende Zeichen
	 * @return der String, der keine Zeichen c mehr enthï¿½lt
	 */
	public static String loescheZeichen (String s, char c) {
		String ausgabe = "";
		int i = 0;
		if (s == null || s.length() == 0) {
			ausgabe = null;
		}
		else {
			while (i < s.length()) {
				char b = s.charAt(i);
				if (b == c) {
					String sa = s.substring(0, i);
					String se = s.substring(i+1);
					s = sa + se;
				}
				else {
					i++;
				}
			}
			ausgabe = s;
		}
		return ausgabe;
	}
	
	/**
	 * Lï¿½scht aus dem String s einen Teilbereich heraus, der durch die Anfangsposition 
	 * start und durch seine Lï¿½nge l gegegeben ist. Werden alle Zeichen gelï¿½scht, so muss
	 * null zurï¿½ck gegeben werden
	 * Liefert null zurï¿½ck, falls der ï¿½bergebene String null ist oder falls start
	 * und l ï¿½ber den String hinaus greifen.  s muss grï¿½ï¿½er oder gleich 0 sein, und
	 * l muss grï¿½ï¿½er als 0 sein
	 * Beispiel: loescheStringAnPosition("Hallo Leute!", 6, 5) ergibt "Hallo !"
	 * loeschenStringAnPosition("AG", 0, 2) ergibt null
	 * @param s der String in dem gelï¿½scht werden soll
	 * @param start die Startposition ab der belï¿½scht werden werden
	 * @param l die Anzahl der zu lï¿½schenden Zeichen
	 * @return der String, in dem mehrer Zeichen gelï¿½scht wurden
	 */
	public static String loescheStringAnPosition (String s, int start, int l) {
		String ausgabe = "";
		int ende = start + l;
		if (s == null || s.length() == 0 || s.length() <= (start+l) || l == 0) {
			ausgabe = null;
		}
		else {
			String sa = s.substring(0,start);
			String se = s.substring(ende);
			ausgabe = sa + se;
		}
		
		return ausgabe;
	}

	/**
	 * Lï¿½scht aus dem String s jedes Vorkommen des Strings ds. Die Groï¿½-/Kleinschreibung
	 * wird dabei beachtet. Die beiden ï¿½bergebenen Strings dï¿½rfen nicht null sein und
	 * mï¿½ssen Zeichen enthalten. Werden alle Zeichen gelï¿½scht, so wird null zurï¿½ck
	 * geliefert 
	 * Beispiel: loescheString("HHallallo Leute-HALLHallo", "Hall") ergibt
	 * "Hallo Leute-HALLo"
	 * loescheString("HallHall","Hall") ergibt null
	 * @param s der String in dem gelï¿½scht werden soll
	 * @param ls der zu lï¿½schende Teilstring
	 * @return der String, in dem der Teilstring nicht mehr vorkommt
	 */
	public static String loescheString (String s, String ls) {
		String ausgabe = "";
		if (s == null || s.length() == 0 || ls.length() == 0) {
			ausgabe = null;
		}
		else {
			ausgabe = s.replace(ls, "");
			if (ausgabe.length() == 0) {
				ausgabe = null;
			}
		}
		return ausgabe;
	}
}
