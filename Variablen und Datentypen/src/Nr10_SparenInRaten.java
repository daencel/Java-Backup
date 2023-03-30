
public class Nr10_SparenInRaten
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double g;
		double m;
		double j;
		double g2;
		int x = 12;
		double z = 0;
		System.out.println("Sparen in Raten");
		System.out.println("===============");
		System.out.println();
		g = TestScanner.readDouble("Geben Sie das Guthaben am Jahresanfang ein: ");
		m = TestScanner.readDouble("Geben Sie die Monatsrate ein: ");
		j = TestScanner.readDouble("Geben Sie den Jahreszinssatz ein: ");
		while (x>0) {
			z = m*(x/12.) * (j/100.) + z;
			x--;
		}
		g2 = g + (g * j/100.) + z + (12 * m);	
		System.out.println();
		System.out.println("Das Guthaben am Ende des Jahres betraegt: " + Math.round(g2*100)/100.);
	}
}