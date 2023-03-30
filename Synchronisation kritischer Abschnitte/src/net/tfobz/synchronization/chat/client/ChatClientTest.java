package net.tfobz.synchronization.chat.client;

import net.tfobz.synchronization.chat.server.ChatServer;

public class ChatClientTest {

	private static final String ALPHA_NUMERIC_STRING = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ  ").toLowerCase();

	public static void main(String[] args) throws InterruptedException {

		new Thread(() -> {
			new ChatServer();
		}).start();

		String[] strings = new String[3];

		for (int i = 1; i < 50; i++) {
			for (int x = 0; x < 3; x++) {
				strings[x] = randomAlphaNumeric((int) (Math.random() * 10) + 5);
			}
			args[0] = "user" + i;
			args[1] = "127.0.0" + i;
			new Thread(() -> {
				new ChatClient(args, strings);
			}).start();

			Thread.sleep(1000);
		}
		System.exit(0);
	}

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
