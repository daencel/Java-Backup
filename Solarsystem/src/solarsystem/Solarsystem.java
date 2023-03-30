package solarsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Solarsystem extends JFrame {

	final public static int HEIGHT = 1600;
	final public static int WIDTH = 2560;
	private static final long serialVersionUID = 1L;
	private BufferedImage bf;
	private Planet planets[] = new Planet[80];
	private Sun sun;
	private int counter = 0;
	private int years = 0;

	public static void main(String[] args) {
		new Solarsystem();
	}

	public Solarsystem() {
		setTitle("Solar - System");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		BufferedImage Mercury = null, Venus = null, Earth = null, Mars = null, Jupiter = null, Saturn = null,
				Uranus = null, Neptune = null, Sun = null;
		try {
			Mercury = ImageIO.read(new File("Images/Mercury.png"));
			Venus = ImageIO.read(new File("Images/Venus.png"));
			Earth = ImageIO.read(new File("Images/Earth.png"));
			Mars = ImageIO.read(new File("Images/Mars.png"));
			Jupiter = ImageIO.read(new File("Images/Jupiter.png"));
			Saturn = ImageIO.read(new File("Images/Saturn.png"));
			Uranus = ImageIO.read(new File("Images/Uranus.png"));
			Neptune = ImageIO.read(new File("Images/Neptune.png"));
			Sun = ImageIO.read(new File("Images/Sun.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		bf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		sun = new Sun(WIDTH / 2, HEIGHT / 2, 180, Sun);
		planets[0] = new Planet(WIDTH / 2, HEIGHT / 2 - 140, 4, 240, Mercury);
		planets[1] = new Planet(WIDTH / 2, HEIGHT / 2 - 190, 10, 111, Venus);
		planets[2] = new Planet(WIDTH / 2, HEIGHT / 2 - 250, 12, 68.5, Earth);
		planets[3] = new Planet(WIDTH / 2, HEIGHT / 2 - 310, 5, 36, Mars);
		planets[4] = new Planet(WIDTH / 2, HEIGHT / 2 - 440, 120, 5.8, Jupiter);
		planets[5] = new Planet(WIDTH / 2, HEIGHT / 2 - 570, 115, 2.2, Saturn);
		planets[6] = new Planet(WIDTH / 2, HEIGHT / 2 - 670, 42, 0.8, Uranus);
		planets[7] = new Planet(WIDTH / 2, HEIGHT / 2 - 760, 42, 0.41, Neptune);

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});

		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		animate(bf.getGraphics());
		g.drawImage(bf, 0, 0, getWidth(), getHeight(), null);
		repaint();
	}

	public void animate(Graphics g) {
		super.paint(g);
		sun.draw(g);
		for (int i = 0; i < planets.length; i++) {
			int r = planets[i].getR();
			g.drawOval(WIDTH / 2 - r, HEIGHT / 2 - r, r * 2, r * 2);
			if (planets[i] != null) {
				planets[i].draw(g);
				planets[i].move(counter);
				counter++;
			}
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.PLAIN, 40));
		g.drawString((int) (counter / 8) + " days passed", 50, 50);
		if (counter % 365 == 0) {
			years++;
		}
		g.drawString(years + " years passed on earth", 50, 150);
	}
}
