class Game {
	private Board b;
	private Engine e;
	//private AI ai;

	public Game() {
		this(8,8);
	}

	public Game(int w, int h) {
		b = new Board(w,h);
		e = new Engine();
		//ai = new AI();
	}

	public boolean play(int i, int j) {
		b.place(i,j);
		if (e.capture(b,i,j)) {
			return true;
		} else {
			System.out.println("Illegal Move");
			b.revert(i,j);
			return false;
		}
	}

	public void print() {
		System.out.println(b.toString());
	}
}