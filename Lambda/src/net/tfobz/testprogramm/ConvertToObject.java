package net.tfobz.testprogramm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * Aufgabe 8/9
 * 
 * @author Daniel Lechner
 *
 */
public class ConvertToObject {

	public static void main(String[] args) {
		int[] numbers = { 1, 3, 6, 8 };
		IntStream iStream = Arrays.stream(numbers);
		ArrayList<Integer> list = new ArrayList<Integer>();
		iStream.boxed().forEach(x -> {
			list.add(x);
			System.out.println(x);
		});

		int sum = list.stream().collect(Collectors.summingInt(Integer::intValue));
		System.out.println("Die Summe ist: " + sum);
	}

}
