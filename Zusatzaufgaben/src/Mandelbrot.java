import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
 
public class Mandelbrot extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int MAX_ITER = 255;
    private static int HEIGHT = 1400;
    private static int WIDTH = 1900;
    private static double zoom = 600;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;
    private static int YOffest = 0;
    private static int XOffset = 0;
 
    public Mandelbrot() {
    	setBounds(50, 50, WIDTH, HEIGHT);
    	setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }
 
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	I = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < HEIGHT; y+= 3) {
            for (int x = 0; x < WIDTH; x+= 3) {
                zx = zy = 0;
                cX = (x - WIDTH/2 + XOffset) / zoom;
                cY = (y - HEIGHT/2 + YOffest) / zoom;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << 8));
            }
        }
        g.drawImage(I, 0, 0, this);
    }
 
    public static void main(String[] args) {
        Mandelbrot a = new Mandelbrot();
        
        a.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == '+') {
					zoom += 1000;
					XOffset += 140;
				}
				if (e.getKeyChar() == '-' && zoom > 1000) {
					zoom -= 1000;
					XOffset -= 140;
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					YOffest -= 400;
				}
				if (e.getKeyCode() ==  KeyEvent.VK_DOWN) {
					YOffest += 400;
				}
				if (e.getKeyCode() ==  KeyEvent.VK_RIGHT) {
					XOffset += 400;
				}
				if (e.getKeyCode() ==  KeyEvent.VK_LEFT) {
					XOffset -= 400;
				}
				a.repaint();
			}
		});
        a.setVisible(true);
    }
}