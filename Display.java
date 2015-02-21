import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Display extends JPanel implements MouseListener {
    int width, height;
    int squareWidth, squareHeight;
    boolean CPUhasNoMove;
    Game g;
    public Display() {
        addMouseListener(this);
        setBackground(new Color(65, 140, 35));
        g = new Game();

    }
    public void size(int x, int y) {
        this.setPreferredSize(new Dimension(x, y));
        width = x;
        height = y;
        squareWidth = x / 8;
        squareHeight = y / 8;
    }

    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        graph.setColor(Color.BLACK);
        for (int x = squareWidth; x < width - 4; x += squareWidth) {
            graph.fillRect(x, 0, 4, height);
        }
        for (int y = squareHeight; y < height - 4; y += squareWidth) {
            graph.fillRect(0, y, width, 4);
        }
        for (int x = 0; x < 8; x ++) {
            for (int y = 0; y < 8; y ++) {
                //piece is white

                if (g.getBoard().get(x, y) == -1) {
                    graph.setColor(new Color(242, 235, 201));
                    graph.fillOval(x * squareHeight + 4, y * squareWidth + 4, squareWidth - 4, squareHeight - 4);
                } else if (g.getBoard().get(x, y) == 1) {
                    graph.setColor(Color.BLACK);
                    graph.fillOval(x * squareHeight + 4, y * squareWidth + 4, squareWidth - 4, squareHeight - 4);
                }

            }
        }

    }




    @Override
    public void mouseReleased(MouseEvent e) {

        int x = (e.getX()) / squareWidth;
        int y = (e.getY()) / squareHeight;

        if (g.play(x, y)) {
            paintComponent(getGraphics());
            g.CPUPLay();
            paintComponent(getGraphics());
        }


        boolean c = g.canPLay((byte) (-1));
        boolean u = g.canPLay((byte) (1));
        while (!u && c) { //if only the cpu can play
            g.CPUPLay();
            c = g.canPLay((byte) (-1));
            u = g.canPLay((byte) (1));
        }
        if (!u && !c) {
            gg();
        }
    }
    public void gg() {

        int b = g.getScore((byte)1);
        int w = g.getScore((byte)(-1));
        String whoWins = "NULL";
        //these don't work for some reason
        if (b > w)whoWins = "you win!\n";
        if (b < w)whoWins = "you lose!\n";
        if (w == b) whoWins = "tie\n";

        whoWins += "you have " + b + " tiles, CPU has " + w;
        JOptionPane.showMessageDialog(new JFrame(), whoWins);
        g.endGame();
        g = new Game();
        paintComponent(getGraphics());

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}

