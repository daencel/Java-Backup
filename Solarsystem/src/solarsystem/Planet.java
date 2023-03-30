package solarsystem;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Planet extends Sun {

	private double speed;
	private int r;

	public int getR() {
		return this.r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void move(int timeInterval) {
		double orbitX = Solarsystem.WIDTH/2;
	    double orbitY = Solarsystem.HEIGHT/2;
	    double orbitRadius = this.getR();
	    double orbitSpeed = Math.PI / (20000/(this.speed*0.2));
	    double radian = orbitSpeed * timeInterval;
	    int drawX = (int) (orbitX + orbitRadius * Math.cos(radian));
	    int drawY = (int) (orbitY + orbitRadius * Math.sin(radian));
	    
	    this.setX(drawX);
	    this.setY(drawY);
	}

	public void draw(Graphics g) {
		super.draw(g);
	}

	public Planet(int x, int y, int size, double speed, BufferedImage bf) {
		super(x, y, size, bf);
		this.setSpeed(speed);
		this.setR(Solarsystem.HEIGHT/2 - this.getY());
	}
}
