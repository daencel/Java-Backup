
public class Nr21_ZahlenRatenUmgekhert
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Umgekhertes Zahlenraten");
		System.out.println("=======================");
		System.out.println("Suchen Sie sich eine Zahl im Intervall [0,1000] aus. Ich werde Sie finden.");
		int e;
		int x;
		int anf = 0;
		int end = 1000;
		int t = (int)((Math.random()) * 1000);
		boolean erreicht = false;
		while (erreicht == false) {
			System.out.println("Mein Tipp: " + t);
			e = TestScanner.readInt("Zahl kleiner (0), groesser (1), gefunden (2): ");
			if (e == 0) {
				if (t < anf) {
					System.out.println("Du hast die Zahl waehrend des Spieles gewechselt.");
					break;
				}
				end = t;
				x = (int)((Math.random()*(Math.abs(end - anf))+1));
				t = t - x;
			}
			if (e == 1) {
				if (t > end) {
					System.out.println("Du hast die Zahl waehrend des Spieles gewechselt.");
					break;
				}
				anf = t;
				x = (int)((Math.random()*(Math.abs(end - anf))+1));
				t = t + x;
			}
			if (e == 2) {
				erreicht = true;
				System.out.println("Ich habe die Zahl gefunden!");
			}
		}
	}
}
