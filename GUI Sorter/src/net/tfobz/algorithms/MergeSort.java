package net.tfobz.algorithms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

public class MergeSort extends JFrame {

	private static final long serialVersionUID = 1L;
	private int[] data;
	private BufferedImage bf;
	private static int num;
	private int multiplicator;

	/**
	 * Initialiesiert Array und fuellt diese
	 * 
	 * @param size Groesse der Array
	 */
	public MergeSort(int size) {
		setSize(1400, 800);
		multiplicator = 1400 / size;
		setSize(size * multiplicator, 800);
		setTitle("Merge Sort");
		setResizable(false);
		setLocationRelativeTo(null);
		bf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		data = new int[size];
		Random rand = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = rand.nextInt(770);
		}
		setVisible(true);
		mergeSort(data, data.length, getGraphics());
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
	 * Sortiert die Arrays mit Merge Sort
	 * 
	 * @param a Array
	 * @param n Laenge Array
	 * @param g Graphics zum zeichnen
	 */
	public void mergeSort(int[] a, int n, Graphics g) {
		if (n < 2) {
			return;
		}
		int mid = n / 2;
		int[] l = new int[mid];
		int[] r = new int[n - mid];

		for (int i = 0; i < mid; i++) {
			l[i] = a[i];
		}
		for (int i = mid; i < n; i++) {
			r[i - mid] = a[i];
		}
		mergeSort(l, mid, g);
		for (int i = 0; i < l.length; i++)
			a[i] = l[i];

		mergeSort(r, n - mid, g);
		for (int i = 0; i < r.length; i++)
			a[mid + i] = r[i];

		merge(a, l, r, mid, n - mid, g);
	}

	/**
	 * Verbindet die beiden Arrays
	 * 
	 * @param a
	 * @param l
	 * @param r
	 * @param left
	 * @param right
	 */
	public void merge(int[] a, int[] l, int[] r, int left, int right, Graphics g) {
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			if (l[i] <= r[j]) {
				a[k++] = l[i++];
			} else {
				a[k++] = r[j++];
			}
			paint(g);
		}
		while (i < left) {
			a[k++] = l[i++];
			paint(g);
		}
		while (j < right) {
			a[k++] = r[j++];
			paint(g);
		}
	}

	/**
	 * Sortiert die Array
	 */
	public void sort(Graphics g) {

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
