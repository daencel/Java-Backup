package net.tfobz.testprogramm;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 *
 * Aufgabe 6
 *
 * @author Daniel Lechner
 *
 */
public class DoubleStreamTest {

	public static void main(String[] args) {
		double[] numbers = { 1.0, 3.0, 6.0, 8.0 };
		DoubleStream dStream = Arrays.stream(numbers);
		Object[] objects = dStream.mapToInt(num -> (int) num).mapToObj(x -> "a" + x).toArray();
		for (Object object : objects) {
			System.out.println(object);
		}
	}

}