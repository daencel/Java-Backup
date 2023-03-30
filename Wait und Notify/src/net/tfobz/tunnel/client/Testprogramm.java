package net.tfobz.tunnel.client;

import java.awt.EventQueue;

import net.tfobz.tunnel.server.ServerMain;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Testprogramm {

	public static void main(String[] args) {
		new Thread(() -> {
			new ServerMain();
		}).start();;
		
		new Thread(() -> {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ClientForm one = new ClientForm(1);
						one.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}).start();;
		new Thread(() -> {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ClientForm one = new ClientForm(2);
						one.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}).start();;
	}

}
