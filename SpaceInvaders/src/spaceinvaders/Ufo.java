package spaceinvaders;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Ufo extends Object {
	
	private int HEIGHT = (int) (SpaceInvadersGUI.HEIGHT / 13.33);
	private int WIDTH = (int) (SpaceInvadersGUI.WIDTH / 12.8);
	
	public void move(int dir) {
		this.setX(this.getX() + dir);
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

	public Ufo(int x, int y, Image img) {
		super(x, y, img);
	}
}
