package net.tfobz.algorithms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

public class BogoSort extends JFrame {

	private static final Random generator = new Random();
	private static final long serialVersionUID = 1L;
	private int[] data;
	private BufferedImage bf;
	public static int num;
	public int multiplicator;

	/**
	 * Initialiesiert Array und fuellt diese
	 * 
	 * @param size Groesse der Array
	 */
	public BogoSort(int size) {
		setSize(1400, 800);
		multiplicator = 1400 / size;
		setSize(size * multiplicator + 10, 800);
		setTitle("Insertion Sort");
		setResizable(false);
		setLocationRelativeTo(null);
		bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		data = new int[size];
		Random rand = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = rand.nextInt(770);
		}
		setVisible(true);
		sort(getGraphics());
		num = 0;
		setVisible(true);
		try {
			Thread.sleep(2700);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setVisible(false);
	}

	/**
	 * Sortiert die Array
	 * @param g Graphics g
	 */
	public void sort(Graphics g) {
		while (!isSorted(data)) {
			for (int i = 0; i < data.length; i++) {
				int randomPosition = generator.nextInt(data.length);
				int temp = data[i];
				data[i] = data[randomPosition];
				data[randomPosition] = temp;
				num = randomPosition;
				paint(g);
			}
		}
	}

	/**
	 * Check ob die Arrays Sortiert ist
	 * 
	 * @param array Array
	 * @return true falls sie sortiert ist
	 */
	private boolean isSorted(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Zeichnet die Array ins Buffered Image
	 * 
	 * @param g Graphics Buffered Image
	 */
	public void draw(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		for (int i = 0; i < data.length; i++) {
			if (i == num) {
				g.setColor(Color.RED);
			}
			g.fillRect(i * multiplicator, 800 - data[i], multiplicator, data[i]);
			g.setColor(Color.black);
		}
	}

	@Override
	public void paint(Graphics g) {
		draw(bf.getGraphics());
		g.drawImage(bf, 0, 0, null);
	}
}
