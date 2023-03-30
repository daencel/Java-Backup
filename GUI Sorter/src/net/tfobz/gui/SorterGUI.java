
package net.tfobz.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.tfobz.algorithms.*;

public class SorterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private final static int MIN = 10;
	private final static int MAX = 500;

	public SorterGUI() {
		setTitle("Sorting algorithms");
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		// Amount Points Label
		JLabel size = new JLabel(MAX / 2 + "");
		size.setFont(new Font("Courier New", Font.BOLD, 20));
		size.setBounds(280, 120, 90, 20);
		panel.add(size);

		// Welcome Label
		JLabel sorter = new JLabel("Select the amount of Points:");
		sorter.setFont(new Font("Courier New", Font.BOLD, 15));
		sorter.setBounds(170, 20, 300, 20);
		panel.add(sorter);

		// Slider
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 3, MAX, 250);
		slider.setBounds(100, 60, 400, 40);
		slider.setFont(new Font("Serif", Font.ITALIC, 15));

		// Set the labels to be painted on the slider
		slider.setPaintLabels(true);

		// Add positions label in the slider
		Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
		position.put(MIN, new JLabel("10"));
		position.put(150, new JLabel("150"));
		position.put(250, new JLabel("250"));
		position.put(350, new JLabel("350"));
		position.put(MAX, new JLabel("500"));

		// Set the label to be drawn
		slider.setLabelTable(position);

		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				size.setText(slider.getValue() + "");
			}
		});

		// Button Insertion Sort
		JButton insertionSort = new JButton("Insertion Sort");
		insertionSort.setFont(new Font("Courier New", Font.BOLD, 15));
		insertionSort.setBounds(175, 200, 250, 30);
		panel.add(insertionSort);

		insertionSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new InsertionSort(slider.getValue());
				setVisible(true);
			}
		});

		// Button Bubble Sort
		JButton bubbleSort = new JButton("Bubble Sort");
		bubbleSort.setFont(new Font("Courier New", Font.BOLD, 15));
		bubbleSort.setBounds(175, 250, 250, 30);
		panel.add(bubbleSort);

		bubbleSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new BubbleSort(slider.getValue());
				setVisible(true);
			}
		});

		// Button Selection Sort
		JButton selectionSort = new JButton("Selection Sort");
		selectionSort.setFont(new Font("Courier New", Font.BOLD, 15));
		selectionSort.setBounds(175, 300, 250, 30);
		panel.add(selectionSort);

		selectionSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SelectionSort(slider.getValue());
				setVisible(true);
			}
		});

		// Button Quick Sort
		JButton quickSort = new JButton("QuickSort");
		quickSort.setFont(new Font("Courier New", Font.BOLD, 15));
		quickSort.setBounds(175, 350, 250, 30);
		panel.add(quickSort);

		quickSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new QuickSort(slider.getValue());
				setVisible(true);
			}
		});

		// Button Merge Sort
		JButton mergeSort = new JButton("Merge Sort");
		mergeSort.setFont(new Font("Courier New", Font.BOLD, 15));
		mergeSort.setBounds(175, 400, 250, 30);
		panel.add(mergeSort);

		mergeSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MergeSort(slider.getValue());
				setVisible(true);
			}
		});

		// Button Bogo Sort
		JButton bogoSort = new JButton("Bogo Sort (max 20)");
		bogoSort.setFont(new Font("Courier New", Font.BOLD, 15));
		bogoSort.setBounds(175, 450, 250, 30);
		panel.add(bogoSort);

		bogoSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new BogoSort(slider.getValue());
				setVisible(true);
			}
		});

		// Gnome Merge Sort
		JButton gnomeSort = new JButton("Gnome Sort");
		gnomeSort.setFont(new Font("Courier New", Font.BOLD, 15));
		gnomeSort.setBounds(175, 500, 250, 30);
		panel.add(gnomeSort);

		gnomeSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GnomeSort(slider.getValue());
				setVisible(true);
			}
		});

		panel.add(slider);
		add(panel);
		slider.setVisible(true);
		panel.setVisible(true);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SorterGUI();
	}

}
