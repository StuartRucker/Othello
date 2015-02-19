//does not work. Just my work so far...

import java.util.ArrayList;


public class GameState {
	Board b;
	byte color;
	ArrayList<GameState> children;

	public GameState(Board newB, byte col) {
		children = new ArrayList<GameState>(30);
		b = newB;
		color = col;
	}

	public ArrayList<GameState> findAllChildren() {
		// check each play location X,Y
		for (int X = 0; X < 8; X++) {
			for (int Y = 0; Y < 8; Y++) {
				if (b.get(X, Y) == 0) { // if an empty square
					Board a = getNextBoard(color, X, Y, b);
					if (a != null) {
						children.add(new GameState(a, (byte) -color));
					}
				}
			}
		}
		return children;
	}

	public int getScore() {
		// returns an arbitrary number denoting how good the board is
		int sum = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				sum += b.get(x, y);
			}
		}
		return sum;
	}

	// board configuration after playing a tile on board c, at position x, y
	public Board getNextBoard(byte color, int x, int y, Board c) {
		Board r = c.copy();
		boolean S = moveFlip(r, color, x, y, 1, 1);
		boolean T = moveFlip(r, color, x, y, 0, 1);
		boolean U = moveFlip(r, color, x, y, -1, 1);
		boolean A = moveFlip(r, color, x, y, 1, 0);
		boolean R = moveFlip(r, color, x, y, -1, 0);
		boolean P = moveFlip(r, color, x, y, 1, -1);
		boolean Q = moveFlip(r, color, x, y, 0, -1);
		boolean M = moveFlip(r, color, x, y, -1, -1);
		if (S || T || U || A || R || P || Q || M) {
			return r;
		} else {
			return null;
		}
	}

	private boolean moveFlip(Board r, byte color, int x, int y, int i, int j) {
		// go forward
		int checkX = x + i;
		int checkY = y + j;
		int passed = 0;
		while (r.get(checkX, checkY) == -color) {
			passed++;
			checkX += i;
			checkY += j;
		}
		if (passed > 0 && r.get(checkX, checkY) == color) {
			while (checkX != x && checkY != y) {
				r.set(checkX, checkY, color);
				checkX -= i;
				checkY -= j;
			}
			return true;

		} else {// no move found;
			return false;
		}

	}

	public ArrayList<GameState> getChildren() {
		return children;
	}

	public Board getBoard() {
		// TODO Auto-generated method stub
		return b;
	}
}
