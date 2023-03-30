
public class Nr17_PerfekteZahlen
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Perfekte Zahlen");
		System.out.println("===============");
		System.out.println("Die ersten 4 perfekten Zahlen sind: ");
		System.out.println();
		int i;
		int a = 2;
		while (a < 1000000000) {
			int erg = 0;
			i = a;
			i--;
			while (i > 0) {
				if (a%i == 0) {
					erg = erg + i;
				}
				i--;
			}
			if (erg == a) {
				System.out.println(a);
			}
			a++;
		}
	}
}