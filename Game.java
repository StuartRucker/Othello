class Game {
    private Board b;
    private AI CPU;

    public Game() {
        this(8,8);
    }

    public Game(int w, int h) {
        b = new Board(w,h);
        CPU = new AI(b,(byte)-1);
    }

    public boolean play(int i, int j) {
	     if(b.get(i, j) == 0){ 
    		if (Engine.capture(b,i,j,(byte)1)) {
	        	return true;
	        } else {
	            System.out.println("Illegal Move");
	            return false;
	        }
	     }
	     else{
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

    public void CPUPLay(){
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
    	boolean played = false;
    	for(int x = 0; x < 8; x ++){
    		for(int y = 0; y < 8; y ++){
    			if(!played && b.get(x, y) == 0)
    				if(Engine.capture(b, x, y, (byte)-1)) played = true;
    		}
    	}
    }
    
        
}
