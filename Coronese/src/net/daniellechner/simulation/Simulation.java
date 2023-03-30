package net.daniellechner.simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Simulation extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Person> people;
	private int width;
	private int height;
	private Image neutral;
	private Image dead;
	private Image infected;

	public void setPeople(ArrayList<Person> people, int width, int height) {
		this.people = people;
		this.width = width;
		this.height = height;
		neutral = new ImageIcon(getClass().getResource("neutral.png")).getImage();
		dead = new ImageIcon(getClass().getResource("dead.png")).getImage();
		infected = new ImageIcon(getClass().getResource("infected.png")).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Random ran = new Random();
		int size = 12;
		if (people == null)
			return;
		for (Person person : people) {
			g.setColor(Color.BLACK);
			g.drawLine(0, height / 3, width, height / 3);
			g.drawLine(0, height / 3 * 2, width, height / 3 * 2);
			g.drawLine(0, height / 3 * 3, width, height / 3 * 3);
			g.drawLine(width / 3, 0, width / 3, height);
			g.drawLine(width / 3 * 2, 0, width / 3 * 2, height);
			g.drawLine(width / 3 * 3, 0, width / 3 * 3, height);
			Image active = neutral;
			if (person.isInfected()) {
				g.setColor(Color.ORANGE);
				active = infected;
			}
			if (person.isDead()) {
				g.setColor(Color.RED);
				active = dead;
			}

			int x = 0;
			int y = 0;
			if (person.getCommunity() == 0) {
				x = ran.nextInt(width / 3);
				y = ran.nextInt(height / 3);
			} else if (person.getCommunity() == 1) {
				x = ran.nextInt(width / 3) + width / 3;
				y = ran.nextInt(height / 3);
			} else if (person.getCommunity() == 2) {
				x = ran.nextInt(width / 3) + width / 3 + width / 3;
				y = ran.nextInt(height / 3);
			} else if (person.getCommunity() == 3) {
				x = ran.nextInt(width / 3);
				y = ran.nextInt(height / 3) + height / 3;
			} else if (person.getCommunity() == 4) {
				x = ran.nextInt(width / 3) + width / 3;
				y = ran.nextInt(height / 3) + height / 3;
			} else if (person.getCommunity() == 5) {
				x = ran.nextInt(width / 3) + width / 3 + width / 3;
				y = ran.nextInt(height / 3) + height / 3;
			} else if (person.getCommunity() == 6) {
				x = ran.nextInt(width / 3);
				y = ran.nextInt(height / 3) + height / 3 + height / 3;
			} else if (person.getCommunity() == 7) {
				x = ran.nextInt(width / 3) + width / 3;
				y = ran.nextInt(height / 3) + height / 3 + height / 3;
			} else if (person.getCommunity() == 8) {
				x = ran.nextInt(width / 3) + width / 3 + width / 3;
				y = ran.nextInt(height / 3) + height / 3 + height / 3;
			}
			if (person.isDead()) {
				if (person.getX() == 0 && person.getY() == 0) {
					person.setX(x);
					person.setY(y);
				}
				g.drawImage(active, person.getX(), person.getY(), size, size, null);
				// g.fillOval(person.getX(), person.getY(), size, size);
			} else if (person.daySinceInfected > 5) {
				if (person.getX() == 0 && person.getY() == 0) {
					person.setX(x);
					person.setY(y);
				}
				g.drawImage(active, person.getX(), person.getY(), size, size, null);
				// g.fillOval(person.getX(), person.getY(), size, size);
			} else
				g.drawImage(active, x, y, size, size, null);
			// g.fillOval(x, y, size, size);
		}
	}
}
