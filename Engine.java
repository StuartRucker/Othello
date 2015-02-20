class Engine {

	public Engine() {

	}

	public static boolean capture(Board r, int x, int y, byte color) {
		//Span outwards from point
		boolean S = moveFlip(r,color,x,y,1,1);
		boolean T = moveFlip(r,color,x,y,0,1);
		boolean U = moveFlip(r,color,x,y,-1,1);
		boolean A = moveFlip(r,color,x,y,1,0);
		boolean R = moveFlip(r,color,x,y,-1,0);
		boolean P = moveFlip(r,color,x,y,1,-1);
		boolean Q = moveFlip(r,color,x,y,0,-1);
		boolean M = moveFlip(r,color,x,y,-1,-1);
		if(S||T||U||A||R||P||Q||M){
			return true;
		}
		else{
			return false;
		}
	}

	private static boolean moveFlip(Board r,byte color, int x, int y, int i, int j) {
		//go forward
		int checkX = x + i;
		int checkY = y + j;
		int passed = 0;
		while(r.get(checkX,checkY) == -color){
			//System.out.println("in while loop: "+ (checkX+1) + " "+ (checkY+1));
			passed ++;
			checkX += i;
			checkY += j;
		}
		//System.out.println("passed: " + passed + ", color " +r.get(checkX,checkY));
		if(passed> 0 && r.get(checkX,checkY) == color){
			while(checkX != x || checkY != y){
				r.set(checkX,checkY,color);
				
				checkX -= i;
				checkY -= j;
			}
			r.set(x,y,color);
			return true;
		
		}
		else{//no move found;
			return false;
		}
		
	}
}
