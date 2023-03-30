package net.tfobz.algorithms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

public class BubbleSort extends JFrame {

	private static final long serialVersionUID = 1L;
	private int[] data;
	private BufferedImage bf;
	private static int num;
	private int multiplicator;

	public BubbleSort(int size) {
		setSize(1400, 800);
		multiplicator = 1400 / size;
		setSize(size * multiplicator, 800);
		setTitle("Bubble Sort");
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
		isSorted(data, getGraphics());
		try {
			Thread.sleep(2700);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setVisible(false);
	}

	/**
	 * Sortiert die Array
	 * 
	 * @param g Graphics
	 */
	public void sort(Graphics g) {
		int n = data.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				paint(g);
				num = j;
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
	}
	
	/**
	 * Check ob die Arrays Sortiert ist
	 * 
	 * @param array Array
	 * @param g     Graphics
	 * @return true falls sie sortiert ist
	 */
	private boolean isSorted(int[] array, Graphics g) {
		for (int i = 1; i < array.length; i++) {
			num = i;
			paint(g);
			if (array[i] < array[i - 1]) {
				return false;
			}
		}
		num = -1;
		paint(g);
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
