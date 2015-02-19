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
        //return true;
    }

    public void print() {
        System.out.println(b.toString());
    }
    public Board getBoard(){
        return b;
    }
    public boolean isValidMove(int x, int y){
        //to do
        return true;
    }
        
}
