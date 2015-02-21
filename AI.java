
public class AI {
	GameState current;
	public AI(Board init, byte color){
		current = new GameState(init);
	}
	
	//now each gamestate has pointers to all sub gamestates
	public void initGenerateDepth(int depth, GameState root){
		if(depth == 0)return;
		for(GameState child: root.findAllChildren()){
			initGenerateDepth(depth-1,child);
		}
	}
	
	public Board bestMove(){
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
	}
	
	public void playMove(Board b){
		
	}
	public void addLayers(int layersToAdd){
		
	}
	
	
	
	public int getLowestScore(GameState root){
		//end case
		if(root.getChildren().get(0) == null){
			return root.getScore();
		}
		int min = Integer.MAX_VALUE;
		for(GameState s:root.getChildren()){
			min = Math.min(min, getLowestScore(s));
		}
		return min;
	}
	
}

