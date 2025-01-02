package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface IObserver {
    
	void onGameRoundStarted(IFieldViewer fv);
    
	void onGameRoundFinished(GameRoundResult grr);
    
    void onMoveBefore(Mark mark);
    
	void onMoveDone(int cellIndex, Mark mark);

}
