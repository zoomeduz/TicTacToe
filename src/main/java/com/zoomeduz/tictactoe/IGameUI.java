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

    void displayMove(IFieldViewer fv, Mark mark);

    void displayRoundResult(GameRoundResult grr);

}
