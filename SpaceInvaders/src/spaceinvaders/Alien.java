package spaceinvaders;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Alien extends Object {

	protected boolean alive;
	private int HEIGHT = (int) (SpaceInvadersGUI.HEIGHT / 20);
	private int WIDTH = (int) (SpaceInvadersGUI.WIDTH / 25.6);

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void move(int diff) {
		this.x += diff;
	}

	public Alien(int x, int y, Image alien) {
		super(x, y, alien);
		this.setAlive(true);
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void draw(Graphics g) {
		g.drawImage(this.img, this.getX(), this.getY(), WIDTH, HEIGHT, null);
	}
}
