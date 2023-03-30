
public class EinMalEins
{
	public static void printerg(int erg) {
		if (erg < 10)
			System.out.print("   " + erg);
		else
			if (erg < 100)
				System.out.print("  " + erg);
			else
				System.out.print(" " + erg);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int y = 0;
		int erg;
		System.out.println("Ein Mal Eins - Tabelle");
		System.out.println("========================================");
		while (y<10) {
			y++;
			erg = 0;
			while (erg<(y*10)) {
				erg = erg + y;
				printerg(erg);
			}
			System.out.println();
		}
	}
}
