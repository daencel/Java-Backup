package net.tfobz.tictactoegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TTTGUI extends JFrame
{

	private JPanel buttonPanel;
	private JPanel framePanel;
	private JPanel statusBar;
	private JLabel status = new JLabel("Tic Tac Toe ", SwingConstants.CENTER);
	private JButton[][] buttons;
	TicTacToe ttt;

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor des Tic Tac Toe Feldes
	 * @param size groesse
	 */
	public TTTGUI(int size) {
		framePanel = new JPanel();
		framePanel.setLayout(new BorderLayout());
		status.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		createStatusBar();
		framePanel.add(statusBar, BorderLayout.SOUTH);
		ttt = new TicTacToe(size);
		buttons = new JButton[size][size];
		new JFrame("");
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setLocationRelativeTo(null);
		setSize(250 * size, 250 * size);
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setLayout(new GridLayout(size, size, 5, 5));
		framePanel.add(buttonPanel, BorderLayout.CENTER);
		setContentPane(framePanel);
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
							int index = getFieldnumber(e,size);
							if (index >= 0) {
								if (ttt.getPlayer() == -1) {
									ttt.setTurnP1(index);
									((JButton) e.getSource()).setText("X");
								} else {
									ttt.setTurnP2(index);
									((JButton) e.getSource()).setText("O");
								}
							}
							if (ttt.getWinner() == -1) {
								status.setText("Player 1 is the winner!");
							} else if (ttt.getWinner() == -2) {
								status.setText("Player 2 is the winner!");
							}
						} else if (ttt.getWinner() == 0) {
							status.setText("No winner");
						}
					}
				});
			}
		}
		setVisible(true);
	}

	/**
	 * Giebt den Index von dem gedrueckten Feld zurueck
	 * @param e Action Event
	 * @param size groesse des Feldes
	 * @return Index  (-2 Fehler)
	 */
	public int getFieldnumber(ActionEvent e,int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (buttons[i][j].equals(e.getSource())) {
					if (ttt.getField(i, j) >= 0) {
						return (i * size + j);
					}
				}
			}
		}
		return -2;
	}

	/**
	 * Erstellt eine Status Bar im JFrame
	 */
	public void createStatusBar() {
		statusBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		statusBar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(10, 10, 10, 10)));
		status.setHorizontalAlignment(SwingConstants.CENTER);
		statusBar.add(status);
	}

	/**
	 * Setzt den Status Bar Text
	 * @param stat Text
	 */
	public void setStatus(String s) {
		status.setText(s);
	}

	public static void main(String[] args) {
		new TTTGUI(3);
	}

}
