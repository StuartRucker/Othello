class Test {
	public static void main(String[] args) {
		Board b = new Board();
		System.out.println(b.toString());
		b.place(4,3);
		b.place(3,3);
		b.place(3,4);
		b.place(4,4);
		System.out.println(b.toString());
	}
}