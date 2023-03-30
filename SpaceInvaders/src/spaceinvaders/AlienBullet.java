package spaceinvaders;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class AlienBullet extends Object {
	
	private int HEIGHT = (int) (SpaceInvadersGUI.HEIGHT / 20);
	private int WIDTH = (int) (SpaceInvadersGUI.WIDTH / 64);

	public AlienBullet(int x, int y, Image img) {
		super(x, y, img);
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void move(int off) {
		this.setY(this.getY() + off);
	}

	public void draw(Graphics g) {
		g.drawImage(this.img, this.getX(), this.getY(), WIDTH, HEIGHT, null);
	}

}
