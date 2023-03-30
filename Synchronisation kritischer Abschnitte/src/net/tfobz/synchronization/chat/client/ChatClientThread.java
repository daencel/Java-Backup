package net.tfobz.synchronization.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

public class ChatClientThread extends Thread {
	private BufferedReader in = null;

	public ChatClientThread(BufferedReader in) {
		this.in = in;
	}

	@Override
	public void run() {
		try {
			while (true) {
				synchronized (in) {
					String line = in.readLine();
					System.out.println(line);
				}
			}
		} catch (SocketException e) {
		} catch (IOException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
