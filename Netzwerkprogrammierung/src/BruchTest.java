import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BruchTest {

	@Test
	public void setupRigth() throws BruchException {
		Bruch a = new Bruch(1, 2);
		a.toString();
	}

	@Test(expected = BruchException.class)
	public void setupWrong() throws BruchException {
		Bruch a = new Bruch(3, 0);
		a.toString();
	}

	@Test
	public void kuerzen() throws Exception {
		Bruch a = new Bruch(40, 20);
		Bruch b = new Bruch(2, 1);
		assertTrue(a.compareTo(b));
	}

	@Test
	public void compareToRigth() throws Exception {
		Bruch a = new Bruch(40, 20);
		Bruch b = new Bruch(40, 20);
		assertTrue(a.compareTo(b));
	}

	@Test(expected = NullPointerException.class)
	public void compareToNull() throws Exception {
		Bruch a = new Bruch(40, 20);
		Bruch b = null;
		a.compareTo(b);
	}

	@Test(expected = ClassCastException.class)
	public void compareToWrongType() throws Exception {
		Bruch a = new Bruch(40, 20);
		int b = 3;
		a.compareTo(b);
	}

	@Test
	public void cloneTest() throws Exception {
		Bruch a = new Bruch(1, 2);
		Bruch b = a.clone();
		assertTrue(a.compareTo(b));
	}

	@Test
	public void addiere() throws Exception {
		Bruch a = new Bruch(1, 2);
		Bruch b = new Bruch(1, 2);
		a.addiere(b);
		Bruch result = new Bruch(2, 1);
		assertTrue(a.compareTo(result));
	}
	
	@Test
	public void subrahiere() throws Exception {
		Bruch a = new Bruch(2, 1);
		Bruch b = new Bruch(1, 2);
		a.subdrahiere(b);
		Bruch result = new Bruch(3, 2);
		assertTrue(a.compareTo(result));
	}
	
	@Test
	public void mulipliziere() throws Exception {
		Bruch a = new Bruch(1, 2);
		Bruch b = new Bruch(1, 2);
		a.multipliziere(b);
		Bruch result = new Bruch(1, 4);
		assertTrue(a.compareTo(result));
	}
	
	@Test
	public void dividiere() throws Exception {
		Bruch a = new Bruch(4, 1);
		Bruch b = new Bruch(1, 2);
		a.dividiere(b);
		Bruch result = new Bruch(8, 1);
		assertTrue(a.compareTo(result));
	}
}