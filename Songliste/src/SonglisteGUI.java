import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SonglisteGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Songliste songliste = new Songliste(900);
	private static Song aktuellerSong;
	private static boolean neuModus;
	private static boolean neww;

	public static void main(String[] args) {
		JFrame frame = new SonglisteGUI();
		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		songliste.setPfad("C:\\Users\\Daniel Lechner\\Documents\\ECLIPSE DOCUMENTS\\Songlist\\tracklist.csv");
		int lret = songliste.lesenSongs();
		if (lret == -1) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "No path found");
			System.exit(0);
		}
		if (lret == -2) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "Not able to find file");
			System.exit(0);
		}
		if (lret == -3) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "Read error");
			System.exit(0);
		}
		if (lret == -4) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "Array size to small for this many songs");
			System.exit(0);
		}
		if (lret == 0) {
			aktuellerSong = songliste.getErster();
			if (aktuellerSong == null) {
				JOptionPane.showMessageDialog(frame.getContentPane(), "File is empty");
				System.exit(0);
			}
		}

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setLayout(null);

		JLabel LabelTitle = new JLabel("Title:");
		LabelTitle.setBounds(20, 50, 150, 25);
		LabelTitle.setFont(new Font("Courier New", Font.BOLD, 25));
		panel.add(LabelTitle);

		JTextField Title = new JTextField(aktuellerSong.getTitle());
		Title.setBounds(250, 50, 730, 25);
		Title.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Title);

		JLabel LabelInterpret = new JLabel("Artist:");
		LabelInterpret.setBounds(20, 150, 150, 25);
		LabelInterpret.setFont(new Font("Courier New", Font.BOLD, 25));
		panel.add(LabelInterpret);

		JTextField Interpret = new JTextField(aktuellerSong.getInterpret());
		Interpret.setBounds(250, 150, 730, 25);
		Interpret.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Interpret);

		JLabel LabelAlbum = new JLabel("Album:");
		LabelAlbum.setBounds(20, 250, 150, 25);
		LabelAlbum.setFont(new Font("Courier New", Font.BOLD, 25));
		panel.add(LabelAlbum);

		JTextField Album = new JTextField(aktuellerSong.getAlbum());
		Album.setBounds(250, 250, 730, 25);
		Album.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Album);

		JLabel LabelJahr = new JLabel("Year:");
		LabelJahr.setBounds(20, 350, 150, 25);
		LabelJahr.setFont(new Font("Courier New", Font.BOLD, 25));
		panel.add(LabelJahr);

		JTextField Jahr = new JTextField("" + aktuellerSong.getErscheinungsjahr());
		Jahr.setBounds(250, 350, 730, 25);
		Jahr.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Jahr);

		JButton Neu = new JButton("New");
		Neu.setBounds(20, 500, 320, 50);
		Neu.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Neu);

		JButton Erster = new JButton("First");
		Erster.setBounds(20, 450, 240, 50);
		Erster.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Erster);

		JButton Letzter = new JButton("Last");
		Letzter.setBounds(740, 450, 240, 50);
		Letzter.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Letzter);

		JButton Vorheriger = new JButton("Previous");
		Vorheriger.setBounds(260, 450, 240, 50);
		Vorheriger.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Vorheriger);

		JButton LoeschenAlles = new JButton("Delete All");
		LoeschenAlles.setBounds(660, 500, 320, 50);
		LoeschenAlles.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(LoeschenAlles);

		JButton Naechster = new JButton("Next");
		Naechster.setBounds(500, 450, 240, 50);
		Naechster.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Naechster);

		JButton Loeschen = new JButton("Delete");
		Loeschen.setBounds(340, 500, 320, 50);
		Loeschen.setFont(new Font("Courier New", Font.PLAIN, 25));
		panel.add(Loeschen);

		// Button Erster gedrueckt
		Erster.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				neww = false;
				if (neuModus && !(Title.getText().contentEquals("") && Album.getText().contentEquals("")
						&& Interpret.getText().contentEquals("") && Jahr.getText().contentEquals(""))) {
					neww = true;
					try {
						Song ret = new Song();
						ret.setTitle(Title.getText());
						ret.setAlbum(Album.getText());
						ret.setInterpret(Interpret.getText());
						ret.setErscheinungsjahr(Integer.parseInt(Jahr.getText()));
						int x = songliste.anfuegenNeuen(ret);
						if (x == -1) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"The value you entered isn't valid for this field");
						}
						if (x == -2) {
							JOptionPane.showMessageDialog(frame.getContentPane(), "Too many songs");
						} else {
							aktuellerSong = songliste.getErster();
							Title.setText(aktuellerSong.getTitle());
							Album.setText(aktuellerSong.getAlbum());
							Interpret.setText("" + aktuellerSong.getErscheinungsjahr());
							Jahr.setText(null);
						}
						neuModus = false;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame.getContentPane(),
								"The value you entered isn't valid for this field");
						neuModus = true;
					}
				} else {
					neuModus = false;
				}
				if (!neuModus && !neww) {
					boolean worked = true;
					if (aktuellerSong != null) {
						aktuellerSong.setAlbum(Album.getText());
						aktuellerSong.setTitle(Title.getText());
						aktuellerSong.setAlbum(Album.getText());
						try {
							aktuellerSong.setErscheinungsjahr(Integer.parseInt(Jahr.getText()));
						} catch (NumberFormatException nz) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"The value you entered isn't valid for this field");
							Jahr.setText("");
							worked = false;
						}
					}
					if (worked) {
						aktuellerSong = songliste.getErster();
						if (aktuellerSong != null) {
							Title.setText(aktuellerSong.getTitle());
							Interpret.setText(aktuellerSong.getInterpret());
							Album.setText(aktuellerSong.getAlbum());
							Jahr.setText("" + (aktuellerSong.getErscheinungsjahr()));
						}
					}
				}
			}
		});

		// Button Vorheriger gedrueckt
		Vorheriger.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				neww = false;
				if (neuModus) {
					neww = true;
					if (!(Title.getText().contentEquals("") && Album.getText().contentEquals("")
							&& Interpret.getText().contentEquals("") && Jahr.getText().contentEquals(""))) {
						try {
							Song ret = new Song();
							ret.setTitle(Title.getText());
							ret.setAlbum(Album.getText());
							ret.setInterpret(Interpret.getText());
							ret.setErscheinungsjahr(Integer.parseInt(Jahr.getText()));
							int x = songliste.anfuegenNeuen(ret);
							if (x == -1) {
								JOptionPane.showMessageDialog(frame.getContentPane(),
										"The value you entered isn't valid for this field");
							}
							if (x == -2) {
								JOptionPane.showMessageDialog(frame.getContentPane(), "Too many songs");
							} else {
								aktuellerSong = songliste.getVoriger();
								aktuellerSong = songliste.getVoriger();
								Title.setText(aktuellerSong.getTitle());
								Interpret.setText(aktuellerSong.getInterpret());
								Album.setText(aktuellerSong.getAlbum());
								Jahr.setText("" + (aktuellerSong.getErscheinungsjahr()));
							}
							neuModus = false;
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"The value you entered isn't valid for this field");
							neuModus = true;
						}
					} else {
						aktuellerSong = songliste.getVoriger();
						aktuellerSong = songliste.getNaechster();
						aktuellerSong = songliste.getAktueller();
						if (aktuellerSong == null) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"You can't go to the specific record");
						} else {
							Title.setText(aktuellerSong.getTitle());
							Interpret.setText(aktuellerSong.getInterpret());
							Album.setText(aktuellerSong.getAlbum());
							Jahr.setText("" + (aktuellerSong.getErscheinungsjahr()));
						}
						neuModus = false;
					}
				}
				if (!neuModus && !neww) {
					boolean worked = true;
					if (aktuellerSong != null) {
						aktuellerSong.setAlbum(Album.getText());
						aktuellerSong.setTitle(Title.getText());
						aktuellerSong.setAlbum(Album.getText());
						try {
							aktuellerSong.setErscheinungsjahr(Integer.parseInt(Jahr.getText()));
						} catch (NumberFormatException nz) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"The value you entered isn't valid for this field");
							Jahr.setText("");
							worked = false;
						}
					}
					if (worked) {
						aktuellerSong = songliste.getVoriger();
						if (aktuellerSong == null) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"You can't go to the specific record");
						} else {
							Title.setText(aktuellerSong.getTitle());
							Interpret.setText(aktuellerSong.getInterpret());
							Album.setText(aktuellerSong.getAlbum());
							Jahr.setText("" + (aktuellerSong.getErscheinungsjahr()));
						}
					}
				}

			}
		});

		// Naechster Button geloescht
		Naechster.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (neuModus && Title.getText().contentEquals("") && Album.getText().contentEquals("")
						&& Interpret.getText().contentEquals("") && Jahr.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(frame.getContentPane(), "You can't go to the specific record");
				} else {
					neww = false;
					if (neuModus && !(Title.getText().contentEquals("") && Album.getText().contentEquals("")
							&& Interpret.getText().contentEquals("") && Jahr.getText().contentEquals(""))) {
						neww = true;
						try {
							Song ret = new Song();
							ret.setTitle(Title.getText());
							ret.setAlbum(Album.getText());
							ret.setInterpret(Interpret.getText());
							ret.setErscheinungsjahr(Integer.parseInt(Jahr.getText()));
							int x = songliste.anfuegenNeuen(ret);
							if (x == -1) {
								JOptionPane.showMessageDialog(frame.getContentPane(),
										"The value you entered isn't valid for this field");
							}
							if (x == -2) {
								JOptionPane.showMessageDialog(frame.getContentPane(), "Too many songs");
								neuModus = false;
							} else {
								Title.setText("");
								Album.setText("");
								Interpret.setText("");
								Jahr.setText(null);
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"The value you entered isn't valid for this field");
							neuModus = true;
						}
					}
					if (!neuModus && !neww) {
						boolean worked = true;
						if (aktuellerSong != null) {
							aktuellerSong.setAlbum(Album.getText());
							aktuellerSong.setTitle(Title.getText());
							aktuellerSong.setAlbum(Album.getText());
							try {
								aktuellerSong.setErscheinungsjahr(Integer.parseInt(Jahr.getText()));
							} catch (NumberFormatException nz) {
								JOptionPane.showMessageDialog(frame.getContentPane(),
										"The value you entered isn't valid for this field");
								Jahr.setText("");
								worked = false;
							}
						}
						if (worked) {
							aktuellerSong = songliste.getAktueller();
							aktuellerSong = songliste.getNaechster();
							if (aktuellerSong == null) {
								Title.setText("");
								Interpret.setText("");
								Album.setText("");
								Jahr.setText("");
								neuModus = true;
							} else {
								Title.setText(aktuellerSong.getTitle());
								Interpret.setText(aktuellerSong.getInterpret());
								Album.setText(aktuellerSong.getAlbum());
								Jahr.setText("" + (aktuellerSong.getErscheinungsjahr()));
							}
						}
					}
				}
			}
		});

		// Letzter Button gedrueckt
		Letzter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				neww = false;
				if (neuModus && !(Title.getText().contentEquals("") && Album.getText().contentEquals("")
						&& Interpret.getText().contentEquals("") && Jahr.getText().contentEquals(""))) {
					neww = true;
					try {
						Song ret = new Song();
						ret.setTitle(Title.getText());
						ret.setAlbum(Album.getText());
						ret.setInterpret(Interpret.getText());
						ret.setErscheinungsjahr(Integer.parseInt(Jahr.getText()));
						int x = songliste.anfuegenNeuen(ret);
						if (x == -1) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"The value you entered isn't valid for this field");
						}
						if (x == -2) {
							JOptionPane.showMessageDialog(frame.getContentPane(), "Too many songs");
							neuModus = false;
						} else {
							aktuellerSong = songliste.getLetzter();
							Title.setText(aktuellerSong.getTitle());
							Interpret.setText(aktuellerSong.getInterpret());
							Album.setText(aktuellerSong.getAlbum());
							Jahr.setText("" + (aktuellerSong.getErscheinungsjahr()));
						}
						neuModus = false;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame.getContentPane(),
								"The value you entered isn't valid for this field");
						neuModus = true;
					}
				} else {
					neuModus = false;
				}
				if (!neuModus && !neww) {
					boolean worked = true;
					if (aktuellerSong != null) {
						aktuellerSong.setAlbum(Album.getText());
						aktuellerSong.setTitle(Title.getText());
						aktuellerSong.setAlbum(Album.getText());
						try {
							aktuellerSong.setErscheinungsjahr(Integer.parseInt(Jahr.getText()));
						} catch (NumberFormatException nz) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"The value you entered isn't valid for this field");
							Jahr.setText("");
							worked = false;
						}
					}
					if (worked) {
						aktuellerSong = songliste.getLetzter();
						if (aktuellerSong != null) {
							Title.setText(aktuellerSong.getTitle());
							Interpret.setText(aktuellerSong.getInterpret());
							Album.setText(aktuellerSong.getAlbum());
							Jahr.setText("" + (aktuellerSong.getErscheinungsjahr()));
						}
					}
				}
			}
		});

		// Button Neu gedrueckt
		Neu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				neww = false;
				if (neuModus) {
					try {
						Song ret = new Song();
						ret.setAlbum(Album.getText());
						ret.setErscheinungsjahr(Integer.parseInt(Jahr.getText()));
						ret.setInterpret(Interpret.getText());
						if (Title.getText() == null) {
							ret.setErscheinungsjahr(0);
						} else {
							ret.setTitle(Title.getText());
						}
						int x = songliste.anfuegenNeuen(ret);
						if (x == -1) {
							JOptionPane.showMessageDialog(frame.getContentPane(),
									"The value you entered isn't valid for this field");
						}
						if (x == -2) {
							JOptionPane.showMessageDialog(frame.getContentPane(), "Too many songs");
							neuModus = false;
						} else {
							aktuellerSong = songliste.getLetzter();
							Title.setText("");
							Interpret.setText("");
							Album.setText("");
							Jahr.setText("");
							neuModus = true;
							neww = true;
						}
					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(frame.getContentPane(),
								"The value you entered isn't valid for this field");
					}
				}
				if (!neuModus && !neww) {
					Title.setText("");
					Interpret.setText("");
					Album.setText("");
					Jahr.setText("");
					neuModus = true;
				}
			}
		});

		// Button Loeschen gedrueckt
		Loeschen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!neuModus) {
					if (JOptionPane.showConfirmDialog(frame.getContentPane(), "Are you sure?", "Delete all",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if (songliste.loeschenAktuellen() == -1) {
							JOptionPane.showMessageDialog(frame.getContentPane(), "An error has occured");
						} else {
							aktuellerSong = songliste.getNaechster();
							aktuellerSong = songliste.getVoriger();
							Title.setText(aktuellerSong.getTitle());
							Album.setText(aktuellerSong.getAlbum());
							Interpret.setText(aktuellerSong.getInterpret());
							Jahr.setText("" + aktuellerSong.getErscheinungsjahr());
						}
					} else {
						JOptionPane.showMessageDialog(frame.getContentPane(), "The query was cancelled");
					}
				} else {
					JOptionPane.showMessageDialog(frame.getContentPane(), "You can't delete an empty field");
				}
			}
		});

		// Loeschen Alles Button gedrueckt
		LoeschenAlles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(frame.getContentPane(), "Are you sure?", "Delete all",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					int ret = songliste.loeschenAlle();
					if (ret == -1) {
						JOptionPane.showMessageDialog(frame.getContentPane(), "An error has occured");
					} else {
						JOptionPane.showMessageDialog(frame.getContentPane(), "Success!");
					}
					Title.setText("");
					Interpret.setText("");
					Album.setText("");
					Jahr.setText("");
					neuModus = true;
				} else {
					JOptionPane.showMessageDialog(frame.getContentPane(), "The query was cancelled");
				}
			}
		});

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				songliste.sort();
				songliste.schreibenSongs();
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		frame.setVisible(true);
	}
}