
public class PersonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person Anna2 = new Person("Anna", "Huber",true,null,null);
		Person Edi = new Person("Edi","Greif",true,null,null);
		Person Resi = new Person("Resi","Greif",true,null,null);
		Person Martin = new Person("Martin","Seeber",false,null,null);
		Person Edda = new Person("Edda","Huber",true,null,null);
		Person Erwin = new Person("Erwin","Pircher",false,null,null);
		Person Astrid = new Person("Astrid","Prenn",true,null,null);
		Person Franz = new Person("Franz","Amonn",false,null,null);
		Person Berta = new Person("Berta","Greif",true,Anna2,Edi);
		Person Hans = new Person("Hans","Seeber",false,Resi,Martin);
		Person Elsa = new Person("Elsa","Pircher",true,Edda,Erwin);
		Person Sepp = new Person("Sepp","Amonn",false,Astrid,Franz);
		Person Anna = new Person("Anna","Seeber",true,Berta,Hans);
		Person Rudi = new Person("Rudi","Amonn",false,Elsa,Sepp);
		Person Adam = new Person("Adam","Amonn",false,Anna,Rudi);
		Person Grosseltern[] = Adam.getGrosseltern();
		System.out.println(Grosseltern[0].toString());
		System.out.println();
		System.out.println(Anna.getList());
	}

}
