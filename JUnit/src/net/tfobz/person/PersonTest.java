package net.tfobz.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import net.tfobz.person.Person.Gender;

public class PersonTest {

	private static Person p = null;

	@BeforeClass
	public static void setup() throws Exception {
		p = new Person("Sepp", Gender.MALE);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		p = null;
	}

	@Test
	public void creation() {
		assertEquals("Sepp", p.getName());
		assertEquals(Gender.MALE, p.getGender());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nameGenderEmpty() {
		p.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nameEmpty() {
		p.setName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void genderEmpty() {
		p.setGender(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creationgNameGenderEmpty() {
		p = new Person(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creationgGenderEmpty() {
		p = new Person("Sepp", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creationgNameEmpty() {
		p = new Person("", null);
	}

	@Test
	public void equals() {
		Person a = new Person("Maria", Gender.FEMALE);
		Person b = new Person("Maria", Gender.FEMALE);
		// System.out.println(a.equals(b));
		assertEquals(a.getName(), b.getName());
		assertEquals(a.getGender(), b.getGender());
	}

	@Test(expected = IllegalArgumentException.class)
	public void equalsNull() {
		Person a = new Person("Maria", Gender.FEMALE);
		Person b = null;
		a.equals(b);
	}

	@Test
	public void parent() {
		Person v = new Person("Paul", Gender.MALE);
		Person m = new Person("Sabine", Gender.FEMALE);
		p.setFather(v);
		assertSame(p.getFather(), v);
		assertNotNull(p.getFather());
		p.setMother(m);
		assertSame(p.getMother(), m);
		assertNotNull(p.getMother());
	}

	@Test(expected = IllegalArgumentException.class)
	public void parentIncorrectGender() {
		Person wrongmother = new Person("Josep", Gender.MALE);
		Person wrongfather = new Person("Christine", Gender.FEMALE);
		p.setMother(wrongmother);
		p.setFather(wrongfather);
	}

	@Test
	public void children() {
		Person s = new Person("Carl", Gender.MALE);
		Person f = new Person("Paul", Gender.MALE);
		Person m = new Person("Sabine", Gender.FEMALE);
		Person f2 = new Person("Paul 2", Gender.MALE);
		Person m2 = new Person("Sabine 2", Gender.FEMALE);
		// Setzt Eltern
		s.setFather(f);
		s.setMother(m);
		assertEquals(s.getMother(), m);
		assertEquals(s.getFather(), f);
		ArrayList<Person> children = m.getChildren();
		// Tauscht Eltern aus
		s.setMother(m2);
		s.setFather(f2);
		assertEquals(s.getMother(), m2);
		assertEquals(s.getFather(), f2);
		// Loescht Vater und Mutter
		s.setFather(null);
		s.setMother(null);
		assertNull(s.getFather());
		assertNull(s.getMother());
		children = s.getChildren();
		// Muesste leer sein -> keine Ausgabe
		for (int i = 0; i < children.size(); i++) {
			System.out.println(children.get(i).toString());
		}
	}

	@Test
	public void daugthersSons() {
		Person s1 = new Person("Daniel", Gender.MALE);
		Person s2 = new Person("Markus", Gender.MALE);
		Person d1 = new Person("Marie", Gender.FEMALE);
		Person d2 = new Person("Martina", Gender.FEMALE);
		Person f = new Person("Stephan", Gender.MALE);
		Person m = new Person("Caterina", Gender.FEMALE);
		s1.setFather(f);
		s1.setMother(m);
		s2.setFather(f);
		s2.setMother(m);
		d1.setFather(f);
		d1.setMother(m);
		d2.setFather(f);
		d2.setMother(m);
		ArrayList<Person> sons = m.getSons();
		assertEquals(2, sons.size());
		for (int i = 0; i < sons.size(); i++) {
			// System.out.println("Son: " + sons.get(i).toString());
		}
		ArrayList<Person> daugther = f.getDaugthers();
		assertEquals(2, daugther.size());
		for (int i = 0; i < daugther.size(); i++) {
			// System.out.println("Daugther: " + daugther.get(i).toString());
		}
	}

	@Test
	public void sistersBrothers() {
		Person s1 = new Person("Daniel", Gender.MALE);
		Person s2 = new Person("Markus", Gender.MALE);
		Person d1 = new Person("Marie", Gender.FEMALE);
		Person d2 = new Person("Martina", Gender.FEMALE);
		Person f = new Person("Stephan", Gender.MALE);
		Person m = new Person("Caterina", Gender.FEMALE);
		s1.setFather(f);
		s1.setMother(m);
		s2.setFather(f);
		s2.setMother(m);
		d1.setFather(f);
		d1.setMother(m);
		d2.setFather(f);
		d2.setMother(m);
		ArrayList<Person> brotherss1 = s1.getBrothers();
		assertEquals(1, brotherss1.size());
		for (int i = 0; i < brotherss1.size(); i++) {
			// System.out.println("Brother: " + brotherss1.get(i).toString());
		}
		ArrayList<Person> sisterss1 = s1.getSisters();
		assertEquals(2, sisterss1.size());
		for (int i = 0; i < sisterss1.size(); i++) {
			// System.out.println("Sister: " + sisterss1.get(i).toString());
		}
	}

	@Test
	public void descendants() {
		Person Baby = new Person("Nadia", Gender.FEMALE);
		Person Kind1 = new Person("Karl", Gender.MALE);
		Person Kind2 = new Person("Matthias", Gender.MALE);
		Person Kind3 = new Person("Lukas", Gender.MALE);
		Person Kind4 = new Person("Marian", Gender.MALE);
		Person Kind5 = new Person("Leonie", Gender.FEMALE);
		Person Kind6 = new Person("Babsi", Gender.FEMALE);
		Person Elternteil1 = new Person("Matts", Gender.MALE);
		Person Elternteil2 = new Person("Laura", Gender.FEMALE);
		Person Elternteil3 = new Person("Lara", Gender.FEMALE);
		Person Grossvater = new Person("Heinrich", Gender.MALE);
		Baby.setFather(Kind1);
		Kind1.setFather(Elternteil1);
		Kind2.setFather(Elternteil1);
		Kind3.setFather(Elternteil1);
		Kind4.setFather(Elternteil1);
		Kind5.setMother(Elternteil2);
		Kind6.setMother(Elternteil2);
		Elternteil1.setFather(Grossvater);
		Elternteil2.setFather(Grossvater);
		Elternteil3.setFather(Grossvater);
		ArrayList<Person> descendants = Grossvater.getDescendants();
		assertEquals(10, descendants.size());
		for (int i = 0; i < descendants.size(); i++) {
			// System.out.println("Descendants: " + descendants.get(i).toString());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void parentOfHimself() {
		Person temp = new Person("Peter", Gender.MALE);
		temp.setFather(temp);
	}

	@Test(expected = IllegalArgumentException.class)
	public void parentOfDescendant() {
		Person Baby = new Person("Nadia", Gender.FEMALE);
		Person Kind1 = new Person("Karl", Gender.MALE);
		Person Kind2 = new Person("Matthias", Gender.MALE);
		Person Kind3 = new Person("Lukas", Gender.MALE);
		Person Kind4 = new Person("Marian", Gender.MALE);
		Person Kind5 = new Person("Leonie", Gender.FEMALE);
		Person Kind6 = new Person("Babsi", Gender.FEMALE);
		Person Elternteil1 = new Person("Matts", Gender.MALE);
		Person Elternteil2 = new Person("Laura", Gender.FEMALE);
		Person Elternteil3 = new Person("Lara", Gender.FEMALE);
		Person Grossvater = new Person("Heinrich", Gender.MALE);
		Baby.setFather(Kind1);
		Kind1.setFather(Elternteil1);
		Kind2.setFather(Elternteil1);
		Kind3.setFather(Elternteil1);
		Kind4.setFather(Elternteil1);
		Kind5.setMother(Elternteil2);
		Kind6.setMother(Elternteil2);
		Elternteil1.setFather(Grossvater);
		Elternteil2.setFather(Grossvater);
		Elternteil3.setFather(Grossvater);
		Grossvater.setFather(Baby);
	}
}