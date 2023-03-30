package net.tfobz.tictactoeserver;

public class TicTacToe extends java.lang.Object {

	static final int PLAYER1 = -1;
	static final int PLAYER2 = -2;
	private static int SIZE = 3;
	private int[][] field;

	/**
	 * erstellt das Spielfeld mit der übergebenen größe
	 * 
	 * @param size groesse des Feldes
	 */
	public TicTacToe(int size) {
		int count = 0;
		SIZE = size;
		field = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				field[i][j] = count;
				count++;
			}
		}
	}

	/**
	 * Giebt Anzahl Buttons zurueck
	 * 
	 * @return die Anzahl der Felder im Spielfeld
	 */
	public int getFieldSize() {
		return SIZE * SIZE - 1;
	}

	/**
	 * 
	 * @return das Spielfeld als String mit "\n" nach jeder Zeile
	 */
	@Override
	public java.lang.String toString() {
		String ret = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (field[i][j] >= 0) {
					ret += field[i][j] + " ";
				} else if (field[i][j] == -1) {
					ret += "X ";
				} else
					ret += "O ";
			}
			ret += "\n";
		}
		return ret;
	}

	/**
	 * Methode zum Setzen fuer den 1. Spieler
	 * 
	 * @param turn das Feld in das der Zug gesetzt werden soll
	 * @return -1 falls ein nicht gueltiges Feld übergeben wurde
	 * @return -2 falls das Feld schon gesetzt wurde
	 */
	public int setTurnP1(int turn) {
		if (turn > (SIZE * SIZE) - 1 || turn < 0) {
			return -1;
		} else {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (field[i][j] == turn) {
						field[i][j] = -1;
						return 0;
					}
				}
			}
			return -2;
		}
	}

	/**
	 * Setzt den Zug vom Spieler -2
	 * 
	 * @param turn Zug
	 * @return y
	 */
	public int setTurnP2(int turn) {
		if (turn > (SIZE * SIZE) - 1 || turn < 0) {
			return -1;
		} else {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (field[i][j] == turn) {
						field[i][j] = -2;
						return 0;
					}
				}
			}
			return -2;
		}
	}

	/**
	 * Setzt den uebergebenen Zug ins Spielfeld
	 * 
	 * @param turn   Zug
	 * @param player Spieler
	 * @return 0 erflogreich
	 */
	public int setTurn(int turn, int player) {
		if (turn > (SIZE * SIZE) - 1 || turn < 0 || player < -2 || player > -1) {
			return -1;
		} else {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (field[i][j] == turn) {
						field[i][j] = player;
						return 0;
					}
				}
			}
			return -2;
		}
	}

	/**
	 * Kontrolliert ob das Spiel noch winnbar ist
	 * 
	 * @return true - winnbar
	 */
	public boolean isWinnable() {
		String row = "";
		String col = "";
		String diag1 = "";
		String diag2 = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				row += field[i][j];
				col += field[j][i];
				diag1 += field[j][j];
				if (i + j == SIZE - 1) {
					diag2 += field[i][j];
				}
			}
			if ((!(row.contains("-2") && row.contains("-1")) || (!(col.contains("-2") && col.contains("-1"))))) {
				return true;
			}
			row = "";
			col = "";
		}
		if (!((diag1.contains("-2") && diag1.contains("-1")) || (diag2.contains("-2") && diag2.contains("-1")))) {
			return true;
		}
		return false;
	}

	/**
	 * ermittelt den Gewinner
	 * 
	 * @return -1 Player,-2 Player 2, 0 kein Gewinner
	 */
	public int getWinner() {
		// schleife fuer Spieler 1
		int sumH = 0;
		int sumV = 0;
		int sumD1 = 0;
		int sumD2 = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (field[i][j] == -1) {
					sumH++;
					;
				}
			}
			for (int j = 0; j < SIZE; j++) {
				if (field[j][i] == -1) {
					sumV++;
				}
			}
			if (field[i][i] == -1) {
				sumD1++;
			}

			for (int j = 0; j < SIZE; j++) {
				if (i + j == SIZE - 1 && field[i][j] == -1) {
					sumD2++;
				}
			}
			if (sumH == SIZE || sumV == SIZE || sumD1 == SIZE || sumD2 == SIZE) {
				return -1;
			}
			sumH = 0;
			sumV = 0;
		}

		// schleife fuer Spieler 2
		sumH = 0;
		sumV = 0;
		sumD1 = 0;
		sumD2 = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (field[i][j] == -2) {
					sumH++;
				}
			}
			for (int j = 0; j < SIZE; j++) {
				if (field[j][i] == -2) {
					sumV++;
				}
			}
			if (field[i][i] == -2) {
				sumD1++;
			}

			for (int j = 0; j < SIZE; j++) {
				if (i + j == SIZE - 1 && field[i][j] == -2) {
					sumD2++;
				}
			}
			if (sumH == SIZE || sumV == SIZE || sumD1 == SIZE || sumD2 == SIZE) {
				return -2;
			}
			sumH = 0;
			sumV = 0;
		}
		// keiner gewonnen
		return 0;
	}

	/**
	 * Gibt aktuellen Spieler zurueck
	 * 
	 * @return Spieler
	 */
	public int getPlayer() {
		int countX = 0;
		int countY = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (field[i][j] == -1) {
					countX++;
				} else if (field[i][j] == -2) {
					countY++;
				}
			}
		}
		if (countX == countY) {
			return PLAYER1;
		} else if (countX > countY) {
			return PLAYER2;
		} else {
			return PLAYER1;
		}
	}

	/**
	 * Giebt das Spielfeld zurueck
	 * 
	 * @param row Reihe
	 * @param col Zeile
	 * @return -3 ausserhalb des Spielfeld, 0 Spielfeld gesetzt
	 */
	public int getField(int row, int col) {
		if (row > SIZE || col > SIZE) {
			return -3;
		} else if (field[row][col] >= 0) {
			return 0;
		} else
			return field[row][col];
	}

	public int getSize() {
		return SIZE;
	}

}
