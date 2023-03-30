package net.tfobz.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

public class PersonListTest {

	@Test(expected = IllegalArgumentException.class)
	public void readPersonsFromFileError() {
		PersonList personlist;
		String path = "C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\PersonList\\readErrors.txt";
		try {
			personlist = new PersonList(path);
			assertEquals(personlist.size(), 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readPersonsFromFileCorrect() {
		PersonList personlist = null;
		String path = "C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\PersonList\\readCorrect.txt";
		try {
			personlist = new PersonList(path);
			assertEquals(personlist.size(), 7);
			assertSame(personlist.get(2).getFather(), personlist.get(0));

//			for (int i = 0; i < personlist.size(); i++) {
//				 System.out.print(personlist.get(i).toString());
//				if (personlist.get(i).getFather() != null && personlist.get(i).getMother() != null) {
//					 System.out.print(" Father: " + personlist.get(i).getFather().toString());
//					 System.out.print(" Son: " + personlist.get(i).getMother().toString());
//				}
//				 System.out.println();
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
