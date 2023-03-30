package spaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class SpaceInvadersGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static int HEIGHT;
	static int WIDTH;
	private int score = 0;
	private long time = 0;
	private int fps = 0;
	private int actualfps = fps;
	private int diff = 1;
	private boolean right = false;
	private boolean left = false;
	private boolean ingame = false;
	private boolean inoptions = false;
	private boolean ingameover = false;
	private boolean inpaused = false;
	private Vector<Alien> aliens = new Vector<Alien>();
	private Vector<Bullet> b = new Vector<Bullet>();
	private Vector<AlienBullet> alienB = new Vector<AlienBullet>();
	private Ufo ufo;
	private Spaceship s;
	private BufferedImage img_spaceship = null;
	private BufferedImage img_spaceshipded = null;
	private BufferedImage img_bullet = null;
	private BufferedImage img_alien = null;
	private BufferedImage img_start = null;
	private BufferedImage img_logo = null;
	private BufferedImage img_gameOver = null;
	private BufferedImage img_alienbullet = null;
	private BufferedImage img_ufo = null;
	private BufferedImage img_alien2 = null;
	private Clip soundtrack;
	private Clip gameOver;
	private Clip explosion;
	private Clip shot;
	private Clip invaderkilled;
	private Clip spaceshot;
	private Clip ufosound;
	private BufferedImage bf;
	private JTextField text = new JTextField();
	private Vector<Scores> highscore = new Vector<Scores>();

	/**
	 * Konstruktor mit Listeners
	 */
	public SpaceInvadersGUI() {
		super("Space Invaders");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		HEIGHT = getHeight();
		WIDTH = getWidth();
		
		bf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		load();

		try {
			img_logo = ImageIO.read(new File("Images/logo.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (ingame) {
					if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
						left = false;
						right = false;
					}
					if (e.getKeyChar() == ' ') {
						b.add(new Bullet(s.getX() + s.getWIDTH() / 2 - 20, s.getY() - s.getHEIGHT() / 2, img_bullet));
						shot.loop(1);
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (!ingame && !ingameover && !inpaused && !inoptions) {
					if (code == KeyEvent.VK_ENTER) {
						ingame = true;
						repaint();
					} else if (code == KeyEvent.VK_SPACE) {
						option();
					} else if (code == KeyEvent.VK_ESCAPE) {
						save();
						System.exit(0);
					}
				} else if (ingame && !inoptions) {
					if (code == KeyEvent.VK_Q) {
						s.lives = 10;
					}
					if (code == KeyEvent.VK_LEFT) {
						left = true;
					} else if (code == KeyEvent.VK_RIGHT) {
						right = true;
					} else if (code == KeyEvent.VK_ESCAPE) {
						ingame = false;
						inpaused = true;
						paused();
					}
				}
				if (inoptions) {
					if (code == KeyEvent.VK_1) {
						if (soundtrack.isActive()) {
							soundtrack.stop();
						} else {
							soundtrack.loop(Clip.LOOP_CONTINUOUSLY);
						}
					} else if (code == KeyEvent.VK_2) {
						if (diff > 1) {
							diff--;
							option();
						}
					} else if (code == KeyEvent.VK_3) {
						if (diff < 7) {
							diff++;
							option();
						}
					} else if (code == KeyEvent.VK_4 || code == KeyEvent.VK_ENTER) {
						inoptions = false;
						ingame = true;
						repaint();
					} else if (code == KeyEvent.VK_5) {
						save();
						System.exit(0);
					}
				}
				if (inpaused) {
					if (code == KeyEvent.VK_1) {
						if (soundtrack.isActive()) {
							soundtrack.stop();
						} else {
							soundtrack.loop(Clip.LOOP_CONTINUOUSLY);
						}
					} else if (code == KeyEvent.VK_3) {
						if (diff < 7) {
							diff = Math.abs(diff) + 1;
							paused();
						}
					} else if (code == KeyEvent.VK_4 || code == KeyEvent.VK_ENTER) {
						inpaused = false;
						ingame = true;
						repaint();
					} else if (code == KeyEvent.VK_5) {
						save();
						System.exit(0);
					}
				}
				if (ingameover) {
					if (code == KeyEvent.VK_ENTER) {
						try {
							text.getText();
							addScore(text.getText(), score);
						} catch (Exception e2) {
							addScore("unknown", score);
						}
						save();
						s = new Spaceship(getWidth() / 2, getHeight() - 200, img_spaceship);
						for (int j = HEIGHT / 4; j <= HEIGHT / 2; j += HEIGHT / 8) {
							for (int i = HEIGHT / 8; i <= getWidth() - HEIGHT / 8; i += HEIGHT / 8)
								aliens.add(new Alien(i, j, img_alien));
						}
						diff = 1;
						score = 0;
						ingameover = false;
						ingame = true;
						text.setVisible(false);
						repaint();
					}
					if (code == KeyEvent.VK_ESCAPE) {
						save();
						System.exit(0);
					}
				}
			}
		});

		setIconImage(img_logo);

		setup();
	}

	/**
	 * Laed alle Dateien und den Ladescreen
	 */
	public void setup() {
		try {
			File path = new File("Images/Soundtrack.wav");
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
			soundtrack = AudioSystem.getClip();
			soundtrack.open(audioInput);
			path = new File("Images/shoot.wav");
			audioInput = AudioSystem.getAudioInputStream(path);
			shot = AudioSystem.getClip();
			shot.open(audioInput);
			path = new File("Images/ufo.wav");
			audioInput = AudioSystem.getAudioInputStream(path);
			ufosound = AudioSystem.getClip();
			ufosound.open(audioInput);
			path = new File("Images/spaceshipshot.wav");
			audioInput = AudioSystem.getAudioInputStream(path);
			spaceshot = AudioSystem.getClip();
			spaceshot.open(audioInput);
			path = new File("Images/explosion.wav");
			audioInput = AudioSystem.getAudioInputStream(path);
			explosion = AudioSystem.getClip();
			explosion.open(audioInput);
			path = new File("Images/invaderkilled.wav");
			audioInput = AudioSystem.getAudioInputStream(path);
			invaderkilled = AudioSystem.getClip();
			invaderkilled.open(audioInput);
			path = new File("Images/GameOver.wav");
			audioInput = AudioSystem.getAudioInputStream(path);
			gameOver = AudioSystem.getClip();
			gameOver.open(audioInput);
			img_alienbullet = ImageIO.read(new File("Images/alienbullet2.png"));
			img_gameOver = ImageIO.read(new File("Images/gameover.jpg"));
			img_start = ImageIO.read(new File("Images/start.png"));
			img_bullet = ImageIO.read(new File("Images/bullet.png"));
			img_alien = ImageIO.read(new File("Images/alien.png"));
			img_spaceship = ImageIO.read(new File("Images/spaceship.png"));
			img_spaceshipded = ImageIO.read(new File("Images/spaceshipisded.png"));
			img_ufo = ImageIO.read(new File("Images/ufo.png"));
			img_alien2 = ImageIO.read(new File("Images/alien.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		s = new Spaceship(getWidth() / 2, getHeight() - 200, img_spaceship);

		for (int j = HEIGHT / 4; j <= HEIGHT / 2; j += HEIGHT / 8) {
			for (int i = HEIGHT / 8; i <= getWidth() - HEIGHT / 8; i += HEIGHT / 8)
				aliens.add(new Alien(i, j, img_alien));
		}

		Graphics g = getGraphics();

		g.fillRect(0, 0, getWidth(), getHeight());

		g.drawImage(img_logo, 0, 150, getWidth(), getHeight() - 300, null);
		bremse(1500);

		g.fillRect(0, 0, getWidth(), getHeight());
		bremse(300);

		ingame = false;
		soundtrack.loop(Clip.LOOP_CONTINUOUSLY);
		g.drawImage(img_start, 0, 0, getWidth(), getHeight(), null);
	}

	/**
	 * ruft zeichne auf und gibt das Buffered image aus
	 */
	public void paint(Graphics g) {

		g = getGraphics();

		if (ingame) {
			zeichne(bf.getGraphics());
			g.drawImage(bf, 0, 0, null);
			if (ingame) {
				long newtime = new java.util.GregorianCalendar().getTimeInMillis();
				if (newtime - time > 1000) {
					time = new java.util.GregorianCalendar().getTimeInMillis();
					actualfps = fps;
					fps = 0;
				}
				repaint();
			} else {
				bremse(1000);
				gameOver();
			}
		}

	}

	/**
	 * Zeichnet die Spiel - Oberflaeche
	 * 
	 * @param g Grafics des Buffered Image
	 */
	public void zeichne(Graphics g) {

		fps++;

		super.paint(g);

		g.setFont(new Font("Courier New", Font.PLAIN, HEIGHT / 40));
		g.drawString("Score: " + score, (int) (WIDTH / 1.7), (int) (HEIGHT / 22.857));

		g.setFont(new Font("Courier New", Font.PLAIN, HEIGHT / 80));
		g.drawString("FPS: " + actualfps, WIDTH - WIDTH / 17, (int) (HEIGHT / 53.33));

		for (int i = 0; i < s.getLives(); i++) {
			g.drawImage(img_spaceship, WIDTH / 16 * i, (int) (HEIGHT / 53.33), WIDTH / 21, HEIGHT / 18, null);
		}

		boolean touching = false;
		for (int i = 0; i < aliens.size() && !touching; i++) {
			if (aliens.elementAt(i).getX() <= WIDTH / 128 || aliens.elementAt(i).getX() >= WIDTH - WIDTH / 21) {
				touching = true;
				diff *= -1;
			}
		}

		for (int i = 0; i < alienB.size(); i++) {
			if (spaceShot(alienB.elementAt(i))) {
				if (s.getLives() > 1) {
					spaceshot.loop(1);
					score += 10 * Math.abs(diff);
					s.decreaselives();
					alienB.remove(i);
				} else {
					explosion.loop(1);
					s.setImg(img_spaceshipded);
					ingame = false;
					ingameover = true;
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, (int) (WIDTH / 14.222), HEIGHT / 10);
					s.draw(g);
				}
			} else {
				if (alienB.elementAt(i).getY() > HEIGHT) {
					alienB.remove(i);
				} else {
					alienB.get(i).draw(g);
					alienB.get(i).move(HEIGHT / 320);
				}
			}
		}

		if (ufo != null) {
			ufo.draw(g);
			if (!ufosound.isActive()) {
				ufosound.loop(1);
			}
			if (shotUfo()) {
				score += ((int) (Math.random() * 50)) * 10;
				ufo = null;
			} else {
				ufo.move(HEIGHT / 400);
				if (ufo.getX() > WIDTH) {
					ufo = null;
				}
			}
		}

		for (int i = 0; i < aliens.size(); i++) {
			aliens.elementAt(i).move(diff);
			if (isBottom(aliens.elementAt(i)) && Math.random() < 0.004 * (Math.abs(diff) * 0.3)) {
				alienB.add(new AlienBullet(aliens.get(i).getX(), aliens.get(i).getY(), img_alienbullet));
			}
			int ret = shot(aliens.elementAt(i), b);
			if (ret >= 0) {
				invaderkilled.loop(1);
				score += Math.abs(diff) * 10;
				aliens.remove(i);
				b.remove(ret);
				if (aliens.size() == 0) {
					for (int j = HEIGHT / 4; j <= HEIGHT / 2; j += HEIGHT / 8) {
						for (int k = HEIGHT / 8; k <= getWidth() - HEIGHT / 8; k += HEIGHT / 8)
							aliens.add(new Alien(k, j, img_alien2));
					}
					ufo = new Ufo((int) (WIDTH / 12.8), HEIGHT / 8, img_ufo);
					if (diff > 0) {
						diff += 1;
					} else {
						diff -= 1;
					}
				}
			} else {
				aliens.elementAt(i).draw(g);
			}
		}

		s.draw(g);

		for (int i = 0; i < b.size(); i++) {
			if (b.elementAt(i).getY() < 0) {
				b.removeElementAt(i);
			} else {
				b.elementAt(i).move(HEIGHT / 160);
				b.elementAt(i).draw(g);
			}
		}

		if (right) {
			if (s.getX() < getWidth() - s.getWIDTH())
				s.move(7);
		}
		if (left) {
			if (s.getX() > 0)
				s.move(-7);
		}
	}

	/**
	 * Kontrolliert, ob ein Alien mit einer Bullet im vecor kolliert ist
	 * 
	 * @param temp Alien
	 * @param b    Bullet Vector
	 * @return -1 bei keiner Kollision ansonsten ID des Bullets
	 */
	public int shot(Alien temp, Vector<Bullet> b) {
		for (int i = 0; i < b.size(); i++) {
			if (b.elementAt(i).getX() + b.elementAt(i).getWIDTH() > temp.getX()
					&& b.elementAt(i).getX() < temp.getX() + temp.getWIDTH()
					&& b.elementAt(i).getY() < temp.getY() + temp.getHEIGHT() && b.elementAt(i).getY() > temp.getY()) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Kontrolliert, ob das Alien mit einen Bullet kollidiert ist
	 * 
	 * @return
	 */
	public boolean shotUfo() {
		for (int i = 0; i < b.size(); i++) {
			if (b.elementAt(i).getX() > ufo.getX() && b.elementAt(i).getX() < ufo.getX() + ufo.getWIDTH()
					&& b.elementAt(i).getY() < ufo.getY() + ufo.getHEIGHT() && b.elementAt(i).getY() > ufo.getY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Kontrolliert, ob das Raumschiff mit den Alienbullet kollidiert ist
	 * 
	 * @param a Alien Bullet
	 * @return true bei Kollision false bei keiner Kollision
	 */
	public boolean spaceShot(AlienBullet a) {
		if (a.getX() + a.getWIDTH() > s.getX() && a.getX() + a.getWIDTH() < s.getX() + s.getWIDTH()
				&& a.getY() > s.getY() && a.getY() < s.getY() + s.getHEIGHT()) {
			return true;
		}
		return false;
	}

	/**
	 * Game Over Oberflaeche
	 */
	public void gameOver() {

		left = false;
		right = false;
		ingame = false;
		ingameover = true;

		alienB.clear();
		aliens.clear();
		b.clear();
		ufo = null;

		try {
			File path = new File("Images/GameOver.wav");
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
			gameOver = AudioSystem.getClip();
			gameOver.open(audioInput);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		gameOver.start();

		Graphics g = getGraphics();

		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.drawImage(img_gameOver, 0, 0, WIDTH, HEIGHT / 3, null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.PLAIN, HEIGHT / 16));
		g.drawString("Your Score is: " + score, (int) (WIDTH / 3.657), (int) (HEIGHT / 2.286));
	
		if (highscore.size() != 0 && score > highscore.elementAt(0).getDigit()) {
			g.setColor(Color.RED);
			g.setFont(new Font("Courier New", Font.PLAIN, HEIGHT / 16));
			g.drawString("Congratulations! You broke the high score!", WIDTH / 51, (int) (HEIGHT / 1.7));
			g.setColor(Color.WHITE);
		}

		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(null);

		text = new JTextField("username");
		text.setBounds((int) (WIDTH / 2.56), (int) (HEIGHT / 1.4545), (int) (WIDTH / 5.12), (int) (HEIGHT / 22.857));
		text.setFont(new Font("Courier New", Font.PLAIN, (int) (HEIGHT / 22.857)));
		text.setVisible(true);

		panel.add(text);

		text.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					text.getText();
					addScore(text.getText(), score);
				} catch (Exception e2) {
					addScore("unknown", score);
				}
				save();
				s = new Spaceship(getWidth() / 2, getHeight() - 200, img_spaceship);
				for (int j = HEIGHT / 4; j <= HEIGHT / 2; j += HEIGHT / 8) {
					for (int i = HEIGHT / 8; i <= getWidth() - HEIGHT / 8; i += HEIGHT / 8)
						aliens.add(new Alien(i, j, img_alien));
				}
				diff = 1;
				score = 0;
				ingameover = false;
				ingame = true;
				text.setVisible(false);
				SpaceInvadersGUI.this.requestFocus();
				repaint();
			}
		});

		g.setFont(new Font("Courier New", Font.PLAIN, HEIGHT / 32));
		g.drawString("Press Enter to save Score", WIDTH / 3, (int) (HEIGHT / 1.2307));

		panel.setVisible(true);
	}

	/**
	 * Oberflaeche Pausen Menue
	 */
	public void paused() {

		inpaused = true;

		Graphics g = getGraphics();

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.PLAIN, (int) (HEIGHT / 10.666)));
		g.drawString("Space Invaders Multigame", (int) (WIDTH / 8.533), (int) (HEIGHT / 5.333));
		g.setFont(new Font("Courier New", Font.PLAIN, HEIGHT / 16));
		g.drawString("Game Pause Menu", (int) (WIDTH / 8.533), (int) (HEIGHT / 3.555));
		g.setFont(new Font("Courier New", Font.PLAIN, (int) (HEIGHT / 22.857)));
		g.drawString("1 - Music ON / OFF", (int) (WIDTH / 6.4), (int) (HEIGHT / 2.461));
		g.setColor(Color.RED);
		g.drawString("2 - Decrease Difficulty: " + Math.abs(diff), (int) (WIDTH / 6.4), (int) (HEIGHT / 2.1313));
		g.setColor(Color.WHITE);
		g.drawString("3 - Increase Difficulty: " + Math.abs(diff), (int) (WIDTH / 6.4), (int) (HEIGHT / 1.882));
		g.drawString("4 - Start Game", (int) (WIDTH / 6.4), (int) (HEIGHT / 1.6842));
		g.drawString("5 - Exit Game", (int) (WIDTH / 6.4), (int) (HEIGHT / 1.5238));
		g.setFont(new Font("Courier New", Font.PLAIN, HEIGHT / 16));
		g.setColor(Color.RED);
		try {
			g.drawString("high score held by : " + highscore.elementAt(0).getUser() + " ("
					+ highscore.elementAt(0).getDigit() + ")", WIDTH / 13, (int) (HEIGHT / 1.28));
		} catch (Exception e) {
			g.drawString("not able to read high score ", (int) (WIDTH / 6.4), (int) (HEIGHT / 1.28));
		}
		g.setColor(Color.BLACK);

	}

	/**
	 * Optionen Oberflaeche
	 */
	public void option() {

		inoptions = true;

		Graphics g = getGraphics();

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.PLAIN, (int) (HEIGHT / 10.666)));
		g.drawString("Space Invaders Multigame", (int) (WIDTH / 8.533), (int) (HEIGHT / 5.333));
		g.setFont(new Font("Courier New", Font.PLAIN, HEIGHT / 16));
		g.drawString("Game Pause Menu", (int) (WIDTH / 8.533), (int) (HEIGHT / 3.555));
		g.setFont(new Font("Courier New", Font.PLAIN, (int) (HEIGHT / 22.857)));
		g.drawString("1 - Music ON / OFF", (int) (WIDTH / 6.4), (int) (HEIGHT / 2.461));
		g.drawString("2 - Decrease Difficulty: " + Math.abs(diff), (int) (WIDTH / 6.4), (int) (HEIGHT / 2.1313));
		g.drawString("3 - Increase Difficulty: " + Math.abs(diff), (int) (WIDTH / 6.4), (int) (HEIGHT / 1.882));
		g.drawString("4 - Start Game", (int) (WIDTH / 6.4), (int) (HEIGHT / 1.6842));
		g.drawString("5 - Exit Game", (int) (WIDTH / 6.4), (int) (HEIGHT / 1.5238));
		g.setFont(new Font("Courier New", Font.PLAIN, HEIGHT / 16));
		g.setColor(Color.RED);
		try {
			g.drawString("High Score held by : " + highscore.elementAt(0).getUser() + " ("
					+ highscore.elementAt(0).getDigit() + ")", (int) (WIDTH / 12.8), (int) (HEIGHT / 1.28));
		} catch (Exception e) {
			g.drawString("not able to read High Score ", (int) (WIDTH / 6.4), (int) (HEIGHT / 1.28));
		}
		g.setColor(Color.BLACK);
	}

	/**
	 * Ladet alle Scores der csv Datei in den Vector
	 */
	public void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Images/highscore.csv"));
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					reader.close();
					break;
				} else {
					highscore.add(new Scores(line));
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Fuegt einen neuen Score hinzu
	 * 
	 * @param user  Benutzer des Scores
	 * @param digit Score
	 */
	public void addScore(String user, int digit) {
		highscore.add(new Scores(user, digit));
	}

	/**
	 * Sortiert und Speichert die Highscores
	 */
	public void save() {
		Scores ret[] = new Scores[highscore.size()];
		for (int i = 0; i < highscore.size(); i++) {
			ret[i] = highscore.elementAt(i);
			int x = i;
			while (x > 0 && ret[x - 1].getDigit() < ret[x].getDigit()) {
				Scores h = ret[x];
				ret[x] = ret[x - 1];
				ret[x - 1] = h;
				--x;
			}
		}
		for (int i = 0; i < highscore.size(); i++) {
			highscore.set(i, ret[i]);
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Images/highscore.csv"));
			for (int k = 0; k < highscore.size() && highscore.elementAt(k) != null; k++) {
				writer.write(highscore.elementAt(k).toString() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Veranlasst dass das Programm millis Millisekunden pausiert
	 * 
	 * @param millis Anzahl der Millisekunden die zu warten sind
	 */
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Kontrolliert, ob sich unter den uebergebenen Alien ein Alien befindet
	 * 
	 * @param a Alien
	 * @return true oder false
	 */
	public boolean isBottom(Alien a) {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.elementAt(i).getY() > a.getY() && aliens.elementAt(i).getX() < a.getX() + 5
					&& aliens.elementAt(i).getX() > a.getX() - 5) {
				return false;
			}
		}
		return true;
	}
}
