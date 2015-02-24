/*
*
* @Author: Vinayak Kurup, Stuart Rucker, Oscar Suen
* @Version: February 24, 2015
*
* AI Class-- runs the AI for the game
*/

public class AI {
	GameState current;
	//Initializes Board
	public AI(Board init, int depth) {
		current = new GameState(init, (byte)1, (byte)(-1), (byte)(-1));
		generateDepth(0, depth, current);
		computeValue(current);
	}

	//now each gamestate has pointers to all sub gamestates
	//what does generating the depth do?
	public void generateDepth(int depth, int target, GameState root) {
		//System.out.println("generating depth =" + depth);
		if (depth == target) {
			return;
		}
		for (GameState child : root.findAllChildren()) {
			generateDepth(depth + 1, target, child);
		}
	}

	//Computes the value of the board by iterating through GameState children
	public double computeValue(GameState root) {
		if (root.getChildren().isEmpty()) {
			root.addValue(root.getScore());
			//System.out.println("Leaf Score: " + root.getScore());
			return root.getScore();
		} else {
			int rtn = 0;
			for (GameState s : root.getChildren()) {
				root.addValue(computeValue(s));
			}
			root.avg();
			//System.out.println("Internal Value: " + root.getValue());
			return root.getValue();
		}
	}
	
	//Returns the board that has the best move (relying on computeValue()
	public Board bestMove(GameState root) {
		double min = Double.MAX_VALUE;
		Board b = null;
		for (GameState c : root.getChildren()) 
		{
			if (min > c.getValue()) 
			{ //Stuart wtf > was correct
				min = c.getValue();
				b = c.getBoard();
				current = c;
			}
		}
		return b;
	}

	//Current gamestate
	public GameState getCurrent() 
	{
		return current;
	}

	public void update(Board b, byte x, byte y) 
	{
		for (GameState s : current.getChildren()) 
		{
			if (s.getX() == x && s.getY() == y) 
			{ //Why no worky-worky... HMM let's figure it out
				current = s;
				return;
			}
		}
		System.out.println("No children equal to current board position");
		current = current.getChildren().get(0);
	}

	public void addLayers(GameState root, int i) {
		if (root.getChildren().isEmpty()) {
			generateDepth(0, i, root);
			return;
		}
		for (GameState s : root.getChildren()) {
			addLayers(s, i);
		}
	}

	/*public Board bestMove(){
	    int bestWorstCaseScenario = Integer.MIN_VALUE;
	    Board b = new Board();
	    for(GameState x: current.getChildren()){
	        int lowScore = getLowestScore(x);
	        if(bestWorstCaseScenario < lowScore){
	            b = x.getBoard();;
	            bestWorstCaseScenario = lowScore;
	        }
	    }
	    return b;
	}*/

	/*public int getLowestScore(GameState root){
	    //end case
	    if(root.getChildren().get(0) == null){ //Bad code wtf stuart
	        return root.getScore();
	    }
	    int min = Integer.MAX_VALUE;
	    for(GameState s:root.getChildren()){
	        min = Math.min(min, getLowestScore(s));
	    }
	    return min;
	}*/

}

