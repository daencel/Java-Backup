package net.tfobz.minmaxfinder;

import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class FindMin extends Thread {

	private int[] field;
	private JTextField txt = null;
	private JProgressBar spb = null;

	public FindMin(int[] sfield, JTextField stxt, JProgressBar pb) {
		field = sfield;
		txt = stxt;
		spb = pb;
		this.start();
	}

	@Override
	public void run() {
		int min = Integer.MAX_VALUE;
		int temp = 0;
		for (int i = 0; i < field.length; i++) {
			if (field[i] < min)
				min = field[i];
			if (i % (field.length / 100) == 0) {
				spb.setValue(temp++);
			}
		}
		spb.setValue(temp++);
		txt.setText((int) min + "");
	}
}
