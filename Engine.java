class Engine {

	public Engine() {

	}

	public boolean capture(Board r, int x, int y, byte color) {
		// Span outwards from point

		boolean S = moveFlip(r, color, x, y, 1, 1);
		boolean T = moveFlip(r, color, x, y, 0, 1);
		boolean U = moveFlip(r, color, x, y, -1, 1);
		boolean A = moveFlip(r, color, x, y, 1, 0);
		boolean R = moveFlip(r, color, x, y, -1, 0);
		boolean P = moveFlip(r, color, x, y, 1, -1);
		boolean Q = moveFlip(r, color, x, y, 0, -1);
		boolean M = moveFlip(r, color, x, y, -1, -1);
		if (S || T || U || A || R || P || Q || M) {
			return true;
		} else {
			return false;
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
			while (checkX != x || checkY != y) {
				r.set(checkX, checkY, color);

				checkX -= i;
				checkY -= j;
			}
			r.set(x, y, color);
			return true;

		} else {// no move found;
			return false;
		}

	}

	public boolean canPLay(Board r, byte color) {
		Board b = r.copy();
		boolean canPlay = false;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (!canPlay && b.get(x, y) == 0)
					if (capture(b, x, y, color)){
						canPlay = true;
						System.out.println("you can play at "+ (x+1) + " "+(1+y));
					}
			}
		}
		return canPlay;

	}
}
