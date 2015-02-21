
public class AI {
	GameState current;
	public AI(Board init){
		current = new GameState(init, (byte)1); 
		initGenerateDepth(2,current);
	}
	
	//now each gamestate has pointers to all sub gamestates
	public void initGenerateDepth(int depth, GameState root){
		System.out.println("generating depth =" + depth);
		if(depth == 0)return;
		for(GameState child: root.findAllChildren()){
			System.out.println(child.getBoard());
			initGenerateDepth(depth-1,child);
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
			return root.getScore();
		} else {
			int rtn = 0;
			for (GameState s : root.getChildren()) {
				root.addValue(computeValue(s));
			}
			root.avg();
		}
		return root.getValue();
	}

	public Board bestMove(GameState root) {
		double m = Double.MAX_VALUE;
		Board b = null;
		for (GameState c : root.getChildren()) {
			if (m < c.getValue()) {
				m = c.getValue();
				b = c.getBoard();
			}
		}
		return b;
	}
	
	public void playMove(Board b){
		
	}
	public void addLayers(int layersToAdd){
		
	}
	
	public GameState getCurrent() {
		return current;
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

