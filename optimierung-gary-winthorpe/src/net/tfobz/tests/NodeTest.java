package net.tfobz.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import net.tfobz.algorithm.*;

/**
 * 
 * @author Daniel Lechner, Noah Aberham
 * 
 *         JUnit Test fuer Node Klasse
 */
public class NodeTest {

	@Test
	public void buildTest() {
		Node a = new Node(null, 1, 2, 3, 4);
		assertNull(a.getParent());
		assertEquals(a.getX(), 1);
		assertEquals(a.getY(), 2);
		assertEquals((int) a.getG(), 3);
		assertEquals((int) a.getH(), 4);
		assertEquals((int) a.getF(), 7);
	}

	@Test
	public void parentTest() {
		Node a = new Node(null, 1, 2, 3, 4);
		Node b = new Node(a, 2, 2, 4, 3);
		assertSame(b.getParent(), a);
	}

	@Test
	public void compareTest() {
		Node a = new Node(null, 1, 2, 3, 4);
		Node b = new Node(null, 1, 2, 3, 4);
		assertEquals(0, a.compareTo(b));
		b.setG(100);
		assertEquals(-1, a.compareTo(b));
		b.setG(0);
		assertEquals(1, a.compareTo(b));
	}

	@Test
	public void getFTest() {
		Node a = new Node(null, 1, 2, 3, 4);
		assertEquals((int) a.getF(), 7);
	}
}
