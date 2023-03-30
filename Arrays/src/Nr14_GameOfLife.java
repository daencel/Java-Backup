
import java.util.Arrays;
import java.applet.Applet;
import java.awt.*;

public class Nr14_GameOfLife extends Applet {
	
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		// Fixe Anzahl von Zeilen und Spalten
		final int ANZAHL_ZEILEN = 45;
		final int ANZAHL_SPALTEN = 45;
		// Maximale Anzahl von Iterationsschritten
		final int  MAX_SCHRITTE = 150;
		boolean [][] matrix1 = new boolean [ANZAHL_ZEILEN][ANZAHL_SPALTEN];
		int groesse = 19;
		if (groesse <= ANZAHL_ZEILEN/2 && groesse <= ANZAHL_SPALTEN/2) {
			//fuellenMatrixZufaellig(matrix1,0.5);
			fuellenMatrixSternMitte(matrix1,groesse);
			int a = 0;
			boolean [][] matrix2;
			do {
				ausgebenMatrix(matrix1,g);
				matrix2 = matrix1;
				int [][] matrixint = anzahlLebendeNachbarn(matrix1);
				matrix1 = berechneMatrix(matrixint,matrix2);
				a++;
				bremse(300);
			} while (a < MAX_SCHRITTE && existierenUnterschiede(matrix1,matrix2) == true);
			System.exit(0);
		}
		else
			System.out.println("Die angegebene Groesse ist fuer diese Matrix zu gross");
	}
		
	@Override
	public void init() {
		setSize(1100,1100);
	}
	
	/**
	 * Faellt die Uebergebene Matrix mit einen Stern, mit einer best. Groesse
	 * Die Groesse wird uebergeben. Er ist genau in der Mitte. Fuer Sterngroesse 0
	 * wird ein einziges Feld in der Mitte gefuellt
	 * @param matrix die zu fuellende Matrix
	 * @param gr groesse des Sterns
	 */
	public void fuellenMatrixSternMitte(boolean [][] matrix, int gr) {
		int y = matrix.length;
		int x = matrix[0].length;
		int xm = (int)(x/2);
		int ym = (int)(y/2);
		matrix [ym][xm] = true;
		int a = 1;
		while (a <= gr) {
			matrix [ym][xm+a] = true;
			matrix [ym+a][xm] = true;
			matrix [ym-a][xm] = true;
			matrix [ym][xm-a] = true;
			a++;
		}
	}
	
	/**
	 * Gibt die Array als Grafik aus
	 * @param matrix auszugebene Array
	 */
	public void ausgebenMatrix(boolean [][] matrix, Graphics g) {
			// Ermittle die Breite und H�he des Applets in Pixel
			int breite = getWidth()/matrix[0].length;
			int hoehe = getHeight()/matrix.length;
			for (int i = 0; i < matrix.length; i++)
				for (int j = 0; j < matrix[0].length; j++) {
					g.setColor(matrix[i][j] ? Color.BLACK : Color.GRAY);
					g.fillRect(breite*j+1, hoehe*i+1, breite-1, hoehe-1);
				}
	}
	
	/**
	 * Berechnet die Anzahl der lebenden Nachbarn
	 * @param matrix initialisierte Array
	 * @return int Array mit der Anzahl der Nachbarn in den Feldern
	 */
	public int [][] anzahlLebendeNachbarn(boolean [][] matrix) {
		int y = matrix.length;
		int x = matrix[0].length;
		int [][] matrix2 = new int [x][y];
		int z = 0;
		int s = 0;
		boolean fertig = false;
		while (!fertig) {
			int a = 0;
			if (z < matrix.length && s+1 < matrix[0].length && matrix [z][s+1]) {
				a++;
			}
			if (z+1 < matrix.length && s < matrix[0].length && matrix [z+1][s]) {
				a++;
			}
			if (z+1 < matrix.length && s+1 < matrix[0].length && matrix [z+1][s+1]) {
				a++;
			}
			if (z-1 >= 0 && s < matrix[0].length && matrix [z-1][s]) {
				a++;
			}
			if (z < matrix.length && s-1 >= 0 && matrix [z][s-1]) {
				a++;
			}
			if (z-1 >= 0 && s-1 >= 0 && matrix [z-1][s-1]) {
				a++;
			}
			if (z+1 < matrix.length && s-1 >= 0 && matrix [z+1][s-1]) {
				a++;
			}
			if (z-1 >= 0 && s+1 < matrix[0].length && matrix [z-1][s+1]) {
				a++;
			}
			matrix2 [z][s] = a;
			s++;
			if (s >= x) {
				s = 0;
				z++;
			}
			if (z >= y) {
				fertig = true;
			}
		}
		return matrix2;
	}
	
	/**
	 * Testet 2 Arrays auf unterschiede
	 * @param matrix erste Array
	 * @param matrix2 zweite Array
	 * @param return false oder true ob sie gliech sind
	 */
	public boolean existierenUnterschiede(boolean [][] matrix, boolean [][] matrix2) {
		boolean untersch = true;
		if (Arrays.deepEquals(matrix, matrix2)) {
			untersch = false;
		}
		return untersch;
	}
	
	/**
	 * Berechnet die neue Matrix mithilfe dern uebertragenen Arrays
	 * @param matrix Array mit den Leben der Nachbarn
	 * @param matrixb boolean Array die vorher augegeben wurde
	 * @return neue Array, mit neuer Anordnung
	 */
	public boolean [][] berechneMatrix(int [][] matrix, boolean [][] matrixb) {
		int y = matrix.length;
		int x = matrix[0].length;
		boolean [][] matrix2 = new boolean [y][x];
		int z = 0;
		int s = 0;
		boolean fertig = false;
		while (fertig == false) {
			int a = matrix [z][s];
			if (a == 3) {
				matrix2 [z][s] = true;
			}
			if (a < 2 || a > 3) {
				matrix2 [z][s] = false;
			}
			if (a == 2 && matrixb [z][s] == true) {
				matrix2 [z][s] = true;
			}
			s++;
			if (s >= x) {
				s = 0;
				z++;
			}
			if (z >= y) {
				fertig = true;
			}
		}
		return matrix2;
	}
	
	/**
	 * Veranlasst dass das Programm millis Millisekunden pausiert
	 * @param millis Anzahl der Millisekunden die zu warten sind
	 */
	public void bremse(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
	
	/**
	 * Fuellt die Matrix boolean [][] matrix auf zufaelligen Positionen mit Leben.
	 * Das Verhaeltniss wird mit double a uebergeben.
	 * @param matrix Matrix
	 * @param a Verhaelniss
	 */
	public void fuellenMatrixZufaellig(boolean [][] matrix, double a) {
		int y = matrix.length;
		int x = matrix[0].length;
		int g = y * x;
		a = (int)(g * a);
		for (int b = 0; b < a; b++) {
			int y1 = (int)(Math.random()*y);
			int x1 = (int)(Math.random()*x);
			while (matrix [y1][x1] == true) {
				y1 = (int)((Math.random()*y-1)+1);
				x1 = (int)((Math.random()*x-1)+1);
			}
			matrix [y1][x1] = true;
		}
	}
}
