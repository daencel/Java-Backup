
public class Nr11_Satellitenzeit
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sz;
		int d;
		int h;
		int m;
		int s;
		System.out.println("Satellitenzeit");
		System.out.println("==============");
		sz = TestScanner.readInt("Geben Sie die Sekunden ein: ");
		d = sz / 86400;
		h = (sz%86400)/3600;
		m = ((sz%86400)%3600)/60;
		s = (((sz%86400)%3600)%60);
		System.out.println();
		System.out.println(d+"d "+h+"h "+m+"m "+s+"s ");
	}
}