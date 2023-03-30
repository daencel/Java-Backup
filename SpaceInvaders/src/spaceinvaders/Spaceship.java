package spaceinvaders;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Spaceship extends Object {

	protected boolean alive;
	protected int lives;
	private int HEIGHT = (int) (SpaceInvadersGUI.HEIGHT / 14);
	private int WIDTH = (int) (SpaceInvadersGUI.WIDTH / 15);

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getLives() {
		return this.lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public Spaceship(int x, int y, Image img) {
		super(x, y, img);
		this.setAlive(true);
		this.setLives(3);
	}

	public int decreaselives() {
		this.lives--;
		return this.lives;
	}

	public void move(int dir) {
		this.setX(this.getX() + dir);
	}

	public void draw(Graphics g) {
		g.drawImage(this.img, this.getX(), this.getY(), WIDTH, HEIGHT, null);
	}
}
