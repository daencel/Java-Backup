package net.tfobz.rssreader;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

//@formatter:off

/**
 * 
 * Aufgaben:
 * 
 * 1)
 * ? : &#63; 
 * < : &lt; 
 * > : &gt; 
 * " : &quot; 
 * ' : &apos; 
 * 
 * 2)
 * #PCDATA (engl. Parsed Character Data) ist ein Zeichenbereich in SGML und XML.
 * Für PCDATA wird das Schlüsselwort #PCDATA innerhalb der DTD verwendet.
 * 
 * #CDATA (engl. Character Data) ist ein Schlüsselwort in SGML und XML.
 * Mit CDATA werden Zeichendaten gekennzeichnet, deren Inhalt vom Parser nicht analysiert wird.
 * 
 * 
 * 
 * RSS (Rich Site Summary) sind Dateiformate für Web-Feeds. Sie zeigen Änderungen auf Websites.
 * <br> Beispiele fuer RSS - Feeds:
 * 
 * <ul>
 * 	<li>
 * 		https://www.stol.it/rss/feed/AlleRessorts
 * 	</li>
 * 	<li>
 * 		https://www.spiegel.de/schlagzeilen/tops/index.rss
 * 	</li>
 * 	<li>
 * 		https://www.corrieredellosport.it/rss/calcio/serie-a/juve
 * 	</li>
 * 	<li>
 * 		https://www.gazzetta.it/rss/home.xml
 * 	</li>
 * </ul>
 *
 * 
 * @author Daniel Lechner
 *
 */
public class GUI extends JFrame {
	
	//@formatter:on

	private static final long serialVersionUID = 1L;

	/**
	 * Content Pane
	 */
	private JPanel contentPane;

	/**
	 * Arraylist mit den Items
	 */
	private static ArrayList<Item> items;

	/**
	 * Alle funktionierenden URLs
	 */
	private ArrayList<String> urls;

	/**
	 * Output fuer das JEditorPane
	 */
	private StringBuilder output = new StringBuilder();

	/**
	 * Boolean fuer den Scheduler
	 */
	boolean activated = true;

	/**
	 * J Editor Pane fuer die Ausgaben
	 */
	private JEditorPane editor;

	/**
	 * Tray Icon fuer Hinweise
	 */
	private TrayIcon trayIcon;

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
	 * Baut das Frame auf und beinhaltet alle Action Listener
	 */
	public GUI() {
		UIManager.put("OptionPane.minimumSize", new Dimension(500, 100));

		setTitle("RSS - Reader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		ImageIcon img = new ImageIcon(getClass().getResource("rss.png"));
		setIconImage(img.getImage());

		urls = new ArrayList<String>();

		JPanel south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new BorderLayout(0, 0));

		JPanel buttons = new JPanel();
		south.add(buttons, BorderLayout.EAST);

		JPanel websites = new JPanel();
		south.add(websites, BorderLayout.WEST);

		JButton btnSpiegel = new JButton("Spiegel");
		btnSpiegel.addActionListener(e -> add("https://www.spiegel.de/schlagzeilen/tops/index.rss"));
		websites.add(btnSpiegel);

		JButton btnGazetta = new JButton("Gazetta");
		btnGazetta.addActionListener(e -> add("https://www.gazzetta.it/rss/home.xml"));
		websites.add(btnGazetta);

		JButton btnStol = new JButton("Stol");
		btnStol.addActionListener(e -> add("https://www.stol.it/rss/feed/AlleRessorts"));
		websites.add(btnStol);

		JButton btnAdd = new JButton("Add URL...");
		buttons.add(btnAdd);

		JButton btnDeactivate = new JButton("Deactivate Scheduler");
		buttons.add(btnDeactivate);

		JButton btnUpdate = new JButton("Update");
		buttons.add(btnUpdate);

		editor = new JEditorPane();
		editor.setEditable(false);

		HTMLEditorKit kit = new HTMLEditorKit();
		editor.setEditorKit(kit);

		Document doc = kit.createDefaultDocument();
		editor.setDocument(doc);

		JScrollPane scrollPane = new JScrollPane(editor);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		ConstantUpdater updater = new ConstantUpdater();
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
		scheduler.scheduleAtFixedRate(updater, 20, 10, TimeUnit.SECONDS);

		if (SystemTray.isSupported()) {
			SystemTray systemTray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("rss.png"));
			PopupMenu trayPopupMenu = new PopupMenu();
			trayPopupMenu.setFont(new Font("TimesRoman", 10, 17));

			MenuItem close = new MenuItem("Close Application");
			close.addActionListener(e -> {
				dispose();
				System.exit(0);
			});
			trayPopupMenu.add(close);

			MenuItem update = new MenuItem("Update");
			update.addActionListener(e -> {
				update();
			});
			trayPopupMenu.add(update);

			trayIcon = new TrayIcon(image, "RSS - Reader", trayPopupMenu);
			trayIcon.setImageAutoSize(true);
			try {
				systemTray.add(trayIcon);
			} catch (AWTException awtException) {
				awtException.printStackTrace();
			}
		} else {
			System.out.println("SystemTray not supported");
			System.exit(-1);
		}

