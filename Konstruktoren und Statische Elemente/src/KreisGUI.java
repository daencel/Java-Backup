import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class KreisGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static double rh = 0.0;
	private static double uh = 0.0;
	private static double fh = 0.0;

	public static void main(String[] args) {
		KreisGUI frame = new KreisGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Kreisberechnung");
		frame.setLocation(70, 70);
		frame.setSize(800,900);
		frame.setResizable(false);
		
		JPanel panel = (JPanel) frame.getContentPane();
	    panel.setLayout(null);
	    
		JLabel Rad = new JLabel("Radius:");
		frame.add(Rad);
		Rad.setBounds(100, 100, 300, 50);
		Rad.setFont(new Font("Courier New",Font.PLAIN, 50));
		
		JLabel Umf = new JLabel("Umfang:");
		frame.add(Umf);
		Umf.setBounds(100, 300, 300, 50);
		Umf.setFont(new Font("Courier New",Font.PLAIN, 50));
		
		JLabel Fla = new JLabel("Flaeche:");
		frame.add(Fla);
		Fla.setBounds(100, 500, 300, 50);
		Fla.setFont(new Font("Courier New",Font.PLAIN, 50));
		
		JTextField Radius = new JTextField();
		frame.add(Radius);
		Radius.setText("0.0");
		Radius.setBounds(400, 100, 300, 50);
		Radius.setFont(new Font("Courier New",Font.PLAIN, 50));
		
		JTextField Umfang = new JTextField();
		Umfang.setText("0.0");
		frame.add(Umfang);
		Umfang.setBounds(400, 300, 300, 50);
		Umfang.setFont(new Font("Courier New",Font.PLAIN, 50));
		
		JTextField Flaeche = new JTextField();
		Flaeche.setText("0.0");
		frame.add(Flaeche);
		Flaeche.setBounds(400, 500, 300, 50);
		Flaeche.setFont(new Font("Courier New",Font.PLAIN, 50));
		
		JButton solve = new JButton("solve");
		frame.add(solve);
		solve.setBounds(250, 650, 300, 70);
		solve.setFont(new Font("Courier New",Font.PLAIN, 50));
		
		frame.setVisible(true);
		
		solve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double radius = Double.parseDouble(Radius.getText());
					double umfang = Double.parseDouble(Umfang.getText());
					double flaeche = Double.parseDouble(Flaeche.getText());
					if (!(Math.abs(radius - rh) < 0.00001)) {
						Umfang.setText(Double.toString(Math.round(2*Math.PI*radius*100.0)/100.0));
						Flaeche.setText(Double.toString(Math.round(Math.PI*radius*radius*100.0)/100.0));
					}
					else {
						if (!(Math.abs(umfang - uh) < 0.00001)) {
							Radius.setText(Double.toString(Math.round((umfang/(2*Math.PI))*100.0)/100.0));
							Flaeche.setText(Double.toString(Math.round((umfang/(2*Math.PI))*(umfang/(2*Math.PI))*Math.PI*100.0)/100.0));
						}
						else if (!(Math.abs(flaeche - fh) < 0.00001)) {
							Radius.setText(Double.toString(Math.round(Math.sqrt(flaeche/Math.PI)*100.0)/100.0));
							Umfang.setText(Double.toString(Math.round(2*Math.PI*Math.sqrt(flaeche/Math.PI)*100.0)/100.0));
						}
					}
					rh = Double.parseDouble(Radius.getText());
					uh = Double.parseDouble(Umfang.getText());
					fh = Double.parseDouble(Flaeche.getText());
				} catch (NumberFormatException x) {
					System.out.println("ERROR");
					Radius.setText("0.0");
					Umfang.setText("0.0");
					Flaeche.setText("0.0");
					rh = Double.parseDouble(Radius.getText());
					uh = Double.parseDouble(Umfang.getText());
					fh = Double.parseDouble(Flaeche.getText());
				}

			}
		});
	}
}
