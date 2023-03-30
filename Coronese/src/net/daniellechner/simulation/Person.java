package net.daniellechner.simulation;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Person {

	private boolean dead = false;
	private boolean infected = false;
	private int community;
	private int x;
	private int y;
	public double daySinceInfected = 0;
	public boolean immune = false;

	public Person(int community) {
		if (community <= 8 && community >= 0)
			this.community = community;
		else
			this.community = new Random().nextInt(9);
	}

	/**
	 * infects a City
	 * 
	 * @param people all people
	 */
	public void infectCity(ArrayList<Person> people) {
		for (Person person : people)
			if (person.getCommunity() == this.getCommunity())
				if (Math.random() < GUI.virus.getInfectionrate() && !person.isDead())
					if (!immune)
						if (Virus.started) {
							person.setInfected(true);
						} else {
							if (this.daySinceInfected < 5)
								person.setInfected(true);
						}
					else if (Math.random() < 0.2)
						person.setInfected(true);
	}

	public void next() {
		daySinceInfected++;
	}

	@Override
	public String toString() {
		return "Person [dead=" + dead + ", infected=" + infected + ", community=" + community + "]";
	}

	/**
	 * @return the infected
	 */
	public boolean isInfected() {
		return infected;
	}

	/**
	 * @param infected the infected to set
	 */
	public void setInfected(boolean infected) {
		if (infected == false) {
			this.daySinceInfected = 0;
			this.immune = true;
		}
		this.infected = infected;
	}

	/**
	 * @return the community
	 */
	public int getCommunity() {
		return community;
	}

	/**
	 * @param community the community to set
	 */
	public void setCommunity(int community) {
		this.community = community;
	}

	/**
	 * @return the dead
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * @param dead the dead to set
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
		this.infected = false;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

}
