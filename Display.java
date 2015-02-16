import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;

public class Display extends JPanel implements MouseListener{
	int width, height;
	int squareWidth, squareHeight;
	int[][] board;
	int turn = 0;
	public Display() {
		addMouseListener(this);
		setBackground(new Color(65,140,35));
		board = new int[8][8];
	}
	public void size(int x, int y){
		this.setPreferredSize(new Dimension(x,y));
		width = x;
		height = y;
		squareWidth = x/8;
		squareHeight = y/8;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for (int x = squareWidth; x < width-4; x += squareWidth) {
			g.fillRect(x,0, 4, height);
		}
		for (int y = squareHeight; y < height-4; y += squareWidth) {
			g.fillRect(0,y,width, 4);
		}
		for(int x = 0; x < 8; x ++){
			for(int y = 0; y < 8; y ++){
				//piece is white
				if(board[x][y] == 1){
					g.setColor(new Color(242,235,201));
					g.fillOval(x * squareHeight + 4, y* squareWidth + 4, squareWidth - 4, squareHeight - 4);
				}
				else if(board[x][y] == 2){
					g.setColor(Color.BLACK);
					g.fillOval(x * squareHeight + 4, y* squareWidth + 4, squareWidth - 4, squareHeight - 4);
				}
	
			}
		}
		
	}
	public boolean isValidMove(int x, int y){
		//TODO
		//Make sure the move is not cheating
		return true;
	}
	public int[] CPUDecideMove(){
		//TODO
		//return the coordinates that the cpu would like to make
		//This is the gut!
		Random gen = new Random();
		return new int[]{gen.nextInt(7),gen.nextInt(7)};
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		turn++;
		int x = (e.getX())/squareWidth;
		int y = (e.getY())/squareHeight;
		
		if(isValidMove(x,y)){
			board[x][y] = 1;
			//play CPU 
			int[] CPU = CPUDecideMove();
			board[CPU[0]][CPU[1]] = 2;
		}
		
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
