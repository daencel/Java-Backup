
public class Nr07_VerschluesselungDrehen
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Verschluesselung durch Drehen");
		System.out.println("=============================");
	  	do {
			String t = TestScannerErweitert.readString("Text: ");
			while (t.length() < 1) {
				System.out.println("Text muss mindestens ein Zeichen enthalten");
				t = TestScannerErweitert.readString("Text: ");
			}
			int m = 0;
			int a = 0;
			int e = t.length()-1;
			System.out.print("Verschluesslt: ");
			while (a < (t.length()/2)){
				System.out.print((t.charAt(a)));
				System.out.print((t.charAt(e)));
				a++;
				e--;
				m++;
			}
			if (t.length()%2 != 0) {
				System.out.println(t.charAt(m));
			}
			else {
				System.out.println();
			}
	  	} while (Character.toLowerCase(TestScannerErweitert.readChar("Nochmal (j/n)? " )) == 'j');
	}
}
