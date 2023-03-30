package fahrzeugverwaltung;

import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Vector;
import net.gobbz.fahrzeuge.*;


public class Fahrzeugverwaltung {

	private static Vector<Fahrzeug> v = new Vector<Fahrzeug>();

	public static void main(String[] args) {
		
		v.addElement(new Car(300, 4000, 250));
		v.addElement(new Bicycle(20, 20, true));
		v.addElement(new Semi(190, 10000, 300, 20000));
		
		System.out.println("Fahrzeugverwaltung");
		int input = 0;
		do {
			System.out.println("==================");
			System.out.println("1 - Eingabe");
			System.out.println("2 - Suchen");
			System.out.println("3 - Aendern");
			System.out.println("4 - Loeschen");
			System.out.println("5 - Liste");
			System.out.println("6 - Ende");
			do {
				try {
					input = TestScannerErweitert.readInt("Ihre Wahl (1 - 6): ");
				} catch (InputMismatchException e1) {
				}
			} while (input > 6 || input < 1);
			System.out.println();
			switch (input) {
			case 1:
				System.out.println("1 - Auto hinzufuegen");
				System.out.println("2 - Fahrrad hinzufuegen");
				System.out.println("3 - Lastwagen hinzufuegen");
				int in = 0;
				do {
					try {
						in = TestScannerErweitert.readInt("Eingabe: ");
					} catch (InputMismatchException e1) {
						in = 0;
					}
				} while (in > 3 || in == 0);
				boolean erfolgreich = true;
				switch (in) {
				case 1:
					erfolgreich = true;
					do {
						try {
							int g = TestScannerErweitert.readInt("Geschwindigkeit: ");
							int k = TestScannerErweitert.readInt("Gewicht: ");
							int ps = TestScannerErweitert.readInt("PS: ");
							Fahrzeug tmp = new Car(g, k, ps);
							v.addElement(tmp);
							erfolgreich = true;
						} catch (InputMismatchException e2) {
							System.out.println("illegal input");
							erfolgreich = false;
						}
					} while (!erfolgreich);
					break;
				case 2:
					erfolgreich = true;
					do {
						try {
							int g = TestScannerErweitert.readInt("Geschwindigkeit: ");
							int k = TestScannerErweitert.readInt("Gewicht: ");
							int licht = 0;
							do {
								licht = TestScannerErweitert.readInt("Lichter vorhanden? 1> ja | 0> nein : ");
							} while (licht != 0 && licht != 1);
							boolean bbike = false;
							if (licht == 1)
								bbike = true;
							Fahrzeug tmp = new Bicycle(g, k, bbike);
							v.addElement(tmp);
						} catch (InputMismatchException e2) {
							System.out.println("illegal input");
							erfolgreich = false;
						}
					} while (!erfolgreich);
					break;
				case 3:
					erfolgreich = true;
					do {
						try {
							int g = TestScannerErweitert.readInt("Geschwindigkeit: ");
							int k = TestScannerErweitert.readInt("Gewicht: ");
							int ps = TestScannerErweitert.readInt("PS: ");
							int l = TestScannerErweitert.readInt("Ladeflaeche: ");
							Fahrzeug tempcar = new Semi(g, k, ps, l);
							v.addElement(tempcar);
							erfolgreich = true;
						} catch (InputMismatchException e2) {
							System.out.println("illegal input");
							erfolgreich = false;
						}
					} while (!erfolgreich);
					break;
				}
				break;
			case 2:
				int nr = 0;
				do {
					try {
						nr = TestScannerErweitert.readInt("Geben Sie die ID des gesuchten Fahrzeuges ein: ");
						nr = idToPos(nr, v);
					} catch (InputMismatchException e2) {
						System.out.println("illegal input");
						nr = -1;
					}
				} while (nr == -1);
				System.out.println();
				System.out.println(v.elementAt(nr).toString());
				break;
			case 3:
				int id = 0;
				do {
					try {
						id = TestScannerErweitert.readInt("Geben Sie die ID des zu aendernden Fahrzeuges ein: ");
						id = idToPos(id, v);
					} catch (InputMismatchException e2) {
						System.out.println("illegal input");
						id = -1;
					}
				} while (id == -1);
				System.out.println();
				System.out.println(v.elementAt(id).toString());
				if (v.elementAt(id) instanceof Car) {
					erfolgreich = true;
					do {
						try {
							Car temp = (Car) v.elementAt(id);
							int g = TestScannerErweitert.readInt("Neue Geschwindigkeit: ");
							int k = TestScannerErweitert.readInt("Neues Gewicht: ");
							int ps = TestScannerErweitert.readInt("Neue PS: ");
							temp.setSpeed(g);
							temp.setWeight(k);
							temp.setPs(ps);
							v.set(id, temp);
							erfolgreich = true;
						} catch (InputMismatchException e2) {
							erfolgreich = false;
						}
					} while (!erfolgreich);
				} else if (v.elementAt(id) instanceof Semi) {
					erfolgreich = true;
					do {
						try {
							Semi temp = (Semi)v.elementAt(id);
							int g = TestScannerErweitert.readInt("Neue Geschwindigkeit: ");
							int k = TestScannerErweitert.readInt("Neues Gewicht: ");
							int ps = TestScannerErweitert.readInt("Neue PS: ");
							int l = TestScannerErweitert.readInt("Neue Ladeflaeche: ");
							temp.setSpeed(g);
							temp.setWeight(k);
							temp.setPs(ps);
							temp.setCapacity(l);
							v.set(id, temp);
							erfolgreich = true;
						} catch (InputMismatchException e2) {
							erfolgreich = false;
						}
					} while (!erfolgreich);
				} else if (v.elementAt(id) instanceof Bicycle) {
					erfolgreich = true;
					do {
						try {
							Bicycle temp = (Bicycle)v.elementAt(id);
							int g = TestScannerErweitert.readInt("Neue Geschwindigkeit: ");
							int k = TestScannerErweitert.readInt("Neues Gewicht: ");
							int licht = 0;
							do {
								licht = TestScannerErweitert.readInt("Lichter vorhanden? 1> ja | 0> nein : ");
							} while (licht != 0 && licht != 1);
							boolean bbike = false;
							if (licht == 1)
								bbike = true;
							temp.setSpeed(g);
							temp.setWeight(k);
							temp.setLaemp(bbike);
							erfolgreich = true;
						} catch (InputMismatchException e2) {
							erfolgreich = false;
						}
					} while (!erfolgreich);
				}
				
				break;
			case 4:
				int delnr = 0;
				do {
					try {
						delnr = TestScannerErweitert.readInt("Geben Sie die ID des zu loeschenden Fahrzeuges ein: ");
						delnr = idToPos(delnr, v);
					} catch (InputMismatchException e2) {
						System.out.println("illegal input");
						delnr = -1;
					}
				} while (delnr == -1);
				v.remove(delnr);
				break;
			case 5:
				Enumeration<Fahrzeug> e = v.elements();
				while (e.hasMoreElements()) {
					Fahrzeug temp = (Fahrzeug) e.nextElement();
					System.out.println(temp.toString());
				}
				break;
			}
			System.out.println();
		} while (input != 6);
	}

	public static int idToPos(int id, Vector<Fahrzeug> v) {
		for (int i = 0; i < v.size(); i++) {
			if (v.elementAt(i).getID() == id) {
				return i;
			}
		}
		return -1;
	}
}
