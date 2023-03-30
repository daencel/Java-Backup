public class Mastermind {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Mastermind");
		System.out.println("==========");
		int nr = 0;
		int l = TestScannerErweitert.readInt("Code Laenge: ");
		int farben = TestScannerErweitert.readInt("Anzahl Farben: ");
		while (farben < l) {
			System.out.println("Die Farbenanzahl muss groesser gleich " + l + " sein.");
			farben = TestScannerErweitert.readInt("Anzahl Farben: ");
		}
		String code = erzeugeCode(l,farben);
		boolean ende = false;
		String endee = "ENDE";
		System.out.print("====================================>	");
		String tipp = eingabeTipp(l);
		while (ende == false) {
			if (tipp.equals(endee)) {
				nr++;
				System.out.println(nr + "):	" + tipp + " =	(w: " + ermittleWeiss(code,tipp) + ", s: " + ermittleSchwarz(code,tipp) + "):		Ende");
				ende = true;
			}
			if (ende == false) {
				if (tipp.equals(code)) {
					nr++;
					System.out.println(nr + "):	" + tipp + " =	(w: " + ermittleWeiss(code,tipp) + ", s: " + ermittleSchwarz(code,tipp) + "):		Code gefunden");	
					System.out.print("====================================>	");
					code = erzeugeCode(l,farben);
					tipp = eingabeTipp(l);
					nr = 0;
				}
				else {
					nr++;
					System.out.print(nr + "):	" + tipp + " =	(w: " + ermittleWeiss(code,tipp) + ", s: " + ermittleSchwarz(code,tipp) + "):		");	
					tipp = eingabeTipp(l);
				}
			}	
		}
	}
	
	/**
	 * Errechnet die Buchstaben, die sich am richtigen Platz befinden.
	 * Eingespielt wird der Code und der Tipp, welcher vom Benutzer eingegeben wird.
	 * @param tode Code
	 * @param tipp Tipp, welcher vom Benutzer eingegeben wurde
	 * @param return Buchstaben, die sich am richtigen Platz befinden
	 */
	public static int ermittleSchwarz(String code, String tipp) {
		int o = 0;
		if (code.length() == tipp.length()) {
			for (int i = 0; i < code.length(); i++) {
				if (code.charAt(i) == tipp.charAt(i)) {
					o++;
				}
			}
		}
		else {
			o = -1;
		}
		return o;
	}
	
	/**
	 * Errechnet die Buchstaben, die sich am falschem Platz befinden.
	 * Eingespielt wird der Code und der Tipp, welcher vom Benutzer eingegeben wird.
	 * @param tode Code
	 * @param tipp Tipp, welcher vom Benutzer eingegeben wurde
	 * @param return Buchstaben, die sich am falschem Platz befinden
	 */
	public static int ermittleWeiss(String code, String tipp) {
		int o = 0;
		if (code.length() == tipp.length()) {
			for (int i = 0; i < code.length(); i++) {
				if (code.charAt(i) != tipp.charAt(i) && code.contains(Character.toString(tipp.charAt(i)))) {
					o++;
				}
			}
		}
		else {
			o = -1;
		}
		return o;
	}
	
	/**
	 * Kontrolliert ob im String srt doppelte Buchstaben vorkommen.
	 * False wird bei keinen doppelten Buchstaben und true bei doppelt
	 * oder mehr vorkommenden gleichen Buchstaben zurueckgegeben
	 * @param str String zu kontrollieren
	 * @return false oder true
	 */
	public static boolean enthaeltDoppelte (String str) {
		str = str.toUpperCase();
		for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return true; 
                }
            }
		}
        return false; 
    }
		
	/*
	 * Lässt dem Benutzer den Tipp eingeben und wandelt ihn in Grossbuchstaben um und erkennt ob
	 * der Benutzer "ende"eingibt, um das Programm zu beenden.
	 * @param lange länge des Codes
	 * @param return Tipp in Grossbuchstaben oder ENDE
	 */
	public static String eingabeTipp (int laenge) {
		boolean ende = false;
		String ende1 = "ende";
		String tipp = TestScannerErweitert.readString("Ihr Tipp: ");
		while (ende == false && (tipp.length() == 0 || !(tipp.length() == laenge) || enthaeltDoppelte(tipp) == true)) {
			if (tipp.equals(ende1)) {
				ende = true;
				tipp = "ENDE";
			}
			else {
				if (enthaeltDoppelte(tipp) == true) {
					System.out.print("Keine doppelten Zeichen erlaubt!	");
					tipp = TestScannerErweitert.readString("Ihr Tipp: ");
				}
				else {
					System.out.print("Laenge stimmt nicht mit Code ueberein!	");
					tipp = TestScannerErweitert.readString("Ihr Tipp: ");
				}
			}
		}
		tipp = tipp.toUpperCase();
		return tipp;
	}
	
	/**
	 * Erzeugt einen zufaelligen String mit int Stellen und
	 * int Farben Moeglichkeiten. Ein Farbencode kann nicht ein
	 * zweites mal im Code vorkommen. Die Laenge muss laenger oder gleich 
	 * den Moeglichkeiten von farben sein, sonst wird null uebertragen.
	 * @param stellen Anzahl Stellen des erzeugenden Codes
	 * @param farben Anzahl Moeglichkeiten
	 * @return Code
	 */
	public static String erzeugeCode (int stellen, int farben) {
		String ausgabe = "";
		if (stellen > farben) {
			ausgabe = null;
		}
		else {
			while (stellen > 0) {
				String ran = Character.toString(((char)((Math.random()*farben+1)+64)));
				while (ausgabe.contains(ran)) {
					ran = Character.toString(((char)((Math.random()*farben+1)+64)));
				}
				ausgabe = ran + ausgabe;
				stellen--;
			}
		}
		return ausgabe;
	}
}