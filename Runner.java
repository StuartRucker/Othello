import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Runner implements ActionListener{
	static int WIDTH =0;
	static int HEIGHT = 0;
	static JFrame frame;
	static JMenu whosePlaying;
	static Display d;
	public static void main(String[] args) {
		frame = new JFrame("Slider Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) dim.getHeight() - 100;
		HEIGHT = (int) dim.getHeight() - 100;

		frame.setSize(WIDTH, HEIGHT + 40);
		frame.setResizable(false);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

		startStarterScreen();

        JMenuBar agf = new JMenuBar();
        whosePlaying = new JMenu("your Turn");
        whosePlaying.setEnabled(false);
        agf.add(whosePlaying);
        
        JMenu game = new JMenu("game");
        
        //restart
        JMenuItem restart = new JMenuItem("restart");
        restart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	d.setVisible(false);
            	Runner.startStarterScreen();
            }
        });
        game.add(restart);
        
        //quit
        JMenuItem quit = new JMenuItem("quit");
        quit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	System.exit(0);
            }
        });
        game.add(quit);
        
        //minigame
        JMenuItem minigame = new JMenuItem("minigame");
        minigame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	RunnerLife qwe = new RunnerLife();
            }
        });
        game.add(minigame);
        
        agf.add(game);
        frame.setJMenuBar(agf);
	}
	public static void startGame(int n){
		
		System.out.println("changing");
		d = new Display(n);
		d.size(WIDTH, HEIGHT);
		
		frame.setContentPane(d);
	}
	public static void startStarterScreen(){
		startPanel start = new startPanel(WIDTH,HEIGHT);
		frame.setContentPane(start);
		frame.setVisible(true);
	}
	public static void changePrompt(String s) {
		whosePlaying.setText(s);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
