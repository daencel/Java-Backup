package net.tfobz.minmaxfinder;

import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class FindAvg extends Thread {

	private int[] field;
	private JTextField txt = null;
	private JProgressBar spb = null;

	public FindAvg(int[] sfield, JTextField stxt, JProgressBar pb) {
		field = sfield;
		txt = stxt;
		spb = pb;
		this.start();
	}

	@Override
	public void run() {
		long sum = 0;
		int temp = 0;
		for (int i = 0; i < field.length; i++) {
			sum += field[i];
			if (i % (field.length / 100) == 0) {
				spb.setValue(temp++);
			}
		}
		spb.setValue(temp++);
		double avg = sum / field.length;
		txt.setText((int) avg + "");
	}
}