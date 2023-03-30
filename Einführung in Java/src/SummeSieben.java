
public class SummeSieben
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 7;
		int b = 1000;
		int c = 1;
		int erg = 0;
		while (erg < b) {
			erg = a * c;
			System.out.println(erg + "	" + erg/c);
			c = c + 1;
		}
	}

}
