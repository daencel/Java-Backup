
public class Nr20_Zahlenraten
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Zahlenraten");
		System.out.println("===========");
		System.out.println("Ich habe mir eine Zahl im Intervall [0,1000] ausgedacht. Versuchen Sie diese zu erraten.");
		int a = (int)((Math.random()) * 1000);
		int e = 0;
		boolean erreicht = false;
		while (erreicht == false) {
			int t = TestScanner.readInt("Ihr Tipp: ");
			if (t < a) {
				System.out.println("Ihre Zahl ist zu klein");
			}
			if (t > a) {
				System.out.println("Ihre Zahl ist zu gross");
			}
			if (t == a) {
				erreicht = true;
			}
			e++;
		}
		System.out.println("Mein Kompliment! Sie haben die Zahl nach " + e + " Versuch/e gefunden.");
	}
}