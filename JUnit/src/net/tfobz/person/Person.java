package net.tfobz.person;

import java.util.ArrayList;

public class Person {

	public enum Gender {
		FEMALE, MALE
	};

	protected Gender gender = null;
	protected String name = null;
	protected Person mother = null;
	protected Person father = null;
	protected ArrayList<Person> children = new ArrayList<Person>();
	protected ArrayList<Person> daugthers = new ArrayList<Person>();
	protected ArrayList<Person> sons = new ArrayList<Person>();
	protected ArrayList<Person> brothers = new ArrayList<Person>();
	protected ArrayList<Person> sisters = new ArrayList<Person>();
	
	/**
	 * Person - Kontruktor
	 * 
	 * @param name   Name
	 * @param gender Geschlecht
	 */
	public Person(String name, Gender gender) {
		if (name == null || name.length() <= 0) {
			throw new IllegalArgumentException("Name cannot be null or nothing");
		} else if (gender == null) {
			throw new IllegalArgumentException("Gender cannot be null");
		} else {
			this.setName(name);
			this.setGender(gender);
		}
	}

	/**
	 * Gibt das Geschlecht einer Person zurueck
	 * 
	 * @return Geschlecht
	 */
	public Gender getGender() {
		if (this.gender != null && this instanceof Person) {
			return this.gender;
		} else {
			throw new IllegalArgumentException("Gender cannot be null");
		}
	}

	/**
	 * Setzt das Geschlecht einer Person
	 * 
	 * @param gender Geschlecht
	 */
	public void setGender(Gender gender) {
		if (gender == null) {
			throw new IllegalArgumentException("Gender cannot be null");
		} else {
			this.gender = gender;
		}
	}

	/**
	 * Gibt den Namen einer Person zurueck
	 * 
	 * @return Namen
	 */
	public String getName() {
		if (this.name != null && this instanceof Person) {
			return this.name;
		} else {
			throw new IllegalArgumentException("Gender cannot be null");
		}
	}

	/**
	 * Setzt den Namen einer Person
	 * 
	 * @param name Name
	 */
	public void setName(String name) {
		if (name == null || name.length() <= 0) {
			throw new IllegalArgumentException("Name cannot be null or nothing");
		} else {
			this.name = name;
		}
	}

	/**
	 * Gibt die Mutter zurueck
	 * 
	 * @return Mutter
	 */
	public Person getMother() {
		return mother;
	}

	/**
	 * Setzt die Mutter einer Person
	 * 
	 * @param mother Mutter
	 */
	public void setMother(Person mother) {
		if (mother == null && this.getMother() != null) {
			this.getMother().children.remove(this.getMother().getChildren().indexOf(this));
			this.mother = null;
		} else if (mother != null && this.getMother() != null) {
			this.getMother().children.remove(this.getMother().getChildren().indexOf(this));
			this.mother = mother;
			mother.children.add(this);
		} else if (mother == null) {
			throw new IllegalArgumentException("Mother cannot be null");
		} else if (mother.getGender() != Gender.FEMALE) {
			throw new IllegalArgumentException("Mother is wrong gender");
		} else if (this.getMother() == null && !(mother.getChildren().contains(this))) {
			this.mother = mother;
			mother.children.add(this);
		}
	}

	/**
	 * Gibt den Vater einer Person zurueck
	 * 
	 * @return Vater
	 */
	public Person getFather() {
		return father;
	}

	/**
	 * Setzt den Vater einer Person
	 * 
	 * @param father Vater
	 */
	public void setFather(Person father) {
		if (father == null && this.getFather() != null) {
			this.getFather().children.remove(this.getFather().getChildren().indexOf(this));
			this.father = null;
		} else if (father == null) {
			throw new IllegalArgumentException("Father cannot be null");
		} else if (this.getDescendants().contains(father)) {
			throw new IllegalArgumentException("Father is already in descendants");
		} else if (father.equals(this)) {
			throw new IllegalArgumentException("Father cannot be Father of himslef");
		} else if (father != null && this.getFather() != null) {
			this.getFather().children.remove(this.getFather().getChildren().indexOf(this));
			this.father = father;
			father.children.add(this);
		} else if (father.getGender() != Gender.MALE) {
			throw new IllegalArgumentException("Father is wrong gender");
		} else if (this.getFather() == null && !(father.getChildren().contains(this))) {
			this.father = father;
			father.children.add(this);
		}
	}

