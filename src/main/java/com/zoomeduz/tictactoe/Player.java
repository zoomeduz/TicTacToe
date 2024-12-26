package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface Player {
    
    void onGameStarted(FieldViewer fv);
    
    void onGameFinished(GameRoundResult grr);

    int getMove();
    
    void onMoveProcessed(MoveResult mr);

}
