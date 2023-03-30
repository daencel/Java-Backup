package net.daniellechner.simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicLookAndFeel;
import com.bulenkov.darcula.DarculaLaf;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class initGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ResourceBundle dictionary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Locale currentLocale = Locale.getDefault();
		currentLocale = new Locale(currentLocale.getLanguage(), currentLocale.getCountry());
		dictionary = ResourceBundle.getBundle("MessagesBundle", currentLocale);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initGUI frame = new initGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public initGUI() {

		setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1001, 701);
		setMinimumSize(new Dimension(1000, 700));
		setTitle(dictionary.getString("titleFrame"));
		setLocationRelativeTo(null);

		BasicLookAndFeel darcula = new DarculaLaf();
		try {
			UIManager.setLookAndFeel(darcula);
		} catch (UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblSarscovSimulation = new JLabel(dictionary.getString("title"));
		lblSarscovSimulation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSarscovSimulation.setFont(new Font("Serif", Font.PLAIN, 40));
		contentPane.add(lblSarscovSimulation, BorderLayout.NORTH);

		JLabel lblc = new JLabel("© Daniel Lechner 2020, Theme: Darcula");
		lblc.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblc, BorderLayout.SOUTH);

		JPanel introduction = new JPanel();
		introduction.setBorder(new EmptyBorder(10, 40, 10, 40));
		contentPane.add(introduction, BorderLayout.CENTER);
		introduction.setLayout(new BorderLayout(0, 0));

		JPanel more = new JPanel();
		more.setBorder(new EmptyBorder(30, 0, 40, 0));
		introduction.add(more, BorderLayout.SOUTH);
		more.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton info1 = new JButton(dictionary.getString("btninfo1"));
		more.add(info1);
		info1.addActionListener(e -> {
			try {
				Desktop.getDesktop()
						.browse(new URL("https://www.who.int/emergencies/diseases/novel-coronavirus-2019").toURI());
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		});

		JButton info2 = new JButton(dictionary.getString("btninfo2"));
		more.add(info2);

		info2.addActionListener(e -> {
			try {
				Desktop.getDesktop().browse(
						new URL("https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public")
								.toURI());
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		});

		JPanel panel = new JPanel();
		introduction.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JTextPane txtInfo = new JTextPane();
		panel.add(txtInfo);
		txtInfo.setEditable(false);
		txtInfo.setFont(new Font("Serif", Font.PLAIN, 15));
		txtInfo.setText(dictionary.getString("intro1") + "\n\n" + dictionary.getString("intro2") + "\n\n"
				+ dictionary.getString("intro3"));
		JLabel lblWelcome = new JLabel(dictionary.getString("welcome"));
		panel.add(lblWelcome, BorderLayout.NORTH);
		lblWelcome.setFont(new Font("Serif", Font.PLAIN, 20));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel config = new JPanel();
		config.setBorder(new EmptyBorder(0, 0, 40, 0));
		panel.add(config, BorderLayout.SOUTH);

		JLabel lblPopulation = new JLabel(dictionary.getString("population") + ": 1000    ");
		config.add(lblPopulation);

		JButton btnStartConfiguration = new JButton(dictionary.getString("btnStart"));
		btnStartConfiguration.setBackground(Color.GREEN);
		config.add(btnStartConfiguration);

		btnStartConfiguration.addActionListener(e -> {
			dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GUI frame = new GUI(1000, dictionary);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});
	}
}