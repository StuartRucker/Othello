import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Runner {
	public static void main(String[] args){
		JFrame frame = new JFrame("Slider Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int WIDTH = (int) dim.getHeight() - 30;
		int HEIGHT = (int) dim.getHeight() - 30;
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);

		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));

		Display d = new Display();
		d.size(WIDTH,HEIGHT);

		main.add(d);

		frame.setContentPane(d);
		frame.setVisible(true);
	}
}
