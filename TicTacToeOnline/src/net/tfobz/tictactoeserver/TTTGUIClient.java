package net.tfobz.tictactoeserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TTTGUIClient extends JFrame {

	private static final int SIZE = 3;
	private java.net.Socket client = null;
//	 4. Stock
//	 private static java.lang.String ipAdresse = "10.216.149.114";
//	 1. Stock
	private static java.lang.String ipAdresse = "10.216.122.108";
	private JPanel buttonPanel;
	private JPanel framePanel;
	private JPanel statusBar;
	private JLabel status = new JLabel(" ", SwingConstants.CENTER);
	private JButton[][] buttons;
	private Timer timer;
	private static int PORT = 2222;
	private static TicTacToe ttt;
	private static final long serialVersionUID = 1L;

	public TTTGUIClient(int size) {

		framePanel = new JPanel();
		framePanel.setLayout(new BorderLayout());
		status.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		createStatusBar();
		framePanel.add(statusBar, BorderLayout.SOUTH);
		ttt = new TicTacToe(size);
		buttons = new JButton[size][size];

		setResizable(false);
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200 * size, 200 * size);
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setLayout(new GridLayout(size, size, 5, 5));
		framePanel.add(buttonPanel, BorderLayout.CENTER);
		setContentPane(framePanel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					client.close();
				} catch (Exception e) {
					;
				}
			}
		});

		try {
			client = new Socket(ipAdresse, PORT);
			makeButtons(size);

			timer = new Timer(500, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (isGegnerZug(client) && ttt.isWinnable() && ttt.getWinner() >= 0) {
							int temp = -1;
							do {
								temp = getGegnerZug(client);
							} while (temp < 0);
							buttons[temp / ttt.getSize()][temp % ttt.getSize()].setText("O");
							if (ttt.getWinner() == -1) {
								status.setText("Player 1 is the winner!");
								timer.stop();
								disableButtons();
							} else if (ttt.getWinner() == -2) {
								status.setText("Player 2 is the winner!");
								timer.stop();
								disableButtons();
							}
						} else if (ttt.getWinner() < 0) {
							status.setText("No Winner");
							timer.stop();
							disableButtons();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			timer.start();

			setVisible(true);
		} catch (UnknownHostException e) {
			handleException(e);
		} catch (IOException e) {
			handleException(e);
		}

	}

	/**
	 * Erstellt die Buttons und die Action Listeners
	 * 
	 * @param size groesse des Spielfeldes
	 */
	private void makeButtons(int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buttons[i][j] = new JButton("");
				buttons[i][j].setFont(new Font("Comic Sans MS", Font.PLAIN, 70));
				buttonPanel.add(buttons[i][j]);
				buttons[i][j].setBackground(Color.WHITE);
				buttons[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (ttt.isWinnable() && ttt.getWinner() >= 0) {
							int index = getFieldnum(e, size);
							if (index >= 0 && ttt.getPlayer() == -1) {
								try {
									int temp;
									do {
										temp = setMeinZug(index);
									} while (temp < 0);
									((JButton) e.getSource()).setText("X");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							if (ttt.getWinner() == -1) {
								status.setText("Player 1 is the winner!");
								timer.stop();
								disableButtons();
							} else if (ttt.getWinner() == -2) {
								status.setText("Player 2 is the winner!");
								timer.stop();
								disableButtons();
							}
						} else if (ttt.getWinner() < 0) {
							status.setText("No Winner");
							timer.stop();
							disableButtons();
						}
					}
				});
			}
		}
	}

	/**
	 * gibt den index zur�ck von dem Feld indem ein Zug gestzt wurde
	 * 
	 * @param e    Action Event des JButtons
	 * @param size Gr�sse des Tic-Tac_toe Feldes
	 * @return index, falls nicht nicht gefunden: -1
	 */
	public int getFieldnum(ActionEvent e, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (buttons[i][j].equals(e.getSource())) {
					if (ttt.getField(i, j) >= 0) {
						return (i * size + j);
					}
				}
			}
		}
		return -1;
	}

	/**
	 * Erstellt eine Status bar am unterem Rand
	 */
	public void createStatusBar() {
		statusBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		statusBar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(2, 2, 2, 2)));
		status.setHorizontalAlignment(SwingConstants.CENTER);
		statusBar.add(status);
	}

	/**
	 * Setzt einen Text in die Statusbar
	 * 
	 * @param stat Text
	 */
	public void setStatus(String stat) {
		status.setText(stat);
	}

	/**
	 * Erhaelt den Gegner Zug und setzt ihn
	 * 
	 * @param client Socket des Client
	 * @return -1 Auserhalb des Spielfeldes, -2 Zug bereits gesetzt, 0 erflogreich
	 * @throws IOException
	 */
	public static int getGegnerZug(Socket client) throws IOException {
		InputStream in = client.getInputStream();
		int index = in.read();
		if (index > ttt.getFieldSize() || index < 0)
			return -1;
		if (ttt.getField(index / ttt.getSize(), index % ttt.getSize()) < 0)
			return -2;
		ttt.setTurnP2(index);

		return index;
	}

	/**
	 * 
	 * @param client
	 * @return
	 * @throws IOException
	 */
	public static boolean isGegnerZug(Socket client) throws IOException {
		return client.getInputStream().available() > 0;
	}

	/**
	 * Setzt den eigenen Zug und schreibt ihn
	 * 
	 * @param index Zug
	 * @return -1 Auserhalb des Spielfeldes, -2 Zug bereits gesetzt, 0 erflogreich
	 * @throws IOException
	 */
	public int setMeinZug(int index) throws IOException {
		OutputStream out = client.getOutputStream();
		if (index > ttt.getFieldSize() || index < 0)
			return -1;
		if (ttt.getField(index / ttt.getSize(), index % ttt.getSize()) < 0)
			return -2;
		ttt.setTurnP1(index);
		out.write(index);
		return 0;
	}

	/**
	 * Deaktiviert alle Buttons im Spielfeld
	 */
	public void disableButtons() {
		for (int j = 0; j < buttons.length; j++) {
			for (int y = 0; y < buttons[0].length; y++) {
				buttons[j][y].setEnabled(false);
			}
		}
	}

	/**
	 * behandelt die Exception und gibt sie aus
	 * 
	 * @param e exception
	 */
	private static void handleException(Exception e) {
		System.out.println("Exception Objekt: " + e.getClass().getName());
		System.out.println("Fehlermeldung: " + e.getMessage());
	}

	public static void main(String[] args) {
		new TTTGUIClient(SIZE);
	}

}