	/**
	 * Gibt die Kinder als ArrayList zurueck
	 * 
	 * @return Kinder
	 */
	public ArrayList<Person> getChildren() {
		return children;
	}

	/**
	 * Giebt die Toechter als ArrayList zurueck
	 * 
	 * @return Toechter
	 */
	public ArrayList<Person> getDaugthers() {
		children = this.getChildren();
		for (int i = 0; i < children.size(); i++) {
			daugthers.add(children.get(i).clone());
		}
		for (int i = 0; i < daugthers.size(); i++) {
			if (daugthers.get(i).getGender() == Gender.MALE) {
				daugthers.remove(i);
				i--;
			}
		}
		return daugthers;
	}

	/**
	 * Giebt die Soehne als ArrayList zurueck
	 * 
	 * @return Soehne
	 */
	public ArrayList<Person> getSons() {
		children = this.getChildren();
		for (int i = 0; i < children.size(); i++) {
			sons.add(children.get(i).clone());
		}
		for (int i = 0; i < sons.size(); i++) {
			if (sons.get(i).getGender() == Gender.FEMALE) {
				sons.remove(i);
				i--;
			}
		}
		return sons;
	}

	/**
	 * Giebt die Schwestern als ArrayList zurueck
	 * 
	 * @return Schwestern
	 */
	public ArrayList<Person> getSisters() {
		if (this.getFather() != null && this.getMother() != null) {
			children = this.getMother().getChildren();
			for (int i = 0; i < children.size(); i++) {
				sisters.add(children.get(i).clone());
			}
			if (this.getGender() == Gender.FEMALE) {
				sisters.remove(this.getMother().getChildren().indexOf(this));
			}
			for (int i = 0; i < sisters.size(); i++) {
				if (sisters.get(i).getGender() == Gender.MALE) {
					sisters.remove(i);
					i--;
				}
			}
			return sisters;
		} else {
			return sisters;
		}
	}

	/**
	 * Giebt die Brueder als ArrayList zurueck
	 * 
	 * @return Brueder
	 */
	public ArrayList<Person> getBrothers() {
		if (this.getFather() != null && this.getMother() != null) {
			children = this.getMother().getChildren();
			for (int i = 0; i < children.size(); i++) {
				if (children.get(i).getFather() == this.getFather()
						&& children.get(i).getMother() == this.getMother()) {
					brothers.add(children.get(i).clone());
				}
			}
			if (this.getGender() == Gender.MALE) {
				brothers.remove(this.getFather().getChildren().indexOf(this));
			}
			for (int i = 0; i < brothers.size(); i++) {
				if (brothers.get(i).getGender() == Gender.FEMALE) {
					brothers.remove(i);
					i--;
				}
			}
			return brothers;
		} else {
			return brothers;
		}
	}

	/**
	 * Giebt die Nachkommen in einer Arraylist zurueck
	 * 
	 * @return nachkommen
	 */
	public ArrayList<Person> getDescendants() {
		ArrayList<Person> descendants = new ArrayList<Person>();
		for (Person child : this.getChildren()) {
			descendants.add(child);
			descendants.addAll(child.getDescendants());
		}
		return descendants;
	}

	/**
	 * Kontrolliert ob eine Person gleich ist wie eine andere
	 * 
	 * @param zweite Person
	 * @return true wenn beide gleich sind, ansonsten false
	 */
	public boolean equals(Person b) {
		if (this == null || b == null) {
			throw new IllegalArgumentException("Persons cannot be null");
		} else if (!(this instanceof Person) || !(b instanceof Person)) {
			return false;
		} else {
			if (this.getName() == b.getName() && this.getGender() == b.getGender()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Klont eine Person und giebt diese zurueck
	 * 
	 * @return geklonte Person
	 */
	public Person clone() {
		Person ret = new Person(this.name, this.gender);
		return ret;
	}

	/**
	 * Giebt die Person als String zurueck
	 * 
	 * @return String
	 */
	public String toString() {
		return (this.getName() + " " + this.getGender());
	}
}
