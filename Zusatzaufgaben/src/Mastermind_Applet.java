import java.applet.Applet;
import java.awt.*;
import java.awt.event.*; 



public class Mastermind_Applet extends Applet implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private TextField tfInput;
	private String tfOutput = "";
	private int nr = 0;
	String code = erzeugeCode(4,4);
	
	public void paint(Graphics g) {
		int p = 0;
		g.setColor(new Color(0,0,0));
		for (String line : tfOutput.split("_")) {
			g.drawString(line, 60, 125+p);
			p+=20;
			if (line.contains("ENDE")) {
				bremse(1000);
				System.exit(0);
				break;
			}
		}
	}
	
	
	@Override
	public void init() {
		setSize(1000,1000);
		this.setLayout(null);
		Label Master = new Label("Mastermind = Ein Code mit 4 Stellen wurde generiert");
		Master.setBounds(50,50, 400,20);
		this.add(Master);
		Label lInput = new Label("Bitte Tipp eingeben: ");
		lInput.setBounds(60,90, 120,15);
		this.add(lInput);
		tfInput = new TextField(4);
		tfInput.setBounds(200,88, 40,22);
		this.add(tfInput);
		tfInput.addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent e) {
		String Input = tfInput.getText();
		Input = Input.toUpperCase();
		if (Input == "ENDE") {
			nr++;
			tfOutput = tfOutput+"_"+(nr + "):  " + Input + " =	(w: " + ermittleWeiss(code,Input) + ", s: " + ermittleSchwarz(code,Input) + "): Ende");
			this.repaint();
		}
		else {
			if (Input.equals(code)) {
				nr++;
				tfOutput = tfOutput+"_"+(nr + "):  " + Input + " =	(w: " + ermittleWeiss(code,Input) + ", s: " + ermittleSchwarz(code,Input) + "): Code gefunden \n Ein neuer Code wurde generiert, Tipp erneut eingeben");
				code = erzeugeCode(4,4);
				this.repaint();
				nr = 0;
			}
			else {
				nr++;
				tfOutput = tfOutput+"_"+(nr + "):  " + Input + " =	(w: " + ermittleWeiss(code,Input) + ", s: " + ermittleSchwarz(code,Input) + ")");
				this.repaint();
			}
		}
		tfInput.setText("");
	}
	
	
	/**
	 * Errechnet die Buchstaben, die sich am richtigen Platz befinden.
	 * Eingespielt wird der Code und der Tipp, welcher vom Benutzer eingegeben wird.
	 * @param code Code
	 * @param tipp Tipp, welcher vom Benutzer eingegeben wurde
	 * @param return Buchstaben, die sich am richtigen Platz befinden
	 */
	public int ermittleSchwarz(String code, String tipp) {
		int o = 0;
		if (code.length() == tipp.length() && enthaeltDoppelte(tipp) == false) {
			for (int i = 0; i < code.length(); i++) {
				if (code.charAt(i) == tipp.charAt(i)) {
					o++;
				}
			}
		}
		else {
			o = -1;
		}
		return o;
	}
	
	/**
	 * Errechnet die Buchstaben, die sich am falschem Platz befinden.
	 * Eingespielt wird der Code und der Tipp, welcher vom Benutzer eingegeben wird.
	 * @param tode Code
	 * @param tipp Tipp, welcher vom Benutzer eingegeben wurde
	 * @param return Buchstaben, die sich am falschem Platz befinden
	 */
	public int ermittleWeiss(String code, String tipp) {
		int o = 0;
		if (code.length() == tipp.length() && enthaeltDoppelte(tipp) == false) {
			for (int i = 0; i < code.length(); i++) {
				if (code.charAt(i) != tipp.charAt(i) && code.contains(Character.toString(tipp.charAt(i)))) {
					o++;
				}
			}
		}
		else {
			o = -1;
		}
		return o;
	}
	
	/**
	 * Kontrolliert ob im String srt doppelte Buchstaben vorkommen.
	 * False wird bei keinen doppelten Buchstaben und true bei doppelt
	 * oder mehr vorkommenden gleichen Buchstaben zurueckgegeben
	 * @param str String zu kontrollieren
	 * @return false oder true
	 */
	public boolean enthaeltDoppelte (String str) {
		str = str.toUpperCase();
		for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return true; 
                }
            }
		}
        return false; 
    }
	
	/**
	 * Erzeugt einen zufaelligen String mit int Stellen und
	 * int Farben Moeglichkeiten. Ein Farbencode kann nicht ein
	 * zweites mal im Code vorkommen. Die Laenge muss laenger oder gleich 
	 * den Moeglichkeiten von farben sein, sonst wird null uebertragen.
	 * @param stellen Anzahl Stellen des erzeugenden Codes
	 * @param farben Anzahl Moeglichkeiten
	 * @return Code
	 */
	public String erzeugeCode (int stellen, int farben) {
		String ausgabe = "";
		if (stellen > farben) {
			ausgabe = null;
		}
		else {
			while (stellen > 0) {
				String ran = Character.toString(((char)((Math.random()*farben+1)+64)));
				while (ausgabe.contains(ran)) {
					ran = Character.toString(((char)((Math.random()*farben+1)+64)));
				}
				ausgabe = ran + ausgabe;
				stellen--;
			}
		}
		return ausgabe;
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
}