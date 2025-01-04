package com.zoomeduz.tictactoe;

import java.util.Set;

/**
 *
 * @author zoomeduz
 */
interface IGameUI {
    
    void displayGameStart();
    
    int getCellIndex(IFieldViewer fv, Set<Integer> availCellIndexes); //убрать availCellIndexes
    
    Mark getMarkForPlayer();
    
    void displayField(IFieldViewer fv);
    
    void displayFieldWithHelp(IFieldViewer fv);
    
    void displayCurrentMoveMark(IFieldViewer fv, Mark mark);
    
    void displayRoundResult(IFieldViewer fv, GameRoundResult grr);
    
}
