package net.daniellechner.simulation;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Virus {

	public int day = 0;
	/**
	 * probability to get infected
	 */
	private double infectionrate;
	/**
	 * probability to die
	 */
	private double deathrate;
	/**
	 * probability of people gettin out
	 */
	private double movingrate;
	/**
	 * probability of recovering
	 */
	private double recoveryrate;

	public static boolean started = true;

	/**
	 * Creates a Virus
	 * 
	 * @param infectionrate Rate of Infection
	 * @param deathrate     Rate of death
	 * @param movingrate    Likelihood of people leaving the community
	 * @param recoveryrate  Rate of recovery
	 */
	public Virus(double infectionrate, double deathrate, double movingrate, double recoveryrate) {
		this.deathrate = deathrate;
		this.infectionrate = infectionrate;
		this.movingrate = movingrate;
		this.recoveryrate = recoveryrate;
	}

	/**
	 * Checks if there are still People alive
	 * 
	 * @param people all the People
	 * @return true if there is at least one person alive
	 */
	public boolean peopleAlive(ArrayList<Person> people) {
		for (Person person : people)
			if (!person.isDead())
				return true;
		return false;
	}

	/**
	 * Plays the next day scenario
	 * 
	 * @param people all the people
	 */
	public int nextDay(ArrayList<Person> people) {
		day++;
		travel(people);
		for (Person person : people) {
			if (person.isInfected()) {
				person.next();
				person.infectCity(people);
			}
		}
		int rec = 0;
		if (getInfectedCount(people) > 12)
			started = false;
		if (!started)
			rec = recover(people);
		die(people);
		return rec;
	}

	/**
	 * Makes the people travel to another community
	 * 
	 * @param people all the people
	 */
	public void travel(ArrayList<Person> people) {
		for (Person person : people)
			if (Math.random() < getMovingrate() && !person.isDead()) {
				person.setCommunity(new Random().nextInt(9));
			}
	}

	/**
	 * returns the amount of infected people left
	 * 
	 * @param people all the people
	 * @return the infected people amount
	 */
	public int getInfectedCount(ArrayList<Person> people) {
		int count = 0;
		for (Person person : people)
			if (person.isInfected() && !person.isDead())
				count++;
		return count;
	}

	/**
	 * returns the amount of healthy people left
	 * 
	 * @param people all the people
	 * @return the healty people amount
	 */
	public int getHealthyCount(ArrayList<Person> people) {
		int count = 0;
		for (Person person : people)
			if (!person.isInfected() && !person.isDead())
				count++;
		return count;
	}

	/**
	 * Recovers some people
	 * 
	 * @param people all the people
	 */
	public int recover(ArrayList<Person> people) {
		int rec = 0;
		for (Person person : people)
			if (person.isInfected())
				if (Math.random() < getRecoveryrate()) {
					person.setInfected(false);
					rec++;
				}
		return rec;
	}

	/**
	 * Leis some people die
	 * 
	 * @param people all the people
	 */
	public void die(ArrayList<Person> people) {
		for (Person person : people)
			if (person.isInfected())
				if (Math.random() < getDeathrate() / 8)
					person.setDead(true);
	}

	@Override
	public String toString() {
		return "Virus [infectionrate=" + infectionrate + ", deathrate=" + deathrate + ", movingrate=" + movingrate
				+ ", recoveryrate=" + recoveryrate + "]";
	}

	/**
	 * @return the infectionrate
	 */
	public double getInfectionrate() {
		return infectionrate;
	}

	/**
	 * @param infectionrate the infectionrate to set
	 */
	public void setInfectionrate(double infectionrate) {
		this.infectionrate = infectionrate;
	}

	/**
	 * @return the deathrate
	 */
	public double getDeathrate() {
		return deathrate;
	}

	/**
	 * @param deathrate the deathrate to set
	 */
	public void setDeathrate(double deathrate) {
		this.deathrate = deathrate;
	}

	/**
	 * @return the movingrate
	 */
	public double getMovingrate() {
		return movingrate;
	}

	/**
	 * @param movingrate the movingrate to set
	 */
	public void setMovingrate(double movingrate) {
		this.movingrate = movingrate;
	}

	/**
	 * @return the recoveryrate
	 */
	public double getRecoveryrate() {
		return recoveryrate;
	}

	/**
	 * @param recoveryrate the recoveryrate to set
	 */
	public void setRecoveryrate(double recoveryrate) {
		this.recoveryrate = recoveryrate;
	}

}