package solarsystem;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sun {

	private int x;
	private int y;
	private BufferedImage bf;
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

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

	public BufferedImage getBf() {
		return this.bf;
	}

	public void setBf(BufferedImage bf) {
		this.bf = bf;
	}

	public void draw(Graphics g) {
		g.drawImage(this.getBf(), this.getX() - this.getSize() / 2, this.getY() - this.getSize() / 2, this.getSize(),
				this.getSize(), null);
	}

	public Sun(int x, int y, int size, BufferedImage sun) {
		this.setX(x);
		this.setY(y);
		this.setBf(sun);
		this.setSize(size);
	}
}
