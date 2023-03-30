package net.tfobz.clock;

import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class JDigitalClock extends JLabel implements Runnable {

	private static final long serialVersionUID = 1L;
	private boolean stopped = false;
	DateTimeFormatter dtf;
	LocalDateTime now;

	public JDigitalClock() {
		dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		now = LocalDateTime.now();
		setText(dtf.format(now));
		setFont(new Font("Sans Serif", Font.PLAIN, 70));
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	@Override
	public void run() {
		while (true) {
			do {
				sleep();
			} while (getStopped());
			now = LocalDateTime.now();
			setText(dtf.format(now));
		}

	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public boolean getStopped() {
		return this.stopped;
	}

	public void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