		btnAdd.setMnemonic('a');
		btnDeactivate.setMnemonic('d');
		btnUpdate.setMnemonic('u');

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(GUI.this, "Paste the Link:");
				boolean success = false;
				RSSReader reader = null;
				StringBuilder newoutput = new StringBuilder("");
				try {
					reader = new RSSReader(input);
					success = true;
				} catch (MalformedURLException e1) {
					if (input != null) {
						JOptionPane.showMessageDialog(GUI.this, "Link is not valid", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				if (success) {
					try {
						items = reader.parseURL();
					} catch (Exception e2) {
						success = false;
						JOptionPane.showMessageDialog(GUI.this, "Cannot parse this website", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					if (success) {
						if (reader.getChannel().getTitle() == null)
							newoutput.append("<b nowrap>Channel:</b> null");
						else
							newoutput.append("<b>Channel:</b> " + reader.getChannel().getTitle());
						if (items.get(0) == null)
							newoutput.append("<b> Newest Item:</b> null <b> Date:</b> null ");
						else {
							newoutput.append("<b> Newest Item:</b> " + items.get(0).getTitle());
							newoutput.append("<b> Date:</b> " + items.get(0).getPubDate());
						}
						newoutput.append("<br>");
						newoutput.append(output);
						output = newoutput;
						editor.setText("<html><font size=\"2\">" + output + "</font></html>");
						urls.add(input);
					}
				}
				editor.setCaretPosition(0);
			}
		});

		btnDeactivate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnDeactivate.getText().equals("Deactivate Scheduler")) {
					activated = false;
					StringBuilder newoutput = new StringBuilder(
							"<em><b>Message: </b> Scheduler is now deactivated </em><br>");
					newoutput.append(output);
					output = newoutput;
					editor.setText("<html><font size=\"2\">" + output + "</font></html>");
					btnDeactivate.setText("  Activate Scheduler  ");
					editor.setCaretPosition(0);
				} else {
					activated = true;
					StringBuilder newoutput = new StringBuilder(
							"<em><b>Message: </b> Scheduler is now running (schedule period = 10 sec.) </em><br>");
					newoutput.append(output);
					output = newoutput;
					editor.setText("<html><font size=\"2\">" + output + "</font></html>");
					btnDeactivate.setText("Deactivate Scheduler");
					editor.setCaretPosition(0);
				}
				editor.setCaretPosition(0);
			}
		});

		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
	}

	/**
	 * Die übergenene URL wird gepared und im output ausgegeben
	 * 
	 * @param url übergebene URL
	 */
	public void add(String url) {
		StringBuilder newoutput = new StringBuilder("");
		try {
			RSSReader reader = new RSSReader(url);
			items = reader.parseURL();
			if (reader.getChannel().getTitle() == null)
				newoutput.append("<b nowrap>Channel:</b> null");
			else
				newoutput.append("<b>Channel:</b> " + reader.getChannel().getTitle());
			if (items.get(0) == null)
				newoutput.append("<b> Newest Item:</b> null <b> Date:</b> null ");
			else {
				newoutput.append("<b> Newest Item:</b> " + items.get(0).getTitle());
				newoutput.append("<b> Date:</b> " + items.get(0).getPubDate());
			}
			newoutput.append("<br>");
			newoutput.append(output);
			output = newoutput;
			editor.setText("<html><font size=\"2\">" + output + "</font></html>");
			urls.add(url);
		} catch (Exception e) {

		}
		editor.setCaretPosition(0);
	}

	/**
	 * Updatet mit den RSS Feed den Editor mit einen ExecuterService
	 */
	public void update() {
		output.insert(0, "<em><b>Message: </b> Updating Channels... </em><br>");
		StringBuilder editorOutput = new StringBuilder("<html><font size=\"2\">");
		editorOutput.append(output);
		editorOutput.append("</font></html>");
		editor.setText(editorOutput.toString());

		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < urls.size(); i++) {
			Updater callable = new Updater(urls.get(i));
			Future<String> future = executor.submit(callable);
			while (!future.isDone()) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException x) {
					x.printStackTrace();
				}
			}
			try {
				output.insert(0, future.get(15, TimeUnit.SECONDS));

				editorOutput = new StringBuilder("<html><font size=\"2\">");
				editorOutput.append(output);
				editorOutput.append("</font></html>");
				editor.setText(editorOutput.toString());
			} catch (Exception e2) {
				editor.setText("<html><font size=\"5\" style=\"color:red;\"> Timeout! Restart program </font></html>");
				trayIcon.displayMessage("Timeout!", "Restart program", TrayIcon.MessageType.ERROR);
			}
		}
		editor.setCaretPosition(0);
	}

	/**
	 * Bearbeitet einen uebergeben String (URL) und mit call wird er dann geparsed,
	 * implementiert gleichzeitigen Zugriff
	 * 
	 * @author Daniel Lechner
	 *
	 */
	public class Updater implements Callable<String> {

		/**
		 * der uebergebene String (URL)
		 */
		private String url;

		/**
		 * Konstruktor des Updater
		 * 
		 * @param url url fuer den Updater
		 */
		public Updater(String url) {
			this.url = url;
		}

		@Override
		public String call() throws Exception {
			RSSReader reader = new RSSReader(url);
			ArrayList<Item> items = reader.parseURL();
			StringBuilder ret = new StringBuilder();
			if (reader.getChannel().getTitle() == null)
				ret.append("<b>Channel:</b> null");
			else
				ret.append("<b>Channel:</b> " + reader.getChannel().getTitle());
			if (items.get(0) == null)
				ret.append("<b> Newest Item:</b> null <b> Date:</b> null ");
			else {
				ret.append("<b> Newest Item:</b> " + items.get(0).getTitle());
				ret.append("<b> Date:</b> " + items.get(0).getPubDate());
			}
			ret.append("<br>");
			return ret.toString();
		}
	}

	/**
	 * Benutzt die Updater Klasse um alle 10 Sekunden, falls aktiviert, ein Update
	 * der Channels zu erstellen
	 * 
	 * @author Daniel Lechner
	 *
	 */
	private class ConstantUpdater implements Runnable {

		@Override
		public void run() {
			if (activated) {
				if (urls.size() > 0) {
					trayIcon.displayMessage("New Items", "There are new RSS Items", TrayIcon.MessageType.INFO);
				}
				StringBuilder newoutput = new StringBuilder(
						"<em><b>Message: </b> Updating Channels (Scheduler)... </em><br>");
				newoutput.append(output);
				output = newoutput;
				editor.setText("<html><font size=\"2\">" + output + "</font></html>");

				ExecutorService executor = Executors.newCachedThreadPool();
				for (int i = 0; i < urls.size(); i++) {
					Updater callable = new Updater(urls.get(i));
					Future<String> future = executor.submit(callable);
					while (!future.isDone()) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						StringBuilder ret = new StringBuilder(future.get(30, TimeUnit.SECONDS));
						ret.append(output);
						output = ret;
						editor.setText("<html><font size=\"2\">" + output + "</font></html>");
					} catch (Exception e2) {
						editor.setText(
								"<html><font size=\"5\" style=\"color:red;\"> Timeout! Restart program </font></html>");
						trayIcon.displayMessage("Timeout!", "Restart program", TrayIcon.MessageType.ERROR);
					}
				}
			}
			editor.setCaretPosition(0);
		}
	}
}