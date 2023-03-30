package Sorter;

public class Hauptprogramm {

	public static void main(String[] args) {
		
		Stoppuhr uhr = new Stoppuhr();
		uhr.setPath("C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\Sorter\\SortTime.csv");
		
		for (int l = 0; l <= 100000; l = l + 5000) {
			int [] arr = Sortieralgorithmen.RandomArray(l);
			
			uhr.starteStoppuhr();
			Sortieralgorithmen.InsertionSort(arr);
			uhr.stoppeStoppuhr();
			uhr.getGestoppteZeit();
			
			uhr.starteStoppuhr();
			Sortieralgorithmen.BubbleSort(arr);
			uhr.stoppeStoppuhr();
			uhr.getGestoppteZeit();
			
			uhr.starteStoppuhr();
			Sortieralgorithmen.SelectionSort(arr);
			uhr.stoppeStoppuhr();
			uhr.getGestoppteZeit();
			
			arr = null;
			
			System.out.println(l);
		}
		if (uhr.schreibeZeiten() == 1) {
			System.out.println("ERROR");
		} else {
			System.out.println("FINISHED");
		}
	}
}
