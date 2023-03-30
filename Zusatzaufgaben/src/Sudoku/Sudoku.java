package Sudoku;

public class Sudoku {
	
	public static int [][]GRID_TO_SOLVE = {
			{0,3,0,0,0,0,0,0,0},
			{0,0,0,1,9,5,0,0,0},
			{0,0,8,0,0,0,0,6,0},
			{8,0,0,0,6,0,0,0,0},
			{4,0,0,8,0,0,0,0,1},
			{0,0,0,0,2,0,0,0,0},
			{0,6,0,0,0,0,2,8,0},
			{0,0,0,4,1,9,0,0,5},
			{0,0,0,0,0,0,0,7,0}
	};
	
	private int[][]board;
	//leeres Feld
	private static final int EMPTY = 0;
	//Sudoku Groesse
	private static final int SIZE = 9;
	
	public Sudoku(int[][] board) {
		this.board = new int [SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param number
	 * @return
	 */
	private boolean isInRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[row][i] == number)
				return true;
		
		return false;
	}
	
	/**
	 * 
	 * @param col
	 * @param number
	 * @return
	 */
	private boolean isInCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[i][col] == number)
				return true;
				
		return false;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param number
	 * @return
	 */
	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3;j++)
				if (board[i][j] == number)
					return true;
		return false;
	}
	
	/**
	 * Kontrolliert ob eine Nummer in einer Spalte, Zeile und Box passt
	 * @param col Spalte
	 * @param row Zeile
	 * @param number Nummer die zu checken ist
	 * @return true wenn die Nummer passt
	 */
	private boolean isOK(int row, int col, int number) {
		return !isInRow(row, number) && !isInCol(col, number) &&  !isInBox(row, col, number);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean loesen() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (board[row][col] == EMPTY) {
					//Alle moeglichen nummber durchprobieren
					for (int number = 1; number <= SIZE; number++) {
						if (isOK(row, col, number)) {
							board[row][col] = number;
							//Rekursiv
							if (loesen()) {
								return true;
							}
							//Loesung nicht gefunden
							else {
								board[row][col] = EMPTY;
							}
						}
					}
					return false;
				}
			}
		}
		//Sudoku geloest
		return true;
	}
	
	/**
	 * Gibt das Sudoku aus
	 */
	public void ausgeben() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(GRID_TO_SOLVE);
		System.out.println("Dieses Sudoku wird geloesst werden:");
		sudoku.ausgeben();
		if (sudoku.loesen()) {
			System.out.println("Geloesst:");
			sudoku.ausgeben();
		}
		else {
			System.out.println("Sudoku unloessbar");
		}
	}
}
