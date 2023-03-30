package net.tfobz.testprogramm;

import java.util.stream.IntStream;

/**
 * 
 * Aufgabe 5
 * 
 * @author Daniel Lechner
 *
 */
public class IntStreamTest {

	private static IntStream stream;

	public static void main(String[] args) {
		stream = IntStream.iterate(100, i -> i + 2).limit(51);
		System.out.println("Sum: " + sum());
		stream = IntStream.iterate(100, i -> i + 2).limit(51);
		System.out.println("Average: " + average());
	}

	public static int sum() {
		return stream.sum();
	}

	public static Double average() {
		return stream.average().getAsDouble();
	}

}
