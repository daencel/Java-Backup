package net.tfobz.mathematischeausdruecke;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JTest {

	@Test
	public void testMath() {
		String expectedMath = "(((3.0*((6.0+7.0=13.0)^5.0=371293.0)=1113879.0)/(Log(10.0,(Wurzel(2.0,((70.0/4.0=17.5)+(990.0/8.0=123.75)=141.25)=11.884864324004713))=1.074994228245738))=1036172.0749121762)^4.0=1.1527298385505436E24)";
		//@formatter:off
		Operation o = new Potenz(
						new Division(
								new Multiplikation(
										new Konstante(3),
										new Potenz(
												new Addition(
														new Konstante(6), 
														new Konstante(7)), 
												new Konstante(5))),
								new Logarithmus(
										new Wurzel(
												new Addition(
														new Division(
																new Konstante(70), 
																new Konstante(4)),
														new Division(
																new Konstante(990), 
																new Konstante(8))), 
												new Konstante(2)),
										new Konstante(10))),
						new Konstante(4));
		//@formatter:on
		System.out.println("Rechenweg:            " + o.toString());
		System.out.println("Erwarteter Rechenweg: " + expectedMath);
		System.out.println("Loesung:              " + o.getErgebnis());
		assertEquals(expectedMath, o.toString());
	}
}
