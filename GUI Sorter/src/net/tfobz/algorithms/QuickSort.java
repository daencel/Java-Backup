package net.tfobz.algorithms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Random;

import javax.swing.JFrame;

public class QuickSort extends JFrame {

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
	public QuickSort(int size) {
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
		sort(data, 0, data.length - 1, getGraphics());
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
	 * @param arr  Array zu sortieren {@link Array}
	 * @param low
	 * @param high
	 * @param g    Grafiken {@link Graphics}
	 */
	public void sort(int arr[], int low, int high, Graphics g) {
		if (low < high) {
			int pi = partition(arr, low, high, g);
			sort(arr, low, pi - 1, g);
			sort(arr, pi + 1, high, g);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			paint(g);
		}
	}

	/**
	 * Partitioniert die Array
	 * 
	 * @param arr  Array {@link Array}
	 * @param low
	 * @param high
	 * @param g    Graphics {@link Graphics}
	 * @return
	 */
	public int partition(int arr[], int low, int high, Graphics g) {
		int pivot = arr[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				num = i;
				paint(g);
			}
		}
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return i + 1;
	}

	/**
	 * Check ob die Arrays Sortiert ist
	 * 
	 * @param array Array {@link Array}
	 * @param g     Graphics {@link Graphics}
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
