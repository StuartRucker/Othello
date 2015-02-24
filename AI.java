public class AI {

	GameState current;

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

	public double computeValue(GameState root) {
		//will be superseded by minimax
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
		double m = Double.MAX_VALUE;
		Board b = null;
		for (GameState c : root.getChildren()) {
			if (m > c.getValue()) {
				m = c.getValue();
				b = c.getBoard();
				current = c;
			}
		}
		return b;
	}

	public GameState getCurrent() {
		return current;
	}

	public void update(Board b, byte x, byte y) {
		for (GameState s : current.getChildren()) {
			if (s.getX() == x && s.getY() == y) {
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

	public double minimax(GameState root, boolean mplayer) {
		//Copy of wikipedia algorithm translated to java
		if (root.getChildren().isEmpty()) {//change to isTerminal later
			root.setValue(root.getScore());
			return root.getScore();
		}
		if (mplayer) {
			double bestValue = Double.MIN_VALUE;
			for (GameState s : root.getChildren()) {
				double val = minimax(s, false);
				bestValue = Math.max(bestValue, val);
			}
			root.setValue(bestValue);
			return bestValue;
		} else {
			double bestValue = Double.MAX_VALUE;
			for (GameState s : root.getChildren()) {
				double val = minimax(s, true);
				bestValue = Math.min(bestValue, val);
			}
			root.setValue(bestValue);
			return bestValue;
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

