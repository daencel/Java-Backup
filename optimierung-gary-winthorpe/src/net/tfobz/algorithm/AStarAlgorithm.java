package net.tfobz.algorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.PriorityQueue;
import javax.swing.JFrame;

/**
 * 
 * @author Daniel Lechner, Noah Aberham
 * 
 *         Diese Klasse berechnet den kuerzesten Weg zum Ziel und zeichnet
 *         diesen auch
 */
public class AStarAlgorithm extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	// Scale zum zeichnen
	private int SCALE;
	// Diagonale Bewegungen
	private boolean diag = false;
	// Open Liste
	private PriorityQueue<Node> queue;
	// Closed Liste
	private ArrayList<Node> closedList;
	// Node Array
	private Node[][] field;
	// Akutelle Node
	private Node current;
	// Koordinaten StartPunkt
	private int xStart, yStart;
	// Koordinaten ZielPunkt
	private int xEnd, yEnd;
	// Buffered Image zum zeichnen
	private BufferedImage img;
	// Insets des JFrame
	private Insets in;

	/**
	 * Konstruktor des A Stern Algorithmus
	 * 
	 * @param arr    Node Array
	 * @param xStart X Start Koordinate
	 * @param yStart Y Start Koordinate
	 * @param xEnd   X End Koordinate
	 * @param yEnd   Y End Koordinate
	 * @param diag   bei true sind diagonale Schritte erlaubt
	 */
	public AStarAlgorithm(Node[][] arr, int xStart, int yStart, int xEnd, int yEnd, boolean diag) {

		setTitle("A Star Alorithm");
		this.SCALE = 600 / arr.length;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(arr.length * SCALE, arr.length * SCALE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		in = getInsets();
		setSize(getWidth() + in.left + in.right, getHeight() + in.top + in.bottom);
		img = new BufferedImage((arr.length * SCALE), (arr.length * SCALE), BufferedImage.TYPE_INT_RGB);

		this.field = arr;
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		this.diag = diag;
		this.queue = new PriorityQueue<Node>();
		this.closedList = new ArrayList<Node>();

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				if (field[i][j] != null)
					if (diag)
						field[i][j].setH(euclidianDist(field[i][j], field[xEnd][yEnd]));
					else
						field[i][j].setH(manhattanDist(field[i][j], field[xEnd][yEnd]));
			}
		}

		this.field[xStart][yStart].setG(0.0);
		this.field[xStart][yStart].setParent(this.field[xStart][yStart]);
		this.queue.add(this.field[xStart][yStart]);
		this.field[xEnd][yEnd].setH(0.0);
	}

	/**
	 * Berechnet die Euklidische Distanz zwischen 2 Nodes
	 * 
	 * @param n1 Node 1
	 * @param n2 Node 2
	 * @return Euklidische Distanz
	 */
	public double euclidianDist(Node n1, Node n2) {
		return Math.hypot((n1.getX() - n2.getX()), (n1.getY() - n2.getY()));
	}

	/**
	 * Berechnet die Mahnatten Distanz zwischen 2 Nodes
	 * 
	 * @param n1 Node 1
	 * @param n2 Node 2
	 * @return Mahnatten Distanz
	 */
	public double manhattanDist(Node n1, Node n2) {
		return Math.abs(n1.getX() - n2.getX()) + Math.abs(n1.getY() - n2.getY());
	}

	/**
	 * Run Methode, welchen anhand des A Stern Algorithmus den kuerzesten Weg zum
	 * Ziel kalkuliert
	 */
	@Override
	public void run() {
		ArrayList<Node> nxt;
		while (!queue.isEmpty()) {
			nxt = new ArrayList<Node>();
			current = queue.poll();
			closedList.add(current);
			if (current == field[xEnd][yEnd]) {
				current.printPath(img.getGraphics(), SCALE);
				drawGrid(img.getGraphics());
				getGraphics().drawImage(img, 0, in.top, null);
				return;
			}
			repaint();
			nxt = getNeighbours(current);
			for (Node n : nxt) {
				if (!(closedList.contains(n))) {
					if (!queue.contains(n)) {
						n.setParent(current);
						n.setG(current.getG() + euclidianDist(n, current));
						queue.add(n);
					} else if (n.getG() > current.getG() + euclidianDist(n, current)) {
						queue.remove(n);
						n.setG(current.getG() + euclidianDist(n, current));
						n.setParent(current);
						queue.add(n);
					}
				}
				bremse(20);
			}
		}
		System.out.println("No possible path found...");
//		System.exit(0);
	}

	/**
	 * Gibt die anliegenden Nachbarn zurueck, unterscheidet zwischen diagonal und
	 * nicht
	 * 
	 * @param n Die Node deren Nachbarn zurueckgegeben werden
	 * @return falls diag true ist werden alle 8 anliegenden Nodes in einer
	 *         ArrayList zurueckgegeben andernfalls werdeb nur die 4 Nodes
	 *         zurueckgegeben die anliegende Kanten haben
	 */
	public ArrayList<Node> getNeighbours(Node n) {
		ArrayList<Node> ret = new ArrayList<Node>();
		if (!diag) {
			if (n.getX() > 0) {
				if (field[n.getX() - 1][n.getY()] != null) {
					ret.add(field[n.getX() - 1][n.getY()]);
				}
			}
			if (n.getX() < field.length - 1) {
				if (field[n.getX() + 1][n.getY()] != null) {
					ret.add(field[n.getX() + 1][n.getY()]);
				}
			}
			if (n.getY() > 0) {
				if (field[n.getX()][n.getY() - 1] != null) {
					ret.add(field[n.getX()][n.getY() - 1]);
				}
			}
			if (n.getY() < field[0].length - 1) {
				if (field[n.getX()][n.getY() + 1] != null) {
					ret.add(field[n.getX()][n.getY() + 1]);
				}
			}
		} else {
			for (int i = n.getX() + 1; i >= n.getX() - 1; i--) {
				for (int j = n.getY() + 1; j >= n.getY() - 1; j--) {
					if (i >= 0 && i < field.length && j >= 0 && j < field[i].length
							&& (field[i][j] != field[xStart][yStart])) {
						if (field[i][j] != null) {
							ret.add(field[i][j]);
						}
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Zeichnet das zuerst bezeichnete BufferedImage auf das JFrame
	 */
	@Override
	public void paint(Graphics g) {
		if (img == null)
			return;
		drawImg(img.getGraphics());
		g.drawImage(img, in.left, in.top, null);
	}

	/**
	 * Zeichnet alle noten, Hindernisse und Nachbarn auf das BufferedImage
	 * 
	 * @param g Graphics des Buffered Image
	 */
	public void drawImg(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (this.field[i][j] != null) {
					if (field[i][j].getX() == xStart && field[i][j].getY() == yStart) {
						g.setColor(Color.GREEN);
					} else if (field[i][j].getX() == xEnd && field[i][j].getY() == yEnd) {
						g.setColor(Color.MAGENTA);
					} else if (field[i][j].equals(current)) {
						g.setColor(Color.CYAN);
					} else if (closedList.contains(field[i][j])) {
						g.setColor(Color.RED);
					} else if (queue.contains(field[i][j])) {
						g.setColor(Color.YELLOW);
					} else
						g.setColor(Color.GRAY);
					g.fillRect(field[i][j].getX() * SCALE, field[i][j].getY() * SCALE, SCALE, SCALE);
				}
			}
		}
		drawGrid(g);
	}

	/**
	 * Zeichnet ein Gitter fuer bessere Visualitaet
	 * 
	 * @param g Graphics g
	 */
	public void drawGrid(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i < img.getWidth() / SCALE; i++) {
			for (int j = 0; j < img.getHeight() / SCALE; j++) {
				g.drawLine(0, j * SCALE, img.getWidth(), j * SCALE);
				g.drawLine(i * SCALE, 0, i * SCALE, img.getHeight());
			}
		}
	}

	/**
	 * Haelt den aktiven Thread fuer eine bestimmte Zeit an
	 * 
	 * @param millis Dauer der Pause
	 */
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}