package net.tfobz.testprogramm;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * Aufgabe 10/13/14
 * 
 * @author Daniel Lechner
 *
 */
public class PersonenTest {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Mai", 90));
		persons.add(new Person("Mark", 17));
		persons.add(new Handwerker("Markus", 33, "Handwerker"));
		persons.add(new Person("Max", 35));
		persons.add(new Person("Noah", 64));
		persons.add(new Person("Diego", 5));
		persons.add(new Person("Yao", 19));

		Supplier<Stream<Person>> supplier = () -> persons.stream();

		IntSummaryStatistics temp = supplier.get().collect(Collectors.summarizingInt(x -> x.getAlter()));
		System.out.println(temp + "\n");

		System.out.println(supplier.get().collect(Collectors.groupingBy(s -> s.getName().charAt(0))).toString() + "\n");

		System.out.println(
				supplier.get().collect(Collectors.groupingBy(s -> s.getClass().getSimpleName())).toString() + "\n");
		
		System.out.println(
				supplier.get().collect(Collectors.groupingBy(p -> p.getName().charAt(0), Collectors.counting())));

		List<Person> list = supplier.get().filter(s -> s.getName().length() > 3).collect(Collectors.toList());
		for (Person object : list) {
			System.out.println(object);
		}
	}

}