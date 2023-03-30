package net.tfobz.tictactoesingle;

public class TicTacToeSingle {

	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe(3);
		System.out.println("TIC-TAC-TOE\n============");
		int in;
		while (ttt.getWinner() >= 0 && ttt.isWinnable()) {
			in = -1;
			System.out.println(ttt.toString());
			while (in < 0 || in > ttt.getFieldSize()) {
				try {
					in = TestScannerErweitert.readInt("Spieler 1: ihr Zug: ");
					if (ttt.setTurnP1(in) < 0) {
						in = -1;
					}
				} catch (Exception e) {
					System.out.println("unglueltige Eingabe");
					in = -1;
					// e.printStackTrace();
				}
			}
			in = -1;

			if (ttt.getWinner() < 0) {
				System.out.println(ttt.toString() + "\n");
				System.out.println("Congratulations Player " + ttt.getWinner() * -1);
				break;
			}
			System.out.println(ttt.toString());
			while (in < 0 || in > ttt.getFieldSize()) {
				try {
					in = TestScannerErweitert.readInt("Spieler 2: ihr Zug: ");
					if (ttt.setTurnP2(in) < 0) {
						in = -1;
					}
				} catch (Exception e) {
					System.out.println("unglueltige Eingabe");
					in = -1;
					// e.printStackTrace();
				}
			}
		}
		if (ttt.getWinner() >= 0)
			System.out.println("Game can't be won anymore");
	}
}
