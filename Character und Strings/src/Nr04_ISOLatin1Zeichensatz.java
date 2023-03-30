
public class Nr04_ISOLatin1Zeichensatz
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char a = (char)32;
		int c = 0;
		int e = 0;
		while (a < 256) {
			if (e == 7) {
				System.out.println(c+32 + " 	" + a + " 	");
				e = 0;
			}
			else {
				if (a > 126 && a < 160) {
					System.out.print(c +32 + "	 ?	 ");
				}
				else {
					System.out.print(c+32 + "	 " + a + "	 ");
				}
				e++;
			}
			a++;
			c++;
		}
	}
}
