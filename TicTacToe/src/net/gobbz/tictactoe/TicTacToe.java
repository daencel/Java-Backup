package net.gobbz.tictactoe;

public class TicTacToe extends java.lang.Object {

	// Nummer des ersten Spielers
	static final int PLAYER1 = -1;
	// Nummer des ersten Spielers
	static final int PLAYER = -2;
	// Groesse des Spielfeldes
	private int SIZE;
	// Das Spielfeld
	private int[][] field;

	public static void main(String[] args) {
		new TicTacToe(3);
	}

	/**
	 * Konstruktor initialisiert das Spielfeld mit Zahlen beginnend bei 0. Ist die
	 * Feldgröße kleiner als 3 dann wird diese auf 3 gesetzt und ein entsprechendes
	 * Spielfeld angelegt. Ist beispielsweise die Feldgröße auf 3 eingestellt, so
	 * wird das Spielfeld folgendermaßen initialisiert:
	 * 
	 * @param size Groesse des Spielfeldes
	 */
	public TicTacToe(int size) {
		SIZE = size;
		int count = 0;
		field = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				field[i][j] = count;
				count++;
			}
		}
	}

	/**
	 * Liefert die Feldgröße des Spielfeldes zurück
	 * 
	 * @return Feldgröße des Spielfeldes
	 */
	public int getFieldSize() {
		return SIZE * SIZE - 1;
	}

	/**
	 * Zeilenweise Ausgabe des Spielfeldes. Dabei werden an den gesetzten Postionen
	 * nicht die Spielernummern ausgegeben sondern für den ersten Spieler ein X und
	 * für den Zweiten ein O.
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
	 * Setzt den Zug des Spielers 1
	 * 
	 * @param turn Zug
	 * @return 0 falls Zug erfolgreich gesetzt werden konnte -1 falls der Zug
	 *         außerhalb des Spielfeldes liegt -2 falls der Zug bereits gesetzt
	 *         wurde
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
	 * Setzt den Zug des Spielers 2
	 * 
	 * @param turn Zug
	 * @return 0 falls Zug erfolgreich gesetzt werden konnte -1 falls der Zug
	 *         außerhalb des Spielfeldes liegt -2 falls der Zug bereits gesetzt
	 *         wurde
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
	 * Setzt den übergebenen Zug im Spielfeld für den Spieler dessen Nummer
	 * ebenfalls übergeben wurde
	 * 
	 * @param turn   Zug
	 * @param player Spielnummer
	 * @return 0 falls Zug erfolgreich gesetzt werden konnte -1 falls der Zug
	 *         außerhalb des Spielfeldes liegt -2 falls der Zug bereits gesetzt
	 *         wurde
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
	 * Ermittelt die Nummer des Spielers der gewonnen hat
	 * 
	 * @return0 0 derzeit hat noch niemand gewonnen oder SPIELER1 oder SPIELER2
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
	 * Ermittelt wie das Spielfeld an der Stelle zeile/spalte gesetzt ist
	 * 
	 * @param row Zeile des Spielfeldes an der nachgeschaut werden soll
	 * @param col Reihe des Spielfeldes an der nachgeschaut werden soll
	 * @return 0 falls an der übergebenen Position noch kein Spieler gesetzt hat
	 *         SPIELER1 falls an der übergebenen Position der erste Spieler gesetzt
	 *         hat SPIELER2 falls an der übergebenen Position der zweite Spieler
	 *         gesetzt hat -3 falls zeile und/oder spalte außerhalb des Spielfeldes
	 *         zugreifen wollen
	 */
	public int getField(int row, int col) {
		if (row > SIZE || col > SIZE) {
			return -3;
		} else if (field[row][col] >= 0) {
			return 0;
		} else
			return field[row][col];
	}

	/**
	 * Ermittelt ob einer der Spieler das Spiel noch gewinnen kann
	 * 
	 * @return true wenn man es gewinnen kann
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
				diag1 += field[i][i];
				if (j + i == SIZE - 1) {
					diag2 += field[i][j];
				}
			}
			if (!((row.contains("-1") && row.contains("-2")) || (col.contains("-2") && col.contains("-1")))) {
				return true;
			}
			row = "";
			col = "";
		}
		if (!((diag1.contains("-1") && diag1.contains("-2")) || (diag2.contains("-2") && diag2.contains("-1")))) {
			return true;
		}
		return false;
	}
}
