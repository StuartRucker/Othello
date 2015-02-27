import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


public class Runner {
	static int WIDTH =0;
	static int HEIGHT = 0;
	static JFrame frame;
	static JMenu whosePlaying;
	public static void main(String[] args) {
		frame = new JFrame("Slider Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) dim.getHeight() - 100;
		HEIGHT = (int) dim.getHeight() - 100;

		frame.setSize(WIDTH, HEIGHT + 40);
		frame.setResizable(false);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

		startPanel start = new startPanel(WIDTH,HEIGHT);
		
		 
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));

		/*Display d = new Display();
		d.size(WIDTH, HEIGHT);*/

		main.add(start);
		//main.add(d);

		frame.setContentPane(start);
		frame.setVisible(true);

        JMenuBar agf = new JMenuBar();
        whosePlaying = new JMenu("your Turn");
        whosePlaying.setEnabled(false);
        agf.add(whosePlaying);
        frame.setJMenuBar(agf);
	}
	public static void startGame(int n){
		
		System.out.println("changing");
		Display d = new Display(n);
		d.size(WIDTH, HEIGHT);
		
		frame.setContentPane(d);
	}

	public static void changePrompt(String s) {
		whosePlaying.setText(s);
	}
}
