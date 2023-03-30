package net.tfobz.person;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import net.tfobz.person.Person.Gender;

public class PersonList extends ArrayList<Person> {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor mit Buffered Reader
	 * 
	 * @param reader Buffered Reader
	 * @throws IOException IO Fehler
	 */
	public PersonList(BufferedReader reader) throws IOException {
		readPersons(reader);
	}

	/**
	 * Konstruktor mit path
	 * 
	 * @param filename path
	 * @throws FileNotFoundException File wurde nicht gefunden
	 * @throws IOException           IO Fehler
	 */
	public PersonList(String filename) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(filename);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		readPersons(reader);
	}

	/**
	 * Liest die Personen aus der File
	 * 
	 * @param reader Buffered Reader
	 * @throws IOException
	 */
	private void readPersons(BufferedReader reader) throws IOException {
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				reader.close();
				break;
			}
			String[] parts = line.split(";");
			String name;
			Gender gender;
			if (parts.length == 4) {
				if (parts[0] != null && parts[0] != "null" && parts[0].length() > 0) {
					name = parts[0];
				} else {
					throw new IllegalArgumentException(line + " -> at the Name occured an Error");
				}
				if (parts[1] != "null" && parts[1].equals("MALE")) {
					gender = Gender.MALE;
				} else if (parts[1] != "null" && parts[1].equals("FEMALE")) {
					gender = Gender.FEMALE;
				} else {
					throw new IllegalArgumentException(line + " -> at the Gender occured an Error");
				}
				Person add = new Person(name, gender);
				if (parts[2] != "null" && parts[2].length() > 0) {
					for (int i = 0; i < PersonList.this.size(); i++) {
						if (PersonList.this.get(i).getGender() == Gender.FEMALE
								&& PersonList.this.get(i).getName().equals(parts[2])) {
							add.setMother(PersonList.this.get(i));
						}
					}
				}
				if (parts[3] != "null" && parts[3].length() > 0) {
					for (int i = 0; i < PersonList.this.size(); i++) {
						if (PersonList.this.get(i).getGender() == Gender.MALE
								&& PersonList.this.get(i).getName().equals(parts[3])) {
							add.setFather(PersonList.this.get(i));
						}
					}
				}
				PersonList.this.add(add);
			} else {
				throw new IllegalArgumentException(line + " -> made a Problem");
			}
		}
	}
}
