class Board {
	private int width;
	private int height;
	private byte[][] array; //black 1, white -1
	private boolean player; //black true, white false
	private int turn; //necessary? int?

	public Board() {
		this(8,8);
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
	}

	public byte get(int x, int y) {
		return array[x][y];
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

	public void place(int x, int y) {
		if (x == -1 && y == -1) {
			player = !player;
			return;
		}
		if (player) {
			array[x][y] = 1;
		} else if (!player) {
			array[x][y] = -1;
		}
		player = !player;
	}

	public void flip(int x, int y) {
		array[x][y] = (byte) -array[x][y];
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

}