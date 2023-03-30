
public class KreisProgramm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Kreis k1 = new Kreis();
		double f = 3;
		k1.setFlaeche(f);
		Kreis k2 = new Kreis();
		k2 = k1.clone();
		System.out.println(k1.toString());
		System.out.println(k2.toString());
		System.out.println("k1.equals(k2) ergibt " + k1.equals(k2));
		k2.setUmfang(-1);
		k2.setRadius(1);
		System.out.println("k1.compareTo(k2) ergibt " + k1.compareTo(k2));
		System.out.println(k1.toString());
		System.out.println(k2.toString());
	}
}
