package net.tfobz.uebungen;

import javax.swing.JFrame;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Testprogramm {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test - Frame");
		frame .setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}