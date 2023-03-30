package net.tfobz.testprogramm;

import java.util.Arrays;

/**
 * 
 * Aufgabe 7
 * 
 * @author Daniel Lechner
 *
 */
public class MaximumTest {

	public static void main(String[] args) {
		int[] numbers = { 1, 3, 6, 8 };
		int max = Arrays.stream(numbers).reduce(Integer::max).getAsInt();
		System.out.println("Highest number: " + max);
	}
}