package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface IGameUI {
    
    void displayGameStart();
    
    int getCellIndex(IFieldViewer fv);
    
    Mark getMarkForPlayer();
    
    void displayField(IFieldViewer fv);
    
    void displayHelp();
    
    void displayCurrentMoveMark(IFieldViewer fv, Mark mark);
    
    void displayRoundResult(IFieldViewer fv, GameRoundResult grr);
    
}
