package spaceinvaders;

import java.awt.Image;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Object {

	protected int x;
	protected int y;
	protected Image img;

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImg() {
		return this.img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Object(int x, int y, Image img) {
		this.setImg(img);
		this.setX(x);
		this.setY(y);
	}
}
