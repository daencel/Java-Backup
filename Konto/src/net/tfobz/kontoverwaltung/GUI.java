package net.tfobz.kontoverwaltung;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Content Pane
     */
    private JPanel contentPane;

    /**
     * Maximale Anzahl von Konten
     */
    private static final int MAX_KONTEN = 100;

    /**
     * Array aller Konten
     */
    private Konto[] konten = null;

    /**
     * Startzinssatz aller Konten
     */
    private static final double STARTZINSSATZ = 0.25;

    /**
     * Startueberziehung aller Konten
     */
    private static final double STARTUEBERZEIHUNG = -1000.0;

    /**
     * Counter fuer das Array
     */
    private static int counter = 0;

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
     */
    public GUI() {
        setTitle("Kontoverwaltung");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 350);
        setLocationRelativeTo(null);

        konten = new Konto[MAX_KONTEN];
        try {
            Konto.setStartzinssatz(STARTZINSSATZ);
            Gehaltskonto.setStartueberziehung(STARTUEBERZEIHUNG);
        } catch (KontoException e2) {
            JOptionPane.showMessageDialog(GUI.this, "Fehler beim setzten der Startparameter", "Internal Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel settings = new JPanel();
        settings.setBackground(Color.LIGHT_GRAY);
        contentPane.add(settings, BorderLayout.NORTH);
        settings.setLayout(new GridLayout(3, 0));

        JPanel north = new JPanel();
        north.setBackground(Color.LIGHT_GRAY);
        north.setLayout(new GridLayout(0, 4));
        settings.add(north);

        JTextField txtKontonummer1Top = new JTextField();
        north.add(txtKontonummer1Top);

        JTextField txtKontonummer2Top = new JTextField();
        north.add(txtKontonummer2Top);

        JButton btnNeuesGehaltskonto = new JButton("Neues Gehaltskonto");
        north.add(btnNeuesGehaltskonto);

        JButton btnNeuesSparkonto = new JButton("Neues Sparkonto");
        north.add(btnNeuesSparkonto);

        JPanel center = new JPanel();
        center.setBackground(Color.LIGHT_GRAY);
        settings.add(center);
        center.setLayout(new GridLayout(1, 2));

        JPanel centerLeft = new JPanel();
        centerLeft.setBackground(Color.LIGHT_GRAY);
        centerLeft.setLayout(new GridLayout(1, 2));
        center.add(centerLeft);

        JLabel lblKontonummer1 = new JLabel("Kontonummer 1:");
        lblKontonummer1.setHorizontalAlignment(SwingConstants.CENTER);
        centerLeft.add(lblKontonummer1);

        JLabel lblKontonummer2 = new JLabel("Kontonummer 2:");
        lblKontonummer2.setHorizontalAlignment(SwingConstants.CENTER);
        centerLeft.add(lblKontonummer2);

        JPanel centerRigth = new JPanel();
        centerRigth.setBackground(Color.LIGHT_GRAY);
        centerRigth.setLayout(new GridLayout(1, 1));
        center.add(centerRigth);

        JButton btnAnzeigen = new JButton("Anzeigen");
        centerRigth.add(btnAnzeigen);

        JPanel south = new JPanel();
        south.setBackground(Color.LIGHT_GRAY);
        south.setLayout(new GridLayout(0, 4));
        settings.add(south);

        JTextField txtKontonummer1Bottom = new JTextField();
        south.add(txtKontonummer1Bottom);
        txtKontonummer1Bottom.setColumns(10);

        JTextField txtKontonummer2Bottom = new JTextField();
        south.add(txtKontonummer2Bottom);
        txtKontonummer2Bottom.setColumns(10);

        JButton btnBuchen = new JButton("Buchen");
        south.add(btnBuchen);

        JButton btnueberweisen = new JButton("Überweisen");
        south.add(btnueberweisen);

        JTextArea consol = new JTextArea(5, 20);
        consol.setFont(new Font("Monospaced", Font.PLAIN, 11));
        consol.setForeground(Color.WHITE);
        consol.setBackground(Color.BLACK);
        consol.setEditable(false);
        consol.setCaretPosition(0);
        consol.setText("Startup...");
        contentPane.add(consol, BorderLayout.CENTER);

        btnNeuesGehaltskonto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    konten[counter] = new Gehaltskonto();
                    txtKontonummer1Top.setText("");
                    txtKontonummer2Top.setText("");
                    consol.setText("Neues Gehaltkonto: " + konten[counter].toString() + "\n" + consol.getText());
                    counter++;
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(GUI.this, "Gehaltkonto konnte nicht angelegt werden",
                            "Internal - Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNeuesSparkonto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = true;
                double ersteZahlung = -1;
                double sparrate = -1;
                try {
                    ersteZahlung = Double.parseDouble(txtKontonummer1Top.getText());
                    sparrate = Double.parseDouble(txtKontonummer2Top.getText());
                } catch (Exception e2) {
                    success = false;
                    JOptionPane.showMessageDialog(GUI.this, "Die benötigten Felder wurden nicht oder falsch gefüllt",
                            "Parse - Error", JOptionPane.ERROR_MESSAGE);
                }
                if (success) {
                    try {
                        konten[counter] = new Sparkonto(ersteZahlung, sparrate);
                        txtKontonummer1Top.setText("");
                        txtKontonummer2Top.setText("");
                        consol.setText("Neues Sparkonto: " + konten[counter].toString() + "\n" + consol.getText());
                        counter++;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        JOptionPane.showMessageDialog(GUI.this,
                                "Sparkonto konnte nicht angelegt werden, wahrscheinlich wurden die Felder falsch ausgefüllt",
                                "Input - Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnAnzeigen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = true;
                int index = -1;
                try {
                    index = Integer.parseInt(txtKontonummer1Bottom.getText());
                } catch (Exception e2) {
                    success = false;
                    JOptionPane.showMessageDialog(GUI.this, "Kontonummer konnte nicht ausgelesen werden",
                            "Parse - Error", JOptionPane.ERROR_MESSAGE);
                }
                if (success) {
                    try {
                        txtKontonummer1Bottom.setText("");
                        consol.setText("Anzeigen: " + konten[index].toString() + "\n" + consol.getText());
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(GUI.this, "Kontonummer nicht gefunden", "Input - Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnueberweisen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = true;
                int konto1 = -1;
                int konto2 = -1;
                double betrag = -1;
                try {
                    konto1 = Integer.parseInt(txtKontonummer1Bottom.getText());
                    konto2 = Integer.parseInt(txtKontonummer2Bottom.getText());
                    betrag = Double.parseDouble(txtKontonummer1Top.getText());
                    success = true;
                } catch (Exception e2) {
                    success = false;
                    JOptionPane.showMessageDialog(GUI.this, "Kontonummern oder Betrag konnten nicht ausgelesen werden",
                            "Parse - Error", JOptionPane.ERROR_MESSAGE);
                }
                if (success && konto1 == konto2) {
                    JOptionPane.showMessageDialog(GUI.this, "Die Kontonummern können nicht gleich sein",
                            "Input - Error", JOptionPane.ERROR_MESSAGE);
                    success = false;
                }
                if (success) {
                    try {
                        konten[konto1].ueberweisen(konten[konto2], betrag);
                        txtKontonummer1Bottom.setText("");
                        txtKontonummer2Bottom.setText("");
                        txtKontonummer1Top.setText("");
                        consol.setText("Überweisung vom Konto " + konto1 + " auf Konto " + konto2 + ", Betrag: "
                                + betrag + "; Status erfolgreich \n" + consol.getText());
                    } catch (KontoException e1) {
                        JOptionPane.showMessageDialog(GUI.this, "Ueberweisung war nicht erfolgreich", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        btnBuchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = true;
                int konto1 = -1;
                double betrag = -1;
                try {
                    konto1 = Integer.parseInt(txtKontonummer1Bottom.getText());
                    betrag = Double.parseDouble(txtKontonummer1Top.getText());
                    success = true;
                } catch (Exception e2) {
                    success = false;
                    JOptionPane.showMessageDialog(GUI.this, "Kontonummern oder Betrag konnten nicht ausgelesen werden",
                            "Parse - Error", JOptionPane.ERROR_MESSAGE);
                }
                if (success) {
                    try {
                        konten[konto1].buchen(betrag);
                        consol.setText("Buchung: " + konten[konto1].toString() + "\n" + consol.getText());
                        txtKontonummer1Bottom.setText("");
                        txtKontonummer1Top.setText("");
                    } catch (KontoException e1) {
                        JOptionPane.showMessageDialog(GUI.this, "Betrag ist nicht gültig", "Input - Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnAnzeigen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    Integer.parseInt(txtKontonummer1Bottom.getText());
                    txtKontonummer1Bottom.setBackground(Color.GREEN);
                } catch (Exception e2) {
                    txtKontonummer1Bottom.setBackground(Color.RED);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtKontonummer1Bottom.setBackground(Color.WHITE);
            }
        });

        btnBuchen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    Integer.parseInt(txtKontonummer1Bottom.getText());
                    txtKontonummer1Bottom.setBackground(Color.GREEN);
                } catch (Exception e2) {
                    txtKontonummer1Bottom.setBackground(Color.RED);
                }
                try {
                    Double.parseDouble(txtKontonummer1Top.getText());
                    txtKontonummer1Top.setBackground(Color.GREEN);
                } catch (Exception e2) {
                    txtKontonummer1Top.setBackground(Color.RED);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtKontonummer1Bottom.setBackground(Color.WHITE);
                txtKontonummer1Top.setBackground(Color.WHITE);
            }
        });

        btnNeuesSparkonto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    Double.parseDouble(txtKontonummer1Top.getText());
                    txtKontonummer1Top.setBackground(Color.GREEN);
                } catch (Exception e2) {
                    txtKontonummer1Top.setBackground(Color.RED);
                }
                try {
                    Double.parseDouble(txtKontonummer2Top.getText());
                    txtKontonummer2Top.setBackground(Color.GREEN);
                } catch (Exception e2) {
                    txtKontonummer2Top.setBackground(Color.RED);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtKontonummer1Top.setBackground(Color.WHITE);
                txtKontonummer2Top.setBackground(Color.WHITE);
            }
        });

        btnueberweisen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    Double.parseDouble(txtKontonummer1Top.getText());
                    txtKontonummer1Top.setBackground(Color.GREEN);
                } catch (Exception e2) {
                    txtKontonummer1Top.setBackground(Color.RED);
                }
                try {
                    Integer.parseInt(txtKontonummer1Bottom.getText());
                    txtKontonummer1Bottom.setBackground(Color.GREEN);
                } catch (Exception e2) {
                    txtKontonummer1Bottom.setBackground(Color.RED);
                }
                try {
                    Integer.parseInt(txtKontonummer2Bottom.getText());
                    txtKontonummer2Bottom.setBackground(Color.GREEN);
                } catch (Exception e2) {
                    txtKontonummer2Bottom.setBackground(Color.RED);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtKontonummer1Bottom.setBackground(Color.WHITE);
                txtKontonummer2Bottom.setBackground(Color.WHITE);
                txtKontonummer1Top.setBackground(Color.WHITE);
            }
        });
    }
}