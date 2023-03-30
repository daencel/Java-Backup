package Snake;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {

	private static final long serialVersionUID = 1L;

	public Snake() {
        add(new Board());
        setSize(1300, 1300);
        pack();
        setResizable(false);
        setTitle("Snake - Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new Snake();
                ex.setVisible(true); 
                ex.setSize(1300,1300);
            }
        });
    }
}