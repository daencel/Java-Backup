
public class Nr00_DoubleUeberlauf
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a = Double.MAX_VALUE;
		int x = 0;
		while (x <= 100000) {
			a=a+1E300;
			x=x+1;
		}
		System.out.println(a);
	}
}