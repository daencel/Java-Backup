
public class QuadratProgramm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quadrat [] quad = new Quadrat [50];
		int min = 0;
		int minpos = 0;
		for (int i = 0; i < 50; i++) {
			int ran = (int)(Math.random()*11);
			if (ran > min) {
				min = ran;
				minpos = i;
			}
			int a = ran;
			int b = ran;
			int flaeche = ran*ran;
			int umfang = 4*ran;
			Quadrat aa = new Quadrat();
			aa.setSeiteA(a);
			aa.setSeiteB(b);
			aa.setFlaeche(flaeche);
			aa.setUmfang(umfang);
			quad[i] = aa;
		}
		System.out.println(quad[minpos].toString());
	}

}
