package net.tfobz.jpgcompression;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Content - Pane
	 */
	private JPanel contentPane;

	/**
	 * File selcted from the File Chooser
	 */
	private File selectedFile;

	/**
	 * List with all the tasks
	 */
	private List<Compress> tasks = new ArrayList<Compress>();

	/**
	 * List with the Future Elements
	 */
	private List<Future<BufferedImage>> future = null;

	/**
	 * List with the done tasks
	 */
	private ArrayList<BufferedImage> done = new ArrayList<BufferedImage>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("all")
	public <T> GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);
		setTitle("JPG - Compression");
		ImageIcon img = new ImageIcon(getClass().getResource("icon.png"));
		setIconImage(img.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		selectedFile = new File(getClass().getResource("feld.jpg").getPath().replace("%20", " "));

		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);

		ImageComponent image = new ImageComponent();
		try {
			image.setImage(selectedFile);
		} catch (IOException e3) {
		}
		contentPane.add(image, BorderLayout.CENTER);

		JButton btnOpen = new JButton("Open...");
		btnOpen.setMnemonic('o');
		southPanel.add(btnOpen);
		btnOpen.addActionListener(e -> {
			JFileChooser f = new JFileChooser();
			f.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg"));
			f.setAcceptAllFileFilterUsed(false);
			int returnVal = f.showOpenDialog(GUI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				selectedFile = f.getSelectedFile();
				try {
					image.setImage(selectedFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				progressBar.setValue(0);
			}
		});

		JSpinner granularitaet = new JSpinner();
		granularitaet.setModel(new SpinnerNumberModel(0.1, 0.01, 0.1, 0.01));
		southPanel.add(granularitaet);

		JButton btnCompress = new JButton("Compress");

		JButton btnCompressSave = new JButton("Compress and save");
		btnCompressSave.setMnemonic('s');
		southPanel.add(btnCompressSave);
		btnCompressSave.addActionListener(e -> {
			if (image.getImage() != null) {
				btnOpen.setEnabled(false);
				btnCompressSave.setEnabled(false);
				btnCompress.setEnabled(false);
				granularitaet.setEnabled(false);
				Thread a = new Thread(() -> {
					this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					double intervall = Math.round((Double) granularitaet.getValue() * 100.0) / 100.0;
					progressBar.setValue(0);
					BufferedImage temp = image.getImage();

					String path = selectedFile.getAbsolutePath();
					path = path.replaceAll(selectedFile.getName(), "") + selectedFile.getName().split("\\.")[0]
							+ "-Compressed";
					new File(path).mkdirs();

					tasks = new ArrayList<Compress>();
					future = null;
					done = new ArrayList<BufferedImage>();

					for (double i = 1.0; i > 0; i -= intervall) {
						tasks.add(new Compress(temp, i));
					}

					ExecutorService es = Executors.newCachedThreadPool();

					InvokeThread t = null;
					if (temp.getHeight() > 1000)
						t = new InvokeThread();
					try {
						future = es.invokeAll(tasks);
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
					if (temp.getHeight() > 1000)
						t.interrupt();

					ProgressThread progress = new ProgressThread(progressBar);
					progress.start();

					for (Future<BufferedImage> tempF : future) {
						while (!tempF.isDone())
							try {
								Thread.sleep(100);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						try {
							temp = tempF.get(9, TimeUnit.SECONDS);
						} catch (InterruptedException | ExecutionException | TimeoutException e1) {
							e1.printStackTrace();
						}
						try {
							ImageIO.write(temp, "jpg",
									new File(path + "\\Compressed" + Math.round(future.indexOf(tempF)) + ".jpg"));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						done.add(temp);
					}
					progressBar.setValue(tasks.size());

					image.setImage(temp);
					btnOpen.setEnabled(true);
					btnCompressSave.setEnabled(true);
					btnCompress.setEnabled(true);
					granularitaet.setEnabled(true);
					this.setDefaultCloseOperation(EXIT_ON_CLOSE);

					JOptionPane.showMessageDialog(GUI.this, "Files have been saved");
				});
				a.start();
			}
		});

		btnCompress.setMnemonic('c');
		southPanel.add(btnCompress);
		btnCompress.addActionListener(e -> {
			if (image.getImage() != null) {
				btnOpen.setEnabled(false);
				btnCompressSave.setEnabled(false);
				btnCompress.setEnabled(false);
				granularitaet.setEnabled(false);
				Thread a = new Thread(() -> {
					this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					double intervall = Math.round((Double) granularitaet.getValue() * 100.0) / 100.0;
					progressBar.setValue(0);
					BufferedImage temp = image.getImage();

					tasks = new ArrayList<Compress>();
					future = null;
					done = new ArrayList<BufferedImage>();

					for (double i = 1.0; i > 0; i -= intervall) {
						tasks.add(new Compress(temp, i));
					}

					ExecutorService es = Executors.newCachedThreadPool();

					InvokeThread t = null;
					if (temp.getHeight() > 1000)
						t = new InvokeThread();
					try {
						future = es.invokeAll(tasks);
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
					if (temp.getHeight() > 1000)
						t.interrupt();

					ProgressThread progress = new ProgressThread(progressBar);
					progress.start();

					for (Future<BufferedImage> tempF : future) {
						while (!tempF.isDone())
							try {
								Thread.sleep(100);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						try {
							temp = tempF.get(9, TimeUnit.SECONDS);
						} catch (InterruptedException | ExecutionException | TimeoutException e1) {
							e1.printStackTrace();
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						done.add(temp);
						image.setImage(temp);
					}
					progressBar.setValue(tasks.size());
					btnOpen.setEnabled(true);
					btnCompressSave.setEnabled(true);
					btnCompress.setEnabled(true);
					granularitaet.setEnabled(true);
					this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				});
				a.start();
			}
		});

		southPanel.add(progressBar);
	}

	/**
	 * 
	 * @author Daniel Lechner
	 *
	 */
	private class InvokeThread extends Thread {

		/**
		 * Changing text that is shown in the Dialog
		 */
		private String text = "Loading components .";

		/**
		 * JDialog
		 */
		private JDialog dialog;

		/**
		 * JLabel with the text
		 */
		private JLabel label;

		/**
		 * Builds the Dialog and starts the Thread
		 */
		public InvokeThread() {
			dialog = new JDialog();
			dialog.setTitle("INFORMATION");
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setResizable(false);
			dialog.setModal(true);
			dialog.setAlwaysOnTop(true);

			JPanel contentPane = new JPanel();
			dialog.setContentPane(contentPane);
			label = new JLabel(text + "...");
			contentPane.add(label);

			dialog.pack();
			dialog.setLocationRelativeTo(null);
			label.setText(text);

			start();
		}

		@Override
		public void run() {
			while (!isInterrupted()) {
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					dialog.setVisible(false);
					dialog.dispose();
					interrupt();
				}
				if (label.getText().equals("Loading components ...."))
					text = "Loading components ";
				label.setText(text += ".");
			}
		}
	}

	/**
	 * 
	 * @author Daniel Lechner
	 *
	 */
	private class ProgressThread extends Thread {

		/**
		 * The Progress bar
		 */
		private JProgressBar bar;

		public ProgressThread(JProgressBar bar) {
			this.bar = bar;
		}

		@Override
		public void run() {
			bar.setMaximum(tasks.size());
			while (done.size() < tasks.size()) {
				EventQueue.invokeLater(() -> bar.setValue(done.size()));
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}