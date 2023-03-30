package net.tfobz.tests;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import net.tfobz.algorithm.AStarAlgorithm;
import net.tfobz.algorithm.Node;

/**
 * 
 * @author Daniel Lechner, Noah Aberham
 *
 *         JUnit Test fuer Distanzen und Konstruktor
 */
public class AStarAlgorithmTest {

	private static AStarAlgorithm a;
	private static Node[][] arr;

	@BeforeClass
	public static void build() {
		arr = new Node[10][10];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = new Node(null, i, j, Integer.MAX_VALUE, 0);
			}
		}
		a = new AStarAlgorithm(arr, 5, 5, 9, 9, false);
	}

	@Test
	public void testEuclidianDist() {
		int temp = (int) a.euclidianDist(arr[0][0], arr[1][1]);
		assertEquals(temp, 1);
	}

	@Test
	public void testManhattenDist() {
		int temp = (int) a.manhattanDist(arr[0][0], arr[1][1]);
		assertEquals(temp, 2);
	}
}
