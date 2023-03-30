
public class Nr13_Kartentrick {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Kartentrick");
		System.out.println("===========");
		do {
			int [][] karten = null;
			karten = fuellen(karten,0);
			ausgeben(karten);
			int spalte = TestScannerErweitert.readInt("Spalte der Karte: ");
			while (spalte < 1 || spalte > 3) {
				System.out.println("Die Spalte ist nicht im Wetrebreich!");
				spalte = TestScannerErweitert.readInt("Spalte der Karte: ");
			}
			karten = fuellen(karten,spalte);
			ausgeben(karten);
			spalte = TestScannerErweitert.readInt("Spalte der Karte: ");
			while (spalte < 1 || spalte > 3) {
				System.out.println("Die Spalte ist nicht im Wetrebreich!");
				spalte = TestScannerErweitert.readInt("Spalte der Karte: ");
			}
			karten = fuellen(karten,spalte);
			ausgeben(karten);
			spalte = TestScannerErweitert.readInt("Spalte der Karte: ");
			while (spalte < 1 || spalte > 3) {
				System.out.println("Die Spalte ist nicht im Wetrebreich!");
				spalte = TestScannerErweitert.readInt("Spalte der Karte: ");
			}
			karten = fuellen(karten,spalte);
			ausgeben(karten);
			System.out.println("Karte " + karten[3][1] + " wurde gewaehlt");
		} while (Character.toLowerCase(TestScannerErweitert.readChar("Nochmals (j/n)? " )) == 'j');
	}
	
	/**
	 * Füllt die Array karten neu, wobei Spalte a als zweites genommen wird.
	 * @param karten Array
	 * @param a Spalte, welche als zweites ausgegeben wird
	 * @return neue String
	 */
	public static int [][] fuellen(int [][] karten, int a) {
		int [][] karten2 = new int [7][3];
		// keine Spalte wurde ausgweÃ¤hlt, Array wird zum ersten mal befüllt
		if (a == 0) {
			int zahl = 1;
			int z = 0;
			int s = 0;
			while (z <= 6) {
				karten2 [z][s] = zahl;
				z++;
				zahl++;
			}
			z = 0;
			s++;
			while (z <= 6) {
				karten2 [z][s] = zahl;
				z++;
				zahl++;
			}
			z = 0;
			s++;
			while (z <= 6) {
				karten2 [z][s] = zahl;
				z++;
				zahl++;
			}
		}
		// Spalte 1 wurde ausgewÃ¤hlt und Augabe wird ausgerichtet
		if (a == 1) {
			int z1 = 0;
			int s1 = 2;
			int z2 = 0;
			int s2 = 0;
			boolean fertig = false;
			while (fertig == false) {
				karten2 [z2][s2] = karten [z1][s1];
				s2++;
				z1++;
				if (s2 == 3) {
					s2 = 0;
					z2++;
				}
				if (z1 == 7 && s1 == 2) {
					z1 = 0;
					s1 = 0;
				}
				if (z1 == 7 && s1 == 0) {
					z1 = 0;
					s1 = 1;
				}
				if (z2 == 7) {
					fertig = true;
				}
				
			}
		}
		// Spalte 2 wurde ausgewaehlt und Augabe wird ausgerichtet
		if (a == 2) {
			int z1 = 0;
			int s1 = 0;
			int z2 = 0;
			int s2 = 0;
			boolean fertig = false;
			while (!fertig) {
				karten2 [z2][s2] = karten [z1][s1];
				s2++;
				z1++;
				if (s2 == 3) {
					s2 = 0;
					z2++;
				}
				if (z1 == 7 && s1 == 0) {
					z1 = 0;
					s1 = 1;
				}
				if (z1 == 7 && s1 == 1) {
					z1 = 0;
					s1 = 2;
				}
				if (z2 == 7) {
					fertig = true;
				}
			}
		}
		// Spalte 3 wurde ausgewaehlt und Augabe wird ausgerichtet
		if (a == 3) {
			int z1 = 0;
			int s1 = 1;
			int z2 = 0;
			int s2 = 0;
			boolean fertig = false;
			while (fertig == false) {
				karten2 [z2][s2] = karten [z1][s1];
				s2++;
				z1++;
				if (s2 == 3) {
					s2 = 0;
					z2++;
				}
				if (z1 == 7 && s1 == 1) {
					z1 = 0;
					s1 = 2;
				}
				if (z1 == 7 && s1 == 2) {
					z1 = 0;
					s1 = 0;
				}
				if (z2 == 7) {
					fertig = true;
				}
				
			}
		}
		return karten2;
	}
	
	/**
	 * Gibt die Array aus im Format 3 : 7
	 * @param karten Array zum ausgeben
	 */
	public static void ausgeben(int [][] karten) {
		for ( int zeile = 0; zeile < karten.length; zeile++ ){
	      for ( int spalte=0; spalte < karten[zeile].length; spalte++ ) {
	    	  if (karten[zeile][spalte] < 10) {
	    		  System.out.print("  " + karten[zeile][spalte]);
	    	  }
	    	  else
	    		  System.out.print(" " + karten[zeile][spalte]);
	      }
	      System.out.println();
		}
	}
}
