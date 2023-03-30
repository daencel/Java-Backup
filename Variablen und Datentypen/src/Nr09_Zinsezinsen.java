
public class Nr09_Zinsezinsen
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double k1;
		double k2;
		double p;
		int n;
		System.out.println("Zinsezinsen");
		System.out.println("===========");
		System.out.println();
		k1 = TestScanner.readDouble("Geben Sie das Anfangskapital ein: ");
		p = TestScanner.readDouble("Geben Sie den Zinssatz ein: ");
		n = TestScanner.readInt("Geben Sie die Jahre ein: ");
		k2 = k1 * Math.pow(1+p/100,n);
		System.out.println();
		System.out.println("Ihr Endkapital nach " + n + " Jahren betraegt: " + Math.round(k2*100)/100.);
	}
}