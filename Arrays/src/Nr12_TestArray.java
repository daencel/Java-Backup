
public class Nr12_TestArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a [] = randomIntArray(7, 0, 9);
		printIntArray("random a:		", a);
		int min = getMinimum(a);
		int max = getMaximum(a);
		double mit = getMittelwert(a);
		int ind = indexOf(a, 9);
		int minpos = getMinPos(a, 5);
		System.out.println("Minimum:		" + min);
		System.out.println("Maximum:		" + max);
		System.out.println("Mittelwert:		" + mit);
		System.out.println("Index of 9:		" + ind);
		System.out.println("Pos of smallest 5:	" + minpos);
		addZahl(a, 1);
		printIntArray("a + 1:			", a);
		swap(a, 0, 3);
		printIntArray("swap (0 and 3):		", a);
		a = delDoppelte(a);
		printIntArray("delete duplicates a:	", a);
		sortMinArray(a);
		printIntArray("sort a:			", a);
	}
	
	/**
	 * Gibt den Inhalt des Arrays int [] a mit 
	 * String msg davor aus
	 * @param a Array dessen Inhalt ausgegeben wird
	 * @param msg Nachricht vor dem Inhalt
	 */
	public static void printIntArray(String msg, int [] a) {
		System.out.print(msg);
		int i = 0;
		System.out.print("{");
		System.out.print(a [i]);
		i++;
		while (i < a.length) {
			System.out.print(", ");
			System.out.print(a [i]);
			i++;
		}
		System.out.print("}\n");
	}
	
	/**
	 * Erstellt eine Array mit der Laenge Anzahl aus den Zahlen zwischen
	 * von bis bis und gibt diese dann zurueck.
	 * @param anzahl Laenge des Array
	 * @param von Startzahl der Zufallszahlen, eingeschlossen
	 * @param bis Endzahl der Zufallszahlen, eingeschlossen
	 * @return erstellte Array
	 */
	public static int[] randomIntArray(int anzahl, int von, int bis) {
		int [] a = new int [anzahl];
		int z = 0;
		while (z < anzahl) {
			a [z] = von + (int)(Math.random() * ((bis - von) + 1));
			z++;
		}
		return a;
	}
	
	/**
	 * Ermittelt die kleinste Zahl in einen Array und giebt diese zurueck
	 * @param a Array
	 * @param min die kleinste Zahl
	 */
	public static int getMinimum(int[] a) {
		int min = Integer.MAX_VALUE;
        for(int i = 0; i< a.length; i++){
            if(a[i]<min){
                min = a[i];
            }           
        }
        return min;
	}
	
	/*
	 * Ermittelt die groesste Zahl in einen Array und giebt diese zurueck
	 * @param a Array
	 * @param max die größte Zahl
	 */
	public static int getMaximum(int[] a) {
		int max = Integer.MIN_VALUE;
        for(int i = 0; i< a.length; i++){
            if(a[i]>max){
                max = a[i];
            }           
        }
        return max;
	}
	
	/**
	 * Ermittelt den Mittelwert einea Arraya und giebt diesen zurueck
	 * @param a Array
	 * @return Mittelwert
	 */
	public static double getMittelwert(int [] a) {
		int x = 0;
		double summe = 0;
		while (x < a.length) {
			int h = a [x];
			summe += h;
			x++;
		}
		double mw = summe/x;
		return mw;
	}

	/**
	 * Gibt die Position von z in dem Array a aus
	 * @param a Array
	 * @param z Zahl
	 * @return Position
	 */
	public static int indexOf(int [] a, int z) {
		int pos = -1;
		int x = 0;
		boolean gefunden = false;
		while (x < a.length && !gefunden) {
			if (a[x] == z) {
				gefunden = true;
				pos = x + 1;
			}
			else {
				x++;
			}
		}
		return pos;
	}
	
	/**
	 * Gibt die Position der kleinsten Zahl in einen Array ab einer Position zurueck
	 * @param a Array
	 * @param pos Position
	 * @return position
	 */
	public static int getMinPos(int [] a, int pos) {
		int min = Integer.MAX_VALUE;
		int posmin = -1;
        while (pos < a.length){
            if(a[pos]<min){
            	posmin = pos;
            	min = a[pos];
            }
            pos++;
        }
        return posmin;
	}
	
	/**
	 * Erhöt jedes Element von a um den Wert z
	 * @param int[] a Array
	 * @param int z Zahl die addiert wird
	 */
	public static void addZahl(int [] a, int z) {
		int x = 0;
		while (x < a.length) {
			a [x] += z;
			x++;
		}
	}
	
	/**
	 * Tauscht im Array a das Element a[i] mit den Element a[j]
	 * @param a Array
	 * @param i Position erstes Element
	 * @param j Position zweites Element 
	 */
	public static void swap(int [] a, int i, int j) {
		int h = a[j];
		a[j] = a[i];
		a[i] = h;
	}
	
	/**
	 * Sortiert die Array vom kleinsten zum groessten.
	 * @param a Array zu sortieren
	 */
	public static void sortMinArray(int [] a) {
		int x = 0;
		while (x < a.length-1) {
			int h = getMinPos(a,x);
			swap(a,x,h);
			x++;
		}
	}
	
	/**
	 * Loescht doppelte Integer Werte in einer Array ohne die Reihenfolge zu aendern
	 * @param list Array mit doppelten Werten
	 * @return neue Array
	 */
	public static int [] delDoppelte(int [] list) {
        int newLength = list.length;
        for (int i = 1; i < list.length; i++) {
            for (int j = 0; j < i; j++) {
                if (list[i] == list[j]) {
                    newLength--;
                    break;
                }
            }
        }
        int[] newArray = new int[newLength];
        newArray[0] = list[0];
        int id = 1;
        boolean isDuplicate;
        for (int i = 1; i < list.length; i++) {
            isDuplicate = false;
            for (int j = 0; j < i; j++) {
                if (list[i] == list[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                newArray[id] = list[i];
                id++;
            }
        }
        return newArray;
    }
}


