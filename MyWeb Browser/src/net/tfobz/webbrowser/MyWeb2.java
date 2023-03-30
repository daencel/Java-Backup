package net.tfobz.webbrowser;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class MyWeb2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JEditorPane browser;
	private JPanel contentPane;
	private JTextField adresse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyWeb2 frame = new MyWeb2();
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
	public MyWeb2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("MyWeb");
		setBounds(100, 100, 680, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblAdresse = new JLabel("Adresse: ");
		panel.add(lblAdresse);

		adresse = new JTextField();
		adresse.setText("https://www.google.de");
		panel.add(adresse);
		adresse.setColumns(50);
		adresse.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				adresse.selectAll();
			}
		});

		browser = new JEditorPane();
		browser.setEditable(false);
		scrollPane.setViewportView(browser);
		setConstrains();

		browser.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					adresse.setText(e.getURL().toString());
					setConstrains();
				}
			}
		});

		browser.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.isAltDown() && e.getKeyChar() == 's') {
					adresse.requestFocusInWindow();
				}
			}

		});
		adresse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setConstrains();
			}
		});

		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConstrains();
			}
		});
		panel.add(btnSearch);
	}

	/**
	 * Ladet die Webseite und fuellt die ADresse in die Titelleiste
	 */
	private void setConstrains() {
		try {
			String adr = adresse.getText();
			if (!adr.contains("https://")) {
				adr = "https://" + adr;
			}
			adresse.setText(adr);
			browser.setPage(adr);
			if (adr.length() < 70) {
				setTitle("MyWEb - " + adr);
			}
		} catch (Exception e2) {
			JOptionPane.showInternalMessageDialog(null, "Cannot show hyperlink", "ERROR", JOptionPane.YES_OPTION);
			setTitle("MyWeb - ERROR");
		}
	}
}