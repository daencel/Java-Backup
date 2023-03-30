package net.daniellechner.simulation;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicLookAndFeel;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.demo.charts.ExampleChart;

import com.bulenkov.darcula.DarculaLaf;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int allInfected = 1;
	private static int allDead = 0;
	private static int allRecovered = 0;
	private ArrayList<Person> people;
	public static Virus virus;

	/**
	 * Create the frame.
	 */
	public GUI(int size, ResourceBundle dictionary) {
		setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 700);
		setMinimumSize(new Dimension(1100, 600));
		setTitle(dictionary.getString("title"));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		BasicLookAndFeel darcula = new DarculaLaf();
		try {
			UIManager.setLookAndFeel(darcula);
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		TrayIcon trayIcon = null;
		if (SystemTray.isSupported()) {
			SystemTray systemTray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png"));
			PopupMenu trayPopupMenu = new PopupMenu();
			trayPopupMenu.setFont(new Font("TimesRoman", 10, 17));

			trayIcon = new TrayIcon(image, "Simulation", trayPopupMenu);
			trayIcon.setImageAutoSize(true);
			try {
				systemTray.add(trayIcon);
			} catch (AWTException awtException) {
				awtException.printStackTrace();
			}
		} else {
			System.out.println("SystemTray not supported");
		}

		JLabel lbltitle = new JLabel(dictionary.getString("title"));
		lbltitle.setFont(new Font("Serif", Font.PLAIN, 34));
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbltitle, BorderLayout.NORTH);

		JPanel optionPane = new JPanel();
		optionPane.setBorder(new EmptyBorder(15, 0, 30, 0));
		contentPane.add(optionPane, BorderLayout.SOUTH);

		JLabel infectionRatelbl = new JLabel(dictionary.getString("inf"));
		optionPane.add(infectionRatelbl);

		JSpinner infectionRateSpinner = new JSpinner();
		infectionRateSpinner.setModel(new SpinnerNumberModel(4, 0.0, 25, 1));
		optionPane.add(infectionRateSpinner);

		JLabel deathRatelbl = new JLabel(dictionary.getString("mort"));
		optionPane.add(deathRatelbl);

		JSpinner deathRateSpinner = new JSpinner();
		deathRateSpinner.setModel(new SpinnerNumberModel(2, 0, 50, 1));
		optionPane.add(deathRateSpinner);

		JLabel lblRecoveryRate = new JLabel(dictionary.getString("rec"));
		optionPane.add(lblRecoveryRate);

		JSpinner recoveryRateSpinner = new JSpinner();
		recoveryRateSpinner.setModel(new SpinnerNumberModel(10, 0, 50, 1));
		optionPane.add(recoveryRateSpinner);

		JLabel lblProbabilityOfPeople = new JLabel(dictionary.getString("lea"));
		optionPane.add(lblProbabilityOfPeople);

		JSpinner leavingSpinner = new JSpinner();
		leavingSpinner.setModel(new SpinnerNumberModel(40, 0, 100, 5));
		optionPane.add(leavingSpinner);

		JButton btnRestart = new JButton(dictionary.getString("inf0"));
		btnRestart.setBackground(Color.GREEN);
		btnRestart.setMnemonic('I');
		optionPane.add(btnRestart);

		JPanel dataPane = new JPanel();
		dataPane.setBorder(new EmptyBorder(0, 20, 0, 20));
		contentPane.add(dataPane, BorderLayout.EAST);
		dataPane.setLayout(new BoxLayout(dataPane, BoxLayout.Y_AXIS));

		JLabel lblData = new JLabel(dictionary.getString("data"));
		lblData.setFont(new Font("Serif", Font.PLAIN, 25));
		dataPane.add(lblData);

		JLabel lblDay = new JLabel(dictionary.getString("day") + " 0");
		lblDay.setFont(new Font("Serif", Font.PLAIN, 18));
		lblDay.setToolTipText("");
		dataPane.add(lblDay);

		JSeparator stop = new JSeparator();
		dataPane.add(stop);

		JLabel lblHealthy = new JLabel(dictionary.getString("healthy") + " 0");
		lblHealthy.setFont(new Font("Tahoma", Font.PLAIN, 17));
		dataPane.add(lblHealthy);

		JLabel lblInfected = new JLabel(dictionary.getString("infected") + " 0");
		lblInfected.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInfected.setForeground(Color.ORANGE);
		dataPane.add(lblInfected);

		JLabel lblDead = new JLabel(dictionary.getString("dead") + " 0");
		lblDead.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDead.setForeground(Color.RED);
		dataPane.add(lblDead);

		JLabel lblRecovered = new JLabel(dictionary.getString("recovered") + " 0");
		lblRecovered.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRecovered.setForeground(Color.GREEN);
		dataPane.add(lblRecovered);

		JSeparator sbottom = new JSeparator();
		dataPane.add(sbottom);

		JLabel lblSpeed = new JLabel(dictionary.getString("pause"));
		lblSpeed.setFont(new Font("Serif", Font.PLAIN, 18));
		dataPane.add(lblSpeed);

		JPanel panel = new JPanel();
		dataPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JSlider timeSlider = new JSlider();
		panel.add(timeSlider);
		timeSlider.setAlignmentX(JSlider.CENTER_ALIGNMENT);
		timeSlider.setSnapToTicks(true);
		timeSlider.setPaintTicks(true);
		timeSlider.setPaintLabels(true);
		timeSlider.setMinorTickSpacing(200);
		timeSlider.setMajorTickSpacing(200);
		timeSlider.setMaximum(2000);
		timeSlider.setMinimum(0);
		timeSlider.setValue(1000);
		timeSlider.setOrientation(SwingConstants.VERTICAL);

		Simulation simulationPanel = new Simulation();
		simulationPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		contentPane.add(simulationPanel, BorderLayout.CENTER);
		people = new ArrayList<Person>();

		Random ran = new Random();
		for (int i = 0; i < size; i++) {
			people.add(new Person(ran.nextInt(9)));
		}
		simulationPanel.setPeople(people, 828, 561);
		simulationPanel.repaint();
		lblHealthy.setText(dictionary.getString("healthy") + people.size());

		btnRestart.addActionListener(e -> {

			if (btnRestart.getText().equals(dictionary.getString("restart"))) {
				int ret = JOptionPane.showConfirmDialog(GUI.this, dictionary.getString("reset"));
				if (ret == JOptionPane.CANCEL_OPTION)
					return;
				if (ret == JOptionPane.YES_OPTION) {
					recoveryRateSpinner.setModel(new SpinnerNumberModel(10, 0, 50, 1));
					infectionRateSpinner.setModel(new SpinnerNumberModel(4, 0.0, 25, 1));
					deathRateSpinner.setModel(new SpinnerNumberModel(2, 0, 50, 1));
					leavingSpinner.setModel(new SpinnerNumberModel(40, 0, 50, 1));
				}
			}

			btnRestart.setText(dictionary.getString("restart"));
			btnRestart.setMnemonic('r');
			Thread t = new Thread(() -> {
				double infectivity = (double) infectionRateSpinner.getValue();
				double deatrate = (int) deathRateSpinner.getValue();
				double recoveryrate = (int) recoveryRateSpinner.getValue();
				double movingrate = (int) leavingSpinner.getValue();
				virus = new Virus(infectivity / 1000, deatrate / 100, movingrate / 100, recoveryrate / 100);
				people = new ArrayList<Person>();

				for (int i = 0; i < size; i++) {
					people.add(new Person(ran.nextInt()));
				}

				people.get(new Random().nextInt(people.size())).setInfected(true);

				simulationPanel.setPeople(people, simulationPanel.getWidth(), simulationPanel.getHeight());

				ArrayList<Integer> deathCharX = new ArrayList<Integer>();
				ArrayList<Integer> deathCharY = new ArrayList<Integer>();
				ArrayList<Integer> infectionCharX = new ArrayList<Integer>();
				ArrayList<Integer> infectionCharY = new ArrayList<Integer>();

				while (allInfected != 0) {
					allRecovered = virus.nextDay(people);
					lblDay.setText(dictionary.getString("day") + virus.day);

					simulationPanel.repaint();

					allInfected = 0;
					allDead = 0;
					for (Person person : people) {
						if (person.isDead())
							allDead++;
						else if (person.isInfected())
							allInfected++;
					}
					lblInfected.setText(dictionary.getString("infected") + allInfected);
					lblDead.setText(dictionary.getString("dead") + allDead);
					lblRecovered.setText(dictionary.getString("recovered") + allRecovered);
					lblHealthy.setText(dictionary.getString("healthy") + virus.getHealthyCount(people));

					deathCharX.add(virus.day);
					deathCharY.add(allDead);
					infectionCharX.add(virus.day);
					infectionCharY.add(allInfected);

					try {
						Thread.sleep(timeSlider.getValue());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				}

				dispose();

				ExampleChart<XYChart> exampleChart = new Chart(deathCharX, deathCharY, infectionCharX, infectionCharY);
				XYChart chart = exampleChart.getChart();
				new SwingWrapper<XYChart>(chart).displayChart();

				int rett = JOptionPane.showConfirmDialog(GUI.this, dictionary.getString("save"), "Save",
						JOptionPane.YES_NO_OPTION);
				if (rett == JOptionPane.YES_OPTION) {
					JFileChooser fc = new JFileChooser();
					fc.setFileFilter(new FileNameExtensionFilter("IMAGE", "jpg"));
					int ret = fc.showSaveDialog(GUI.this);
					if (ret == JFileChooser.APPROVE_OPTION) {
						try {
							BitmapEncoder.saveBitmapWithDPI(chart, fc.getSelectedFile().getAbsolutePath(),
									BitmapFormat.PNG, 1000);
							// VectorGraphicsEncoder.saveVectorGraphic(chart,
							// fc.getSelectedFile().getAbsolutePath(), VectorGraphicsFormat.PDF);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			t.start();
		});

		infectionRateSpinner.addChangeListener(e -> {
			if (virus != null) {
				double temp = (double) infectionRateSpinner.getValue();
				virus.setInfectionrate(temp / 1000);
			}
		});

		deathRateSpinner.addChangeListener(e -> {
			if (virus != null) {
				double temp = (int) deathRateSpinner.getValue();
				virus.setDeathrate(temp / 100);
			}
		});

		recoveryRateSpinner.addChangeListener(e -> {
			if (virus != null) {
				double temp = (int) recoveryRateSpinner.getValue();
				virus.setRecoveryrate(temp / 100);
			}
		});

		leavingSpinner.addChangeListener(e -> {
			if (virus != null) {
				double temp = (int) leavingSpinner.getValue();
				virus.setMovingrate(temp / 100);
			}
		});

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				simulationPanel.setPeople(people, simulationPanel.getWidth(), simulationPanel.getHeight());
			}
		});
	}
}