package net.tfobz.tictactoesingle;

public class TicTacToe extends java.lang.Object {

	static final int PLAYER1 = -1;
	static final int PLAYER = -2;
	private static int SIZE = 3;
	private int[][] field;

	/**
	 * Erstellt das Spielfeld mit der uebergebenen Groesse
	 * 
	 * @param size größe des Feldes
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
	 * Gibt die Groesse des Spielfeldes zurueck
	 * 
	 * @return die Anzahl der Felder im Spielfeld
	 */
	public int getFieldSize() {
		return SIZE * SIZE - 1;
	}

	/**
	 * Gibt das Spielfeld als String zurueck
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
	 * Methode zum Setzen fuer den Zug des 1. Spieler
	 * 
	 * @param turn das Feld in das der Zug gesetzt werden soll
	 * @return -1 falls ein nicht gueltiges Feld uebergeben wurde
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
	 * Methode zum Setzen fuer den Zug des 2. Spieler
	 * 
	 * @param turn das Feld in das der Zug gesetzt werden soll
	 * @return -1 falls ein nicht gueltiges Feld uebergeben wurde
	 * @return -2 falls das Feld schon gesetzt wurde
	 * @return 0 erfolgreich
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
	 * Setzt den Zuge eines Spielers
	 * 
	 * @param turn   Zug
	 * @param player Spieler, der den Zug gesetzt hat
	 * @return -1 falls ein nicht gueltiges Feld uebergeben wurde
	 * @return -2 falls das Feld schon gesetzt wurde
	 * @return 0 erfolgreich
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
	 * Kontrolliert ob noch einer das Spiel gewinnen kann
	 * 
	 * @return true wenn noch jemand gewinnen kann
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
		// schleife für Spieler 1
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

		// schleife für Spieler 2
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

	public int getField(int row, int col) {
		if (row > SIZE || col > SIZE) {
			return -3;
		} else if (field[row][col] >= 0) {
			return 0;
		} else
			return field[row][col];
	}

}
