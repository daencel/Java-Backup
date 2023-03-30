package spaceinvaders;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Bullet extends Object {
	
	private int HEIGHT = (int) (SpaceInvadersGUI.HEIGHT / 20);
	private int WIDTH = (int) (SpaceInvadersGUI.WIDTH / 64);

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public Bullet(int x, int y, Image img) {
		super(x, y, img);
	}

	public void move(int y) {
		this.setY(this.getY() - y);
	}

	public void draw(Graphics g) {
		g.drawImage(this.img, this.getX(), this.getY(), WIDTH, HEIGHT, null);
	}
}
