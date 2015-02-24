class Game {
	private Board b;
	private AI CPU;

	public Game() {
		this(8, 8, 6);
	}

	public Game(int w, int h, int l) {
		b = new Board(w, h);
		CPU = new AI(b, l);
	}

	public boolean play(int i, int j) {
		if (b.get(i, j) == 0) {
			if (Engine.capture(b, i, j, (byte)1)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void print() {
		System.out.println(b.toString());
	}
	
	public Board getBoard() {
		return b;
	}

	public boolean CPUPLay(byte x, byte y) {
		/*try {
		    Thread.sleep(1000);
		} catch (InterruptedException e1) {
		    e1.printStackTrace();
		}*/
		boolean played = false;
		CPU.update(b, x, y);
		CPU.minimax(CPU.getCurrent(),false);
		b = CPU.bestMove(CPU.getCurrent());
		CPU.addLayers(CPU.getCurrent(), 2);
		return played;
	}

	public int getScore(byte c) {
		int score = 0;
		for (int x = 0; x < b.getWidth(); x++) {
			for (int y = 0; y < b.getHeight(); y++) {
				if (b.get(x, y) == c) {
					score += 1;
				}
			}
		}
		return score;
	}

	public boolean canPLay(byte c) {
		return Engine.canPLay(b, c);
	}
}
