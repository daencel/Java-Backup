package net.tfobz.testprogramm;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 
 * Aufgabe 3/4
 * 
 * @author Daniel Lechner
 *
 */
public class StreamTest {

	public static void main(String[] args) {
		Person a = new Person("Martin Rauch", 40);
		Person b = new Person("Alex Moser", 17);
		Person c = new Person("Diego Picone", 18);
		Handwerker d = new Handwerker("Leon Kaufmann", 18, "Maurer");
		Handwerker e = new Handwerker("Arno Kompatscher", 40, "Landeshauptmann");
		Person[] myList = { a, b, c, d, e };

		Arrays.stream(myList).filter(s -> s instanceof Handwerker).forEach(System.out::println);

		System.out.println("Anzahl Handwerker :" + Arrays.stream(myList).filter(s -> s instanceof Handwerker).count());

		Stream.Builder<Person> builder = Stream.builder();
		Stream<Person> stream = builder.add(a).add(b).add(c).add(d).add(e).build();
		System.out.println("Anzahl handwerker: " + stream.filter(s -> s instanceof Handwerker).count());
	}

}
