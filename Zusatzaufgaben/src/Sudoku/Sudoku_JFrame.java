package Sudoku;
import java.awt.*;
import javax.swing.*;

public class Sudoku_JFrame extends JPanel  {
	
	private static final long serialVersionUID = 1L;

	//Sudoku - Grid
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
	
	private static int[][]board;
	//leeres Feld
	private static final int EMPTY = 0;
	//Sudoku Groesse
	private static final int SIZE = 9;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int breite = getWidth()/SIZE;
		int hoehe = getHeight()/SIZE;
		g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == 0) {
					g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
				}
				else {
					g.setFont(new Font("TimesRoman", Font.BOLD, 60));
				}
				g.drawString(Integer.toString(board[i][j]), breite*j+60, hoehe*i+60);
			}
		hoehe = getHeight();
		int y = getHeight()/(SIZE/3);
		int y2 = 0;
		for (int i = 1; i < SIZE/3; i++) {
			y2 = y2 + y;
			g.drawLine(0, y2-30, getWidth(), y2-30);
		}
		int x2 = 0;
		int x = getWidth()/(SIZE/3);
		for (int i = 1; i < SIZE/3; i++) {
			x2 = x2 + x;
			g.drawLine(x2, 0, x2, getHeight());
		}	
	}

	public static void main(String[] args) {
		Sudoku_JFrame sudoku = new Sudoku_JFrame(GRID_TO_SOLVE);
		JFrame Frame = new JFrame("Sudoku Solver");
		Frame.setVisible(true);
		Frame.setSize(1400,1400);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.add(new Sudoku_JFrame(board));
		Frame.setResizable(false);
		bremse(2000);
		if (sudoku.loesen()) {
			Frame.repaint();
		}
		else {
			Frame.setVisible(false);
		}
	}
	
	/**
	 * Fuellt das Sudoku
	 * @param board gefuelltes Sudoku
	 */
	public Sudoku_JFrame(int[][] board) {
		Sudoku_JFrame.board = new int [SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Sudoku_JFrame.board[i][j] = board[i][j];
			}
		}
	}
	
	/**
	 * Kontrolliert ob die Nummer in einer Zeile vorhanden ist
	 * @param row Zeile
	 * @param number zu kontrollierende Nummer
	 * @return true, wenn die nummer in der Zeile ist
	 */
	private boolean isInRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[row][i] == number)
				return true;
		
		return false;
	}
	
	/**
	 * Kontrolliert ob die Nummer in einer Spalte vorhanden ist
	 * @param col Spalte
	 * @param number zu kontrollierende Nummer
	 * @return true, wenn die nummer in der Spalte ist
	 */
	private boolean isInCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[i][col] == number)
				return true;
				
		return false;
	}
	
	/**
	 * Kontrolliert, ob die Nummer in einen 3x3 Quadrat ist
	 * @param row Zeile
	 * @param col Spalte
	 * @param number zu kontrollierende Nummer
	 * @return true, wenn die nummer im Quadrat ist
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
	 * Loest die Array rekursiv 
	 * @return zeigt ob das loesen erfolgreich war
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
	 * Veranlasst dass das Programm millis Millisekunden pausiert
	 * @param millis Anzahl der Millisekunden die zu warten sind
	 */
	public static void bremse(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
