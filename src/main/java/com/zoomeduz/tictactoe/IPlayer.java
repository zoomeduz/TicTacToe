package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface IPlayer {
    
    void onGameStarted(IFieldViewer fv);
    
    void onGameFinished(GameRoundResult grr);

    int getMove();
    
    void onMoveProcessed(MoveResult mr);

}
