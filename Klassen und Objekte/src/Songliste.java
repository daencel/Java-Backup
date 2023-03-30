import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Songliste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Pfad zu den Textdateien
		String quelle = "C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\tracklist.csv";
		String ziel = "C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\sortlist.csv";
		
		int nrzeile = 0;
		Song [] sort  = new Song [1000];
		boolean gelegt = false;
		
		// Zeilenweises Lesen aus einer Datei
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quelle));
			String zeile = reader.readLine();
			while (true) {
				zeile = reader.readLine();
				if (zeile == null)
					// Dateiende erkannt
					break;
				else
					gelegt = false;
					int i = nrzeile;
					Song eintrag = new Song();
					eintrag.setSong(zeile);
					sort[nrzeile] = eintrag;
					while (!gelegt) {
						if ((i-1) >= 0 && sort[i-1].compareTo(sort[i]) == 1) {
							Song a = sort [i];
							sort[i] = sort[i-1];
							sort[i-1] = a;
							i--;
						}
						else {
							gelegt = true;
						}
					}
					nrzeile++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden");
		} catch (IOException e) {
			System.out.println("Lesefehler in Datei");
		}
		
		// Zeilenweises Schreiben in eine Datei
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(ziel));
			// ACHTUNG: Am Ende jeder Zeile muss eine Zeilenschaltung \n eingefügt werden
			writer.write("Titel;Album;Interpret;Jahr\n");
			for (int i = 0; i < nrzeile; i++) {
				writer.write(sort[i].getTitle() + ";" + sort[i].getAlbum() + ";" + sort[i].getInterpret() + ";" + sort[i].getErscheinungsjahr() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Datei nicht angelegt");
		}
	}
}