package Snake;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final int BREITE = 1300;
    private final int HOEHE = 1300;
    private final int DOT_SIZE = 25;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean links = false;
    private boolean rechts = true;
    private boolean oben = false;
    private boolean unten = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    

    public Board() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(BREITE, HOEHE));
        loadImages();
        initGame();
    }

    /**
     * Die Bilder werden geladen und gesetzt
     */
    private void loadImages() {
        ImageIcon a = new ImageIcon("C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\Snake\\dot.png");
        ball = a.getImage();
        ImageIcon b = new ImageIcon("C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\Snake\\apple.png");
        apple = b.getImage();
        ImageIcon c = new ImageIcon("C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\Snake\\greendot.png");
        head = c.getImage();
    }

    /**
     * Initialisiert das Spiel
     */
    private void initGame() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ausgeben(g);
    }
    
    /**
     * Zeichnet die Snake auf das JFrame
     * @param g
     */
    private void ausgeben(Graphics g) {
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this); 
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }        
    }

    /**
     * Ausgabe falls man verliert
     * @param g
     */
    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (BREITE - metr.stringWidth(msg)) / 2, HOEHE / 2);
    }

    /**
     * Checkt ob die Snake auf den Apfel ist
     */
    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }

    
    /**
     * Bewegt die Snake
     */
    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (links) {
            x[0] -= DOT_SIZE;
        }
        if (rechts) {
            x[0] += DOT_SIZE;
        }
        if (oben) {
            y[0] -= DOT_SIZE;
        }
        if (unten) {
            y[0] += DOT_SIZE;
        }
    }

    /**
     * Kontrolliert ob eine Kollision passiert
     */
    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }
        if (y[0] >= HOEHE) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] >= BREITE) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if(!inGame) {
            timer.stop();
        }
    }

    
    /**
     * Setzt den Apfel auf eine bestimmte x und y Position
     */
    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));
        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    
    /**
     * Liest die Tastatureingabe
     * @author Daniel Lechner
     *
     */
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!rechts)) {
                links = true;
                oben = false;
                unten = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!links)) {
                rechts = true;
                oben = false;
                unten = false;
            }
            if ((key == KeyEvent.VK_UP) && (!unten)) {
                oben = true;
                rechts = false;
                links = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!oben)) {
                unten = true;
                rechts = false;
                links = false;
            }
        }
    }
}