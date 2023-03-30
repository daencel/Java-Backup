package moon_lander;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MoonLander extends JFrame {

	private static final long serialVersionUID = 1L;
	final static private int WIDTH = 900;
	final static private int HEIGHT = 900;
	private static boolean gameOver = false;
	private static int acceleration = -9;
	private static int x = WIDTH/2;
	private static int y = HEIGHT/2;
	private static boolean isPressed = false;
	private ImageIcon a1 = new ImageIcon("C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\MoonLander\\MoonLander.png");
	private Image Lander = a1.getImage();
	private Image i;
	private Graphics doubleG;
	
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new MoonLander();
		frame.setTitle("Moonlander");
		frame.setSize(HEIGHT,WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);
		frame.repaint();
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("test");
				isPressed = true;
				int key = e.getKeyCode();
	            if (key == KeyEvent.VK_UP) {
	            	if (acceleration < 9) {
	            		acceleration++;
	            	}
	            	y -= acceleration;
	            }
				frame.repaint();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				isPressed = false;
				frame.repaint();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
	            if (key == KeyEvent.VK_UP) {
	            	if (y < HEIGHT) {
		                if (acceleration < 9) {
		                	acceleration++;
		                }
		                y-= acceleration;
		                frame.repaint();
	            	}
	            }
	            if (key == KeyEvent.VK_RIGHT) {
	            	if (x <= WIDTH) {
	            		x += 5;
	            		frame.repaint();
	            	}
	            }
	            if (key == KeyEvent.VK_LEFT) {
	            	if (x >= 0) {
	            		x -= 5;
	            		frame.repaint();;
	            	}
	            }
			}
		});
		
		while (!isPressed && !gameOver) {
			y-= acceleration;
			if (acceleration > -9) {
				acceleration--;
			}
			bremse(90);
			if (y <= 0) {
				gameOver = true;
			}
			frame.repaint();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageObserver observer = null;
		g.drawImage(Lander, x, y, 30, 30, Color.BLACK, observer);
		if (gameOver) {
			System.out.println("Game Over");
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 80));
			g.drawString("Game Over", WIDTH, HEIGHT);
			bremse(4000);
			System.exit(0);
		}
	}
	
	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		doubleG.setColor(getForeground());
		
		paint(doubleG);
		
		g.drawImage(i,0,0,this);
		
	}
	
	/**
	 * Veranlasst dass das Programm millis Millisekunden pausiert
	 * @param millis Anzahl der Millisekunden die zu warten sind
	 */
	public static void bremse(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
