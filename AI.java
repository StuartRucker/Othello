
public class AI {
	GameState current;
	public AI(Board init) {
		current = new GameState(init, (byte)1);
		System.out.println(current.getBoard());
		initGenerateDepth(0, 6, current);
		computeValue(current);
	}

	//now each gamestate has pointers to all sub gamestates
	public void initGenerateDepth(int depth, int target, GameState root) {
		//System.out.println("generating depth =" + depth);
		if (depth == target) {
			return;
		}
		for (GameState child : root.findAllChildren()) {
			initGenerateDepth(depth + 1, target, child);
			
			if (depth == 0) {
				System.out.println("generating depth =" + depth);
				System.out.println(child.getBoard());
			}
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

	public Board bestMove(GameState root) {
		System.out.println("bestMove");
		double m = Double.MAX_VALUE;
		Board b = null;
		for (GameState c : root.getChildren()) {
			if (m > c.getValue()) { //Stuart wtf > was correct
				m = c.getValue();
				b = c.getBoard();
			}
		}
		return b;
	}

	public void playMove(Board b) {

	}
	public void addLayers(int layersToAdd) {

	}

	public GameState getCurrent() {
		return current;
	}

	public void update(Board b) {
		System.out.println("update");
		for (GameState s : current.getChildren()) {
			if (b.equals(s.getBoard())) {
				current = s;
				return;
			}
		}
		System.out.println("whoops");
		current = current.getChildren().get(0);
	}

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

