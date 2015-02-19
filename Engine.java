class Engine {

	public Engine() {

	}

	public boolean capture(Board b, int i, int j) {
		//Span outwards from point
		boolean m1 = moveFlip(b,i,j, 1, 0);
		boolean m2 = moveFlip(b,i,j,-1, 0);
		boolean m3 = moveFlip(b,i,j, 0, 1);
		boolean m4 = moveFlip(b,i,j, 0,-1);
		boolean m5 = moveFlip(b,i,j, 1, 1);
		boolean m6 = moveFlip(b,i,j, 1,-1);
		boolean m7 = moveFlip(b,i,j,-1, 1);
		boolean m8 = moveFlip(b,i,j,-1,-1);
		return m1 || m2 || m3 || m4 || m5 || m6 || m7 || m8;
	}

	private boolean moveFlip(Board b, int x1, int y1, int i, int j) {
		//Problems when placing right next to same color
		//Need to implement border check
		int x = x1;
		int y = y1;
		byte p1 = b.get(x1,y1);
		byte p2 = (byte) (-p1);
		int c = 0;
		do {
			x+=i;
			y+=j;
			c++;
		} while (b.get(x,y) == p2 && c > 1);
		if (b.get(x,y) == p1) {
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