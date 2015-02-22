//does not work. Just my work so far...

import java.util.LinkedList;


public class GameState {
	Board b;
	LinkedList<GameState> children;
	double v;
	byte color;
	byte moveX;
	byte moveY;

	public GameState(Board newB, byte color1, byte x, byte y) {
		children = new LinkedList<GameState>();
		b = newB;
		v = 0;
		color = color1;
		moveX = x;
		moveY = y;
	}

	public LinkedList<GameState> findAllChildren() {
		// check each play location X,Y
		for (int x = 0; x < b.getWidth(); x++) {
			for (int y = 0; y < b.getHeight(); y++) {
				if (b.get(x, y) == 0) { // if an empty square
					Board a = getNextBoard(b, x, y, color);
					if (a != null) {
						children.add(new GameState(a, (byte)(-color), (byte)x, (byte)y));
					}
				}
			}
		}
		return children;
	}

	public double getScore() {
		// returns an (arbitrary) number denoting how good the board is
		int sum = 0;
		for (int x = 0; x < b.getWidth(); x++) {
			for (int y = 0; y < b.getHeight(); y++) {
				sum += b.get(x, y);
			}
		}
		sum += Math.random()/2;
		return sum;
	}

	// board configuration after playing a tile on board c, at position x, y
	public Board getNextBoard(Board c, int x, int y, byte color) {
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

	public void addValue(double v1) {
		v += v1;
	}

	public void avg() {
		v /= children.size();
		v += getScore();
		v /= 2;
	}

	public double getValue() {
		return v;
	}

	public byte getX() {
		return moveX;
	}

	public byte getY() {
		return moveY;
	}
}
