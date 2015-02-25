/*
* @Board.java
* @Authors: Stuart Rucker, Oscar Suen, Vinayak Kurup
*
* The entire board at any time-- represented using byte array
*/

class Board {
	private int width;
	private int height;
	private byte[][] array; //black = 1, white = -1, unfilled = 0
	private boolean player; //black true, white false
	//private int turn; //necessary? int?

	public Board() { //default constructor
		this(8, 8);
	}

	public Board(int w, int h) {
		width = w;
		height = h;
		array = new byte[w][h];
		player = true;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				array[i][j] = 0;
			}
		}
		place(4, 3);
		place(3, 3);
		place(3, 4);
		place(4, 4);
	}

	public byte get(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return array[x][y];
		} else {
			return Byte.MAX_VALUE;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean getPlayer() {
		return player;
	}

	public void setPlayer(boolean p) {
		player = p;
	}

	public byte getPlayeri() {
		return player ? (byte)1 : (byte)(-1);
	}

	public void place(int x, int y) { //I could be wrong-- but this doens't work if I understand how it's supposed to
		if (x == -1 && y == -1) {
			//unable to place
			player = !player;
			return;
		}
		if (array[x][y] == 0) {
			if (player) {
				array[x][y] = 1;
			} else if (!player) {
				array[x][y] = -1;
			}
			player = !player;
		} else {
			player = !player;
		}
	}

	public void revert(int x, int y) {
		array[x][y] = 0;
		player = !player;
	}

	public void flip(int x, int y) {
		array[x][y] = (byte)(-array[x][y]);
	}

	public void set(int x, int y, byte b) {
		array[x][y] = b;
	}

	public Board copy() {
		Board s = new Board(width, height);
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y ++) {
				s.set(x, y, array[x][y]);
			}
		}
		s.setPlayer(getPlayer());
		return s;
	}

	public String toString() {
		String rtn = "";
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (array[i][j] == 0) {
					rtn += "-";
				} else if (array[i][j] == 1) {
					rtn += "X";
				} else if (array[i][j] == -1) {
					rtn += "O";
				}
			}
			rtn += "\n";
		}
		return rtn;
	}

	public boolean equals(Board b) {
		return array.equals(b.getArray());
	}

	public byte[][] getArray() {
		return array;
	}

	public double win() {
		boolean b = false;
		boolean w = false;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (array[i][j] == (byte)(1)) {
					b = true;
				} else if (array[i][j] == (byte)(-1)) {
					w = true;
				}
				if (b && w) {
					return 0;
				}
			}
		}
		if (b) {
			return Double.POSITIVE_INFINITY;
		}
		return Double.NEGATIVE_INFINITY;
	}

}
