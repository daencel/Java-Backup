package net.tfobz.tunnel.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * In dieser Konsolenanwendung wird zuerst ein VisitorsMonitor angelegt, und
 * dann wartet das Programm in einer Endlosschleife auf Clientanfragen. Erreicht
 * ihm eine solche, so wird diese in einem Thread vom Typ ServerThread
 * abgearbeitet. Dadurch dass jede Anfrage in einem eigenen Thread abgearbeitet
 * wird, können mehrere Anfragen gleichzeitig bearbeitet werden
 */
public class ServerMain {
	/**
	 * Port an welchem der Server arbeitet
	 */
	protected static final int PORT = 1234;

	/**
	 * Besuchermonitor wird angelegt, und in einer Endlosschleife wird auf
	 * Clientanfragen gewartet, welche alle über einzelne ServerThreads abgearbeitet
	 * werden. Dadurch dass jede Anfrage in einem eigenen Thread abgearbeitet wird,
	 * können mehrere Anfragen gleichzeitig bearbeitet werden
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		VisitorsMonitor visitorsMonitor = new VisitorsMonitor();
		System.out.println("S  E  R  V  E  R\n=================\n" + visitorsMonitor.getAvailableVisitors()
				+ " available Visitors");
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			behandleException(e);
		}
		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				new ServerThread(clientSocket, visitorsMonitor);
			} catch (IOException e1) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					behandleException(e);
				}
				behandleException(e1);
			}
		}
	}

	public ServerMain() {
		VisitorsMonitor visitorsMonitor = new VisitorsMonitor();
		System.out.println("S  E  R  V  E  R\n=================\n" + visitorsMonitor.getAvailableVisitors()
				+ " available Visitors");
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			behandleException(e);
		}
		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				new ServerThread(clientSocket, visitorsMonitor);
			} catch (IOException e1) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					behandleException(e);
				}
				behandleException(e1);
			}
		}
	}

	/**
	 * Methode zur Exceptionbehandlung
	 * 
	 * @param e Exception
	 */
	public static void behandleException(Exception e) {
		e.printStackTrace();
		System.out.println(e.getClass().getName() + ": " + e.getMessage());
	}
}
