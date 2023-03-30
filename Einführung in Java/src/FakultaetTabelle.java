
public class FakultaetTabelle
{

	public static void printerg(int erg) {
		if (erg < 10)
			System.out.println("         " + erg);
		else
			if (erg < 100)
				System.out.println("        " + erg);
			else
				if (erg < 1000)
					System.out.println("       " + erg);
				else
					if (erg < 10000)
						System.out.println("      " + erg);
					else
						if (erg < 100000)
							System.out.println("     " + erg);
						else
							if (erg < 1000000)
								System.out.println("    " + erg);
							else
								if (erg < 10000000)
									System.out.println("  " + erg);
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n= 0;
		int x = 1;
		int erg = 1;
		System.out.println("	n"+"	  n!");
		System.out.println("===================");
		while (n < 10) {
			n = n + 1;
				while (x <= n) {
					erg = erg * x;
					x = x + 1;
				}
			System.out.print("	"+n);
			printerg(erg);
		}
	}
}
