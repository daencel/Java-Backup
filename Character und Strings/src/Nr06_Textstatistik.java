
public class Nr06_Textstatistik
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Textstatistik");
		System.out.println("=============");
		String a = "abc";
		int s = 0;
		int b = 0;
		int l = 0;
		int z = 0;
		int i = 0;
		a = TestScannerErweitert.readString("Text: ");
		while (a.length() < 1) {
			System.out.println("Text muss mindestens ein Zeichen enthalten");
			a = TestScannerErweitert.readString("Text: ");
		}
		while (i < a.length()) {
			//Selbstlaute
			int h = (a.charAt(i));
			if (h == 'a' || h == 'e' || h == 'i' || h == 'o' || h == 'u' || h == 'A' || h == 'E' || h == 'I' || h == 'O' || h == 'U') {
				s++;
			}
			i++;
		}
		i = 0;
		while (i < a.length()) {
			//Buchstaben
			if (Character.isLetter(a.charAt(i))) {
				b++;
			}
			i++;
		}
		i = 0;
		while (i < a.length()) {
			//Leerzeichen
			if (Character.isWhitespace(a.charAt(i))) {
				l++;
			}
			i++;
		}
		i = 0;
		while (i < a.length()) {
			//Zeichen
			z++;
			i++;
		}
		System.out.println();
		System.out.println("Anzahl Selbstlaute:	" + s);
		System.out.println("Anzahl Buchstaben:	" + b);
		System.out.println("Anzahl Leerzeichen:	" + l);
		System.out.println("Anzahl Zeichen:		" + z);
	}

}
