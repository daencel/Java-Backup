package Sorting_Algorithms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Sorter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private static int b;
	
	JPanel panel = (JPanel) frame.getContentPane();
	
	public Sorter() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1100,800);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		SelectSort();
	}
	
	public void SelectSort() {
		panel.setLayout(null);
		
		JLabel welcome = new JLabel("What Sorting Algorithm do you want to use?");
		welcome.setBounds(300, 100, 1600, 50);
		welcome.setFont(new Font("Courier New",Font.BOLD, 50));
		panel.add(welcome);
		
		JTextField Iterations = new JTextField("900");
		Iterations.setBounds(800, 900, 300, 50);
		Iterations.setFont(new Font("Courier New",Font.BOLD, 30));
		panel.add(Iterations);
		
		JButton BubbleSort = new JButton("Bubble Sort");
		BubbleSort.setBounds(800, 400, 300, 50);
		BubbleSort.setFont(new Font("Courier New",Font.BOLD, 30));
		panel.add(BubbleSort);
		
		BubbleSort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int a = getIterations(Iterations);
				if (a != 0) {
					BubbleSort(a);
				}
			}
		});
		
		JButton InsertionSort = new JButton("Insertion Sort");
		InsertionSort.setBounds(800, 500, 300, 50);
		InsertionSort.setFont(new Font("Courier New",Font.BOLD, 30));
		panel.add(InsertionSort);
		
		InsertionSort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int a = getIterations(Iterations);
				if (a != 0) {
					InsertionSort(a);
				}
			}
		});
		
		JButton QuickSort = new JButton("Quick Sort");
		QuickSort.setBounds(800, 600, 300, 50);
		QuickSort.setFont(new Font("Courier New",Font.BOLD, 30));
		panel.add(QuickSort);
		
		QuickSort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int a = getIterations(Iterations);
				if (a != 0) {
					int arr[] = RandomArray(a);
					if (arr.length < 150) {
						b = 1;
						if (arr.length < 100) {
							b = 2;
							if (arr.length < 30) {
								b = 7;
							}
						}
					}
					QuickSort(arr, 0, arr.length-1);
				}
			}
		});
		
		JButton SelectionSort = new JButton("Selection Sort");
		SelectionSort.setBounds(800, 700, 300, 50);
		SelectionSort.setFont(new Font("Courier New",Font.BOLD, 30));
		panel.add(SelectionSort);
		
		SelectionSort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int a = getIterations(Iterations);
				if (a != 0) {
					SelecionSort(a);
				}
			}
		});
		
		frame.setVisible(true);
	}
	
	public int getIterations(JTextField a) {
		try {
			if (Integer.parseInt(a.getText()) < 1000)
				return Integer.parseInt(a.getText());
			else {
				JOptionPane.showMessageDialog(frame.getContentPane(),"The value you entered is to high");
				a.setText("");
				return 0;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame.getContentPane(),"The value you entered isn't valid for this field");
			a.setText("");
			return 0;
		}
	}
	
	public void SelecionSort(int a) {
		Graphics g = frame.getGraphics();
		g.clearRect(0, 0, 1900, 1400);
		int arr[] = RandomArray(a);
		DrawSort(arr);
	    int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j;
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i];
    		DrawSort(arr);
    		arr[i] = temp; 
    		DrawSort(arr);
        }
        bremse(6000);
        SelectSort();
	}
	
	public void BubbleSort(int a) {
		Graphics g = frame.getGraphics();
		g.clearRect(0, 0, 1900, 1400);
		int arr[] = RandomArray(a);
		if (arr.length < 200)
			b = 1;
			if (arr.length < 100)
				b = 3;
				if (arr.length < 30)
					b = 10;
		DrawSort(arr);
	    for (int i = 0; i < a - 1; i++)
	    	for (int j = 0; j < a-1; j++)
	    		if (arr[j] > arr[j+1]) {
	    			int temp = arr[j];
	    			arr[j] = arr[j+1];
	    			DrawSort(arr);
	    			arr[j+1] = temp;
	    			DrawSort(arr);
	    		}
	    bremse(6000);
	    SelectSort();
	}
	
	public void InsertionSort(int a) {
		Graphics g = frame.getGraphics();
		g.clearRect(0, 0, 1900, 1400);
		int arr[] = RandomArray(a);
		if (arr.length < 200)
			b = 1;
			if (arr.length < 100)
				b = 3;
				if (arr.length < 30)
					b = 10;
		DrawSort(arr);
	    for (int i = 1; i < a-1; i++) { 
	        int key = arr[i]; 
	        int j = i - 1; 
	        while (j >= 0 && arr[j] > key) { 
	            arr[j + 1] = arr[j]; 
	            DrawSort(arr);
	            j = j - 1; 
	        } 
	        arr[j + 1] = key; 
	    }
	    bremse(6000);
	    SelectSort();
	}
	
	/**
	public void MergeSort(int arr [],int n) {
		if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    int[] l = new int[mid];
	    int[] r = new int[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	        l[i] = arr[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = arr[i];
	    }
	    MergeSort(l, mid);
	    MergeSort(r, n - mid);
	    
	    merge(arr, l, r, mid, n - mid);
	}
	
	public void merge(int[] a, int[] l, int[] r, int left, int right) {
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	        if (l[i] <= r[j]) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }
	    while (i < left) {
	        a[k++] = l[i++];
	    }
	    while (j < right) {
	        a[k++] = r[j++];
	    }
	}**/
	
	public int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if (arr[j] <= pivot) {
            	i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            DrawSort(arr);
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1; 
    } 
  
    public void QuickSort(int arr[], int low, int high) {
    	if (low < high) {
    		int pi = partition(arr, low, high);
    		QuickSort(arr, low, pi-1); 
            QuickSort(arr, pi+1, high); 
        } 
    } 
				
	
	public int[] RandomArray(int a) {
		int ret [] = new int [a];
		for (int i = 0; i < a; i++) {
			ret[i] = (int) (Math.random()*1000+1);
		}
		return ret;
	}

	
	public void DrawSort(int a []) {
		Graphics g = panel.getGraphics();
		bremse(b);
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1900, 1400);
		g.setColor(Color.BLACK);
		int l1 = 1700/a.length;
		int l = 150;
		for (int i = 0; i < a.length-1; i++) {
			g.fillRect(l, 1200-a[i], l1, a[i]);
			l += l1;
		}
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
	
	public static void main(String[] args) {
		new Sorter();
	}
}