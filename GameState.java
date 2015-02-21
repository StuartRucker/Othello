//does not work. Just my work so far...

import java.util.LinkedList;


public class GameState {
	Board b;
	LinkedList<GameState> children;

	public GameState(Board newB) {
		children = new LinkedList<GameState>();
		b = newB;
	}

	public LinkedList<GameState> findAllChildren() {
		// check each play location X,Y
		for (int X = 0; X < b.getWidth(); X++) {
			for (int Y = 0; Y < b.getHeight(); Y++) {
				if (b.get(X, Y) == 0) { // if an empty square
					Board a = getNextBoard(b.getPlayeri(), X, Y, b);
					if (a != null) {
						children.add(new GameState(a));
					}
				}
			}
		}
		return children;
	}

	public int getScore() {
		// returns an (arbitrary) number denoting how good the board is
		int sum = 0;
		for (int x = 0; x < b.getWidth(); x++) {
			for (int y = 0; y < b.getHeight(); y++) {
				sum += b.get(x, y);
			}
		}
		return sum;
	}

	// board configuration after playing a tile on board c, at position x, y
	public Board getNextBoard(byte color, int x, int y, Board c) {
		Board r = c.copy();
		if (Engine.capture(r, x, y, color)) {
			return r;
		} else {
			return null;
		}
	}

	public LinkedList<GameState> getChildren() {
		return children;
	}

	public Board getBoard() {
		return b;
	}
}
