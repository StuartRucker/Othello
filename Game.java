class Game {
	private Board b;
	private AI CPU;

	public Game() {
		this(8, 8);
	}

	public Game(int w, int h) {
		b = new Board(w, h);
		CPU = new AI(b);
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

	/*public boolean CPUPLay(){
	    try {
	        Thread.sleep(1000);
	    } catch (InterruptedException e1) {
	        e1.printStackTrace();
	    }
	    boolean played = false;
	    for (int x = 0; x < 8; x ++) {
	        for (int y = 0; y < 8; y ++) {
	            if (!played && b.get(x, y) == 0) {
	                if (Engine.capture(b, x, y, (byte)(-1))) {
	                   played = true;
	                }
	            }
	        }
	    }
	    return played;
	}*/

	public boolean CPUPLay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		boolean played = false;
		CPU.computeValue(CPU.getCurrent());
		b = CPU.bestMove(CPU.getCurrent());
		return played;
	}

	public void endGame() {
		// TODO Auto-generated method stub

	}

	public int getScore(byte c) {
		int score = 0;
		for (int x = 0; x < 8; x ++) { //sorry oscar, i will never make my code repurpose-able
			for (int y = 0; y < 8; y ++) {
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
