package net.tfobz.testprogramm;

import java.util.stream.IntStream;

/**
 * 
 * Aufgabe 11/12
 * 
 * @author Daniel Lechner
 *
 */
public class IntStreamReduce {

	public static void main(String[] args) {
		IntStream stream = IntStream.range(1, 201);
		stream.mapToObj(x -> "" + x).reduce((x, y) -> x + "," + y).ifPresent(s -> System.out.println(s));
		
		System.out.println();
		
		IntStream.iterate(0, x -> x+1).skip(11).limit(10).forEach(System.out::println);
	}

}
