package net.tfobz.testprogramm;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * Aufgabe 2
 * 
 * @author Daniel Lechner
 *
 */
public class ActionListenerTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Action Listener Test");
		frame.setSize(400, 200);

		JButton button = new JButton("Press me");
		button.addActionListener(e -> {
			System.out.println(button.getText());
		});

		frame.add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
