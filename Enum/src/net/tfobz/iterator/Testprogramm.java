package net.tfobz.iterator;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Testprogramm {

	public static void main(String[] args) {
		MeineDefaultListe<Integer> liste = new MeineDefaultListe<Integer>();
		
		MeinIterator<Integer> iterator = liste.elemente();
		iterator.einfuegenElement(1);
		iterator.einfuegenElement(2);
		iterator.einfuegenElement(3);
		iterator.einfuegenElement(4);
		iterator.einfuegenElement(5);
		iterator = liste.elemente();
		
		while(iterator.hatNaechstesElement()) {
			System.out.print(iterator.naechstesElement() + "; ");
		}
	}

}
