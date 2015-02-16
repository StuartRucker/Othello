class Engine {

	public Engine() {

	}

	public boolean capture(Board b, int i, int j) {
		//Span outwards from point
		return
		moveFlip(b,i,j, 1, 0) ||
		moveFlip(b,i,j,-1, 0) ||
		moveFlip(b,i,j, 0, 1) ||
		moveFlip(b,i,j, 0,-1) ||
		moveFlip(b,i,j, 1, 1) ||
		moveFlip(b,i,j, 1,-1) ||
		moveFlip(b,i,j,-1, 1) ||
		moveFlip(b,i,j,-1,-1);
	}

	private boolean moveFlip(Board b, int x1, int y1, int i, int j) {
		//Problems when placing right next to same color
		int x = x1;
		int y = y1;
		byte p1 = b.get(i,j);
		byte p2 = (byte) (-p1);
		int c = 0;
		do {
			x+=i;
			y+=j;
			c++;
		} while (b.get(x,y) == p2);
		if (b.get(x,y) == p1 && c > 1) {
			x-=i;
			y-=j;
			while (b.get(x,y) == p2) {
				b.flip(x,y);
				x-=i;
				y-=j;
			}
			return true;
		} else {
			return false;
		}
	}
}