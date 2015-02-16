class Engine {

	public Engine() {

	}

	public void capture(Board b, int i, int j) {
		//Span outwards from point
		int x = i;
		int y = j;
		byte p1 = b.get(i,j);
		byte p2 = (byte) (-p1);
		do {
			x++;
			y++;
		} while (b.get(x,y) == (byte)(p2);
		x--;
		y--;
		while (b.get(x,y) == (byte)(p2) {
			//b.flip(--x,--y);
			x--;
			y--;
			b.flip(x,y);
		}
	}
}