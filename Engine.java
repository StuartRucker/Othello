class Engine {

	public Engine() {

	}

	public void capture(Board b, int i, int j) {
		//Span outwards from point
		byte p2 = (byte) (-b.get(i,j));
		moveFlip(b,i,j, 1, 0,p2);
		moveFlip(b,i,j,-1, 0,p2);
		moveFlip(b,i,j, 0, 1,p2);
		moveFlip(b,i,j, 0,-1,p2);
		moveFlip(b,i,j, 1, 1,p2);
		moveFlip(b,i,j, 1,-1,p2);
		moveFlip(b,i,j,-1, 1,p2);
		moveFlip(b,i,j,-1,-1,p2);
	}

	private void moveFlip(Board b, int x1, int y1, int i, int j, byte p2) {
		int x = x1;
		int y = y1;
		do {
			x+=i;
			y+=j;
		} while (b.get(x,y) == (byte)(p2));
		if (b.get(x,y) == (byte)(-p2)) {
			x-=i;
			y-=j;
			while (b.get(x,y) == (byte)(p2)) {
				b.flip(x,y);
				x-=i;
				y-=j;
			}
		}
	}
}