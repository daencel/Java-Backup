package net.tfobz.minmaxfinder;

import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class FindMax extends Thread {

	private int[] field;
	private JTextField txt = null;
	private JProgressBar spb = null;

	public FindMax(int[] sfield, JTextField stxt, JProgressBar pb) {
		field = sfield;
		txt = stxt;
		spb = pb;
		this.start();
	}

	@Override
	public void run() {
		double max = 0;
		int temp = 0;
		for (int i = 0; i < field.length; i++) {
			if (field[i] > max)
				max = field[i];
			if (i % (field.length / 100) == 0) {
				spb.setValue(temp++);
			}
		}
		spb.setValue(temp++);
		txt.setText((int) max + "");
	}

}
