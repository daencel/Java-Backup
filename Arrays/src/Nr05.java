
public class Nr05
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] b = new int [32];
		int a = 0;
		int c = 1;
		while (a<31) {
			b [a] = c;
			c = c*2+1;
			a++;
		}
		System.out.println(b[5]);
		}

}
