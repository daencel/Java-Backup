
public class Nr08_Kugelberechnung
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double r;
		double v;
		double o;
		double u;
		System.out.println("Kugelberechnung");
		System.out.println("===============");
		System.out.println();
		r = TestScanner.readDouble("Geben Sie den Radius der Kugel in cm ein: ");
		u=2*r*Math.PI;
		o=4*Math.PI*Math.pow(r, 2);
		v=(4*Math.PI*Math.pow(r, 3))/3;
		System.out.println();
		System.out.println("Der Umfang betraegt:		" + Math.round(u) + " cm");
		System.out.println("Die Oberflache betraegt:	" + Math.round(o) + " cm²");
		System.out.println("Das Volumen betraegt:		" + Math.round(v) + " cm³");
	}

}
