package Sorter;

public class Sortieralgorithmen {
	
	/**
	 * Gibt die Array als String aus
	 * @param a Array
	 * @return Array als String
	 */
	public static String toString(int [] a) {
		String ret = "";
		for (int i = 0; i < a.length-1; i++) {
			ret += a[i];
		}
		return ret;
	}
	
	/**
	 * Erstellt eine zufaellige Array der Laenge a
	 * @param a Laenge der Array
	 * @return Array
	 */
	public static int[] RandomArray(int a) {
		int ret [] = new int [a];
		for (int i = 0; i < a; i++) {
			ret[i] = (int) (Math.random()*9+1);
		}
		return ret;
	}
	
	/**
	 * SOrtiert die Array mit Insertion Sort
	 * @param input
	 * @return
	 */
	public static int [] InsertionSort(int[] input) {
	    for (int i = 1; i < input.length; i++) { 
	        int key = input[i]; 
	        int j = i - 1;
	        while (j >= 0 && input[j] > key) {
	            input[j + 1] = input[j];
	            j = j - 1;
	        }
	        input[j + 1] = key; 
	    }
	    return input;
	}
	
	/**
	 * Sortiert die Array nach Quick Sort
	 * @param arr Array zu sortieren
	 * @param low Low Pivot
	 * @param high High Pivot
	 * @return sortierte Array
	 */
	public static int [] QuickSort(int arr[], int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
	        QuickSort(arr, low, pi-1);
	        QuickSort(arr, pi+1, high); 
	    } 
		return arr;
	}
	
	/**
	 * Sortiert eine Array nach Bubble Sort
	 * @param arr zu sortierende array
	 * @return sortierte array
	 */
	public static int [] BubbleSort(int []arr) {
	    for (int i = 0; i < arr.length - 1; i++)
	    	for (int j = 0; j < arr.length-1; j++)
	    		if (arr[j] > arr[j+1]) {
	    			int temp = arr[j];
	    			arr[j] = arr[j+1];
	    			arr[j+1] = temp;
	    		}
	    return arr;
	}
	 
	/**
	 * Teil des Quicksort Algorithmus
	 * @param arr Array zu Sortieren
	 * @param low Low Pivot
	 * @param high High Pivot
	 * @return Index
	 */
	 public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if (arr[j] <= pivot) {
            	i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1; 
	 }
	 
	 /**
	  * Sortiert die Array nach Selection Sort
	  * @param arr zu sortierende Array
	  * @return sortierte Array
	  */
	 public static int [] SelectionSort(int [] arr) {
		    int n = arr.length;
	        for (int i = 0; i < n-1; i++) {
	            int min_idx = i; 
	            for (int j = i+1; j < n; j++) 
	                if (arr[j] < arr[min_idx]) 
	                    min_idx = j;
	            int temp = arr[min_idx]; 
	            arr[min_idx] = arr[i];
	    		arr[i] = temp;
	        }
	        return arr;
	 }
}
