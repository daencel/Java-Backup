package net.tfobz.digitalclock;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class DigitalClockLabel extends JLabel implements Runnable {

	private static final long serialVersionUID = 1L;
	private boolean stopped = false;

	@Override
	public void run() {
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Tahoma", Font.PLAIN, 120));
		setVisible(true);
		while (true) {
			if (!stopped) {
				setText(java.time.LocalTime.now().toString().substring(0, 8));
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the stopped
	 */
	public boolean isStopped() {
		return stopped;
	}

	/**
	 * @param stopped the stopped to set
	 */
	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
}
