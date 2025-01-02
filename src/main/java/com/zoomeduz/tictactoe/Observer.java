package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface Observer {
    
	void onGameRoundStarted(FieldViewer fv);
    
	void onGameRoundFinished(GameRoundResult grr);
    
    void onMoveBefore(Mark mark);
    
	void onMoveDone(int cellIndex, Mark mark);

}
