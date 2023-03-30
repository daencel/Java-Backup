
public class Nr08_VerschluesselungCaesar
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Verschluesselung nach Caesar");
		System.out.println("============================");
		char w = TestScannerErweitert.readChar("V>erschluesseln, E>ntschluesseln, A>bbruch: ");
		while (w == 'V' || w == 'v' || w == 'E' || w == 'e') {
			if (w == 'V' || w == 'v') {
				String a = TestScannerErweitert.readString("Text: ");
				while (a.length() < 1) {
					System.out.println("Text muss mindestens ein zeichen enthalten!");
					a = TestScannerErweitert.readString("Text: ");
				}
				a = a.toLowerCase();
				int v = TestScannerErweitert.readInt("Verschiebung: ");
				if (v > 26) {
					int r = v/26;
					v = v - (r*26);
				}
				System.out.print("Verschluesselt: ");
				int i = 0;
				while (i < a.length()) {
					char b = a.charAt(i);
					if (!(b >= 65 && b <=122)) {
						i++;
					}
					else {
						int z = b + v;
						if (z > 122) {
							int r = z - 123;
							z = 97 + r;
						}
						if (z < 97) {
							int r = z - 96;
							z = 122 - Math.abs(r);
						}
						if (z > 96) {
							z = z - 32;
							System.out.print((char)z);
							i++;
						}
						else {
							System.out.print((char)z);
							i++;
						}
					}
				}
				System.out.println();
				w = TestScannerErweitert.readChar("V>erschluesseln, E>ntschluesseln, A>bbruch: ");
			}
			if (w == 'E' || w == 'e') {
				int i = 0;
				String a = TestScannerErweitert.readString("Text: ");
				while (a.length() < 1) {
					System.out.println("Text muss mindestens ein zeichen enthalten!");
					a = TestScannerErweitert.readString("Text: ");
				}
				a = a.toUpperCase();
				int v = TestScannerErweitert.readInt("Verschiebung: ");
				if (v > 26) {
					int r = v/26;
					v = v - (r*26);
				}
				System.out.print("Verschluesselt: ");
				i = 0;
				while (i < a.length()) {
					char b = a.charAt(i);
						int z = b - v;
						if (z < 65) {
							int r = z - 64;
							z = 90 - Math.abs(r);
						}
						if (z > 90) {
							int r = z - 90;
							z = 65 + Math.abs(r);
						}
						System.out.print((char)z);
						i++;
				}
				System.out.println();
				w = TestScannerErweitert.readChar("V>erschluesseln, E>ntschluesseln, A>bbruch: ");
			}
		}
	}
}